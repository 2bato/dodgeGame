package persistence;


import model.MyGame;
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
            MyGame g = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMyGame() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMyGame.json");
        try {
            MyGame g = reader.read();
            assertEquals(0, g.getGameScore());
            assertEquals(0, g.getProjectiles().size());
            assertEquals(0,g.getHighScores().getGamesPlayed());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMyGame() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMyGame.json");
        try {
            MyGame g = reader.read();
            assertEquals(5, g.getHighScores().getGamesPlayed());
            List<ScoreEntry> ScoreEntries = g.getHighScores().getScoreEntries();
            assertEquals(5, ScoreEntries.size());
            checkScoreEntry("2023/08/03 03:43:30", 13, ScoreEntries.get(0));
            checkScoreEntry("2023/08/03 03:43:46", 8, ScoreEntries.get(1));
            checkPlayer(460,400, g.getPlayer());
            checkProjectile(0.12353567311725536,-5.99872810998027,
                    397.6922545784321,512.0, g.getProjectiles().get(0));
            checkProjectile(3.4430983748688737,-4.913763687945863,
                    278.10055862719594,573.0, g.getProjectiles().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}