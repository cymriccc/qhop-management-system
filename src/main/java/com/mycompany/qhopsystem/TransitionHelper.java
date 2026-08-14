package com.mycompany.qhopsystem;

import java.awt.AlphaComposite;
import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Window;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class TransitionHelper {

    // Prevents spam-clicking from breaking the animation
    private static boolean isAnimating = false;

    public static void fade(JPanel container, String nextCard) {
        if (isAnimating) {
            return;
        }

        Window window = SwingUtilities.getWindowAncestor(container);
        if (!(window instanceof JFrame)) {
            // Fallback just in case the frame isn't fully loaded
            ((CardLayout) container.getLayout()).show(container, nextCard);
            return;
        }

        isAnimating = true;
        JFrame frame = (JFrame) window;

        // 1. Capture the current screen before swapping
        BufferedImage img = new BufferedImage(container.getWidth(), container.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        container.paint(g2);
        g2.dispose();

        // 2. Create an animated glass pane
        FadePane fadePane = new FadePane(img, container);
        frame.setGlassPane(fadePane);
        fadePane.setVisible(true);

        // 3. Swap the card instantly (hidden behind the glass pane screenshot)
        ((CardLayout) container.getLayout()).show(container, nextCard);

        // 4. Animate the dissolve effect
        Timer timer = new Timer(15, null);
        timer.addActionListener(e -> {
            fadePane.alpha -= 0.05f; // Adjust this value to change transition speed

            if (fadePane.alpha <= 0.0f) {
                fadePane.alpha = 0.0f;
                timer.stop();
                fadePane.setVisible(false);
                frame.setGlassPane(new JPanel()); // Clean up
                isAnimating = false;
            }
            fadePane.repaint();
        });
        timer.start();
    }

    private static class FadePane extends JComponent {

        float alpha = 1.0f;
        BufferedImage image;
        JPanel container;

        public FadePane(BufferedImage image, JPanel container) {
            this.image = image;
            this.container = container;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            // Apply the current opacity level
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

            // Map the coordinates so the screenshot perfectly aligns
            Point pt = SwingUtilities.convertPoint(container, 0, 0, this);
            g2.drawImage(image, pt.x, pt.y, null);
            g2.dispose();
        }
    }
}
