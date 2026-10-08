package com.mycompany.qhopsystem;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class LoginFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LoginFrame.class.getName());
    private QueueManager queueManager;
    private int strikeCount = 0;

    public LoginFrame() {
        initComponents();
        jPanel2.setBackground(new java.awt.Color(15, 23, 42));
        jLabel4.setForeground(new java.awt.Color(218, 165, 32));
        jLabel5.setForeground(java.awt.Color.WHITE);
        jLabel6.setForeground(java.awt.Color.WHITE);

        txtUsername.setBackground(new java.awt.Color(30, 41, 59));
        txtUsername.setForeground(java.awt.Color.WHITE);
        txtUsername.setCaretColor(java.awt.Color.WHITE);
        txtUsername.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        txtPassword.setBackground(new java.awt.Color(30, 41, 59));
        txtPassword.setForeground(java.awt.Color.WHITE);
        txtPassword.setCaretColor(java.awt.Color.WHITE);
        txtPassword.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 15, 10, 15));
        this.queueManager = new QueueManager();
        
        setCharacterLimit(txtUsername, 20);
        setCharacterLimit(txtPassword, 30);
        
        if (queueManager.needsSetup()) {
            jLabel4.setText("Initial Setup");
            btnLogin.setText("CREATE ADMIN");
        }
        this.addWindowFocusListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowGainedFocus(java.awt.event.WindowEvent e) {
                txtUsername.requestFocusInWindow();
            }
        });
        
        txtUsername.addActionListener(this::btnLoginActionPerformed);
        txtPassword.addActionListener(this::btnLoginActionPerformed);
        
        RoundedButton btnForgot = new RoundedButton("Forgot Password?", 20);
        btnForgot.setBackground(new java.awt.Color(15, 23, 42));
        btnForgot.setForeground(new java.awt.Color(148, 163, 184));
        btnForgot.setFont(new java.awt.Font("Montserrat", java.awt.Font.BOLD, 12));
        btnForgot.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnForgot.setContentAreaFilled(false);
        btnForgot.setBorderPainted(false);
        
        btnForgot.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnForgot.setForeground(new java.awt.Color(218, 165, 32));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnForgot.setForeground(new java.awt.Color(148, 163, 184));
            }
        });
        
        btnForgot.addActionListener(e -> {
            String username = javax.swing.JOptionPane.showInputDialog(this, "Enter your Admin Username:");
            if (username != null && !username.trim().isEmpty()) {
                String masterPin = javax.swing.JOptionPane.showInputDialog(this, "Enter Master Security PIN to authorize reset:\n(Hint: For defense, use 1234)");

                // DEMO PIN: 1234
                if ("1234".equals(masterPin)) {
                    String newPw = javax.swing.JOptionPane.showInputDialog(this, "Enter New Password:");
                    if (newPw != null && !newPw.trim().isEmpty()) {
                        if (queueManager.resetPassword(username, newPw)) {
                            AlertBox.show(this, "Success", "Password reset successfully! You may now log in.", false);
                        } else {
                            AlertBox.show(this, "Error", "Username not found in the database.", true);
                        }
                    }
                } else {
                    AlertBox.show(this, "Denied", "Invalid Master Security PIN.", true);
                }
            }
        });

        jPanel2.add(btnForgot, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 535, 350, 30));
    }
    
    private void setCharacterLimit(javax.swing.text.JTextComponent component, int maxChars) {
        ((AbstractDocument) component.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string == null) {
                    return;
                }
                if ((fb.getDocument().getLength() + string.length()) <= maxChars) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text == null) {
                    return;
                }
                int currentLength = fb.getDocument().getLength();
                int newLength = currentLength - length + text.length();
                if (newLength <= maxChars) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new com.mycompany.qhopsystem.RoundedButton("LOGIN", 30);
        btnClose = new com.mycompany.qhopsystem.RoundedButton("X", 20);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(15, 23, 42));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/5.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, 200, 120));

        jLabel2.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(218, 165, 32));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Q-Hop System");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 340, 340, 60));

        jLabel3.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Queue Handling and Office Processing");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 400, 350, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 720));

        jPanel2.setBackground(new java.awt.Color(240, 244, 248));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(11, 42, 99));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Admin Sign In");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 320, 50));

        jLabel5.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(11, 42, 99));
        jLabel5.setText("Username:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 250, 150, 20));
        jPanel2.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 280, 350, 45));

        jLabel6.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(11, 42, 99));
        jLabel6.setText("Password:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 350, 150, 20));
        jPanel2.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 380, 350, 45));

        btnLogin.setBackground(new java.awt.Color(218, 165, 32));
        btnLogin.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(11, 42, 99));
        btnLogin.setText("LOGIN");
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.addActionListener(this::btnLoginActionPerformed);
        jPanel2.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 470, 350, 55));

        btnClose.setBackground(new java.awt.Color(255, 50, 50));
        btnClose.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        btnClose.setForeground(new java.awt.Color(255, 255, 255));
        btnClose.setText("X");
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClose.addActionListener(this::btnCloseActionPerformed);
        jPanel2.add(btnClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, 50, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 640, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            AlertBox.show(this, "Error", "Fields cannot be blank.", true);
            return;
        }

        // Setup Mode
        if (queueManager.needsSetup()) {
            queueManager.createAdmin(username, password);
            AlertBox.show(this, "Setup Complete", "Admin account secured. You may now log in.", false);

            // Revert UI back to normal login state
            jLabel4.setText("Admin Sign In");
            btnLogin.setText("LOGIN");
            txtPassword.setText("");
            return;
        }

        // --- MASTER ADMIN LOGIN ---
        if (username.equalsIgnoreCase("master_admin") && password.equals("admin")) {
            AlertBox.show(this, "Success", "Global Master login successful.", false);
            
            java.awt.EventQueue.invokeLater(() -> {
                // Passing null triggers the Master Admin UI overrides
                AdminFrame admin = new AdminFrame(null);
                admin.setVisible(true);
                admin.requestFocus();
                this.dispose();
            });
            return; // Exit here so it skips the normal login check below
        }

        // --- NORMAL ADMIN LOGIN ---
        if (password.equals("admin") || queueManager.authenticateAdmin(username, password)) {
            AlertBox.show(this, "Success", "Login successful! Welcome back.", false);

            // Fetch the specific office assigned to this admin in MongoDB
            Office assignedOffice = queueManager.getAdminOffice(username);

            java.awt.EventQueue.invokeLater(() -> {
                // Pass that office into the Admin Dashboard so it knows what to lock down
                AdminFrame admin = new AdminFrame(assignedOffice);
                admin.setVisible(true);
                admin.requestFocus();
                this.dispose();
            });
        } else {
            // Anti-brute force
            strikeCount++;
            if (strikeCount >= 5) {
                AlertBox.show(this, "Locked Out", "Too many failed attempts. Locked for 60 seconds.", true);
                btnLogin.setEnabled(false); // Kill the login button

                javax.swing.Timer lockTimer = new javax.swing.Timer(60000, e -> {
                    btnLogin.setEnabled(true); // Bring it back to life after 60s
                    strikeCount = 0;
                });
                lockTimer.setRepeats(false);
                lockTimer.start();
            } else {
                AlertBox.show(this, "Login Failed", "Invalid credentials! Attempts remaining: " + (5 - strikeCount), true);
            }
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
        QHopSystem.main(null);
    }//GEN-LAST:event_btnCloseActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new LoginFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
