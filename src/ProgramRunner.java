import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProgramRunner {
	
	private static NavigationProject project;
	private static Graph graph;

	public static void main(String[] args) {
		graph = new Graph();
		project = new NavigationProject(graph);
		graph.addLocation("Seattle");
		graph.addPath("Seattle", "Tacoma", 34, 34);
		graph.addPath("Seattle", "MT Rainer", 86, 103);
		graph.addPath("Tacoma", "Olympia", 31, 33);
		graph.addPath("Olympia", "MT Rainer", 65, 89);
		graph.addPath("Portland", "Olympia", 121,113);
		graph.addPath("Portland", "Boise", 436,417);
		graph.addPath("Seattle", "Boise", 494, 487);
		graph.addPath("Seattle", "Salt Lake City", 955, 904);
		graph.addPath("Boise", "Salt Lake City", 340, 304);
		graph.addPath("Seattle", "Yellowstone", 980, 930);
		graph.addPath("Boise", "Yellowstone", 1, 1);
		System.out.println(graph.getShortestPathDistance("Seattle","Yellowstone"));
		project.clearResults();
	}

}