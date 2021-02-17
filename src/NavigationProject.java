import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NavigationProject {
	
	private JFrame frame;
	private Graph graph;
	
	//Search Bar
	private JTextField searchBarStarting;
	private JTextField searchBar;
	private JButton search;
	private JButton clear;
	private JPanel searchPanel;
	
	//Search by Distance and Time
	private JTextField searchBarDistTime;
	private JPanel searchByDistTime;
	private JButton searchBut2;
	private JCheckBox distanceCheckBox;
	private JCheckBox timeCheckBox;
	
	//Add to graph
	private JButton addButton;
	private JTextField startingLocation;
	private JTextField endingLocation;
	private JTextField distanceAdd;
	private JTextField timeAdd;
	private JPanel addPanel;
	
	//Results
	private JPanel resultsPanel;
	
	public NavigationProject(Graph graph) {
		this.graph = graph;
		frame = new JFrame();
		frame.setTitle("CSSE 230 Navigation Project");
		
		//Search Bar Adds
		searchBarStarting = new JTextField(30);
		searchBar = new JTextField(30);
		search = new JButton("Search");
		clear = new JButton("Clear");
		
		JLabel startingPos = new JLabel("Starting City");
		JLabel endPos = new JLabel("Ending City");
		JLabel instruc = new JLabel("If searching for the shortest distance or time between cities, input the starting and");
		JLabel instruc2 = new JLabel(" ending city and choose distance or time.");
		JLabel instruc3 = new JLabel("If searching for all cities reachable by a certain time or distance,  select distance or");
		JLabel instruc4 = new JLabel(" time and input amount desired.");
		
		searchPanel = new JPanel( new GridLayout(5, 2));
		
		searchPanel.add(instruc);
		searchPanel.add(instruc2);
		searchPanel.add(instruc3);
		searchPanel.add(instruc4);
		searchPanel.add(startingPos, BorderLayout.BEFORE_FIRST_LINE);
		searchPanel.add(searchBarStarting, BorderLayout.AFTER_LINE_ENDS);
		searchPanel.add(endPos, BorderLayout.AFTER_LINE_ENDS);
		searchPanel.add(searchBar, BorderLayout.AFTER_LINE_ENDS);
		searchPanel.add(search, BorderLayout.EAST);
		searchPanel.add(clear, BorderLayout.SOUTH);
		clear.addActionListener(new ClearButtonListener());
		search.addActionListener(new SearchButtonListener());
		frame.add(searchPanel, BorderLayout.NORTH);
		
		//Search By Time or Distance
		distanceCheckBox = new JCheckBox("Search by Distance");
		timeCheckBox = new JCheckBox("Search by Time");
		
		JLabel instructionSpecial = new JLabel(" ");
		JLabel instructionSpecial2 = new JLabel(" ");
		searchBarDistTime = new JTextField();
		searchByDistTime = new JPanel(new GridLayout(8, 1));
		searchBut2 = new JButton("Specific Time or Distance Search");
		
		searchByDistTime.add(instructionSpecial);
		searchByDistTime.add(instructionSpecial2);
		searchByDistTime.add(distanceCheckBox);
		searchByDistTime.add(timeCheckBox);
		searchByDistTime.add(searchBarDistTime);
		searchByDistTime.add(searchBut2);
		searchBut2.addActionListener(new SearchDistOrTimeButtonListener());
		frame.add(searchByDistTime, BorderLayout.WEST);
		
		//Add Button Adds
		addButton = new JButton("Add City");
		JLabel addInstructions = new JLabel("Input a Starting and Ending City to add as");
		JLabel addInstructions2 = new JLabel("well as a distance and time between them.");
		startingLocation = new JTextField();
		JLabel startingLoc = new JLabel("New Starting City Name");
		endingLocation = new JTextField();
		JLabel endLoc = new JLabel("New Ending City Name");
		distanceAdd = new JTextField();
		JLabel distAdd = new JLabel("New City-City Distane");
		timeAdd = new JTextField();
		JLabel tIMEaDD = new JLabel("New City-City Time");
		addPanel = new JPanel(new GridLayout(10, 1));
		
		addPanel.add(addInstructions);
		addPanel.add(addInstructions2);
		addPanel.add(startingLoc);
		addPanel.add(startingLocation);
		addPanel.add(endLoc);
		addPanel.add(endingLocation);
		addPanel.add(distAdd);
		addPanel.add(distanceAdd);
		addPanel.add(tIMEaDD);
		addPanel.add(timeAdd);
		addPanel.add(addButton);
		addButton.addActionListener(new AddButtonListener());
		frame.add(addPanel, BorderLayout.EAST);
		
		//Frame visible
		frame.setVisible(true);
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void addResults(ArrayList<String> locations) {
		JLabel intro = new JLabel("Result of Search");
		resultsPanel.add(intro);
		for(int i = 0; i < locations.size(); i++) {
			JLabel result = new JLabel(locations.get(i));
			resultsPanel.add(result);
		}
		frame.add(resultsPanel, BorderLayout.SOUTH);
		frame.revalidate();
	}
	
	public void clearResults() {
		resultsPanel = new JPanel(new GridLayout(30, 1));
		String totalLocations = "All Locations On Map:    ";
		for(String key: graph.G.keySet()) {
			totalLocations = totalLocations + key + ";    ";
		}
		totalLocations = totalLocations + "Add more above";
		JLabel intro = new JLabel(totalLocations);
		resultsPanel.add(intro);
		frame.add(resultsPanel, BorderLayout.SOUTH);
		frame.revalidate();
	}
	
	public class SearchButtonListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				clearResults();
				ArrayList<String> result = new ArrayList<String>();
				ArrayList<String> timeResult = new ArrayList<String>();
				if(searchBarStarting.getText() == null || searchBarStarting.getText().trim().isEmpty()) {
					result.add("Give a Starting Location");
					addResults(result);
					return;
					//Make the results panel say this
				}
				if(searchBar.getText() == null || searchBar.getText().trim().isEmpty()) {
					result.add("Give a Ending Location");
					addResults(result);
					return;
					//Make the results panel say this
				}
				String searchStarting = searchBarStarting.getText();
				String searchEnding = searchBar.getText();
				ArrayList<String> bothTimeAndDist = new ArrayList<String>();
				if(graph.getNode(searchStarting) == null) {
					result.add("Use an actual location in Graph");
					addResults(result);
					return;
					//Make the results panel say this
				}
				if(graph.getNode(searchEnding) == null) {
					result.add("Use an actual location in Graph");
					addResults(result);
					return;
					//Make the results panel say this
				}
				if(!distanceCheckBox.isSelected() && !timeCheckBox.isSelected()) {
					result.add("Select the search type");
					addResults(result);
					return;
					//Make the results panel say this
				}
				if(distanceCheckBox.isSelected()) {
					result = graph.getShortestPathDistance(searchStarting, searchEnding);
					if(timeCheckBox.isSelected()) {
						//Search for both distance close and time short
						timeResult = graph.getShortestPathTime(searchStarting, searchEnding);
						for(int i = 0; i < result.size(); i++) {
							for(int j = 0; j < timeResult.size(); j++) {
								if(result.get(i) == timeResult.get(j)) {
									bothTimeAndDist.add(result.get(i));
								}
							}
						}
						addResults(bothTimeAndDist);
						return;
					}
					else {
						//Search for distance close
						addResults(result);
						return;
					}
				}
				if(timeCheckBox.isSelected()) {
					timeResult = graph.getShortestPathTime(searchStarting, searchEnding);
					if(distanceCheckBox.isSelected()) {
						//Search for both distance close and time short
						result = graph.getShortestPathDistance(searchStarting, searchEnding);
						for(int i = 0; i < result.size(); i++) {
							for(int j = 0; j < timeResult.size(); j++) {
								if(result.get(i) == timeResult.get(j)) {
									bothTimeAndDist.add(result.get(i));
								}
							}
						}
						addResults(bothTimeAndDist);;
						return;
					}
					else {
						//Search for time short
						addResults(timeResult);
						return;
					}
				}
			}
		}
	
	public class SearchDistOrTimeButtonListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				clearResults();
				ArrayList<String> result = new ArrayList<String>();
				if(searchBarDistTime.getText() == null || searchBarDistTime.getText().trim().isEmpty()) {
					result.add("Give a search number");
					addResults(result);
					return;
					//Make the results panel say this
				}
				int searchedNumber;
				try {
					searchedNumber = Integer.parseInt(searchBarDistTime.getText());
				} catch(NumberFormatException nfe) {
					result.add("Use an actual number in Seach Bar");
					addResults(result);
					return;
					//Make the results panel say this
				}
				if(searchedNumber >= 85) {
					result.add("This Number Input is too large");
					addResults(result);
					return;
					//Make the results panel say this
				}
				String searchStarting = searchBarStarting.getText();
				if(graph.getNode(searchStarting) == null) {
					result.add("Use an actual location in Graph");
					addResults(result);
					return;
					//Make the results panel say this
				}
				if(searchStarting == null || searchStarting.trim().isEmpty()) {
					result.add("Give a Starting Location");
					addResults(result);
					return;
					//Make the results panel say this
				}
				if(!distanceCheckBox.isSelected() && !timeCheckBox.isSelected()) {
					result.add("Select the search type");
					addResults(result);
					return;
					//Make the results panel say this
				}
				if(distanceCheckBox.isSelected()) {
					if(timeCheckBox.isSelected()) {
						result.add("Only have 1 search parameter");
						addResults(result);
						return;
						//Make the results panel say this
					}
				}
				if(distanceCheckBox.isSelected()) {
					result = graph.citiesReachableDistance(searchStarting, searchedNumber);
					addResults(result);
					return;
					//Search for selected distance
				}
				if(timeCheckBox.isSelected()) {
					result = graph.citiesReachableTime(searchStarting, searchedNumber);
					addResults(result);
					return;
					//Search for selected time
				}
			}	
		}
	
	public class AddButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			clearResults();
			ArrayList<String> result = new ArrayList<String>();
			if(startingLocation.getText() == null || startingLocation.getText().trim().isEmpty()) {
				result.add("Input a Starting City");
				addResults(result);
				return;
				//Make the results panel say this
			}
			if(endingLocation.getText() == null || endingLocation.getText().trim().isEmpty()) {
				result.add("Input an Ending City");
				addResults(result);
				return;
				//Make the results panel say this
			}
			int distance;
			try {
				distance = Integer.parseInt(distanceAdd.getText());
			} catch(NumberFormatException nfe) {
				result.add("Use an actual number in Seach Bar");
				addResults(result);
				return;
				//Make the results panel say this
			}
			int time;
			try {
				time = Integer.parseInt(timeAdd.getText());
			} catch(NumberFormatException nfe) {
				result.add("Use an actual number in Seach Bar");
				addResults(result);
				return;
				//Make the results panel say this
			}
			graph.addPath(startingLocation.getText(), endingLocation.getText(), distance, time);
			result.add("City Has Been Added");
			addResults(result);
			return;
		}
	}
	
	public class ClearButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			clearResults();
			distanceCheckBox.setSelected(false);
			timeCheckBox.setSelected(false);
			searchBarStarting.setText("");
			searchBar.setText("");
			searchBarDistTime.setText("");
			startingLocation.setText("");
			endingLocation.setText("");
			distanceAdd.setText("");;
			timeAdd.setText("");;
		}
	}
}
