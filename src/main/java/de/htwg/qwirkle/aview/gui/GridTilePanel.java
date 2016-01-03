package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQControllerGui;
import de.htwg.qwirkle.model.Tile;
import util.observer.QEvent;

import javax.swing.*;
import java.awt.*;

/**
 * Created by niels on 18.12.2015.
 * This is implemented as Label to use Icons, however it rather functions as a Panel
 */
public class GridTilePanel extends ATilePanel {

    private final Dimension SIZE_ON_GRID = new Dimension(30, 30);
    private int row;
    private int col;

    public GridTilePanel(int row, int col, IQControllerGui controller) {

        this.controller = controller;
        controller.addObserver(this);

        this.row = row;
        this.col = col;

        this.setBorder(ATilePanel.TILE_BORDER);
        this.setPreferredSize(SIZE_ON_GRID);

        this.addComponentListener(this);

        update(new QEvent());
    }

    @Override
    public void update(QEvent e) {
        tile = controller.peekTileFromGrid(row, col);
        if (tile == null) {
            tile = new Tile();                    // null if empty cell; create undef Tile
        }
        refreshImage();
    }


}
