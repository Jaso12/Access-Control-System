package release.states;

import release.Door;

public class StateUnlockedShortly implements State {
  // ------------------------------------Method section--------------------------------------------- \\
  public State lock() { return new StateLocked(); }
  public State unlock() { return new StateUnlocked(); }

  public State unlockShortly(Door door) { return this; }

  public void open(Door door) {
    door.changeClosed(false);
  }

  public void close(Door door) {
    door.changeClosed(true);
  }
}
