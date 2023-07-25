package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
 * Represents an arbitrary number of ScoreEntry
 */
public class HighScores implements Writable {
    private final List<ScoreEntry> scoreEntries;
    private int gamesPlayed;

    // Constructs a new game
    // EFFECTS:  create empty list of ScoreEntry and games played set to 0
    public HighScores() {
        this.scoreEntries = new ArrayList<>();
        this.gamesPlayed = 0;
    }

    public List<ScoreEntry> getHighScores() {
        return scoreEntries;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void addScoreEntry(ScoreEntry se) {
        scoreEntries.add(se);
        gamesPlayed = scoreEntries.size();
    }

    // Create and add new ScoreEntry to HighScores
    // MODIFIES: this
    // EFFECTS: create new ScoreEntry and add to HighScores and add 1 to games played
    public void addScore(int score, String time) {
        ScoreEntry scoreEntry = new ScoreEntry(score, time);
        scoreEntries.add(scoreEntry);
        gamesPlayed = scoreEntries.size();
    }

    // Sort HighScores by Score
    // EFFECTS: return List of ScoreEntry sorted in descending order by score
    public List<ScoreEntry> getTopHighScores() {
        scoreEntries.sort(Comparator.comparingInt(ScoreEntry::getScore).reversed());
        return new ArrayList<>(scoreEntries);
    }

    // Retrieves the highest score entry so far
    // EFFECTS: return the highest score entry in high scores as string
    public String getHighScore() {
        if (getGamesPlayed() == 0) {
            return "No High Score Yet";
        } else {
            return getTopHighScores().get(0).getScoreEntryString();
        }
    }

    // EFFECTS: returns this high scores as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("gamePlayed", gamesPlayed);
        json.put("scoreEntries", scoreEntriesToJson());
        return json;
    }

    // EFFECTS: returns score entries in this high scores as a JSON array
    private JSONArray scoreEntriesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (ScoreEntry s : scoreEntries) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }
}
