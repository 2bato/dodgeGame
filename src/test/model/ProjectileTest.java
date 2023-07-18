package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Unit tests for the Projectile class.
 */
class ProjectileTest {
    private Projectile testProjectile;


    @BeforeEach
    void runBefore() {
        testProjectile = new Projectile(5, 5);
    }

    @Test
    void testRandomCoords() {
        double[] coords = testProjectile.randomCoords();
        assertTrue(coords[0] == 0 || coords[0] == MyGame.WIDTH || coords[1] == 0 || coords[1] == MyGame.HEIGHT);
    }

    @Test
    void testMove() {
        double xinitial = testProjectile.getX();
        double yinitial = testProjectile.getY();

        testProjectile.move();

        assertEquals(xinitial + testProjectile.getDx(), testProjectile.getX(), 1);
        assertEquals(yinitial + testProjectile.getDy(), testProjectile.getY(), 1);
    }

    @Test
    void testHandleBoundary() {
        testProjectile.makeDummyProjectile(0, 0,-1,-1);
        testProjectile.move();
        assertTrue(testProjectile.handleBoundary());
        testProjectile.makeDummyProjectile(5,5,0,0);
        assertFalse(testProjectile.handleBoundary());
        testProjectile.makeDummyProjectile(MyGame.WIDTH, 0,1,0);
        testProjectile.move();
        assertTrue(testProjectile.handleBoundary());
        testProjectile.makeDummyProjectile(0, MyGame.HEIGHT,-1,1);
        testProjectile.move();
        assertTrue(testProjectile.handleBoundary());
        testProjectile.makeDummyProjectile(MyGame.WIDTH, MyGame.HEIGHT,1,1);
        testProjectile.move();
        assertTrue(testProjectile.handleBoundary());
        testProjectile.makeDummyProjectile(5, -5,1,1);
        assertTrue(testProjectile.handleBoundary());
        testProjectile.makeDummyProjectile(-5, 5,1,1);
        assertTrue(testProjectile.handleBoundary());
        testProjectile.makeDummyProjectile(1000, 5,1,1);
        assertTrue(testProjectile.handleBoundary());
        testProjectile.makeDummyProjectile(5, 1000,1,1);
        assertTrue(testProjectile.handleBoundary());
    }
}