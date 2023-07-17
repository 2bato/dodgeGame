package model;

import java.awt.*;


/*
 * Represents the player.
 */
public class Player {
    public static final int SIZE = 2;
    public static final int SPEED = 1;
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

    // Move play left
    // MODIFIES: this
    // EFFECTS: move player left by SPEED
    public void moveLeft() {
        xcoord -= SPEED;
        handleBoundary();
    }

    // Move play right
    // MODIFIES: this
    // EFFECTS: move player right by SPEED
    public void moveRight() {
        xcoord += SPEED;
        handleBoundary();
    }

    // Move play up
    // MODIFIES: this
    // EFFECTS: move player up by SPEED
    public void moveUp() {
        ycoord -= SPEED;
        handleBoundary();
    }

    // Move play down
    // MODIFIES: this
    // EFFECTS: move player down by SPEED
    public void moveDown() {
        ycoord += SPEED;
        handleBoundary();
    }

    // Based from Space Invader
    // Ensures players stays within the boundaries of the game
    // MODIFIES: this
    // EFFECTS: moves player back if it goes beyond boundaries
    private void handleBoundary() {
        if (xcoord < 0) {
            xcoord = 0;
        } else if (xcoord > MyGame.WIDTH) {
            xcoord = MyGame.WIDTH;
        } else if (ycoord < 0) {
            ycoord = 0;
        } else if (ycoord > MyGame.HEIGHT) {
            ycoord = MyGame.HEIGHT;
        }
    }

    // Based from Space Invader
    // Determines if player has collided with a projectile
    // EFFECTS:  returns true if player has collided with projectile m, false otherwise
    public boolean checkHit(Projectile p) {
        Rectangle playerBoundingSquare = new Rectangle(getX() - SIZE / 2, getY() - SIZE / 2, SIZE, SIZE);
        Rectangle projectileBoundingRect = new Rectangle((int) (p.getX() - Projectile.SIZE / 2), (int) (p.getY()
                - Projectile.SIZE / 2), Projectile.SIZE, Projectile.SIZE);
        return playerBoundingSquare.intersects(projectileBoundingRect);
    }
}

