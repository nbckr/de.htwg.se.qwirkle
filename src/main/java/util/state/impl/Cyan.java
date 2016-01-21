package util.state.impl;

import util.state.Color;

/**
 * Created by opuee on 20.01.16.
 */
public class Cyan extends Color {
    @Override
    public String getAnsiFG() {
        return "\u001B[36m";
    }

    @Override
    public String getAnsiBG() {
        return "\u001B[46m";
    }

    @Override
    public String toString() {
        return "CYAN";
    }
}
