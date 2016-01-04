package de.htwg.qwirkle.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luluschi on 23.10.2015.
 */
public class Player {

    private String name;
    private int score;
    private List<Tile> hand;

    /**
     * Standard constructor calls constructor with standard name: Player1, Player2, ...
     */
    public Player() {
        this("Player");
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
     */
    public void addTileToHand(Tile t) {
        if (hand.size() > 6) {
            throw new IllegalArgumentException();
        }

        hand.add(t);
    }

    public void addTilesToHand(List<Tile> tiles) {
        if (this.hand.size() + tiles.size() > 6) {
            throw new IllegalArgumentException();
        }
        this.hand.addAll(tiles);
    }

    public void removeTile(int index) {
        if (index < 1 || index > getHandHandSize()) {
            throw new IllegalArgumentException();
        }
        hand.remove(index - 1);
    }

    public void removeTiles(List<Tile> tiles) {
        if (!hand.containsAll(tiles)) {
            throw new IllegalArgumentException();
        }
        hand.removeAll(tiles);
    }

    public Tile getTile(int index) {
        if((index < 1) || (index > getHandHandSize())) {
            return null;
        }
        Tile tile = hand.get(index - 1);
        return tile;
    }

    /**
     * Adds a given number of points to the player's score
     * @param i Value to add to score. Has to be greater than 0
     */
    public void addScore(int i) {
        assert i > 0;
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
    public List<Tile> getHand() {
        return new ArrayList<Tile>(this.hand);
    }

    public int getHandHandSize() {
        return hand.size();
    }

    @Override
    public String toString() {
        return "Player{" +
                "score=" + score +
                ", name='" + name + '\'' +
                '}';
    }

    public String printHand() {
        if (hand.isEmpty()) {
            return "(empty)";
        }
        StringBuilder sb = new StringBuilder();
        for (Tile t : hand) {
            sb.append(t.toString() + " ");
        }
        return sb.toString();
    }
}
