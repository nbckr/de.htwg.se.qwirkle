package util;

/*
 * A class to represent a positon on the Grid, heavily inspired by Java's Dimension
 * class. The <code>GridPosition</code> class encapsulates the row and
 * column of a Tile on the Grid (in integer precision) in a single object.
 */
public class GridPosition {

    public int row;
    public int col;

    public GridPosition() {
        this(0, 0);
    }

    public GridPosition(GridPosition g) {
        this(g.row, g.col);
    }

    public GridPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public double getCol() {
        return col;
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean equals(Object obj) {
        if (obj instanceof GridPosition) {
            GridPosition g = (GridPosition)obj;
            return (row == g.row) && (col == g.col);
        }
        return false;
    }

    public int hashCode() {
        int sum = row + col;
        return sum * (sum + 1)/2 + row;
    }

    public String toString() {
        return getClass().getName() + "[row=" + row + ",col=" + col + "]";
    }
}
