package util.state.impl;

import util.state.Shape;

/**
 * Created by opuee on 20.01.16.
 */
public class Square extends Shape {
    @Override
    public String getANSI() {
        return "SQ";
    }

    @Override
    public String toString() {
        return "SQUARE";
    }
}
