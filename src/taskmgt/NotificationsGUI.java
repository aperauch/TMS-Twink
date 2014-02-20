/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package taskmgt;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;
import taskmgt.Models.Project;
import taskmgt.Models.State;
import taskmgt.Models.Task;
import taskmgt.Models.TeamLeader;
import taskmgt.Models.User;


/**
 *
 * @author Ray
 */
public final class NotificationsGUI extends javax.swing.JDialog {

    private final User noitifyUser;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    private final ProjectTaskGUI ptGUI;
    //Jtable Function
    //Fill Whole Table
    private void fillTable(){
        DefaultTableModel tableModel=(DefaultTableModel) jTable1.getModel();
        tableModel.setRowCount(0);
        if(noitifyUser instanceof TeamLeader){
            for(Project project:Data.projectList){
                if(project.getOwner().equalsIgnoreCase(noitifyUser.getEmail())){
                    for(Task task:project.getTasks()){
                        if(task.getStatus()==State.New){
                            LinkedList<String> row=new LinkedList();
                            row.add(project.getTitle());
                            row.add(Integer.toString(task.getID()));
                            row.add(task.getTitle());    
                            row.add(task.getOwner());
                            row.add(dateFormat.format(task.getStartDate()));
                            row.add(dateFormat.format(task.getEndDate()));   
                            tableModel.addRow(row.toArray());
                        }
                    }
                }
            }
        }
        else{
            for(Project project:Data.projectList){
                for(Task task:project.getTasks()){
                    if(task.getStatus()==State.ToDo&&task.getOwner().equalsIgnoreCase(noitifyUser.getEmail())){
                        LinkedList<String> row=new LinkedList();
                        row.add(project.getTitle());
                        row.add(Integer.toString(task.getID()));
                        row.add(task.getTitle());    
                        row.add(task.getOwner());
                        row.add(dateFormat.format(task.getStartDate()));
                        row.add(dateFormat.format(task.getEndDate()));   
                        tableModel.addRow(row.toArray());
                    }
                }
            }
            
        }
        jTable1.setModel(tableModel);
    }
    //Initial Table
    private void initialTable(){
        //Inital table
        jTable1.setShowGrid(false);
        jTable1.setShowHorizontalLines(false);
        jTable1.setShowVerticalLines(false);
        jTable1.setGridColor(Color.BLUE);
        jTable1.getTableHeader().setReorderingAllowed(false);
    }
    
    /**
     * Creates new form Notes
     * @param parent
     * @param modal
     */
    public NotificationsGUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        noitifyUser=Data.getCurrentUser();
        if(noitifyUser instanceof TeamLeader){
            jButton3.setVisible(false);
            jButton1.setVisible(true);
            jButton2.setVisible(true);
        }
        else{
            jButton3.setVisible(true);
            jButton1.setVisible(false);
            jButton2.setVisible(false);
        }
        initialTable();
        fillTable();
        ptGUI=(ProjectTaskGUI) parent;
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                   ptGUI.refreshTasksList();
            }
        }
        );
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Notifications");
        setResizable(false);

        jButton1.setText("Approve");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Reject");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("OK");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Project Title", "Task ID", "Task Title", "Owner", "Start Date", "End Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jButton1)
                        .addGap(46, 46, 46)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if(jTable1.getRowCount()!=0){
            int row = jTable1.getSelectedRow();
            if (row >= 0){                 
                String pTitle=jTable1.getValueAt(row, 0).toString();
                int taskID=Integer.parseInt(jTable1.getValueAt(row, 1).toString());
                for(Project project:Data.projectList){
                if(project.getTitle().equalsIgnoreCase(pTitle)){
                    for(Task task:project.getTasks()){
                        if(task.getID()==taskID){
                            task.setStatus(State.ToDo);
                            break;
                        }
                    }
                    break;
                }
                }
            } 
        }
        Data.Finalize();
        fillTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if(jTable1.getRowCount()!=0){
        int row = jTable1.getSelectedRow();
        if (row >= 0){                 
            String pTitle=jTable1.getValueAt(row, 0).toString();
            int taskID=Integer.parseInt(jTable1.getValueAt(row, 1).toString());
            for(Project project:Data.projectList){
                if(project.getTitle().equalsIgnoreCase(pTitle)){
                    for(Task task:project.getTasks()){
                        if(task.getID()==taskID&&task.getStatus()==State.New){
                            project.removeTask(task);
                            break;
                        }
                    }
                    break;
                }
            }
        }
        }
        Data.Finalize();
        fillTable();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NotificationsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NotificationsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NotificationsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NotificationsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NotificationsGUI dialog = new NotificationsGUI(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
