package release;

import java.util.ArrayList;
import java.util.List;

/* Main node of the tree structure that holds a polymorphic array of areas which can be: more partitions
*  or directly a Space. (tree leaf)*/
public class Partition extends Area {
    // ----------------------------------Attribute section------------------------------------------- \\
    private List<Area> sub_areas = new ArrayList<>();

    // ------------------------------------Method section--------------------------------------------- \\
    public Partition(String id) { super(id); }
    public Partition(String id, Area parent) { super(id, parent); }
    public Partition(String id, Area parent, ArrayList<Area> sub_areas) { super(id, parent); this.sub_areas = sub_areas; }

    /* Methods for managing the array of subareas. */
    public void addSubArea(Area sub_area) { this.sub_areas.add(sub_area); }
    public void removeSubArea(Area sub_area) { this.sub_areas.remove(sub_area); }

    /*  Imagine the partitions and spaces like a tree. Then, for a certain partition this method returns
        all the leaves of his branch (if it faces another partition it'll use execute recursively)*/
    public List<Space> getSubspaces() {
        List<Space> allSubSpaces = new ArrayList<>();

        for (Area area : sub_areas) {
            if (area instanceof Partition)
                allSubSpaces.addAll(((Partition) area).getSubspaces());
            else
                allSubSpaces.add((Space) area);
        }

        return allSubSpaces;
    }

    /* Recursive method to get the area with a specific ID, it checks first if the partition itself is
       the target Area. If not, for all its subareas it will call the same method and check if it returns
       an object of Area to also return it. If it instead gets null it will check the next one and so on
       until it finishes, then if no area found it will also return null.*/
    @Override
    public Area getAreaById(String id) {
        if (this.id.equals(id))
            return this;

        for (Area area : sub_areas) {
            Area newArea = area.getAreaById(id);
            if (newArea != null)
                return newArea;
        }

        return null;
    }

    /* Recursive method to retrieve all doors of a partition. It will go throughout all sub_areas deep down
       the three until it arrives to space where it will retrieve the doors of it. Then for that doors it
       will check two things: 1. If the door is already on the return array, and 2. check if the door
       received matches that it's attributes "from" and "to" are in the leaves of the partitions, the
       subspaces. (This will prevent changing the state of shared spaces like stairs)*/
    @Override
    public List<Door> getDoors(){
        List<Door> doors = new ArrayList<>();
        List<Space> allSubSpaces = getSubspaces();

        for(Area area : sub_areas) {
            List<Door> sub_doors = area.getDoors();

            for (Door door : sub_doors)
                if (!doors.contains(door) && allSubSpaces.contains(door.getToSpace()) && allSubSpaces.contains(door.getFromSpace()))
                    doors.add(door);
        }
        return doors;
    }
}
