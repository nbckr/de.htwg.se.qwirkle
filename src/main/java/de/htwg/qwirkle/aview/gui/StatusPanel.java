// mostly copied from Sudoku
package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQControllerGui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by niels on 18.12.2015.
 */
public class StatusPanel extends JPanel {

    private final JLabel statusLabel;

    public StatusPanel(IQControllerGui controller) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        statusLabel = new JLabel("");
        add(statusLabel);
    }

    public void setText(String statusMessage) {
        statusLabel.setText(" " + statusMessage);
    }

    public void clear() {
        statusLabel.setText(" ");
    }
}
