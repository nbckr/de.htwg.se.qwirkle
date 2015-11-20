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

    private ArrayList<Player> players;      // TODO: cyclic list?
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



        this.state = State.initialized;
    }

    public void init(ArrayList<Player> players) {
        assert((players.size() > 0)&&(players.size() < 5));

        //print welcome
        this.statusMessage = MessageUtil.WELCOME;
        notifyObservers(new QEvent(QEvent.Events.message));

        this.players = players;
        initPlayers();
    }

    private void initPlayers() {
        // deal tiles
        for (int i = 0; i < 6; i++) {
            for (Player p : this.players) {
                p.addTileToHand(this.supply.getTile());
            }
        }

        // choose first player
        /*int vOld = -1, vNew;

        for (Player p : this.players) {
            vNew = p.evalPlayer();

            if(vNew > vOld) {
                vOld = vNew;
                this.currentPlayer = p;
            }
        }
        */

        this.currentPlayer = this.players.get(0);

        this.statusMessage = "Player " + this.currentPlayer.getName() + " starts.";
        this.state = State.next;
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
     * @param t_old old Tile to trade in
     * @return new Tile to replace the old one
     */
    public Tile tradeTile(Tile t_old) {
        assert(!supply.isEmpty());
        Tile t_new = supply.getTile();
        supply.insertTile(t_old);

        notifyObservers();
        return t_new;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public void nextPlayer(){
        int index = (this.players.indexOf(this.currentPlayer) + 1) % this.players.size();
        this.currentPlayer = this.players.get(index);
        this.statusMessage = "Player " + this.currentPlayer.getName() + " is next in line.";
    }

    public void refillPlayer() {
        for (int i = this.currentPlayer.getHand().size(); i <= 6; i++) {
            this.currentPlayer.addTileToHand(this.supply.getTile());
        }
    }

    public String getGridString() {
        return grid.toString();
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    private enum State {
        next,
        initialized
    }
}
