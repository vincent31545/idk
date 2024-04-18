package hw4;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;


//change to set for efficency
//change to public methods
//use given set methods

//A Graph is a directed multigraph. This means that there can be an edge going
// from node A to node B and an edge going from node B to node A. There
// also has the possibility to have multiple edges going from A to node B as 
// long as those nodes have different edge weights ("labels")

public class Graph<T, V> {
	// Abstraction Function:
    // A Graph is made up of nodes and edges. When two or more nodes are added
	// it is possible to connect them with an edge. All edges must have an edge weight
	// thus when an the edge weight is removed it should remove that particular edge 
	// although it is possible to have multiple edges of different edge weights connecting
	// those two nodes. The adjacencyList is the particular number of edges that a particular
	// node can explore to.

    // Representation invariant for every Graph g:
    //   (g.getNodes().size() >= 0) &&
	//   (g.getEdges().size() >= 0) &&
	//   (g.edgeInGraph(node1, node2) == true ==> g.get(node1).get(node2).size() > 0) &&
    //   for node in g.getNodes() ==> g.getEdges().size() == sum(g.getAdjacencyList(node).size)() &&
	//   g.edgeInGraph(node1, node2) == true ==> edgeWeight ==> Set)
	//
    //   In other words,
    //     * number of nodes in graph is non-negative
	//     * number of edges in graph is non-negative
	//     * number of edge weights is never zero
    //     * The sum of all the adjacency lists (including edgeweights) must equal the number of edges
	//     * Given an edge there are no duplicate edge weights
	
	
	//this variable holds the entire graph
	//the first key is all the nodes in the graph
	//the second key is the adjacencyList of that particular node
	//the third list holds all the edge weights that exist for that particular edge
	private HashMap<T, HashMap<T, Set<V>>> adjList;
	
	//constructor
	public Graph() {
		adjList = new HashMap<>();
	}
	
	// @requires nothing
	// @param T value of the node to add
	// @effects Adds the node to the Graph and does nothing if the node already exists
	// @modifies Graph
	// @throws nothing
	// @returns nothing
	public void addNode(T node) {
		if (nodeInGraph(node))
			return;
		adjList.put(node, new HashMap<>());
//		checkRep();
	}
	
	// @requires nothing
	// @param T value of the source node and the destination node and value of the edge
	// @effects Adds the edge to the Graph and does nothing if the nodes are not in the graph or is a repeat edge weight
	// @throws nothing
	// @returns nothing
	public void addEdge(T node1, T node2, V value){
		if (nodeInGraph(node1) == false || nodeInGraph(node2) == false)
			return;
		else if (edgeInGraph(node1, node2) == false)
			adjList.get(node1).put(node2, new HashSet<V>());
		adjList.get(node1).get(node2).add(value);
//		checkRep();
	}
	
	// @requires nothing
	// @param T value of the node to remove
	// @effects Removes the node from the Graph and does nothing if the node is the node is not in the graph
	// @effects It will also remove all edges going into that node
	// @modifies Graph
	// @throws nothing
	// @returns nothing
	public void removeNode(T node) {
		if (nodeInGraph(node))
			adjList.remove(node);
		else
			return;
		//removes the connection from other nodes to the node passed in
		Set<T> nodes = adjList.keySet();
		Iterator<T> nodesItr = nodes.iterator();
		T key;
		while(nodesItr.hasNext()) {
			key = nodesItr.next();
			if (edgeInGraph(key, node) == true) 
            	adjList.get(key).remove(node);
		}
//        checkRep();
	}
	
	// @requires nothing
	// @param T of the source node and the destination node
	// @effects Removes all edge weights and the connection between the source node to the destination node
	// @effects It does not effect the connection from the destination node to the source node
	// @effects It will do nothing if the edge does not exist
	// @modifies Graph
	// @throws nothing
	// @returns nothing
	public void removeEdge(T node1, T node2) {
		if (nodeInGraph(node1) == false)
			return;
		else if (nodeInGraph(node2) == false)
			return;
		else if (edgeInGraph(node1, node2) == false)
			return;
		else
			adjList.get(node1).remove(node2);
//		checkRep();
	}
	
	// @requires nothing
	// @param T of the source node and the destination node and the weight to remove
	// @effects Removes the edge weight for that edge from the Graph
	// @effects Removes the edge if there are no more edge weights for that edge
	// @effects Does nothing if the edge weight does not exist
	// @modifies Graph
	// @throws nothing
	// @returns nothing
	public void removeEdgeWeight(T node1, T node2, V value) {
		if (nodeInGraph(node1) == false)
			return;
		else if (nodeInGraph(node2) == false)
			return;
		else if (edgeInGraph(node1, node2) == false)
			return;
		else if (edgeWeightInGraph(node1, node2, value) == false)
			return;
		else
			adjList.get(node1).get(node2).remove(value);
		if (adjList.get(node1).get(node2).size() == 0)
			adjList.get(node1).remove(node2);
//		checkRep();
	}
	
	// @requires nothing
	// @param nothing
	// @effects nothing
	// @modifies nothing
	// @throws nothing
	// @returns ArrayList<T> of nodes
	public ArrayList<T> getNodes(){
		ArrayList<T> temp = new ArrayList<T>();
		Set<T> nodes = adjList.keySet();
		Iterator<T> nodesItr = nodes.iterator();
		T key;
        while (nodesItr.hasNext()) { 
        	key = nodesItr.next();
            temp.add(key);
        }
        return temp;
	}
	
	// @requires nothing
	// @param nothing
	// @effects nothing
	// @modifies nothing
	// @throws nothing
	// @returns ArrayList<T[]> (each edge will be an array of size 2 [source, destination]
	public ArrayList<ArrayList<T>> getEdges(){
		ArrayList<ArrayList<T>> temp = new ArrayList<>();
		Set<T> startNodes = adjList.keySet();
		Iterator<T> startNodesItr = startNodes.iterator();
		Set<T> endNodes;
		Iterator<T> endNodesItr;
		T node1;
		T node2;
        while (startNodesItr.hasNext()) { 
        	node1 = startNodesItr.next();
        	endNodes = adjList.get(node1).keySet();
        	endNodesItr = endNodes.iterator();
        	while (endNodesItr.hasNext()) {
        		node2 = endNodesItr.next();
        		ArrayList<T> edge = new ArrayList<T>();
                edge.add(node1);
                edge.add(node2);
                temp.add(edge);
        	}
        }
        return temp;
	}
	
	// @requires nothing
	// @param T value of the source node and the destination node
	// @effects nothing
	// @modifies nothing
	// @throws nothing
	// @returns ArrayList<T> of edge weights
	public ArrayList<V> getEdgeWeight(T node1, T node2) {
		ArrayList<V> temp = new ArrayList<V>(adjList.get(node1).get(node2));
		return temp;
	}
	
	// @requires nothing
	// @param T value of the node
	// @effects nothing
	// @modifies nothing
	// @throws nothing
	// @returns ArrayList<T> of adjacent nodes
	public ArrayList<T> getAdjacencyList(T node){
		ArrayList<T> temp = new ArrayList<T>();
		if (nodeInGraph(node) == false)
			return temp;
		Set<T> nodes = adjList.get(node).keySet();
		Iterator<T> nodesItr = nodes.iterator();
		T key;
        while (nodesItr.hasNext()) { 
        	key = nodesItr.next();
            temp.add(key);
        }
        return temp;
	}
	
	// @requires nothing
	// @param T value of a node
	// @effects nothing
	// @modifies nothing
	// @throws nothing
	// @returns true if the node is in the graph
	// @returns false if the node is not in the graph
	public boolean nodeInGraph(T node) {
		return adjList.containsKey(node);
	}
	
	// @requires nothing
	// @param T value of the source node and the destination node
	// @effects nothing
	// @modifies nothing
	// @throws nothing
	// @returns true if the edge is in the graph
	// @returns false if the edge is not in the graph
	public boolean edgeInGraph(T node1, T node2) {
		return adjList.get(node1).containsKey(node2);
	}
	
	// @requires nothing
	// @param T value of the source node and the destination node and the edge weight
	// @effects nothing
	// @modifies nothing
	// @throws nothing
	// @returns true if the edgeWeight is in the graph
	// @returns false if the edgeWeight is not in the graph
	public boolean edgeWeightInGraph(T node1, T node2, V value){
		if (edgeInGraph(node1, node2) == true && adjList.get(node1).get(node2).contains(value))
			return true;
		return false;
	}
	
	// Throws a RuntimeException if the rep invariant is violated.
    private void checkRep() throws RuntimeException {
        if (getNodes().size() < 0)
            throw new RuntimeException("Number of nodes can not be negative");
        if (getEdges().size() < 0)
        	throw new RuntimeException("Number of edges can not be negative");
        
        Set<T> nodes = adjList.keySet();
        Iterator<T> nodesItr = nodes.iterator();
        Set<T> temp;
        Iterator<T> tempItr;
        ArrayList<T> adjacentNodes;
		T node1;
		T node2;
		int counter = 0;
        while (nodesItr.hasNext()) {
        	adjacentNodes = new ArrayList<T>();
        	node1 = nodesItr.next();
        	temp = adjList.get(node1).keySet();
        	tempItr = temp.iterator();
        	while (tempItr.hasNext()) {
        		node2 = tempItr.next();
        		if (adjacentNodes.contains(node2) == true)
        			throw new RuntimeException("There can not be duplicate edge weights");
        		adjacentNodes.add(node2);
        		if(adjList.get(node1).get(node2).size() == 0)
        			throw new RuntimeException("Number of edge weights can not be 0");
        		counter++;
        	}
        }
        
        if (counter != getEdges().size()) 
        	throw new RuntimeException("Number of edges from adjacency list doesn't match number of edges");
    }
}
