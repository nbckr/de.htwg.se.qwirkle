package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQControllerGui;
import util.Constants;
import util.observer.IObserver;
import util.observer.QEvent;

import javax.swing.*;
import java.awt.*;

public class HandPanel extends JPanel {

    private static final int SPACING = 5;

    public HandPanel(IQControllerGui controller) {

        setLayout(new FlowLayout());
        setBorder(Constants.INNER_BORDER);

        for (int i = 1; i < 7; i++) {
            JPanel outerPanel = new JPanel();
            outerPanel.setBorder(BorderFactory.createEmptyBorder(SPACING, SPACING, SPACING, SPACING));
            outerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

            HandTileLabel handTilePanel = new HandTileLabel(null, controller, i);
            outerPanel.add(handTilePanel);
            this.add(outerPanel);
        }
    }
}
