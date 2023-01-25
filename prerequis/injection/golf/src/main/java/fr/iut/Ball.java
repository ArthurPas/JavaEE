package fr.iut;
import java.awt.geom.Point2D;
/**
 * Golf ball class.
 */
public class Ball {
    private final Point2D position = new Point2D.Double();
    /**
     * default constructor with a position of (0,0)
     */
    public Ball() {
        position.setLocation(0, 0);
    }
    public Point2D getPosition() {
        return position;
    }
    public void setPosition(final Point2D position) {
        this.position.setLocation(position);
    }
}