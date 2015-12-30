package de.htwg.qwirkle.model;

/**
 * Created by niboecke on 30.10.2015.
 */
public class VariousTests {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_TEST = "\u001B[41m";

    public static void main(String[] args) {

        System.out.println(ANSI_TEST + "CI" + ANSI_RESET + " "+ ANSI_TEST + "DI" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "This text is red!" + ANSI_RESET);
        System.out.println("\u00a5123");
        System.out.println("\u00a2");
        System.out.println("\u2666");

    }
}
