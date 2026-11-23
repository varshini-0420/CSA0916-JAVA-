import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DrawingPadApp extends JFrame {

    private ArrayList<Point> points = new ArrayList<>();
    private JLabel statusLabel;
    private JPanel drawingPanel;

    public DrawingPadApp() {
        setTitle("Drawing Pad");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        statusLabel = new JLabel("Click inside the drawing area to draw.");

        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                for (Point point : points) {
                    g.fillOval(point.x - 4, point.y - 4, 8, 8);
                }
            }
        };

        drawingPanel.setBackground(Color.WHITE);

        drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                points.add(e.getPoint());

                statusLabel.setText(
                    "Points drawn: " + points.size()
                    + " | X: " + e.getX()
                    + " | Y: " + e.getY()
                );

                drawingPanel.repaint();
            }
        });

        add(drawingPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DrawingPadApp());
    }
}