package fr.iut.club;


import fr.iut.Ball;
import fr.iut.Club;
import java.awt.geom.Point2D;
/**
 * Simple putter club implementation.
 */
public class Putter implements Club {
    /**
     * inner constant used to compute new position after shoot
     */
    private final static int DISTANCE_MAX = 10;
    public void shoot(final double force, final double direction, final Ball ball) {
        double x = ball.getPosition().getX();
        double y = ball.getPosition().getY();
        x += (force * DISTANCE_MAX) * Math.sin(direction);
        y += (force * DISTANCE_MAX) * Math.cos(direction);
        ball.setPosition(new Point2D.Double(x, y));  }
}