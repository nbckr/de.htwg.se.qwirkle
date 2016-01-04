package de.htwg.qwirkle.controller.impl;

import de.htwg.qwirkle.controller.IQController;
import de.htwg.qwirkle.controller.IQControllerGui;
import de.htwg.qwirkle.model.Grid;
import de.htwg.qwirkle.model.Player;
import de.htwg.qwirkle.model.Supply;
import de.htwg.qwirkle.model.Tile;
import util.Constants;
import util.observer.QEvent;
import util.observer.Observable;

import java.util.ArrayList;
import java.util.List;

public class QController extends Observable implements IQController, IQControllerGui {

    private State state;
    private List<Player> players;
    private Player currentPlayer;
    private Supply supply;
    private Grid grid;
    private String statusMessage;

    public QController() {
        this.state = State.UNINIZIALIZED;
        create();
    }

    @Override
    public void init(List<Player> players) {
        assert (!players.isEmpty()) && (players.size() < 5);

        //print welcome
        this.statusMessage = Constants.WELCOME;
        notifyObservers(new QEvent(QEvent.Event.MESSAGE));

        this.players = (ArrayList) players;
        initPlayers();
        state = State.INITIALIZED;
        notifyObservers();
    }

    /**
     * Initializes the players. Adds tile and chooses the first player.
     */
    private void initPlayers() {
        // deal tiles
        for (Player player : this.players) {
            refillPlayer(player);
        }

        // first player starts; could be replaced by more complex algorithm
        this.currentPlayer = this.players.get(0);

        this.statusMessage = this.currentPlayer.getName() + " starts.";

        setState(State.PLAYING);
        notifyObservers();
    }

    @Override
    public boolean gridFieldIsEmpty(int row, int col) {
        return grid.fieldIsEmpty(row, col);
    }

    @Override
    public int addTileToGrid(Tile t, int row, int col) {
        int points = 42;
        // TODO: validate if playing here is even possible, return -1 if no

        grid.setTile(t, row, col);
        // TODO: how many points does the player get?

        this.statusMessage = "Still " + this.currentPlayer.getName() +"s turn";
        notifyObservers();
        return points;
    }

    @Override
    public int addSelectedTileToGrid(int row, int col) {
        return addTileToGrid(getSingleSelectedTile(), row, col);
    }


    @Override
    public Tile tradeTile(Tile oldTile) {
        assert !supply.isEmpty();
        Tile newTile = supply.getTile();
        supply.insertTile(oldTile);

        notifyObservers();
        return newTile;
    }

    @Override
    public List<Tile> tradeTiles(List<Tile> oldTiles) {
        List<Tile> newTiles = new ArrayList<>();
        for (Tile oldTile : oldTiles) {
            newTiles.add(tradeTile(oldTile));
        }
        return newTiles;
        // no notifyObservers(); ?
    }

    @Override
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    @Override
    public void nextPlayer(){
        int index = (this.players.indexOf(this.currentPlayer) + 1) % this.players.size();
        this.currentPlayer = this.players.get(index);
        this.statusMessage = this.currentPlayer.getName() + ", it's your turn!";
        notifyObservers(new QEvent(QEvent.Event.NEXTPLAYER));
    }

    @Override
    public void refillPlayer(Player player) {
        for (int i = player.getHandHandSize(); i < 6; i++) {
            addTileToHand(supply.getTile());
        }
        notifyObservers();
    }

    @Override
    public void refillPlayer() {
        refillPlayer(getCurrentPlayer());
    }

    @Override
    public Tile getTileFromGrid(int row, int col) {
        return this.grid.getTile(row, col);
    }

    @Override
    public Tile getTileFromPlayer(int index) {
        if (index >= getCurrentPlayer().getHandHandSize()) {
            return null;
        }
        return getCurrentPlayer().getTile(index);
    }

    @Override
    public void removeTileFromPlayer(int index) {
        if (index >= getCurrentPlayer().getHandHandSize()) {
            throw new IllegalArgumentException();
        }
        getCurrentPlayer().removeTile(index);
        notifyObservers();
    }

    @Override
    public void removeTilesFromPlayer(List<Tile> tiles) {
        getCurrentPlayer().removeTiles(tiles);
        notifyObservers();
    }

    @Override
    public void addTileToHand(Tile tile) {
        getCurrentPlayer().addTileToHand(tile);
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

    @Override
    public void create() {
        this.grid = new Grid();
        this.supply = new Supply();
        notifyObservers();
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
        if (isSelected && getState() == State.ADDTILES) {
            unselectAll();
        }
        tile.setSelected(isSelected);

        notifyObservers();
    }

    @Override
    public void selectTileToggle(Tile tile) {
        selectTile(tile, !tile.isSelected());
    }

    @Override
    public void unselectAll() {
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
        Tile selectedTile = null;
        for (Tile tile : getCurrentHand()) {
            if (tile.isSelected()) {
                selectedTile = tile;
                break;
            }
        }
        return selectedTile;
    }

    @Override
    public List<Tile> getSelectedTiles() {
        List<Tile> selectedTiles = new ArrayList<>();
        for (Tile tile : getCurrentHand()) {
            if (tile.isSelected()) {
                selectedTiles.add(tile);
            }
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
        removeTilesFromPlayer(oldTiles);
        unselectAll();
        List<Tile> newTiles = tradeTiles(oldTiles);
        getCurrentPlayer().addTilesToHand(newTiles);
        notifyObservers();
    }


    @Override
    public List<Tile> getCurrentHand() {
        return getCurrentPlayer().getHand();
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void setState(State state) {
        this.state = state;
        notifyObservers();
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }
}
