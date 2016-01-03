/*
Main class to start Quirkle.
Based on blueprint from https://moodle.htwg-konstanz.de/moodle/course/view.php?id=713
 */

package de.htwg.qwirkle;

import de.htwg.qwirkle.aview.tui.TextUI;
import de.htwg.qwirkle.aview.gui.QFrame;
import de.htwg.qwirkle.controller.impl.QController;

public final class Qwirkle {

    private Qwirkle() {};

    public static void main(String[] args) {

        // start controller
        QController controller =  new QController();

        // start GUI
        new QFrame(controller);

        // start TUI
        TextUI tui = new TextUI(controller);;
    }

}
