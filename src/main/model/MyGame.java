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
    public static final int WIDTH = 39;
    public static final int HEIGHT = 22;
    public static final Random RND = new Random();

    private HighScores highScores;
    private final List<Projectile> projectiles;
    private Player player;
    private boolean isGameOver;
    private int gameScore;

    // Constructs a new game
    // EFFECTS:  create empty list of projectiles and empty list of high scores, centres player on screen
    public MyGame() {
        this.projectiles = new ArrayList<>();
        this.highScores = new HighScores();
        set();
    }

    public HighScores getHighScores() {
        return highScores;
    }

    public String getHighScore() {
        return highScores.getHighScore();
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

    public void setHighScores(HighScores hs) {
        this.highScores = hs;
    }

    // EFFECTS: return high scores as a list of string
    public List<String> getTopHighScores() {
        List<String> topHighScores = new ArrayList<>();
        for (ScoreEntry scoreEntry : highScores.getTopHighScores()) {
            topHighScores.add(scoreEntry.getScoreEntryString());
        }
        return topHighScores;
    }

    // Sets / resets the game
    // MODIFIES: this
    // EFFECTS:  clears list of projectiles, initializes player, game status, and score
    public void set() {
        projectiles.clear();
        player = new Player(WIDTH / 2, HEIGHT / 2);
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
    // MODIFIES: this
    // EFFECTS: moves the projectiles, removes ones off-screen, update score
    private void updateProjectiles() {
        for (Projectile projectile : projectiles) {
            projectile.move();
            if (projectile.handleBoundary()) {
                projectiles.remove(projectile);
                gameScore++;
                return;
            }
        }
    }

    // Creates a new projectile (based from Space Invader)
    // MODIFIES: this
    // EFFECTS: adds a new projectile to the list
    public void createNewProjectile() {
        if (RND.nextInt(20) < 1) {
            Projectile projectile = new Projectile(player.getX(), player.getY());
            projectiles.add(projectile);
        }
    }

    // Manually add a projectile for testing
    // MODIFIES: this
    // EFFECTS: adds new projectile to projectiles
    public void addDummyProjectile(int x, int y, int dx, int dy) {
        Projectile projectile = new Projectile(0, 0);
        projectile.makeDummyProjectile(x, y, dx, dy);
        projectiles.add(projectile);
    }


    // Checks for collisions between player and projectile
    // MODIFIES: this
    // EFFECTS:  ends game and add score to high scores if collision occurs
    private void checkGameOver() {
        for (Projectile projectile : projectiles) {
            if (player.checkHit(projectile)) {
                isGameOver = true;
                projectiles.clear();
                highScores.addScore(gameScore, currentTime());
                return;
            }
        }
    }

    // Return current date and time
    // EFFECTS: return current date and time as string
    String currentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return formatter.format(now);
    }

    // Responds to inputs
    // MODIFIES: this
    // EFFECTS:  moves player based on input
    public void movePlayer(char character) {
        if (character == 'a') {
            player.moveLeft();
        } else if (character == 'd') {
            player.moveRight();
        } else if (character == 'w') {
            player.moveUp();
        } else if (character == 's') {
            player.moveDown();
        }
    }
}
