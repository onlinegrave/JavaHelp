

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.*;
import java.util.function.Predicate;

import com.help.project.takenoko.player.bot.SaboteurBot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.help.project.takenoko.action.Action;
import com.help.project.takenoko.action.ActionValidator;
import com.help.project.takenoko.action.PossibleActionLister;
import com.help.project.takenoko.game.GameInventory;
import com.help.project.takenoko.game.GameInventoryException;
import com.help.project.takenoko.game.WeatherDice;
import com.help.project.takenoko.game.board.Board;
import com.help.project.takenoko.game.board.BoardException;
import com.help.project.takenoko.game.board.MovablePiece;
import com.help.project.takenoko.game.objective.*;
import com.help.project.takenoko.game.tile.*;
import com.help.project.takenoko.player.InventoryException;
import com.help.project.takenoko.utils.Coord;

class SaboteurBotTest {
    private Board board;
    private SaboteurBot bot;
    private PossibleActionLister actionLister;
    private ActionValidator validator;
    private GameInventory gameInventory;
    private ArrayList<Action> alreadyPlayedActions;

    @BeforeEach
    void setUp() {
        board = new Board();
        var random = new Random(0);
        bot = new SaboteurBot(random, "Saboteur");

        gameInventory =
                new GameInventory(20, new TileDeck(random), random, new WeatherDice(random));

        alreadyPlayedActions = new ArrayList<>();

        validator =
                new ActionValidator(
                        board, gameInventory, bot, WeatherDice.Face.SUN, alreadyPlayedActions);
        actionLister = new PossibleActionLister(board, validator, bot.getPrivateInventory());

        bot.beginTurn(2);
    }

    @Test
    void pickObjectiveAndIrrigationFirstTurn() {
        var action = bot.chooseAction(board, actionLister);

        assertTrue(action instanceof Action.TakeObjective);

        alreadyPlayedActions.add(action);

        action = bot.chooseAction(board, actionLister);
        assertTrue(action instanceof Action.TakeIrrigationStick);
    }

    @Test
    void oftenRetrieveBamboo()
            throws BambooSizeException, BambooIrrigationException, IrrigationException,
                    BoardException {
        var tile = new BambooTile(Color.GREEN);
        board.placeTile(new Coord(0, 1), tile);
        tile.growBamboo();

        // We don't want to take an objective or irrigation stick
        var possibleActions =
                actionLister.getPossibleActions().stream()
                        .filter(Predicate.not(Action.TakeObjective.class::isInstance))
                        .filter(Predicate.not(Action.TakeIrrigationStick.class::isInstance))
                        .toList();

        var lister = mock(PossibleActionLister.class);
        when(lister.getPossibleActions()).thenReturn(possibleActions);

        var action = bot.chooseAction(board, lister);

        assertTrue(
                action instanceof Action.MovePiece movePiece
                        && movePiece.piece() == MovablePiece.PANDA
                        && movePiece.to().equals(new Coord(0, 1)));
    }

    @Test
    void randomValidActionWhenNoBamboo() throws IrrigationException, BoardException {
        var tile = new BambooTile(Color.GREEN);
        board.placeTile(new Coord(0, 1), tile);

        var action = bot.chooseAction(board, actionLister);
        assertTrue(validator.isValid(action));
    }

    @Test
    void chooseWeatherWithRain() {
        var allowedWeathers = Arrays.asList(WeatherDice.Face.values());
        var weather = bot.chooseWeather(allowedWeathers);
        assertEquals(WeatherDice.Face.RAIN, weather);
    }

    @Test
    void chooseRandomWeatherWithNoRain() {
        var allowedWeathers = List.of(WeatherDice.Face.SUN, WeatherDice.Face.CLOUDY);
        var weather = bot.chooseWeather(allowedWeathers);
        assertTrue(allowedWeathers.contains(weather));
    }

    @Test
    void throwsWhenNoPossibleWeather() {
        var allowedWeathers = new ArrayList<WeatherDice.Face>();
        assertThrows(IllegalStateException.class, () -> bot.chooseWeather(allowedWeathers));
    }

    @Test
    void pickWatershedWhenPossible() {
        var pickWatershed = new Action.PickPowerUp(PowerUp.WATERSHED);

        var defaultPossibleActions = new ArrayList<>(actionLister.getPossibleActions());
        defaultPossibleActions.add(pickWatershed);

        var lister = mock(PossibleActionLister.class);
        when(lister.getPossibleActions()).thenReturn(defaultPossibleActions);

        assertEquals(pickWatershed, bot.chooseAction(board, lister));
    }

    @Test
    void endTurnWhenNoPossibleAction() {
        var lister = mock(PossibleActionLister.class);
        when(lister.getPossibleActions()).thenReturn(new ArrayList<>());
        assertEquals(Action.END_TURN, bot.chooseAction(board, lister));
    }

    @Test
    void focusesOnTwoHighestScoreObjectives()
            throws BambooSizeException, InventoryException, IrrigationException, BoardException,
                    GameInventoryException {
        var proposedObjective =
                new TilePatternObjective(Color.GREEN, TilePatternObjective.LINE_2, 1);
        var highScoreObjective =
                new BambooSizeObjective(
                        2, 1, Color.GREEN, 3, PowerUpNecessity.NO_MATTER, PowerUp.NONE);
        var allObjectives =
                List.of(
                        // low score
                        proposedObjective,
                        new TilePatternObjective(Color.GREEN, TilePatternObjective.LINE_2, 2),
                        new TilePatternObjective(Color.GREEN, TilePatternObjective.LINE_2, 1),
                        // high score
                        new HarvestingObjective(1, 1, 1, 4),
                        highScoreObjective);

        for (var objective : allObjectives) {
            bot.getPrivateInventory().addObjective(objective);
        }

        // usually, it would take an irrigation stick, so let's prevent it
        while (gameInventory.hasIrrigation()) gameInventory.decrementIrrigation();

        // let's provide it with an opportunity to complete the low score objective
        board.placeTile(new Coord(0, 1), new BambooTile(Color.GREEN));

        // even though it could complete the low score objective, it will not
        var action = bot.chooseAction(board, actionLister);

        // it will simulate actions
        assertTrue(action instanceof Action.SimulateActions);
        var simResult = ((Action.SimulateActions) action).outObjectiveStatus();

        simResult.put(
                new Action.PlaceTile(new Coord(0, 1), TileDeck.DEFAULT_DRAW_PREDICATE),
                Map.of(proposedObjective, new Objective.Status(1, 1)));
        simResult.put(
                new Action.MovePiece(MovablePiece.GARDENER, new Coord(0, 1)),
                Map.of(highScoreObjective, new Objective.Status(1, 4)));

        var newAction = bot.chooseAction(board, actionLister);

        // it could complete the low score objective! but it will not, because it is focused on the
        // high score objective. What an idiot! :)
        assertEquals(new Action.MovePiece(MovablePiece.GARDENER, new Coord(0, 1)), newAction);
    }
}
