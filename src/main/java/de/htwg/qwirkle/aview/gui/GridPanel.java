package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQControllerGui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by niels on 18.12.2015.
 */
public class GridPanel extends JPanel {

    private static final int GAP_PX = 1;

    public GridPanel(IQControllerGui controller) {

        int rows = controller.getNumRows();
        int cols = controller.getNumCols();
        int cells = rows * cols;

        setLayout(new GridLayout(rows, cols, GAP_PX, GAP_PX));
        setBorder(BorderFactory.createLoweredBevelBorder());

        // Add cells for all tiles
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                GridTilePanel tilePanel = new GridTilePanel(row, col, controller);
                this.add(tilePanel);
            }
        }
    }
}
