package Test;

import static org.junit.jupiter.api.Assertions.*;
import main.*;
//import main.insert;
//import main.AVLPlayerNode;

import org.junit.jupiter.api.Test;


public class Tests {

	@Test
	void toStringTest() {
		Player a = new Player("a", 1, 1.0);
		Player b = new Player("b", 2, 2.0);
		Player c = new Player("c", 3, 3.0);
		Player d = new Player("d", 4, 4.0);
		Player e = new Player("e", 5, 5.0);
		
		AVLPlayerNode node = new AVLPlayerNode(b, 2.0);
		
		node.insert(a, 1.0);
		node.insert(c, 3.0);
		node.insert(d, 4.0);
		node.insert(e, 5.0);
		
		
		assertEquals(node.treeString(), "((a)b((c)d(e)))");
		//assertEquals(node.getPlayer(3.0), c);
	    
		//assertEquals(node.balanceF(node),0);
		//node.delete(1.0);
		//assertEquals(node.treeString(), "(b(c))");
		//assertEquals(avlPlayerNode.rightW(avlPlayerNode), 0);
		//assertEquals(insert(root), ;
		//AVLPlayerNode root = new AVLPlayerNode(c, 1.0);
		//AVLPlayerNode root = new AVLPlayerNode(c, 1.0);
		
	}
	
	//@Test
	void getPlayerTest() {
		Player a = new Player("a", 1, 1.0);
		Player b = new Player("b", 2, 2.0);
		Player c = new Player("c", 3, 3.0);
		
		AVLPlayerNode node = new AVLPlayerNode(b, 2.0);
		
		node.insert(a, 1.0);
		node.insert(c, 3.0);
		
		
		assertEquals(node.getPlayer(3.0), c);
	}
	
	@Test
	void balanceFactorTest() {
		Player a = new Player("a", 1, 1.0);
		Player b = new Player("b", 2, 2.0);
		Player c = new Player("c", 3, 3.0);
		Player d = new Player("d", 4, 4.0);
		Player e = new Player("e", 5, 5.0);
		
		AVLPlayerNode node = new AVLPlayerNode(b, 2.0);
		
		node.insert(a, 1.0);
		node.insert(c, 3.0);
		node.insert(d, 4.0);
		node.insert(e, 5.0);
		
		assertEquals(node.balanceF(node),-1);
	}
	
	@Test
	void deleteTest() {
		Player a = new Player("a", 1, 1.0);
		Player b = new Player("b", 2, 2.0);
		Player c = new Player("c", 3, 3.0);
		
		
		AVLPlayerNode node = new AVLPlayerNode(b, 2.0);
		
		node.insert(a, 1.0);
		node.insert(c, 3.0);
		
		
		node.delete(1.0);
		
		assertEquals(node.treeString(), "(b(c))");
		
	}
	
	@Test
	void getRankTest() {
		Player a = new Player("a", 1, 1.0);
		Player b = new Player("b", 2, 2.0);
		Player c = new Player("c", 3, 3.0);
		Player d = new Player("d", 4, 4.0);
		Player e = new Player("e", 5, 5.0);
		
		
		AVLPlayerNode node = new AVLPlayerNode(b, 2.0);
		
		node.insert(a, 1.0);
		node.insert(c, 3.0);
		node.insert(d, 4.0);
		node.insert(e, 5.0);
		assertEquals(node.getRank(5.0), 1);
	}
	


}
