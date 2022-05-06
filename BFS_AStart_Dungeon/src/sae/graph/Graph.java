package sae.graph;

import java.util.ArrayList;

import sae.dungeon.Direction;

public class Graph {

	ArrayList<Node> graph_array = new ArrayList<>();

	public Graph() {
		super();

	}

	public void addNode(Node node) {
		graph_array.add(node);

	}


	public void addEdge(Node node, Node node2, Direction dir, Direction inverse) {
		node.addNeighbors(node2, dir);
		node2.addNeighbors(node, inverse);
	}

	public Node getNodeByName(String name) {
		for(Node node : graph_array) {
			if(node.getName().equalsIgnoreCase(name)) {
				return node;
			}
		}
		return null;

	}


	public ArrayList<Node> getGraph_array() {
		return graph_array;
	}

	public void setGraph_array(ArrayList<Node> graph_array) {
		this.graph_array = graph_array;
	}

	public String toString() {
		String result = ""; 
		for (Node node : graph_array) {
			result += node.toString();
		}
		return result;
	}
}
