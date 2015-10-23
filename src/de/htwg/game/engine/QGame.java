package de.htwg.game.engine;

import de.htwg.game.data.Supply;
import de.htwg.game.data.Tile;

/**
 * QGame is the main game engine. It runs at all times and controls all other parts of the game.
 * Created by niels on 23.10.2015.
 */
public class QGame {

    protected Tile[][] board;
    protected Supply supply;
    private int rounds;

    public static void main(String[] args) {
        System.out.println("test");
    }

    private void init() {
        board = new Tile[101][101];
        supply = new Supply();
        rounds = 0;

        // create Players

        // each Player draws 6 Tiles

        // determine who starts

        // auto-play first round

    }

}
