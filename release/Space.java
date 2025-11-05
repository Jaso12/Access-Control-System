package release;

import java.util.List;
import java.util.ArrayList;

/* They represent the leaves of the three structure formated with partitions and them. They contain all
*  the doors of a room/space. */
public class Space extends Area {
    // ----------------------------------Attribute section------------------------------------------- \\
    // Although now there's virtually no way of changing this attribute in the future might be useful.
    private List<Door> doors = new ArrayList<>();

    // ------------------------------------Method section--------------------------------------------- \\
    public Space(String id) { super(id); }

    public void addDoor(Door door) { doors.add(door); }

    /* Ternary comparison that returns itself if the argument ID matches the area ID or null if not.*/
    @Override
    public Area getAreaById(String id) {
        return this.id.equals(id) ? this : null;
    }

    @Override
    public List<Door> getDoors(){
        // Returns doors that give access only to the space.
        List<Door> toDoors = new ArrayList<>();

        for (Door door : doors)
            if (door.getToSpace() == this)
                toDoors.add(door);

        return toDoors;
    }
}
