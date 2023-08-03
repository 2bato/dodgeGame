package model;

import org.json.JSONObject;
import persistence.Writable;

import java.awt.*;


/*
 * Represents the player.
 */
public class Player implements Writable {
    public static final int SIZE = 40;
    public static final int SPEED = 15;
    private int x;
    private int y;

    // Constructor for Player.
    // EFFECTS: create player at position x, y.
    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Move play left
    // MODIFIES: this
    // EFFECTS: move player left by SPEED
    public void moveLeft() {
        x -= SPEED;
        handleBoundary();
    }

    // Move play right
    // MODIFIES: this
    // EFFECTS: move player right by SPEED
    public void moveRight() {
        x += SPEED;
        handleBoundary();
    }

    // Move play up
    // MODIFIES: this
    // EFFECTS: move player up by SPEED
    public void moveUp() {
        y -= SPEED;
        handleBoundary();
    }

    // Move play down
    // MODIFIES: this
    // EFFECTS: move player down by SPEED
    public void moveDown() {
        y += SPEED;
        handleBoundary();
    }

    // Based from Space Invader
    // Ensures players stays within the boundaries of the game
    // MODIFIES: this
    // EFFECTS: moves player back if it goes beyond boundaries
    private void handleBoundary() {
        if (x < 0) {
            x = 0;
        } else if (x > MyGame.WIDTH) {
            x = MyGame.WIDTH;
        } else if (y < 0) {
            y = 0;
        } else if (y > MyGame.HEIGHT) {
            y = MyGame.HEIGHT;
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

    // EFFECTS: returns this player as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("x", x);
        json.put("y", y);
        return json;
    }
}

