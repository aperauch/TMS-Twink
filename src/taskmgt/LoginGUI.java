package taskmgt;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import taskmgt.Models.Administrator;
import taskmgt.Models.User;

/**
 *
 * @author Ray
 */
public class LoginGUI extends javax.swing.JFrame {
    //User Define Methods
    public void clearTextField(){
        EmailField.setText(null);
        PasswordField.setText(null);
    }
    /**
     * Creates new form LoginGUI
     */
    public LoginGUI() {
        initComponents();
        
        //Debug - Remove before turning in!
        EmailField.setText("mike@mike.com");
        PasswordField.setText("123");
        
        //Set button focuse and add enter event
        ButtonLogin.requestFocusInWindow();
        ButtonLogin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {ButtonLogin.doClick();}
            }
        });
        
        this.addWindowListener(new WindowAdapter(){
                @Override
                public void windowClosing(WindowEvent we){
                    Data.Finalize();
                }
            }
        );
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        PasswordField = new javax.swing.JPasswordField();
        ButtonLogin = new javax.swing.JButton();
        EmailField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setLocationByPlatform(true);
        setResizable(false);

        jLabel1.setText("E-Mail:");

        jLabel2.setText("Password:");

        PasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordFieldActionPerformed(evt);
            }
        });

        ButtonLogin.setText("Login");
        ButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(EmailField)
                            .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(ButtonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(EmailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(ButtonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonLoginActionPerformed
        
        String email=EmailField.getText().toString();
        String password=new String(PasswordField.getPassword());
        if(email.isEmpty()||Utilities.checkEmail(email)==false){
             JOptionPane.showMessageDialog(null,"Please enter the right E-mail address!","Warning",JOptionPane.WARNING_MESSAGE);
        }
        else{
            User user=Data.getUser(email,password);
            if(user==null){
                JOptionPane.showMessageDialog(null,"We can't find your account. Please verify!","Warning",JOptionPane.WARNING_MESSAGE);
            }
            else if(user.getPassword().isEmpty()){
                java.security.SecureRandom random = new java.security.SecureRandom();
                String randomPassword[] = { new java.math.BigInteger(130, random).toString(32) };
                String newPassword=JOptionPane.showInputDialog(null, "Enter a password or use the random one", randomPassword[0]);//"Warning", JOptionPane.WARNING_MESSAGE );
                user.setPassword(newPassword);
                Data.userList.add(user);
            }
            else{
                //Set the current user that is logging into the system.
                Data.setCurrentUser(user);
                
                if(user instanceof Administrator){
                    AdminGUI adminForm=new AdminGUI(this,user);
                    adminForm.show();
                    adminForm.setLocationRelativeTo(null);
                    this.setVisible(false);
                }
                else{
                    ProjectTaskGUI projectTaskForm=new ProjectTaskGUI(this);
                    projectTaskForm.show();
                    projectTaskForm.setLocationRelativeTo(null);
                    this.setVisible(false);
                }
            }
        }
    }//GEN-LAST:event_ButtonLoginActionPerformed

    private void PasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordFieldActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginGUI().setVisible(true);
            }
        });
    }
    
    //
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonLogin;
    private javax.swing.JTextField EmailField;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
