package de.htwg.qwirkle.model;

import junit.framework.TestCase;
import de.htwg.qwirkle.model.Tile.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

/**
 * Created by niboecke on 30.10.2015.
 */

public class GridTest extends TestCase {

    Tile tile1, tile2;
    Supply supply;
    Grid grid, grid_full, grid_fail;

    @Before
    public void setUp() {
        grid = new Grid(20,20);

        //test separate constructors
        grid_full = new Grid();
        assertNotNull(grid_full);

        tile1 = new Tile(Color.BLUE, Shape.CIRCLE);
        tile2 = new Tile(Color.RED, Shape.CROSS);

        supply = new Supply();
    }

    @Test(expected=IllegalArgumentException.class)
    public void testGrid() throws Exception {
        grid_fail = new Grid(9,9);
    }

    @Test
    public void testSetTile() throws Exception {
        assertEquals(true, grid.setTile(tile1, 3,3));
        assertEquals(false, grid.setTile(tile2, 3,3));
    }

    @Test
    public void testGetTile() throws Exception {
        assertEquals(true, grid.setTile(tile1, 3,3));
        assertEquals(tile1, grid.getTile(3,3));
        assertEquals(null, grid.getTile(1,1));
    }

    @Test
    public void testToString() throws Exception {

        grid.setTile(tile1,0,0);
        grid.setTile(tile2,2,4);

        for (int i = 0; i < 15; i++) {
            grid.setTile(supply.getTile(), 16, i);
        }

        System.out.println(grid);
    }
}