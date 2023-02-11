package test.help.project.takenoko.player;

import static org.junit.jupiter.api.Assertions.*;

import com.help.project.takenoko.player.InventoryException;
import com.help.project.takenoko.player.PrivateInventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.help.project.takenoko.game.objective.BambooSizeObjective;
import com.help.project.takenoko.game.tile.BambooSizeException;
import com.help.project.takenoko.game.tile.Color;

class PrivateInventoryTest {

    PrivateInventory privateInventory;

    @BeforeEach
    void setUp() {
        privateInventory = new PrivateInventory();
    }

    @Test
    void objective() throws BambooSizeException, InventoryException {
        var objective = new BambooSizeObjective(1, 2, Color.PINK, 1);
        for (int i = 0; i < 5; i++) {
            assertTrue(privateInventory.canDrawObjective());
            privateInventory.addObjective(objective);
        }
        assertFalse(privateInventory.canDrawObjective());
        assertThrows(InventoryException.class, () -> privateInventory.addObjective(objective));
    }
}
