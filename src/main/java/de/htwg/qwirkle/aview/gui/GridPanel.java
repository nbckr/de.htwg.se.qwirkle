package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQControllerGui;
import util.Constants;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {

    private static final int GAP_PX = 2;

    public GridPanel(IQControllerGui controller) {

        int rows = controller.getNumRows();
        int cols = controller.getNumCols();

        setLayout(new GridLayout(rows, cols, GAP_PX, GAP_PX));
        setBorder(Constants.INNER_BORDER);

        // Add cells for all tiles
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                GridTileLabel gridTilePanel = new GridTileLabel(row, col, controller);
                this.add(gridTilePanel);
            }
        }

    }
}
