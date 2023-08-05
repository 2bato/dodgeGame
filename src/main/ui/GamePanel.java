package ui;

import model.MyGame;
import model.Player;
import model.Projectile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Based from Space Invader
/*
 * The panel in which the game is rendered.
 */
public class GamePanel extends JPanel {

    private static final String OVER = "Game Over!";
    private static final String REPLAY = "R to replay";
    private MyGame game;
    private BufferedImage cannonball;
    // "Cannonball" by Flixberry Entertainment licensed CC-BY 4.0: https://opengameart.org/content/cannonball
    private BufferedImage background;
    // "Water" by Aswin Vos licensed CC-BY-SA 3.0: https://opengameart.org/content/water
    private BufferedImage duck;
    // "Rubber Duck" by tgfcoder licensed CC0: https://opengameart.org/content/rubber-duck



    // Constructs a game panel
    // EFFECTS:  sets size and background colour of panel, updates this with the game to be displayed
    public GamePanel(MyGame g) {
        setPreferredSize(new Dimension(MyGame.WIDTH, MyGame.HEIGHT));
        setBackground(Color.white);
        this.game = g;
        try {
            background = ImageIO.read(new File("data/ocean.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Draws the panel
    // EFFECTS: draws g onto panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);

        drawGame(g);

        if (game.getGameStatus()) {
            gameOver(g);
        }
    }

    // Draws the game
    // MODIFIES: g
    // EFFECTS:  draws the game onto g
    private void drawGame(Graphics g) {
        drawPlayer(g);
        drawProjectiles(g);
    }

    // Draw the player
    // MODIFIES: g
    // EFFECTS:  draws the tank onto g
    private void drawPlayer(Graphics g) {
        Player p = game.getPlayer();

        try {
            duck = ImageIO.read(new File("data/RubberDucky.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        g.drawImage(duck, p.getX() - Player.SIZE / 2,
                p.getY() - Player.SIZE / 2, Player.SIZE, Player.SIZE, null);
    }

    // Draws the projectiles
    // MODIFIES: g
    // EFFECTS:  draws the projectiles onto g
    private void drawProjectiles(Graphics g) {
        for (Projectile next : game.getProjectiles()) {
            drawProjectile(g, next);
        }
    }

    // Draws a projectile
    // MODIFIES: g
    // EFFECTS:  draws the Projectile p onto g
    private void drawProjectile(Graphics g, Projectile m) {
        try {
            cannonball = ImageIO.read(new File("data/cannonball.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        g.drawImage(cannonball, (int) (m.getX() - Projectile.SIZE / 2),
                (int) (m.getY() - Projectile.SIZE / 2), Projectile.SIZE, Projectile.SIZE, null);

    }

    // Draws the "game over" message and replay instructions
    // MODIFIES: g
    // EFFECTS:  draws "game over" and replay instructions onto g
    private void gameOver(Graphics g) {
        Color saved = g.getColor();
        g.setColor(new Color(0, 0, 0));
        g.setFont(new Font("Times New Roman", 20, 20));
        FontMetrics fm = g.getFontMetrics();
        centreString(OVER, g, fm, MyGame.HEIGHT / 2);
        centreString(REPLAY, g, fm, MyGame.HEIGHT / 2 + 50);
        g.setColor(saved);
    }

    // Centres a string on the screen
    // MODIFIES: g
    // EFFECTS:  centres the string str horizontally onto g at vertical position yPos
    private void centreString(String str, Graphics g, FontMetrics fm, int ypos) {
        int width = fm.stringWidth(str);
        g.drawString(str, (MyGame.WIDTH - width) / 2, ypos);
    }
}
