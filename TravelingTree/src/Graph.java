import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph{
	public class Edge {
		Node n;
		int w;
		public Edge(Node n, int w) {
			this.n = n;
			this.w = w;
		}

		public int getTime(Node n) {
			return n.timeCost;
		}
		public int getDistance(Node n) {
			return n.distanceCost;
		}
	}
	public class Node {
		public String Label;
		public int distanceCost;
		public int timeCost;
		private String attachedNode;

		public Node(String s,String e, int t, int d) {
			this.Label = s;
			this.attachedNode = e;
			this.distanceCost = d;
			this.timeCost = t;
		}
		public String toString() {
			return "|"+Label+"-->distance:"+distanceCost+" mile(s) -->time:"+timeCost+" hr(s) -->:"+attachedNode;
		}

		public void setName(String newName) {
			this.Label = newName;
		}
		public int hashCode() {
			return Label.hashCode();
		}

	}
	Map<String, List<Node>> G = null;
	public Graph() {
		G = new HashMap<>();
	}
	public boolean addEdge (String nodeStart, String nodeEnd,int d, int t ) {
		if(!G.containsKey(nodeStart)) {
			G.put(nodeStart, new LinkedList<>());
		}
		G.get(nodeStart).add(new Node(nodeStart,nodeEnd,d,t));
		return true;
	}
	public boolean addPath(String nodeStart, String nodeEnd,int d, int t ) {
		addEdge(nodeEnd, nodeStart, d, t);
		addEdge(nodeStart, nodeEnd, d,t);
		
		return true;
	}
	public String toString() {
		String graph = "";
		for(String key: G.keySet()) {
			graph += key+ "==>"+G.get(key)+"\n";
		}
		
		return graph;
	}
	
	
	
	
	
}
