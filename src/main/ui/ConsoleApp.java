package ui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import model.MyGame;
import model.Player;
import model.Projectile;

import java.io.IOException;


/*
 * Represents the console window where the game is played (based from Snake Console)
 */
public class ConsoleApp {
    private MyGame game;
    private Screen screen;
    private WindowBasedTextGUI endGui;

    // Constructs window
    // EFFECTS: creates the screen and game and begins the game cycle
    public void start() throws IOException, InterruptedException {
        screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();
        game = new MyGame();
        game.set();
        beginTicks();
    }



    // Begins the game cycle
    // EFFECTS: ticks every 30 ticks until game ends or window is closed
    private void beginTicks() throws IOException, InterruptedException {
        while (!game.getGameStatus() || endGui.getActiveWindow() != null) {
            tick();
            Thread.sleep(1000L / 30);
        }
    }

    // Moves game forward by a tick
    // MODIFIES: this
    // EFFECTS: update game, clear screen, and re-render screen
    private void tick() throws IOException {
        handleUserInput();
        game.update();
        screen.clear();
        render();
        screen.refresh();
    }

    // Pass user input to game
    // MODIFIES: this
    // EFFECTS: moves player according to input or end game
    private void handleUserInput() throws IOException {
        KeyStroke stroke = screen.pollInput();
        if (stroke == null) {
            return;
        }
        if (stroke.getCharacter() != null && stroke.getCharacter() != 'k') {
            game.movePlayer(stroke.getCharacter());
        } else if (stroke.getCharacter() != null && stroke.getCharacter() == 'k') {
            printScores();
            System.exit(10);
        }
    }

    // Draws game or end screen on the screen
    // MODIFIES: this
    // EFFECTS: draws score, player, projectiles or end screen.
    private void render() {
        if (game.getGameStatus()) {
            if (endGui == null) {
                drawEndScreen();
            }
            return;
        }
        drawScore();
        drawPlayer();
        drawProjectiles();
    }

    // Draws end screen on the screen
    // MODIFIES: this
    // EFFECTS: draws end screen.
    private void drawEndScreen() {
        endGui = new MultiWindowTextGUI(screen);

        MessageDialogBuilder dialogBuilder = new MessageDialogBuilder()
                .setTitle("Game over!")
                .setText("You finished with a score of " + game.getGameScore() + "! " + "High Score: "
                        + game.getHighScore())
                .addButton(MessageDialogButton.Retry);

        MessageDialogButton button = dialogBuilder.build().showDialog(endGui);

        if (button == MessageDialogButton.Retry) {
            game.set();
            endGui = null;
        }
    }

    // Draws score
    // MODIFIES: this
    // EFFECTS: draws score on the screen.
    private void drawScore() {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(TextColor.ANSI.GREEN);
        text.putString(1, 0, "Score: ");

        text = screen.newTextGraphics();
        text.setForegroundColor(TextColor.ANSI.WHITE);
        text.putString(8, 0, String.valueOf(game.getGameScore()));
    }

    // Draws player
    // MODIFIES: this
    // EFFECTS: draws player on the screen.
    @SuppressWarnings({"checkstyle:AvoidEscapedUnicodeCharacters", "checkstyle:SuppressWarnings"})
    private void drawPlayer() {
        Player player = game.getPlayer();

        drawPosition(player.getX(), player.getY(), TextColor.ANSI.GREEN, '\u2588', true);
    }

    // Draws projectiles
    // MODIFIES: this
    // EFFECTS: draws projectiles on the screen.
    @SuppressWarnings({"checkstyle:AvoidEscapedUnicodeCharacters", "checkstyle:SuppressWarnings"})
    private void drawProjectiles() {
        for (Projectile projectile : game.getProjectiles()) {
            drawPosition((int) projectile.getX(), (int) projectile.getY(), TextColor.ANSI.RED, '\u2B24', false);
        }
    }

    // Draws assets
    // MODIFIES: this
    // EFFECTS: draws assets on the screen.
    private void drawPosition(int x, int y, TextColor color, char c, boolean wide) {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(color);
        text.putString(x * 2, y + 1, String.valueOf(c));
        if (wide) {
            text.putString(x * 2 + 1, y + 1, String.valueOf(c));
        }
    }

    // Prints high scores
    // EFFECTS: print out list of high scores
    private void printScores() {
        System.out.println("High Scores:");
        for (String score : game.getHighScores()) {
            System.out.println(score);
        }
    }
}
