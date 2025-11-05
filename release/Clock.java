package release;

import java.util.Observable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import release.states.*;

/* The Clock class is responsible for notifying registered DoorObservers at regular intervals, such as
*  every second. It provides methods to add or remove observers and to notify all registered observers
*  by calling their update() method. This allows observers to implement time-sensitive actions without
*  managing their own timers.*/
public class Clock extends Observable {
    // ----------------------------------Attribute section------------------------------------------- \\
    private static Clock instance;
    private final ScheduledExecutorService scheduler;

    // ------------------------------------Method section--------------------------------------------- \\
    private Clock() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            setChanged();
            notifyObservers();
        }, 0, 1, TimeUnit.SECONDS);
    }

    public static synchronized Clock getInstance() {
        if (instance == null) {
            instance = new Clock();
        }
        return instance;
    }
}
