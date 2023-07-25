package persistence;

import model.HighScores;
import model.ScoreEntry;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads high scores from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads high scores from file and returns it;
    // throws IOException if an error occurs reading data from file
    public HighScores read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseHighScores(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses high scores from JSON object and returns it
    private HighScores parseHighScores(JSONObject jsonObject) {
        HighScores hs = new HighScores();
        addScoreEntries(hs, jsonObject);
        return hs;
    }

    // MODIFIES: hs
    // EFFECTS: parses score entries from JSON object and adds them to high scores
    private void addScoreEntries(HighScores hs, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("scoreEntries");
        for (Object json : jsonArray) {
            JSONObject nextSE = (JSONObject) json;
            addScoreEntry(hs, nextSE);
        }
    }

    // MODIFIES: hs
    // EFFECTS: parses score entry from JSON object and adds it to high scores
    private void addScoreEntry(HighScores hs, JSONObject jsonObject) {
        int score = jsonObject.getInt("score");
        String time = jsonObject.getString("time");
        ScoreEntry se = new ScoreEntry(score, time);
        hs.addScoreEntry(se);
    }
}