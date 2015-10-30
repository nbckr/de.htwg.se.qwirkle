package de.htwg.qwirkle.model;

import junit.framework.TestCase;
import de.htwg.qwirkle.model.Tile.*;

/**
 * Created by niboecke on 30.10.2015.
 */

public class GridTest extends TestCase {

    static Tile tile1 = new Tile(Color.BLUE, Shape.CIRCLE);
    static Tile tile2 = new Tile(Color.RED, Shape.CROSS);
    static Tile tile3 = new Tile(Color.CYAN, Shape.STAR);
    static Tile tile4 = new Tile(Color.GREEN, Shape.DIAMOND);
    static Tile tile5 = new Tile(Color.YELLOW, Shape.CLOVER);
    static Tile tile6 = new Tile(Color.PURPLE, Shape.SQUARE);

    public void testAddTile() throws Exception {

    }

    public void testToString() throws Exception {
        Grid grid = new Grid(15,15);

        grid.addTile(tile1,0,0);
        grid.addTile(tile2,2,4);
        grid.addTile(tile3,10,10);
        grid.addTile(tile4,3,5);
        grid.addTile(tile5,2,7);
        grid.addTile(tile6,3,3);

        System.out.println(grid);
    }
}