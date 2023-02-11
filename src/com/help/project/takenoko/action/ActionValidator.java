package com.help.project.takenoko.action;

import java.util.ArrayList;
import java.util.List;

import com.help.project.takenoko.game.board.Board;
import com.help.project.takenoko.game.GameInventory;
import com.help.project.takenoko.game.WeatherDice;
import com.help.project.takenoko.game.board.BoardException;
import com.help.project.takenoko.game.board.MovablePiece;
import com.help.project.takenoko.game.objective.HarvestingObjective;
import com.help.project.takenoko.game.tile.BambooTile;
import com.help.project.takenoko.game.tile.Color;
import com.help.project.takenoko.game.tile.EmptyDeckException;
import com.help.project.takenoko.game.tile.PowerUp;
import com.help.project.takenoko.player.Player;

@SuppressWarnings("DuplicateBranchesInSwitch")
public class ActionValidator {
    private final Board board;
    private final GameInventory gameInventory;
    private final Player player;
    private final List<Action> alreadyPlayedActions;
    private final WeatherDice.Face weather;

    public ActionValidator(
            Board board,
            GameInventory gameInventory,
            Player player,
            WeatherDice.Face weather,
            List<Action> alreadyPlayedActions) {
        this.board = board;
        this.gameInventory = gameInventory;
        this.player = player;
        this.alreadyPlayedActions = alreadyPlayedActions;
        this.weather = weather;
    }

    public ActionValidator(
            Board board, GameInventory gameInventory, Player player, WeatherDice.Face weather) {
        this(board, gameInventory, player, weather, new ArrayList<>());
    }

    public boolean isValid(Action action) {
        if (weather != WeatherDice.Face.WIND
                && alreadyPlayedActions.stream().anyMatch(a -> a.isSameTypeAs(action)))
            return false;

        if (action.hasCost() && player.availableActionCredits() < 1) return false;

        return switch (action) {
            case Action.None ignored -> true;
            case Action.PlaceIrrigationStick a -> isValid(a);
            case Action.PlaceTile a -> isValid(a);
            case Action.TakeIrrigationStick a -> isValid(a);
            case Action.TakeObjective a -> isValid(a);
            case Action.UnveilObjective a -> isValid(a);
            case Action.MovePiece a -> isValid(a);
            case Action.EndTurn ignored -> true;
            case Action.SimulateActions ignored -> true;
            case Action.BeginSimulation ignored -> true;
            case Action.EndSimulation ignored -> true;
            case Action.PickPowerUp a -> isValid(a);
            case Action.PlacePowerUp a -> isValid(a);
            case Action.GrowOneTile growOneTile -> isValid(growOneTile);
            case Action.MovePandaAnywhere movePandaAnywhere -> isValid(movePandaAnywhere);
        };
    }

    private boolean isValid(Action.GrowOneTile growOneTile) {
        if (weather != WeatherDice.Face.RAIN) return false;

        try {
            return board.getTile(growOneTile.at()) instanceof BambooTile bambooTile
                    && bambooTile.isCultivable()
                    && bambooTile.isIrrigated();
        } catch (BoardException e) {
            return false;
        }
    }

    private boolean isValid(Action.MovePandaAnywhere movePandaAnywhere) {
        return weather == WeatherDice.Face.THUNDERSTORM
                && board.getPlacedCoords().contains(movePandaAnywhere.to());
    }

    private boolean isValid(Action.TakeObjective takeObjective) {
        var deck = gameInventory.getObjectiveDeck(takeObjective.type());
        return deck.size() > 0 && player.getPrivateInventory().canDrawObjective();
    }

    private boolean isValid(Action.PlaceIrrigationStick action) {
        if (!player.getVisibleInventory().hasIrrigation()) {
            return false;
        }

        var coord = action.coord();
        var side = action.side();

        try {
            var tile = board.getTile(coord);
            return !tile.isSideIrrigated(side);
        } catch (BoardException e) {
            return false;
        }
    }

    private boolean isValid(Action.PlaceTile action) {
        var deck = gameInventory.getTileDeck();
        if (deck.size() == 0) return false;
        try {
            int pickedTile = deck.simulateDraw(action.drawPredicate());
            return pickedTile >= 0
                    && pickedTile < deck.size()
                    && board.isAvailableCoord(action.coord());
        } catch (EmptyDeckException e) {
            return false;
        }
    }

    private boolean isValid(Action.TakeIrrigationStick ignored) {
        return gameInventory.hasIrrigation();
    }

    private boolean isValid(Action.UnveilObjective action) {
        if (!action.objective().isAchieved()) return false;

        if (action.objective() instanceof HarvestingObjective needs) {
            var inventory = player.getVisibleInventory();
            return inventory.getBamboo(Color.GREEN) >= needs.getGreen()
                    && inventory.getBamboo(Color.PINK) >= needs.getPink()
                    && inventory.getBamboo(Color.YELLOW) >= needs.getYellow();
        }
        return true;
    }

    private boolean isValid(Action.MovePiece action) {
        var currentCoord = board.getPieceCoord(action.piece());
        try {
            if (action.piece().equals(MovablePiece.PANDA)
                    && board.getTile(action.to()) instanceof BambooTile bambooTile
                    && bambooTile.getPowerUp().equals(PowerUp.ENCLOSURE)) {
                return false;
            }
        } catch (BoardException ignored) {
            return false;
        }
        return board.getPlacedCoords().contains(action.to())
                && !currentCoord.equals(action.to())
                && currentCoord.isAlignedWith(action.to());
    }

    private boolean isValid(Action.PickPowerUp action) {
        return weather == WeatherDice.Face.CLOUDY
                && gameInventory.getPowerUpReserve().canPick(action.powerUp());
    }

    private boolean isValid(Action.PlacePowerUp action) {
        try {
            return board.getTile(action.coord()) instanceof BambooTile bambooTile
                    && bambooTile.getPowerUp().equals(PowerUp.NONE)
                    && player.getVisibleInventory().hasPowerUp(action.powerUp());
        } catch (BoardException ignored) {
        }
        return false;
    }
}
