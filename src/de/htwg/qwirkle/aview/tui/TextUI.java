package de.htwg.qwirkle.aview.tui;

import de.htwg.qwirkle.controller.QController;
import de.htwg.qwirkle.model.Grid;
import de.htwg.qwirkle.model.Player;
import de.htwg.qwirkle.model.Tile;
import org.apache.logging.log4j.LogManager;
import util.MessageUtil;
import util.observer.QEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

import util.observer.IObserver;
import org.apache.logging.log4j.Logger;

/**
 * Created by niboecke on 30.10.2015.
 */
public class TextUI implements IObserver {

    private Scanner scanner;
    private QController controller;
    private static final Logger logger = LogManager.getLogger(TextUI.class);

    /**
     * @param controller Qwirkle game controller
     */
    public TextUI(QController controller) {
        logger.error("TextUI");
        this.scanner = new Scanner(System.in);
        this.controller = controller;
        controller.addObserver(this);

        initializePlayer();
    }

    /**
     * Prints the minor information of the grid and current player
     */
    public void printTUI() {
        // print name of player, content of player's hand, number of round
        System.out.println(controller.getGridString());
        System.out.println(controller.getStatusMessage());
        System.out.println("Hand: " + controller.getCurrentPlayer().printHand());
        System.out.println("Score: " + controller.getCurrentPlayer().getScore());
        System.out.println("Please enter a command (press h for help)");
    }

    /**
     * Reads number of players from console and starts the initialisation
     */
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

    /**
     * Prints the controllers status message
     */
    public void printMessage() {
        System.out.println(this.controller.getStatusMessage());
    }

    /**
     * @param line the user input to process. Possible inputs are:
     *             a - add tile(s) to grid
     *             t - trade in tile(s)
     *             h - show help
     *             q - quit
     * @return true in all cases except 'q' pressed
     */
    public boolean processInputLine(String line) {

        if (line.equalsIgnoreCase("a")) {
            addTileRoutine();
        }

        if (line.equalsIgnoreCase("t")) {
            tradeTileRoutine();
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

    private void tradeTileRoutine() {
        int size = this.controller.getCurrentPlayer().getHand().size();
        System.out.println("Which tiles do you want to trade? (1-" + size +", separated by space:");
        String trading = scanner.next();
        List<Integer> integerList;

        try {
            String[] stringArray = trading.split("\\s+");
            integerList = new ArrayList<>();
            for(String string : stringArray) {
                int i = Integer.parseInt(string);
                assert(i <= this.controller.getCurrentPlayer().getHand().size());
                integerList.add(i);
            }
        } catch (PatternSyntaxException ex) {
            System.out.println("Invalid input");
            return;
        }

        for(int i : integerList) {
            Tile oldTile = controller.getCurrentPlayer().getTileFromHand(i);
            Tile newTile = controller.tradeTile(oldTile);
            controller.getCurrentPlayer().addTileToHand(newTile);
        }

        controller.nextPlayer();
        printTUI();
    }

    private void addTileRoutine() {
        boolean tileSet = false;

        while(true) {
            int size = this.controller.getCurrentPlayer().getHand().size();
            System.out.println("Select tile to add to grid(1-" + size + ", 0 to quit):");
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
            controller.refillPlayer();
            controller.nextPlayer();
            printTUI();
        }
    }

    /**
     * Reacts on a given event.
     * @param e Event for update
     */
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
