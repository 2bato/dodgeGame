package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * Represents an arbitrary number of ScoreEntry
 */
public class HighScores implements Writable {
    private List<ScoreEntry> scoreEntries;
    private int gamesPlayed;

    // Constructs a new game
    // EFFECTS:  create empty list of ScoreEntry and games played set to 0
    public HighScores() {
        this.scoreEntries = new ArrayList<>();
        this.gamesPlayed = 0;
    }

    public List<ScoreEntry> getScoreEntries() {
        return scoreEntries;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void clearHighScores() {
        scoreEntries = Collections.emptyList();
        gamesPlayed = 0;
    }

    // MODIFIES: this
    // EFFECTS: add given score entries and add 1 games played
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

    public void highToLow() {
        scoreEntries.sort(Comparator.comparingInt(ScoreEntry::getScore).reversed());
    }

    public void lowToHigh() {
        scoreEntries.sort(Comparator.comparingInt(ScoreEntry::getScore));
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
