package hw6;
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Test;
//
//
//
//public final class LegoPathsTest {
//	private void eqPath(String rep, String attempt) {
//		assertEquals(rep, attempt);
//	}
//	
//	/////////////////
//	// createNewGraph
//	/////////////////
//	@Test
//	public void testCreateNewGraph() {
//		LegoPaths legoPaths = new LegoPaths();
//		legoPaths.createNewGraph("data/courses.csv");
//		legoPaths.createNewGraph("data/disconnectedCourses.csv");
//		legoPaths.createNewGraph("data/empty.csv");
//		legoPaths.createNewGraph("data/one.csv");
//		legoPaths.createNewGraph("data/oneMultiCourse.csv");
//		legoPaths.createNewGraph("data/singleConnection.csv");
//		legoPaths.createNewGraph("data/simpleConnections.csv");
//	}
//	
//	///////////
//	// findPath
//	///////////
//	@Test
//	public void testFindPath() {
//		LegoPaths legoPaths = new LegoPaths();
//		legoPaths.createNewGraph("data/courses.csv");
////		eqPath("path from Mohammed J. Zaki to Wilfredo Colon:\n" +
////			   "Mohammed J. Zaki to David Eric Goldschmidt with weight 1.000\n" +
////			   "David Eric Goldschmidt to Michael Joseph Conroy with weight 1.000\n" +
////			   "Michael Joseph Conroy to Alan R Cutler with weight 1.000\n" +
////			   "Alan R Cutler to Wilfredo Colon with weight 1.000\ntotal cost: 6.000\n", 
////			   legoPaths.findPath("Mohammed J. Zaki", "Wilfredo Colon"));
//		eqPath("path from David Eric Goldschmidt to Hugh Johnson:\n" +
//			   "no path found\n", 
//			   legoPaths.findPath("David Eric Goldschmidt", "Hugh Johnson"));
//		eqPath("unknown part Donald Knuth\n", 
//				legoPaths.findPath("Donald Knuth", "Malik Magdon-Ismail"));
//		eqPath("unknown part Donald Knuth\n" + 
//			   "unknown part Brian Kernighan\n", 
//			   legoPaths.findPath("Donald Knuth","Brian Kernighan"));
//		eqPath("path from Barbara Cutler to Barbara Cutler:\ntotal cost: 0.000\n", 
//				legoPaths.findPath("Barbara Cutler", "Barbara Cutler"));
//		eqPath("unknown part Donald Knuth\n", 
//				legoPaths.findPath("Donald Knuth", "Donald Knuth"));
//		
//		legoPaths.createNewGraph("data/simpleConnections.csv");
//		eqPath("path from Adam to Bob:\n"
//				+ "Adam to Bob with weight 0.500\n"
//				+ "total cost: 0.500\n",
//				legoPaths.findPath("Adam", "Bob"));
//		eqPath("path from Adam to Bob:\n"
//		        + "Adam to Bob with weight 0.500\n"
//		        + "total cost: 0.500\n",
//		        legoPaths.findPath("Adam", "Bob"));
//		eqPath("path from Adam to Carl:\n"
//		        + "Adam to Carl with weight 1.000\n"
//		        + "total cost: 1.000\n",
//		        legoPaths.findPath("Adam", "Carl"));
//		eqPath("path from Adam to Darrin:\n"
//		        + "Adam to Darrin with weight 1.000\n"
//		        + "total cost: 1.000\n",
//		        legoPaths.findPath("Adam", "Darrin"));
//		eqPath("path from Adam to Eric:\n"
//		        + "Adam to Darrin with weight 1.000\n"
//		        + "Darrin to Eric with weight 1.000\n"
//		        + "total cost: 2.000\n",
//		        legoPaths.findPath("Adam", "Eric"));
//		eqPath("path from Darrin to Eric:\n"
//		        + "Darrin to Eric with weight 1.000\n"
//		        + "total cost: 1.000\n",
//		        legoPaths.findPath("Darrin", "Eric"));
//		eqPath("path from Bob to Darrin:\n"
//		        + "Bob to Adam with weight 0.500\n"
//		        + "Adam to Darrin with weight 1.000\n"
//		        + "total cost: 1.500\n",
//		        legoPaths.findPath("Bob", "Darrin"));
//		eqPath("path from Darrin to Bob:\n"
//		        + "Darrin to Adam with weight 1.000\n"
//		        + "Adam to Bob with weight 0.500\n"
//		        + "total cost: 1.500\n",
//		        legoPaths.findPath("Darrin", "Bob"));
//	}
//	
//	@Test
//	public void testFindPathNotConnected() {
//		LegoPaths legoPaths = new LegoPaths();
//		legoPaths.createNewGraph("data/disconnectedCourses.csv");
//		eqPath("path from Adam to Adam:\ntotal cost: 0.000\n", legoPaths.findPath("Adam", "Adam"));
//		eqPath("path from Adam to Bob:\nno path found\n", legoPaths.findPath("Adam", "Bob"));
//		eqPath("path from Adam to Carl:\nno path found\n", legoPaths.findPath("Adam", "Carl"));
//		eqPath("path from Adam to Darrin:\nno path found\n", legoPaths.findPath("Adam", "Darrin"));
//		eqPath("path from Adam to Eric:\nno path found\n", legoPaths.findPath("Adam", "Eric"));
//		
//		eqPath("path from Bob to Bob:\ntotal cost: 0.000\n", legoPaths.findPath("Bob", "Bob"));
//		eqPath("path from Bob to Adam:\nno path found\n", legoPaths.findPath("Bob", "Adam"));
//		eqPath("path from Bob to Carl:\nno path found\n", legoPaths.findPath("Bob", "Carl"));
//		eqPath("path from Bob to Darrin:\nno path found\n", legoPaths.findPath("Bob", "Darrin"));
//		eqPath("path from Bob to Eric:\nno path found\n", legoPaths.findPath("Bob", "Eric"));
//		
//		eqPath("path from Carl to Carl:\ntotal cost: 0.000\n", legoPaths.findPath("Carl", "Carl"));
//		eqPath("path from Carl to Bob:\nno path found\n", legoPaths.findPath("Carl", "Bob"));
//		eqPath("path from Carl to Adam:\nno path found\n", legoPaths.findPath("Carl", "Adam"));
//		eqPath("path from Carl to Darrin:\nno path found\n", legoPaths.findPath("Carl", "Darrin"));
//		eqPath("path from Carl to Eric:\nno path found\n", legoPaths.findPath("Carl", "Eric"));
//		
//		eqPath("path from Darrin to Darrin:\ntotal cost: 0.000\n", legoPaths.findPath("Darrin", "Darrin"));
//		eqPath("path from Darrin to Bob:\nno path found\n", legoPaths.findPath("Darrin", "Bob"));
//		eqPath("path from Darrin to Carl:\nno path found\n", legoPaths.findPath("Darrin", "Carl"));
//		eqPath("path from Darrin to Adam:\nno path found\n", legoPaths.findPath("Darrin", "Adam"));
//		eqPath("path from Darrin to Eric:\nno path found\n", legoPaths.findPath("Darrin", "Eric"));
//		
//		eqPath("path from Eric to Eric:\ntotal cost: 0.000\n", legoPaths.findPath("Eric", "Eric"));
//		eqPath("path from Eric to Bob:\nno path found\n", legoPaths.findPath("Eric", "Bob"));
//		eqPath("path from Eric to Carl:\nno path found\n", legoPaths.findPath("Eric", "Carl"));
//		eqPath("path from Eric to Darrin:\nno path found\n", legoPaths.findPath("Eric", "Darrin"));
//		eqPath("path from Eric to Adam:\nno path found\n", legoPaths.findPath("Eric", "Adam"));
//	}
//	
//	@Test
//	public void testFindPathEmpty() {
//		LegoPaths legoPaths = new LegoPaths();
//		legoPaths.createNewGraph("data/empty.csv");
//		
//		eqPath("unknown part Adam\n", legoPaths.findPath("Adam", "Adam"));
//		eqPath("unknown part Carl\nunknown part Bob\n", legoPaths.findPath("Carl", "Bob"));
//	}
//	
//	@Test
//	public void testFindPathOnePart() {
//		LegoPaths legoPaths = new LegoPaths();
//		legoPaths.createNewGraph("data/one.csv");
//		eqPath("path from Dr. Callahan to Dr. Callahan:\ntotal cost: 0.000\n", legoPaths.findPath("Dr. Callahan", "Dr. Callahan"));
//		eqPath("unknown part Adam\n", legoPaths.findPath("Adam", "Dr. Callahan"));
//		eqPath("unknown part Bob\n", legoPaths.findPath("Dr. Callahan", "Bob"));
//	}
//	
//	@Test
//	public void testFindPathOneMultiSetPart() {
//		LegoPaths legoPaths = new LegoPaths();
//		legoPaths.createNewGraph("data/oneMultiCourse.csv");
//		eqPath("path from Adam to Adam:\ntotal cost: 0.000\n", legoPaths.findPath("Adam", "Adam"));
//		eqPath("unknown part Bob\n", legoPaths.findPath("Adam", "Bob"));
//		eqPath("unknown part Carl\nunknown part Bob\n", legoPaths.findPath("Carl", "Bob"));
//	}
//	
//	@Test
//	public void testFindPathOneSetConnection() {
//		LegoPaths legoPaths = new LegoPaths();
//		legoPaths.createNewGraph("data/singleConnection.csv");
//		eqPath("path from Adam to Bob:\nAdam to Bob with weight 1.000\ntotal cost: 1.000\n", legoPaths.findPath("Adam", "Bob"));
//		eqPath("path from Adam to Carl:\nAdam to Carl with weight 1.000\ntotal cost: 1.000\n", legoPaths.findPath("Adam", "Carl"));
//		
//		eqPath("path from Bob to Adam:\nBob to Adam with weight 1.000\ntotal cost: 1.000\n", legoPaths.findPath("Bob", "Adam"));
//		eqPath("path from Bob to Carl:\nBob to Carl with weight 1.000\ntotal cost: 1.000\n", legoPaths.findPath("Bob", "Carl"));
//		
//		eqPath("path from Carl to Adam:\nCarl to Adam with weight 1.000\ntotal cost: 1.000\n", legoPaths.findPath("Carl", "Adam"));
//		eqPath("path from Carl to Bob:\nCarl to Bob with weight 1.000\ntotal cost: 1.000\n", legoPaths.findPath("Carl", "Bob"));
//	}
//}