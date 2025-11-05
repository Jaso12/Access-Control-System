package release.states;

import release.*;

public interface State {
  void open(Door door);
  void close(Door door);

  State lock();
  State unlock();
  State unlockShortly(Door door);
}
