package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class MyGameTest {
    private MyGame testMyGame;

    @BeforeEach
    void runBefore() {
        testMyGame = new MyGame();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testMyGame.getProjectiles().size());
        assertEquals(0, testMyGame.getTopHighScores().size());
        assertEquals(MyGame.WIDTH / 2, testMyGame.getPlayer().getX());
        assertEquals(MyGame.HEIGHT / 2, testMyGame.getPlayer().getY());
        assertFalse(testMyGame.getGameStatus());
        assertEquals(0, testMyGame.getGameScore());
        assertEquals(0, testMyGame.getHighScores().getHighScores().size());
        assertEquals(0, testMyGame.getHighScores().getGamesPlayed());

    }

    @Test
    void testUpdate() {
        testMyGame.update();
        assertFalse(testMyGame.getGameStatus());
        testMyGame.addDummyProjectile(2, 2, -1, -1);
        testMyGame.update();
        assertEquals(1, testMyGame.getProjectiles().get(0).getX());
        assertEquals(1, testMyGame.getProjectiles().get(0).getY());
        assertEquals(0, testMyGame.getGameScore());
        testMyGame.update();
        testMyGame.update();
        assertEquals(1, testMyGame.getGameScore());
        assertEquals(0, testMyGame.getProjectiles().size());
        testMyGame.addDummyProjectile(-1, -1,0 ,0);
        testMyGame.update();
        assertEquals(2, testMyGame.getGameScore());
        assertEquals(0, testMyGame.getProjectiles().size());
        testMyGame.addDummyProjectile(MyGame.WIDTH/2,MyGame.HEIGHT/2,0,0);
        testMyGame.update();
        assertTrue(testMyGame.getGameStatus());
        assertEquals(0, testMyGame.getProjectiles().size());
        assertEquals(1, testMyGame.getTopHighScores().size());
        testMyGame.addDummyProjectile(MyGame.WIDTH/2,MyGame.HEIGHT/2,1,1);
        testMyGame.update();
        assertTrue(testMyGame.getGameStatus());
        assertEquals(1, testMyGame.getProjectiles().size());
        assertEquals(1, testMyGame.getTopHighScores().size());
        assertTrue(testMyGame.getGameStatus());
        testMyGame.set();
        testMyGame.update();
        assertFalse(testMyGame.getGameStatus());
        testMyGame.addDummyProjectile(MyGame.WIDTH/2,MyGame.HEIGHT/2,0,0);
        testMyGame.update();
        assertTrue(testMyGame.getGameStatus());
    }

    @Test
    void testCreateProjectile() {
        testMyGame.createNewProjectile();
        testMyGame.createNewProjectile();
        testMyGame.createNewProjectile();
        testMyGame.createNewProjectile();
        testMyGame.createNewProjectile();
        testMyGame.createNewProjectile();
        testMyGame.createNewProjectile();
        testMyGame.createNewProjectile();
        testMyGame.createNewProjectile();
        testMyGame.createNewProjectile();
        testMyGame.createNewProjectile();
        testMyGame.createNewProjectile();
        testMyGame.createNewProjectile();
        testMyGame.createNewProjectile();
        testMyGame.createNewProjectile();
        testMyGame.createNewProjectile();
        assertTrue(testMyGame.getProjectiles().size() <= 10);
    }

    @Test
    void testAddDummyProjectile() {
        testMyGame.addDummyProjectile(0, 0, 0, 0);
        assertEquals(1, testMyGame.getProjectiles().size());
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
        assertEquals(MyGame.WIDTH / 2 - 1, testMyGame.getPlayer().getX());
        testMyGame.movePlayer('d');
        assertEquals(MyGame.WIDTH / 2, testMyGame.getPlayer().getX());
        testMyGame.movePlayer('w');
        assertEquals(MyGame.HEIGHT / 2 - 1, testMyGame.getPlayer().getY());
        testMyGame.movePlayer('s');
        assertEquals(MyGame.HEIGHT / 2, testMyGame.getPlayer().getY());
        testMyGame.movePlayer('q');
        assertEquals(MyGame.HEIGHT / 2, testMyGame.getPlayer().getY());
    }

    @Test
    void testGetHighScore() {
        HighScores hs = new HighScores();
        ScoreEntry se1 = new ScoreEntry(5, "now");
        ScoreEntry se2 = new ScoreEntry(4, "now");
        ScoreEntry se3 = new ScoreEntry(7, "now");
        assertEquals("No High Score Yet", testMyGame.getHighScore());
        hs.addScoreEntry(se1);
        hs.addScoreEntry(se2);
        hs.addScoreEntry(se3);
        testMyGame.setHighScores(hs);
        assertEquals(3,testMyGame.getHighScores().getHighScores().size());
        assertEquals(se1,testMyGame.getHighScores().getHighScores().get(0));
        assertEquals("7 now",testMyGame.getTopHighScores().get(0));
    }
}
