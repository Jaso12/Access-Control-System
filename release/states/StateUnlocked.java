package release.states;

import release.Door;

public class StateUnlocked implements State {
  // ------------------------------------Method section--------------------------------------------- \\
  public State lock() { return new StateLocked(); }
  public State unlock() { return this; }
  public State unlockShortly(Door door) { return this; }

  @Override
  public void open(Door door) {
    door.changeClosed(false);
  }

  @Override
  public void close(Door door) {
    door.changeClosed(true);
  }

}