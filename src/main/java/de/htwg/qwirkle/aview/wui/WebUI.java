package de.htwg.qwirkle.aview.wui;

import de.htwg.qwirkle.controller.IQController.State;
import de.htwg.qwirkle.controller.impl.QController;
import de.htwg.qwirkle.model.Grid;
import de.htwg.qwirkle.model.Player;
import de.htwg.qwirkle.model.Tile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.Constants;
import util.observer.IObserver;
import util.observer.QEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class WebUI implements IObserver {

    private QController controller;
    private static final Logger LOG = LogManager.getLogger(WebUI.class);

    /**
     * @param controller Qwirkle game controller
     */
    public WebUI(QController controller) {
        this.controller = controller;
        controller.addObserver(this);

        // only read names in TUI if they haven't been read in GUI already
        if (controller.getState() == State.UNINITIALIZED) {
            this.readPlayerNames();
        }
    }


    public String getAsString() {
        return new StringBuilder()
                .append(controller.getGridString())
                .append(controller.getStatusMessage())
                .append("Hand: " + controller.getCurrentPlayer().printHand())
                .append("Score: " + controller.getCurrentPlayer().getScore())
                .append("Please enter a command (press h for help)")
                .toString();
    }

    /**
     * Reads number of players from console and starts the initialisation
     */
    public void readPlayerNames() {
        // NOTE: Fixed players for first web app draft
        List<Player> players = Arrays.asList(new Player("Niels"), new Player("Mark"));
        this.controller.init(players);
    }

    /**
     * Prints the controllers status message
     */
    public void printMessage() {
        LOG.info(this.controller.getStatusMessage());
    }

    private void tradeTiles(String command) {
        assert controller.getState() == State.TRADETILES;
        String trading = command;

        if ("0".equals(trading)) {
            // no tiles added, so same player's turn
            return;
        }

        List<Integer> integerList;
        try {
            String[] stringArray = trading.split(",");
            integerList = new ArrayList<Integer>();
            for (String string : stringArray) {
                int i = Integer.parseInt(string);
                assert i <= this.controller.getCurrentPlayer().getHand().size();
                integerList.add(i);

                Tile t = controller.getTileFromPlayer(i);
                controller.selectTile(t, true);
            }
        } catch (PatternSyntaxException ex) {
            LOG.info("Invalid input: " + ex);
            return;
        }

        controller.tradeSelectedTiles();
        controller.refillCurrentAndGoToNextPlayer();
    }

    private void addTileToGrid(String command) {
        assert controller.getState() == State.ADDTILES;
        boolean tileSet = false;

        int indexAtHand = Integer.parseInt(command.split(",")[0]);
        if (indexAtHand == 0) {
            return;
        } else if (indexAtHand > controller.getCurrentHand().size()) {
            LOG.info(Constants.INVALID);
            return;
        }

        controller.unselectAllTilesAtHand();
        controller.selectTile(controller.getTileFromPlayer(indexAtHand), true);

        int rowAtGrid = Integer.parseInt(command.split(",")[1]);
        int colAtGrid = Integer.parseInt(command.split(",")[2]);

        controller.setTargetPositionOnGrid(new Grid.Position(rowAtGrid, colAtGrid));
        if (!controller.validateAdding(controller.getSingleSelectedTile(), controller.getTargetPositionOnGrid())) {
            LOG.info("You can't place this tile here!");
            return;
        }

        controller.addSelectedTileToTargetPosition();
        controller.removeSelectedTilesFromCurrentPlayer();
        tileSet = true;

        if (tileSet) {
            controller.refillCurrentAndGoToNextPlayer();

        } else {         // no tiles added, so same player's turn, can choose add or trade
            controller.setState(State.CHOOSE_ACTION);
        }
    }

    /**
     * Reacts on a given event.
     *
     * @param e Event for update
     */
    @Override
    public void update(QEvent e) {
        switch (e.getEvent()) {
            case GET_PLAYER:
                readPlayerNames();
                break;
            case MESSAGE:
                printMessage();
                break;
            case IGNORE:
                break;
            default:
                return;
        }
    }
}
