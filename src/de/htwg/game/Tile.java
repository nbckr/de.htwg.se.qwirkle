package de.htwg.game;

/**
 * Created by niels on 23.10.2015.
 */
public class Tile {

    private final String color;
    private final String shape;

    public Tile(String color, String shape) {
        this.color = color;
        this.shape = shape;
    }

    public String getColor() {
        return color;
    }

    public String getShape() {
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


