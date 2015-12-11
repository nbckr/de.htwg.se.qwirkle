package de.htwg.qwirkle.model;

/**
 * Created by niboecke on 30.10.2015.
 */

public class Grid {

    public static final int MAX_ROWS = 99;
    public static final int MAX_COLS = MAX_ROWS;

    private Tile[][] tiles;
    private int numRows;
    private int numCols;

    /**
     * Standard constructor calls constructor with standard fieldsize values
     */
    public Grid() {
        this(MAX_ROWS, MAX_COLS);
    }

    /**
     * Creates new Grid, all cells automatically initialized with null
     * @param i number of rows
     * @param j number of columns
     */
    public Grid(int i, int j) {

        if ((i < 10) || (j < 10) || (i > MAX_COLS) || (j > MAX_ROWS))
            throw new IllegalArgumentException("Number of rows / columns must be between 10 and " + MAX_COLS);

        tiles = new Tile[i][j];
        numRows = i;
        numCols = j;
    }

    /**
     * setTile adds a tile to an empty cell of the tiles
     * @param t Tile to put on the tiles
     * @param i position to specify row
     * @param j position to specify column
     * @return true if successful, false otherwise
     */
    public boolean setTile(Tile t, int i, int j) {

        if (tiles[i][j] != null)
            return false;

        tiles[i][j] = t;
        return true;
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
        // write column numbers: [][j]
        for (int j = 0; j < numCols; j++) {
            sb.append(String.format("%02d ", j));
        }
        sb.append("\n");
        // write row numbers: [i][] and draw tiles
        for(int i = 0; i < numRows; i++) {
            sb.append(String.format("%02d ", i));
            for (int j = 0; j < numCols; j++) {
                if(tiles[i][j] != null) {
                    sb.append(tiles[i][j] + " ");
                } else {
                    sb.append("|| ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}