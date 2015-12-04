/*
Source: https://moodle.htwg-konstanz.de/moodle/course/view.php?id=713
 */

package util.observer;

import java.lang.Override;
import java.util.ArrayList;
import java.util.List;

public abstract class Observable implements IObservable {

    private List<IObserver> subscribers = new ArrayList<IObserver>(2);

    @Override
    public void addObserver(IObserver s) {
        subscribers.add(s);
    }

    @Override
    public void removeObserver(IObserver s) {
        subscribers.remove(s);
    }

    @Override
    public void removeAllObservers() {
        subscribers.clear();
    }

    @Override
    public void notifyObservers() {
        notifyObservers(new QEvent(QEvent.Events.def));
    }

    @Override
    public void notifyObservers(QEvent e) {
        for (IObserver observer: subscribers) {
            observer.update(e);
        }
    }
}