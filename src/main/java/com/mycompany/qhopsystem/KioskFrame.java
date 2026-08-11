package com.mycompany.qhopsystem;


public class KioskFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(KioskFrame.class.getName());
    private Office selectedOffice;
    private UserCategory selectedCategory;
    private String idNumber;
    
    // We will initialize the QueueManager here later
    private QueueManager qManager = new QueueManager();

    public KioskFrame() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        eastPanel = new javax.swing.JPanel();
        cardContainer = new javax.swing.JPanel();
        homePanel = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        welcomeTxt = new javax.swing.JLabel();
        brandTxt = new javax.swing.JLabel();
        messageTxt = new javax.swing.JLabel();
        buttonPanel = new javax.swing.JPanel();
        registrarBtn = new com.mycompany.qhopsystem.RoundedButton("Registrar", 30);
        admissionBtn = new com.mycompany.qhopsystem.RoundedButton("Registrar", 30);
        treasuryBtn = new com.mycompany.qhopsystem.RoundedButton("Registrar", 30);
        inquiryBtn = new com.mycompany.qhopsystem.RoundedButton("Registrar", 30);
        printConfirmPanel = new javax.swing.JPanel();
        userTypePanel = new javax.swing.JPanel();
        logo1 = new javax.swing.JLabel();
        brandTxt1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        studentBtn = new com.mycompany.qhopsystem.RoundedButton("Registrar", 30);
        staffBtn = new com.mycompany.qhopsystem.RoundedButton("Registrar", 30);
        guestBtn = new com.mycompany.qhopsystem.RoundedButton("Registrar", 30);
        backBtn = new com.mycompany.qhopsystem.RoundedButton("Registrar", 30);
        keypadPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        servicePanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1280, 720));

        eastPanel.setPreferredSize(new java.awt.Dimension(0, 0));

        javax.swing.GroupLayout eastPanelLayout = new javax.swing.GroupLayout(eastPanel);
        eastPanel.setLayout(eastPanelLayout);
        eastPanelLayout.setHorizontalGroup(
            eastPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        eastPanelLayout.setVerticalGroup(
            eastPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(eastPanel, java.awt.BorderLayout.EAST);

        cardContainer.setLayout(new java.awt.CardLayout());

        homePanel.setBackground(new java.awt.Color(15, 23, 42));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/2.png"))); // NOI18N

        welcomeTxt.setFont(new java.awt.Font("Montserrat", 0, 15)); // NOI18N
        welcomeTxt.setForeground(new java.awt.Color(255, 255, 255));
        welcomeTxt.setText("welcome to");

        brandTxt.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 48)); // NOI18N
        brandTxt.setForeground(new java.awt.Color(218, 165, 32));
        brandTxt.setText("Q-Hop");

        messageTxt.setFont(new java.awt.Font("Montserrat", 0, 15)); // NOI18N
        messageTxt.setForeground(new java.awt.Color(255, 255, 255));
        messageTxt.setText("How can we assist you today?");

        buttonPanel.setMinimumSize(new java.awt.Dimension(700, 400));
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new java.awt.Dimension(700, 400));
        buttonPanel.setLayout(new java.awt.GridLayout(2, 2, 25, 25));

        registrarBtn.setBackground(new java.awt.Color(0, 240, 255, 15));
        registrarBtn.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        registrarBtn.setForeground(new java.awt.Color(255, 255, 255));
        registrarBtn.setText("Registrar");
        registrarBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registrarBtn.addActionListener(this::registrarBtnActionPerformed);
        buttonPanel.add(registrarBtn);

        admissionBtn.setBackground(new java.awt.Color(0, 240, 255, 15));
        admissionBtn.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        admissionBtn.setForeground(new java.awt.Color(255, 255, 255));
        admissionBtn.setText("Admission");
        admissionBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        admissionBtn.addActionListener(this::admissionBtnActionPerformed);
        buttonPanel.add(admissionBtn);

        treasuryBtn.setBackground(new java.awt.Color(0, 240, 255, 15));
        treasuryBtn.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        treasuryBtn.setForeground(new java.awt.Color(255, 255, 255));
        treasuryBtn.setText("Treasury");
        treasuryBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        treasuryBtn.addActionListener(this::treasuryBtnActionPerformed);
        buttonPanel.add(treasuryBtn);

        inquiryBtn.setBackground(new java.awt.Color(0, 240, 255, 15));
        inquiryBtn.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        inquiryBtn.setForeground(new java.awt.Color(255, 255, 255));
        inquiryBtn.setText("General Inquiry");
        inquiryBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        inquiryBtn.addActionListener(this::inquiryBtnActionPerformed);
        buttonPanel.add(inquiryBtn);

        javax.swing.GroupLayout homePanelLayout = new javax.swing.GroupLayout(homePanel);
        homePanel.setLayout(homePanelLayout);
        homePanelLayout.setHorizontalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePanelLayout.createSequentialGroup()
                .addGap(540, 540, 540)
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(messageTxt)
                    .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(brandTxt)
                        .addComponent(logo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homePanelLayout.createSequentialGroup()
                .addContainerGap(241, Short.MAX_VALUE)
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homePanelLayout.createSequentialGroup()
                        .addComponent(welcomeTxt)
                        .addGap(590, 590, 590))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homePanelLayout.createSequentialGroup()
                        .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(231, 231, 231))))
        );
        homePanelLayout.setVerticalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePanelLayout.createSequentialGroup()
                .addComponent(logo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(welcomeTxt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(brandTxt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messageTxt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        cardContainer.add(homePanel, "successPanel");

        javax.swing.GroupLayout printConfirmPanelLayout = new javax.swing.GroupLayout(printConfirmPanel);
        printConfirmPanel.setLayout(printConfirmPanelLayout);
        printConfirmPanelLayout.setHorizontalGroup(
            printConfirmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        printConfirmPanelLayout.setVerticalGroup(
            printConfirmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        cardContainer.add(printConfirmPanel, "printConfirmPanel");

        userTypePanel.setBackground(new java.awt.Color(15, 23, 42));

        logo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/3.png"))); // NOI18N

        brandTxt1.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 48)); // NOI18N
        brandTxt1.setForeground(new java.awt.Color(218, 165, 32));
        brandTxt1.setText("<html><p>USER TYPE<br>SELECTION</p></html>");

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridLayout(3, 1, 0, 20));

        studentBtn.setBackground(new java.awt.Color(0, 240, 255, 15)
        );
        studentBtn.setFont(new java.awt.Font("Montserrat", 1, 15)); // NOI18N
        studentBtn.setForeground(new java.awt.Color(255, 255, 255));
        studentBtn.setText("Student / Parent");
        studentBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        studentBtn.addActionListener(this::studentBtnActionPerformed);
        jPanel1.add(studentBtn);

        staffBtn.setBackground(new java.awt.Color(0, 240, 255, 15));
        staffBtn.setFont(new java.awt.Font("Montserrat", 1, 15)); // NOI18N
        staffBtn.setForeground(new java.awt.Color(255, 255, 255));
        staffBtn.setText("Staff / Employee");
        staffBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        staffBtn.addActionListener(this::staffBtnActionPerformed);
        jPanel1.add(staffBtn);

        guestBtn.setBackground(new java.awt.Color(0, 240, 255, 15));
        guestBtn.setFont(new java.awt.Font("Montserrat", 1, 15)); // NOI18N
        guestBtn.setForeground(new java.awt.Color(255, 255, 255));
        guestBtn.setText("Guest");
        guestBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        guestBtn.addActionListener(this::guestBtnActionPerformed);
        jPanel1.add(guestBtn);

        backBtn.setText("BACK");
        backBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backBtn.addActionListener(this::backBtnActionPerformed);

        javax.swing.GroupLayout userTypePanelLayout = new javax.swing.GroupLayout(userTypePanel);
        userTypePanel.setLayout(userTypePanelLayout);
        userTypePanelLayout.setHorizontalGroup(
            userTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userTypePanelLayout.createSequentialGroup()
                .addContainerGap(327, Short.MAX_VALUE)
                .addGroup(userTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userTypePanelLayout.createSequentialGroup()
                        .addComponent(backBtn)
                        .addGap(609, 609, 609))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userTypePanelLayout.createSequentialGroup()
                        .addComponent(logo1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(brandTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(452, 452, 452))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userTypePanelLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(412, 412, 412))))
        );
        userTypePanelLayout.setVerticalGroup(
            userTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userTypePanelLayout.createSequentialGroup()
                .addGroup(userTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userTypePanelLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(brandTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(userTypePanelLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(logo1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89)
                .addComponent(backBtn)
                .addGap(32, 32, 32))
        );

        cardContainer.add(userTypePanel, "userTypePanel");

        keypadPanel.setBackground(new java.awt.Color(15, 23, 42));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/3.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        jLabel2.setText("<html><p>Please enter your <br> ID number</p></html>");

        javax.swing.GroupLayout keypadPanelLayout = new javax.swing.GroupLayout(keypadPanel);
        keypadPanel.setLayout(keypadPanelLayout);
        keypadPanelLayout.setHorizontalGroup(
            keypadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(keypadPanelLayout.createSequentialGroup()
                .addGap(358, 358, 358)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(508, Short.MAX_VALUE))
        );
        keypadPanelLayout.setVerticalGroup(
            keypadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(keypadPanelLayout.createSequentialGroup()
                .addGroup(keypadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(keypadPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(keypadPanelLayout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(482, Short.MAX_VALUE))
        );

        cardContainer.add(keypadPanel, "card6");

        servicePanel.setBackground(new java.awt.Color(15, 23, 42));

        javax.swing.GroupLayout servicePanelLayout = new javax.swing.GroupLayout(servicePanel);
        servicePanel.setLayout(servicePanelLayout);
        servicePanelLayout.setHorizontalGroup(
            servicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1292, Short.MAX_VALUE)
        );
        servicePanelLayout.setVerticalGroup(
            servicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 734, Short.MAX_VALUE)
        );

        cardContainer.add(servicePanel, "servicePanel");

        getContentPane().add(cardContainer, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void registrarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarBtnActionPerformed
        selectedOffice = Office.REGISTRAR;
        java.awt.CardLayout cardLayout = (java.awt.CardLayout) cardContainer.getLayout();
        cardLayout.show(cardContainer, "userTypePanel");
    }//GEN-LAST:event_registrarBtnActionPerformed

    private void admissionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionBtnActionPerformed
        selectedOffice = Office.ADMISSIONS;
        java.awt.CardLayout cardLayout = (java.awt.CardLayout) cardContainer.getLayout();
        cardLayout.show(cardContainer, "userTypePanel");
    }//GEN-LAST:event_admissionBtnActionPerformed

    private void treasuryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_treasuryBtnActionPerformed
        selectedOffice = Office.TREASURY;
        java.awt.CardLayout cardLayout = (java.awt.CardLayout) cardContainer.getLayout();
        cardLayout.show(cardContainer, "userTypePanel");
    }//GEN-LAST:event_treasuryBtnActionPerformed

    private void inquiryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inquiryBtnActionPerformed
        selectedOffice = Office.GENERAL_INQUIRY;
        java.awt.CardLayout cardLayout = (java.awt.CardLayout) cardContainer.getLayout();
        cardLayout.show(cardContainer, "userTypePanel");
    }//GEN-LAST:event_inquiryBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        selectedOffice = null;
        java.awt.CardLayout cardLayout = (java.awt.CardLayout) cardContainer.getLayout();
        cardLayout.show(cardContainer, "successPanel");
    }//GEN-LAST:event_backBtnActionPerformed

    private void studentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentBtnActionPerformed
        selectedCategory = UserCategory.STUDENT_PARENT;

        java.awt.CardLayout cardLayout = (java.awt.CardLayout) cardContainer.getLayout();
        cardLayout.show(cardContainer, "keypadPanel");
    }//GEN-LAST:event_studentBtnActionPerformed

    private void staffBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staffBtnActionPerformed
        selectedCategory = UserCategory.STAFF_EMPLOYEE;

        java.awt.CardLayout cardLayout = (java.awt.CardLayout) cardContainer.getLayout();
        cardLayout.show(cardContainer, "keypadPanel");
    }//GEN-LAST:event_staffBtnActionPerformed

    private void guestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guestBtnActionPerformed
        selectedCategory = UserCategory.GUEST;
        idNumber = "N/A";

        java.awt.CardLayout cardLayout = (java.awt.CardLayout) cardContainer.getLayout();
        cardLayout.show(cardContainer, "printConfirmPanel");
    }//GEN-LAST:event_guestBtnActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new KioskFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton admissionBtn;
    private javax.swing.JButton backBtn;
    private javax.swing.JLabel brandTxt;
    private javax.swing.JLabel brandTxt1;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JPanel cardContainer;
    private javax.swing.JPanel eastPanel;
    private javax.swing.JButton guestBtn;
    private javax.swing.JPanel homePanel;
    private javax.swing.JButton inquiryBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel keypadPanel;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel logo1;
    private javax.swing.JLabel messageTxt;
    private javax.swing.JPanel printConfirmPanel;
    private javax.swing.JButton registrarBtn;
    private javax.swing.JPanel servicePanel;
    private javax.swing.JButton staffBtn;
    private javax.swing.JButton studentBtn;
    private javax.swing.JButton treasuryBtn;
    private javax.swing.JPanel userTypePanel;
    private javax.swing.JLabel welcomeTxt;
    // End of variables declaration//GEN-END:variables
}
