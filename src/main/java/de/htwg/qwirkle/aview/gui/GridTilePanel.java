package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQControllerGui;
import de.htwg.qwirkle.model.Tile;

import javax.swing.*;
import java.awt.*;

/**
 * Created by niels on 18.12.2015.
 */
public class GridTilePanel extends JPanel implements ITilePanel {

    private static final Dimension DEFAULT_TILESIZE = new Dimension(25, 25);
    private int row;
    private int col;
    private Tile tile;
    private IQControllerGui controller;

    public GridTilePanel(int row, int col, IQControllerGui controller) {

        this.row = row;
        this.col = col;
        this.controller = controller;
        this.tile = controller.getTileReference(row, col);

        setSize(DEFAULT_TILESIZE);
    }

    public void paintComponent(Graphics g) {

        /*ImageIcon image = new ImageIcon("\\main\\resources\\img\\TILE_BLUE_CIRCLE.jpg");
        JLabel label = new JLabel("", image, JLabel.CENTER);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add( label, BorderLayout.CENTER );*/

        Image img = new ImageIcon("\\main\\resources\\img\\TILE_BLUE_CIRCLE.jpg").getImage();
        g.drawImage(img, 0, 0, DEFAULT_TILESIZE.width, DEFAULT_TILESIZE.height, null);

    }


}
