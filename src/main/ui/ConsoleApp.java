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

    private void tick() throws IOException {
        handleUserInput();
        game.update();
        screen.clear();
        render();
        screen.refresh();
    }

    private void handleUserInput() throws IOException {
        KeyStroke stroke = screen.pollInput();

        if (stroke == null) {
            return;
        }
        if (stroke.getCharacter() != null) {
            game.movePlayer(stroke.getCharacter());
        }
    }

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

    private void drawEndScreen() {
        endGui = new MultiWindowTextGUI(screen);

        MessageDialogBuilder dialogBuilder = new MessageDialogBuilder()
                .setTitle("Game over!")
                .setText("You finished with a score of " + game.getGameScore() + "! " + "High Score: "
                        + game.getHighScore())
                .addButton(MessageDialogButton.Close);

        MessageDialogButton button = dialogBuilder.build().showDialog(endGui);

        if (button == MessageDialogButton.Close) {
            game.set();
            endGui = null;
        }
    }

    private void drawScore() {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(TextColor.ANSI.GREEN);
        text.putString(1, 0, "Score: ");

        text = screen.newTextGraphics();
        text.setForegroundColor(TextColor.ANSI.WHITE);
        text.putString(8, 0, String.valueOf(game.getGameScore()));
    }

    @SuppressWarnings({"checkstyle:AvoidEscapedUnicodeCharacters", "checkstyle:SuppressWarnings"})
    private void drawPlayer() {
        Player player = game.getPlayer();

        drawPosition(player.getX(), player.getY(), TextColor.ANSI.GREEN, '\u2588', true);
    }

    @SuppressWarnings({"checkstyle:AvoidEscapedUnicodeCharacters", "checkstyle:SuppressWarnings"})
    private void drawProjectiles() {
        for (Projectile projectile : game.getProjectiles()) {
            drawPosition((int) projectile.getX(), (int) projectile.getY(), TextColor.ANSI.RED, '\u2B24', false);
        }
    }


    private void drawPosition(int x, int y, TextColor color, char c, boolean wide) {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(color);
        text.putString(x * 2, y + 1, String.valueOf(c));

        if (wide) {
            text.putString(x * 2 + 1, y + 1, String.valueOf(c));
        }
    }
}
