package de.htwg.qwirkle.model;

/**
 * Created by niels on 23.10.2015.
 */

import util.TilePrintUtil;

public class Tile {

    protected enum Color {
        CYAN, YELLOW, RED, BLUE, GREEN, PURPLE, UNDEF
    }

    protected enum Shape {
        CIRCLE, SQUARE, CLOVER, DIAMOND, STAR, CROSS, UNDEF
    }

    private final Color color;
    private final Shape shape;

    public Tile() {
        this(Color.UNDEF, Shape.UNDEF);
    }

    public Tile(Color c, Shape s) {
        this.color = c;
        this.shape = s;
    }

    public Color getColor() {
        return color;
    }

    public Shape getShape() {
        return shape;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Tile)) {
            return false;
        }
        Tile t = (Tile) o;
        if((this.color == t.color) && (this.shape == t.shape)) {
            return true;
        }
        return false;
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
                myColor = TilePrintUtil.ANSI_CYAN_BG;
                break;
            case YELLOW:
                myColor = TilePrintUtil.ANSI_YELLOW_BG;
                break;
            case RED:
                myColor = TilePrintUtil.ANSI_RED_BG + TilePrintUtil.ANSI_WHITE;
                break;
            case BLUE:
                myColor = TilePrintUtil.ANSI_BLUE_BG + TilePrintUtil.ANSI_WHITE;
                break;
            case GREEN:
                myColor = TilePrintUtil.ANSI_GREEN_BG;
                break;
            case PURPLE:
                myColor = TilePrintUtil.ANSI_PURPLE_BG + TilePrintUtil.ANSI_WHITE;
                break;
            default:
                myColor = TilePrintUtil.ANSI_BLACK_BG;
        }

        myShape = shape.toString().substring(0, 2);

        return myColor + myShape + TilePrintUtil.ANSI_RESET;
    }


}