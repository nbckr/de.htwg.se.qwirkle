package util.state.impl;

import util.state.Shape;

/**
 * Created by opuee on 20.01.16.
 */
public class Diamond extends Shape {
    @Override
    public String getANSI() {
        return "DI";
    }

    @Override
    public String toString() {
        return "DIAMOND";
    }
}
