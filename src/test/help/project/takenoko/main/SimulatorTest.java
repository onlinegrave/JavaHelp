package test.help.project.takenoko.main;

import static java.util.logging.Level.OFF;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import com.help.project.takenoko.main.Simulator;
import com.help.project.takenoko.player.PlayerType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class SimulatorTest {

    @Test
//    @Disabled("This test is not deterministic") // FIXME: make it deterministic
    void simulator() {
        var logger = Logger.getGlobal();
        logger.setLevel(OFF);
        var sim =
                new Simulator(20, List.of(PlayerType.RANDOM, PlayerType.SABOTEUR), logger, new Random(0));
        var res = sim.simulate();
        assertEquals(20, res.nbGames());
        assertEquals(2, res.players().size());
        assertEquals(17, res.getNumWins().get("Mireille"));
        assertEquals(3, res.getNumWins().get("Philippe"));
    }
}
