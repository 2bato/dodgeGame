package model;

import java.util.concurrent.ThreadLocalRandom;

/*
 * Represents a projectile.
 */
public class Projectile {

    public static final int SIZE = 10;
    public static final int SPEED = 2;

    private final double dx;
    private final double dy;

    private double xcoord;
    private double ycoord;

    // Constructor for projectiles
    // EFFECTS: create projectile moving towards coordinates x, y at random coordinates x, y on the edge of the screen.
    public Projectile(int x, int y) {
        double[] coords = randomCoords();
        this.xcoord = coords[0];
        this.ycoord = coords[1];
        double xdiff = x - xcoord;
        double ydiff = y - ycoord;
        double hypotenuse = Math.sqrt(xdiff * xdiff + ydiff * ydiff);
        this.dx = (xdiff / hypotenuse) * SPEED;
        this.dy = (ydiff / hypotenuse) * SPEED;
    }

    // Generate a random coordinate at the edges of the screen.
    public double[] randomCoords() {
        double x;
        double y;
        if (ThreadLocalRandom.current().nextBoolean()) {
            x = ThreadLocalRandom.current().nextDouble(800); //placeholder for screen width
            if (ThreadLocalRandom.current().nextBoolean()) {
                y = 0;
            } else {
                y = 800; // placeholder for screen height
            }
        } else {
            if (ThreadLocalRandom.current().nextBoolean()) {
                x = 0;
            } else {
                x = 800; //placeholder for screen width
            }
            y = ThreadLocalRandom.current().nextDouble(800); //placeholder for screen height
        }
        return new double[]{x, y};
    }

    public double getX() {
        return xcoord;
    }

    public int getY() {
        return (int) ycoord;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    // Updates the missile on clock tick
    // modifies: this
    // effects: missile is moved DY units (up the screen)
    public void move() {
        ycoord += dy;
        xcoord += dx;
    }
}