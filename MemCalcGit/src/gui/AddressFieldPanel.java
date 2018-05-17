package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AddressFieldPanel extends JPanel {
	//------------------------------------------------------------------------------
	// CLASS SCOPE VARIABLES
	JLabel textLabel = new JLabel(" ");
	JLabel fieldLabel = new JLabel(" ");
	JLabel lengthLabel = new JLabel(" ");
	
	//------------------------------------------------------------------------------
	// CONSTRUCTORS
	public AddressFieldPanel() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		textLabel.setFont(new Font("SansSerif", 0, 18));
		fieldLabel.setFont(new Font("SansSerif", 0, 24));
		lengthLabel.setFont(new Font("SansSerif", 1, 18));
		
		textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		fieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lengthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		add(textLabel);
		add(fieldLabel);
		add(lengthLabel);
	}
	
	//------------------------------------------------------------------------------
	// PUBLIC SETTERS
	
	// Set the text.
	public void setTextLabel(String text) {
		textLabel.setText(text);
	}
	
	public void setFieldLabel(String text) {
		fieldLabel.setText(text);
	}
	
	public void setLengthLabel(int numBits) {
		lengthLabel.setText(numBits + " bits");
	}
	
	// Set the background color.
	public void setFieldLabelBGColor(Color color) {
		fieldLabel.setOpaque(true);
		fieldLabel.setBackground(color);
	}
}
