package de.htwg.qwirkle.controller.impl;

import de.htwg.qwirkle.controller.IQController;
import de.htwg.qwirkle.model.Grid;
import org.junit.Before;
import org.junit.Test;
import de.htwg.qwirkle.model.Player;
import de.htwg.qwirkle.model.Tile;

import static org.junit.Assert.*;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class QControllerTest {

    Tile tile;
    QController controller;
    ArrayList<Player> singlePlayerList;
    Player horst, kalle;

    @Before
    public void setUp() {
        controller =  new QController();
        tile = new Tile();
        singlePlayerList = new ArrayList<Player>();
        horst = new Player("Horst");
        kalle = new Player("Kalle");
        singlePlayerList.add(horst);
        singlePlayerList.add(kalle);
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
        Grid.Position pos = new Grid.Position(2,2);
        Tile t = horst.getTile(1);
        controller.selectTile(t, true);
        controller.setTargetPositionOnGrid(pos);

        controller.addSelectedTileToTargetPosition();

        assertEquals(t, controller.getTileFromGrid(pos));
    }

    @Test
    public void testTradeTiles() throws Exception {
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        tiles.add(horst.getTile(1));
        tiles.add(horst.getTile(2));
        tiles.add(horst.getTile(3));

        ArrayList<Tile> newt = (ArrayList<Tile>) controller.tradeTiles(tiles);

        assertEquals(3, newt.size());
    }

    @Test
    public void testRefillCurrentAndGoToNextPlayer() throws Exception {
        Player one = controller.getCurrentPlayer();

        one.removeTile(1);
        assertEquals(5, one.getHandSize());

        controller.refillCurrentAndGoToNextPlayer();

        assertEquals(6, one.getHandSize());
        assertNotEquals(one, controller.getCurrentPlayer());
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
        Tile t = controller.getTileFromPlayer(8);
        assertEquals(null, t);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveTileFromCurrentPlayer() throws Exception {
        controller.removeTileFromCurrentPlayer(8);
    }

    @Test
    public void testRemoveTilesFromCurrentPlayer() throws Exception {
        ArrayList<Integer> ind = new ArrayList<Integer>();
        ind.add(1);
        ind.add(2);
        ind.add(3);

        controller.removeTilesFromCurrentPlayer(ind);

        assertEquals(3, controller.getCurrentHandSize());
    }

    @Test
    public void testRemoveSelectedTilesFromCurrentPlayer() throws Exception {
        Player p = controller.getCurrentPlayer();

        controller.selectTile(p.getTile(1), true);
        controller.selectTile(p.getTile(2), true);

        controller.removeSelectedTilesFromCurrentPlayer();

        assertEquals(4, controller.getCurrentHandSize());
    }

    @Test
    public void testAddTileToCurrentPlayer() throws Exception {

    }

    @Test
    public void testAddTilesToCurrentPlayer() throws Exception {
        Player p = controller.getCurrentPlayer();
        ArrayList<Tile> tiles = new ArrayList<Tile>();

        tiles.add(p.getTile(1));
        tiles.add(p.getTile(2));

        p.removeTile(1);
        p.removeTile(2);

        assertEquals(4, p.getHandSize());

        controller.addTilesToCurrentPlayer(tiles);
        assertEquals(6, p.getHandSize());
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
        Tile t = horst.getTile(1);

        controller.selectTileToggle(t);

        assertEquals(1, controller.getNumberOfSelectedTiles());
    }

    @Test
    public void testUnselectAllTilesAtHand() throws Exception {
        // TODO
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSingleSelectedTile() throws Exception {
        controller.selectTile(horst.getTile(1), true);
        controller.selectTile(horst.getTile(2), true);

        controller.getSingleSelectedTile();
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
        controller.selectTile(horst.getTile(1), true);
        controller.selectTile(horst.getTile(2), true);
        controller.selectTile(horst.getTile(3), true);

        controller.tradeSelectedTiles();

        assertEquals(6, horst.getHandSize());
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

    @Test
    public void testGetRemainingTilesInSupply() throws Exception {
        // TODO
    }

    @Test
    public void testSupplyIsEmpty() throws Exception {
        // TODO
    }

    @Test
    public void testValidateAdding() throws Exception {
        // TODO
    }

    @Test
    public void testGetNumberOfTilesOnGrid() throws Exception {
        // TODO
    }
}