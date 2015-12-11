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
    private static final int MAX_SIZE = 108;

    /**
     * Creates 108 Tiles - three of each color/shape combination - and puts them in Supply in random order.
     */
    public Supply() {
        tiles = new ArrayList<>(MAX_SIZE);
        random = new Random(43);            // TODO: random always gives the same numbers...

        for (Shape s : Shape.values()) {

            //ignore undefined values
            if(s == Shape.UNDEF)
                continue;

            for (Color c : Color.values()) {

                //ignore undefined values
                if(c == Color.UNDEF)
                    continue;
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
        if (tiles.isEmpty()) {
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
        if(tiles.size() >= MAX_SIZE)
        {
            throw new IndexOutOfBoundsException("number of max tiles in supply is already reached.");
        }

        tiles.add(t);
        Collections.shuffle(tiles);
    }

    public int getSize() {
        return tiles.size();
    }

    public boolean isEmpty() {
        return tiles.isEmpty();
    }

    @Override
    public String toString() {
        return "Supply{" +
                "tiles=" + tiles +
                '}';
    }
}
