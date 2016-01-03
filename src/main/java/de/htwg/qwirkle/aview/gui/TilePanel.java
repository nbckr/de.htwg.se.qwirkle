package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQControllerGui;
import de.htwg.qwirkle.model.Tile;
import util.StretchIcon;
import util.observer.IObserver;
import util.observer.QEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Created by niels on 18.12.2015.
 * TODO: change name to label
 */
public class TilePanel extends JLabel implements ITilePanel, ComponentListener,
        IObserver {

    private static final Dimension SIZE_ON_GRID = new Dimension(30, 30);
    private static final Dimension SIZE_ON_HAND = new Dimension(50, 50);

    private int row;
    private int col;
    private Tile tile;
    private boolean onGrid;
    private IQControllerGui controller;
    private StretchIcon icon;

    // for displaying Tiles on GridPanel
    public TilePanel(int row, int col, IQControllerGui controller) {

        this.controller = controller;
        controller.addObserver(this);
        this.row = row;
        this.col = col;
        onGrid = true;

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setPreferredSize(SIZE_ON_GRID);

        this.addComponentListener(this);

        update(new QEvent());
    }

    // for displaying Tiles on HandPanel
    public TilePanel(Tile tile, IQControllerGui controller) {

        this.controller = controller;
        this.tile = tile;
        onGrid = false;

        update(new QEvent());

        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        setPreferredSize(SIZE_ON_HAND);
        addComponentListener(this);
    }

    private void refreshImage() {
        String filepath = tile.getImageFilepath();
        this.icon = new StretchIcon(filepath, false);
        this.setIcon(icon);
    }


    /*@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        refreshTile();
    }*/

    @Override
    public void update(QEvent e) {

        if (onGrid) {
            tile = controller.getTileReference(row, col);
            if (tile == null) {
                tile = new Tile();                    // null if empty cell; create undef Tile
            }
        }
        // else: Tile is on Hand and newly instanciated in HandPanel's update()
        refreshImage();
    }

    @Override
    public void componentResized(ComponentEvent e) {
        // always keep a 1:1 aspect ratio
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
