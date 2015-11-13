package de.htwg.qwirkle.model;

import junit.framework.TestCase;
import de.htwg.qwirkle.model.Tile.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by niboecke on 30.10.2015.
 */

public class GridTest extends TestCase {

    Tile tile1, tile2, tile3, tile4, tile5, tile6;
    Supply supply;
    Grid grid;

    @Before
    public void setUp() {
        grid = new Grid(20,20);

        tile1 = new Tile(Color.BLUE, Shape.CIRCLE);
        tile2 = new Tile(Color.RED, Shape.CROSS);
        tile3 = new Tile(Color.CYAN, Shape.STAR);
        tile4 = new Tile(Color.GREEN, Shape.DIAMOND);
        tile5 = new Tile(Color.YELLOW, Shape.CLOVER);
        tile6 = new Tile(Color.PURPLE, Shape.SQUARE);

        supply = new Supply();
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

        /*grid.setTile(tile1,0,0);
        grid.setTile(tile2,2,4);
        grid.setTile(tile3,10,10);
        grid.setTile(tile4,3,5);
        grid.setTile(tile5, 2, 7);
        grid.setTile(tile6,3,3);*/

        for (int i = 0; i < 15; i++) {
            grid.setTile(supply.getTile(), 16, i);
        }

        //System.out.println(grid);
    }
}