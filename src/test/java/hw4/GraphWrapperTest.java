package hw4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

public class GraphWrapperTest {

	private GraphWrapper graph1 = new GraphWrapper();
	private GraphWrapper graph2 = new GraphWrapper();
	
	private void eqNodes(GraphWrapper g, String rep) {
		Iterator<String> temp = g.listNodes();
		String ans = "";
		while (temp.hasNext()) {
			ans = ans + temp.next() + ", ";
		}
		if (ans.length() == 0)
			return;
		ans = ans.substring(0, ans.length() -2);
		assertEquals(ans, rep);
	}
	
	private void eqChildren(GraphWrapper g, String node, String rep) {
		Iterator<String> temp = g.listChildren(node);
		String ans = "";
		while (temp.hasNext()) {
			ans = ans + temp.next() + ", ";
		}
		if (ans.length() == 0)
			return;
		ans = ans.substring(0, ans.length() -2);
		assertEquals(ans, rep);
	}
	
	//////////////
	// Constructor
	//////////////
	@Test
	public void testConstructor() {
		new GraphWrapper();
	}
	
	//////////
	// addNode
	//////////
	@Test
	public void testAddNode() {
		graph1.addNode("a");
		graph2.addNode("A");
		eqNodes(graph1, "a");
		eqChildren(graph1, "", "");
		eqNodes(graph2, "A");
		eqChildren(graph2, "", "");
	}
	@Test
	public void testAddMultipleNodes() {
		graph1.addNode("a");
		graph1.addNode("A");
		graph1.addNode("1203758");
		graph2.addNode("!!(@&^*");
		graph2.addNode("aAlakjshdf");
		graph2.addNode("78148763");
		graph2.addNode("Hello World");
		eqNodes(graph1, "1203758, A, a");
		eqChildren(graph1, "", "");
		eqNodes(graph2, "!!(@&^*, 78148763, Hello World, aAlakjshdf");
		eqChildren(graph2, "", "");
	}
	@Test
	public void testAddDuplicateNodes() {
		graph1.addNode("a");
		graph1.addNode("a");
		graph1.addNode("a");
		graph1.addNode("1");
		graph1.addNode("2");
		graph1.addNode("3");
		graph1.addNode("1");
		graph1.addNode("2");
		graph1.addNode("3");
		graph2.addNode("!!(@&^*");
		graph2.addNode("aAlakjshdf");
		graph2.addNode("78148763");
		graph2.addNode("Hello World");
		graph2.addNode("!!(@&^*");
		graph2.addNode("Hello World");
		graph2.addNode("Hello World");
		eqNodes(graph1, "1, 2, 3, a");
		eqChildren(graph1, "", "");
		eqNodes(graph2, "!!(@&^*, 78148763, Hello World, aAlakjshdf");
		eqChildren(graph2, "", "");
	}
	
	//////////
	// addEdge
	//////////
	@Test 
	public void testAddEdge() {
		graph1.addNode("a");
		graph1.addNode("b");
		graph1.addNode("c");
		graph1.addEdge("a", "b", "1");
		graph1.addEdge("a", "c", "2");
		graph1.addEdge("b", "a", "5");
		graph1.addEdge("b", "c", "6");
		graph1.addEdge("c", "a", "3");
		graph1.addEdge("c", "b", "4");
		eqNodes(graph1, "a, b, c");
		eqChildren(graph1, "a", "b(1), c(2)");
		eqChildren(graph1, "b", "a(5), c(6)");
		eqChildren(graph1, "c", "a(3), b(4)");
	}
	
	@Test 
	public void testAddSameEdge() {
		graph1.addNode("a");
		graph1.addNode("b");
		graph1.addNode("c");
		graph1.addEdge("a", "b", "1");
		graph1.addEdge("a", "b", "2");
		graph1.addEdge("a", "b", "3");
		graph1.addEdge("a", "b", "1");
		graph1.addEdge("a", "b", "2");
		graph1.addEdge("a", "b", "3");
		eqNodes(graph1, "a, b, c");
		eqNodes(graph1, "a, b, c");
		eqChildren(graph1, "a", "b(1), b(2), b(3)");
		eqChildren(graph1, "b", "");
		eqChildren(graph1, "c", "");
	}
	
	@Test 
	public void testAddEdgeWithNonexistingNode() {
		graph1.addNode("a");
		graph1.addNode("b");
		graph1.addNode("c");
		graph1.addEdge("a", "e", "1");
		graph1.addEdge("a", "f", "2");
		graph1.addEdge("a", "b", "3");
		graph1.addEdge("a", "g", "1");
		graph1.addEdge("a", "h", "2");
		graph1.addEdge("a", "i", "3");
		graph2.addNode("a");
		graph2.addEdge("a", "f", "2");
		eqNodes(graph1, "a, b, c");
		eqChildren(graph1, "a", "b(3)");
		eqChildren(graph1, "b", "");
		eqChildren(graph1, "c", "");
	}
	
}