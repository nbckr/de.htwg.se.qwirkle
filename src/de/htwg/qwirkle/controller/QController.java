package de.htwg.qwirkle.controller;

import de.htwg.qwirkle.model.Grid;
import de.htwg.qwirkle.model.Player;
import de.htwg.qwirkle.model.Supply;
import de.htwg.qwirkle.model.Tile;
import util.MessageUtil;
import util.observer.Observable;

import java.util.ArrayList;
import java.util.TreeMap;

public class QController extends Observable {

    private ArrayList<Player> players;      // TODO: cyclic list?
    private Player currentPlayer;
    private Supply supply;
    private Grid grid;
    private String statusMessage;
    private int rounds;

    /**
     * @param grid the grid for this game instance
     * @param numPlayers the nuber of player
     */
    public QController(Grid grid, int numPlayers) {
        this.grid = grid;
        this.supply = new Supply();
        this.statusMessage = MessageUtil.WELCOME;
        this.rounds = 0;

        this.players = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            String name = "Player " + i;
            players.add(new Player(name));
        }
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

    public String getGridString() {
        return grid.toString();
    }

    public String getStatusMessage() {
        return statusMessage;
    }
}
