import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProgramRunner {
	
	private static NavigationProject project;
	private static Graph graph;

	public static void main(String[] args) {
		graph = new Graph();
		project = new NavigationProject(graph);
		graph.addLocation("Chicago");
		graph.addPath("Chicago","New York", 7, 7);
		graph.addPath("Chicago","Seattle", 18, 18);
		graph.addPath("Seattle","New York", 31, 30);
		graph.addPath("Seattle", "Salt Lake", 8, 10);
		graph.addPath("Salt Lake","Chicago", 8, 8);
		graph.addPath("Salt Lake", "Boise", 4, 5);
		graph.addPath("Salt Lake", "Ogden", 1, 1);
		graph.addPath("Seattle", "Tacoma", 4, 2);
		graph.addPath("Salt Lake", "Tacoma", 7, 6);
	}

}
