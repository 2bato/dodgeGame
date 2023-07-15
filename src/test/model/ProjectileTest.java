package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertTrue(coords[0] == 0 || coords[0] == 800 || coords[1] == 0 || coords[1] == 800);
    }

    @Test
    void testMove() {
        double xinitial = testProjectile.getX();
        double yinitial = testProjectile.getY();

        testProjectile.move();

        assertEquals(xinitial + testProjectile.getDx(), testProjectile.getX(), 1);
        assertEquals(yinitial + testProjectile.getDy(), testProjectile.getY(), 1);
    }
}