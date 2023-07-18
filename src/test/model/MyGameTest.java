package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @Test
    void testUpdate() {
        testMyGame.update();
        assertFalse(testMyGame.getGameStatus());
    }

    @Test
    void testGetHighScore() {
        assertEquals("No High Score Yet", testMyGame.getHighScore());
        testMyGame.getHighScores().add("10 2023");
    }

    @Test
    void testCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        assertEquals(formatter.format(now), testMyGame.currentTime());
    }

    @Test
    void testMovePlayer() {
        testMyGame.movePlayer('a');
        assertEquals(MyGame.WIDTH /2 - 1, testMyGame.getPlayer().getX());
        testMyGame.movePlayer('d');
        assertEquals(MyGame.WIDTH /2, testMyGame.getPlayer().getX());
        testMyGame.movePlayer('w');
        assertEquals(MyGame.HEIGHT /2 - 1, testMyGame.getPlayer().getY());
        testMyGame.movePlayer('s');
        assertEquals(MyGame.HEIGHT /2, testMyGame.getPlayer().getY());
        testMyGame.movePlayer('s');
        assertEquals(MyGame.HEIGHT /2 + 1, testMyGame.getPlayer().getY());

    }
}
