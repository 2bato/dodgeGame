package ui;

import model.MyGame;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;

// Based from Space Invader
/*
 * The panel in which the score is rendered.
 */
public class ScorePanel extends JPanel {
    private static final String SCORE_TXT = "Projectiles dodged: ";
    private static final int LBL_WIDTH = 200;
    private static final int LBL_HEIGHT = 30;
    private MyGame game;
    private JLabel projectilesLbl;
    private JLabel highscoreLbl;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/mygame.json";


    // Constructs a score panel
    // EFFECTS: sets the background colour and draws the initial labels and buttons;
    //          updates this with the game whose score is to be displayed;
    public ScorePanel(MyGame g) {
        game = g;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        projectilesLbl = new JLabel(SCORE_TXT + game.getGameScore());
        projectilesLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        JButton saveButton = addButton("Save Game");
        JButton loadButton = addButton("Load Game");
        JButton clearButton = addButton("Clear High Scores");
        JButton sortHiToLoButton = addButton("Descending Order");
        JButton sortLoToHiButton = addButton("Ascending Order");
        highscoreLbl = new JLabel("No High Score Yet");
        highscoreLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT * 10));
        highscoreLbl.setVerticalAlignment(JLabel.TOP);
        add(projectilesLbl);
        add(saveButton);
        add(loadButton);
        add(clearButton);
        add(sortHiToLoButton);
        add(sortLoToHiButton);
        add(highscoreLbl);
        add(Box.createHorizontalStrut(10));
    }

    // Updates the score panel
    // MODIFIES: this
    // EFFECTS:  updates the game score and high scores
    public void update() {
        projectilesLbl.setText(SCORE_TXT + game.getGameScore());

        if (game.getHighScoresString().isEmpty()) {
            highscoreLbl.setText("No High Score Yet");
        } else {
            highscoreLbl.setText("<html> High Scores:<br/>" + highScoresToString() + "<html>");
        }
    }

    // Return high scores as a string
    // EFFECTS: return high scores as a single string with line breaks
    public String highScoresToString() {
        return String.join("<br/>", game.getHighScoresString());
    }

    // Action event handler
    // MODIFIES: game
    // EFFECTS: call functions based on buttons clicked
    public void buttonClicked(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Save Game")) {
            saveGame();
        }
        if (command.equals("Load Game")) {
            loadGame();
        }
        if (command.equals("Clear High Scores")) {
            clearScores();
        }
        if (command.equals("Ascending Order")) {
            sortOrderLoToHi();
        }
        if (command.equals("Descending Order")) {
            sortOrderHiToLo();
        }
    }

    // MODIFIES: json
    // EFFECTS: saves the game to file
    private void saveGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(game);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: game
    // EFFECTS: loads game from file
    private void loadGame() {
        try {
            game.setHighScores(jsonReader.read().getHighScores());
            game.setProjectiles(jsonReader.read().getProjectiles());
            game.setGameScore(jsonReader.read().getGameScore());
            game.setPlayer(jsonReader.read().getPlayer());
            game.setGameOver(jsonReader.read().getGameStatus());
            game.update();
            System.out.println("Loaded game from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    private void clearScores() {
        game.getHighScores().clearHighScores();
    }

    private void sortOrderHiToLo() {
        game.getHighScores().highToLow();
    }

    private void sortOrderLoToHi() {
        game.getHighScores().lowToHigh();
    }

    // EFFECTS: create a button that is not focusable and listens to clicks
    private JButton addButton(String name) {
        JButton button = new JButton(name);
        button.setFocusable(false);
        button.addActionListener(this::buttonClicked);
        return button;
    }
}


