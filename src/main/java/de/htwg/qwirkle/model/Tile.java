package de.htwg.qwirkle.model;

import util.Constants;

public class Tile {

    public enum Color {
        CYAN, YELLOW, RED, BLUE, GREEN, PURPLE, UNDEF
    }

    public enum Shape {
        CIRCLE, SQUARE, CLOVER, DIAMOND, STAR, CROSS, UNDEF
    }

    private final Color color;
    private final Shape shape;
    private boolean isSelectedAtHand;

    public Tile() {
        this(Color.UNDEF, Shape.UNDEF);
    }

    public Tile(Color color, Shape shape) {
        this.color = color;
        this.shape = shape;
        this.isSelectedAtHand = false;
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

    public boolean isSelected() {
        return isSelectedAtHand;
    }

    public void setSelected(boolean isSelected) {
        this.isSelectedAtHand = isSelected;
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
        String myColor = Constants.ANSI_BLACK_BG;

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
                break;
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