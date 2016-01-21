package de.htwg.qwirkle.model;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import util.state.impl.*;

import static org.junit.Assert.*;

/**
 * Created by luluschi on 23.10.2015.
 */

public class PlayerTest {

    Player player, player_full;
    ArrayList<Tile> tiles;

    @Before
    public void setUp() {
        player = new Player("Agate");

        player_full = new Player();
        assertNotNull(player_full);

        tiles = new ArrayList<Tile>();
        tiles.add(new Tile(new Blue(), new Circle()));
        tiles.add(new Tile(new Green(), new Diamond()));
        tiles.add(new Tile(new Yellow(), new Clover()));
        tiles.add(new Tile(new Purple(), new Star()));

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

    @Test(expected=IllegalArgumentException.class)
    public void testAddTileToHand() {
        boolean retval;

        for (int i = 0; i <= 5; i++)
        {
            player.addTileToHand(new Tile(new Blue(), new Clover()));
            // assert no Exceptions
        }

        player.addTileToHand(new Tile(new Cyan(), new Diamond()));
        player.addTileToHand(new Tile(new Cyan(), new Diamond()));
    }

    @Test
    public void testgetTileFromHand() {
        Tile retval;

        for (Tile t : tiles) {
            player.addTileToHand(t);
        }

        retval = player.getTile(2);
        assertEquals(tiles.get(1), retval);

        retval = player.getTile(1);
        assertNotNull(retval);

        retval = player.getTile(10);
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

    @Test(expected=IllegalArgumentException.class)
    public void testAddTilesToHand() throws Exception {
        player.addTilesToHand(tiles);
        assertEquals(4, player.getHandSize());

        ArrayList<Tile> t = new ArrayList<Tile>();
        t.add(new Tile(new Purple(), new Cross()));
        t.add(new Tile(new Green(), new Diamond()));

        player.addTilesToHand(t);
        assertEquals(6, player.getHandSize());

        player.addTilesToHand(t);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testRemoveTile() throws Exception {
        player.addTilesToHand(tiles);
        assertEquals(4, player.getHandSize());

        player.removeTile(1);
        assertEquals(3, player.getHandSize());

        player.removeTile(5);
    }

    @Test
    public void testRemoveTiles() throws Exception {
        player.addTilesToHand(tiles);
        assertEquals(4, player.getHandSize());

        ArrayList<Integer> l = new ArrayList<Integer>();
        l.add(1);
        l.add(2);
        player.removeTiles(l);
        assertEquals(2, player.getHandSize());
    }

    @Test
    public void testGetTile() throws Exception {
        Tile t = new Tile(new Red(), new Circle());
        player.addTileToHand(t);

        assertEquals(t , player.getTile(1));
    }

    @Test
    public void testGetHandSize() throws Exception {
        player.addTilesToHand(tiles);
        assertEquals(4, player.getHandSize());
    }
}