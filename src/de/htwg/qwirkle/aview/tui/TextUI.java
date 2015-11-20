package de.htwg.qwirkle.aview.tui;

import de.htwg.qwirkle.controller.QController;
import de.htwg.qwirkle.model.Grid;
import de.htwg.qwirkle.model.Player;
import de.htwg.qwirkle.model.Tile;
import util.MessageUtil;
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

        initializePlayer();
    }

    public void printTUI() {
        // print name of player, content of player's hand, number of round
        System.out.println(controller.getGridString());
        System.out.println(controller.getStatusMessage());
        System.out.println("Hand: " + controller.getCurrentPlayer().printHand());
        System.out.println("Score: " + controller.getCurrentPlayer().getScore());
        System.out.println("Please enter a command (press h for help)");
    }

    public void initializePlayer() {
        int noP = 1;
        String name, tmp;
        ArrayList<Player> players;

        players = new ArrayList<>();

        while(noP <= 4) {
            if (noP >= 3) {
                System.out.println("Enter another Player? (y/n)");
                tmp = this.scanner.next();
                if (tmp.equals("n")) {
                    break;
                }
            }

            System.out.printf("Enter name of Player%d:", noP);
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
            boolean tileSet = false;

            while(true) {
                System.out.println("Select tile to add to grid(1-5, 0 to quit):");
                int iTile = scanner.nextInt();
                if(iTile == 0){
                    break;
                }

                Tile selectedTile = controller.getCurrentPlayer().getTileFromHand(iTile);
                if (selectedTile == null) {
                    System.out.println(MessageUtil.INVALID);
                    continue;
                }

                System.out.print("Select position on grid(row column):");
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                controller.addTileToGrid(selectedTile, row, col);
                tileSet = true;
            }

            if(tileSet) {
                controller.nextPlayer();
                printTUI();
            }

        }

        if (line.equalsIgnoreCase("t")) {
            System.out.println();
        }

        if (line.equalsIgnoreCase("n")) {
            System.out.println();
        }

        if (line.equalsIgnoreCase("h")) {
            System.out.println(MessageUtil.INSTRUCTIONS);
        }

        if (line.equalsIgnoreCase("q")) {
            // ToDo: evaluate player's score
            System.out.println(MessageUtil.SEEYOU);
            return false;   // quit loop
        }

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
