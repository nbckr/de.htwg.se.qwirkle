package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQControllerGui;
import de.htwg.qwirkle.model.Tile;
import util.observer.IObserver;
import util.observer.QEvent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by niels on 18.12.2015.
 */
public class HandPanel extends JPanel implements IObserver {

    private static final Dimension DEFAULT_SIZE = new Dimension(150, 100);
    private IQControllerGui controller;
    ArrayList<Tile> tiles;

    public HandPanel(IQControllerGui controller) {

        this.controller = controller;
        controller.addObserver(this);

        setLayout(new FlowLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        setSize(DEFAULT_SIZE);

        for (int i = 0; i < 6; i++) {
            HandTilePanel handTilePanel = new HandTilePanel(null, controller, i);
            this.add(handTilePanel);
        }
    }

    @Override
    public void update(QEvent e) {

        /*if (e.getEvent() == QEvent.Event.NEXTPLAYER) {
            // TODO only update here?
        }

        System.out.println("ICH WILL!");
        if (controller.getState() != IQController.State.EMPTY) {
            this.removeAll();
            tiles = controller.getCurrentPlayer().getHand();
            for (Tile tile : tiles) {
                GridTilePanel tilePanel = new GridTilePanel(tile, controller);
                this.add(tilePanel);
            }
        }*/
    }
}
