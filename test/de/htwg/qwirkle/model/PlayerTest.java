package de.htwg.qwirkle.model;

import junit.framework.TestCase;

import java.util.ArrayList;
import de.htwg.qwirkle.model.Tile.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by luluschi on 23.10.2015.
 */

public class PlayerTest extends TestCase {

    Player player;
    ArrayList<Tile> tiles;

    @Before
    public void setUp() {
        player = new Player("Agate");

        tiles = new ArrayList<Tile>();
        tiles.add(new Tile(Color.BLUE, Shape.CIRCLE));
        tiles.add(new Tile(Color.GREEN, Shape.DIAMOND));

    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("Agate", player.getName());
    }

    @Test
    public void testGetHand() throws Exception {
        assertEquals(0, player.getHand().size());

        for (Tile t : tiles) {
            player.addTileToHand(t);
        }
        assertEquals(true, tiles.equals(player.getHand()));
    }

    @Test
    public void testAddTileToHand() throws Exception {
        testGetHand();
    }

    @Test
    public void testGetScore() throws Exception {
        assertEquals(0, player.getScore());
        player.addScore(123);
        assertEquals(123, player.getScore());
        // player.addScore(-234);
        // assertEquals(123, player.getScore());
        System.out.println(player);
    }

    @Test
    public void testAddScore() throws Exception {
        testGetScore();
    }

    @Test
    public void testPrintHand() throws Exception {
        System.out.println(player.printHand());
        for (Tile t : tiles) {
            player.addTileToHand(t);
        }
        System.out.println(player.printHand());
    }

    @Test
    public void testToString() throws Exception {
        System.out.println(player);
    }
}