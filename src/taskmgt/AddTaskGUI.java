/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package taskmgt;

import java.text.*;
import java.util.*;
import java.util.Locale;
import javax.swing.JOptionPane;
import taskmgt.Models.*;

/**
 *
 * @author Ray
 */
public class AddTaskGUI extends javax.swing.JDialog {
    
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
    ProjectTaskGUI parentFrame;
    Task taskToEdit;
    
    
    /**
     * Creates new form Task
     * @param parent
     * @param modal
     */
    //Default Constructor
    public AddTaskGUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        taskToEdit = null;
        
        //The parent frame is the window the user was interacting with prior to this window.
        //This variable allows for updates to be called back to parent.
        parentFrame = (ProjectTaskGUI) parent;
      
        //Populate Members list
        if (TaskSystem.getCurrentUser() instanceof TeamLeader){
            LinkedList<User> projMembers=parentFrame.getSelectProject().getMembers();
            for(User user:projMembers){
                if(!(user instanceof Administrator) && user.checkActive()){
                    ownerComboBox.addItem(user.getEmail());
                }
            }
        } 
        else {
            ownerComboBox.addItem(TaskSystem.getCurrentUser().getEmail());
        }
        //Load Project Date
        startDateField.setText(dateFormat.format(parentFrame.getSelectProject().getStartDate()).toString());
        endDateField.setText(dateFormat.format(parentFrame.getSelectProject().getEndDate()).toString());
        
        if(TaskSystem.getCurrentUser() instanceof TeamLeader && parentFrame.getSelectProject().getOwner().equalsIgnoreCase(TaskSystem.getCurrentUser().getEmail())){
            this.setTitle("Add Task");
            addTaskBtn.setText("Add");
        }
        else{
            this.setTitle("Request Task");
            addTaskBtn.setText("Request");
        }
    }
    
//    //Called when adding a task
//    public AddTaskGUI(java.awt.Frame parent, boolean modal, String flag) {
//        super(parent, modal);
//        initComponents();
//        taskToEdit = null;
//        
//        //The parent frame is the window the user was interacting with prior to this window.
//        //This variable allows for updates to be called back to parent.
//        parentFrame = (ProjectTaskGUI) parent;
//        
//        //Populate Members list
//        if (TaskSystem.getCurrentUser() instanceof TeamLeader){
//            LinkedList<User> projMembers=parentFrame.getSelectProject().getMembers();
//            for(User user:projMembers){
//                if(!(user instanceof Administrator) && user.checkActive()){
//                    ownerComboBox.addItem(user.getEmail());
//                }
//            }
//            ownerComboBox.addItem(TaskSystem.getCurrentUser().getEmail());
//        } 
//        else {
//            ownerComboBox.addItem(TaskSystem.getCurrentUser().getEmail());
//        }
//        
//        if(flag.equals("add"))
//            setFormAdd();
//        else
//            setFormEdit();
//        
//        if(TaskSystem.getCurrentUser() instanceof TeamLeader){
//            this.setTitle("Add Task");
//            addTaskBtn.setText("Add");
//        }
//        else{
//            this.setTitle("Request Task");
//            addTaskBtn.setText("Request");
//        }
//    }
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        taskTitleField = new javax.swing.JTextField();
        addTaskBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ownerComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        startDateField = new javax.swing.JTextField();
        endDateField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Task");

        taskTitleField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taskTitleFieldActionPerformed(evt);
            }
        });

        addTaskBtn.setText("Add");
        addTaskBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTaskBtnActionPerformed(evt);
            }
        });

        jLabel3.setText("End Date:");

        jLabel2.setText("Start Date:");

        jLabel4.setText("Team Member List:");

        jLabel1.setText("Task Title:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(startDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(taskTitleField, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ownerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(endDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(addTaskBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(taskTitleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(startDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(endDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ownerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(addTaskBtn)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void taskTitleFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taskTitleFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_taskTitleFieldActionPerformed

    private void addTaskBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTaskBtnActionPerformed

        if(taskTitleField.getText().isEmpty()||startDateField.getText().isEmpty()||endDateField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please enter all information!", "Missing Fields", JOptionPane.WARNING_MESSAGE);
            return;
        }
        //Get attributes that are not given from textfields
        Project project = parentFrame.getSelectProject();
        int projectID = project.getID();
        String ownerEmail = (String) ownerComboBox.getSelectedItem();
               
        //Get attributes from the textfields
        String title = taskTitleField.getText();
        String startDate = startDateField.getText();
        String endDate = endDateField.getText();
        
        //Convert date Strings to Date objects
        Date start = new Date();
        Date end = new Date();
        try {        
            start = dateFormat.parse(startDate);
            end = dateFormat.parse(endDate);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Please enter date in mm/dd/yyyy format.", "Incorrect Date Value", JOptionPane.WARNING_MESSAGE);
        }
        
        //Validate Start Date End Date
        if(start.compareTo(parentFrame.getSelectProject().getStartDate())<0){
            JOptionPane.showMessageDialog(null, "Please choose a date after the start date of Project", "Incorrect Start Date", JOptionPane.WARNING_MESSAGE);
            return;
        }
        else if(end.compareTo(parentFrame.getSelectProject().getEndDate())>0){
            JOptionPane.showMessageDialog(null, "Please choose a date before the end date of Project", "Incorrect End Date", JOptionPane.WARNING_MESSAGE);
            return;       
        }
        
        //If adding a task
        if (addTaskBtn.getText().equalsIgnoreCase("add")&&TaskSystem.getCurrentUser() instanceof TeamLeader){
            //Create Task object with attributes
            Task task;
            if (TaskSystem.getCurrentUser() instanceof TeamLeader)
                task = new Task(title, ownerEmail, projectID, start, end, State.ToDo);
            else
                task = new Task(title, ownerEmail, projectID, start, end);
            
            //Push task onto task list for the project
            if(!project.getTasks().contains(task)){
                project.addTask(task);
                parentFrame.addTaskTableRow(task);
                if(TaskSystem.getCurrentUser() == task.getOwnerObject()){parentFrame.addTaskCount();}
            }
            else{
                JOptionPane.showMessageDialog(null, "This task is already exists.", "Task exists!", JOptionPane.WARNING_MESSAGE);
            }            
            //Update parent frame with new tasks
        }
        //Else, requesting a task
        else
        {
            //Create Task object with attributes
            Task task;task = new Task(title, ownerEmail, projectID, start, end, State.New);
            if(!project.getTasks().contains(task)){
                project.addTask(task);
                parentFrame.addTaskTableRow(task);
            }
            else{
                JOptionPane.showMessageDialog(null, "This task is already exists.", "Task exists!", JOptionPane.WARNING_MESSAGE);
            }  

            //Update parent frame with new tasks
        }
    }//GEN-LAST:event_addTaskBtnActionPerformed

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
            java.util.logging.Logger.getLogger(AddTaskGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddTaskGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddTaskGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddTaskGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddTaskGUI dialog = new AddTaskGUI(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton addTaskBtn;
    private javax.swing.JTextField endDateField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JComboBox ownerComboBox;
    private javax.swing.JTextField startDateField;
    private javax.swing.JTextField taskTitleField;
    // End of variables declaration//GEN-END:variables
}
