package model;

/*
 * Represents the player.
 */
public class Player {
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
        xcoord -= 1;

        handleBoundary();
    }

    public void moveRight() {
        xcoord += 1;

        handleBoundary();
    }

    public void moveUp() {
        ycoord += 1;

        handleBoundary();
    }

    public void moveDown() {
        ycoord -= 1;

        handleBoundary();
    }

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
}

