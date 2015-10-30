package de.htwg.qwirkle.model;

import de.htwg.qwirkle.model.Tile.*;
import junit.framework.TestCase;

/**
 * Created by niboecke on 30.10.2015.
 */
public class TileTest extends TestCase {

    public void testGetColor() throws Exception {

    }

    public void testGetShape() throws Exception {

    }

    public void testToString() throws Exception {
        Tile tile1 = new Tile(Color.BLUE, Shape.CIRCLE);
        Tile tile2 = new Tile(Color.RED, Shape.CROSS);
        Tile tile3 = new Tile(Color.CYAN, Shape.STAR);
        Tile tile4 = new Tile(Color.GREEN, Shape.DIAMOND);
        Tile tile5 = new Tile(Color.YELLOW, Shape.CLOVER);
        Tile tile6 = new Tile(Color.PURPLE, Shape.SQUARE);

        System.out.printf("%s %s %s %n", tile4, tile5, tile6);
        System.out.println(tile1);
        System.out.println(tile2);
        System.out.println(tile3);
    }
}