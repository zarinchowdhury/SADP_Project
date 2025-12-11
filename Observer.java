// ========================================================
// OBSERVER PATTERN
// ========================================================
interface Observer {
void update(String message);
}


interface Subject {
void attach(Observer obs);
void notifyAllObservers(String msg);
}


class NotificationCenter implements Subject {
private final java.util.List<Observer> observers = new java.util.ArrayList<>();
public void attach(Observer obs){ observers.add(obs); }
public void notifyAllObservers(String msg){ observers.forEach(o -> o.update(msg)); }
}