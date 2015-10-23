package de.htwg.general;

import de.htwg.game.data.Player;

public class Main {

    public static void main(String[] args) {
        Player pl = new Player("Heino");
        String n = pl.getName();
        n = "Frank";

        System.out.print(pl.getName());
    }
}
