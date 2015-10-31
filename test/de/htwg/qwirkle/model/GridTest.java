package de.htwg.qwirkle.model;

import junit.framework.TestCase;
import de.htwg.qwirkle.model.Tile.*;

import static org.junit.Assert.*;

/**
 * // TODO warum klappt das @Before nicht=
 * // TODO assertEquals() verwenden
 * Created by niboecke on 30.10.2015.
 */

public class GridTest extends TestCase {

    Tile tile1, tile2, tile3, tile4, tile5, tile6;
    Supply supply;

    @Before
    public void setUp() {
        Tile tile1 = new Tile(Color.BLUE, Shape.CIRCLE);
        Tile tile2 = new Tile(Color.RED, Shape.CROSS);
        Tile tile3 = new Tile(Color.CYAN, Shape.STAR);
        Tile tile4 = new Tile(Color.GREEN, Shape.DIAMOND);
        Tile tile5 = new Tile(Color.YELLOW, Shape.CLOVER);
        Tile tile6 = new Tile(Color.PURPLE, Shape.SQUARE);

        Supply supply = new Supply();
    }

    @Test
    public void testSetTile() throws Exception {
    }

    @Test
    public void testGetTile() throws Exception {

    }

    @Test
    public void testToString() throws Exception {
        Grid grid = new Grid(20,20);

        grid.setTile(tile1,0,0);
        grid.setTile(tile2,2,4);
        grid.setTile(tile3,10,10);
        grid.setTile(tile4,3,5);
        grid.setTile(tile5, 2, 7);
        grid.setTile(tile6,3,3);

        for (int i = 0; i < 15; i++) {
            grid.setTile(supply.getTile(), 16, i);
        }

        System.out.println(grid);
    }
}