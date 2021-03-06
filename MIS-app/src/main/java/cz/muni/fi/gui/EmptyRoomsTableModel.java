package cz.muni.fi.gui;

import cz.muni.fi.MIS.Main;
import cz.muni.fi.MIS.Reservation;
import cz.muni.fi.MIS.ReservationManager;
import cz.muni.fi.MIS.Room;
import cz.muni.fi.MIS.RoomManager;
import java.time.LocalDate;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.SwingWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by V.Mecko on 3.5.2017.
 */
public class EmptyRoomsTableModel extends AbstractTableModel {
    private static final ResourceBundle texts = ResourceBundle.getBundle("texts");
    public static final int ROOM_COLUMNS = 3;
    
    protected ApplicationContext ctx;
    protected RoomManager roomManager;
    protected ReservationManager resManager;
    protected List<Room> rooms = new ArrayList<>();
    protected List<Reservation> reservations = new ArrayList<>();
    protected LocalDate from;
    protected LocalDate to;

    public EmptyRoomsTableModel(LocalDate from,LocalDate to) {
        ctx = new AnnotationConfigApplicationContext(Main.SpringConfig.class); 
        roomManager = ctx.getBean(texts.getString("roomManager"), RoomManager.class);
        resManager = ctx.getBean(texts.getString("reservationManager"), ReservationManager.class);
        this.from=from;
        this.to=to;
        RetrieveSwingWorker retrieveSwingWorker = new RetrieveSwingWorker();
        retrieveSwingWorker.execute();
    }
    
    private class CreateSwingWorker extends SwingWorker<Void,Void> {
        private Room room;
        
        public CreateSwingWorker(Room room) {
            this.room = room;
        }
        
        @Override    
        protected Void doInBackground() throws Exception {
            roomManager.createRoom(room);
            rooms.add(room);
            return null;
        }
        
        @Override    
        protected void done() {
            int lastRow = getRowCount() - 1;
            fireTableRowsInserted(lastRow, lastRow);
        }
    }
    
    private class RetrieveSwingWorker extends SwingWorker<Void,Void> {        
        @Override    
        protected Void doInBackground() throws Exception {
            rooms = resManager.findEmptyRoom(from, to);
            return null;
        }
        
        @Override    
        protected void done() {
            fireTableRowsInserted(0, getRowCount() - 1);
        }
    }
 
    @Override
    public int getRowCount() {
        return rooms.size();
    }
 
    @Override
    public int getColumnCount() {
        return ROOM_COLUMNS;
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Room room = rooms.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return room.getRoomID();
            case 1:
                return room.getCapacity();
            case 2:
                return room.getRoomNumber();
            default:
                throw new IllegalArgumentException(texts.getString("working with bad column index."));
        }
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return texts.getString("id");
            case 1:
                return texts.getString("capacity");
            case 2:
                return texts.getString("roomNumber");
            default:
                throw new IllegalArgumentException(texts.getString("working with bad column index."));
        }
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Long.class;
            case 1:
                return Integer.class;
            case 2:
                return String.class;
            default:
                throw new IllegalArgumentException(texts.getString("working with bad column index."));
        }
    }
    

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
            case 1:
            case 2:
                return false;
            default:
                throw new IllegalArgumentException(texts.getString("Working with bad column index."));
        }
    }
    
    public void addRow(Room room) {
        CreateSwingWorker createSwingWorker = new CreateSwingWorker(room);
        createSwingWorker.execute();
    }   
}
