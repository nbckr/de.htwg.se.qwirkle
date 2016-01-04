package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQControllerGui;
import util.ConstantValues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Created by niels on 18.12.2015.
 */
public class GridPanel extends JPanel {

    private static final Dimension SIZE = new Dimension(800, 800);
    private static final int GAP_PX = 2;

    public GridPanel(IQControllerGui controller) {

        int rows = controller.getNumRows();
        int cols = controller.getNumCols();

        setLayout(new GridLayout(rows, cols, GAP_PX, GAP_PX));
        setBorder(ConstantValues.INNER_BORDER);

        // Add cells for all tiles
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                GridTilePanel gridTilePanel = new GridTilePanel(row, col, controller);
                this.add(gridTilePanel);
            }
        }

        setPreferredSize(SIZE);
    }
}
