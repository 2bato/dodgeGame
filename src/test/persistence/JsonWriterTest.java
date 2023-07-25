package persistence;

import org.junit.jupiter.api.Test;

import model.HighScores;
import model.ScoreEntry;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            HighScores hs = new HighScores();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyHighScores() {
        try {
            HighScores hs = new HighScores();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyHighScores.json");
            writer.open();
            writer.write(hs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyHighScores.json");
            hs = reader.read();
            assertEquals(0, hs.getGamesPlayed());
            assertEquals(0, hs.getHighScores().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralHighScores() {
        try {
            HighScores hs = new HighScores();
            hs.addScoreEntry(new ScoreEntry(5, "today"));
            hs.addScoreEntry(new ScoreEntry(4, "tomorrow"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralHighScores.json");
            writer.open();
            writer.write(hs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralHighScores.json");
            hs = reader.read();
            assertEquals(2, hs.getGamesPlayed());
            List<ScoreEntry> ScoreEntries = hs.getHighScores();
            assertEquals(2, ScoreEntries.size());
            checkScoreEntry("today", 5, ScoreEntries.get(0));
            checkScoreEntry("tomorrow", 4, ScoreEntries.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
