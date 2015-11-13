package de.htwg.qwirkle.model;

import de.htwg.qwirkle.model.Tile.*;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by niboecke on 30.10.2015.
 */
public class TileTest extends TestCase {

    Tile tile;

    @Before
    public void setUp() {
        tile = new Tile(Color.BLUE, Shape.CIRCLE);
    }

    @Test
    public void testGetColor() throws Exception {
        assertEquals(Color.BLUE, tile.getColor());
    }

    @Test
    public void testGetShape() throws Exception {
        assertEquals(Shape.CIRCLE, tile.getShape());
    }

    @Test
    public void testToString() throws Exception {
        System.out.printf("%s%n", tile);
    }

}
