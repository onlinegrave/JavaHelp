package com.help.project.takenoko.player;

import java.util.Random;
import com.help.project.takenoko.player.bot.PlotRushBot;
import com.help.project.takenoko.player.bot.RandomBot;
import com.help.project.takenoko.player.bot.SaboteurBot;

public class PlayerFactory {
    private PlayerFactory() {}

    public static Player makePlayer(PlayerType type, String name, Random random) {
        return switch (type) {
            case RANDOM -> new RandomBot(random, name);
            case PLOT_RUSH -> new PlotRushBot(random, name);
            case SABOTEUR -> new SaboteurBot(random, name);
        };
    }
}
