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
            g.addDummyProjectile(1,1,1,1);
            g.addDummyProjectile(1,1,1,1);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMyGame.json");
            writer.open();
            writer.write(g);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMyGame.json");
            g = reader.read();
            assertEquals(2, g.getHighScores().getGamesPlayed());
            List<ScoreEntry> ScoreEntries = g.getHighScores().getScoreEntries();
            assertEquals(2, ScoreEntries.size());
            checkScoreEntry("today", 5, ScoreEntries.get(0));
            checkScoreEntry("tomorrow", 4, ScoreEntries.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
