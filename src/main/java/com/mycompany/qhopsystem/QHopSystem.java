package com.mycompany.qhopsystem;

public class QHopSystem {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KioskFrame().setVisible(true);
            }
        });
    }
}