package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQControllerGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Created by niels on 18.12.2015.
 */
public class GridPanel extends JPanel {

    private static final int GAP_PX = 2;

    public GridPanel(IQControllerGui controller) {

        int rows = controller.getNumRows();
        int cols = controller.getNumCols();
        int cells = rows * cols;

        setLayout(new GridLayout(rows, cols, GAP_PX, GAP_PX));

        setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

        // Add cells for all tiles
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                GridTilePanel tilePanel = new GridTilePanel(row, col, controller);
                this.add(tilePanel);
            }
        }

        setPreferredSize(new Dimension(900, 900));

        /*// make the Tiles always keep a 1:1 aspect ratio
        this.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                Rectangle b = e.getComponent().getBounds();
                int d = Math.min(b.height, b.width);
                e.getComponent().setBounds(b.x, b.y, d, d);
            }

            @Override
            public void componentMoved(ComponentEvent e) {}

            @Override
            public void componentShown(ComponentEvent e) {}

            @Override
            public void componentHidden(ComponentEvent e) {}
        });*/
    }
}
