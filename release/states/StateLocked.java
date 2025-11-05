package release.states;

import java.util.Timer;
import java.util.TimerTask;
import release.*;

public class StateLocked implements State {
  // ------------------------------------Method section--------------------------------------------- \\
  public State lock() { return this; }
  public State unlock() { return new StateUnlocked(); }
  public State unlockShortly(Door door) {
    new DoorObserver(door, 10);  // Observador para contar los 10 segundos
    return new StateUnlockedShortly();
  }

  @Override
  public void open(Door door) {
    System.out.println("Can't open door " + door.getId() + " because it's locked");
  }

  @Override
  public void close(Door door) {
    door.changeClosed(true);
  }
}
