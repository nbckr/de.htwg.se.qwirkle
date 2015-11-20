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
    private ArrayList<Integer> buffer;

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
        this.buffer = new ArrayList<Integer>(6);
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

    public Tile getTileFromHand(int index) {
        Tile retval;

        if((index < 1) || (index > 6)) {
            return null;
        }
        retval = this.hand.get(index);
        this.hand.remove(index);
        return retval;
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

    public int evalPlayer() {
        ArrayList<Integer> buffer = new ArrayList<>(6);
        int greatValC, greatValS, greatValAll = -1;

        assert(this.hand.size() > 0);

        for (Tile t : this.hand) {
            // get eval number for current tile's color
            greatValC = evalPlayerC(t);
            //save intern buffer
            buffer = this.buffer;

            // get eval number for current tile's shape
            greatValS = evalPlayerS(t);

            // jump to next tile if greatValAll is greatest
            if((greatValC < greatValAll) && (greatValS < greatValAll)) {
                continue;
            }

            // switch to greatest val, current tile and if shape or color
            if(greatValC >= greatValS) {
                greatValAll = greatValC;
            } else {
                greatValAll = greatValS;
                //save buffer of ValS
                buffer = this.buffer;
            }
        }

        // write back buffer for end of eval
        this.buffer = buffer;

        return greatValAll;
    }

    private int evalPlayerC(Tile t) {
        int greatVal= 0;
        this.buffer = new ArrayList<>(6);

        for (Tile e : this.hand) {
            if(!(t.equals(e))) {
                if(t.getColor() == e.getColor()) {
                    greatVal++;
                    buffer.add(this.hand.indexOf(e));
                }
            }
        }

        return greatVal;
    }

    private int evalPlayerS(Tile t) {
        int greatVal= 0;
        this.buffer = new ArrayList<>(6);

        for (Tile e : this.hand) {
            if(!(t.equals(e))) {
                if(t.getShape() == e.getShape()) {
                    greatVal++;
                    buffer.add(this.hand.indexOf(e));
                }
            }
        }

        return greatVal;
    }

    public void addTileToBuffer(Tile t) {
        int index;

        if((index = this.hand.indexOf(t)) >= 0) {
            if(!(buffer.contains(index))) {
                this.buffer.add(index);
            }
        }
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
