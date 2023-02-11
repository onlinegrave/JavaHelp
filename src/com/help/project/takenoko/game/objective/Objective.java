package com.help.project.takenoko.game.objective;

import com.help.project.takenoko.action.Action;
import com.help.project.takenoko.game.board.Board;
import com.help.project.takenoko.game.board.VisibleInventory;

public sealed interface Objective
        permits BambooSizeObjective, HarvestingObjective, TilePatternObjective {
    enum Type {
        BAMBOO_SIZE,
        HARVESTING,
        TILE_PATTERN
    }

    record Status(int completed, int totalToComplete) {
        public boolean achieved() {
            return completed >= totalToComplete;
        }

        public float progressFraction() {
            return (float) completed / totalToComplete;
        }
    }

    Status computeAchieved(Board board, Action lastAction, VisibleInventory visibleInventory);

    Status status();

    void forceRecomputeOnNextCheck();

    default boolean isAchieved() {
        return status().achieved();
    }

    int getScore();

    Type getType();
}
