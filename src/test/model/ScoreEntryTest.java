package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreEntryTest {
    private ScoreEntry testScoreEntry;

    @BeforeEach
    void runBefore() {
        testScoreEntry = new ScoreEntry(5,"2023");
    }

    @Test
    void testConstructor() {
        assertEquals(5, testScoreEntry.getScore());
        assertEquals("2023", testScoreEntry.getTime());
    }

    @Test
    void testGetScoreEntryString() {
        assertEquals("5 2023",testScoreEntry.getScoreEntryString());
    }
}
