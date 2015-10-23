package de.htwg.game.data;

/**
 * Created by niels on 23.10.2015.
 */
public class Tile {

    protected enum Color {
        ORANGE, YELLOW, RED, BLUE, GREEN, PURPLE
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
        return "Tile {" +
                "color='" + color + '\'' +
                ", shape='" + shape + '\'' +
                '}';
    }

}


