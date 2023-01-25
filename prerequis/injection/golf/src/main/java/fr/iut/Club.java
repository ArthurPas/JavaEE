package fr.iut;
/**
 * Abstraction for all golf club
 */
public interface Club {
    /**
     * @param force     float between 0 and 1 to indicate the force vector value
     * @param direction the direction assuming North is PI/2 rad
     * @param ball      the ball to move
     */
    void shoot(final double force, final double direction, Ball ball);
}