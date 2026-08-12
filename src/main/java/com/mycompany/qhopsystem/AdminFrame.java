package com.mycompany.qhopsystem;


public class AdminFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AdminFrame.class.getName());

    public AdminFrame() {
        initComponents();
        
        // scale the logo (dont change please)
        javax.swing.ImageIcon originalIcon = new javax.swing.ImageIcon(getClass().getResource("/5.png"));
        java.awt.Image scaledImage = originalIcon.getImage().getScaledInstance(200, -1, java.awt.Image.SCALE_SMOOTH);
        jLabel1.setIcon(new javax.swing.ImageIcon(scaledImage));
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sidebarPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnNavDashboard = new com.mycompany.qhopsystem.RoundedButton("Dashboard", 30);
        btnNavQueue = new com.mycompany.qhopsystem.RoundedButton("Queue", 30);
        btnNavTransaction = new com.mycompany.qhopsystem.RoundedButton("Transaction", 30);
        contentContainer = new javax.swing.JPanel();
        dashboardPanel = new javax.swing.JPanel();
        jPanel3 = new com.mycompany.qhopsystem.RoundedPanel(30);
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new com.mycompany.qhopsystem.RoundedPanel(30);
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new com.mycompany.qhopsystem.RoundedPanel(30);
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new com.mycompany.qhopsystem.RoundedPanel(30);
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new com.mycompany.qhopsystem.RoundedPanel(20);
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        sidebarPanel.setBackground(new java.awt.Color(15, 23, 42));
        sidebarPanel.setMaximumSize(new java.awt.Dimension(250, 720));
        sidebarPanel.setMinimumSize(new java.awt.Dimension(250, 720));
        sidebarPanel.setPreferredSize(new java.awt.Dimension(250, 720));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/5.png"))); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(120, 120));

        btnNavDashboard.setBackground(new java.awt.Color(218, 165, 32));
        btnNavDashboard.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        btnNavDashboard.setForeground(new java.awt.Color(11, 42, 99));
        btnNavDashboard.setText("Dashboard");
        btnNavDashboard.setBorderPainted(false);
        btnNavDashboard.setContentAreaFilled(false);
        btnNavDashboard.setFocusPainted(false);
        btnNavDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        btnNavQueue.setBackground(new java.awt.Color(15, 23, 42));
        btnNavQueue.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        btnNavQueue.setForeground(new java.awt.Color(255, 255, 255));
        btnNavQueue.setText("Queue");
        btnNavQueue.setBorderPainted(false);
        btnNavQueue.setContentAreaFilled(false);
        btnNavQueue.setFocusPainted(false);
        btnNavQueue.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        btnNavTransaction.setBackground(new java.awt.Color(15, 23, 42));
        btnNavTransaction.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        btnNavTransaction.setForeground(new java.awt.Color(255, 255, 255));
        btnNavTransaction.setText("Transaction");
        btnNavTransaction.setBorderPainted(false);
        btnNavTransaction.setContentAreaFilled(false);
        btnNavTransaction.setFocusPainted(false);
        btnNavTransaction.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout sidebarPanelLayout = new javax.swing.GroupLayout(sidebarPanel);
        sidebarPanel.setLayout(sidebarPanelLayout);
        sidebarPanelLayout.setHorizontalGroup(
            sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNavDashboard)
                    .addComponent(btnNavQueue)
                    .addComponent(btnNavTransaction)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        sidebarPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnNavDashboard, btnNavQueue, btnNavTransaction});

        sidebarPanelLayout.setVerticalGroup(
            sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarPanelLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNavDashboard)
                .addGap(18, 18, 18)
                .addComponent(btnNavQueue)
                .addGap(18, 18, 18)
                .addComponent(btnNavTransaction)
                .addGap(0, 471, Short.MAX_VALUE))
        );

        sidebarPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnNavDashboard, btnNavQueue, btnNavTransaction});

        getContentPane().add(sidebarPanel, java.awt.BorderLayout.WEST);

        contentContainer.setBackground(new java.awt.Color(240, 244, 248));
        contentContainer.setPreferredSize(new java.awt.Dimension(1041, 720));
        contentContainer.setLayout(new java.awt.CardLayout());

        dashboardPanel.setBackground(new java.awt.Color(240, 244, 248));
        dashboardPanel.setLayout(null);

        jPanel3.setBackground(new java.awt.Color(15, 23, 42));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setMaximumSize(new java.awt.Dimension(200, 110));
        jPanel3.setMinimumSize(new java.awt.Dimension(200, 110));
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(200, 110));

        jLabel4.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("24");

        jLabel7.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Complete");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(98, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(33, 33, 33))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(33, 33, 33))
        );

        dashboardPanel.add(jPanel3);
        jPanel3.setBounds(445, 6, 200, 110);

        jPanel2.setBackground(new java.awt.Color(15, 23, 42));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(200, 110));
        jPanel2.setMinimumSize(new java.awt.Dimension(200, 110));
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(200, 110));

        jLabel3.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("1");

        jLabel6.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Serving");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(92, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(54, 54, 54))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(35, 35, 35))
        );

        dashboardPanel.add(jPanel2);
        jPanel2.setBounds(239, 6, 200, 110);

        jPanel1.setBackground(new java.awt.Color(15, 23, 42));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(200, 110));
        jPanel1.setMinimumSize(new java.awt.Dimension(200, 110));
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(200, 110));

        jLabel2.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("3");

        jLabel5.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Waiting");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(73, 73, 73))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(31, 31, 31))
        );

        dashboardPanel.add(jPanel1);
        jPanel1.setBounds(33, 6, 200, 110);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setMaximumSize(new java.awt.Dimension(400, 300));
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(350, 250));

        jLabel9.setFont(new java.awt.Font("Montserrat", 1, 72)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(218, 165, 32));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("R-001");

        jLabel10.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(11, 42, 99));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Registrar / Request Document");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(37, 37, 37))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel9)
                .addGap(30, 30, 30)
                .addComponent(jLabel10)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        dashboardPanel.add(jPanel4);
        jPanel4.setBounds(33, 203, 350, 250);

        jPanel5.setBackground(new java.awt.Color(210, 220, 230));
        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(200, 40));

        jLabel8.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(11, 42, 99));
        jLabel8.setText("Currently Serving");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel8)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        dashboardPanel.add(jPanel5);
        jPanel5.setBounds(104, 134, 200, 40);

        contentContainer.add(dashboardPanel, "card5");

        getContentPane().add(contentContainer, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new AdminFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNavDashboard;
    private javax.swing.JButton btnNavQueue;
    private javax.swing.JButton btnNavTransaction;
    private javax.swing.JPanel contentContainer;
    private javax.swing.JPanel dashboardPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel sidebarPanel;
    // End of variables declaration//GEN-END:variables
}
