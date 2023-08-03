package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// Based from JsonSerializationDemo
// Represents a reader that reads high scores from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads high scores from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MyGame read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGame(jsonObject);
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
    private void addHighScores(MyGame g, JSONObject jsonObject) {
        HighScores hs = new HighScores();
        addScoreEntries(hs, jsonObject);
        g.setHighScores(hs);
    }

    // MODIFIES: g
    // EFFECTS: parses score entries from JSON object and adds them to high scores
    private void addScoreEntries(HighScores hs, JSONObject jsonObject) {
        JSONObject highscoresObject = jsonObject.getJSONObject("highscores");
        JSONArray jsonArray = highscoresObject.getJSONArray("scoreEntries");

        for (Object json : jsonArray) {
            JSONObject nextSE = (JSONObject) json;
            addScoreEntry(hs, nextSE);
        }
    }

    // MODIFIES: g
    // EFFECTS: parses score entry from JSON object and adds it to high scores
    private void addScoreEntry(HighScores hs, JSONObject jsonObject) {
        int score = jsonObject.getInt("score");
        String time = jsonObject.getString("time");
        ScoreEntry se = new ScoreEntry(score, time);
        hs.addScoreEntry(se);
    }

    // EFFECTS: parses game from JSON object and returns it
    private MyGame parseGame(JSONObject jsonObject) {
        MyGame g = new MyGame();
        addProjectiles(g, jsonObject);
        addHighScores(g, jsonObject);
        addPlayer(g, jsonObject);
        addScore(g, jsonObject);
        addGameStatus(g, jsonObject);
        return g;
    }

    // MODIFIES: g
    // EFFECTS: parses projectiles from JSON object and adds them to game
    private void addProjectiles(MyGame g, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("projectiles");
        List<Projectile> projectiles = new ArrayList<>();
        for (Object json : jsonArray) {
            JSONObject nextSE = (JSONObject) json;
            addProjectile(projectiles, nextSE);
        }
        g.setProjectiles(projectiles);
    }

    // MODIFIES: g
    // EFFECTS: parses projectile from JSON object and adds it to projectiles
    private void addProjectile(List<Projectile> projectiles, JSONObject jsonObject) {
        double dx = jsonObject.getDouble("dx");
        double dy = jsonObject.getDouble("dy");
        double xcoord = jsonObject.getDouble("xcoord");
        double ycoord = jsonObject.getDouble("ycoord");
        Projectile p = new Projectile(0, 0);
        p.makeDummyProjectile(xcoord, ycoord, dx, dy);
        projectiles.add(p);
    }

    // MODIFIES: g
    // EFFECTS: parses player from JSON object and adds it to game
    private void addPlayer(MyGame g, JSONObject jsonObject) {
        JSONObject playerObject = jsonObject.getJSONObject("player");
        int x = playerObject.getInt("x");
        int y = playerObject.getInt("y");
        Player p = new Player(x, y);
        g.setPlayer(p);
    }

    // MODIFIES: g
    // EFFECTS: parses game score from JSON object and adds it to game
    private void addScore(MyGame g, JSONObject jsonObject) {
        int score = jsonObject.getInt("score");
        g.setGameScore(score);
    }

    // MODIFIES: g
    // EFFECTS: parses game status from JSON object and adds it to game
    private void addGameStatus(MyGame g, JSONObject jsonObject) {
        Boolean gameover = jsonObject.getBoolean("gamestatus");
        g.setGameOver(gameover);
    }
}