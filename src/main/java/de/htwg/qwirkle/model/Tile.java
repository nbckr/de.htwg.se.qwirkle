package de.htwg.qwirkle.model;

import util.Constants;
import util.GridPosition;

public class Tile {

    protected enum Color {
        CYAN, YELLOW, RED, BLUE, GREEN, PURPLE, UNDEF
    }

    protected enum Shape {
        CIRCLE, SQUARE, CLOVER, DIAMOND, STAR, CROSS, UNDEF
    }

    private final Color color;
    private final Shape shape;
    private GridPosition position;
    private boolean isTargetedOnGrid;
    private boolean isSelectedAtHand;

    public Tile() {
        this(null);
    }

    public Tile(GridPosition position) {
        this(Color.UNDEF, Shape.UNDEF, position);
    }

    public Tile(Color color, Shape shape) {
        this(color, shape, null);
    }


    /**
     * @param color
     * @param shape
     * @param position position on Grid or null if not on the grid yet
     */
    public Tile(Color color, Shape shape, GridPosition position) {
        this.color = color;
        this.shape = shape;
        this.isSelectedAtHand = false;
        this.isTargetedOnGrid = false;
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public Shape getShape() {
        return shape;
    }

    public boolean isUndefined() {
        return shape == Shape.UNDEF;
    }

    public boolean isSelectedAtHand() {
        return isSelectedAtHand;
    }

    public void setSelectedAtHand(boolean isSelected) {
        this.isSelectedAtHand = isSelected;
    }

    public GridPosition getPosition() {
        return position;
    }

    public void setPosition(GridPosition position) {
        this.position = position;
    }

    public boolean isOnGrid() {
        return position == null ? false : true;
    }

    public boolean getIsTargetedOnGrid() {
        return isTargetedOnGrid;
    }

    public void setIsTargetedOnGrid(boolean targeted) {
        this.isTargetedOnGrid = targeted;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Tile)) {
            return false;
        }
        Tile t = (Tile) o;
        return (this.color == t.color) && (this.shape == t.shape);
    }

    @Override
    public int hashCode() {
        // method is not supported yet but should be overwritten when equals() is
        return 42;
    }

    @Override
    public String toString() {
        String myShape;
        String myColor;

        if(this.color == Color.UNDEF ||this.shape == Shape.UNDEF) {
            return "undefined";
        }

        switch(color) {
            case CYAN:
                myColor = Constants.ANSI_CYAN_BG;
                break;
            case YELLOW:
                myColor = Constants.ANSI_YELLOW_BG;
                break;
            case RED:
                myColor = Constants.ANSI_RED_BG + Constants.ANSI_WHITE;
                break;
            case BLUE:
                myColor = Constants.ANSI_BLUE_BG + Constants.ANSI_WHITE;
                break;
            case GREEN:
                myColor = Constants.ANSI_GREEN_BG;
                break;
            case PURPLE:
                myColor = Constants.ANSI_PURPLE_BG + Constants.ANSI_WHITE;
                break;
            default:
                myColor = Constants.ANSI_BLACK_BG;
        }

        myShape = shape.toString().substring(0, 2);

        return myColor + myShape + Constants.ANSI_RESET;
    }

    /**
     * Special toString implementation to generate image file names
     * @return tile name in the form "TILE_COLOR_SHAPE", e.g. "TILE_BLUE_CIRCLE"
     */
    public String toString2() {
        return "TILE_" + color.toString() + "_" + shape.toString();
    }

    /**
     * @return relative path to corresponding image that has to exist in src/resources/img
     *         e.g. "src/main/resources/img/TILE_BLUE_CIRCLE.jpg"
     */
    public String getImageFilepath() {
        return "src/main/resources/img/" + toString2() + ".jpg";
    }
}