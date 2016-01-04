package de.htwg.qwirkle.controller;

import de.htwg.qwirkle.controller.impl.QController;
import de.htwg.qwirkle.model.Player;
import de.htwg.qwirkle.model.Tile;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class QControllerTest extends TestCase {

    Tile tile;
    QController controller;
    ArrayList<Player> singlePlayerList;
    Player horst;

    @Before
    public void setUp() {
        controller =  new QController();
        tile = new Tile();
        singlePlayerList = new ArrayList<>();
        horst = new Player("Horst");
        singlePlayerList.add(horst);
        controller.init(singlePlayerList);
    }

    @Test
    public void testInit() throws Exception {
        assertNotNull(controller.getStatusMessage());
    }

    @Test
    public void testAddTileToGrid() throws Exception {
        controller.addTileToGrid(tile, 1, 1);
        assertEquals(tile, controller.getTileFromGrid(1, 1));
    }

    @Test
    public void testTradeTile() throws Exception {
        assertNotNull(controller.tradeTile(tile));
    }

    @Test
    public void testGetCurrentPlayer() throws Exception {
        assertEquals(horst, controller.getCurrentPlayer());
    }

    @Test
    public void testNextPlayer() throws Exception {
        controller.refillCurrentAndGoToNextPlayer();
    }

    @Test
    public void testRefillPlayer() throws Exception {
        for(int i = 0; i < 5; i++) {
            //controller.getTileFromPlayer(1);
            //controller.getCurrentPlayer().removeTile(1);
            controller.removeTileFromCurrentPlayer(1);
        }
        controller.refillCurrentPlayer();
        assertEquals(6, controller.getCurrentHandSize());
    }

    @Test
    public void testGetGridString() throws Exception {
        controller.getGridString();
    }

    @Test
    public void testGetStatusMessage() throws Exception {
        controller.getStatusMessage();
    }

    @Test
    public void testgetTileReference() throws Exception {
        controller.addTileToGrid(tile, 1, 1);
        assertEquals(tile, controller.getTileFromGrid(1, 1));
    }
}