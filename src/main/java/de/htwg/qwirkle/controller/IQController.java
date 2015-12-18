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

    /**
     * @param t Tile to add
     * @param i position to specify row
     * @param j position to specify column
     * @return the points to add to player's score, or -1 if adding was impossible
     */
    int addTileToGrid(Tile t, int i, int j);

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
     * Reset the current player with the player next in line.
     */
    void nextPlayer();

    /**
     * Refills the current players hand with tiles from supply
     */
    void refillPlayer();

    /**
     * Returns the Tile on this position of the grid; the Tile remains on the grid though
     */
    Tile getTileReference(int row, int col);

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

    public enum State {
        NEXT,
        INITIALIZED
    }
}
