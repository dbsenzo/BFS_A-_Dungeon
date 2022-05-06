
package sae.solver;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

import sae.graph.GraphSoluce;
import sae.graph.Node;

public class SolverWithAstar implements Solver {

	private Node nodeArrive;
	private Node nodeDepart;
	private Queue<Node> closedList = new LinkedList<Node>();
	private List<Node> leChemin = new LinkedList<Node>();
	private int i = 0;
	private GraphSoluce graph = new GraphSoluce();
	private Map<Node, Node> pi = new HashMap<Node, Node>();

	public SolverWithAstar(Node nodeDepart, Node nodeArrive) {
		super();
		this.nodeArrive = nodeArrive;
		this.nodeDepart = nodeDepart;
	}

	public Comparator<Node> compareParHeuristique = new Comparator<Node>() {
		public int compare(Node n1, Node n2) {
			if (getF(n1) < getF(n2)) {
				return -1;
			}else if (getF(n1) > getF(n2)){
				return 1;
			}else {
				return 0;
			}
		}
	};


	public float distance(Node nodeA, Node nodeB) {
		int xA = nodeA.getCoord().getX();
		int yA = nodeA.getCoord().getY();


		int xB = nodeB.getCoord().getX();
		int yB = nodeB.getCoord().getY();

		float t = (Math.abs(xB - xA) + Math.abs(yB - yA));
		return t;
	}


	public void resolve() {
		PriorityQueue<Node> openList = new PriorityQueue<>(compareParHeuristique);
		openList.add(nodeDepart);

		while (!openList.isEmpty()){
			Node u = openList.poll();
			leChemin.add(u);
			if ((u.getCoord().getX() == nodeArrive.getCoord().getX()) && (u.getCoord().getY() == nodeArrive.getCoord().getY())) {
				i++;
			}

			for (Node v : u.neighbors().keySet()) {
				if ((!closedList.contains(v)) || ((openList.contains(v)) && (getF(v) < getF(u)))) {
					setG(v,getG(u));
					setF(v,getG(v) + distance(v,nodeArrive));
					openList.add(v);
					pi.put(v,u);
					i++;
				}
			}
			closedList.add(u);
		}
	}


	public float getF(Node node) {
	
		return node.getG() + distance(nodeArrive,node);

	}

	public float getG(Node node) {
		return node.getG();
	}

	public float getH(Node node) {
		
		return distance(nodeArrive,node);
	}

	public void setG(Node node, float g) {
		node.setG(g + 1);
	}
	public void setF(Node node, float g) {
		node.setF(g);
	}
	public void setH(Node node, float h) {
		node.setG(h);
	}

	public void afficheList(List<Node> leChemin2) {
		for (Node n : leChemin2) {
			System.out.println(n.getName());
		}
	}

	public void retourner(List<Node> node_List_To_Reverse, List<Node> newList) {
		for (int i = node_List_To_Reverse.size() - 1; i >= 0; i--) {
			Node n = node_List_To_Reverse.get(i);
			newList.add(n);
		}
	}


	@Override
	public GraphSoluce getSoluce() {
		List<Node> solution = new ArrayList<Node>();
		List<Node> newList = new ArrayList<Node>();
		Node current = nodeArrive;
		for (int i = 0; i < pi.size() -1; i++) {
			for (Entry<Node, Node> v : pi.entrySet()) {
				if (v.getKey().getName() == current.getName()) {
					solution.add(v.getValue());
					current = v.getValue();
				}
			}
		}
		retourner(solution, newList);
		newList.add(nodeArrive);
		for (int i = 0; i < newList.size(); i++) {
			graph.add(newList.get(i));
		}

		return graph;
	}

	@Override
	public int getSteps() {
		return i;
	}
}

