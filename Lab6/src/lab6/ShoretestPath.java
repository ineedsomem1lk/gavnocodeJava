package lab6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class ShoretestPath {

	@Test
	void testShortestPath() 
	{
		Graph test = new Graph();
		
		Node<String> node = new Node<String>("Misha",1);
		Node<String> node2 = new Node<String>("StepOne",2);
		Node<String> node3 = new Node<String>("Slavik",3);
		Node<String> node4 = new Node<String>("ArteK",4);
		Node<String> node5 = new Node<String>("Kotya",5);
		
		test.edges.add(new Edge(2,5,1));
		test.edges.add(new Edge(1,2,1));
		test.edges.add(new Edge(2,3,2));
		test.edges.add(new Edge(3,4,3));
		test.edges.add(new Edge(4,5,4));
		test.edges.add(new Edge(1,4,1));
		test.edges.add(new Edge(5,1,1));
		test.edges.add(new Edge(1,5,1));
		
		test.graph.add(node);
		test.graph.add(node2);
		test.graph.add(node3);
		test.graph.add(node4);
		test.graph.add(node5);
		
		String result = test.findShortestWay("Misha","Slavik");
		assertEquals("Misha --> StepOne --> Slavik",result);
	}

}
