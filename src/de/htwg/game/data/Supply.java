package de.htwg.game.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Collections.*;

import de.htwg.game.data.Tile.*;

/**
 * Created by niels on 23.10.2015.
 */
public class Supply {

    private ArrayList<Tile> tiles;
    private static Random random;

    /**
     * Create 108 Tiles and put them in Supply in random order
     */
    public Supply() {
        tiles = new ArrayList<>();

        for (Shape s : Shape.values()) {
            for (Color c : Color.values()) {
                for (int i = 0; i < 3; i++) {
                    tiles.add(new Tile(c, s));
                }
            }
        }
        Collections.shuffle(tiles); // not really neccesary as getTile() is random
    }

    /**
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

    public void insertTile() {

    }

    public int getSize() {
        return tiles.size();
    }
}
