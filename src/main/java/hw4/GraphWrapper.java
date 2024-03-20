package hw4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class GraphWrapper {
	private Graph graph;
	
	public GraphWrapper() {
		graph = new Graph();
	}
	
	public void addNode(String nodeData) {
		graph.addNode(nodeData);
	}

	public void addEdge(String parentNode, String childNode, String edgeLabel) {
		graph.addEdge(parentNode, childNode, edgeLabel);
	}

	public Iterator<String> listNodes(){
		ArrayList<String> temp = graph.getNodes();
		Collections.sort(temp);
		Iterator<String> itr = temp.iterator();
		return itr;
	}

	public Iterator<String> listChildren(String parentNode){
		ArrayList<String> nodes = graph.getAdjacencyList(parentNode);
		ArrayList<String> temp;
		ArrayList<String> result = new ArrayList<String>();
		Collections.sort(nodes);
		for (int i = 0; i < nodes.size(); i++) {
			temp = graph.getEdgeWeight(parentNode, nodes.get(i));
			for (int j = 0; j < temp.size(); j++)
				result.add(nodes.get(i) + "(" + temp.get(j) + ")");
		}
		Iterator<String> itr = result.iterator();
		return itr;
	}

}