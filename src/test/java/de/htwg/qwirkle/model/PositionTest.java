package de.htwg.qwirkle.model;

import org.junit.Before;
import org.junit.Test;
import de.htwg.qwirkle.model.Grid.*;

import static org.junit.Assert.*;

public class PositionTest {

    Position position;

    @Before
    public void setUp() {
        position = new Position(10, 10);
    }

    @Test
    public void testGetRow() throws Exception {
        assertEquals(10, position.getRow());
    }

    @Test
    public void testGetCol() throws Exception {
        assertEquals(10, position.getCol());
    }

    @Test
    public void testSetPosition() throws Exception {
        position.setPosition(20, 20);
        assertEquals(20, position.getCol());
        assertEquals(20, position.getRow());
    }

    @Test
    public void testEquals() throws Exception {
        Position pos1 = new Position(1, 1);
        Position pos2 = new Position(1, 1);
        assertEquals(pos1, pos2);
    }

    @Test
    public void testHashCode() throws Exception {
        assertEquals(220, position.hashCode());
    }

    @Test
    public void testToString() throws Exception {
        // the getClass().getName() method apparently uses '$' to show inner class
        String testOutput = "de.htwg.qwirkle.model.Grid$Position[row=10,col=10]";
        assertEquals(testOutput, position.toString());
    }
}