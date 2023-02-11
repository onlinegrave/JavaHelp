package com.help.project.takenoko.player.bot;

import java.util.List;
import com.help.project.takenoko.action.Action;
import com.help.project.takenoko.action.PossibleActionLister;
import com.help.project.takenoko.game.WeatherDice;
import com.help.project.takenoko.game.board.Board;
import com.help.project.takenoko.player.PlayerBase;

public class DefaultBot extends PlayerBase<DefaultBot> implements PlayerBase.PlayerBaseInterface {
    public Action chooseActionImpl(Board board, PossibleActionLister actionLister) {
        return Action.END_TURN;
    }

    @Override
    public WeatherDice.Face chooseWeatherImpl(List<WeatherDice.Face> allowedWeathers) {
        return WeatherDice.Face.SUN;
    }
}
