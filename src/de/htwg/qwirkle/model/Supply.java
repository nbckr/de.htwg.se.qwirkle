package de.htwg.qwirkle.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Collections.*;
import java.lang.Math;

import de.htwg.qwirkle.model.Tile.*;

/**
 * Created by niels on 23.10.2015.
 */
public class Supply {

    private ArrayList<Tile> tiles;
    private static Random random;
    private static final int MAX_SIZE = 108;

    /**
     * Creates 108 Tiles - three of each color/shape combination - and puts them in Supply in random order.
     */
    public Supply() {
        tiles = new ArrayList<>(MAX_SIZE);
        random = new Random(43);            // TODO: random always gives the same numbers...

        for (Shape s : Shape.values()) {
            for (Color c : Color.values()) {
                for (int i = 0; i < 3; i++) {
                    tiles.add(new Tile(c, s));
                }
            }
        }
        Collections.shuffle(tiles);
    }

    /**
     * Draws a Tile from Supply randomly.
     * @return a Tile randomly chosen from Supply or null if Supply is empty
     */
    public Tile getTile() {
        if (tiles.size() == 0) {
            return null;
        }

        int i = Math.abs(random.nextInt() % tiles.size());
        Tile t = tiles.get(i);
        tiles.remove(i);
        return t;
    }

    /**
     * Inserts a Tile into Supply.
     * @param t the Tile to insert
     */
    public void insertTile(Tile t) {
        assert(tiles.size() < MAX_SIZE);
        tiles.add(t);
        Collections.shuffle(tiles);
    }

    public int getSize() {
        return tiles.size();
    }

    public boolean isEmpty() {
        return tiles.size() == 0;
    }

    @Override
    public String toString() {
        return "Supply{" +
                "tiles=" + tiles +
                '}';
    }
}
