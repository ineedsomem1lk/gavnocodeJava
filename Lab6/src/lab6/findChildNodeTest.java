package lab6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class findChildNodeTest {

	@Test
	void test() {
		Graph test = new Graph();
		
		Node<String> node = new Node<String>("A",1);
		Node<String> node2 = new Node<String>("B",2);
		Node<String> node3 = new Node<String>("C",3);
		Node<String> node4 = new Node<String>("D",4);
		Node<String> node5 = new Node<String>("E",5);
		Node<String> node6 = new Node<String>("F",6);
		Node<String> node7 = new Node<String>("G",7);
		
		test.edges.add(new Edge(1,2,1));
		test.edges.add(new Edge(2,3,1));
		test.edges.add(new Edge(2,4,1));
		test.edges.add(new Edge(2,5,1));
		test.edges.add(new Edge(2,6,1));
		test.edges.add(new Edge(3,7,1));
		test.edges.add(new Edge(5,6,1));
		test.edges.add(new Edge(6,7,1));
		
		test.graph.add(node);
		test.graph.add(node2);
		test.graph.add(node3);
		test.graph.add(node4);
		test.graph.add(node5);
		test.graph.add(node6);
		test.graph.add(node7);
		String result = test.findChildNode(2);
		assertEquals(result,"3 4 5 6 ");
	}

}
