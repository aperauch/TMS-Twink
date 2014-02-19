package taskmgt;

import java.awt.Color;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.*;
import taskmgt.Models.Project;
import taskmgt.Models.Task;
import taskmgt.Models.State;
import taskmgt.Models.TeamLeader;
import taskmgt.Models.TeamMember;
import taskmgt.Models.User;

/**
 *
 * @author Ray
 */
public final class ProjectTaskGUI extends javax.swing.JFrame {//implements ListSelectionListener {

    private Project currentProject;
    private static boolean jListListenerFlag = false;
    private final SimpleDateFormat simpleDate = new SimpleDateFormat("MM/dd/yyyy");

    /**
     * Creates new form Project
     */
    public ProjectTaskGUI() {
        initComponents();
        refreshProjectsList();
        cellListener();
    }

    public ProjectTaskGUI(final LoginGUI form) {
        initComponents();
        initialTable();
        refreshProjectsList();
        jLabelHello.setText("Hello, " + Data.getCurrentUser().getName());
        jLabelProjects.setText("You have " + "projectCount" + " projects.");
        if (Data.getCurrentUser() instanceof TeamLeader) {
            ButtonAddTask.setText("Add Task");
            TableColumn statusColumn = jTableTasks.getColumnModel().getColumn(5);
            JComboBox comboBox = new JComboBox();
            comboBox.addItem("New");
            comboBox.addItem("ToDo");
            comboBox.addItem("Archive");
            comboBox.addItem("Completed");
            statusColumn.setCellEditor(new DefaultCellEditor(comboBox));
        } else if (Data.getCurrentUser() instanceof TeamMember) {
            ButtonCreateProject.setVisible(false);
            jButton5.setVisible(false);
            ButtonAddTask.setText("Request Task");
            TableColumn statusColumn = jTableTasks.getColumnModel().getColumn(5);
            JComboBox comboBox = new JComboBox();
            comboBox.addItem("ToDo");
            comboBox.addItem("Completed");
            statusColumn.setCellEditor(new DefaultCellEditor(comboBox));
        }

        cellListener();
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                form.setVisible(true);
                form.clearTextField();
            }
        }
        );
    }

    //Methods
    public Project getSelectProject() {
        if (currentProject == null) {
            setSelectedProject();
        }
        return currentProject;
    }

    public void setSelectedProject(Project project) {
        currentProject = project;
    }

    public void setSelectedProject() {
        String projectTitle = (String) jListProjects.getSelectedValue();

        if (projectTitle != null && !projectTitle.isEmpty()) {
            currentProject = Data.getProjectByTitle(projectTitle);
        }
    }


    public void initialTable(){
       //Inital table
        jTableTasks.setShowGrid(false);
        jTableTasks.setShowHorizontalLines(false);
        jTableTasks.setShowVerticalLines(false);
        jTableTasks.setGridColor(Color.BLUE);
        jTableTasks.getTableHeader().setReorderingAllowed(false);
    }
    
    public void refreshProjectsList() {
        DefaultListModel jListModel = new DefaultListModel();
        //If projects exists, then update Projects List
        if (!Data.projectList.isEmpty()) {
            for (Project project : Data.projectList) {
                if (project.getStatus() != State.Archive) {
                    if (Data.getCurrentUser().getEmail().equalsIgnoreCase(project.getOwner())) {
                        jListModel.addElement(project.getTitle());
                    } else {
                        for (User member : project.getMembers()) {
                            if (Data.getCurrentUser().equals(member)) {
                                jListModel.addElement(project.getTitle());
                                break;
                            }                        
                        }
                    }
                }
            }
            jListProjects.setModel(jListModel);
            jListProjects.setEnabled(true);
        } else {
            jListModel.addElement("<No Projects Yet>");
            jListProjects.setModel(jListModel);
            jListProjects.setEnabled(false);
        }
    }
    private void refreshTasksList(){
        clearTaskTable();
        //Get selected project title
        String projectTitle = (String) jListProjects.getSelectedValue();
        //Get project object from title
        currentProject = Data.getProjectByTitle(projectTitle);
        if (currentProject != null) {
            for (Task task : currentProject.getTasks()) {
                addTaskTableRow(task);
            }
        }
    }

    public void clearTaskTable() {
        DefaultTableModel model = (DefaultTableModel) jTableTasks.getModel();

        model.setRowCount(0);
        jTableTasks.setModel(model);
    }

    public void addTaskTableRow(Task t) {
        DefaultTableModel model = (DefaultTableModel) jTableTasks.getModel();
        TableColumn statusColumn = jTableTasks.getColumnModel().getColumn(4);
        JComboBox comboBox = new JComboBox();
        for(User member:currentProject.getMembers()){
            comboBox.addItem(member.getEmail());
        }
        if(Data.getCurrentUser() instanceof TeamLeader){
            comboBox.addItem(Data.getCurrentUser().getEmail());
        }
        statusColumn.setCellEditor(new DefaultCellEditor(comboBox));
        model.addRow(t.toTableRow());
        jTableTasks.setModel(model);
    }

    private void cellListener() {
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableCellListener tcl = (TableCellListener) e.getSource();
                if (tcl.getNewValue().toString().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in information!", "Warning", JOptionPane.WARNING_MESSAGE);
                    refreshTasksList();
                } else {

                    TeamLeader currentUser;
                    if ((Data.getCurrentUser()) instanceof TeamLeader) {
                        currentUser = (TeamLeader) Data.getCurrentUser();
                    } else {
                        JOptionPane.showMessageDialog(null, "Only team leaders can edit tasks!", "Warning", JOptionPane.WARNING_MESSAGE);
                        refreshTasksList();
                        return;
                    }

                    int col = tcl.getColumn();
                    int row = tcl.getRow();
                    int taskId = Integer.parseInt(jTableTasks.getValueAt(row, 0).toString());
                    String origOwnerEmail = jTableTasks.getValueAt(row, 4).toString();
                    switch (col) {
                        case 0://ID - Non-editable
                        case 1://Title
                            currentProject.getTaskByID(taskId).setTitle(tcl.getNewValue().toString());
                            break;
                        case 2://Start Date
                            try {
                                currentProject.getTaskByID(taskId).setStartDate(simpleDate.parse(tcl.getNewValue().toString()));
                            } catch (ParseException ex) {
                                JOptionPane.showMessageDialog(null, "Inccrrect date format!\nUse MM/dd/yyyy", "Warning", JOptionPane.WARNING_MESSAGE);
                            }
                            break;
                        case 3://End Date
                            try {
                                currentProject.getTaskByID(taskId).setEndDate(simpleDate.parse(tcl.getNewValue().toString()));
                            } catch (ParseException ex) {
                                JOptionPane.showMessageDialog(null, "Inccrrect date format!\nUse MM/dd/yyyy", "Warning", JOptionPane.WARNING_MESSAGE);
                            }
                            break;
                        case 4://Owner Email
                            currentProject.getTaskByID(taskId).setOwner(tcl.getNewValue().toString());
                            break;
                        case 5://Status
                            String state = tcl.getNewValue().toString();
                            if (state.equalsIgnoreCase(State.Completed.name())) {
                                currentProject.getTaskByID(taskId).setStatus(State.Completed);
                            } else if (state.equalsIgnoreCase(State.New.name())) {
                                currentProject.getTaskByID(taskId).setStatus(State.New);
                            } else if (state.equalsIgnoreCase(State.ToDo.name())) {
                                currentProject.getTaskByID(taskId).setStatus(State.ToDo);
                            } else {
                                JOptionPane.showMessageDialog(null, "Please pick a valid status!"
                                        + "New\nCompleted\nPending\nRejected", "Warning", JOptionPane.WARNING_MESSAGE);
                            }
                            break;
                    }
                }
                //fillTable();
            }
        };
        TableCellListener tcl = new TableCellListener(jTableTasks, action);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jListProjects = new javax.swing.JList();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ButtonCreateProject = new javax.swing.JButton();
        ButtonAddTask = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableTasks = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabelProjects = new javax.swing.JLabel();
        jLabelHello = new javax.swing.JLabel();

        setTitle("Project");
        setResizable(false);

        jListProjects.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListProjects.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListProjectsValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jListProjects);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("You have new notifications");

        jLabel2.setText("Notifications");

        jButton1.setText("Review");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 264, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 22, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setText("Projects:");

        jLabel4.setText("Tasks:");

        ButtonCreateProject.setText("Create Project");
        ButtonCreateProject.setActionCommand("Manage Projects");
        ButtonCreateProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonCreateProjectActionPerformed(evt);
            }
        });

        ButtonAddTask.setText("Add Task");
        ButtonAddTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAddTaskActionPerformed(evt);
            }
        });

        jButton5.setText("Edit Project");
        jButton5.setActionCommand("Manage Projects");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTableTasks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Title", "Start Date", "End Date", "Owner", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTableTasks);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelProjects.setText("You have new notifications");

        jLabelHello.setText("Hello");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelProjects)
                    .addComponent(jLabelHello))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabelHello)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jLabelProjects))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ButtonCreateProject)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ButtonAddTask, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(783, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ButtonAddTask))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5)
                            .addComponent(ButtonCreateProject))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        NotificationsGUI notifyForm = new NotificationsGUI(this, true);
        notifyForm.show();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ButtonCreateProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCreateProjectActionPerformed
        if (Data.getCurrentUser() instanceof TeamLeader) {
            EditProjectGUI createProjectForm = new EditProjectGUI(this, true, "create");
            createProjectForm.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Only leaders can create a project. :/");
        }
    }//GEN-LAST:event_ButtonCreateProjectActionPerformed

    private void ButtonAddTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAddTaskActionPerformed
        if (jListProjects.getModel().getSize() <= 0) {
            JOptionPane.showMessageDialog(null, "Please create a project first.");
        } else if (jListProjects.isSelectionEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a project to add a task to.");
        } else {
            AddTaskGUI addTaskForm = new AddTaskGUI(this, true, "add");
            addTaskForm.setLocationRelativeTo(null);
            addTaskForm.setVisible(true);
        }
    }//GEN-LAST:event_ButtonAddTaskActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        if (Data.getCurrentUser() instanceof TeamLeader) {
            try {
                if (jListProjects.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select a project to edit.");
                }
                EditProjectGUI editProjectForm = new EditProjectGUI(this, true, "edit");
                editProjectForm.setVisible(true);
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "Please select a project to edit.\n(If there are no projects, create a project to add.)");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Only leaders can edit a project. :/");

        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jListProjectsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListProjectsValueChanged
        //First, clear the Task jTable
        clearTaskTable();

        //Get selected project title
        String projectTitle = (String) jListProjects.getSelectedValue();

        //Get project object from title
        currentProject = Data.getProjectByTitle(projectTitle);

        if (currentProject != null) {
            for (Task task : currentProject.getTasks()) {
                addTaskTableRow(task);
            }
        }

    }//GEN-LAST:event_jListProjectsValueChanged

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
            java.util.logging.Logger.getLogger(ProjectTaskGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProjectTaskGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProjectTaskGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProjectTaskGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProjectTaskGUI().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAddTask;
    private javax.swing.JButton ButtonCreateProject;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelHello;
    private javax.swing.JLabel jLabelProjects;
    private javax.swing.JList jListProjects;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableTasks;
    // End of variables declaration//GEN-END:variables

}
