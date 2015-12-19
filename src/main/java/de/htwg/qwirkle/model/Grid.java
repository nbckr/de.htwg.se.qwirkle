package de.htwg.qwirkle.model;

/**
 * Created by niboecke on 30.10.2015.
 */

public class Grid {

    public static final int MAX_ROWS = 60;
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
     * @param rows number of rows
     * @param cols number of columns
     */
    public Grid(int rows, int cols) {

        if ((rows < 10) || (cols < 10) || (rows > MAX_COLS) || (cols > MAX_ROWS))
            throw new IllegalArgumentException("Number of rows / columns must be between 10 and " + MAX_COLS);

        tiles = new Tile[rows][cols];
        numRows = rows;
        numCols = cols;
        initEmptyCells();
    }

    /**
     * Put "empty" Tile objects on each cell
     */
    private void initEmptyCells() {

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                setTile(new Tile(), row, col);
            }

        }
    }

    /**
     * setTile adds a tile to an empty cell of the tiles
     * @param t Tile to put on the tiles
     * @param row position to specify row
     * @param col position to specify column
     * @return true if successful, false otherwise
     */
    public boolean setTile(Tile t, int row, int col) {

        // adding tile fails if there is already a tile on the position and it' not
        // the "undefined" tile
        if (tiles[row][col] != null && !"undefined".equals(tiles[row][col].toString())) {
            return false;
        }

        tiles[row][col] = t;
        return true;
    }

    /**
     * getTile returns the tile of a cell on the tiles
     * @param row position to specify row
     * @param col position to specify column
     * @return Tile on the specified position or null if cell is empty
     */
    public Tile getTile(int row, int col) {
        if ("undefined".equals(tiles[row][col].toString())) {
            return null;
        }
        return tiles[row][col];
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

    public int getNumRows() {
        return numRows;
    }
    public int getNumCols() {
        return numCols;
    }
}