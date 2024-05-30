import Fallin.engine.GameEngine;
import Fallin.engine.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    void testInitialValues() {
        Player player = new Player(9, 0);
        assertEquals(9, player.getX());
        assertEquals(0, player.getY());
        assertEquals(100, player.getLife());
        assertEquals(0, player.getTreasuresCollected());
        assertEquals(0, player.getStepsTaken());
    }

    @Test
    void testMove() {
        Player player = new Player(9, 0);
        player.move(8, 0);
        assertEquals(8, player.getX());
        assertEquals(0, player.getY());
        assertEquals(1, player.getStepsTaken());
    }

    @Test
    void testCollectTreasure() {
        Player player = new Player(9, 0);
        player.collectTreasure();
        assertEquals(1, player.getTreasuresCollected());
    }

    @Test
    void testTakeDamage() {
        Player player = new Player(9, 0);
        player.takeDamage(10);
        assertEquals(90, player.getLife());
    }

    @Test
    void testHeal() {
        Player player = new Player(9, 0);
        player.takeDamage(10);
        player.heal(5);
        assertEquals(95, player.getLife());
    }
}
