package de.htwg.qwirkle.model;

import util.Constants;
import util.state.Color;
import util.state.Shape;

public class Tile {

    private final Color color;
    private final Shape shape;
    private boolean isSelectedAtHand;

    public Tile() {
        this(null, null);
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
        return shape == null;
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

        if(this.color == null ||this.shape == null) {
            return "undefined";
        }

        return this.color.toString() + this.shape.toString() + Constants.ANSI_RESET;
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