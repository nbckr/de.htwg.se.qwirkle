package util.state.impl;

import util.state.Color;

/**
 * Created by opuee on 20.01.16.
 */
public class Blue extends Color {
    @Override
    public String getAnsiFG() {
        return "\u001B[34m";
    }

    @Override
    public String getAnsiBG() {
        return "\u001B[44m";
    }

    @Override
    public String toString() {
        return "BLUE";
    }
}
