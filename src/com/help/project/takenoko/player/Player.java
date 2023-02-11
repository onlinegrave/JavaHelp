package com.help.project.takenoko.player;

import java.util.List;
import com.help.project.takenoko.action.Action;
import com.help.project.takenoko.action.PossibleActionLister;
import com.help.project.takenoko.game.WeatherDice;
import com.help.project.takenoko.game.board.Board;
import com.help.project.takenoko.game.board.VisibleInventory;

public interface Player {
    void beginTurn(int actionCredits);

    int availableActionCredits();

    Action chooseAction(Board board, PossibleActionLister actionLister);

    PrivateInventory getPrivateInventory();

    VisibleInventory getVisibleInventory();

    void increaseScore(int delta);

    int getScore();

    void decreaseScore(int score);

    WeatherDice.Face chooseWeather(List<WeatherDice.Face> allowedWeathers);

    String getName();
}
