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
        testPlayer = new Player(50, 50);
        testPlayerTopl = new Player(1 , 1);
        testPlayerTopr = new Player(MyGame.WIDTH - 1 , 1);
        testPlayerBotl = new Player(1 , MyGame.HEIGHT - 1);
        testPlayerBotr = new Player(MyGame.WIDTH - 1 , MyGame.HEIGHT - 1);
    }

    @Test
    void testConstructor() {
        assertEquals(50, testPlayer.getX());
        assertEquals(50, testPlayer.getY());
    }

    @Test
    void testMoveLeft() {
        testPlayer.moveLeft();
        assertEquals(35, testPlayer.getX());
        assertEquals(50, testPlayer.getY());
    }

    @Test
    void testMoveRight() {
        testPlayer.moveRight();
        assertEquals(65, testPlayer.getX());
        assertEquals(50, testPlayer.getY());
    }

    @Test
    void testMoveUp() {
        testPlayer.moveUp();
        assertEquals(50, testPlayer.getX());
        assertEquals(35, testPlayer.getY());
    }

    @Test
    void testMoveDown() {
        testPlayer.moveDown();
        assertEquals(50, testPlayer.getX());
        assertEquals(65, testPlayer.getY());
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
        p.makeDummyProjectile(50,50,0,0);
        assertTrue(testPlayer.checkHit(p));
        p.makeDummyProjectile(21,21,0,0);
        assertTrue(testPlayer.checkHit(p));
        p.makeDummyProjectile(20,20,0,0);
        assertFalse(testPlayer.checkHit(p));
        p.makeDummyProjectile(4,6,0,0);
        assertFalse(testPlayer.checkHit(p));
        p.makeDummyProjectile(51,51,0,0);
        assertTrue(testPlayer.checkHit(p));
    }
}