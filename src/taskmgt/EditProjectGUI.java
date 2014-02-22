/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskmgt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.JOptionPane;
import taskmgt.Models.*;

/**
 *
 * @author Ray
 */
public class EditProjectGUI extends javax.swing.JDialog implements ActionListener {

    DefaultListModel listmodel = new DefaultListModel();
    
    ProjectTaskGUI projectgui;
    Project proj;
    Date sDate = null;
    Date eDate = null;
    
    String title;
    String startDate;
    String endDate;
    String owner = TaskSystem.getCurrentUser().getEmail();

    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    boolean weAreCreating = false;
    boolean clicked = false;

    /**
     *
     * Creates new weAreCreating CreateProject
     *
     * @param parent
     * @param modal
     */
    public EditProjectGUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

    }

    public EditProjectGUI(java.awt.Frame parent, boolean modal, String flag) {
        super(parent, modal);

        initComponents();
        
        //Fill in dates
        Date today = new Date();
        jTextField2.setText(sdf.format(today));
        jTextField3.setText(sdf.format(today));
        
        if (flag.equals("create")) {
            projectgui = (ProjectTaskGUI) parent;
            setFormCreate();
        } else {
            projectgui = (ProjectTaskGUI) parent;
            setFormEdit();
        }
    }

    private void setFormCreate() {
        this.setTitle("Create Project");
        jButtonCreateProject.setText("Create");
        refreshMemberList();
        weAreCreating = true;
    }

    private void setFormEdit() {
        this.setTitle("Edit Project");
        jButtonCreateProject.setText("Save");
        refreshEditMemberList();
        setTextFields();
        weAreCreating = false;
    }

    private void setTextFields() {
        String title = projectgui.getSelectProject().getTitle();

        LinkedList<User> members = projectgui.getSelectProject().getMembers();
        DefaultListModel jListModel = new DefaultListModel();
        for (User membersOnProject : members) {

            jListModel.addElement(membersOnProject.getEmail());

        }
        jList2.setModel(jListModel);

        jTextField1.setText(title);

        Date sDate = projectgui.getSelectProject().getStartDate();
        Date eDate = projectgui.getSelectProject().getEndDate();
        String startDate = sdf.format(sDate);
        String endDate = sdf.format(eDate);
        jTextField2.setText(startDate);
        jTextField3.setText(endDate);
    }

    public void refreshMemberList() {
        //Update Member List
        DefaultListModel jListModel = new DefaultListModel();
            for (User member : TaskSystem.getMembers()) {
                if(member.checkActive()&&!member.equals(TaskSystem.getCurrentUser()))
                    jListModel.addElement(member.getEmail());
            }
        jList1.setModel(jListModel);
    }
    
    public void refreshEditMemberList(){
        boolean flag=true;
        DefaultListModel jListModel = new DefaultListModel();
        LinkedList<User> projectMembers = projectgui.getSelectProject().getMembers();
            for (User member : TaskSystem.getMembers()) {
                for(User projMember:projectMembers){
                    if(member.equals(projMember))
                        flag=false;
                }
                if(member.checkActive()&&!member.equals(TaskSystem.getCurrentUser())&&flag){
                        jListModel.addElement(member.getEmail());
                }
                flag=true;
            }
        jList1.setModel(jListModel);        
    }
    /**
     * This method is called from within the constructor to initialize the
     * weAreCreating. WARNING: Do NOT modify this code. The content of this
     * method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonCreateProject = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonAddMember = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButtonRemoveMember = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButtonCreateProject.setText("Create Project");
        jButtonCreateProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreateProjectActionPerformed(evt);
            }
        });

        jList2.setName(""); // NOI18N
        jScrollPane2.setViewportView(jList2);

        jList1.setName("currentMemberList"); // NOI18N
        jScrollPane1.setViewportView(jList1);

        jLabel1.setText("Project Name:");

        jLabel2.setText("Start Date:");

        jButtonAddMember.setText("Add");
        jButtonAddMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddMemberActionPerformed(evt);
            }
        });

        jLabel3.setText("End Date:");

        jButtonRemoveMember.setText("Remove");
        jButtonRemoveMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveMemberActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jLabel4.setText("Members:");

        jLabel5.setText("Members on Project:");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonCreateProject)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButtonAddMember, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                                    .addComponent(jButtonRemoveMember, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel4))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jTextField1)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(91, 91, 91)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jButtonAddMember)
                        .addGap(62, 62, 62)
                        .addComponent(jButtonRemoveMember))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(7, 7, 7)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jButtonCreateProject)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCreateProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreateProjectActionPerformed
        if(jList2.getModel().getSize()<=0){
            JOptionPane.showMessageDialog(null, "Please choose members to fullfil this project!", "Empty Member List", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (weAreCreating) {
            title = jTextField1.getText().toString();
            startDate = jTextField2.getText().toString();
            endDate = jTextField3.getText().toString();
            for (Project p : TaskSystem.projectList) {
                if (p.getTitle().equalsIgnoreCase(title)) {
                    JOptionPane.showMessageDialog(null, "The Project Title you enetered already exists.\nPlease choose a different title.", "Duplication", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            if (!title.isEmpty()) {

                try {
                    sDate = sdf.parse(startDate);
                    eDate = sdf.parse(endDate);
                    if (!sdf.format(sDate).equals(startDate) || !sdf.format(eDate).equals(endDate)) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid date.", "Invalid Date", JOptionPane.WARNING_MESSAGE);
                    } else {

                        proj = new Project(title, owner, sDate, eDate);
                        TaskSystem.projectList.add(proj);

                        User userEmail = null;
                        List<String> emails = new ArrayList<>();

                        if (jList2.getModel().getSize() > 0) {
                            for (int i = 0; i < jList2.getModel().getSize(); i++) {
                                emails.add((String) jList2.getModel().getElementAt(i));
                            }
                            for (String s : emails) {
                                userEmail = TaskSystem.getUserByEmail(s);
                                proj.addMember(userEmail);

                            }
                        }

                        this.dispose();
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter date in mm/dd/yyyy format.", "Incorrect Date Value", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please make sure the following are correct:\n\n-There is a title\n-The date is valid", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            }

            if (projectgui != null) {
                projectgui.refreshProjectsList();
            }
        }
        //functionality is when editprojGUI appears
        else if (!weAreCreating) {

            title = jTextField1.getText().toString();
            startDate = jTextField2.getText().toString();
            endDate = jTextField3.getText().toString();
            if(title.compareToIgnoreCase(projectgui.getSelectProject().getTitle())!=0){
                for (Project p : TaskSystem.projectList) {
                    if (p.getTitle().equalsIgnoreCase(title)) {
                        JOptionPane.showMessageDialog(null, "The Project Title you enetered already exists.\nPlease choose a different title.", "Duplication", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
            }
            if (!title.isEmpty()) {
                try {
                    sDate = sdf.parse(startDate);
                    eDate = sdf.parse(endDate);
                    if (!sdf.format(sDate).equals(startDate) || !sdf.format(eDate).equals(endDate)) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid date.", "Invalid Date", JOptionPane.WARNING_MESSAGE);
                    } else {

                        //editing project details
                        Project editProj = projectgui.getSelectProject();
                        TaskSystem.projectList.remove(editProj);
                        editProj.setTitle(title);
                        editProj.setEndDate(eDate);
                        editProj.setStartDate(sDate);
                        LinkedList<User> projMems = editProj.getMembers();

                        User userEmail;
                        List<String> newemails = new ArrayList<>();
                        LinkedList<User> newMembers = new LinkedList<>();

                        //adding new members to list   
   
                            if (jList2.getModel().getSize() > 0) {
                                for (int i = 0; i < jList2.getModel().getSize(); i++) {
                                    newemails.add((String) jList2.getModel().getElementAt(i));
                                            
                                }
                                for (String s : newemails) {
                                    userEmail = TaskSystem.getUserByEmail(s);
                                    newMembers.add(userEmail);
                                    editProj.setMembers(newMembers);
                                }
                            }
                            
                        TaskSystem.projectList.add(editProj);
                        this.dispose();
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter date in mm/dd/yyyy format.", "Incorrect Date Value", JOptionPane.WARNING_MESSAGE);
                }

                if (projectgui != null) {
                    projectgui.refreshProjectsList();

                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Please enter a valid Title", "Title missing", JOptionPane.WARNING_MESSAGE);
                return;            
            }
        }

        
    }//GEN-LAST:event_jButtonCreateProjectActionPerformed


    private void jXDatePicker1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXDatePicker1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jXDatePicker1ActionPerformed

    private void jButtonAddMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddMemberActionPerformed
        // TODO add your handling code here:
        if(!weAreCreating ){
            clicked = true;
            List<String> usersToAdd = jList1.getSelectedValuesList();

            for (String user : usersToAdd) {
               
                DefaultListModel model1 = (DefaultListModel) jList1.getModel();
                DefaultListModel model2 = (DefaultListModel)jList2.getModel();
                
                model2.addElement(user);
                
                int selectedIndex = jList1.getSelectedIndex();
                if(selectedIndex != -1){
                    model1.remove(selectedIndex);   
                }                 
            }
        } 
        else if (jList1.isSelectionEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a member to add to a project.");
        } 
        else {
            List<String> usersToAdd = jList1.getSelectedValuesList();

            for (String user : usersToAdd) {
                if (!listmodel.contains(user)) {
                    listmodel.addElement(user);
                }
            }
            jList2.setModel(listmodel);
            
            DefaultListModel model = (DefaultListModel) jList1.getModel();
            int selectedIndex = jList1.getSelectedIndex();
            if (selectedIndex != -1) {
                model.remove(selectedIndex);              
            }
        }
    }//GEN-LAST:event_jButtonAddMemberActionPerformed

    private void jXDatePicker2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXDatePicker2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jXDatePicker2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jButtonRemoveMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveMemberActionPerformed

        if (jList2.isSelectionEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a member to remove from the project.");
        } 
        else {
            List<String> usersToAdd = jList2.getSelectedValuesList();
            DefaultListModel model1 = (DefaultListModel) jList1.getModel();
            DefaultListModel model2 = (DefaultListModel)jList2.getModel();
            for (String user : usersToAdd) {
                model2.removeElement(user);
                model1.add(0,user);
            }
            jList1.setModel(model1);
            jList2.setModel(model2);
        }
        
    }//GEN-LAST:event_jButtonRemoveMemberActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

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
            java.util.logging.Logger.getLogger(EditProjectGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditProjectGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditProjectGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditProjectGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditProjectGUI dialog = new EditProjectGUI(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        }
        );

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddMember;
    private javax.swing.JButton jButtonCreateProject;
    private javax.swing.JButton jButtonRemoveMember;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        clicked = true;
    }
}
