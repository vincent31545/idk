package hw5;

//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Test;
//
//
////import hw4.Graph;
//
//
//public final class ProfessorPathsTest {
//	private void eqPath(String rep, String attempt) {
//		assertEquals(rep, attempt);
//	}
//	
//	/////////////////
//	// createNewGraph
//	/////////////////
//	@Test
//	public void testCreateNewGraph() {
//		ProfessorPaths profPaths = new ProfessorPaths();
//		profPaths.createNewGraph("data/courses.csv");
//		profPaths.createNewGraph("data/disconnectedCourses.csv");
//		profPaths.createNewGraph("data/empty.csv");
//		profPaths.createNewGraph("data/one.csv");
//		profPaths.createNewGraph("data/oneMultiCourse.csv");
//		profPaths.createNewGraph("data/singleConnection.csv");
//	}
//	
//	///////////
//	// findPath
//	///////////
//	@Test
//	public void testFindPath() {
//		ProfessorPaths profPaths = new ProfessorPaths();
//		profPaths.createNewGraph("data/courses.csv");
//		eqPath("path from Mohammed J. Zaki to Wilfredo Colon:\n" +
//			   "Mohammed J. Zaki to David Eric Goldschmidt via CSCI-2300\n" +
//			   "David Eric Goldschmidt to Michael Joseph Conroy via CSCI-4430\n" +
//			   "Michael Joseph Conroy to Alan R Cutler via CHEM-1200\n" +
//			   "Alan R Cutler to Wilfredo Colon via CHEM-1100\n", 
//			   profPaths.findPath("Mohammed J. Zaki", "Wilfredo Colon"));
//		eqPath("path from David Eric Goldschmidt to Hugh Johnson:\n" +
//			   "no path found\n", 
//			   profPaths.findPath("David Eric Goldschmidt", "Hugh Johnson"));
//		eqPath("unknown professor Donald Knuth\n", 
//				profPaths.findPath("Donald Knuth", "Malik Magdon-Ismail"));
//		eqPath("unknown professor Donald Knuth\n" + 
//			   "unknown professor Brian Kernighan\n", 
//			   profPaths.findPath("Donald Knuth","Brian Kernighan"));
//		eqPath("path from Barbara Cutler to Barbara Cutler:\n", 
//				profPaths.findPath("Barbara Cutler", "Barbara Cutler"));
//		eqPath("unknown professor Donald Knuth\n", 
//				profPaths.findPath("Donald Knuth", "Donald Knuth"));
//		
//		
//		profPaths.createNewGraph("data/singleConnection.csv");
//	}
//	
//	@Test
//	public void testFindPathNotConnected() {
//		ProfessorPaths profPaths = new ProfessorPaths();
//		profPaths.createNewGraph("data/disconnectedCourses.csv");
//		eqPath("path from Adam to Adam:\n", profPaths.findPath("Adam", "Adam"));
//		eqPath("path from Adam to Bob:\nno path found\n", profPaths.findPath("Adam", "Bob"));
//		eqPath("path from Adam to Carl:\nno path found\n", profPaths.findPath("Adam", "Carl"));
//		eqPath("path from Adam to Darrin:\nno path found\n", profPaths.findPath("Adam", "Darrin"));
//		eqPath("path from Adam to Eric:\nno path found\n", profPaths.findPath("Adam", "Eric"));
//		
//		eqPath("path from Bob to Bob:\n", profPaths.findPath("Bob", "Bob"));
//		eqPath("path from Bob to Adam:\nno path found\n", profPaths.findPath("Bob", "Adam"));
//		eqPath("path from Bob to Carl:\nno path found\n", profPaths.findPath("Bob", "Carl"));
//		eqPath("path from Bob to Darrin:\nno path found\n", profPaths.findPath("Bob", "Darrin"));
//		eqPath("path from Bob to Eric:\nno path found\n", profPaths.findPath("Bob", "Eric"));
//		
//		eqPath("path from Carl to Carl:\n", profPaths.findPath("Carl", "Carl"));
//		eqPath("path from Carl to Bob:\nno path found\n", profPaths.findPath("Carl", "Bob"));
//		eqPath("path from Carl to Adam:\nno path found\n", profPaths.findPath("Carl", "Adam"));
//		eqPath("path from Carl to Darrin:\nno path found\n", profPaths.findPath("Carl", "Darrin"));
//		eqPath("path from Carl to Eric:\nno path found\n", profPaths.findPath("Carl", "Eric"));
//		
//		eqPath("path from Darrin to Darrin:\n", profPaths.findPath("Darrin", "Darrin"));
//		eqPath("path from Darrin to Bob:\nno path found\n", profPaths.findPath("Darrin", "Bob"));
//		eqPath("path from Darrin to Carl:\nno path found\n", profPaths.findPath("Darrin", "Carl"));
//		eqPath("path from Darrin to Adam:\nno path found\n", profPaths.findPath("Darrin", "Adam"));
//		eqPath("path from Darrin to Eric:\nno path found\n", profPaths.findPath("Darrin", "Eric"));
//		
//		eqPath("path from Eric to Eric:\n", profPaths.findPath("Eric", "Eric"));
//		eqPath("path from Eric to Bob:\nno path found\n", profPaths.findPath("Eric", "Bob"));
//		eqPath("path from Eric to Carl:\nno path found\n", profPaths.findPath("Eric", "Carl"));
//		eqPath("path from Eric to Darrin:\nno path found\n", profPaths.findPath("Eric", "Darrin"));
//		eqPath("path from Eric to Adam:\nno path found\n", profPaths.findPath("Eric", "Adam"));
//	}
//	
//	@Test
//	public void testFindPathEmpty() {
//		ProfessorPaths profPaths = new ProfessorPaths();
//		profPaths.createNewGraph("data/empty.csv");
//		
//		eqPath("unknown professor Adam\n", profPaths.findPath("Adam", "Adam"));
//		eqPath("unknown professor Carl\nunknown professor Bob\n", profPaths.findPath("Carl", "Bob"));
//	}
//	
//	@Test
//	public void testFindPathOneProfessor() {
//		ProfessorPaths profPaths = new ProfessorPaths();
//		profPaths.createNewGraph("data/one.csv");
//		eqPath("path from Dr. Callahan to Dr. Callahan:\n", profPaths.findPath("Dr. Callahan", "Dr. Callahan"));
//		eqPath("unknown professor Adam\n", profPaths.findPath("Adam", "Dr. Callahan"));
//		eqPath("unknown professor Bob\n", profPaths.findPath("Dr. Callahan", "Bob"));
//	}
//	
//	@Test
//	public void testFindPathOneMultiCourseProfessor() {
//		ProfessorPaths profPaths = new ProfessorPaths();
//		profPaths.createNewGraph("data/oneMultiCourse.csv");
//		eqPath("path from Adam to Adam:\n", profPaths.findPath("Adam", "Adam"));
//		eqPath("unknown professor Bob\n", profPaths.findPath("Adam", "Bob"));
//		eqPath("unknown professor Carl\nunknown professor Bob\n", profPaths.findPath("Carl", "Bob"));
//	}
//	
//	@Test
//	public void testFindPathOneCourseConnection() {
//		ProfessorPaths profPaths = new ProfessorPaths();
//		profPaths.createNewGraph("data/singleConnection.csv");
//		eqPath("path from Adam to Bob:\nAdam to Bob via Computer Science 101\n", profPaths.findPath("Adam", "Bob"));
//		eqPath("path from Adam to Carl:\nAdam to Carl via Computer Science 101\n", profPaths.findPath("Adam", "Carl"));
//		
//		eqPath("path from Bob to Adam:\nBob to Adam via Computer Science 101\n", profPaths.findPath("Bob", "Adam"));
//		eqPath("path from Bob to Carl:\nBob to Carl via Computer Science 101\n", profPaths.findPath("Bob", "Carl"));
//		
//		eqPath("path from Carl to Adam:\nCarl to Adam via Computer Science 101\n", profPaths.findPath("Carl", "Adam"));
//		eqPath("path from Carl to Bob:\nCarl to Bob via Computer Science 101\n", profPaths.findPath("Carl", "Bob"));
//	}
//}
//
