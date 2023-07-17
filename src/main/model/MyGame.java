package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * Represents a new game. (based from Space Invader)
 */
public class MyGame {
    public static final int WIDTH = 80;
    public static final int HEIGHT = 80;
    public static final Random RND = new Random();

    private HighScores highScores;
    private List<Projectile> projectiles;
    private Player player;
    private boolean isGameOver;
    private int gameScore;

    // Constructs a new game
    // EFFECTS:  create empty list of projectiles, centres player on screen
    public MyGame() {
        projectiles = new ArrayList<Projectile>();
        highScores = new HighScores();
        set();
    }

    public List<String> getHighScores() {
        return highScores.getTopHighScores();
    }

    public String getHighScore() {
        if (highScores.getTopHighScores().isEmpty()) {
            return "No High Score Yet";
        } else {
            return highScores.getTopHighScores().get(0);
        }
    }

    public boolean getGameStatus() {
        return isGameOver;
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    public Player getPlayer() {
        return player;
    }

    public int getGameScore() {
        return gameScore;
    }


    // Sets / resets the game
    // MODIFIES: this
    // EFFECTS:  clears list of projectiles, initializes player, game status, and score
    public void set() {
        projectiles.clear();
        player = new Player(5, 5);
        isGameOver = false;
        gameScore = 0;
    }

    // Updates the game on clock tick
    // MODIFIES: this
    // EFFECTS:  updates player and projectiles
    public void update() {
        updateProjectiles();
        createNewProjectile();
        checkGameOver();
    }

    // Updates projectiles
    // MODIFIES: This
    // EFFECTS: Moves the projectiles, removes ones off-screen, update score
    private void updateProjectiles() {
        List<Projectile> projectilesOut = new ArrayList<>();

        for (Projectile next : projectiles) {
            next.move();
            if (next.getY() < 0 || next.getY() > HEIGHT
                    || next.getX() < 0 || next.getX() > WIDTH) {
                projectilesOut.add(next);
                gameScore++;
            }
        }
        projectiles.removeAll(projectilesOut);
    }

    // Creates a new projectile
    // MODIFIES: This
    // EFFECTS: Adds a new projectile to the list
    public void createNewProjectile() {
        if (RND.nextInt(80) < 1) {
            Projectile projectile = new Projectile(player.getX(), player.getY());
            projectiles.add(projectile);
        }
    }

    // Checks for collisions between player and projectile
    // modifies: this
    // effects:  ends game if collision occurs
    private void checkGameOver() {
        for (Projectile target : projectiles) {
            if (player.checkHit(target)) {
                isGameOver = true;
            }
        }

        if (isGameOver) {
            projectiles.clear();
            highScores.addScore(gameScore, currentTime());
        }
    }

    private String currentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return formatter.format(now);
    }

    // Responds to keyboard inputs
    // MODIFIES: this
    // EFFECTS:  moves player
    public void movePlayer(char character) {
        if (character == 'a') {
            player.moveLeft();
        } else if (character == 'd') {
            player.moveRight();
        } else if (character == 'w') {
            player.moveUp();
        } else if (character == 's') {
            player.moveDown();
        } else if (character == 'r' && getGameStatus()) {
            set();
        }
    }
}
