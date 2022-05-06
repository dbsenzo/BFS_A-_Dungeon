package sae.graph;

import java.util.HashMap;

import sae.dungeon.Coord;
import sae.dungeon.Direction;

public class Node {
	
	private String name;
	private Coord coord;
	private HashMap<Node,Direction> neighbors;
	private float f;
	private float h;
	private float g;
	
	public Node(String name, Coord coord) {
		super();
		this.name = name;
		this.coord = coord;
		neighbors = new HashMap<Node, Direction>();
	}

	public HashMap<Node, Direction> neighbors() {
		return neighbors;
	}
	
	public void addNeighbors(Node node, Direction dir ) {
		neighbors.put(node, dir);
	}
	
	@Override
	public String toString() {
		String result = "";
		for (Node node : neighbors.keySet()) {
			 result += node.getName()+",";
		}
		return name;
		
	}
	
	public String getName() {
		return name;
	}
	
	public Coord getCoord() {
		return coord;
	}
	
	public void setF(float f) {
        this.f = f;
    }

    public void setHeuri(float h) {
        this.h = h;
    }

    public void setG(float g) {
        this.g = g;
    }

    public float getG() {
        return g;
    }
	

	@Override
	public boolean equals(Object obj) {	
		return this.getName() == ((Node) obj).getName();
	}
}

