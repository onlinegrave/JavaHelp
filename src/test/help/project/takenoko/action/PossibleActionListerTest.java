package test.help.project.takenoko.action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Random;

import com.help.project.takenoko.action.Action;
import com.help.project.takenoko.action.ActionValidator;
import com.help.project.takenoko.action.PossibleActionLister;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.help.project.takenoko.game.GameInventory;
import com.help.project.takenoko.game.WeatherDice;
import com.help.project.takenoko.game.board.Board;
import com.help.project.takenoko.game.board.BoardException;
import com.help.project.takenoko.game.board.MovablePiece;
import com.help.project.takenoko.game.objective.Objective;
import com.help.project.takenoko.game.tile.*;
import com.help.project.takenoko.player.Player;
import com.help.project.takenoko.player.bot.RandomBot;
import com.help.project.takenoko.utils.Coord;

class PossibleActionListerTest {
    ActionValidator validator;
    Player player;
    Board board;
    TileDeck deck;

    @BeforeEach
    void setUp() {
        board = new Board();
        deck = new TileDeck(new Random(0));
        player = new RandomBot(new Random(0), "RandomBot");
        player.beginTurn(2);
        resetValidator(WeatherDice.Face.SUN);
    }

    void resetValidator(WeatherDice.Face weather) {
        WeatherDice dice = mock(WeatherDice.class);
        when(dice.throwDice()).thenReturn(weather);
        GameInventory gameInventory = new GameInventory(20, deck, new Random(0), dice);
        validator = new ActionValidator(board, gameInventory, player, weather);
    }

    @Test
    void listActionsWhenFirstAction() {
        PossibleActionLister lister =
                new PossibleActionLister(board, validator, player.getPrivateInventory());

        var TILE_PRED = TileDeck.DEFAULT_DRAW_PREDICATE;

        var expected =
                List.of(
                        new Action.TakeObjective(Objective.Type.BAMBOO_SIZE),
                        new Action.TakeObjective(Objective.Type.HARVESTING),
                        new Action.TakeObjective(Objective.Type.TILE_PATTERN),

                        // not possible to move characters on the first turn

                        new Action.TakeIrrigationStick(),

                        // not possible to place irrigation stick on the first turn

                        new Action.PlaceTile(new Coord(0, -1), TILE_PRED),
                        new Action.PlaceTile(new Coord(0, 1), TILE_PRED),
                        new Action.PlaceTile(new Coord(-1, 1), TILE_PRED),
                        new Action.PlaceTile(new Coord(1, -1), TILE_PRED),
                        new Action.PlaceTile(new Coord(-1, 0), TILE_PRED),
                        new Action.PlaceTile(new Coord(1, 0), TILE_PRED));

        var actual = lister.getPossibleActions(TILE_PRED);

        assertEquals(expected, actual);
    }

    @Test
    void listActionsWhenATileWasPlacedWithCloud() throws IrrigationException, BoardException {
        resetValidator(WeatherDice.Face.CLOUDY);
        PossibleActionLister lister =
                new PossibleActionLister(board, validator, player.getPrivateInventory());

        var TILE_PRED = TileDeck.DEFAULT_DRAW_PREDICATE;

        board.placeTile(new Coord(0, 1), new BambooTile(Color.GREEN));
        player.getVisibleInventory()
                .incrementIrrigation(); // we assume that the player has an irrigation stick

        var expected =
                List.of(
                        new Action.TakeObjective(Objective.Type.BAMBOO_SIZE),
                        new Action.TakeObjective(Objective.Type.HARVESTING),
                        new Action.TakeObjective(Objective.Type.TILE_PATTERN),
                        new Action.PickPowerUp(PowerUp.ENCLOSURE),
                        new Action.PickPowerUp(PowerUp.FERTILIZER),
                        new Action.PickPowerUp(PowerUp.WATERSHED),
                        new Action.MovePiece(MovablePiece.GARDENER, new Coord(0, 1)),
                        new Action.MovePiece(MovablePiece.PANDA, new Coord(0, 1)),
                        new Action.TakeIrrigationStick(),
                        new Action.PlaceIrrigationStick(new Coord(0, 1), TileSide.UP_RIGHT),
                        new Action.PlaceIrrigationStick(new Coord(0, 1), TileSide.DOWN_RIGHT),
                        new Action.PlaceIrrigationStick(new Coord(0, 1), TileSide.DOWN),
                        new Action.PlaceIrrigationStick(new Coord(0, 1), TileSide.DOWN_LEFT),
                        new Action.PlaceIrrigationStick(new Coord(0, 1), TileSide.UP_LEFT),
                        new Action.PlaceTile(new Coord(0, -1), TILE_PRED),
                        new Action.PlaceTile(new Coord(-1, 1), TILE_PRED),
                        new Action.PlaceTile(new Coord(1, -1), TILE_PRED),
                        new Action.PlaceTile(new Coord(-1, 0), TILE_PRED),
                        new Action.PlaceTile(new Coord(1, 0), TILE_PRED));

        var actual = lister.getPossibleActions(TILE_PRED);

        assertEquals(expected, actual);
    }
}
