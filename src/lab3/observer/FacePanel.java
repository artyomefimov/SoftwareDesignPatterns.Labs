package lab3.observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lab3.observer.Observer.*;

public class FacePanel extends JPanel {

    private Observer observer;
    private Map<String, Shape> shapes;
    private boolean isFirst = true;

    public FacePanel() {
        shapes = new HashMap<>();

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (observer != null && e.getButton() == MouseEvent.BUTTON1) {
                    shapes = observer.handleMouseClick(e, shapes);
                    FacePanel.this.repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.yellow);
        g2d.fillOval(30, 30, getWidth() - 60, getHeight() - 60);

        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(10));

        if (isFirst) {
            Ellipse2D leftEyeOpened = new Ellipse2D.Double(
                    (double) getWidth() / 4 - (double) getWidth() / 16,
                    (double) getHeight() / 2 - (double) getHeight() / 8,
                    (double) getWidth() / 8,
                    (double) getHeight() / 8);
            shapes.put(LEFT_EYE_OPENED, leftEyeOpened);
            Ellipse2D rightEyeOpened = new Ellipse2D.Double(
                    (double) getWidth() * 3 / 4 - (double) getWidth() / 16,
                    (double) getHeight() / 2 - (double) getHeight() / 8,
                    (double) getWidth() / 8,
                    (double) getHeight() / 8);
            shapes.put(RIGHT_EYE_OPENED, rightEyeOpened);
            Ellipse2D mouthOpened = new Ellipse2D.Double(
                    leftEyeOpened.getMaxX(),
                    (double) getHeight() * 3 / 4 - (double) getHeight() / 16,
                    rightEyeOpened.getMinX() - leftEyeOpened.getMaxX(),
                    (double) getHeight() / 16);
            shapes.put(MOUTH_OPENED, mouthOpened);
            Rectangle2D noseBlack = new Rectangle2D.Double(
                    leftEyeOpened.getMaxX() + (rightEyeOpened.getMinX() - leftEyeOpened.getMaxX())/3 + 6,
                    (double) getHeight() * 3 / 5 - 2*(double) getHeight() / 16,
                    (rightEyeOpened.getMinX() - leftEyeOpened.getMaxX()) / 4,
                    (double) getHeight() * 3 / 5 - (double) getHeight() / 2
            );
            shapes.put(NOSE_BLACK, noseBlack);
            observer = new Observer(leftEyeOpened, rightEyeOpened, mouthOpened, noseBlack);
            isFirst = false;
        }

        for (Map.Entry<String, Shape> entry : shapes.entrySet()) {
            if (entry.getValue() != null) {
                if (entry.getKey().equals(NOSE_RED)) {
                    g2d.setColor(Color.red);
                    g2d.fill(entry.getValue());
                    g2d.setColor(Color.black);
                }
                else
                    g2d.fill(entry.getValue());
            }
        }
    }
}
