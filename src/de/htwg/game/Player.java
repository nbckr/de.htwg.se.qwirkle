package de.htwg.game;

import java.util.ArrayList;

/**
 * Created by luluschi on 23.10.2015.
 */
public class Player {
    private String name;
    private int score;
    private ArrayList<Tile> hand;

    public Player(String name) {
        this.name = name;
        this.reset();
    }

    public String getName() {
        return this.name;
    }

    public int getScorte() {
        return this.score;
    }

    public ArrayList<Tile> getHand() {
        return this.hand;
    }

    private final void reset() {
        this.score = 0;
        this.hand = new ArrayList<Tile>();
    }


}
