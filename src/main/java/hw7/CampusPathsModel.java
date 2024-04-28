package hw7;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import hw4.Graph;
import hw6.LegoPaths;

//A CampusPaths is an object that allows you to take in csv files of building information,
//connected information, and intersection information. Because there are no negative edges,
//you can always use dijkstras algorithm to get a valid shortest path. This object allows for
//the creation of an interface to display the informaiton easily.

public class CampusPathsModel {
	// Abstraction Function:
    // A CampusPathModel uses the Graph class to make a single-weight directed graph. The graph can have up to two
	// connections between each point, but they must be traveling in the same direction. This is not controlled by
	// the object itself but by the data it is given. The object itself must correctly parse the information and provides
	// multiple ways of accessing information like the shortest path given ids, building names, or a mixture of both.
	// it must handle invalid building names and ids. 

    // Representation invariant for every CampusPathsModel cpm (assuming data is valid, ex. no duplicate building names and/or ids and/or coordinates):
	//   for building in cpm.buildings ==> !building.getName().equals("") &&
	//   for building in cpm.buildings ==> building.getName() != null && building.getId() != null && building.getX() != null && building.getY() != null &&
	//   for building in cpm.buildings ==> Collections.frequency(buildings, building.getName()) == 1 &&
	//   for building in cpm.buildings ==> Collections.frequency(buildings, building.getId()) == 1 &&
	//	 for building1 in cpm.buildings for building2 in cpm.buildings (building1.getX() == building2.getX() && building1.getY() == building.getY()) &&
	//   cpm.totalCost >= 0
	
    //   In other words,
    //     * a building is fully defined in the arrayList buildings
	//     * a building has no duplicate information
	// 	   * the totalCost is non negative
	
	private static ArrayList<Building> buildings;
	private static HashMap<Integer, Building> map;
	public static Graph<Integer, Double> g;
	private static double totalCost;
	
	// @requires nothing
	// @param nothing
	// @effects returns the totalCost of a path
	// @modifies nothing
	// @throws nothing
	// @returns totalCost
	public static double getTotalCost() {
		return totalCost;
	}
	
	// @requires nothing
	// @param nothing
	// @effects returns a copy of the buildings
	// @modifies nothing
	// @throws nothing
	// @returns ArrayList: all building objects
	public static ArrayList<Building> getBuildings() {
		ArrayList<Building> temp = new ArrayList<>(buildings);
		return temp;
	}
	
	// @requires hasBuilding(input) == true
	// @param String buildingName
	// @effects searches for the id of a building by matching name
	// @modifies nothing
	// @throws nothing
	// @returns the id of the building if it exists
	// @returns the -1 if the building does not exist
	public static String getBuildingName(String input) {
		int num = getIdBuilding(input);
		if (num != -1)
			return map.get(num).getName();
		return map.get(Integer.parseInt(input)).getName();
	}
		
	// @requires nothing
	// @param String buildingName
	// @effects searches for the id of a building by matching name
	// @modifies nothing
	// @throws nothing
	// @returns the id of the building if it exists
	// @returns the -1 if the building does not exist
	private static int getIdBuilding(String buildingName) {
		for (Building building : buildings)
			if (building.getName().equals(buildingName))
				return building.getId();
		return -1;
	}
	
	// @requires nothing
	// @param String idInput: the String input from the user
	// @effects converts string to a check if that building exists ie(if the id matches a building or the name matches a building)
	// @modifies nothing
	// @throws nothing
	// @returns true if the building exists
	// @return false if the building does not exist
	public static boolean hasBuilding(String idInput1) {
		// parse to int
		int id1 = -1;
		try {
			id1 = Integer.parseInt(idInput1);
		} catch (NumberFormatException e) {
			id1 = getIdBuilding(idInput1);
		}
		
		if (!map.containsKey(id1) || map.get(id1).getName().equals(""))
			return false;
		return true;
	}
	
	// @requires map.containsKey(startBuilding) && map.containsKey(endBuilding) && !map.get(startBuilding).getName().equals("") && !map.get(startBuilding).getName().equals("")
	// @param int startBuilding: the id of the building in which you are traveling from
	// @param int endBuilding: the id of the building in which you are traveling to
	// @effects gives the string path if it exists
	// @modifies nothing
	// @throws nothing
	// @returns String of the directions to go from the start building to the end building
	public static ArrayList<Building> dijkstras(String startBuilding, String endBuilding) {
		int start = getIdBuilding(startBuilding);
		int end = getIdBuilding(endBuilding);
		ArrayList<Building> ans = new ArrayList<>();
		totalCost = 0;
		// get the shortest path by lego path
		ArrayList<Integer> path = LegoPaths.findPath(start == -1 ? Integer.parseInt(startBuilding) : start, 
										   			 end == -1 ? Integer.parseInt(endBuilding) : end);
		for (int i = 1; i < path.size(); i++) {
			int b1 = path.get(i-1);
        	int b2 = path.get(i);
			ans.add(map.get(b2));
			totalCost += g.getEdgeWeight(b1, b2).get(0);
		}
		return ans;
	}
	
	// @requires nothing
	// @param String filename: the string path that leads to the csv
	// @effects adds the data to the graph and the map
	// @modifies Graph<Integer, Double> g and HashMap<Integer, Building> map
	// @throws nothing
	// @returns nothing
	private static void parseNodes(String filename) throws IOException {
		map = new HashMap<>();
		g = new Graph<>();
		
	    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	        	// get the different info into an array
	        	String[] lineComponents = line.split(",");
	            if (lineComponents.length != 4)
	                throw new IOException("File " + filename + " not a CSV (BUILDING_NAME,ID,X_COORDIANTE,Y_COORDINATE) file.)");
	            
	            // attempt to parse the info
	            String building = lineComponents[0];
	            int id;
	            int x;
	            int y;
	            try {
                    id = Integer.parseInt(lineComponents[1]);
                    x = Integer.parseInt(lineComponents[2]);
                    y = Integer.parseInt(lineComponents[3]);
                    
                    // add to data structures
                	map.put(id, new Building(building, id, x, y));
                	g.addNode(id);
                } catch (NumberFormatException e) {
                	throw new IOException("File " + filename + " has invalid id or coordinates");
                }
	        }
	    }
	}
	
	// @requires nothing
	// @param int x1: x-coordinate of the first building
	// @param int y1: y-coordinate of the first building
	// @param int x2: x-coordinate of the second building
	// @param int y2: y-coordinate of the second building
	// @effects gives the distance between the two points by the euclidean distance formula: sqrt((x1-x2)^2 + (y1-y2)^2)
	// @modifies nothing
	// @throws nothing
	// @returns the distance between the two points as a double
	private static double distance(int x1, int y1, int x2, int y2) {
		return Math.sqrt((x1 - x2)*(x1 - x2) + (y1-y2)*(y1-y2));
	}
	
	// @requires nothing
	// @param String filename: the string path that leads to the csv
	// @effects adds the data to the graph and the map
	// @modifies Graph<Integer, Double> g 
	// @throws nothing
	// @returns nothing
	private static void parseEdges(String filename) throws IOException {
	    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	        	// get the data from the file
	            int index = line.indexOf(",");
	            if (index == -1) 
	                throw new IOException("File " + filename + " not a CSV (ID,ID) file.)");
	            String sourceNode = line.substring(0, index);
	            String destNode = line.substring(index + 1);
	            
	            // parse the data to integer
	            int source;
	            int dest;
	            try {
	            	source = Integer.parseInt(sourceNode);
                    dest = Integer.parseInt(destNode);
                } catch (NumberFormatException e) {
                    throw new IOException("File " + filename + " has invalid ids");
                }
	            
	            // add to the graph data structure
	            Building b1 = map.get(source);
	            Building b2 = map.get(dest);
	            double weight = distance(b1.getX(), b1.getY(), b2.getX(), b2.getY());
	            g.addEdge(source, dest, weight);
	            g.addEdge(dest, source, weight);
	        }
	    }
	}
	
	// @requires nothing
	// @param nothing
	// @effects adds every building to the list of buildings and sorts them by name
	// @modifies ArrayList<Building> buildings
	// @throws nothing
	// @returns nothing
	private static void parseBuildings() {
		// add every building that is not an intersection to the list
		buildings = new ArrayList<>();
 		for (Map.Entry<Integer, Building> building : map.entrySet()) {
 			String buildingName = building.getValue().getName();
 			if (!buildingName.equals(""))
 				buildings.add(building.getValue());
 		}
 		
 		// sort the buildings by name
 		Collections.sort(buildings, new Comparator<Building>() {
 			public int compare(Building b1, Building b2) {
 				return b1.getName().compareTo(b2.getName());
 			}
 		});
	}
	
	// @requires nothing
	// @param Building b1: first building (building traveling from)
	// @param Building b2: second building (building traveling to)
	// @effects adds every building to the list of buildings and sorts them by name
	// @modifies nothing
	// @throws nothing
	// @returns Stirng: of the direction traveling
	public static String direction(Building b1, Building b2) {
		int x1 = b1.getX();
		int x2 = b2.getX();
		int y1 = b1.getY();
		int y2 = b2.getY();
		double x_change = x2-x1;
		double y_change = y2-y1;
		double angle = Math.atan2(y_change, x_change) + Math.PI * 2;
		angle %= 2 * Math.PI;
		if (angle >= Math.PI * 11.0 / 8.0 && angle < Math.PI * 13.0 / 8.0)
			return "N";
		else if (angle >= Math.PI * 9.0 / 8.0 && angle < Math.PI * 11.0 / 8.0)
			return "NW";
		else if (angle >= Math.PI * 7.0 / 8.0 && angle < Math.PI * 9.0 / 8.0)
			return "W";
		else if (angle >= Math.PI * 5.0 / 8.0 && angle < Math.PI * 7.0 / 8.0)
			return "SW";
		else if (angle >= Math.PI * 3.0 / 8.0 && angle < Math.PI * 5.0 / 8.0)
			return "S";
		else if (angle >= Math.PI * 1.0 / 8.0 && angle < Math.PI * 3.0 / 8.0)
			return "SE";
		else if (angle >= Math.PI * 13.0 / 8.0 && angle < Math.PI * 15.0 / 8.0)
			return "NE";
		else // if (angle >= Math.PI * 15.0 / 8.0 || angle < Math.PI * 1.0 / 8.0)
			return "E";
	}

	// @requires nothing
	// @param nothing
	// @effects parses all the data with helper functions
	// @modifies nothing (but the functions called modifies ArrayList<Building> buildings, HashMap<Integer, Building> map, Graph<Integer, Double> g
	// @throws nothing
	// @returns nothing
	public static void parseData() throws IOException {
		parseNodes("data/RPI_map_data_Nodes.csv");
		parseEdges("data/RPI_map_data_Edges.csv");
		parseBuildings();
	}
	
}
