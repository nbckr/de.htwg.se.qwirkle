package de.htwg.qwirkle.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by niels on 23.10.2015.
 */
public class SupplyTest {

    Supply supply;
    Tile tile;

    @Before
    public void setUp() {
        supply = new Supply();
        tile = new Tile(Tile.Color.BLUE, Tile.Shape.CIRCLE);
    }

    @Test
    public void testGetTile() {
        int initsize = supply.getSize();

        for (int i = 0; i < initsize; i++) {
            assert(supply.getTile() != null);
        }

        assertEquals(null, supply.getTile());
    }

    @Test
    public void testInsertTile() {
        Tile out = supply.getTile();
        supply.insertTile(tile);

        //throws exception
        try {
            supply.insertTile(tile);
        } catch(IndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBoundsException expected: " + e.getMessage());
        }
    }

    @Test
    public void testGetSize() {
        assertEquals(108, supply.getSize());
        Tile out = supply.getTile();
        out = supply.getTile();
        assertEquals(106, supply.getSize());
    }

    @Test
    public void testIsEmpty() {
        int initsize = supply.getSize();
        for (int i = 0; i < initsize; i++) {
            assertEquals(false, supply.isEmpty());
            Tile out = supply.getTile();
        }
        assertEquals(true, supply.isEmpty());
    }

    @Test
    public void testToString() {
        System.out.println(supply);
    }
}