package de.htwg.qwirkle.model;

import org.junit.Before;
import org.junit.Test;
import de.htwg.qwirkle.model.Grid.*;

import static org.junit.Assert.*;

public class PositionTest {

    Position pos1, pos2, pos3;

    @Before
    public void setUp() {
        pos1 = new Grid.Position(10, 10);
        pos2 = new Grid.Position(pos1);
        pos3 = new Grid.Position();
    }

    @Test
    public void testGetRow() throws Exception {
        assertEquals(10, pos1.getRow());
    }

    @Test
    public void testGetCol() throws Exception {
        assertEquals(10, pos1.getCol());
    }

    @Test
    public void testSetPosition() throws Exception {
        pos1.setPosition(20, 20);
        assertEquals(20, pos1.getCol());
        assertEquals(20, pos1.getRow());
    }

    @Test
    public void testEquals() throws Exception {
        Position pos1 = new Position(1, 1);
        Position pos2 = new Position(1, 1);
        assertEquals(pos1, pos2);
        assertNotEquals(pos1, new Integer(1));


    }

    @Test
    public void testHashCode() throws Exception {
        assertEquals(220, pos1.hashCode());
    }

    @Test
    public void testToString() throws Exception {
        // the getClass().getName() method apparently uses '$' to show inner class
        String testOutput = "de.htwg.qwirkle.model.Grid$Position[row=10,col=10]";
        assertEquals(testOutput, pos1.toString());
    }

    @Test
    public void testGetNorth() throws Exception {
        Position northPosition = pos1.getNorth();
        assertEquals(new Position(9, 10), northPosition);
    }

    @Test
    public void testGetEast() throws Exception {
        Position eastPosition = pos1.getEast();
        assertEquals(new Position(10, 11), eastPosition);
    }

    @Test
    public void testGetSouth() throws Exception {
        Position southPosition = pos1.getSouth();
        assertEquals(new Position(11, 10), southPosition);
    }

    @Test
    public void testGetWest() throws Exception {
        Position westPosition = pos1.getWest();
        assertEquals(new Position(10, 9), westPosition);
    }
}