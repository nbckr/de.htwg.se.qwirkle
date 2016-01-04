package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQController;
import de.htwg.qwirkle.controller.IQControllerGui;
import de.htwg.qwirkle.model.Tile;
import util.observer.QEvent;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by niels on 18.12.2015.
 */
public class HandTilePanel extends ATilePanel {

    private static final Dimension SIZE_ON_HAND = new Dimension(75, 75);
    private int position;

    public HandTilePanel(Tile tile, IQControllerGui controller, int position) {

        this.controller = controller;
        controller.addObserver(this);

        this.tile = tile;
        this.position = position;

        this.setPreferredSize(SIZE_ON_HAND);

        addComponentListener(this);
        addMouseListener(new Listener());

        update(new QEvent());
    }

    @Override
    public void refreshTile() {
        if (controller.getState() != IQController.State.UNINIZIALIZED) {
            tile = controller.peekTileFromHand(position);
        }
        if (tile == null) {
            tile = new Tile();
        }
    }

    class Listener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            controller.selectToggle(tile);
        }
    }
}
