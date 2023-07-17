package model;

import java.util.concurrent.ThreadLocalRandom;

/*
 * Represents a projectile.
 */
public class Projectile {

    public static final int SIZE = 1;
    public static final Double SPEED = 0.5;

    private double dx;
    private double dy;
    private double xcoord;
    private double ycoord;

    // Constructor for projectiles
    // EFFECTS: create projectile moving towards coordinates x, y at random coordinates x, y on the edge of the screen.
    public Projectile(double x, double y) {
        double[] coords = randomCoords();
        this.xcoord = coords[0];
        this.ycoord = coords[1];
        double xdiff = x - xcoord;
        double ydiff = y - ycoord;
        double hypotenuse = Math.sqrt(xdiff * xdiff + ydiff * ydiff);
        this.dx = (xdiff / hypotenuse) * SPEED;
        this.dy = (ydiff / hypotenuse) * SPEED;
    }

    public double getX() {
        return xcoord;
    }

    public double getY() {
        return (int) ycoord;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    // Generate a random coordinate at the edges of the screen.
    // EFFECTS: return double[] x, y of a random coordinate at the edge of the game
    public double[] randomCoords() {
        double x;
        double y;
        if (ThreadLocalRandom.current().nextBoolean()) {
            x = ThreadLocalRandom.current().nextDouble(MyGame.WIDTH);
            if (ThreadLocalRandom.current().nextBoolean()) {
                y = 0;
            } else {
                y = MyGame.HEIGHT;
            }
        } else {
            if (ThreadLocalRandom.current().nextBoolean()) {
                x = 0;
            } else {
                x = MyGame.WIDTH;
            }
            y = ThreadLocalRandom.current().nextDouble(MyGame.WIDTH);
        }
        return new double[]{x, y};
    }

    // Updates the location of the projectile
    // MODIFIES: this
    // EFFECTS: projectiles moves by dx and dy
    public void move() {
        ycoord += dy;
        xcoord += dx;
    }
}