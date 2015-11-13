package de.htwg.qwirkle.aview.tui;

import de.htwg.qwirkle.controller.QController;
import de.htwg.qwirkle.model.Grid;
import de.htwg.qwirkle.model.Player;
import util.observer.QEvent;

import java.util.ArrayList;
import java.util.Scanner;
import util.observer.IObserver;

/**
 * Created by niboecke on 30.10.2015.
 */
public class TextUI implements IObserver {

    private Scanner scanner;
    private QController controller;
    private Grid grid;

    public TextUI(QController controller) {
        this.scanner = new Scanner(System.in);
        this.controller = controller;
        controller.addObserver(this);
    }

    public void printTUI() {
        // print name of player, content of player's hand, number of round
        System.out.println(controller.getGridString());
        System.out.println(controller.getStatusMessage());
        System.out.println("Please enter a command (press h for help)");
    }

    public void initializePlayer() {
        int noP = 1;
        String name;
        ArrayList<Player> players;

        players = new ArrayList<>();

        while(noP <= 4) {
            if (noP >= 3) {
                System.out.println("Enter another Player? (y/n)");
                if (this.scanner.next() == "n") {
                    break;
                }
            }

            System.out.printf("Enter name of Player%i:", noP);
            name = this.scanner.next();
            players.add(new Player(name));
            noP++;
        }

        this.controller.init(players);
    }

    public void printMessage() {
        System.out.println(this.controller.getStatusMessage());
    }

    /**
     * @param line the user input to process. Possible inputs are:
     *             a - add tile(s) to grid
     *             t - trade in tile(s)
     *             n - new game
     *             h - show help
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

        if (line.equalsIgnoreCase("h")) {
            System.out.println(util.MessageUtil.INSTRUCTIONS);
        }

        if (line.equalsIgnoreCase("q"))
            return false;   // quit loop

        return true;    // continue loop in all cases but 'q'
    }

    @Override
    public void update(QEvent e) {
        switch(e.getEvent()) {
            case getPlayer:
                initializePlayer();
                break;
            case message:
                printMessage();
                break;
            default:
                printTUI();
                break;
        }
    }
}
