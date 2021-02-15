import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NavigationProject {
	
	private JFrame frame;
	
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
	private JPanel searchPanel;
	
	//Search by Distance and Time
	private JTextField searchBarDistTime;
	private JPanel searchByDistTime;
	private JButton searchBut2;
	private JCheckBox distanceCheckBox;
	private JCheckBox timeCheckBox;
	
	//Results
	private JPanel resultsPanel;
	
	public NavigationProject() {
		frame = new JFrame();
		frame.setTitle("CSSE 230 Navigation Project");
		
		//Search Bar Adds
		searchBarStarting = new JTextField(30);
		searchBar = new JTextField(30);
		search = new JButton("Search");
		
		JLabel startingPos = new JLabel("Starting Position");
		JLabel endPos = new JLabel("Ending Position");
		
		searchPanel = new JPanel( new GridLayout(5, 2));
		
		searchPanel.add(startingPos, BorderLayout.BEFORE_FIRST_LINE);
		searchPanel.add(searchBarStarting, BorderLayout.AFTER_LINE_ENDS);
		searchPanel.add(endPos, BorderLayout.AFTER_LINE_ENDS);
		searchPanel.add(searchBar, BorderLayout.AFTER_LINE_ENDS);
		searchPanel.add(search, BorderLayout.EAST);
		frame.add(searchPanel, BorderLayout.NORTH);
		
		//Distance Panel Adds
		checkBoxPanel = new JPanel();
		frame.add(checkBoxPanel, BorderLayout.EAST);
		checkBoxPanel.setLayout(new GridLayout(8, 1));
		
		JLabel distance = new JLabel("Distance");
		distFar = new JCheckBox("Far");
		distClose = new JCheckBox("Close");
		
		checkBoxPanel.add(distance, BorderLayout.BEFORE_FIRST_LINE);
		checkBoxPanel.add(distFar, BorderLayout.AFTER_LINE_ENDS);
		checkBoxPanel.add(distClose, BorderLayout.AFTER_LINE_ENDS);
		
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
		
		searchBarDistTime = new JTextField();
		searchByDistTime = new JPanel(new GridLayout(8, 1));
		searchBut2 = new JButton("Search");
		
		searchByDistTime.add(distanceCheckBox);
		searchByDistTime.add(timeCheckBox);
		searchByDistTime.add(searchBarDistTime);
		searchByDistTime.add(searchBut2);
		frame.add(searchByDistTime, BorderLayout.WEST);
		
		//Result Adds
		resultsPanel = new JPanel(new GridLayout(30, 1));
		
		frame.add(resultsPanel, BorderLayout.SOUTH);
		
		//Frame visible
		frame.setVisible(true);
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void addResults(String location) {
		JLabel result = new JLabel(location);
		resultsPanel.add(result);
	}

}
