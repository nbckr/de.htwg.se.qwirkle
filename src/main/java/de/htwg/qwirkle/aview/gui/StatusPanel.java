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

    private final JLabel statusLabel = new JLabel("");

    public StatusPanel(IQControllerGui controller) {
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        this.add(statusLabel);
    }

    public void setText(String statusMessage) {
        statusLabel.setText(" " + statusMessage);
    }

    public void clear() {
        statusLabel.setText(" ");
    }
}
