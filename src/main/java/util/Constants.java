package util;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by niboecke on 30.10.2015.
 */
public abstract class Constants {

    // color values used in TUI
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BOLD = "\u001B[1m";
    public static final String ANSI_BLACK = "\u001B[30m";

    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BG = "\u001B[40m";
    public static final String ANSI_YELLOW_BG = "\u001B[43m";
    public static final String ANSI_RED_BG = "\u001B[41m";
    public static final String ANSI_BLUE_BG = "\u001B[44m";
    public static final String ANSI_GREEN_BG = "\u001B[42m";
    public static final String ANSI_PURPLE_BG = "\u001B[45m";
    public static final String ANSI_CYAN_BG = "\u001B[46m";

    // Text messages used in TUI
    public static final String INSTR_TUI = "List of commands:\n" +
            "\ta - add tile(s) to grid\n\t" +
            "t - trade in tile(s)\n" +
            "\tn - new game\n" +
            "\tq - quit";
    public static final String WELCOME = "Welcome to QWIRKLE!";
    public static final String SEEYOU = "\nSee you later!";
    public static final String INVALID = "invalid entry.";

    // Swing values used in GUI
    public static final Dimension HAND_TILE_SIZE = new Dimension(75, 75);
    public static final int SIDE_PANEL_WIDTH = 350;
    public static final Dimension GRID_PANEL_SIZE = new Dimension(900, 900);
    public static final Border INNER_BORDER = BorderFactory.createLineBorder(Color.BLACK, 5);

    // Text messages used in GUI
    public static final String INSTR_CHOOSE =
            "<html>You can either add one or more tiles to <br>" +
            "the grid or you can trade one or more tiles.</html>";
    public static final String INSTR_ADD =
            "<html>Select one tile on your hand and click on the <br>" +
            "desired position on the grid. You can repeat this <br>" +
            "until there are no more tiles on your hand.</html>";
    public static final String INSTR_TRADE =
            "Select all tiles you want to trade and click on OK";
    public static final String INSTR_GUI = "It's really easy :)";
    public static final String ABOUT = "(c) by Niels Boecker & Lukas Luschin 2016";

    private Constants() {
        // prevent class from being instantiated
    }
}
