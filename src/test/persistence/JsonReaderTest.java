package persistence;


import model.HighScores;
import model.ScoreEntry;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            HighScores hs = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyHighScores() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyHighScores.json");
        try {
            HighScores hs = reader.read();
            assertEquals(0, hs.getGamesPlayed());
            assertEquals(0, hs.getHighScores().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralHighScores() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralHighScores.json");
        try {
            HighScores hs = reader.read();
            assertEquals(2, hs.getGamesPlayed());
            List<ScoreEntry> ScoreEntries = hs.getHighScores();
            assertEquals(2, ScoreEntries.size());
            checkScoreEntry("2023/07/25 11:51:12", 18, ScoreEntries.get(0));
            checkScoreEntry("2023/07/25 11:50:28", 7, ScoreEntries.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}