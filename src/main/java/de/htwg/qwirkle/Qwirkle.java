/*
Main class to start Quirkle.
Based on blueprint from https://moodle.htwg-konstanz.de/moodle/course/view.php?id=713
 */

package de.htwg.qwirkle;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.qwirkle.aview.tui.TextUI;
import de.htwg.qwirkle.aview.gui.QFrame;
import de.htwg.qwirkle.controller.IQController;
import de.htwg.qwirkle.controller.impl.QController;

public final class Qwirkle {

    private static QController controller;
    private static QFrame qFrame;
    private static TextUI textUI;

    private Qwirkle() {
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new QwirkleModule());

        controller = (QController) injector.getInstance(IQController.class);

        qFrame = new QFrame(controller);
        qFrame.doNothing();

        textUI = new TextUI(controller);
        textUI.doNothing();
    }

}
