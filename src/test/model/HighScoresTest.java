package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HighScoresTest {

    private HighScores testHighScores;

    @BeforeEach
    void runBefore() {
        testHighScores = new HighScores();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testHighScores.getScoreEntries().size());
        assertEquals(0, testHighScores.getGamesPlayed());
    }

    @Test
    void testGetScoreEntries() {
        testHighScores.addScore(5, "1");
        testHighScores.addScore(3, "2");
        testHighScores.addScore(7, "3");
        testHighScores.addScore(2, "4");
        testHighScores.addScore(10, "6");
        testHighScores.addScore(1, "5");
        assertEquals("5 1",testHighScores.getScoreEntries().get(0).getScoreEntryString());
        assertEquals("3 2",testHighScores.getScoreEntries().get(1).getScoreEntryString());
    }

    @Test
    void testAddScore() {
        testHighScores.addScore(5, "1");
        assertEquals(5,testHighScores.getScoreEntries().get(0).getScore());
        assertEquals("1",testHighScores.getScoreEntries().get(0).getTime());
    }

}
