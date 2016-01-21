package util.state.impl;

import util.state.Color;

/**
 * Created by opuee on 20.01.16.
 */
public class Red extends Color {
    @Override
    public String getAnsiFG() {
        return "\u001B[31m";
    }

    @Override
    public String getAnsiBG() {
        return "\u001B[41m";
    }

    @Override
    public String toString() {
        return "RED";
    }
}
