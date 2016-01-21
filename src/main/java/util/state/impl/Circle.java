package util.state.impl;

import util.state.Shape;

/**
 * Created by opuee on 20.01.16.
 */
public class Circle extends Shape {
    @Override
    public String getANSI() {
        return "CI";
    }

    @Override
    public String toString() {
        return "CIRCLE";
    }
}
