package de.htwg.qwirkle.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import util.state.*;

import util.state.impl.*;

public class Supply {

    private List<Tile> tiles;
    private static Random random;
    private static final int MAX_SIZE = 108;

    /**
     * Creates 108 Tiles - three of each color/shape combination - and puts them in Supply in random order.
     */
    public Supply() {
        tiles = new ArrayList<Tile>(MAX_SIZE);
        random = new Random(System.currentTimeMillis());

        tiles.addAll(initTiles(new Blue()));
        tiles.addAll(initTiles(new Cyan()));
        tiles.addAll(initTiles(new Green()));
        tiles.addAll(initTiles(new Purple()));
        tiles.addAll(initTiles(new Red()));
        tiles.addAll(initTiles(new Yellow()));

        Collections.shuffle(tiles);
    }

    private List<Tile> initTiles(Color c) {
        ArrayList<Tile> t = new ArrayList<Tile>();

        for(int i = 0; i < 3; i++) {
            t.add(new Tile(c, new Circle()));
            t.add(new Tile(c, new Clover()));
            t.add(new Tile(c, new Cross()));
            t.add(new Tile(c, new Diamond()));
            t.add(new Tile(c, new Square()));
            t.add(new Tile(c, new Star()));
        }

        return t;
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
