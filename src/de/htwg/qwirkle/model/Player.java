package de.htwg.qwirkle.model;

import java.util.ArrayList;

/**
 * Created by luluschi on 23.10.2015.
 */
public class Player {

    // private static int playerCount = 0;
    private String name;
    private int score;
    private ArrayList<Tile> hand;

    /**
     * Standard constructor calls constructor with standard name: Player1, Player2, ...
     */
    public Player() {
        this(("Player"));
    }

    /**
     * Create new Player
     * @param name name of the player
     */
    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.hand = new ArrayList<Tile>(6);
    }

    /**
     * Adds a Tile to the player's hand
     * @param t Tile to add
     * @return true if successful, false otherwise (usually if Player already had six Tiles)
     */
    public boolean addTileToHand(Tile t) {
        if(this.hand.size() >= 6)
            return false;

        this.hand.add(t);
        return true;
    }

    /**
     * Adds a given number of points to the player's score
     * @param i Value to add to score. Has to be greater than 0
     */
    public void addScore(int i) {
        assert(i > 0);
        this.score += i;
    }

    /**
     * @return the name of the player
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the current score of the player
     */
    public int getScore() {
        return this.score;
    }

    /**
     * @return a copy of the player's hand
     */
    public ArrayList<Tile> getHand() {
        return new ArrayList<Tile>(this.hand);
    }

    @Override
    public String toString() {
        return "Player{" +
                "score=" + score +
                ", name='" + name + '\'' +
                '}';
    }

    public String printHand() {
        if (hand.size() == 0) {
            return "(empty)";
        }
        StringBuilder sb = new StringBuilder();
        for (Tile t : hand) {
            sb.append(t.toString() + " ");
        }
        return sb.toString();
    }
}
