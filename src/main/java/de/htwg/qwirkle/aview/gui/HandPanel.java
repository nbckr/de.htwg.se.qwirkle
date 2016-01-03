package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQController;
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


    private IQControllerGui controller;
    ArrayList<Tile> tiles;
    private ArrayList<TilePanel> tilePanels;

    public HandPanel(IQControllerGui controller) {

        this.controller = controller;
        controller.addObserver(this);

        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        setSize(new Dimension(200, 100));

        for (int i = 0; i < 6; i++) {
            TilePanel tilePanel = new TilePanel(new Tile(), controller, i);
            this.add(tilePanel);
        }
        //update(new QEvent());
    }

    @Override
    public void update(QEvent e) {

        if (e.getEvent() == QEvent.Event.NEXTPLAYER) {
            // TODO only update here?
        }
        /*
        System.out.println("ICH WILL!");
        if (controller.getState() != IQController.State.EMPTY) {
            this.removeAll();
            tiles = controller.getCurrentPlayer().getHand();
            for (Tile tile : tiles) {
                TilePanel tilePanel = new TilePanel(tile, controller);
                this.add(tilePanel);
            }
        }*/
    }
}
