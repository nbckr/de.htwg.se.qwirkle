package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQControllerGui;
import de.htwg.qwirkle.model.Tile;
import util.StretchIcon;
import util.observer.IObserver;
import util.observer.QEvent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Created by niels on 18.12.2015.
 */
public abstract class AbstractTileLabel extends JLabel implements ComponentListener,
        IObserver {

    protected static final Border BORDER_PLAIN = BorderFactory.createLineBorder(Color.BLUE, 1);
    protected static final Border BORDER_SELECTED = BorderFactory.createLineBorder(Color.RED, 4);
    protected Tile tile;

    protected IQControllerGui controller;
    protected StretchIcon icon;

    protected void refreshTile() {
    }

    protected void refreshImage() {
        String filepath = tile.getImageFilepath();
        icon = new StretchIcon(filepath, false);
        setIcon(icon);
    }

    protected void refreshBorder() {
        if (tile.isSelected()) {
            setBorder(AbstractTileLabel.BORDER_SELECTED);
        } else {
            setBorder(AbstractTileLabel.BORDER_PLAIN);
        }
    }

    public Tile getTile() {
        return tile;
    }
    @Override
    public void update(QEvent e) {
        refreshTile();
        refreshImage();
        refreshBorder();
    }

    @Override
    public void componentResized(ComponentEvent e) {
        // always keep a 1:1 aspect ratio
        Rectangle b = e.getComponent().getBounds();
        int d = Math.max(b.height, b.width);
        e.getComponent().setBounds(b.x, b.y, d, d);
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
}
