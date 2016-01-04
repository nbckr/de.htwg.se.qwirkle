package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQControllerGui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by niels on 18.12.2015.
 */
public class GridPanel extends JPanel {

    private static final int GAP_PX = 2;

    public GridPanel(IQControllerGui controller) {

        int rows = controller.getNumRows();
        int cols = controller.getNumCols();

        setLayout(new GridLayout(rows, cols, GAP_PX, GAP_PX));
        setBorder(QFrame.INNER_BORDER);

        // Add cells for all tiles
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                GridTilePanel gridTilePanel = new GridTilePanel(row, col, controller);
                this.add(gridTilePanel);
            }
        }

        setPreferredSize(new Dimension(800, 800));

        /*

        // make the Tiles always keep a 1:1 aspect ratio
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
