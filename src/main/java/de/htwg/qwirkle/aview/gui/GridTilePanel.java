package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQController;
import de.htwg.qwirkle.controller.IQControllerGui;
import de.htwg.qwirkle.model.Tile;
import util.StretchIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Created by niels on 18.12.2015.
 * TODO: change name to label
 */
public class GridTilePanel extends JLabel implements ITilePanel, ComponentListener {

    private static final Dimension DEFAULT_SIZE = new Dimension(30, 30);
    private int row;
    private int col;
    private Tile tile;
    private IQControllerGui controller;
    private StretchIcon icon;

    public GridTilePanel(int row, int col, IQControllerGui controller) {

        this.row = row;
        this.col = col;
        this.controller = controller;

        refreshTile();

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setPreferredSize(DEFAULT_SIZE);

        // always keep a 1:1 aspect ratio
        this.addComponentListener(this);
    }

    public GridTilePanel(Tile tile, IQControllerGui controller) {

        this.controller = controller;

        refreshTile();

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setPreferredSize(DEFAULT_SIZE);

        this.addComponentListener(this);
    }

    private void refreshTile() {
        this.tile = controller.getTileReference(row, col);
        // for fun: tile = new Supply().getTile();
        if (tile == null) {
            tile = new Tile();                    // null if empty cell; create undef Tile
        }
        refreshImage();
    }

    private void refreshImage() {
        String filepath = tile.getImageFilepath();
        this.icon = new StretchIcon(filepath, false);
        this.setIcon(icon);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        refreshTile();
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Rectangle b = e.getComponent().getBounds();
        int d = Math.max(b.height, b.width);
        e.getComponent().setBounds(b.x, b.y, d, d);
    }

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentShown(ComponentEvent e) {}

    @Override
    public void componentHidden(ComponentEvent e) {}

}
