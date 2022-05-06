package sae.graph;

import java.util.ArrayList;
import java.util.List;

public class GraphSoluce {
	
	private List<Node> soluce;
	
	public GraphSoluce() {
		soluce = new ArrayList<Node>();
		
	}
	public void add(Node node) {
		soluce.add(node);
		
	}
	public List<Node> getSoluce() {
		return soluce;
	}

}
