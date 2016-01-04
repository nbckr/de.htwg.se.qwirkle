package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQControllerGui;
import util.Constants;
import util.observer.IObserver;
import util.observer.QEvent;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;

/**
 * Created by niels on 18.12.2015.
 */
public class HandPanel extends JPanel implements IObserver {

    private static final int SPACING = 5;
    private IQControllerGui controller;
    protected List<JLabel> innerTilePanels;

    public HandPanel(IQControllerGui controller) {

        this.controller = controller;
        controller.addObserver(this);

        setLayout(new FlowLayout());
        setBorder(Constants.INNER_BORDER);

        innerTilePanels = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            JPanel outerPanel = new JPanel();
            outerPanel.setBorder(BorderFactory.createEmptyBorder(SPACING, SPACING, SPACING, SPACING));
            outerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

            HandTileLabel handTilePanel = new HandTileLabel(null, controller, i);
            innerTilePanels.add(handTilePanel);

            outerPanel.add(handTilePanel);
            this.add(outerPanel);
        }
    }

    @Override
    public void update(QEvent e) {
        // neccesary?
    }
}
