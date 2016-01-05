package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQController;
import de.htwg.qwirkle.model.Player;
import util.Constants;
import util.observer.IObserver;
import util.observer.QEvent;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;

/**
 * Created by niels on 03.01.2016.
 */
public class PlayerScorePanel extends JPanel implements IObserver {

    private IQController controller;
    List<PlayerTextLabel> labels;
    private boolean initialized;

    public PlayerScorePanel(IQController controller) {
        this.controller = controller;
        controller.addObserver(this);

        initialized = false;
        labels = new ArrayList<>();

        //setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(Constants.INNER_BORDER);
    }

    public void init() {
        for (Player player : controller.getPlayers()) {
            PlayerTextLabel label = new PlayerTextLabel(player);
            labels.add(label);
            this.add(label);
        }
        initialized = true;
    }

    @Override
    public void update(QEvent e) {
        if (controller.getState() != IQController.State.UNINIZIALIZED) {
            if (!initialized) {
                init();
            }
            for (PlayerTextLabel label : labels) {
                label.refreshText();
            }
        }
    }

    class PlayerTextLabel extends JLabel {
        Player player;

        public PlayerTextLabel(Player player) {
            this.player = player;
        }

        public void refreshText() {
            StringBuilder sb = new StringBuilder(player.getName());
            sb.append(" (Score: " + player.getScore() + ")");
            setText(sb.toString());

            if (player == controller.getCurrentPlayer()) {
                setForeground(Color.RED);
            } else {
                setForeground(Color.BLACK);
            }
        }
    }
}
