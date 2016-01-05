package de.htwg.qwirkle.model;

import util.GridPosition;

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
     */
    public void setTile(Tile tile, int row, int col) {
        setTile(tile, new GridPosition(row, col));
    }

    public void setTile(Tile tile, GridPosition position) {
        if (tiles[position.row][position.col] != null) {
            throw new IllegalArgumentException();
        }
        tiles[position.row][position.col] = tile;
    }

    public boolean fieldIsEmpty(int row, int col) {
        return fieldIsEmpty(new GridPosition(row, col));
    }


    public boolean fieldIsEmpty(GridPosition position) {
        return tiles[position.row][position.col] == null;
    }


    /**
     * getTile returns the tile of a cell on the tiles
     * @return Tile on the specified position or null if cell is empty
     */
    public Tile getTile(GridPosition position) {
        return tiles[position.row][position.col];
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