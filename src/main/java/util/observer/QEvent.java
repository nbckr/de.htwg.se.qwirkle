/*
Source: https://moodle.htwg-konstanz.de/moodle/course/view.php?id=713
 */

package util.observer;

public class QEvent {

    private Event event;

    public QEvent() {
        this(Event.DEFAULT);
    }

    public QEvent(Event e) {
        this.event = e;
    }

    public Event getEvent() {
        return this.event;
    }

    public enum Event {
        DEFAULT,    // default value
        GET_PLAYER, // specifies number of players and initialize them
        MESSAGE,    // print the controller message
        NEXTPLAYER, IGNORE      // do nothing
    }

}
