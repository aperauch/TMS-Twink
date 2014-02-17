package taskmgt;

import Utils.Porter;
import au.com.bytecode.opencsv.CSVWriter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import taskmgt.Models.ModelType;
import taskmgt.Models.Project;
import taskmgt.Models.State;
import taskmgt.Models.Task;
import taskmgt.Models.TeamLeader;
import taskmgt.Models.TeamMember;
import taskmgt.Models.User;
import java.io.File;
import java.io.IOException;
import java.awt.Desktop;

/**
 *
 * @author Ray
 */
public final class AdminGUI extends javax.swing.JFrame{
    
    //User Define Methods
    private void loadLeader(JComboBox comboBox){
        comboBox.removeAllItems();
        LinkedList<TeamLeader> leaders=Data.getLeaders();
        for(TeamLeader leader:leaders){
            if(leader.checkActive()){
                String item=leader.getName()+", "+leader.getEmail();
                comboBox.addItem(item);
            }
        }
    }
    
    private void loadMember(JComboBox comboBox){
        comboBox.removeAllItems();
        LinkedList<TeamMember> members=Data.getMembers();
        for(TeamMember member:members){
            if(member.checkActive()){
                String item=member.getName()+", "+member.getEmail();
                comboBox.addItem(item);
            }
        }
    }
    
    public void transProject(){
        loadLeader(jComboBox1);
        loadLeader(jComboBox2);
        this.jButton1.setText("Transfer Projects");
    }
    
    public void transTask(){
        loadMember(jComboBox1);
        loadMember(jComboBox2);
        this.jButton1.setText("Transfer Tasks");
    }
    
    public void exportCSV() throws IOException {
        boolean projectsOK = Porter.exportCSV(".//Exports//Projects.csv", ModelType.Project);
        boolean tasksOK = Porter.exportCSV(".//Exports//Tasks.csv", ModelType.Task);
        boolean usersOK = Porter.exportCSV(".//Exports//Users.csv", ModelType.AllUsers);
        
        if (projectsOK && tasksOK && usersOK) {
            int answer = 
            JOptionPane.showConfirmDialog(null,"All data has been exported!  View directory?","Export Complete", JOptionPane.YES_NO_OPTION);
                if (answer == 0){
                    System.out.print("Yes");
                  Desktop desktop = Desktop.getDesktop();
                        File dirToOpen = null;
                        try {
                            dirToOpen = new File(".//Exports//");
                            desktop.open(dirToOpen);
                        } catch (IllegalArgumentException iae) {
                            System.out.println("Directory Not Found");
                        }  

                            }
            jPanelImport.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null,"Oops!  The Export did not work.  Does the Export directory exist?","Export Failed", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void encryptedExportCSV() {
        
    }
    
    public void importCSV(boolean merge) {
        
        if (!merge)
            Data.clearAllLists();
        
        boolean projectsOK = Porter.importCSV(".//Imports//Projects.csv", ModelType.Project, merge);
        boolean tasksOK = Porter.importCSV(".//Imports//Tasks.csv", ModelType.Task, merge);
        boolean usersOK = Porter.importCSV(".//Imports//Users.csv", ModelType.AllUsers, merge);
        
        if (projectsOK && tasksOK && usersOK) {
            JOptionPane.showMessageDialog(null,"All data has been imported from Imports directory!","Import Complete", JOptionPane.PLAIN_MESSAGE);
            Data.Finalize();
            jPanelImport.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null,"Oops!  The import did not work.  Was your CSV ordered right?","Import Failed", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void encryptedImportCSV() {
        
    }
    
    /**
     * Creates new form Admin
     */
    public AdminGUI() {
        initComponents();
        user=null;
        this.transFlag=false;
    }
    
    public AdminGUI(final LoginGUI loginForm, final User user){
        initComponents();
        this.user=user;
        this.addWindowListener(new WindowAdapter(){
                @Override
                public void windowClosing(WindowEvent we){
                    loginForm.setVisible(true);
                    loginForm.clearTextField();
                }
            }
        );
        this.transFlag=false;
        this.jPanelTransfer.setVisible(false);
        this.jPanelImport.setVisible(false);
        this.setSize(521, 180);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jComboBox4 = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jPanelTransfer = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox1 = new javax.swing.JComboBox();
        jPanelImport = new javax.swing.JPanel();
        importBtn = new javax.swing.JButton();
        mergeCheckBox = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        exportMenu = new javax.swing.JMenu();
        exportCSVMenuItem = new javax.swing.JMenuItem();
        encExportCSVMenuItem = new javax.swing.JMenuItem();
        importMenu = new javax.swing.JMenu();
        importCSVMenuItem = new javax.swing.JMenuItem();
        encImportCSVMenuItem = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jButton2.setText("Transfer Projects");

        jLabel2.setText("Transfer To:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jComboBox3, 0, 199, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(195, 195, 195)
                .addComponent(jButton2)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setTitle("Admin");
        setResizable(false);

        jPanelTransfer.setPreferredSize(new java.awt.Dimension(506, 144));

        jButton1.setText("Transfer Tasks");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Transfer To:");

        javax.swing.GroupLayout jPanelTransferLayout = new javax.swing.GroupLayout(jPanelTransfer);
        jPanelTransfer.setLayout(jPanelTransferLayout);
        jPanelTransferLayout.setHorizontalGroup(
            jPanelTransferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTransferLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTransferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTransferLayout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox2, 0, 188, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTransferLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(183, 183, 183))))
        );
        jPanelTransferLayout.setVerticalGroup(
            jPanelTransferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTransferLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanelTransferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        importBtn.setText("Import");
        importBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importBtnActionPerformed(evt);
            }
        });

        mergeCheckBox.setText("Merge with existing data?");

        javax.swing.GroupLayout jPanelImportLayout = new javax.swing.GroupLayout(jPanelImport);
        jPanelImport.setLayout(jPanelImportLayout);
        jPanelImportLayout.setHorizontalGroup(
            jPanelImportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelImportLayout.createSequentialGroup()
                .addGroup(jPanelImportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelImportLayout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(mergeCheckBox))
                    .addGroup(jPanelImportLayout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(importBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelImportLayout.setVerticalGroup(
            jPanelImportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelImportLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(mergeCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(importBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jMenu1.setText("Member");

        jMenuItem2.setText("Add/Edit Member");
        jMenuItem2.setToolTipText("");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Transfer");

        jMenuItem3.setText("Transfer Task");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Transfer Project");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        exportMenu.setText("Export");

        exportCSVMenuItem.setText("Export To CSV");
        exportCSVMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportCSVMenuItemActionPerformed(evt);
            }
        });
        exportMenu.add(exportCSVMenuItem);

        encExportCSVMenuItem.setText("Encrypted Export To CSV Because NSA");
        exportMenu.add(encExportCSVMenuItem);

        jMenuBar1.add(exportMenu);

        importMenu.setText("Import");

        importCSVMenuItem.setText("Import From CSV");
        importCSVMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importCSVMenuItemActionPerformed(evt);
            }
        });
        importMenu.add(importCSVMenuItem);

        encImportCSVMenuItem.setText("Encrypted Import From CSV");
        importMenu.add(encImportCSVMenuItem);

        jMenuBar1.add(importMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelTransfer, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 14, Short.MAX_VALUE))
            .addComponent(jPanelImport, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelTransfer, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelImport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        this.jPanelTransfer.setVisible(false);
        MemberGUI addMemberForm=new MemberGUI(this,true, user);
        addMemberForm.show();
        addMemberForm.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        jPanelTransfer.setVisible(true);
        jPanelImport.setVisible(false);
        this.transFlag=false;
        transTask();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed

        jPanelTransfer.setVisible(true);
        jPanelImport.setVisible(false);
        this.transFlag=true;
        transProject();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String str1=jComboBox1.getSelectedItem().toString();
        String str2=jComboBox2.getSelectedItem().toString();
        if(str1.equals(str2)){
            JOptionPane.showMessageDialog(null,"You can't transfer project/task to this user!","Warning",JOptionPane.WARNING_MESSAGE);
        }
        else{
            String user1[]=str1.replaceAll("^[,\\s]+", "").split("[,\\s]+");
            String user2[]=str2.replaceAll("^[,\\s]+", "").split("[,\\s]+");
            String email1=user1[1];
            String email2=user2[1];
            if(this.transFlag){
                for(Project project:Data.projectList){
                    if(project.getOwner().equals(email1)){
                        if(project.getStatus()!=State.Archived)
                            project.setOwner(email2);
                    }
                }
            }
            else{
                for (Project project : Data.projectList) {
                    for(Task task : project.getTasks()){
                        if(task.getOwner().equals(email1)){
                            if(task.getStatus()!=State.Archived)
                                task.setOwner(email2);
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void exportCSVMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportCSVMenuItemActionPerformed
        // TODO add your handling code here:
        jPanelTransfer.setVisible(false);
        try {
            exportCSV();
        } catch (IOException ex) {
            Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_exportCSVMenuItemActionPerformed

    private void importCSVMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importCSVMenuItemActionPerformed
        // TODO add your handling code here:
        jPanelTransfer.setVisible(false);
        jPanelImport.setVisible(true);
    }//GEN-LAST:event_importCSVMenuItemActionPerformed

    private void importBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importBtnActionPerformed
        // TODO add your handling code here:
        boolean merge = mergeCheckBox.isSelected();
        importCSV(merge);
    }//GEN-LAST:event_importBtnActionPerformed

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
            java.util.logging.Logger.getLogger(AdminGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdminGUI().setVisible(true);
            }
        });
    }
    //User define
    private final User user;
    private boolean transFlag;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem encExportCSVMenuItem;
    private javax.swing.JMenuItem encImportCSVMenuItem;
    private javax.swing.JMenuItem exportCSVMenuItem;
    private javax.swing.JMenu exportMenu;
    private javax.swing.JButton importBtn;
    private javax.swing.JMenuItem importCSVMenuItem;
    private javax.swing.JMenu importMenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelImport;
    private javax.swing.JPanel jPanelTransfer;
    private javax.swing.JCheckBox mergeCheckBox;
    // End of variables declaration//GEN-END:variables

}
