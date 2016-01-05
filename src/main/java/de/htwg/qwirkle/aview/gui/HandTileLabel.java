package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQController;
import de.htwg.qwirkle.controller.IQControllerGui;
import de.htwg.qwirkle.model.Tile;
import util.Constants;
import util.observer.QEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HandTileLabel extends AbstractTileLabel {

    private int index;

    public HandTileLabel(Tile tile, IQControllerGui controller, int index) {

        this.controller = controller;
        controller.addObserver(this);

        this.tile = tile;
        this.index = index;

        this.setPreferredSize(Constants.HAND_TILE_SIZE);

        addComponentListener(this);
        addMouseListener(new Listener());

        update(new QEvent());
    }

    @Override
    public void refreshTile() {
        if (controller.getState() != IQController.State.UNINITIALIZED) {
            tile = controller.getTileFromPlayer(index);
        }
        /*if (tile == null) {
            tile = new Tile();
        }*/
    }

    @Override
    public void refreshBorder() {
        if (tile != null && tile.isSelected()) {
            setBorder(BORDER_SELECTED);
        } else {
            setBorder(BORDER_PLAIN);
        }
    }


    class Listener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if (tile != null) {
                controller.selectTileToggle(tile);
            }
        }
    }
}
