package de.htwg.qwirkle.aview.gui;

import util.Constants;
import util.StretchIcon;

import javax.swing.*;
import java.awt.*;

public class LogoPanel extends JPanel {

    private static final String LOGO_PATH = "src/main/resources/img/logo.png";
    private StretchIcon logoIcon;
    private JLabel logoLabel;

    public LogoPanel() {
        logoLabel = new JLabel();
        logoIcon = new StretchIcon(LOGO_PATH, true);
        logoLabel.setIcon(logoIcon);

        setBorder(Constants.INNER_BORDER);

        add(logoLabel);

        logoLabel.setPreferredSize(new Dimension(Constants.SIDE_PANEL_WIDTH-40, 120));
    }
}
