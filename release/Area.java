package release;

import java.util.List;

/* We used a composite to fulfill the functionalities of space partition, area is the base abstract
*  class which can't be instantiated but inherits two more types; Partition and Space. The main feature
*  of this structure is to form a tree-like graph which holds subareas with spaces and doors. In each
*  class you'll find more specific information of the class in the composite implementation.  */
public abstract class Area {
    // -----------------------------------Attribute section------------------------------------------- \\
    protected String id;
    protected Area parent = null;

    // ------------------------------------Method section--------------------------------------------- \\

    //Constructors
    public Area(String id){
        this.id = id;
    }
    public Area(String name, Area parent){ this.id = name; this.parent = parent; }

    // Getters and setters
    public String getId(){
        return id;
    }
    public void setId(String id) { this.id = id; }
    public Area getParent() { return parent; }
    public void setParent(Area parent) { this.parent = parent; }

    // Abstract methods to implement
    public abstract Area getAreaById(String id);
    public abstract List<Door> getDoors();
}
