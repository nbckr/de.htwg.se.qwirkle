package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQControllerGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by niels on 18.12.2015.
 */
public class MenuBar extends JMenuBar implements ActionListener {

    IQControllerGui controller;

    JMenu fileMenu;
    JMenuItem newMenuItem, quitMenuItem;

    JMenu helpMenu;
    JMenuItem instructionsMenuItem, aboutMenuItem;

    public MenuBar(IQControllerGui controller) {

        this.controller = controller;

        // File Menu
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        newMenuItem = new JMenuItem("New");
        newMenuItem.addActionListener(this);
        fileMenu.add(newMenuItem);

        fileMenu.addSeparator();

        quitMenuItem = new JMenuItem("Quit");
        quitMenuItem.addActionListener(this);
        fileMenu.add(quitMenuItem);

        // Help Menu
        helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);

        instructionsMenuItem = new JMenuItem("Instructions");
        instructionsMenuItem.addActionListener(this);
        helpMenu.add(instructionsMenuItem);

        aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(this);
        helpMenu.add(aboutMenuItem);

        // Add Menus to MenuBar
        this.add(fileMenu);
        this.add(helpMenu);
    }

    public void showNewGameDialog(IQControllerGui controller) {
        new NewGameDialog(controller);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == newMenuItem) {
            this.showNewGameDialog(controller);
        }

        if (e.getSource() == quitMenuItem) {
            controller.exit();
        }

        if (e.getSource() == instructionsMenuItem) {
            JOptionPane.showMessageDialog(null, "It's really easy :)",
                    "Instructions", JOptionPane.INFORMATION_MESSAGE);
        }

        if (e.getSource() == aboutMenuItem) {
            JOptionPane.showMessageDialog(null, "(c) by Niels Boecker \n" +
                    "& Lukas Luschin 2016", "About", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
