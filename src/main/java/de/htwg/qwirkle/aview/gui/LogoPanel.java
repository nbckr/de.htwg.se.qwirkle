package de.htwg.qwirkle.aview.gui;

import util.Constants;
import util.StretchIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class LogoPanel extends JPanel {

    private static final String LOGO_PATH = "src/main/resources/img/logo.png";
    private StretchIcon logoIcon;
    private JLabel logoLabel;

    public LogoPanel() {
        logoLabel = new JLabel();
        logoIcon = new StretchIcon(LOGO_PATH, true);
        logoLabel.setIcon(logoIcon);
        logoLabel.setText(".,..........................................................");

        setBorder(Constants.INNER_BORDER);

        add(logoLabel);

        logoLabel.setSize(200,200);
    }
}
