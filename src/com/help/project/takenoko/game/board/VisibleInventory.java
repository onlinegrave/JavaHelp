package com.help.project.takenoko.game.board;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import com.help.project.takenoko.game.objective.Objective;
import com.help.project.takenoko.game.tile.Color;
import com.help.project.takenoko.game.tile.PowerUp;
import com.help.project.takenoko.player.InventoryException;

public class VisibleInventory {
    private final EnumMap<Color, Integer> bamboos;
    private final EnumMap<PowerUp, Integer> powerUps;
    private final List<Objective> finishedObjectives;
    private int irrigationCount;

    public VisibleInventory() {
        bamboos = new EnumMap<>(Color.class);
        irrigationCount = 0;
        powerUps = new EnumMap<>(PowerUp.class);
        finishedObjectives = new ArrayList<>();
    }

    public VisibleInventory(VisibleInventory other) {
        bamboos = new EnumMap<>(other.bamboos);
        irrigationCount = other.irrigationCount;
        powerUps = new EnumMap<>(other.powerUps);
        finishedObjectives = new ArrayList<>(other.finishedObjectives);
    }

    public void restore(VisibleInventory other) {
        bamboos.clear();
        bamboos.putAll(other.bamboos);
        irrigationCount = other.irrigationCount;
        powerUps.clear();
        powerUps.putAll(other.powerUps);
        finishedObjectives.clear();
        finishedObjectives.addAll(other.finishedObjectives);
    }

    public int getBamboo(Color color) {
        return bamboos.getOrDefault(color, 0);
    }

    public void incrementBamboo(Color color) {
        bamboos.put(color, getBamboo(color) + 1);
    }

    public void useBamboo(Color color, int nb) throws InventoryException {
        if (getBamboo(color) < nb) {
            throw new InventoryException("Not enough bamboo");
        }
        bamboos.put(color, getBamboo(color) - nb);
    }

    public boolean hasIrrigation() {
        return irrigationCount > 0;
    }

    public void incrementIrrigation() {
        irrigationCount++;
    }

    public void decrementIrrigation() throws InventoryException {
        if (!hasIrrigation()) {
            throw new InventoryException("Not enough irrigation");
        }
        irrigationCount--;
    }

    public boolean hasPowerUp(PowerUp powerUp) {
        return powerUps.getOrDefault(powerUp, 0) > 0;
    }

    public void incrementPowerUp(PowerUp powerUp) {
        powerUps.put(powerUp, powerUps.getOrDefault(powerUp, 0) + 1);
    }

    public void decrementPowerUp(PowerUp powerUp) throws InventoryException {
        if (!hasPowerUp(powerUp)) {
            throw new InventoryException("Not enough power up");
        }
        powerUps.put(powerUp, powerUps.get(powerUp) - 1);
    }

    public void addObjective(Objective objective) {
        finishedObjectives.add(objective);
    }

    public List<Objective> getFinishedObjectives() {
        return finishedObjectives;
    }

    public void removeObjective(Objective objective) {
        finishedObjectives.remove(objective);
    }
}
