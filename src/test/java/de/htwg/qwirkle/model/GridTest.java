package de.htwg.qwirkle.model;

import org.junit.Before;
import org.junit.Test;
import de.htwg.qwirkle.model.Tile.*;

import static org.junit.Assert.*;

/**
 * Created by niboecke on 30.10.2015.
 */

public class GridTest {

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

    @Test(expected = IllegalArgumentException.class)
    public void testGrid() {
        grid_fail = new Grid(9, 9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetTile() {
        grid.setTile(tile1, 3, 3);
        grid.setTile(tile2, 3 ,3);
        // expect no exceptions
    }

    @Test
    public void testGetTile() {
        grid.setTile(tile1, 3, 3);
        assertEquals(tile1, grid.getTile(new Grid.Position(3,3)));
        assertEquals(null, grid.getTile(new Grid.Position(1,1)));
        assertEquals(null, grid.getTile(new Grid.Position(-1,1)));
    }

    @Test
    public void testToString() {

        grid.setTile(tile1,0,0);
        grid.setTile(tile2,2,4);

        for (int i = 0; i < 15; i++) {
            grid.setTile(supply.getTile(), 16, i);
        }

        System.out.println(grid);
    }

    @Test
    public void testGetNumberOfTiles() throws Exception {
        grid.setTile(tile1, 3, 3);
        grid.setTile(tile1, 4, 4);

        assertEquals(2, grid.getNumberOfTiles());
    }

    @Test
    public void testFieldIsEmpty() {
        grid.fieldIsEmpty(3, 3);
    }

    @Test
    public void testGetNumRows() {
        assertEquals(20, grid.getNumRows());
    }

    @Test
    public void testGetNumCols() {
        assertEquals(20, grid.getNumCols());
    }
}