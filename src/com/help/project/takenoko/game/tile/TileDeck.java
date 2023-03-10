package com.help.project.takenoko.game.tile;

import com.help.project.takenoko.game.Deck;

import java.util.*;

public class TileDeck extends Deck<Tile> {
    public static final DrawPredicate<Tile> DEFAULT_DRAW_PREDICATE = ignored -> 0;
    private static final int DRAW_SIZE = 3;

    public TileDeck(Random random) {
        this(generateOfficialTiles(random));
    }

    public TileDeck(Deque<Tile> tiles) {
        super(tiles, DRAW_SIZE);
    }

    private static Deque<Tile> generateOfficialTiles(Random random) {
        List<Tile> tempTiles = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            tempTiles.add(new BambooTile(Color.GREEN, PowerUp.NONE));
        }
        for (int i = 0; i < 2; i++) {
            tempTiles.add(new BambooTile(Color.GREEN, PowerUp.WATERSHED));
        }
        for (int i = 0; i < 2; i++) {
            tempTiles.add(new BambooTile(Color.GREEN, PowerUp.ENCLOSURE));
        }
        tempTiles.add(new BambooTile(Color.GREEN, PowerUp.FERTILIZER));

        for (int i = 0; i < 4; i++) {
            tempTiles.add(new BambooTile(Color.PINK, PowerUp.NONE));
        }
        tempTiles.add(new BambooTile(Color.PINK, PowerUp.WATERSHED));
        tempTiles.add(new BambooTile(Color.PINK, PowerUp.FERTILIZER));
        tempTiles.add(new BambooTile(Color.PINK, PowerUp.ENCLOSURE));

        for (int i = 0; i < 6; i++) {
            tempTiles.add(new BambooTile(Color.YELLOW, PowerUp.NONE));
        }
        tempTiles.add(new BambooTile(Color.YELLOW, PowerUp.WATERSHED));
        tempTiles.add(new BambooTile(Color.YELLOW, PowerUp.FERTILIZER));
        tempTiles.add(new BambooTile(Color.YELLOW, PowerUp.ENCLOSURE));

        Collections.shuffle(tempTiles, random);
        return new ArrayDeque<>(tempTiles);
    }
}
