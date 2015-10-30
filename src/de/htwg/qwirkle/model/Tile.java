package de.htwg.qwirkle.model;

/**
 * Created by niels on 23.10.2015.
 */

import util.TilePrintUtil;

public class Tile {



    protected enum Color {
        CYAN, YELLOW, RED, BLUE, GREEN, PURPLE
    }

    protected enum Shape {
        CIRCLE, SQUARE, CLOVER, DIAMOND, STAR, CROSS
    }

    private final Color color;
    private final Shape shape;

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
    public String toString() {
        String myShape;
        String myColor;

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

        switch(shape) {
            case CIRCLE:
                myShape = "CI";
                break;
            case SQUARE:
                myShape = "SQ";
                break;
            case CLOVER:
                myShape = "CL";
                break;
            case DIAMOND:
                myShape = "DI";
                break;
            case STAR:
                myShape = "ST";
                break;
            case CROSS:
                myShape = "CR";
                break;
            default:
                myShape = "??";
        }

        return (myColor + myShape + TilePrintUtil.ANSI_RESET);
    }


}