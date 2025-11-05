package release;

import release.requests.RequestReader;
import org.json.JSONObject;
import release.states.*;

public class Door {
  // -----------------------------------Attribute section------------------------------------------- \\
  private final String id;
  private boolean closed = true; // physically
  private State state = new StateUnlocked(); // Managing the door actions itself, used pattern: Composite.
  private Space fromSpace = null;  // Needed to do checking afterward in Area management.
  private Space toSpace = null;

  // ------------------------------------Method section--------------------------------------------- \\
  public Door(String id) { this.id = id; }
  public Door(String id, Space fromSpace, Space toSpace) {
    this.id = id;
    this.fromSpace = fromSpace;
    this.toSpace = toSpace;
  }
  public Door(String id, boolean closed, State state, Space fromSpace, Space toSpace) {
    this.id = id;
    this.closed = closed;
    this.state = state;
    this.fromSpace = fromSpace;
    this.toSpace = toSpace;
  }

  public void changeClosed(Boolean bool){ this.closed = bool; }
  public void changeState(State state){ this.state = state; }

  public String getId() { return id; }
  public State getState() {return state; }

  public boolean isClosed() { return closed; }

  public void setFromToSpace(Space from, Space to){ this.fromSpace = from; this.toSpace = to; }
  public Space getFromSpace() { return fromSpace; }
  public Space getToSpace() { return toSpace; }

  public String getStateName() {
    if (state instanceof StateLocked)
      return "locked";
    else if (state instanceof StateUnlockedShortly)
        return "unlocked_shortly";
    else if (state instanceof StatePropped)
      return "propped";

    return "unlocked";
  }

  public void processRequest(RequestReader request) {
    // it is the Door that process the request because the door has and knows
    // its state, and if closed or open
    if (request.isAuthorized()) {
      String action = request.getAction();
      doAction(action);
    } else {
      System.out.println("not authorized");
    }
    request.setDoorStateName(getStateName());
  }

  private void doAction(String action) {
    switch (action) {
      case Actions.OPEN:
        state.open(this);
        break;
      case Actions.CLOSE:
        state.close(this);
        break;
      case Actions.LOCK:
        //Giving a new instance of state subclass (StateLocked) to lock the specified door
        state = state.lock();
        break;
      case Actions.UNLOCK:
        //Giving a new instance of state subclass (StateUnLocked) to unlock the specified door
        state = state.unlock();
        break;
      // fall through
      case Actions.UNLOCK_SHORTLY:
        //Giving a new instance of state subclass (StateUnLockedShortly) to unlockShortly the specified door
        state = state.unlockShortly(this);
        break;
      default:
        assert false : "Unknown action " + action;
        System.exit(-1);
    }
  }

  @Override
  public String toString() {
    return "Door{"
        + ", id='" + id + '\''
        + ", closed=" + closed
        + ", state=" + getStateName()
        + "}";
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("state", getStateName());
    json.put("closed", closed);
    return json;
  }
}
