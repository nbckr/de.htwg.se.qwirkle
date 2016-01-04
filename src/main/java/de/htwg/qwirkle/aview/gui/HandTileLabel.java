package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQController;
import de.htwg.qwirkle.controller.IQControllerGui;
import de.htwg.qwirkle.model.Tile;
import util.Constants;
import util.observer.QEvent;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by niels on 18.12.2015.
 */
public class HandTileLabel extends AbstractTileLabel {

    private int position;

    public HandTileLabel(Tile tile, IQControllerGui controller, int position) {

        this.controller = controller;
        controller.addObserver(this);

        this.tile = tile;
        this.position = position;

        this.setPreferredSize(Constants.HAND_TILE_SIZE);

        addComponentListener(this);
        addMouseListener(new Listener());

        update(new QEvent());
    }

    @Override
    public void refreshTile() {
        if (controller.getState() != IQController.State.UNINIZIALIZED) {
            tile = controller.getTileFromHand(position);
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
