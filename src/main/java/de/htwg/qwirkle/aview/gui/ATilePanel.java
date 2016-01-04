package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQControllerGui;
import de.htwg.qwirkle.model.Tile;
import util.StretchIcon;
import util.observer.IObserver;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Created by niels on 18.12.2015.
 */
public abstract class ATilePanel extends JLabel implements ComponentListener,
        IObserver {

    protected static final Border TILE_BORDER = BorderFactory.createLineBorder(Color.WHITE, 1);
    protected Tile tile;
    protected IQControllerGui controller;
    protected StretchIcon icon;

    protected void refreshImage() {
        String filepath = tile.getImageFilepath();
        this.icon = new StretchIcon(filepath, false);
        this.setIcon(icon);
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
