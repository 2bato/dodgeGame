package persistence;


import model.ScoreEntry;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkScoreEntry(String time, int score, ScoreEntry se) {
        assertEquals(time, se.getTime());
        assertEquals(score, se.getScore());
    }
}
