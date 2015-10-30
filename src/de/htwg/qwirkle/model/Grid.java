package de.htwg.qwirkle.model;

import java.util.Arrays;

/**
 * Created by niboecke on 30.10.2015.
 */
public class Grid {

    protected Tile[][] tiles;
    private int numRows;
    private int numCols;

    public Grid() {
        new Grid(101,101);
    }

    public Grid(int x, int y) {
        assert(x >= 10 && y >= 10);
        tiles = new Tile[x][y];
        numRows = x;
        numCols = y;
    }

    public void addTile(Tile t, int x, int y) {

        assert(tiles[x][y] == null);
        tiles[x][y] = t;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < numRows; i++) {
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
