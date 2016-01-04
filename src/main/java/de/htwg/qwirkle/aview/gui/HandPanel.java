package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQControllerGui;
import util.Constants;
import util.observer.IObserver;
import util.observer.QEvent;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class HandPanel extends JPanel implements IObserver {

    private static final int SPACING = 5;
    private IQControllerGui controller;

    public HandPanel(IQControllerGui controller) {

        this.controller = controller;
        controller.addObserver(this);

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

    @Override
    public void update(QEvent e) {
        // neccesary?
    }
}
