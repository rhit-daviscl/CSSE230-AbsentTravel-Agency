import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
//edited
public class Graph{
	
	public ArrayList<Node> getNodes(){
		ArrayList<Node> list = new ArrayList<Node>();
		for(String key : G.keySet()) {
			list.add(G.get(key));
		}
		return list;
	}
	
	public ArrayList<Edge> getEdges(){
		ArrayList<Edge> e = new ArrayList<Edge>();
		for(Node n : this.getNodes()) {
			for(Edge f : n.getEdges()) {
				e.add(f);
			}
		}
		return e;
	}
	
	public Node getNode(String s) {
		for(String key : G.keySet()) {
			if(G.get(key).Label.equals(s)) {
				return G.get(key);
			}
		}
		return null;
	}
	
	public class Edge{
		public int distance;
		public int time;
		public String EndLocation;
		public String StartLocation;
		public Edge(int d, int t, String StartLocation, String endLocation) {
			this.distance = d;
			this.EndLocation = endLocation;
			this.StartLocation = StartLocation;
			this.time = t;
		}
		public String toString() {
			String str = "\n" + StartLocation + "==> distance:"+distance+"==> time:"+time+"==>"+EndLocation;
			
			
			return str;
		}
	}
	
	public class Node {
		public String Label;
		public int distanceCost;
		public int timeCost;
		public ArrayList<Edge> edges;
		public Node(String s) {
			this.Label = s;
			this.edges = new ArrayList<Edge>();
		}
		public String toString() {
			return edges.toString();
		}

		public void setName(String newName) {
			this.Label = newName;
		}
		public int hashCode() {
			return Label.hashCode();
		}
		public void addEdge (String End,int d, int t ) {
			
			Edge e = new Edge(d,t, this.Label, End);
			this.edges.add(e);
		}
		public ArrayList<Edge> getEdges(){
			return edges;
		}
		public ArrayList<String> citiesReachableTimeNode(int time,ArrayList<String> citiesVisited){
			
			ArrayList<String> result = new ArrayList<String>();
			for(Edge e: G.get(this.Label).edges) {
				
				if(e.time <= time) {
					
					if(!citiesVisited.contains(e.EndLocation)) {
						
						result.add(e.EndLocation);
						citiesVisited.add(e.EndLocation);
						result.addAll(G.get(e.EndLocation).citiesReachableTimeNode(time-e.time, citiesVisited));
					}
					
				}
				
			}
			return result;
		}
		public ArrayList<String> citiesReachableDistanceNode(int distance,ArrayList<String> citiesVisited){
			
			ArrayList<String> result = new ArrayList<String>();
			for(Edge e: G.get(this.Label).edges) {
				
				if(e.distance <= distance) {
					//System.out.println(this.Label+"==>"+e.EndLocation);
					if(!citiesVisited.contains(e.EndLocation)) {
						result.add(e.EndLocation);
						
						citiesVisited.add(e.EndLocation);
					}
					//System.out.println(e.EndLocation+":distance:"+e.distance);
					result.addAll(G.get(e.EndLocation).citiesReachableDistanceNode(distance-e.distance, citiesVisited));
					
				}

			}
			return result;
		}
	
	
	}
	
	
	public class DijkstraAlgorithm {
		public ArrayList<Node> nodes;
		public ArrayList<Edge> edges;
		public Set<Node> settledNodes;
		public Set<Node> unsettledNodes;
		public Map<Node, Node> precursors;
		public Map<Node, Integer> dist;
		
		public DijkstraAlgorithm(Graph G) {
			this.nodes = new ArrayList<Node>(G.getNodes());
			this.edges = new ArrayList<Edge>(G.getEdges());
		}
		
		public void begin(Node n) {
			settledNodes = new HashSet<Node>();
			unsettledNodes = new HashSet<Node>();
			dist = new HashMap<Node, Integer>();
			precursors = new HashMap<Node, Node>();
			dist.put(n, 0);
			unsettledNodes.add(n);
			while(unsettledNodes.size() > 0) {
				Node node = getMin(unsettledNodes);
				settledNodes.add(node);
				unsettledNodes.remove(node);
				getMinDistance(node);
			}
			
		}
		
		public void getMinDistance(Node n) {
			ArrayList<Node> adjacentNodes = getNeighbors(n);
			for(Node dest : adjacentNodes) {
				if(getShortestDistance(dest) > getShortestDistance(dest) + getDistance(n, dest)) {
					dist.put(dest, getShortestDistance(n) + getDistance(n, dest));
					precursors.put(dest, n);
					unsettledNodes.add(dest);
				}
			}
		}
		
		public int getDistance(Node n, Node dest) {
			for(Edge e : edges) {
				if(getNode(e.StartLocation).equals(n) && getNode(e.EndLocation).equals(dest)) return e.distance;
			}
			throw new NullPointerException();
		}
		
		
		public ArrayList<Node> getNeighbors(Node n){
			ArrayList<Node> neighbors = new ArrayList<Node>();
			for(Edge e : edges) {
				if(getNode(e.StartLocation).equals(n) && !isSettled(getNode(e.EndLocation))) {
					neighbors.add(getNode(e.EndLocation));
				}
			}
			return neighbors;
		}
		
		public Node getMin(Set<Node> nodes) {
			Node min = null;
			for(Node n : nodes) {
				if(min == null) min = n;
				else {
					if(getShortestDistance(n) < getShortestDistance(min)) min = n;
				}
			}
			return min;
		}
		
		public int getShortestDistance(Node dest) {
			Integer d = dist.get(dest);
			if(d == null) return Integer.MAX_VALUE;
			else {
				return d;
			}
		}
		
		public boolean isSettled(Node n) {
			return settledNodes.contains(n);
		}
		
		public ArrayList<Node> getPath(Node dest){
			ArrayList<Node> path = new ArrayList<Node>();
			Node step = dest;
			if(precursors.get(step) == null) return null;
			path.add(step);
			
			while(precursors.get(step) != null) {
				step = precursors.get(step);
				path.add(step);
			}
			Collections.reverse(path);
			return path;
		}
		
	}
	
	Map<String, Node> G = null;
	public Graph() {
		G = new HashMap<>();
	}
	public boolean addLocation(String cityName) {
		if(!G.containsKey(cityName)) {
			Node cityNode = new Node(cityName);
			G.put(cityName, cityNode);
			return true;
		}
		return false;
	}
	public boolean addPath(String start, String end,int d, int t ) {
		if(!G.containsKey(start)) {
			Node cityNode = new Node(start);
			G.put(start, cityNode);
		}
		
		if(!G.containsKey(end)) {
			Node cityNode = new Node(end);
			G.put(end, cityNode);
		}
	
		G.get(start).addEdge(end, d, t);

		G.get(end).addEdge(start, d, t);
		return true;
	}
	public ArrayList<String> citiesReachableTime(String start, int time){
		if(!G.containsKey(start)) {
			throw new IllegalArgumentException("the city - "+start+" does not exsist on map.");
		}
		return G.get(start).citiesReachableTimeNode(time, new ArrayList<String>(Arrays.asList(start)));
	
	}
	public ArrayList<String> citiesReachableDistance(String start, int distance){
		if(!G.containsKey(start)) {
			throw new IllegalArgumentException("the city - "+start+" does not exsist on map.");
		}
		return G.get(start).citiesReachableDistanceNode(distance, new ArrayList<String>(Arrays.asList(start)));
	
	}
	
	public ArrayList<String> getShortestPathDistance(String start, String end) {
		if(start.equals(end)) throw new IllegalArgumentException("The starting city cannot be the same as the destination city.");
		if(start == null || end == null) throw new IllegalArgumentException("One or more of the cities is invalid.");
		if(!G.containsKey(start)) throw new IllegalArgumentException("Start city does not exist.");
		if(!G.containsKey(end)) throw new IllegalArgumentException("Destination city does not exist.");
		
		DijkstraAlgorithm d = new DijkstraAlgorithm(this);
		d.begin(getNode(start));
		ArrayList<Node> path = d.getPath(getNode(end));
		ArrayList<String> strings = new ArrayList<String>();
		for(Node n : path) {
			strings.add(n.Label);
		}
		return strings;
	}
	
	public String toString() {
		String graph = "";
		for(String key: G.keySet()) {
			graph += key+ "==>" +G.get(key).toString()+ "\n";
	
	}
		return graph;
	}
	
	
	
	
	
	
}