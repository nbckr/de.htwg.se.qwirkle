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

public abstract class AbstractTileLabel extends JLabel implements IObserver {

    protected static final Border BORDER_PLAIN = BorderFactory.createLineBorder(Color.BLUE, 1);
    protected static final Border BORDER_SELECTED = BorderFactory.createLineBorder(Color.RED, 4);
    protected static final Border BORDER_HOVER = BorderFactory.createLineBorder(Color.WHITE, 2);
    protected static final Border BORDER_CANDIDATE = BorderFactory.createLineBorder(Color.RED, 2);
    protected static final Border BORDER_TARGET = BorderFactory.createLineBorder(Color.RED, 3);

    protected IQControllerGui controller;
    protected Tile tile;
    protected StretchIcon icon;

    protected void refreshTile() {
    }

    protected void refreshBorder() {
    }

    protected void refreshImage() {
        String filepath;
        if (tile == null) {
            filepath = new Tile().getImageFilepath();
        }else {
            filepath = tile.getImageFilepath();
        }

        icon = new StretchIcon(filepath, false);
        setIcon(icon);
    }

    public Tile getTile() {
        return tile;
    }

    @Override
    public void update(QEvent e) {
        refreshTile();
        refreshBorder();
        refreshImage();
    }
}
