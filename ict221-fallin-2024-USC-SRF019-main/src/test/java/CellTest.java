import Fallin.engine.Cell;
import Fallin.engine.GameEngine;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CellTest {
    @Test
    void testCellCreation() {
        Cell cell = new Cell(1, 1, "Treasure");
        assertEquals(1, cell.getX());
        assertEquals(1, cell.getY());
        assertEquals("Treasure", cell.getType());
    }

    @Test
    void testSetType() {
        Cell cell = new Cell(1, 1, "Empty");
        cell.setType("Trap");
        assertEquals("Trap", cell.getType());
    }
}
