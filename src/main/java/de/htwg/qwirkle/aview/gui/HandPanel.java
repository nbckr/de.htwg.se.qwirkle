package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQControllerGui;
import de.htwg.qwirkle.model.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by niels on 18.12.2015.
 */
public class HandPanel extends JPanel {

    private static final Dimension DEFAULT_SITE = new Dimension(60, 60);
    private IQControllerGui controller;
    ArrayList<Tile> tiles;
    private ArrayList<GridTilePanel> tilePanels;

    public HandPanel(IQControllerGui controller) {

        this.controller = controller;
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

        refreshTiles();
    }

    private void refreshTiles() {

        tiles = controller.getCurrentPlayer().getHand();
        for (Tile tile : tiles) {
            GridTilePanel tilePanel = new GridTilePanel(tile, controller);
        }
    }
}
