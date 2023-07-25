package model;

import org.json.JSONObject;
import persistence.Writable;

/*
 * Represents a single score entry with score and time
 */
public class ScoreEntry implements Writable {
    private final int score;
    private final String time;

    // Constructor a score entry
    // EFFECTS: create score entry with given time and score
    public ScoreEntry(int score, String time) {
        this.time = time;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getTime() {
        return time;
    }

    // Return score entry as string
    // EFFECTS: return given score entry as "score time"
    public String getScoreEntryString() {
        return score + " " + time;
    }

    // EFFECTS: returns this score entry as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("score", score);
        json.put("time", time);
        return json;
    }
}
