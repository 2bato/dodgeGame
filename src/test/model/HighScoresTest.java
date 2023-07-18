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
        assertEquals(0, testHighScores.getHighScores().size());
        assertEquals(0, testHighScores.getGamesPlayed());
    }

    @Test
    void testGetTopHighScores() {
        testHighScores.addScore(5, "1");
        testHighScores.addScore(3, "2");
        testHighScores.addScore(7, "3");
        testHighScores.addScore(2, "4");
        testHighScores.addScore(10, "6");
        testHighScores.addScore(1, "5");
        assertEquals("10 6",testHighScores.getTopHighScores().get(0));
        assertEquals("7 3",testHighScores.getTopHighScores().get(1));
    }

    @Test
    void testGetHighScore() {
        assertEquals("No High Score Yet",testHighScores.getHighScore());
        testHighScores.addScore(5, "1");
        assertEquals("5 1",testHighScores.getHighScore());
        testHighScores.addScore(20, "1");
        assertEquals("20 1",testHighScores.getHighScore());
        testHighScores.addScore(18, "1");
        assertEquals("20 1",testHighScores.getHighScore());
    }

    @Test
    void testAddScore() {
        testHighScores.addScore(5, "1");
        assertEquals(testHighScores.getHighScores().get(0),testHighScores.getHighScores().get(0));
    }

}
