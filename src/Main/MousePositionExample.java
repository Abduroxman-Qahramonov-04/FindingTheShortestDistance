package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MousePositionExample {
    private static int circleX = -10; // Initial circle coordinates outside the panel
    private static int circleY = -10;
    private static final int CIRCLE_RADIUS = 10;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mouse Position Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.RED);
                if (circleX != -10 && circleY != -10) {
                    g.fillOval(circleX - CIRCLE_RADIUS, circleY - CIRCLE_RADIUS, CIRCLE_RADIUS * 2, CIRCLE_RADIUS * 2);
                }
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400, 300);
            }
        };

        JLabel coordinatesLabel = new JLabel("Coordinates:");

        panel.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                int x = e.getX();
//                int y = e.getY();
//                coordinatesLabel.setText("Coordinates: X = " + x + ", Y = " + y);
//                circleX = x;
//                circleY = y;
//                panel.repaint(); // Trigger repaint to draw the circle
//            }
        });

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.add(coordinatesLabel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }
}


