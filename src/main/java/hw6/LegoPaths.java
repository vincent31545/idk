package hw6;

//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import hw4.Graph;

public class LegoPaths {
	private Graph<String, Double> graph;
	
	// @requires nothing
	// @param String value of the file path
	// @effects Edits the graph
	// @modifies graph
	// @throws IOException when the file path does not exist
	// @returns nothing
	private void addData(String filename) {
		Map<String, Set<String>> legoMap = new HashMap<String, Set<String>>();
		Set<String> legos = new HashSet<String>();
		
		//get the right inputs
		try {
			ProfessorParser.readData(filename, legoMap, legos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//add all the professor nodes to the graph
		Iterator<String> legoIterator1;
		Iterator<String> legoIterator2;
		String part1;
		String part2;
		legoIterator1 = legos.iterator();
		while(legoIterator1.hasNext()) {
			part1 = legoIterator1.next();
			graph.addNode(part1);
		}
		
		//add the edges to the graph
		String lego;
		double weight = 0.0;
		//loops through all the courses
		for (Map.Entry<String, Set<String>> entry : legoMap.entrySet()) {
			lego = entry.getKey();
			
			//double loop of adding all the professors edges to each other
			Set<String> legos1 = entry.getValue();
			Set<String> legos2 = entry.getValue();
			legoIterator1 = legos1.iterator();
			while(legoIterator1.hasNext()) {
        	   part1 = legoIterator1.next();
			   legoIterator2 = legos2.iterator();
			   while(legoIterator2.hasNext()) {
				   part2 = legoIterator2.next();
				   if (graph.edgeInGraph(part1, part2)) {
					   weight = graph.getEdgeWeight(part1, part2).get(0);
					   graph.removeEdge(part1, part2);
//					   weight = 1/ (1/weight + 1);
					   graph.addEdge(part1, part2, weight + 1);
				   } else {
					   graph.addEdge(part1, part2, 1.0);
				   }
			   }
			}
		}
	}
	
	// @requires nothing
	// @param String value of the file path
	// @effects Edits the graph
	// @modifies graph
	// @throws IOException when the file path does not exist
	// @returns nothing
	public void createNewGraph(String filename) {
		graph = new Graph<String, Double>();
		addData(filename);
	}
	
	private double calculateCost(ArrayList<String> path) {
		double cost = 0.0;
		for (int i = 1; i < path.size(); i++)
			cost += 1.0/graph.getEdgeWeight(path.get(i-1), path.get(i)).get(0);
		return cost;
	}
	
	// @requires nothing
	// @param String value of the starting node (professor) and the ending node (professor)
	// @effects nothing
	// @modifies graph
	// @throws IOException when the file path does not exist
	// @returns Gives the shortest path of professors in the form of a string with the connecting course
	// @returns Unknown professor if name is not in graph
	// @returns An empty path if the start node is the destination node
	public String findPath(String startNode, String destNode) {
		if (graph.nodeInGraph(startNode) == false && graph.nodeInGraph(destNode) == false && !startNode.equals(destNode))
			return "unknown part " + startNode + "\nunknown part " + destNode + "\n";
		if (graph.nodeInGraph(startNode) == false)
			return "unknown part " + startNode + "\n";
		if (graph.nodeInGraph(destNode) == false)
			return "unknown part " + destNode + "\n";
		if (startNode.equals(destNode))
			return "path from " + startNode + " to " + destNode + ":\ntotal cost: 0.000\n";

	    //ans in the form of a string
	    String ans = "path from " + startNode + " to " + destNode + ":\n";
	    double cost = 0.0d;
	    double weight = 0.0d;

	    PriorityQueue<ArrayList<String>> active = new PriorityQueue<>(Comparator.comparingDouble(path -> calculateCost(path)));
		Set<String> finished = new HashSet<>();
		ArrayList<String> ansPath = new ArrayList<>();
		ansPath.add(startNode); 
		
		boolean found = false;
		active.add(ansPath);
		while (!active.isEmpty()) {
			ansPath = active.poll();
		    String minDest = ansPath.get(ansPath.size() - 1);

		    if (minDest.equals(destNode)) {
		    	found = true;
		        break;
		    }

		    if (finished.contains(minDest))
		        continue;

		    for (String child : graph.getAdjacencyList(minDest)) {
		        if (!finished.contains(child)) {
		            ArrayList<String> newPath = new ArrayList<>(ansPath);
		            newPath.add(child);
		            active.add(newPath);
		        }
		    }
		    
            finished.add(minDest);
		}
		
	    if (found != true)
	      	return ans + "no path found\n";
	    for (int i = 0; i < ansPath.size()- 1; i++) {
	      	String lego1 = ansPath.get(i);
	      	String lego2 = ansPath.get(i+1);
	      	if (lego1.equals(lego2))
	      		weight = 0.0d;
	      	else
	      		weight = 1.0/graph.getEdgeWeight(lego1, lego2).get(0);
	      	cost += weight;
	      	ans = ans + lego1 + " to " + lego2 + String.format(" with weight %.3f\n", weight);
	    }
	    ans = ans + String.format("total cost: %.3f\n", cost);
	    return ans;
	}
	
	public static void main(String[] arg) throws IOException {
		String file = arg[0];
		LegoPaths lp = new LegoPaths();
		System.out.println("HOI");
		lp.createNewGraph(file);
		System.out.println("DONE");
//		System.out.println(lp.findPath("31367 Green Duplo Egg Base", "98138pr0080 Pearl Gold Tile Round 1 x 1 with Blue, Yellow and Black Minecraft Print"));
//		System.out.println(lp.findPath("880006 Black Stopwatch", "3007d White Brick 2 x 8 without Bottom Tubes, 1 End Slot"));
//		System.out.println(lp.findPath("35480 Green Plate Special 1 x 2 Rounded with 2 Open Studs", "27ac01 Light Yellow Window 1 x 2 x 1 (old type) with Extended Lip and Solid Studs, with Fixed Glass"));
//		System.out.println(lp.findPath("76371pr0201 White Duplo Brick 1 x 2 x 2 with Bottom Tube, Target and Water Splash Print", "75266 White Duplo Car Body, Camper / Caravan Roof"));
//		System.out.println(lp.findPath("3035 Dark Gray Plate 4 x 8 to 3035 Dark Gray Plate 4 x 8", "3035 Dark Gray Plate 4 x 8 to 3035 Dark Gray Plate 4 x 8"));
//		System.out.println(lp.findPath("2412a White Tile Special 1 x 2 Grille with Bottom Groove", "2412a White Tile Special 1 x 2 Grille with Bottom Groove"));
		System.out.println(lp.findPath("Adam", "Bob"));
		System.out.println(lp.findPath("Adam", "Carl"));
		System.out.println(lp.findPath("Adam", "Darrin"));
		System.out.println(lp.findPath("Adam", "Eric"));
		System.out.println(lp.findPath("Darrin", "Eric"));
		System.out.println(lp.findPath("Bob", "Darrin"));
		System.out.println(lp.findPath("Darrin", "Bob"));
	}
}