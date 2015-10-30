package de.htwg.qwirkle.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Collections.*;

import de.htwg.qwirkle.model.Tile.*;

/**
 * Created by niels on 23.10.2015.
 */
public class Supply {

    private ArrayList<Tile> tiles;
    private static Random random;

    /**
     * Creates 108 Tiles - three of each color/shape combination - and puts them in Supply in random order.
     */
    public Supply() {
        tiles = new ArrayList<>(108);

        for (Shape s : Shape.values()) {
            for (Color c : Color.values()) {
                for (int i = 0; i < 3; i++) {
                    tiles.add(new Tile(c, s));
                }
            }
        }
        // Collections.shuffle(tiles) not really neccesary as getTile() is random
    }

    /**
     * Draws a Tile from Supply randomly.
     * @return a Tile randomly chosen from Supply or null if Supply is empty
     */
    public Tile getTile() {
        if (tiles.size() == 0) {
            return null;
        }

        int i = random.nextInt() % tiles.size();
        Tile t = tiles.get(i);
        tiles.remove(i);
        return t;
    }

    /**
     * Inserts a Tile into Supply.
     * @param t the Tile to insert
     */
    public void insertTile(Tile t) {
        assert(tiles.size() < 108);
        tiles.add(t);
        // Collections.shuffle(tiles) not really neccesary as getTile() is random
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
