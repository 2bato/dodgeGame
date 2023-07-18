package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Unit tests for the Player class.
 */
class PlayerTest {
    private Player testPlayer;
    private Player testPlayerTopl;
    private Player testPlayerTopr;
    private Player testPlayerBotl;
    private Player testPlayerBotr;


    @BeforeEach
    void runBefore() {
        testPlayer = new Player(5, 5);
        testPlayerTopl = new Player(1 , 1);
        testPlayerTopr = new Player(MyGame.WIDTH - 1 , 1);
        testPlayerBotl = new Player(1 , MyGame.HEIGHT - 1);
        testPlayerBotr = new Player(MyGame.WIDTH - 1 , MyGame.HEIGHT - 1);
    }

    @Test
    void testConstructor() {
        assertEquals(5, testPlayer.getX());
        assertEquals(5, testPlayer.getY());
    }

    @Test
    void testMoveLeft() {
        testPlayer.moveLeft();
        assertEquals(4, testPlayer.getX());
        assertEquals(5, testPlayer.getY());
    }

    @Test
    void testMoveRight() {
        testPlayer.moveRight();
        assertEquals(6, testPlayer.getX());
        assertEquals(5, testPlayer.getY());
    }

    @Test
    void testMoveUp() {
        testPlayer.moveUp();
        assertEquals(5, testPlayer.getX());
        assertEquals(4, testPlayer.getY());
    }

    @Test
    void testMoveDown() {
        testPlayer.moveDown();
        assertEquals(5, testPlayer.getX());
        assertEquals(6, testPlayer.getY());
    }

    @Test
    void testHandleBoundary() {
        testPlayerTopl.moveLeft();
        testPlayerTopl.moveUp();
        assertEquals(0, testPlayerTopl.getY());
        assertEquals(0, testPlayerTopl.getX());
        testPlayerTopl.moveLeft();
        testPlayerTopl.moveUp();
        assertEquals(0, testPlayerTopl.getY());
        assertEquals(0, testPlayerTopl.getX());

        testPlayerTopr.moveRight();
        testPlayerTopr.moveUp();
        assertEquals(0, testPlayerTopr.getY());
        assertEquals(MyGame.WIDTH, testPlayerTopr.getX());
        testPlayerTopr.moveRight();
        testPlayerTopr.moveUp();
        assertEquals(0, testPlayerTopr.getY());
        assertEquals(MyGame.WIDTH, testPlayerTopr.getX());

        testPlayerBotl.moveLeft();
        testPlayerBotl.moveDown();
        assertEquals(MyGame.HEIGHT, testPlayerBotl.getY());
        assertEquals(0, testPlayerBotl.getX());
        testPlayerBotl.moveLeft();
        testPlayerBotl.moveDown();
        assertEquals(MyGame.HEIGHT, testPlayerBotl.getY());
        assertEquals(0, testPlayerBotl.getX());

        testPlayerBotr.moveRight();
        testPlayerBotr.moveDown();
        assertEquals(MyGame.HEIGHT, testPlayerBotr.getY());
        assertEquals(MyGame.WIDTH, testPlayerBotr.getX());
        testPlayerBotr.moveRight();
        testPlayerBotr.moveDown();
        assertEquals(MyGame.HEIGHT, testPlayerBotr.getY());
        assertEquals(MyGame.WIDTH, testPlayerBotr.getX());
    }

    @Test
    void testCheckHit() {
        Projectile p = new Projectile(1,2);
        p.makeDummyProjectile(5,5,0,0);
        assertTrue(testPlayer.checkHit(p));
        p.makeDummyProjectile(10,10,0,0);
        assertFalse(testPlayer.checkHit(p));
        p.makeDummyProjectile(6,6,0,0);
        assertFalse(testPlayer.checkHit(p));
        p.makeDummyProjectile(4,6,0,0);
        assertFalse(testPlayer.checkHit(p));
        p.makeDummyProjectile(4,4,0,0);
        assertTrue(testPlayer.checkHit(p));
    }
}