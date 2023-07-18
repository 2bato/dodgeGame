package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MyGameTest {
    private MyGame testMyGame;

    @BeforeEach
    void runBefore() {
        testMyGame = new MyGame();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testMyGame.getProjectiles().size());
        assertEquals(0, testMyGame.getHighScores().size());
        assertEquals(MyGame.WIDTH / 2, testMyGame.getPlayer().getX());
        assertEquals(MyGame.HEIGHT / 2, testMyGame.getPlayer().getY());
        assertFalse(testMyGame.getGameStatus());
        assertEquals(0, testMyGame.getGameScore());
    }
}
