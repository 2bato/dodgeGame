package model;

import java.awt.*;


/*
 * Represents the player.
 */
public class Player {
    public static final int SIZE = 15;
    public static final int SPEED = 20;
    private int xcoord;
    private int ycoord;

    // Constructor for Player.
    // EFFECTS: create player at position x, y.
    public Player(int x, int y) {
        this.xcoord = x;
        this.ycoord = y;
    }

    public int getX() {
        return xcoord;
    }

    public int getY() {
        return ycoord;
    }

    public void moveLeft() {
        xcoord -= SPEED;

        handleBoundary();
    }

    public void moveRight() {
        xcoord += SPEED;

        handleBoundary();
    }

    public void moveUp() {
        ycoord -= SPEED;

        handleBoundary();
    }

    public void moveDown() {
        ycoord += SPEED;

        handleBoundary();
    }

    //Based from Space Invader
    private void handleBoundary() {
        if (xcoord < 0) {
            xcoord = 0;
        } else if (xcoord > 800) {
            xcoord = 800;
        } else if (ycoord < 0) {
            ycoord = 0;
        } else if (ycoord > 800) {
            ycoord = 800;
        }
    }

    //Based from Space Invader
    // Determines if player has collided with a projectile
    // modifies: none
    // effects:  returns true if player has collided with projectile m, false otherwise
    public boolean checkHit(Projectile p) {
        Rectangle playerBoundingSquare = new Rectangle(getX() - SIZE / 2, getY() - SIZE / 2, SIZE, SIZE);
        Rectangle projectileBoundingRect = new Rectangle((int) (p.getX() - Projectile.SIZE / 2), (int) (p.getY()
                - Projectile.SIZE / 2), Projectile.SIZE, Projectile.SIZE);
        return playerBoundingSquare.intersects(projectileBoundingRect);
    }
}

