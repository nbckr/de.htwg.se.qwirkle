package de.htwg.qwirkle.model;

import org.junit.Before;
import org.junit.Test;
import de.htwg.qwirkle.model.Tile.*;

import static org.junit.Assert.*;

/**
 * Created by niboecke on 30.10.2015.
 */
public class TileTest {

    Tile tile[], tile_comp, tile_undef;

    @Before
    public void setUp() {
        tile = new Tile[7];

        tile[0] = new Tile(Color.CYAN, Shape.CIRCLE);
        tile[1] = new Tile(Color.YELLOW, Shape.SQUARE);
        tile[2] = new Tile(Color.RED, Shape.CLOVER);
        tile[3] = new Tile(Color.BLUE, Shape.DIAMOND);
        tile[4] = new Tile(Color.GREEN, Shape.STAR);
        tile[5] = new Tile(Color.PURPLE, Shape.CROSS);
        tile[6] = new Tile();

        tile_comp = new Tile(Color.CYAN, Shape.CIRCLE);
        tile_undef = new Tile();
    }

    @Test
    public void testGetColor() {
        assertEquals(Color.CYAN, tile[0].getColor());
    }

    @Test
    public void testGetShape() {
        assertEquals(Shape.CIRCLE, tile[0].getShape());
    }

    @Test
    public void testEquals() {
        boolean retval;

        retval = tile[0].equals(tile_comp);
        assertTrue(retval);

        retval = tile[0].equals(tile[1]);
        assertFalse(retval);

        assertFalse(tile[0].equals(new Integer(1)));
    }

    @Test
    public void testToString() {
        for(Tile t : tile) {
            System.out.print(t.toString());
        }

        System.out.println(tile_undef.toString());
    }

    @Test
    public void testIsUndefined() throws Exception {
        assertTrue(tile_undef.isUndefined());
    }

    @Test
    public void testSetSelected() throws Exception {
        tile[0].setSelected(true);
        assertTrue(tile[0].isSelected());
    }

    @Test
    public void testHashCode() throws Exception {
        assertEquals(42, tile[0].hashCode());
    }

    @Test
    public void testToString2() throws Exception {
        assertEquals("TILE_CYAN_CIRCLE", tile[0].toString2());
    }

    @Test
    public void testGetImageFilepath() throws Exception {
        assertEquals("src/main/resources/img/TILE_CYAN_CIRCLE.jpg", tile[0].getImageFilepath());
    }
}
