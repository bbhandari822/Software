import java.util.ArrayList;

/**
 * Created by Binod Bhandari on 12/2/17.
 */
public class HelperClass {

    private int state;
    private ArrayList<ObserverClass> observers = new ArrayList<>();

    public void setState(int state) {
        notifyAllObservers();
    }

    public void attach(ObserverClass observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (ObserverClass observer : observers) {
            observer.update();
        }
    }
}
