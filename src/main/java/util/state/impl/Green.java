package util.state.impl;

import util.state.Color;

/**
 * Created by opuee on 20.01.16.
 */
public class Green extends Color {
    @Override
    public String getAnsiFG() {
        return "\u001B[32m";
    }

    @Override
    public String getAnsiBG() {
        return "\u001B[42m";
    }

    @Override
    public String toString() {
        return "GREEN";
    }
}
