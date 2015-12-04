package de.htwg.qwirkle.controller;

import de.htwg.qwirkle.model.Grid;
import de.htwg.qwirkle.model.Player;
import de.htwg.qwirkle.model.Supply;
import de.htwg.qwirkle.model.Tile;
import util.MessageUtil;
import util.observer.QEvent;
import util.observer.Observable;

import java.util.ArrayList;

public class QController extends Observable {

    private State state;

    private ArrayList<Player> players;
    private Player currentPlayer;
    private Supply supply;
    private Grid grid;
    private String statusMessage;
    private int rounds;

    /**
     * @param grid the grid for this game instance
     */
    public QController(Grid grid) {
        this.grid = grid;
        this.supply = new Supply();
        this.rounds = 0;



        this.state = State.INITIALIZED;
    }

    /**
     * @param players List with all players for the game
     */
    public void init(ArrayList<Player> players) {
        assert((players.size() > 0)&&(players.size() < 5));

        //print welcome
        this.statusMessage = MessageUtil.WELCOME;
        notifyObservers(new QEvent(QEvent.Events.message));

        this.players = players;
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


    /**
     * @param t Tile to add
     * @param i position to specify row
     * @param j position to specify column
     * @return the points to add to player's score, or -1 if adding was impossible
     */
    public int addTileToGrid(Tile t, int i, int j) {
        int points = 0;
        // TODO: validate if playing here is even possible, return -1 if no

        grid.setTile(t, i, j);
        // TODO: how many points does the player get?

        this.statusMessage = "Player " + this.currentPlayer.getName();
        notifyObservers();
        return points;
    }


    /**
     * @param oldTile old Tile to trade in
     * @return new Tile to replace the old one
     */
    public Tile tradeTile(Tile oldTile) {
        assert(!supply.isEmpty());
        Tile newTile = supply.getTile();
        supply.insertTile(oldTile);

        notifyObservers();
        return newTile;
    }

    /**
     * Returns the player next in line.
     * @return the player next in line
     */
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * Reset the current player with the player next in line.
     */
    public void nextPlayer(){
        int index = (this.players.indexOf(this.currentPlayer) + 1) % this.players.size();
        this.currentPlayer = this.players.get(index);
        this.statusMessage = "Player " + this.currentPlayer.getName() + " is next in line.";
    }

    /**
     * Refills the current players hand with tiles from supply
     */
    public void refillPlayer() {
        for (int i = this.currentPlayer.getHand().size(); i <= 6; i++) {
            this.currentPlayer.addTileToHand(this.supply.getTile());
        }
    }

    /**
     * Returns the Tile on this position of the grid; the Tile remains on the grid though
     */
    public Tile getTileReference(int row, int col) {
        return this.grid.getTile(row, col);
    }

    /**
     * Returns the grid as text for tui
     * @return grid as string
     */
    public String getGridString() {
        return grid.toString();
    }

    /**
     * Returns the current status message
     * @return status message
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    private enum State {
        NEXT,
        INITIALIZED
    }
}
