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
    public void testGetName() {
        assertEquals("Agate", player.getName());
    }

    @Test
    public void testGetHand() {
        assertEquals(0, player.getHand().size());

        for (Tile t : tiles) {
            player.addTileToHand(t);
        }
        assertEquals(true, tiles.equals(player.getHand()));
    }

    @Test
    public void testAddTileToHand() {
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

    @Test
    public void testgetTileFromHand() {
        Tile retval;

        for (Tile t : tiles) {
            player.addTileToHand(t);
        }

        retval = player.getTileFromHand(2);
        assertEquals(tiles.get(1), retval);

        retval = player.getTileFromHand();
        assertNotNull(retval);

        retval = player.getTileFromHand(10);
        assertNull(retval);
    }

    @Test
    public void testGetScore() {
        assertEquals(0, player.getScore());
        player.addScore(123);
        assertEquals(123, player.getScore());
        // player.addScore(-234);
        // assertEquals(123, player.getScore());
        System.out.println(player);
    }

    @Test
    public void testAddScore() {
        testGetScore();
    }

    @Test
    public void testPrintHand() {
        System.out.println(player.printHand());
        for (Tile t : tiles) {
            player.addTileToHand(t);
        }
        System.out.println(player.printHand());
    }

    @Test
    public void testToString() {
        System.out.println(player);
    }
}