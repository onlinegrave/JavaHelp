package test.help.project.takenoko.player.bot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Random;

import com.help.project.takenoko.player.bot.RandomBot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import com.help.project.takenoko.action.Action;
import com.help.project.takenoko.action.PossibleActionLister;
import com.help.project.takenoko.game.board.Board;
import com.help.project.takenoko.game.objective.BambooSizeObjective;
import com.help.project.takenoko.game.objective.Objective;
import com.help.project.takenoko.game.tile.TileDeck;
import com.help.project.takenoko.player.InventoryException;
import com.help.project.takenoko.utils.Coord;

class RandomBotTest {
    Random randomSource;
    @Mock PossibleActionLister actionLister = mock(PossibleActionLister.class);

    @BeforeEach
    void setUp() {
        // Fixed seed
        randomSource = new Random(0);
    }

    @Test
    void chooseActions() {
        Board board = new Board();
        RandomBot bot = new RandomBot(randomSource, "edgar");

        bot.beginTurn(1);

        var expectedAction =
                new Action.PlaceTile(new Coord(-1, 0), TileDeck.DEFAULT_DRAW_PREDICATE);
        when(actionLister.getPossibleActions()).thenReturn(List.of(expectedAction));

        Action chosenAction = bot.chooseAction(board, actionLister);
        assertEquals(expectedAction, chosenAction);
    }

    @Test
    void unveilsObjectiveASAP() throws InventoryException {
        Board board = new Board();
        RandomBot bot = new RandomBot(randomSource, "edgar");

        var objMock = mock(BambooSizeObjective.class);
        when(objMock.status()).thenReturn(new Objective.Status(1, 1));

        bot.getPrivateInventory().addObjective(objMock);
        var possibleAction =
                new Action.PlaceTile(new Coord(-1, 0), TileDeck.DEFAULT_DRAW_PREDICATE);
        var expectedAction = new Action.UnveilObjective(objMock);
        when(actionLister.getPossibleActions()).thenReturn(List.of(possibleAction, expectedAction));

        bot.beginTurn(1);
        Action chosenAction = bot.chooseAction(board, actionLister);

        assertEquals(expectedAction, chosenAction);
    }
}
