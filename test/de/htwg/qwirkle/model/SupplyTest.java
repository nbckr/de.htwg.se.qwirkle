package de.htwg.qwirkle.model;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by niels on 23.10.2015.
 */
public class SupplyTest extends TestCase {

    Supply supply;
    Tile tile;

    @Before
    public void setUp() {
        supply = new Supply();
        tile = new Tile(Tile.Color.BLUE, Tile.Shape.CIRCLE);
    }

    @Test
    public void testGetTile() throws Exception {
        int initsize = supply.getSize();
        for (int i = 0; i < initsize; i++) {
            assert(supply.getTile() != null);
        }

        assertEquals(null, supply.getTile());
    }

    @Test
    public void testInsertTile() throws Exception {
        Tile out = supply.getTile();
        supply.insertTile(tile);
    }

    @Test
    public void testGetSize() throws Exception {
        assertEquals(108, supply.getSize());
        Tile out = supply.getTile();
        out = supply.getTile();
        assertEquals(106, supply.getSize());
    }

    @Test
    public void testIsEmpty() throws Exception {
        int initsize = supply.getSize();
        for (int i = 0; i < initsize; i++) {
            assertEquals(false, supply.isEmpty());
            Tile out = supply.getTile();
        }
        assertEquals(true, supply.isEmpty());
    }

    @Test
    public void testToString() throws Exception {
        System.out.println(supply);
    }
}