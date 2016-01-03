package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQController;
import de.htwg.qwirkle.controller.IQControllerGui;
import de.htwg.qwirkle.model.Tile;
import util.observer.QEvent;

import javax.swing.*;
import java.awt.*;

/**
 * Created by niels on 18.12.2015.
 */
public class HandTilePanel extends ATilePanel {

    private final Dimension SIZE_ON_HAND = new Dimension(75, 75);
    private final int BORDER = 5;
    private int position;

    public HandTilePanel(Tile tile, IQControllerGui controller, int position) {

        this.controller = controller;
        controller.addObserver(this);

        this.tile = tile;
        this.position = position;

        update(new QEvent());

        setBorder(BorderFactory.createEmptyBorder(BORDER,BORDER,BORDER,BORDER));
        this.setPreferredSize(SIZE_ON_HAND);

        addComponentListener(this);
    }

    @Override
    public void update(QEvent e) {
        if (controller.getState() != IQController.State.UNINIZIALIZED) {
            tile = controller.peekTileFromHand(position);
        }
        if (tile == null) {
            tile = new Tile();                    // null if empty osition// ; create undef Tile
        }
        refreshImage();
    }
}
