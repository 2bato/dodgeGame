package persistence;

import org.junit.jupiter.api.Test;

import model.MyGame;
import model.ScoreEntry;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            MyGame g = new MyGame();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyMyGame() {
        try {
            MyGame g = new MyGame();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMyGame.json");
            writer.open();
            writer.write(g);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMyGame.json");
            g = reader.read();
            assertEquals(0, g.getGameScore());
            assertEquals(0, g.getProjectiles().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralMyGame() {
        try {
            MyGame g = new MyGame();
            g.addDummyProjectile(5, 5, 2, 1);
            g.addDummyProjectile(21, 21, 3, 1);
            g.movePlayer('s');
            g.getHighScores().addScore(5, "now");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMyGame.json");
            writer.open();
            writer.write(g);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMyGame.json");
            g = reader.read();
            assertEquals(1, g.getHighScores().getGamesPlayed());
            List<ScoreEntry> ScoreEntries = g.getHighScores().getScoreEntries();
            assertEquals(1, ScoreEntries.size());
            checkScoreEntry("now", 5, ScoreEntries.get(0));
            assertEquals(2, g.getProjectiles().size());
            checkProjectile(2, 1, 5, 5, g.getProjectiles().get(0));
            checkProjectile(3, 1, 21, 21, g.getProjectiles().get(1));
            checkPlayer(400, 415, g.getPlayer());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
