import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
//edited
public class Graph{
	
	public class Edge{
		public int distance;
		public int time;
		public String EndLocation;
		public Edge(int d, int t, String endLocation) {
			this.distance = d;
			this.EndLocation = endLocation;
			this.time = t;
		}
		public String toString() {
			String str = "\n==> distance:"+distance+"==> time:"+time+"==>"+EndLocation;
			
			
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

			Edge e = new Edge(d,t,End);
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
	
	public String toString() {
		String graph = "";
		for(String key: G.keySet()) {
			graph += key+ "==>" +G.get(key).toString()+ "\n";
	
	}
		return graph;
	}
	
	
	
	
	
	
}
