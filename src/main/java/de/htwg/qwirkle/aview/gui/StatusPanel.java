package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQController;
import de.htwg.qwirkle.controller.IQControllerGui;
import util.Constants;
import util.observer.IObserver;
import util.observer.QEvent;

import javax.swing.*;
import java.awt.*;

/**
 * Created by niels on 18.12.2015.
 */
public class StatusPanel extends JPanel implements IObserver {

    private static final Dimension SIZE = new Dimension(Constants.SIDE_PANEL_WIDTH, 40);
    private final JLabel statusLabel;
    private IQController controller;

    public StatusPanel(IQControllerGui controller) {

        this.controller = controller;
        controller.addObserver(this);

        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBorder(Constants.INNER_BORDER);
        setPreferredSize(SIZE);

        statusLabel = new JLabel("");
        add(statusLabel);
    }

    public void setText(String statusMessage) {
        statusLabel.setText(statusMessage);
    }

    public void clear() {
        statusLabel.setText("");
    }

    @Override
    public void update(QEvent e) {
        setText(controller.getStatusMessage());
    }
}
