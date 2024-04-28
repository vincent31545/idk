package hw7;

import java.util.ArrayList;
import java.util.Scanner;

public class CampusPathsUI {
	// @requires that both idInput1 and idInput2 are valid buildings
	// @param nothing
	// @effects returns the directions of the path to travel to get there
	// @modifies nothing
	// @throws nothing
	// @returns String: direction to walk from one location to another and the correct cardinal direction
	private static void printPath(String idInput1, String idInput2) {
		ArrayList<Building> path = CampusPathsModel.dijkstras(idInput1, idInput2);
			
		if (path.size() == 0)
			System.out.println("There is no path from " + CampusPathsModel.getBuildingName(idInput1) + " to " + CampusPathsModel.getBuildingName(idInput2) + ".");
		else {
			// convert the path to a string
			String ans = "Path from " + CampusPathsModel.getBuildingName(idInput1) + " to " + CampusPathsModel.getBuildingName(idInput2) + ":\n";
			for (int i = 1; i < path.size(); i++) {
				Building b1 = path.get(i-1);
				Building b2 = path.get(i);
	        	String direction = CampusPathsModel.direction(b1, b2);
	        	//handles walking to an intersection
	        	if (b2.getName().equals(""))
	    			ans = ans + "\tWalk " + direction + " to (Intersection " + b2.getId() + ")\n";
	        	else
	        		ans = ans + "\tWalk " + direction + " to (" + b2.getName() + ")\n";
	        }
			ans = ans + "Total distance: " + String.format("%.3f", CampusPathsModel.getTotalCost()) + " pixel units.";
			System.out.println(ans);
		}
	}
	
	// @requires mptjomg
	// @param nothing
	// @effects takes in user input and displays their requested info
	// @modifies nothing
	// @throws nothing
	// @returns nothing
	public static void acceptInput() {
		Scanner input = new Scanner(System.in);
		while (input.hasNextLine()) {
			String userInput = input.nextLine();
			if (userInput.equals("b")) {
	 			for (Building b : CampusPathsModel.getBuildings())
	 				System.out.println(b.getName() + "," + b.getId());
	 		} else if (userInput.equals("r")) {
	 			// gets the input of the buildings
	 			System.out.print("First building id/name, followed by Enter: ");
	 			String idInput1 = input.nextLine();
	 			System.out.print("Second building id/name, followed by Enter: ");
	 			String idInput2 = input.nextLine();
	 			
	 			if (CampusPathsModel.hasBuilding(idInput1) == false && CampusPathsModel.hasBuilding(idInput2) == false)
	 				System.out.println("Unknown building: [" + idInput1 + "]\nUnknown building: [" + idInput2 + "]");
	 			else if (CampusPathsModel.hasBuilding(idInput1) == false)
	 				System.out.println("Unknown building: [" + idInput1 + "]");
	 			else if (CampusPathsModel.hasBuilding(idInput2) == false)
	 				System.out.println("Unknown building: [" + idInput2 + "]");
	 			else
	 				printPath(idInput1, idInput2);
	 			
	 		} else if (userInput.equals("q")) {
	 			input.close();
	 			return;
	 		} else if (userInput.equals("m")) {
	 			System.out.println("b lists all buildings\n"
	 					 		 + "r prints directions for the shortest route between any two buildings\n"
	 					 		 + "q quits the program\n"
	 					 		 + "m prints a menu of all commands");
	 		} else {
	 			System.out.println("Unknown option");
	 		}
		}
		input.close();
	}
}
