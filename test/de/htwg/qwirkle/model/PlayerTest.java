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

    Player player, player_full;
    ArrayList<Tile> tiles;

    @Before
    public void setUp() {
        player = new Player("Agate");

        player_full = new Player();
        assertNotNull(player_full);

        tiles = new ArrayList<Tile>();
        tiles.add(new Tile(Color.BLUE, Shape.CIRCLE));
        tiles.add(new Tile(Color.GREEN, Shape.DIAMOND));
        tiles.add(new Tile(Color.YELLOW, Shape.CLOVER));
        tiles.add(new Tile(Color.PURPLE, Shape.STAR));

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
        boolean retval;

        for (int i = 0; i <= 7; i++)
        {
            retval = player.addTileToHand(new Tile(Color.BLUE, Shape.CLOVER));
            if(i > 5) {
                assertEquals(false, retval);
            } else {
                assertEquals(true, retval);
            }
        }
    }

    public void testgetTileFromHand() throws Exception {
        Tile retval;

        for (Tile t : tiles) {
            player.addTileToHand(t);
        }

        retval = player.getTileFromHand(2);
        assertEquals(tiles.get(1), retval);

        retval = player.getTileFromHand(10);
        assertNull(retval);
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