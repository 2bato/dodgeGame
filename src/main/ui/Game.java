package ui;

import model.Event;
import model.EventLog;
import model.MyGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static java.awt.event.KeyEvent.VK_R;

// based from space invaders
/*
 * Represents the main window in which the game is played
 */
public class Game extends JFrame {

    private static final int INTERVAL = 10;
    private MyGame game;
    private GamePanel gp;
    private ScorePanel sp;

    // Constructs main window
    // EFFECTS: sets up window in which the game will be played
    public Game() {
        super("Save the Ducky");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        game = new MyGame();
        gp = new GamePanel(game);
        sp = new ScorePanel(game);
        add(gp);
        add(sp, BorderLayout.WEST);
        addKeyListener(new KeyHandler());
        addWindowListener(new WindowHandler());
        pack();
        centreOnScreen();
        setVisible(true);
        addTimer();
    }

    // Set up timer
    // EFFECTS:  initializes a timer that updates game each INTERVAL milliseconds
    private void addTimer() {
        Timer t = new Timer(INTERVAL, ae -> {
            game.update();
            gp.repaint();
            sp.update();
        });

        t.start();
    }

    // Centres frame on desktop
    // MODIFIES: this
    // EFFECTS:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    // A key handler to respond to key events
    // MODIFIES: this
    // EFFECTS: pass key event to move player or restart game
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == VK_R && game.getGameStatus()) {
                game.set();
            }
            game.movePlayer(e.getKeyChar());
        }
    }

    private class WindowHandler extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            EventLog el = EventLog.getInstance();
            for (Event next : el) {
                System.out.println(next.toString());
            }
        }
    }
}

