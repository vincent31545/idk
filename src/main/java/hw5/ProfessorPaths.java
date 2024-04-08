package hw5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import hw4.Graph;

public class ProfessorPaths {
	private Graph graph;
	
	// @requires nothing
	// @param String value of the file path
	// @effects Edits the graph
	// @modifies graph
	// @throws IOException when the file path does not exist
	// @returns nothing
	private void addData(String filename) {
		Map<String, Set<String>> profsTeaching = new HashMap<String, Set<String>>();
		Set<String> profs = new HashSet<String>();
		
		//get the right inputs
		try {
			ProfessorParser.readData(filename, profsTeaching, profs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//add all the professor nodes to the graph
		Iterator<String> profsIterator1;
		Iterator<String> profsIterator2;
		String professor1;
		String professor2;
		profsIterator1 = profs.iterator();
		while(profsIterator1.hasNext()) {
			   professor1 = profsIterator1.next();
			   graph.addNode(professor1);
		}
		
		//add the edges to the graph
		String course;
		//loops through all the courses
		for (Map.Entry<String, Set<String>> entry : profsTeaching.entrySet()) {
            course = entry.getKey();
            
            //double loop of adding all the professors edges to each other
            Set<String> professors1 = entry.getValue();
            Set<String> professors2 = entry.getValue();
            profsIterator1 = professors1.iterator();
            while(profsIterator1.hasNext()) {
			   professor1 = profsIterator1.next();
			   profsIterator2 = professors2.iterator();
			   while(profsIterator2.hasNext()) {
				  professor2 = profsIterator2.next();
				  graph.addEdge(professor1, professor2, course);
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
		graph = new Graph();
		addData(filename);
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
			return "unknown professor " + startNode + "\nunknown professor " + destNode + "\n";
		if (graph.nodeInGraph(startNode) == false)
			return "unknown professor " + startNode + "\n";
		if (graph.nodeInGraph(destNode) == false)
			return "unknown professor " + destNode + "\n";
		if (startNode.equals(destNode))
			return "path from " + startNode + " to " + destNode + ":\n";
		//queue of the current nodes
		Queue<String> queue = new LinkedList<>();
		//map of all visited nodes
        Map<String, ArrayList<String>> visitedNodes = new HashMap<>();
        //ans in the form of a string
        String ans = "path from " + startNode + " to " + destNode + ":\n";
        ArrayList<String> ansPath = new ArrayList<String>();
        
        //add starting node and give it an empty list in the map
        queue.add(startNode);
        visitedNodes.put(startNode, new ArrayList<>());
        visitedNodes.get(startNode).add(startNode);
        while (!queue.isEmpty()) {
            String node = queue.poll();
            if (node.equals(destNode))
                ansPath = new ArrayList<String>(visitedNodes.get(node));
            
            ArrayList<String> adjNodes = graph.getAdjacencyList(node);
            Collections.sort(adjNodes);
            for (String nextNode : adjNodes) {
                if (!visitedNodes.containsKey(nextNode)) {
                    ArrayList<String> path = visitedNodes.get(node);
                    ArrayList<String> newPath = new ArrayList<>(path);
                    newPath.add(nextNode);
                    visitedNodes.put(nextNode, newPath);
                    queue.add(nextNode);
                }
            }
        }
        if (ansPath.size() == 0)
        	return ans + "no path found\n";
        for (int i = 0; i < ansPath.size()- 1; i++) {
        	String prof1 = ansPath.get(i);
        	String prof2 = ansPath.get(i+1);
        	ArrayList<String> temp = graph.getEdgeWeight(prof1, prof2);
        	Collections.sort(temp);
        	ans = ans + prof1 + " to " + prof2 + " via " + temp.get(0) + "\n";
        }
        return ans;
	}
	
//	private String printProfessors() {
//		ArrayList<String> profs = graph.getNodes();
//		String ans = "";
//		int counter = 0;
//		for (String professor : profs) {
//			ans = ans + professor + " ";
//			counter++;
//			if (counter != 0 && counter %100 == 0)
//				ans = ans + "\n";
//		}
//		return ans;
//	}
//	private String printCourses() {
//		ArrayList<String[]> courses = graph.getEdges();
//		Set<String[]> coursesSet = new HashSet<>(courses);
//		Iterator<String[]> setItr = coursesSet.iterator();
//		String ans = "";
//		int counter = 0;
//		while (setItr.hasNext()) {
//			String[] temp = setItr.next();
//			ans = ans + graph.getEdgeWeight(temp[0], temp[1]) + " ";
//			counter++;
//			if (counter != 0 && counter %100 == 0)
//				ans = ans + "\n";
//		}
//		return ans;
//	}
	
//	public static void main(String[] arg) throws IOException {
//		String file = arg[0];
//		ProfessorPaths gprofs = new ProfessorPaths();
//		gprofs.createNewGraph(file);
//		
//		System.out.println("HOI");
//		System.out.println(gprofs.findPath("David R Musser","John Sturman"));
//	}
}