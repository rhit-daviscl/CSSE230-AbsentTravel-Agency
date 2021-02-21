import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
//edited
public class Graph{
	
	public Node getNode(String s) {
		return G.get(s);
	}
	
	//Node class that acts as pin point for each city
	public class Node {
		public String Label;
		public int distanceCost;
		public int timeCost;
		public ArrayList<Edge> edges;
		public Node(String s) {
			this.Label = s;
			this.edges = new ArrayList<Edge>();
		}
		// returns string form of all the edges the node is connected to
		public String toString() {
			return edges.toString();
		}
		// function to reset a cities name
		public void setName(String newName) {
			this.Label = newName;
		}
		
		public void addEdge (Node End,int d, int t ) {
			
			Edge e = new Edge(d,t, this.Label, End);
			this.edges.add(e);
		}
		public ArrayList<Edge> getEdges(){
			return edges;
		}
		
		public void citiesReachableTimeNode(int time,ArrayList<String> unvisitedCities, int costSoFar, ArrayList<String> result){
			if(unvisitedCities.isEmpty()) {
				return ;
			}
			for(Edge e: this.edges) {
				if(e.time <= time) {
					
					if(unvisitedCities.remove(e.EndLocationName)) {
						
						result.add(e.EndLocationName+": "+(e.time+costSoFar)+" minute(s)");
						e.endNode.citiesReachableTimeNode(time-e.time, unvisitedCities, (costSoFar+e.time), result);
					}
					
				}
				
			}
		}
		public void citiesReachableDistanceNode(int distance,ArrayList<String> unvisitedCities, int costSoFar, ArrayList<String> result){
			if(unvisitedCities.isEmpty()) {
				return;
			}
			for(Edge e: this.edges) {
				
				if(e.distance <= distance) {
					
					if(unvisitedCities.remove(e.EndLocationName)) {
						
						result.add(e.EndLocationName+": "+(e.distance+costSoFar)+" mile(s)");
						
					}
					e.endNode.citiesReachableDistanceNode(distance-e.distance, unvisitedCities, (costSoFar+e.distance), result);
					
				}
				
			}

		}
	
	}
	
	public class Edge{
		public int distance;
		public int time;
		public String EndLocationName;
		public Node endNode;
		public String StartLocation;
		public Edge(int d, int t, String StartLocation, Node endLocation) {
			this.distance = d;
			this.EndLocationName = endLocation.Label;
			this.endNode = endLocation;
			this.StartLocation = StartLocation;
			this.time = t;
		}
		public String toString() {
			String str = "\n" + StartLocation + "==> distance:"+distance+"==> time:"+time+"==>"+EndLocationName;
			
			
			return str;
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
				if(getShortestDistance(dest) > getShortestDistance(n) + getDistance(n, dest)) {
					dist.put(dest, getShortestDistance(n) + getDistance(n, dest));
					precursors.put(dest, n);
					unsettledNodes.add(dest);
				}
			}
		}
		
		public int getDistance(Node n, Node dest) {
			for(Edge e : edges) {
				if(getNode(e.StartLocation).equals(n) && getNode(e.EndLocationName).equals(dest)) return e.distance;
			}
			throw new NullPointerException();
		}
		
		
		public ArrayList<Node> getNeighbors(Node n){
			ArrayList<Node> neighbors = new ArrayList<Node>();
			for(Edge e : edges) {
				if(getNode(e.StartLocation).equals(n) && !isSettled(getNode(e.EndLocationName))) {
					neighbors.add(getNode(e.EndLocationName));
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
			Node cityStart = new Node(start);
			G.put(start, cityStart);
		}
		
		if(!G.containsKey(end)) {
			Node cityEnd = new Node(end);
			G.put(end, cityEnd);
		}
	
		G.get(start).addEdge(G.get(end), d, t);

		G.get(end).addEdge(G.get(start), d, t);
		return true;
	}
	public ArrayList<String> citiesReachableTime(String start, int time){
		
		if(!G.containsKey(start)) {
			throw new IllegalArgumentException("the city - "+start+" does not exsist on map.");
		}
		ArrayList<String> unvisitedCities = new ArrayList<String>();
		for(String x: G.keySet()) {
			
			if(!(x.equals(start))) {
				
				unvisitedCities.add(x);
			}
		}
		
		ArrayList<String> result = new ArrayList<String>();
		//Note distances will not be shortest just first found
		
		G.get(start).citiesReachableTimeNode(time, unvisitedCities, 0, result);
		
		return result;
	
	}
	public ArrayList<String> citiesReachableDistance(String start, int distance){
		if(!G.containsKey(start)) {
			throw new IllegalArgumentException("the city - "+start+" does not exsist on map.");
		}
		ArrayList<String> unvisitedCities = new ArrayList<String>();
		for(String x: G.keySet()) {
			if( !(x.equals(start))) {
				unvisitedCities.add(x);
			}
		}
		//System.out.println(unvisitedCities.remove(start));
		//Note distances will not shortest just first found. 
		ArrayList<String> result = new ArrayList<String>();
		G.get(start).citiesReachableDistanceNode(distance, unvisitedCities,0, result);
		return result;
	
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
	
	public ArrayList<String> getShortestPathTime(String start, String end) {
		if(start.equals(end)) throw new IllegalArgumentException("The starting city cannot be the same as the destination city.");
		if(start == null || end == null) throw new IllegalArgumentException("One or more of the cities is invalid.");
		if(!G.containsKey(start)) throw new IllegalArgumentException("Start city does not exist.");
		if(!G.containsKey(end)) throw new IllegalArgumentException("Destination city does not exist.");
		
		//save all the distances in a temp array
		ArrayList<Integer> distances = new ArrayList<Integer>();
		int index = 0;
		for(Edge e : this.getEdges()) {
			distances.add(e.distance);
		}
		//for each edge, set its distance equal to its time
		for(Edge e : this.getEdges()) {
			e.distance = e.time;
		}
		//run our algorithm with these "distances"
		DijkstraAlgorithm d = new DijkstraAlgorithm(this);
		d.begin(getNode(start));
		ArrayList<Node> path = d.getPath(getNode(end));
		ArrayList<String> strings = new ArrayList<String>();
		for(Node n : path) {
			strings.add(n.Label);
		}
		//now re-populate our distances with our saved values
		for(Edge e : this.getEdges()) {
			e.distance = distances.get(index);
			index++;
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
	
	
	
	
	
	
	
}