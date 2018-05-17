package gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GridBagLayoutPlay {
	//------------------------------------------------------------------------------
	// CLASS SCOPE VARIABLES
	private JFrame frame = new JFrame();
	private static final int FRAME_WIDTH = 900;
	private static final int FRAME_HEIGHT = 500;
	
	JPanel mainFramePanel = new JPanel();
	
	Font bigFont = new Font("SansSerif", 0, 20);
	Color paleYellow = new Color(255, 255, 150);
	Color paleRed = new Color(255, 187, 187);
	Color paleBlue = new Color(170, 200, 255);
	Color medYellow = new Color(230, 230, 100);
	
	JLabel labelT1;
	JLabel labelT2;
	JLabel labelB1;
	JLabel labelB2;
	JLabel labelO1;
	JLabel labelO2;
	
	//------------------------------------------------------------------------------
	// CONSTRUCTORS
	public GridBagLayoutPlay() {
		mainFramePanel.setLayout(new GridBagLayout());
		
		createLabels();
		setLabelsToLayout();
		
		// add main to frame
		frame.add(mainFramePanel);
		frame.setTitle("GridBagLayout Play Frame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setVisible(true);
	}
	
	//------------------------------------------------------------------------------
	// PANELS
	public void createLabels() {
		labelT1 = new JLabel("[Tag #]");
		labelB1 = new JLabel("[Block #]");
		labelO1 = new JLabel("[Offset #]");
		
		labelT2 = new JLabel("tag length/tag length/tag length/tag length");
		labelB2 = new JLabel("block length/block length");
		labelO2 = new JLabel("offset length");
		
		labelT1.setFont(bigFont);
		labelB1.setFont(bigFont);
		labelO1.setFont(bigFont);
		labelT2.setFont(bigFont);
		labelB2.setFont(bigFont);
		labelO2.setFont(bigFont);
		
		labelT1.setBackground(medYellow);
		labelB1.setBackground(paleBlue);
		labelO1.setBackground(paleRed);
		labelT2.setBackground(medYellow);
		labelB2.setBackground(paleBlue);
		labelO2.setBackground(paleRed);
		
		labelT1.setOpaque(true);
		labelB1.setOpaque(true);
		labelO1.setOpaque(true);
		labelT2.setOpaque(true);
		labelB2.setOpaque(true);
		labelO2.setOpaque(true);
	}
	
	public void setLabelsToLayout() {
		GridBagConstraints lt1 = new GridBagConstraints();
		GridBagConstraints lb1 = new GridBagConstraints();
		GridBagConstraints lo1 = new GridBagConstraints();
		GridBagConstraints lt2 = new GridBagConstraints();
		GridBagConstraints lb2 = new GridBagConstraints();
		GridBagConstraints lo2 = new GridBagConstraints();
		
		lt1.gridx = 0;
		lt1.gridy = 0;
		mainFramePanel.add(labelT1, lt1);
		
		lb1.gridx = 1;
		lb1.gridy = 0;
		mainFramePanel.add(labelB1, lb1);
		
		lo1.gridx = 2;
		lo1.gridy = 0;
		mainFramePanel.add(labelO1, lo1);
		
		lt2.gridx = 0;
		lt2.gridy = 1;
		mainFramePanel.add(labelT2, lt2);
		
		lb2.gridx = 1;
		lb2.gridy = 1;
		mainFramePanel.add(labelB2, lb2);
		
		lo2.gridx = 2;
		lo2.gridy = 1;
		mainFramePanel.add(labelO2, lo2);
	}
}
