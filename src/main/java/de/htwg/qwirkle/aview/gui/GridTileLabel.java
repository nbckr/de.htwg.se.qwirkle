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
 * This is implemented as Label to use Icons, however it rather functions as a Panel
 */
public class GridTileLabel extends AbstractTileLabel {

    private static final Dimension SIZE_ON_GRID = new Dimension(30, 30);
    private int row;
    private int col;

    public GridTileLabel(int row, int col, IQControllerGui controller) {

        this.controller = controller;
        controller.addObserver(this);

        this.row = row;
        this.col = col;

        //setPreferredSize(Constants.GRID_TILE_SIZE); no, let GridPanel determine size

        addComponentListener(this);
        addMouseListener(new Listener());

        update(new QEvent());
    }

    @Override
    public void refreshTile() {
        tile = controller.getTileFromGrid(row, col);
        if (tile == null) {
            tile = new Tile();
        }
    }

    class Listener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {

            if (controller.getState() == IQController.State.ADDTILES
                    && controller.getNumberOfSelectedTiles() == 1) {
                controller.addSelectedTileToGrid(row, col);
            }
        }
    }
}
