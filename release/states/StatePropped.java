package release.states;

import release.Door;

public class StatePropped implements State {
  public State lock() { return this; }
  public State unlock() { return this; }
  public State unlockShortly(Door door) { return this; }

  @Override
  public void open(Door door) { }

  @Override
  public void close(Door door) {
    door.changeClosed(true);
    door.changeState(new StateLocked());
  }
}
