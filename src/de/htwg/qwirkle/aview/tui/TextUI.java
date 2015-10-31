package de.htwg.qwirkle.aview.tui;

import de.htwg.qwirkle.controller.QController;
import de.htwg.qwirkle.model.Grid;
import util.observer.Event;
import util.observer.IObserver;

/**
 * Created by niboecke on 30.10.2015.
 */
public class TextUI implements IObserver {

    private QController controller;
    private Grid grid;

    public TextUI(QController controller) {
        this.controller = controller;
        controller.addObserver(this);
    }

    public void printTUI() {
        System.out.println(controller.getGridString());
        System.out.println(controller.getStatus());
        System.out.println("Please enter a command: blabla");
    }

    /**
     * @param line the user input to process. Possible inputs are:
     *             a - add tile(s) to grid
     *             t - trade in tile(s)
     *             n - new game
     *             q - quit
     * @return true in all cases except 'q' pressed
     */
    public boolean processInputLine(String line) {

        if (line.equalsIgnoreCase("a")) {

        }

        if (line.equalsIgnoreCase("t")) {

        }

        if (line.equalsIgnoreCase("n")) {

        }

        if (line.equalsIgnoreCase("q"))
            return false;   // quit loop

        return true;    // continue loop in all cases but 'q'
    }

    @Override
    public void update(Event e) {
        printTUI();
    }
}
