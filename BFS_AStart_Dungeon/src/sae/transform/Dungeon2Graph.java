package sae.transform;


import java.util.Map.Entry;
import java.util.Set;


import sae.dungeon.Direction;
import sae.dungeon.Dungeon;
import sae.dungeon.DungeonSoluce;
import sae.dungeon.Room;
import sae.graph.Graph;
import sae.graph.GraphSoluce;
import sae.graph.Node;

public class Dungeon2Graph {

	Graph graph1 = new Graph();	


	private Dungeon dungeon;

	public Dungeon2Graph(Dungeon dungeon) {
		this.dungeon = dungeon;
		transformRoom_AddNeightbors();
	}

	public Set<Room> getRooms(){
		return dungeon.getRooms();
	}

	public void transformRoom_AddNeightbors() {
		for(Room room: getRooms()) {
			Node x = new Node(room.getName(), room.getCoords());
			graph1.addNode(x);
		}
		for (Room room : dungeon.getRooms()) {
			Node n = graph1.getNodeByName(room.getName());
			for(Direction direction : Direction.values()) {
				if(room.getNextRooms().get(direction) != null) {
					graph1.addEdge(n, graph1.getNodeByName(room.getNextRooms().get(direction).getName()), room.getDirection(room.getNextRooms().get(direction)), room.getNextRooms().get(direction).getDirection(room));
					
				}
			}

		}

	}


	public Room getRoomByName(String name) {
		for(Room room : dungeon.getRooms()) {
			if(room.getName().equalsIgnoreCase(name)) {
				return room;
			}
		}
		return null;

	}

	public Node mappedNode(Room room) {
		return graph1.getNodeByName(room.getName());
	}

	public Room mappedRoom(Node node) {
		return dungeon.getRoomByName(node.getName());
	}

	@Override
	public String toString() {
		return graph1.toString();
	}

	public DungeonSoluce transform(GraphSoluce soluceGraphBFS) {
	
        DungeonSoluce transform = new DungeonSoluce();
        Node old = null;
        for(Node n : soluceGraphBFS.getSoluce()) {
        	if(old != null) {
        		for( Entry<Node, Direction> neigh_dir :old.neighbors().entrySet()) {
        			if(neigh_dir.getKey() == n) {
        				transform.addDirection(neigh_dir.getValue());
        				
        				
        			}
        		}
        			
        	}
        	old = n;
        }
        
		return transform;
	}
}
        