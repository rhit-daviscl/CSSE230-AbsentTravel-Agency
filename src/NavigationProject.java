import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NavigationProject {
	
	private JFrame frame;
	private JPanel checkBoxPanel;
	private JCheckBox distance;
	private JCheckBox time;
	private JCheckBox distFar;
	private JCheckBox distClose;
	private JCheckBox timeLong;
	private JCheckBox timeShort;
	
	
	public NavigationProject() {
		frame = new JFrame();
		frame.setTitle("CSSE 230 Navigation Project");
		
		//Search Bar Adds
		JLabel labelSearch = new JLabel("Search Bar");
		frame.add(labelSearch, BorderLayout.NORTH);
		
		checkBoxPanel = new JPanel();
		frame.add(checkBoxPanel, BorderLayout.EAST);
		checkBoxPanel.setLayout(new GridLayout(20, 1));
		
		//Distance Panel Adds
		distance = new JCheckBox("Distance");
		distFar = new JCheckBox("Far");
		distClose = new JCheckBox("Close");
		
		checkBoxPanel.add(distance, BorderLayout.NORTH);
		checkBoxPanel.add(distFar, BorderLayout.NORTH);
		checkBoxPanel.add(distClose, BorderLayout.NORTH);
		
		// Time Panel Adds
		time = new JCheckBox("Time");
		timeLong = new JCheckBox("Long");
		timeShort = new JCheckBox("Short");
		
		checkBoxPanel.add(time, BorderLayout.SOUTH);
		checkBoxPanel.add(timeLong, BorderLayout.SOUTH);
		checkBoxPanel.add(timeShort, BorderLayout.SOUTH);
		
		
		frame.setVisible(true);
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void search() {
		
	}

}
