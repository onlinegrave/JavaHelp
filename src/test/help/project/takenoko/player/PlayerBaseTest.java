package test.help.project.takenoko.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Random;

import com.help.project.takenoko.player.Player;
import com.help.project.takenoko.player.PlayerBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.help.project.takenoko.action.Action;
import com.help.project.takenoko.action.ActionValidator;
import com.help.project.takenoko.action.PossibleActionLister;
import com.help.project.takenoko.game.GameInventory;
import com.help.project.takenoko.game.WeatherDice;
import com.help.project.takenoko.game.board.Board;
import com.help.project.takenoko.game.tile.TileDeck;

class PlayerBaseTest {

    Player player;
    Board board;
    TileDeck deck;

    @BeforeEach
    void setUp() {
        player = new TestPlayer();
        board = new Board();
        deck = new TileDeck(new Random(0));
    }

    @Test
    void credits() {
        player.beginTurn(3);
        assertEquals(3, player.availableActionCredits());

        player.getVisibleInventory().incrementIrrigation();
        var validator =
                new ActionValidator(
                        board,
                        new GameInventory(20, deck, new Random(0), new WeatherDice(new Random(0))),
                        player,
                        WeatherDice.Face.SUN);
        var lister = new PossibleActionLister(board, validator, player.getPrivateInventory());

        player.chooseAction(board, lister);
        assertEquals(2, player.availableActionCredits());

        player.chooseAction(board, lister);
        assertEquals(1, player.availableActionCredits());

        player.chooseAction(board, lister);
        assertEquals(0, player.availableActionCredits());

        // No more credits
        assertThrows(IllegalStateException.class, () -> player.chooseAction(board, lister));
    }

    private static class TestPlayer extends PlayerBase<TestPlayer>
            implements PlayerBase.PlayerBaseInterface {
        @Override
        public Action chooseActionImpl(Board board, PossibleActionLister actionLister) {
            return Action.NONE;
        }

        @Override
        public WeatherDice.Face chooseWeatherImpl(List<WeatherDice.Face> allowedWeathers) {
            return WeatherDice.Face.SUN;
        }
    }
}
