package com.mycompany.qhopsystem;


public class KioskFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(KioskFrame.class.getName());


    public KioskFrame() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        eastPanel = new javax.swing.JPanel();
        cardContainer = new javax.swing.JPanel();
        successPanel = new javax.swing.JPanel();
        landingPanel = new javax.swing.JPanel();
        mainCardPanel = new com.mycompany.qhopsystem.RoundedPanel(40);
        btnStartKiosk = new com.mycompany.qhopsystem.RoundedButton("Click to start", 20);
        contentPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        printConfirmPanel = new javax.swing.JPanel();
        userTypePanel = new javax.swing.JPanel();
        servicePanel = new javax.swing.JPanel();
        jPanel1 = new com.mycompany.qhopsystem.RoundedPanel(40);

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

        landingPanel.setBackground(new java.awt.Color(0, 51, 102));
        landingPanel.setLayout(new java.awt.GridBagLayout());

        mainCardPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainCardPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainCardPanel.setMinimumSize(new java.awt.Dimension(500, 700));
        mainCardPanel.setPreferredSize(new java.awt.Dimension(500, 600));
        mainCardPanel.setLayout(new java.awt.BorderLayout());

        btnStartKiosk.setBackground(new java.awt.Color(218, 165, 32));
        btnStartKiosk.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnStartKiosk.setForeground(new java.awt.Color(0, 0, 0));
        btnStartKiosk.setText("Click to start");
        btnStartKiosk.setPreferredSize(new java.awt.Dimension(500, 80));
        btnStartKiosk.addActionListener(this::btnStartKioskActionPerformed);
        mainCardPanel.add(btnStartKiosk, java.awt.BorderLayout.SOUTH);

        contentPanel.setBackground(new java.awt.Color(255, 255, 255));
        contentPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Please tap the button below to begin.");
        contentPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Welcome!");
        contentPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/2.png"))); // NOI18N
        jLabel4.setText("jLabel4");
        jLabel4.setMinimumSize(new java.awt.Dimension(200, 200));
        jLabel4.setPreferredSize(new java.awt.Dimension(200, 200));
        contentPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, -1, 100));

        jSeparator1.setForeground(new java.awt.Color(218, 165, 32));
        contentPanel.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 260, 10));

        mainCardPanel.add(contentPanel, java.awt.BorderLayout.CENTER);

        landingPanel.add(mainCardPanel, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout successPanelLayout = new javax.swing.GroupLayout(successPanel);
        successPanel.setLayout(successPanelLayout);
        successPanelLayout.setHorizontalGroup(
            successPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(successPanelLayout.createSequentialGroup()
                .addComponent(landingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        successPanelLayout.setVerticalGroup(
            successPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(successPanelLayout.createSequentialGroup()
                .addComponent(landingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        cardContainer.add(successPanel, "successPanel");

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

        javax.swing.GroupLayout userTypePanelLayout = new javax.swing.GroupLayout(userTypePanel);
        userTypePanel.setLayout(userTypePanelLayout);
        userTypePanelLayout.setHorizontalGroup(
            userTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        userTypePanelLayout.setVerticalGroup(
            userTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        cardContainer.add(userTypePanel, "userTypePanel");

        servicePanel.setBackground(new java.awt.Color(0, 51, 102));
        servicePanel.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        jPanel1.setMinimumSize(new java.awt.Dimension(500, 600));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 600));
        jPanel1.setLayout(new java.awt.BorderLayout());
        servicePanel.add(jPanel1, new java.awt.GridBagConstraints());

        cardContainer.add(servicePanel, "servicePanel");

        getContentPane().add(cardContainer, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartKioskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartKioskActionPerformed
        final int width = cardContainer.getWidth() > 0 ? cardContainer.getWidth() : 1280;
        final int height = cardContainer.getHeight() > 0 ? cardContainer.getHeight() : 720;

        // Take snapshot images of the panels for smooth hardware-level sliding
        java.awt.image.BufferedImage imgLanding = new java.awt.image.BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        landingPanel.paint(imgLanding.getGraphics());

        servicePanel.setSize(width, height);
        servicePanel.setVisible(true);
        java.awt.image.BufferedImage imgService = new java.awt.image.BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        servicePanel.paint(imgService.getGraphics());
        servicePanel.setVisible(false);

        final javax.swing.JLayeredPane layeredPane = cardContainer.getRootPane().getLayeredPane();

        // Use an array so the inner class can safely update the offset value without casting errors
        final int[] animOffset = {0};

        final javax.swing.JPanel animPanel = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                g.drawImage(imgLanding, -animOffset[0], 0, null);
                g.drawImage(imgService, width - animOffset[0], 0, null);
            }
        };

        java.awt.Point loc = cardContainer.getLocationOnScreen();
        java.awt.Point rootLoc = cardContainer.getRootPane().getLocationOnScreen();
        animPanel.setBounds(loc.x - rootLoc.x, loc.y - rootLoc.y, width, height);
        animPanel.setOpaque(false);

        cardContainer.setVisible(false);
        layeredPane.add(animPanel, javax.swing.JLayeredPane.PALETTE_LAYER);

        final int totalSteps = 25;
        final int[] currentStep = {0};

        javax.swing.Timer slideTimer = new javax.swing.Timer(10, new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                currentStep[0]++;
                float progress = (float) currentStep[0] / totalSteps;
                // Smooth ease-out curve
                float eased = 1 - (1 - progress) * (1 - progress);
                animOffset[0] = (int) (eased * width);
                animPanel.repaint();

                if (currentStep[0] >= totalSteps) {
                    ((javax.swing.Timer)e.getSource()).stop();
                    layeredPane.remove(animPanel);
                    layeredPane.repaint();
                    cardContainer.setVisible(true);

                    java.awt.CardLayout cl = (java.awt.CardLayout)(cardContainer.getLayout());
                    cl.show(cardContainer, "servicePanel");
                }
            }
        });

        slideTimer.start();
    }//GEN-LAST:event_btnStartKioskActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new KioskFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStartKiosk;
    private javax.swing.JPanel cardContainer;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JPanel eastPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel landingPanel;
    private javax.swing.JPanel mainCardPanel;
    private javax.swing.JPanel printConfirmPanel;
    private javax.swing.JPanel servicePanel;
    private javax.swing.JPanel successPanel;
    private javax.swing.JPanel userTypePanel;
    // End of variables declaration//GEN-END:variables
}
