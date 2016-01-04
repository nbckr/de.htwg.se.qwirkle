package de.htwg.qwirkle.model;

import java.awt.*;

/**
 * Created by niboecke on 30.10.2015.
 */

public class Grid {

    public static final int ROWS = 30;
    public static final int COLS = 30;

    private Tile[][] tiles;
    private int numRows;
    private int numCols;

    /**
     * Standard constructor calls constructor with standard fieldsize values
     */
    public Grid() {
        this(ROWS, COLS);
    }

    /**
     * Creates new Grid, all cells automatically initialized with null
     * @param row number of rows
     * @param col number of columns
     */
    public Grid(int row, int col) {
        create(row, col);
    }

    private void create(int row, int col) {
        if ((row < 10) || (col < 10) || (row > COLS) || (col > ROWS))
            throw new IllegalArgumentException("Number of rows / columns must be " +
                    "between 10 and " + COLS);

        tiles = new Tile[row][col];
        numRows = row;
        numCols = col;
    }

    /**
     * setTile adds a tile to an empty cell of the tiles
     * @param t Tile to put on the tiles
     * @param row position to specify row
     * @param col position to specify column
     * @return true if successful, false otherwise
     */
    public boolean setTile(Tile t, int row, int col) {

        if (tiles[row][col] != null)
            return false;

        tiles[row][col] = t;
        return true;
    }

    public boolean fieldIsEmpty(int row, int col) {
        return tiles[row][col] == null;
    }

    /**
     * getTile returns the tile of a cell on the tiles
     * @param row position to specify row
     * @param col position to specify column
     * @return Tile on the specified position or null if cell is empty
     */
    public Tile getTile(int row, int col) {
        return tiles[row][col];     // if empty, null is returned automatically
    }

    @Override
    public String toString() {

        // inizialise sb with "  " for left upper corner
        StringBuilder sb = new StringBuilder("   ");
        // write column numbers: [][col]
        for (int col = 0; col < numCols; col++) {
            sb.append(String.format("%02d ", col));
        }
        sb.append("\n");

        // write row numbers: [i][] and draw tiles
        for(int row = 0; row < numRows; row++) {
            sb.append(String.format("%02d ", row));
            for (int col = 0; col < numCols; col++) {
                if(tiles[row][col] != null) {
                    sb.append(tiles[row][col] + " ");
                } else {
                    sb.append("|| ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int getNumRows() {
        return numRows;
    }
    public int getNumCols() {
        return numCols;
    }
}