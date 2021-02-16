import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	public NavigationProject() {
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
		checkBoxPanel.setLayout(new GridLayout(8, 1));
		
		JLabel distance = new JLabel("Distance");
		JLabel instructions = new JLabel("Use these Check Boxes when searching normally.");
		distFar = new JCheckBox("Far");
		distClose = new JCheckBox("Close");
		
		checkBoxPanel.add(instructions, BorderLayout.BEFORE_FIRST_LINE);
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
		
		searchBarDistTime = new JTextField();
		searchByDistTime = new JPanel(new GridLayout(8, 1));
		searchBut2 = new JButton("Search");
		
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
	
	public void addResults(String location) {
		JLabel result = new JLabel(location);
		resultsPanel.add(result);
	}
	
	private class SearchButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(distFar.isSelected()) {
				if(timeLong.isSelected()) {
					//Search for both distance far and time long
					System.out.println("Search far and long");
					return;
				}
				if(timeShort.isSelected()) {
					//Search for both distance far and time short
					System.out.println("Search far and short");
					return;
				}
				else {
					//Search for distance far
					System.out.println("Search far");
					return;
				}
			}
			if(distClose.isSelected()) {
				if(timeLong.isSelected()) {
					//Search for both distance close and time long
					System.out.println("Search close and long");
					return;
				}
				if(timeShort.isSelected()) {
					//Search for both distance close and time short
					System.out.println("Search close and short");
					return;
				}
				else {
					//Search for distance close
					System.out.println("Search close");
					return;
				}
			}
			if(timeLong.isSelected()) {
				if(distFar.isSelected()) {
					//Search for both distance far and time long
					System.out.println("Search far and long");
					return;
				}
				if(distClose.isSelected()) {
					//Search for both distance close and time long
					System.out.println("Search close and long");
					return;
				}
				else {
					//Search for time long
					System.out.println("Search long");
					return;
				}
			}
			if(timeShort.isSelected()) {
				if(distFar.isSelected()) {
					//Search for both distance far and time short
					System.out.println("Search far and short");
					return;
				}
				if(distClose.isSelected()) {
					//Search for both distance close and time short
					System.out.println("Search close and short");
					return;
				}
				else {
					//Search for time short
					System.out.println("Search short");
					return;
				}
			}
		}
	}
	
	private class SearchDistOrTimeButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(distanceCheckBox.isSelected()) {
				//Search for selected distance
			}
			if(timeCheckBox.isSelected()) {
				//Search for selected time
			}
			System.out.println("Dist or Time Search");
		}	
	}
	
	private class ClearButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			distClose.setSelected(false);
			distFar.setSelected(false);
			timeShort.setSelected(false);
			timeLong.setSelected(false);
			distanceCheckBox.setSelected(false);
			timeCheckBox.setSelected(false);
			//searchBarStarting
			//searchBar.resetKeyboardActions();
			//searchBarDistTime.resetKeyboardActions();
			//resultsPanel.removeAll();
		}
	}

}
