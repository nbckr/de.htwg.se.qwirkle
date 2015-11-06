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

    private ArrayList<Player> players;
    private Supply supply;
    private Grid grid;
    private String statusMessage;

    public QController(Grid grid) {
        this.grid = grid;
        this.supply = new Supply();
        this.players = null;        // TODO: init players
        this.statusMessage = MessageUtil.WELCOME;
    }

    /**
     * @param t Tile to add
     * @param i position to specify row
     * @param j position to specify column
     * @return the points to add to player's score
     */
    public int addTileToGrid(Tile t, int i, int j) {
        return 0;
    }

    /**
     * @param t old Tile to trade in
     * @return new Tile to replace the old one
     */
    public Tile tradeTile(Tile t) {
        return null;
    }

    public String getGridString() {
        return grid.toString();
    }

    public String getStatusMessage() {
        return statusMessage;
    }
}
