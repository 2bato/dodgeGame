package model;

import java.util.*;

/*
 * Represents an arbitrary number of ScoreEntry
 */
public class HighScores {
    private List<ScoreEntry> highscores;
    private int gamesplayed;

    // Constructs a new game
    // EFFECTS:  create empty list of ScoreEntry and games played set to 0
    public HighScores() {
        this.highscores = new ArrayList<>();
        this.gamesplayed = 0;
    }

    public List<ScoreEntry> getHighScores() {
        return highscores;
    }

    public int getGamesPlayed() {
        return gamesplayed;
    }

    // Add new ScoreEntry to HighScores
    // MODIFIES: this
    // EFFECTS: create new ScoreEntry and add to HighScores and add 1 to games played
    public void addScore(int score, String time) {
        ScoreEntry scoreEntry = new ScoreEntry(score, time);
        highscores.add(scoreEntry);
        gamesplayed += 1;
    }

    // Sort HighScores by Score
    // EFFECTS: return List of Strings of ScoreEntry sorted in descending order by score
    public List<String> getTopHighScores() {
        List<String> topHighScores = new ArrayList<>();
        highscores.sort(Comparator.comparingInt(ScoreEntry::getScore).reversed());
        for (ScoreEntry scoreEntry : highscores) {
            topHighScores.add(scoreEntry.getScoreEntryString());
        }
        return topHighScores;
    }

    // Retrieves the highest score entry so far
    // EFFECTS: return the highest score entry in high scores as string
    public String getHighScore() {
        if (getGamesPlayed() == 0) {
            return "No High Score Yet";
        } else {
            return getTopHighScores().get(0);
        }
    }
}
