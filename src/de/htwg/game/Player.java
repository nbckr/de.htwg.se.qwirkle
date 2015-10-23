package de.htwg.game;

import java.util.ArrayList;

/**
 * Created by luluschi on 23.10.2015.
 */
public class Player {
    private String name;
    private int score;
    private ArrayList<Tile> hand;

    /**
     *
     * @param name The name of the player
     */
    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.hand = new ArrayList<Tile>();
    }

    /**
     * Adds a Tile to the player's hand
     * @param t Tile to add
     */
    public void addTileToHand(Tile t) {
        if(this.hand.size() < 6) {
            this.hand.add(t);
        }
    }

    /**
     * Adds a given number of points to the player's score
     * @param i Value to add to score. Has to be greater than 0
     */
    public void addScore(int i) {
        if (i > 0) {
            this.score += i;
        }
    }

    /**
     *
     * @return the name of the player
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @return the current score of the player
     */
    public int getScore() {
        return this.score;
    }

    /**
     *
     * @return a copy of the player's hand
     */
    public ArrayList<Tile> getHand() {
        return new ArrayList<Tile>(this.hand);
    }
}
