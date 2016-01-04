package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQController;
import util.ConstantValues;
import util.observer.IObserver;
import util.observer.QEvent;

import javax.swing.*;
import java.awt.*;

/**
 * Created by niels on 03.01.2016.
 */
public class PlayerScorePanel extends JPanel implements IObserver {

    private static final Dimension SIZE = new Dimension(QFrame.SIDE_PANEL_WIDTH, 100);
    private IQController controller;
    private JLabel textLabel;

    public PlayerScorePanel(IQController controller) {

        this.controller = controller;
        controller.addObserver(this);

        this.setBorder(ConstantValues.INNER_BORDER);
        this.setPreferredSize(SIZE);

        textLabel = new JLabel("");
        textLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
        textLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        this.add(textLabel);
        refreshText();
    }

    private void refreshText() {

        if (controller.getState() != IQController.State.UNINIZIALIZED) {
            StringBuilder sb = new StringBuilder("Current Player: ");
            sb.append(controller.getCurrentPlayer().getName());
            sb.append(", Score: ");
            sb.append(controller.getCurrentPlayer().getScore());
            textLabel.setText(sb.toString());
        }
    }

    public void clear() {
        textLabel.setText("");
    }

    @Override
    public void update(QEvent e) {
        refreshText();
    }
}
