package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQControllerGui;
import de.htwg.qwirkle.model.Supply;
import de.htwg.qwirkle.model.Tile;
import util.StretchIcon;

import javax.swing.*;
import java.awt.*;

/**
 * Created by niels on 18.12.2015.
 * TODO: change name to label
 */
public class GridTilePanel extends JLabel implements ITilePanel {

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
        this.tile = controller.getTileReference(row, col);
        //tile = new Supply().getTile();

        if (tile == null) {
            tile = new Tile(); // null if empty cell; create undef Tile
        }

        String filepath = tile.getImageFilepath();
        System.out.println(filepath);

        this.icon = new StretchIcon(filepath);
        this.setIcon(icon);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setPreferredSize(DEFAULT_SIZE);
    }

    public void paintComponent(Graphics g) {

    }

    /*public void paintComponent(Graphics g) {

        ImageIcon image = new ImageIcon("\\main\\resources\\img\\TILE_BLUE_CIRCLE.jpg");
        JLabel label = new JLabel("", image, JLabel.CENTER);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add( label, BorderLayout.CENTER );
        Image img = new ImageIcon("src/main/resources/img/TILE_BLUE_CIRCLE.jpg").getImage();
        g.drawImage(img, 0, 0, DEFAULT_SIZE.width, DEFAULT_SIZE.height, null);

        String haha = tile.getImageFilepath();
        System.out.println(haha);}*/



}
