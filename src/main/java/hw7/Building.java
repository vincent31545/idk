package hw7;

// A Building is an RPI campus structure that has a name and location in RPI.

public class Building {
	// Abstraction Function:
    // A building has a name (even if it is an empty string), an id, an x coordinate, and an y coordinate. The x coordinate and the y coordinate correspond
	// to the pixel value of the RPI map picture. The building name refers the the buildings actual name at RPI campus, and the
	// id is a unique identifier of the RPI event. The id does not have any restrictions, but it makes sense that the id should not
	// have non-negative numbers. This Building class is not made to be modified and does not allow any direct methods to modify the
	// class.

    // Representation invariant for every Building b:
    //   b.name != null &&
	//	 b.id != null &&
	//   b.x != null &&
	//   b.y != null
	//
    //   In other words,
    //     * every private variable must be defined at the end of the objects creation
	
    private String name;
    private int id;
    private int x;
    private int y;

	// @requires nothing
	// @param String name: the name of the building
	// @param int x: the x coordinate
	// @param int y: the y coordinate
    // @param int id: the id of the building
	// @effects stores all the parameters into the class private varaibles
	// @modifies this.name, this.x, this.y, this.id
	// @throws nothing
	// @returns nothing
    public Building(String name, int id, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.id = id;
    }
    
	// @requires nothing
	// @param nothing
	// @effects nothing
	// @modifies nothing
	// @throws nothing
	// @returns the building's name
    public String getName() {
        return name;
    }
    
	// @requires nothing
	// @param nothing
	// @effects nothing
	// @modifies nothing
	// @throws nothing
	// @returns the building's id
    public int getId() {
        return id;
    }
    
	// @requires nothing
	// @param nothing
	// @effects nothing
	// @modifies nothing
	// @throws nothing
	// @returns the building's x coordinate
    public int getX() {
        return x;
    }
    
	// @requires nothing
	// @param nothing
	// @effects nothing
	// @modifies nothing
	// @throws nothing
	// @returns the building's y coordinate
    public int getY() {
        return y;
    }
}