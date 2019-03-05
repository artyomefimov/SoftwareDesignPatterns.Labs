package lab3.observer;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Observer {
    public static final String LEFT_EYE_OPENED = "LEFT EYE OPENED";
    public static final String LEFT_EYE_CLOSED = "LEFT EYE CLOSED";
    public static final String RIGHT_EYE_OPENED = "RIGHT EYE OPENED";
    public static final String RIGHT_EYE_CLOSED = "RIGHT EYE CLOSED";
    public static final String MOUTH_OPENED = "MOUTH OPENED";
    public static final String MOUTH_CLOSED = "MOUTH CLOSED";

    private Ellipse2D leftEyeOpened;
    private Ellipse2D rightEyeOpened;
    private Ellipse2D mouthOpened;

    private Rectangle2D leftEyeClosed;
    private Rectangle2D rightEyeClosed;
    private Rectangle2D mouthClosed;

    private Map<String, Shape> shapes;

    public Observer(Ellipse2D leftEyeOpened, Ellipse2D rightEyeOpened, Ellipse2D mouthOpened) {
        this.leftEyeOpened = leftEyeOpened;
        this.rightEyeOpened = rightEyeOpened;
        this.mouthOpened = mouthOpened;
        leftEyeClosed = new Rectangle2D.Double(
                leftEyeOpened.getMinX(), leftEyeOpened.getCenterY(),
                leftEyeOpened.getWidth(), 1);
        rightEyeClosed = new Rectangle2D.Double(
                rightEyeOpened.getMinX(), rightEyeOpened.getCenterY(),
                rightEyeOpened.getWidth(), 1);
        mouthClosed = new Rectangle2D.Double(
                mouthOpened.getMinX(), mouthOpened.getCenterY(),
                mouthOpened.getWidth(), 1);

        shapes = new HashMap<>();
        shapes.put(LEFT_EYE_OPENED, this.leftEyeOpened);
        shapes.put(RIGHT_EYE_OPENED, this.rightEyeOpened);
        shapes.put(MOUTH_OPENED, this.mouthOpened);
    }

    public Map<String, Shape> handleMouseClick(MouseEvent e, Map<String, Shape> fromPanel) {
        if (isClickedOn(leftEyeOpened, e)) {
            return fromPanel.get(LEFT_EYE_OPENED) != null ?
                    makeShapeList(LEFT_EYE_OPENED, LEFT_EYE_CLOSED, leftEyeClosed, fromPanel) :
                    makeShapeList(LEFT_EYE_CLOSED, LEFT_EYE_OPENED, leftEyeOpened, fromPanel);
        } else if (isClickedOn(rightEyeOpened, e)) {
            return fromPanel.get(RIGHT_EYE_OPENED) != null ?
                    makeShapeList(RIGHT_EYE_OPENED, RIGHT_EYE_CLOSED, rightEyeClosed, fromPanel) :
                    makeShapeList(RIGHT_EYE_CLOSED, RIGHT_EYE_OPENED, rightEyeOpened, fromPanel);
        } else if (isClickedOn(mouthOpened, e)) {
            return fromPanel.get(MOUTH_OPENED) != null ?
                    makeShapeList(MOUTH_OPENED, MOUTH_CLOSED, mouthClosed, fromPanel) :
                    makeShapeList(MOUTH_CLOSED, MOUTH_OPENED, mouthOpened, fromPanel);
        } else return fromPanel;
    }

    private Map<String, Shape> makeShapeList(String keyToRemove, String keyToPut, Shape shape, Map<String, Shape> fromPanel) {
        fromPanel.put(keyToRemove, null);
        fromPanel.put(keyToPut, shape);
        return fromPanel;
    }

    private boolean isClickedOn(Shape shape, MouseEvent e) {
        return shape != null && shape.contains(e.getX(), e.getY());
    }
}
