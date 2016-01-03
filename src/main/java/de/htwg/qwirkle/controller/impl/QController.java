package de.htwg.qwirkle.controller.impl;

import de.htwg.qwirkle.controller.IQController;
import de.htwg.qwirkle.controller.IQControllerGui;
import de.htwg.qwirkle.model.Grid;
import de.htwg.qwirkle.model.Player;
import de.htwg.qwirkle.model.Supply;
import de.htwg.qwirkle.model.Tile;
import util.MessageUtil;
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
        this.statusMessage = MessageUtil.WELCOME;
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
        for (int i = 0; i < 6; i++) {
            for (Player p : this.players) {
                p.addTileToHand(this.supply.getTile());
            }
        }

        // first player starts; could be replaced by more complex algorithm
        this.currentPlayer = this.players.get(0);

        this.statusMessage = this.currentPlayer.getName() + " starts.";

        setState(State.PLAYING);
        notifyObservers();
    }


    @Override
    public int addTileToGrid(Tile t, int i, int j) {
        int points = 42;
        // TODO: validate if playing here is even possible, return -1 if no

        grid.setTile(t, i, j);
        // TODO: how many points does the player get?

        this.statusMessage = "Still " + this.currentPlayer.getName() +"s turn";
        notifyObservers();
        return points;
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
    }

    @Override
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    @Override
    public void nextPlayer(){
        int index = (this.players.indexOf(this.currentPlayer) + 1) % this.players.size();
        this.currentPlayer = this.players.get(index);
        this.statusMessage = this.currentPlayer.getName() + " is next in line.";
        notifyObservers(new QEvent(QEvent.Event.NEXTPLAYER));
    }

    @Override
    public void refillPlayer() {
        for (int i = this.currentPlayer.getHand().size(); i <= 6; i++) {
            this.currentPlayer.addTileToHand(this.supply.getTile());
        }
        notifyObservers();
    }

    @Override
    public Tile peekTileFromGrid(int row, int col) {
        return this.grid.getTile(row, col);
    }

    @Override
    public Tile peekTileFromHand(int position) {
        if (position >= getCurrentPlayer().getHand().size()) {
            return null;
        }
        return getCurrentPlayer().getHand().get(position);
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
    }
}
