package release;

import java.util.Observer;
import java.util.Observable;
import release.states.*;

/* The DoorObserver interface is part of the Observer pattern used in the door state system. It allows
*  implementing classes to define specific behaviors in the update method, which the Clock calls on each
*  time tick to notify observers of the passage of time.*/
public class DoorObserver implements Observer {
    // ----------------------------------Attribute section------------------------------------------- \\
    private final Door door;
    private int secondsElapsed = 0;
    private final int duration;

    // ------------------------------------Method section--------------------------------------------- \\
    public DoorObserver(Door door, int duration) {
        this.door = door;
        this.duration = duration;
        Clock.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        secondsElapsed++;

        if (secondsElapsed >= duration) {
            if (door.isClosed()) {
                door.changeState(new StateLocked());
            } else {
                door.changeState(new StatePropped());
            }
            Clock.getInstance().deleteObserver(this); // Stop observing after 10 seconds
        }
    }
}
