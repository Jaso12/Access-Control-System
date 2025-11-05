package release;

public final class DirectoryAreas {
  // ----------------------------------Attribute section------------------------------------------- \\
  private static Area root;

  // ------------------------------------Method section--------------------------------------------- \\
  public static void makeAreas() {
    // Root Area --> type design decision: it's a Partition.
    Partition building = new Partition("building");
    root = building;

    // Partitons of root
    Partition basement = new Partition("basement", building);
    Partition ground_floor = new Partition("ground_floor", building);
    Partition floor_1 = new Partition("floor1", building);

    building.addSubArea(basement);
    building.addSubArea(ground_floor);
    building.addSubArea(floor_1);

    // Spaces of basement
    Space parking = new Space("parking");
    Space stairs = new Space("stairs"); //added to in ground_flor and floor 1 spaces
    Space exterior = new Space("exterior");

    basement.addSubArea(parking);
    basement.addSubArea(stairs);
    basement.addSubArea(exterior);

    // Spaces of ground_floor
    Space room1 = new Space("room1");
    Space room2 = new Space("room2");
    Space hall = new Space("hall");

    ground_floor.addSubArea(room1);
    ground_floor.addSubArea(room2);
    ground_floor.addSubArea(hall);
    ground_floor.addSubArea(exterior);
    ground_floor.addSubArea(stairs);

    // Spaces of floor 1
    Space room3 = new Space("room3");
    Space corridor = new Space("corridor");
    Space it = new Space("IT");

    floor_1.addSubArea(room3);
    floor_1.addSubArea(corridor);
    floor_1.addSubArea(it);
    floor_1.addSubArea(stairs);

    /*Getting all doors and then adding them to each space.
      Also setting each door from what space to what space
      they connect. **/
    Door d1 = DirectoryDoors.findDoorById("D1");
    Door d2 = DirectoryDoors.findDoorById("D2");
    Door d3 = DirectoryDoors.findDoorById("D3");
    Door d4 = DirectoryDoors.findDoorById("D4");
    Door d5 = DirectoryDoors.findDoorById("D5");
    Door d6 = DirectoryDoors.findDoorById("D6");
    Door d7 = DirectoryDoors.findDoorById("D7");
    Door d8 = DirectoryDoors.findDoorById("D8");
    Door d9 = DirectoryDoors.findDoorById("D9");

    d1.setFromToSpace(exterior, parking);
    exterior.addDoor(d1);
    parking.addDoor(d1);

    d2.setFromToSpace(stairs, parking);
    stairs.addDoor(d2);
    parking.addDoor(d2);

    d3.setFromToSpace(exterior, hall);
    exterior.addDoor(d3);
    hall.addDoor(d3);

    d4.setFromToSpace(stairs, hall);
    stairs.addDoor(d4);
    hall.addDoor(d4);

    d5.setFromToSpace(hall, room1);
    hall.addDoor(d5);
    room1.addDoor(d5);

    d6.setFromToSpace(hall, room2);
    hall.addDoor(d6);
    room2.addDoor(d6);

    d7.setFromToSpace(stairs, corridor);
    stairs.addDoor(d7);
    corridor.addDoor(d7);

    d8.setFromToSpace(corridor, room3);
    corridor.addDoor(d8);
    room3.addDoor(d8);

    d9.setFromToSpace(corridor, it);
    corridor.addDoor(d9);
    it.addDoor(d9);
  }

  public static Area getRoot() { return root; }

  public static Area findAreaById(String areaId) { return root.getAreaById(areaId); }
}
