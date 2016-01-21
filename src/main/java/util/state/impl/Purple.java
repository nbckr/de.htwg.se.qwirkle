package util.state.impl;

import util.state.Color;

/**
 * Created by opuee on 20.01.16.
 */
public class Purple extends Color {
    @Override
    public String getAnsiFG() {
        return "\u001B[35m";
    }

    @Override
    public String getAnsiBG() {
        return "\u001B[45m";
    }

    @Override
    public String toString() {
        return "PURPLE";
    }
}
