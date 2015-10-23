package de.htwg.game.data;

import java.util.ArrayList;
import java.util.Random;

import de.htwg.game.data.Tile.Shape;
import de.htwg.game.data.Tile.Color;

/**
 * Created by niels on 23.10.2015.
 */
public class Supply {

    private ArrayList<Tile> tiles;
    private int size;
    private Random random;

    public Supply() {
        // create 108 Tiles and put them in Supply in random order
        for (Shape s : Shape.values()) {
            for (Color c : Color.values()) {
               // for (int i = 0; i < 3; i++)
            }
        }
    }

    public Tile getTile() {
        return null;
    }

    public void insertTile() {

    }

    public int getSize() {
        return size;
    }
}
