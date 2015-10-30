/*
Main class to start Quirkle.
Based on blueprint from https://moodle.htwg-konstanz.de/moodle/course/view.php?id=713
 */

package de.htwg.qwirkle;

import de.htwg.qwirkle.aview.tui.TextUI;
import de.htwg.qwirkle.controller.QController;
import java.util.Scanner;

public final class Qwirkle {

    private static Scanner scanner;
    private static TextUI tui;
    private static QController controller;

    public static void main(String[] args) {

        // Build up the application
        controller =  new QController(9);

        tui = new TextUI(controller);
        tui.printTUI();

        // Create an initial game
        controller.create();

        // continue to read user input on the tui until the user decides to quit
        boolean loop = true;
        scanner = new Scanner(System.in);
        while (loop) {
            loop = tui.processInputLine(scanner.next());
        }
    }

}
