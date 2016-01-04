package de.htwg.qwirkle.controller;

import de.htwg.qwirkle.model.Player;
import de.htwg.qwirkle.model.Tile;
import util.observer.IObservable;

import java.util.List;

/**
 * Created by niels on 11.12.2015.
 */
public interface IQController extends IObservable {
    /**
     * @param players List with all players for the game
     */
    void init(List<Player> players);

    boolean gridFieldIsEmpty(int row, int col);

    /**
     * @param t Tile to add
     * @param row position to specify row
     * @param col position to specify column
     * @return the points to add to player's score, or -1 if adding was impossible
     */
    int addTileToGrid(Tile t, int row, int col);

    /**
     * @param oldTiles old Tiles to trade in
     * @returns new Tiles to replace the old one
     */
    List<Tile> tradeTiles(List<Tile> oldTiles);

    /**
     * call addTileToGrid when exactly one Tile is selected at Hand and add this one.
     */
    int addSelectedTileToGrid(int row, int col);

    /**
     * @param oldTile old Tile to trade in
     * @return new Tile to replace the old one
     */
    Tile tradeTile(Tile oldTile);

    /**
     * Returns the player next in line.
     * @return the player next in line
     */
    Player getCurrentPlayer();

    /**
     * Returns the hand of the current player.
     * @return hand of the current player
     */
    List<Tile> getCurrentHand();

    /**
     * Reset the current player with the player next in line.
     */
    void nextPlayer();

    void refillPlayer(Player player);

    /**
     * Refills the current players hand with tiles from supply
     */
    void refillPlayer();

    /**
     * Returns the Tile on this position of the grid; the Tile remains on the grid though.
     */
    Tile getTileFromGrid(int row, int col);

    /**
     * Returns the Tile on this position the current player's hand; the Tile remains
     * there though.
     */
    Tile getTileFromPlayer(int position);

    void removeTileFromPlayer(int index);

    void removeTilesFromPlayer(List<Tile> tiles);

    void addTileToHand(Tile tile);

    /**
     * Returns the grid as text for tui
     * @return grid as string
     */
    String getGridString();

    /**
     * Returns the current status message
     * @return status message
     */
    String getStatusMessage();

    void create();

    int getNumRows();

    int getNumCols();

    State getState();

    void setState(State state);

    void selectTile(Tile tile, boolean isSelected);

    void selectTileToggle(Tile tile);

    void unselectAll();

    Tile getSingleSelectedTile();

    List<Tile> getSelectedTiles();

    int getNumberOfSelectedTiles();

    void tradeSelectedTiles();

    List<Player> getPlayers();

    void exit();

    public enum State {
        UNINIZIALIZED,          // no game started
        INITIALIZED,    // players names were given
        PLAYING,
        ADDTILES,
        TRADETILES
    }
}
