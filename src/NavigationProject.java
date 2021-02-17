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
	
	//Check Boxes
	private JPanel checkBoxPanel;
	private JCheckBox distFar;
	private JCheckBox distClose;
	private JCheckBox timeLong;
	private JCheckBox timeShort;
	
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
		
		searchPanel = new JPanel( new GridLayout(5, 2));
		
		searchPanel.add(startingPos, BorderLayout.BEFORE_FIRST_LINE);
		searchPanel.add(searchBarStarting, BorderLayout.AFTER_LINE_ENDS);
		searchPanel.add(endPos, BorderLayout.AFTER_LINE_ENDS);
		searchPanel.add(searchBar, BorderLayout.AFTER_LINE_ENDS);
		searchPanel.add(search, BorderLayout.EAST);
		searchPanel.add(clear, BorderLayout.SOUTH);
		clear.addActionListener(new ClearButtonListener());
		search.addActionListener(new SearchButtonListener());
		frame.add(searchPanel, BorderLayout.NORTH);
		
		//Distance Panel Adds
		checkBoxPanel = new JPanel();
		frame.add(checkBoxPanel, BorderLayout.EAST);
		checkBoxPanel.setLayout(new GridLayout(20, 1));
		
		JLabel distance = new JLabel("Distance");
		JLabel instructions = new JLabel("Use these Check Boxes when searching normally.");
		JLabel instructions2 = new JLabel("Fill in the Starting City and Ending City and select the search type.");
		distFar = new JCheckBox("Far");
		distClose = new JCheckBox("Close");
		
		checkBoxPanel.add(instructions, BorderLayout.BEFORE_FIRST_LINE);
		checkBoxPanel.add(instructions2, BorderLayout.AFTER_LAST_LINE);
		checkBoxPanel.add(distance, BorderLayout.WEST);
		checkBoxPanel.add(distFar, BorderLayout.EAST);
		checkBoxPanel.add(distClose, BorderLayout.EAST);
		
		// Time Panel Adds
		JLabel time = new JLabel("Time");
		timeLong = new JCheckBox("Long");
		timeShort = new JCheckBox("Short");
		
		checkBoxPanel.add(time, BorderLayout.SOUTH);
		checkBoxPanel.add(timeLong, BorderLayout.AFTER_LINE_ENDS);
		checkBoxPanel.add(timeShort, BorderLayout.AFTER_LINE_ENDS);
		
		//Search By Time or Distance
		distanceCheckBox = new JCheckBox("Search by Distance");
		timeCheckBox = new JCheckBox("Search by Time");
		
		JLabel instructionSpecial = new JLabel("Fill in the Starting City and select which type to search.");
		JLabel instructionSpecial2 = new JLabel("Put in a number for the shortest time or distance locations.");
		searchBarDistTime = new JTextField();
		searchByDistTime = new JPanel(new GridLayout(20, 1));
		searchBut2 = new JButton("Specific Search");
		
		searchByDistTime.add(instructionSpecial);
		searchByDistTime.add(instructionSpecial2);
		searchByDistTime.add(distanceCheckBox);
		searchByDistTime.add(timeCheckBox);
		searchByDistTime.add(searchBarDistTime);
		searchByDistTime.add(searchBut2);
		searchBut2.addActionListener(new SearchDistOrTimeButtonListener());
		frame.add(searchByDistTime, BorderLayout.WEST);
		
		//Result Adds
		resultsPanel = new JPanel(new GridLayout(30, 1));
		
		frame.add(resultsPanel, BorderLayout.SOUTH);
		
		//Frame visible
		frame.setVisible(true);
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void addResults(ArrayList<String> locations) {
		for(int i = 0; i < locations.size(); i++) {
			JLabel result = new JLabel(locations.get(i));
			resultsPanel.add(result);
		}
		resultsPanel.validate();
	}
	
	public void clearResults() {
		resultsPanel = new JPanel(new GridLayout(30, 1));
		resultsPanel.validate();
	}
	
	public class SearchButtonListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				clearResults();
				ArrayList<String> result = new ArrayList<String>();
				ArrayList<String> timeResult = new ArrayList<String>();
				if(searchBarStarting.getText() == null || searchBarStarting.getText().trim().isEmpty()) {
					System.out.println("Give a Starting Location");
					result.add("Give a Starting Location");
					addResults(result);
					return;
					//Make the results panel say this
				}
				if(searchBar.getText() == null || searchBar.getText().trim().isEmpty()) {
					System.out.println("Give a Ending Location");
					result.add("Give a Ending Location");
					addResults(result);
					return;
					//Make the results panel say this
				}
				String searchStarting = searchBarStarting.getText();
				String searchEnding = searchBar.getText();
				if(graph.getNode(searchStarting) == null) {
					System.out.println("Use an actual location in Graph");
					result.add("Use an actual location in Graph");
					addResults(result);
					return;
					//Make the results panel say this
				}
				if(graph.getNode(searchEnding) == null) {
					System.out.println("Use an actual location in Graph");
					result.add("Use an actual location in Graph");
					addResults(result);
					return;
					//Make the results panel say this
				}
				if(!distFar.isSelected() && !distClose.isSelected() && !timeLong.isSelected() && !timeShort.isSelected()) {
					System.out.println("Select the search type");
					result.add("Select the search type");
					addResults(result);
					return;
					//Make the results panel say this
				}
				
				if(distFar.isSelected()) {
					if(distClose.isSelected()) {
						System.out.println("Only have 1 search parameter per type");
						result.add("Only have 1 search parameter per type");
						addResults(result);
						return;
						//Make the results panel say this
					}
				}
				if(timeLong.isSelected()) {
					if(timeShort.isSelected()) {
						System.out.println("Only have 1 search parameter per type");
						result.add("Only have 1 search parameter per type");
						addResults(result);;
						return;
						//Make the results panel say this
					}
				}	
				if(distFar.isSelected()) {
					//result = graph.getLongestPathDistance(searchStarting, searchEnding);
					if(timeLong.isSelected()) {
						//Search for both distance far and time long
						//timeResult = graph.getLongestPathTime(searchStarting, searchEnding);
						System.out.println("Search far and long");
						return;
					}
					if(timeShort.isSelected()) {
						//Search for both distance far and time short
						//timeResult = graph.getShortestPathTime(searchStarting, searchEnding);
						System.out.println("Search far and short");
						return;
					}
					else {
						//Search for distance far
						System.out.println(result);
						return;
					}
				}
				if(distClose.isSelected()) {
					result = graph.getShortestPathDistance(searchStarting, searchEnding);
					if(timeLong.isSelected()) {
						//Search for both distance close and time long
						//timeResult = graph.getLongestPathTime(searchStarting, searchEnding);
						System.out.println("Search close and long");
						return;
					}
					if(timeShort.isSelected()) {
						//Search for both distance close and time short
						//timeResult = graph.getShortestPathTime(searchStarting, searchEnding);
						System.out.println("Search close and short");
						return;
					}
					else {
						//Search for distance close
						System.out.println(result);
						return;
					}
				}
				if(timeLong.isSelected()) {
					//timeResult = graph.getLongestPathTime(searchStarting, searchEnding);
					if(distFar.isSelected()) {
						//Search for both distance far and time long
						//result = graph.getLongestPathDistance(searchStarting, searchEnding);
						System.out.println("Search far and long");
						return;
					}
					if(distClose.isSelected()) {
						//Search for both distance close and time long
						result = graph.getShortestPathDistance(searchStarting, searchEnding);
						System.out.println("Search close and long");
						return;
					}
					else {
						//Search for time long
						System.out.println(timeResult);
						return;
					}
				}
				if(timeShort.isSelected()) {
					//timeResult = graph.getShortestPathTime(searchStarting, searchEnding);
					if(distFar.isSelected()) {
						//Search for both distance far and time short
						//result = graph.getLongestPathDistance(searchStarting, searchEnding);
						System.out.println("Search far and short");
						return;
					}
					if(distClose.isSelected()) {
						//Search for both distance close and time short
						result = graph.getShortestPathDistance(searchStarting, searchEnding);
						System.out.println("Search close and short");
						return;
					}
					else {
						//Search for time short
						addResults(timeResult);
						System.out.println(timeResult);
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
					System.out.println("Give a search number");
					result.add("Give a search number");
					addResults(result);
					return;
					//Make the results panel say this
				}
				int searchedNumber;
				try {
					searchedNumber = Integer.parseInt(searchBarDistTime.getText());
				} catch(NumberFormatException nfe) {
					System.out.println("Use an actual number in Seach Bar");
					result.add("Use an actual number in Seach Bar");
					addResults(result);
					return;
					//Make the results panel say this
				}
				if(searchedNumber >= 85) {
					System.out.println("This Number Input is too large");
					result.add("This Number Input is too large");
					addResults(result);
					return;
					//Make the results panel say this
				}
				String searchStarting = searchBarStarting.getText();
				if(graph.getNode(searchStarting) == null) {
					System.out.println("Use an actual location in Graph");
					result.add("Use an actual location in Graph");
					addResults(result);
					return;
					//Make the results panel say this
				}
				if(searchStarting == null || searchStarting.trim().isEmpty()) {
					System.out.println("Give a Starting Location");
					result.add("Give a Starting Location");
					addResults(result);
					return;
					//Make the results panel say this
				}
				if(!distanceCheckBox.isSelected() && !timeCheckBox.isSelected()) {
					System.out.println("Select the search type");
					result.add("Select the search type");
					addResults(result);
					return;
					//Make the results panel say this
				}
				if(distanceCheckBox.isSelected()) {
					if(timeCheckBox.isSelected()) {
						System.out.println("Only have 1 search parameter");
						result.add("Only have 1 search parameter");
						addResults(result);
						return;
						//Make the results panel say this
					}
				}
				if(distanceCheckBox.isSelected()) {
					result = graph.citiesReachableDistance(searchStarting, searchedNumber);
					addResults(result);
					System.out.println(result);
					return;
					//Search for selected distance
				}
				if(timeCheckBox.isSelected()) {
					result = graph.citiesReachableTime(searchStarting, searchedNumber);
					addResults(result);
					System.out.println(result);
					return;
					//Search for selected time
				}
			}	
		}
	
	public class ClearButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			clearResults();
			distClose.setSelected(false);
			distFar.setSelected(false);
			timeShort.setSelected(false);
			timeLong.setSelected(false);
			distanceCheckBox.setSelected(false);
			timeCheckBox.setSelected(false);
//			searchBarStarting = new JTextField();
//			searchBar = new JTextField();
//			searchBarDistTime = new JTextField();
		}
	}
}
