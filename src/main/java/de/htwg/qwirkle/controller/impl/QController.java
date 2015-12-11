package de.htwg.qwirkle.controller.impl;

import de.htwg.qwirkle.model.Grid;
import de.htwg.qwirkle.model.Player;
import de.htwg.qwirkle.model.Supply;
import de.htwg.qwirkle.model.Tile;
import util.MessageUtil;
import util.observer.QEvent;
import util.observer.Observable;

import java.util.ArrayList;
import java.util.List;

public class QController extends Observable implements de.htwg.qwirkle.controller.IQController {

    private State state;

    private List<Player> players;
    private Player currentPlayer;
    private Supply supply;
    private Grid grid;
    private String statusMessage;

    /**
     * @param grid the grid for this game instance
     */
    public QController(Grid grid) {
        this.grid = grid;
        this.supply = new Supply();
        this.state = State.INITIALIZED;
    }

    @Override
    public void init(List<Player> players) {
        assert (!players.isEmpty())&&(players.size() < 5);

        //print welcome
        this.statusMessage = MessageUtil.WELCOME;
        notifyObservers(new QEvent(QEvent.Events.MESSAGE));

        this.players = (ArrayList) players;
        initPlayers();
    }

    /**
     * Initializes the players. Adds tile and chooses the start player
     */
    private void initPlayers() {
        // deal tiles
        for (int i = 0; i < 6; i++) {
            for (Player p : this.players) {
                p.addTileToHand(this.supply.getTile());
            }
        }

        this.currentPlayer = this.players.get(0);

        this.statusMessage = "Player " + this.currentPlayer.getName() + " starts.";
        this.state = State.NEXT;
    }


    @Override
    public int addTileToGrid(Tile t, int i, int j) {
        int points = 0;
        // TODO: validate if playing here is even possible, return -1 if no

        grid.setTile(t, i, j);
        // TODO: how many points does the player get?

        this.statusMessage = "Player " + this.currentPlayer.getName();
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
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    @Override
    public void nextPlayer(){
        int index = (this.players.indexOf(this.currentPlayer) + 1) % this.players.size();
        this.currentPlayer = this.players.get(index);
        this.statusMessage = "Player " + this.currentPlayer.getName() + " is next in line.";
    }

    @Override
    public void refillPlayer() {
        for (int i = this.currentPlayer.getHand().size(); i <= 6; i++) {
            this.currentPlayer.addTileToHand(this.supply.getTile());
        }
    }

    @Override
    public Tile getTileReference(int row, int col) {
        return this.grid.getTile(row, col);
    }

    @Override
    public String getGridString() {
        return grid.toString();
    }

    @Override
    public String getStatusMessage() {
        return statusMessage;
    }

}
