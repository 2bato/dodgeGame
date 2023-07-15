package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Unit tests for the Player class.
 */
class PlayerTest {
    private Player testPlayer;


    @BeforeEach
    void runBefore() {
        testPlayer = new Player(5, 5);
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
        assertEquals(6, testPlayer.getY());
    }

    @Test
    void testMoveDown() {
        testPlayer.moveDown();
        assertEquals(5, testPlayer.getX());
        assertEquals(4, testPlayer.getY());
    }
}