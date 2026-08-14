package com.mycompany.qhopsystem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class RoundedButton extends JButton {
    
    private int radius;
    private float hoverAlpha = 0.0f;

    public RoundedButton(String text, int radius) {
        super(text);
        this.radius = radius;
        
        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false); 

        // INSTANT HOVER: Bypasses the Linux Wayland animation flicker entirely
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hoverAlpha = 0.2f; // Instantly apply the 20% white glow
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hoverAlpha = 0.0f; // Instantly remove the glow
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // 1. Base Glass Fill
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        
        // 2. Instant Hover Overlay
        if (hoverAlpha > 0.0f) {
            g2.setColor(new Color(255, 255, 255, (int) (hoverAlpha * 255)));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        }
        
        // 3. Highlight Edge
        g2.setColor(new Color(255, 255, 255, 40)); 
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        
        g2.dispose();
        super.paintComponent(g);
    }
}