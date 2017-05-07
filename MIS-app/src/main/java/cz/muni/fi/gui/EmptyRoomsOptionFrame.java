/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.gui;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

/**
 *
 * @author Vladko
 */
public class EmptyRoomsOptionFrame extends javax.swing.JFrame {
private static final ResourceBundle texts = ResourceBundle.getBundle("texts");
protected EmptyRoomsFrame emptyRoomFrame;
    
private RoomsTableModel roomTable;
    /**
     * Creates new form EmptyRoomsOptionFrame
     */
    public EmptyRoomsOptionFrame() {
        initComponents();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLblShowEmptyRooms = new javax.swing.JLabel();
        jLblFrom = new javax.swing.JLabel();
        jLblTo = new javax.swing.JLabel();
        jDateFrom = new com.toedter.calendar.JDateChooser();
        jDateTo = new com.toedter.calendar.JDateChooser();
        jBtnContinue = new javax.swing.JButton();
        jBtnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("texts"); // NOI18N
        jLblShowEmptyRooms.setText(bundle.getString("emptyRooms")); // NOI18N

        jLblFrom.setText(bundle.getString("startTime")); // NOI18N

        jLblTo.setText(bundle.getString("endTime")); // NOI18N

        jBtnContinue.setText(bundle.getString("continue")); // NOI18N
        jBtnContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnContinueActionPerformed(evt);
            }
        });

        jBtnCancel.setText(bundle.getString("cancel")); // NOI18N
        jBtnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBtnContinue)
                        .addGap(40, 40, 40)
                        .addComponent(jBtnCancel))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLblFrom)
                            .addComponent(jLblTo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLblShowEmptyRooms, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLblShowEmptyRooms, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblFrom)
                    .addComponent(jDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLblTo, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnContinue)
                    .addComponent(jBtnCancel))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnContinueActionPerformed
        Date dateFrom = jDateFrom.getDate();
       
       if(dateFrom == null){
            JOptionPane.showMessageDialog(null,texts.getString("enterDatepls"),texts.getString("requireDate"),JOptionPane.WARNING_MESSAGE);
            return;
        }
       
       LocalDate localDateFrom = dateFrom.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
       
       Date dateTo = jDateTo.getDate();
       
       if(dateTo == null){
            JOptionPane.showMessageDialog(null,texts.getString("enterDatepls"),texts.getString("requireDate"),JOptionPane.WARNING_MESSAGE);
            return;
        }
       LocalDate localDateTo = dateTo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        if (localDateFrom.isAfter(localDateTo)){
            JOptionPane.showMessageDialog(null,texts.getString("dateError"),texts.getString("fromLaterThanTo"),JOptionPane.WARNING_MESSAGE);
            return;
        } 
        
        EmptyRoomsTableModel emptyRoomsTable = new EmptyRoomsTableModel(localDateFrom,localDateTo);
        emptyRoomFrame = new EmptyRoomsFrame(emptyRoomsTable, localDateFrom, localDateTo);
        emptyRoomFrame.setVisible(true);
        
    }//GEN-LAST:event_jBtnContinueActionPerformed

    private void jBtnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelActionPerformed
       this.dispose();
    }//GEN-LAST:event_jBtnCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnCancel;
    private javax.swing.JButton jBtnContinue;
    private com.toedter.calendar.JDateChooser jDateFrom;
    private com.toedter.calendar.JDateChooser jDateTo;
    private javax.swing.JLabel jLblFrom;
    private javax.swing.JLabel jLblShowEmptyRooms;
    private javax.swing.JLabel jLblTo;
    // End of variables declaration//GEN-END:variables
}