package persistence;


import model.Player;
import model.Projectile;
import model.ScoreEntry;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkScoreEntry(String time, int score, ScoreEntry se) {
        assertEquals(time, se.getTime());
        assertEquals(score, se.getScore());
    }

    protected void checkPlayer(int x, int y, Player p) {
        assertEquals(x, p.getX());
        assertEquals(y, p.getY());
    }

    protected void checkProjectile(double dx, double dy, double x, double y, Projectile p) {
        assertEquals(dx, p.getDx());
        assertEquals(dy, p.getDy());
        assertEquals(x, p.getX());
        assertEquals(y, p.getY());

    }
}
