package de.htwg.qwirkle.model;

import junit.framework.TestCase;

import java.util.ArrayList;
import de.htwg.qwirkle.model.Tile.*;

/**
 * Created by luluschi on 23.10.2015.
 */

public class PlayerTest extends TestCase {

    Player p;
    ArrayList<Tile> tiles;

    public void setUp() {
        p = new Player("Agate");

        tiles = new ArrayList<Tile>();
        tiles.add(new Tile(Color.BLUE, Shape.CIRCLE));
        tiles.add(new Tile(Color.GREEN, Shape.DIAMOND));

    }

    public void testGetName() throws Exception {
        assertEquals("Agate", p.getName());
    }

    public void testGetScore() throws Exception {
        assertEquals(0, p.getScore());
        p.addScore(123);
        assertEquals(123, p.getScore());
        p.addScore(-234);
        assertEquals(123, p.getScore());
    }

    public void testGetHand() throws Exception {
        assertEquals(0, p.getHand().size());

        for (Tile t : tiles) {
            p.addTileToHand(t);
        }
        assertEquals(true, tiles.equals(p.getHand()));
    }
}