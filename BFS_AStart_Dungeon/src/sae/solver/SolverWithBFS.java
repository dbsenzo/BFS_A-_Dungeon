package sae.solver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import sae.graph.GraphSoluce;
import sae.graph.Node;


public class SolverWithBFS implements Solver {

	private ArrayList<Node> visited = new ArrayList<>();
	private Map<Node, Node> pi = new HashMap<Node, Node>();
	private ArrayList<Node> node_List_To_Reverse = new ArrayList<Node>();
	private Queue<Node> que;
	private Node start, end;
	private int steps = 0;

	public SolverWithBFS(Node start, Node end) {
		this.start = start;
		this.end = end;
		que = new LinkedList<Node>();

	}

	public void resolve() {
		
		que.add(start);
		visited.add(start);
		while (!que.isEmpty()) {
			Node node = que.poll();
			for (Node voisin : node.neighbors().keySet()) {
				
				if (!visited.contains(voisin)) {
					steps++;
					que.add(voisin);
					visited.add(voisin);
					pi.put(voisin, node);
					
				}
			}
		}

	}

	@Override
	public GraphSoluce getSoluce() {
		GraphSoluce graph = new GraphSoluce();
		Node node;
		node = this.end;
		do {
			node_List_To_Reverse.add(node);
			node = pi.get(node);
		} while (node != this.start);
		node_List_To_Reverse.add(start);

		for (int i = node_List_To_Reverse.size() - 1; i >= 0; i--) {
			Node n = node_List_To_Reverse.get(i);
			graph.add(n);
		}
		return graph;
	}

	@Override
	public int getSteps() {
		return steps;
	}

}
