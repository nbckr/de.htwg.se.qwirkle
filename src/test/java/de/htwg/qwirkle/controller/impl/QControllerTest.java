package de.htwg.qwirkle.controller.impl;

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

    @Test
    public void testAddSelectedTileToTargetPosition() throws Exception {
        // TODO
    }

    @Test
    public void testTradeTiles() throws Exception {
        // TODO
    }

    @Test
    public void testRefillCurrentAndGoToNextPlayer() throws Exception {
        // TODO
    }

    @Test
    public void testRefillCurrentPlayer() throws Exception {
        // TODO
    }

    @Test
    public void testGetTileFromGrid() throws Exception {
        // TODO
    }

    @Test
    public void testGetTileFromPlayer() throws Exception {
        // TODO
    }

    @Test
    public void testRemoveTileFromCurrentPlayer() throws Exception {
        // TODO
    }

    @Test
    public void testRemoveTilesFromCurrentPlayer() throws Exception {
        // TODO
    }

    @Test
    public void testRemoveSelectedTilesFromCurrentPlayer() throws Exception {
        // TODO
    }

    @Test
    public void testAddTileToCurrentPlayer() throws Exception {
        // TODO
    }

    @Test
    public void testAddTilesToCurrentPlayer() throws Exception {
        // TODO
    }

    @Test
    public void testAddTileToPlayer() throws Exception {
        // TODO
    }

    @Test
    public void testSetStatusMessage() throws Exception {
        // TODO
    }

    @Test
    public void testCreate() throws Exception {
        // TODO
    }

    @Test
    public void testGetNumRows() throws Exception {
        // TODO
    }

    @Test
    public void testGetNumCols() throws Exception {
        // TODO
    }

    @Test
    public void testSelectTile() throws Exception {
        // TODO
    }

    @Test
    public void testSelectTileToggle() throws Exception {
        // TODO
    }

    @Test
    public void testUnselectAllTilesAtHand() throws Exception {
        // TODO
    }

    @Test
    public void testGetSingleSelectedTile() throws Exception {
        // TODO
    }

    @Test
    public void testGetSelectedTilesIndexes() throws Exception {
        // TODO
    }

    @Test
    public void testGetSelectedTiles() throws Exception {
        // TODO
    }

    @Test
    public void testGetNumberOfSelectedTiles() throws Exception {
        // TODO
    }

    @Test
    public void testTradeSelectedTiles() throws Exception {
        // TODO
    }

    @Test
    public void testGetTargetPositionOnGrid() throws Exception {
        // TODO
    }

    @Test
    public void testSetTargetPositionOnGrid() throws Exception {
        // TODO
    }

    @Test
    public void testUnselectTargetPositionOnGrid() throws Exception {
        // TODO
    }

    @Test
    public void testTargetPositionOnGridIsSet() throws Exception {
        // TODO
    }

    @Test
    public void testGetCurrentHand() throws Exception {
        // TODO
    }

    @Test
    public void testGetCurrentHandSize() throws Exception {
        // TODO
    }

    @Test
    public void testExit() throws Exception {
        // TODO
    }

    @Test
    public void testGetState() throws Exception {
        // TODO
    }

    @Test
    public void testSetState() throws Exception {
        // TODO
    }

    @Test
    public void testGetPlayers() throws Exception {
        // TODO
    }

    @Test
    public void testGetGuiMainFrame() throws Exception {
        // TODO
    }

    @Test
    public void testSetGuiMainFrame() throws Exception {
        // TODO
    }
}