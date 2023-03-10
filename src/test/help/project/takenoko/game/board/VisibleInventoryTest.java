package test.help.project.takenoko.game.board;

import static org.junit.jupiter.api.Assertions.*;

import com.help.project.takenoko.game.board.VisibleInventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.help.project.takenoko.game.tile.Color;
import com.help.project.takenoko.game.tile.PowerUp;
import com.help.project.takenoko.player.InventoryException;

class VisibleInventoryTest {

    VisibleInventory visibleInventory;

    @BeforeEach
    void setUp() {
        visibleInventory = new VisibleInventory();
    }

    @Test
    void incrementBamboo() {
        assertEquals(0, visibleInventory.getBamboo(Color.GREEN));
        visibleInventory.incrementBamboo(Color.GREEN);
        assertEquals(1, visibleInventory.getBamboo(Color.GREEN));
    }

    @Test
    void useBamboo() throws InventoryException {
        visibleInventory.incrementBamboo(Color.PINK);
        visibleInventory.incrementBamboo(Color.PINK);
        assertEquals(2, visibleInventory.getBamboo(Color.PINK));
        visibleInventory.useBamboo(Color.PINK, 2);
        assertEquals(0, visibleInventory.getBamboo(Color.PINK));
        assertThrows(InventoryException.class, () -> visibleInventory.useBamboo(Color.PINK, 1));
    }

    @Test
    void hasIrrigation() {
        assertFalse(visibleInventory.hasIrrigation());
        visibleInventory.incrementIrrigation();
        assertTrue(visibleInventory.hasIrrigation());
    }

    @Test
    void decrementIrrigation() throws InventoryException {
        visibleInventory.incrementIrrigation();
        assertTrue(visibleInventory.hasIrrigation());
        visibleInventory.decrementIrrigation();
        assertFalse(visibleInventory.hasIrrigation());
        assertThrows(InventoryException.class, () -> visibleInventory.decrementIrrigation());
    }

    @Test
    void hasPowerUp() {
        assertFalse(visibleInventory.hasPowerUp(PowerUp.WATERSHED));
        visibleInventory.incrementPowerUp(PowerUp.WATERSHED);
        assertTrue(visibleInventory.hasPowerUp(PowerUp.WATERSHED));
    }

    @Test
    void incrementPowerUp() {
        assertFalse(visibleInventory.hasPowerUp(PowerUp.WATERSHED));
        visibleInventory.incrementPowerUp(PowerUp.WATERSHED);
        assertTrue(visibleInventory.hasPowerUp(PowerUp.WATERSHED));
    }

    @Test
    void decrementPowerUp() throws InventoryException {
        visibleInventory.incrementPowerUp(PowerUp.WATERSHED);
        assertTrue(visibleInventory.hasPowerUp(PowerUp.WATERSHED));
        visibleInventory.decrementPowerUp(PowerUp.WATERSHED);
        assertFalse(visibleInventory.hasPowerUp(PowerUp.WATERSHED));
        assertThrows(
                InventoryException.class,
                () -> visibleInventory.decrementPowerUp(PowerUp.WATERSHED));
    }
}
