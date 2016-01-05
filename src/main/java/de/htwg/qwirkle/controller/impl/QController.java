package de.htwg.qwirkle.controller.impl;

import de.htwg.qwirkle.aview.gui.QFrame;
import de.htwg.qwirkle.controller.IQController;
import de.htwg.qwirkle.controller.IQControllerGui;
import de.htwg.qwirkle.model.Grid;
import de.htwg.qwirkle.model.Player;
import de.htwg.qwirkle.model.Supply;
import de.htwg.qwirkle.model.Tile;
import util.Constants;
import util.GridPosition;
import util.observer.Observable;

import java.util.ArrayList;
import java.util.List;

public class QController extends Observable implements IQController, IQControllerGui {

    private State state;
    private List<Player> players;
    private Player currentPlayer;
    private Supply supply;
    private Grid grid;
    private GridPosition targetPositionOnGrid;
    private String statusMessage;
    private QFrame guiMainFrame;

    public QController() {
        create();
    }

    @Override
    public void init(List<Player> players) {
        assert (!players.isEmpty()) && (players.size() < 5);

        //print welcome
        setStatusMessage(Constants.WELCOME);

        this.players = (ArrayList) players;
        initPlayers();
        setState(State.INITIALIZED);
    }

    /**
     * Initializes the players. Adds tile and chooses the first player.
     */
    private void initPlayers() {
        for (Player player : players) {
            refillPlayer(player);
        }

        // first player starts; could be replaced by more complex algorithm
        this.currentPlayer = players.get(0);

        setStatusMessage(currentPlayer.getName() + " starts.");
        setState(State.CHOOSE_ACTION);
    }

    /**
     * Most basic method to add a tile to the grid. Note that by setting
     * the status message, this method also notifies observers.
     */
    @Override
    public void addTileToGrid(Tile tile, GridPosition position) {
        int points = 42;

        grid.setTile(tile, position);
        // compute score points...
        getCurrentPlayer().addScore(points);

        setStatusMessage("Still " + this.currentPlayer.getName() +"s turn");
    }

    @Override
    public void addTileToGrid(Tile tile, int row, int col) {
        addTileToGrid(tile, new GridPosition(row, col));
    }

    @Override
    public void addSelectedTileToTargetPosition() {
        assert getNumberOfSelectedTiles() == 1 && targetPositionOnGridIsSet();
        addTileToGrid(getSingleSelectedTile(), getTargetPositionOnGrid());
    }

    @Override
    public Tile tradeTile(Tile oldTile) {
        assert !supply.isEmpty();
        Tile newTile = supply.getTile();
        supply.insertTile(oldTile);
        return newTile;
    }

    @Override
    public List<Tile> tradeTiles(List<Tile> oldTiles) {
        List<Tile> newTiles = new ArrayList<>();
        for (Tile oldTile : oldTiles) {
            newTiles.add(tradeTile(oldTile));
        }
        return newTiles;
    }

    @Override
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public void refillCurrentAndGoToNextPlayer(){
        refillCurrentPlayer();

        int index = (players.indexOf(currentPlayer) + 1) % players.size();
        currentPlayer = players.get(index);

        setStatusMessage(currentPlayer.getName() + ", it's your turn!");
        setState(State.CHOOSE_ACTION);
    }

    @Override
    public void refillPlayer(Player player) {
        for (int i = player.getHandSize(); i < 6; i++) {
            addTileToPlayer(supply.getTile(), player);
        }
    }

    @Override
    public void refillCurrentPlayer() {
        refillPlayer(getCurrentPlayer());
    }

    @Override
    public Tile getTileFromGrid(GridPosition position) {
        return this.grid.getTile(position);
    }

    @Override
    public Tile getTileFromGrid(int row, int col) {
        return getTileFromGrid(new GridPosition(row, col));
    }

    @Override
    public Tile getTileFromPlayer(int index) {
        if (index > getCurrentPlayer().getHandSize()) {
            return null;
        }
        return getCurrentPlayer().getTile(index);
    }

    /**
     * Most basic method to remove tiles from current player.
     * Not that this method notifies observers.
     */
    @Override
    public void removeTileFromCurrentPlayer(int index) {
        if (index >= getCurrentPlayer().getHandSize()) {
            throw new IllegalArgumentException();
        }
        getCurrentPlayer().removeTile(index);
        notifyObservers();
    }

    @Override
    public void removeTilesFromCurrentPlayer(List<Integer> indexes) {
        for (int i : indexes) {
            removeTileFromCurrentPlayer(i);
        }
    }

    @Override
    public void removeSelectedTilesFromCurrentPlayer() {
        List<Integer> selectedTilesIndexes = getSelectedTilesIndexes();
        unselectAllTilesAtHand();
        removeTilesFromCurrentPlayer(selectedTilesIndexes);
    }

    @Override
    public void addTileToCurrentPlayer(Tile tile) {
        addTileToPlayer(tile, getCurrentPlayer());
    }

    @Override
    public void addTilesToCurrentPlayer(List<Tile> tiles) {
        for (Tile tile : tiles) {
            addTileToCurrentPlayer(tile);
        }
    }

    /**
     * Most basic method to add a tile to the player. Note that this method
     * notifies observers, so the caller probably doesn't have to do that.
     */
    @Override
    public void addTileToPlayer(Tile tile, Player player) {
        player.addTileToHand(tile);
        notifyObservers();
    }

    @Override
    public String getGridString() {
        return grid.toString();
    }

    @Override
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * Set the status message. Note that this method notifies observers,
     * so the caller of the method probably doesn't have to do that.
     * @param message new status message
     */
    @Override
    public void setStatusMessage(String message) {
        statusMessage = message;
        notifyObservers();
    }


    @Override
    public void create() {
        this.grid = new Grid();
        this.supply = new Supply();
        this.setState(State.UNINITIALIZED);
    }

    @Override
    public int getNumRows() {
        return grid.getNumRows();
    }

    @Override
    public int getNumCols() {
        return grid.getNumCols();
    }

    @Override
    public void selectTile(Tile tile, boolean isSelected) {
        // if in ADDTILES state, make sure only one is selected
        if (isSelected && getState() == State.ADDTILES) {
            unselectAllTilesAtHand();
        }
        tile.setSelected(isSelected);

        notifyObservers();
    }

    @Override
    public void selectTileToggle(Tile tile) {
        selectTile(tile, !tile.isSelected());
    }

    @Override
    public void unselectAllTilesAtHand() {
        for (Tile tile : getCurrentHand()) {
            tile.setSelected(false);
        }
        notifyObservers();
    }

    @Override
    public Tile getSingleSelectedTile() {
        if (getNumberOfSelectedTiles() != 1) {
            throw new IllegalArgumentException();
        }
        for (Tile tile : getCurrentHand()) {
            if (tile.isSelected()) {
                return tile;
            }
        }
        // if this code runs, something went wrong
        throw new IllegalArgumentException();
    }

    @Override
    public List<Integer> getSelectedTilesIndexes() {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 1; i <= getCurrentHandSize(); i++) {
            if (getTileFromPlayer(i).isSelected()) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    @Override
    public List<Tile> getSelectedTiles() {
        List<Integer> indexes = getSelectedTilesIndexes();
        List<Tile> selectedTiles = new ArrayList<>();
        for (int i : indexes) {
            selectedTiles.add(getTileFromPlayer(i));
        }
        return selectedTiles;
    }

    @Override
    public int getNumberOfSelectedTiles() {
        return getSelectedTiles().size();
    }

    @Override
    public void tradeSelectedTiles() {
        List<Tile> oldTiles = getSelectedTiles();
        removeSelectedTilesFromCurrentPlayer();
        unselectAllTilesAtHand();
        List<Tile> newTiles = tradeTiles(oldTiles);
        addTilesToCurrentPlayer(newTiles);
        notifyObservers();
    }

    @Override
    public GridPosition getTargetPositionOnGrid() {
        return targetPositionOnGrid;
    }

    @Override
    public void setTargetPositionOnGrid(GridPosition position) {
        this.targetPositionOnGrid = position;
        notifyObservers();
    }

    @Override
    public void unselectTargetPositionOnGrid() {
        targetPositionOnGrid = null;
        notifyObservers();
    }

    @Override
    public boolean targetPositionOnGridIsSet() {
        return targetPositionOnGrid != null;
    }

    @Override
    public List<Tile> getCurrentHand() {
        return getCurrentPlayer().getHand();
    }

    @Override
    public int getCurrentHandSize() {
        return getCurrentHand().size();
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    @Override
    public State getState() {
        return state;
    }

    /**
     * Set the global state. Note that this method notifies observers,
     * so the caller of the method probably doesn't have to do that.
     * @param state new state
     */
    @Override
    public void setState(State state) {
        this.state = state;
        notifyObservers();
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public QFrame getGuiMainFrame() {
        return guiMainFrame;
    }

    @Override
    public void setGuiMainFrame(QFrame qFrame) {
        this.guiMainFrame = qFrame;
    }
}
