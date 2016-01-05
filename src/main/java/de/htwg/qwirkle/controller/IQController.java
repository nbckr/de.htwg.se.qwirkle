package de.htwg.qwirkle.controller;

import de.htwg.qwirkle.model.Player;
import de.htwg.qwirkle.model.Tile;
import util.GridPosition;
import util.observer.IObservable;

import java.util.List;

public interface IQController extends IObservable {
    /**
     * @param players List with all players for the game
     */
    void init(List<Player> players);

    boolean gridFieldIsEmpty(int row, int col);

    void addTileToGrid(Tile t, GridPosition position);

    /**
     * @param t Tile to add
     * @param row position to specify row
     * @param col position to specify column
     */
    void addTileToGrid(Tile t, int row, int col);

    /**
     * @param oldTiles old Tiles to trade in
     * @return new Tiles to replace the old one
     */
    List<Tile> tradeTiles(List<Tile> oldTiles);

    /**
     * call addTileToGrid when exactly one Tile is selected at Hand and add this one.
     */
    void addSelectedTileToGrid(int row, int col);

    void addSelectedTileToTargetPosition();

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

    GridPosition getTargetPositionOnGrid();

    void setTargetPositionOnGrid(GridPosition target);

    void unselectTargetPositionOnGrid();

    boolean targetPositionOnGridIsSet();

    /**
     * Returns the hand of the current player.
     * @return hand of the current player
     */
    List<Tile> getCurrentHand();

    /**
     * Reset the current player with the player next in line.
     */
    void refillCurrentAndGoToNextPlayer();

    void refillPlayer(Player player);

    /**
     * Refills the current players hand with tiles from supply
     */
    void refillCurrentPlayer();

    Tile getTileFromGrid(GridPosition position);

    /**
     * Returns the Tile on this position of the grid; the Tile remains on the grid though.
     */
    Tile getTileFromGrid(int row, int col);

    /**
     * Returns the Tile on this position the current player's hand; the Tile remains
     * there though.
     */
    Tile getTileFromPlayer(int position);

    void removeTileFromCurrentPlayer(int index);

    void removeTilesFromCurrentPlayer(List<Tile> tiles);

    void removeSelectedTilesFromCurrentPlayer();

    void addTileToCurrentPlayer(Tile tile);

    void addTilesToCurrentPlayer(List<Tile> tiles);

    void addTileToPlayer(Tile tile, Player player);

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

    void setStatusMessage(String message);

    void create();

    int getNumRows();

    int getNumCols();

    State getState();

    void setState(State state);

    void selectTile(Tile tile, boolean isSelected);

    void selectTileToggle(Tile tile);

    void unselectAllTilesAtHand();

    Tile getSingleSelectedTile();

    List<Tile> getSelectedTiles();

    int getNumberOfSelectedTiles();

    void tradeSelectedTiles();

    List<Player> getPlayers();

    int getCurrentHandSize();

    void exit();

    enum State {
        UNINIZIALIZED,          // no game started
        INITIALIZED,    // players names were given
        CHOOSE_ACTION,
        ADDTILES,
        TRADETILES
    }
}
