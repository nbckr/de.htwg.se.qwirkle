package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQControllerGui;
import de.htwg.qwirkle.model.Tile;
import util.observer.QEvent;

import java.awt.*;

/**
 * Created by niels on 18.12.2015.
 * This is implemented as Label to use Icons, however it rather functions as a Panel
 */
public class GridTilePanel extends ATilePanel {

    private static final Dimension SIZE_ON_GRID = new Dimension(30, 30);
    private int row;
    private int col;

    public GridTilePanel(int row, int col, IQControllerGui controller) {

        this.controller = controller;
        controller.addObserver(this);

        this.row = row;
        this.col = col;

        this.setPreferredSize(SIZE_ON_GRID);

        this.addComponentListener(this);

        update(new QEvent());
    }

    @Override
    public void refreshTile() {
        tile = controller.peekTileFromGrid(row, col);
        if (tile == null) {
            tile = new Tile();
        }
    }


}
