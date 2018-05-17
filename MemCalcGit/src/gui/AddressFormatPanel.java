package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class AddressFormatPanel extends JPanel {
	//------------------------------------------------------------------------------
	// CLASS SCOPE VARIABLES	
	AddressFieldPanel address = new AddressFieldPanel();
	AddressFieldPanel tagField = new AddressFieldPanel();
	AddressFieldPanel blockField = new AddressFieldPanel();
	AddressFieldPanel offsetField = new AddressFieldPanel();
	
	//------------------------------------------------------------------------------
	// CONSTRUCTORS
	public AddressFormatPanel() {
		setLayout(new FlowLayout());
		
		// Binary Address
		address.setTextLabel(" ");
		address.setFieldLabel("Address:  ");
		address.setLengthLabel(0);
		
		// Tag field
		tagField.setTextLabel("Tag");
		tagField.setFieldLabel(" ");
		tagField.setLengthLabel(0);
		
		// Block field
		blockField.setTextLabel("Block");
		blockField.setFieldLabel(" ");
		blockField.setLengthLabel(0);
		
		// Offset field
		offsetField.setTextLabel("Offset");
		offsetField.setFieldLabel(" ");
		offsetField.setLengthLabel(0);
		
		add(address);
		add(tagField);
		add(blockField);
		add(offsetField);
	}
	
	//----------------------------------------------------------------------------
	// SETTERS
	
	/*
	 * Set the background color of the exponent panel.
	 * <p>
	 * Useful for demonstrating relationships between logarithmic 
	 * parts and other properties (like tags, cache blocks, and offsets)
	 * when using multiple LogarithmicPanels together.
	 * @param bgColor The desired background color as a Color object
	 */
	
	public void setTagBGColor(Color bgColor) {
		tagField.setFieldLabelBGColor(bgColor);
	}
	
	public void setBlockBGColor(Color bgColor) {
		blockField.setFieldLabelBGColor(bgColor);
	}
	
	public void setOffsetBGColor(Color bgColor) {
		offsetField.setFieldLabelBGColor(bgColor);
	}
	
	public void setAddressBGColor(Color bgColor) {
		address.setFieldLabelBGColor(bgColor);
	}
	
	/*
	 * Set the text in the address field labels.
	 * <p>
	 * Used by MainMemoryCalc to connect the logarithmic panel exponents
	 * to the field lengths of each field, displayed visually in the fields.
	 * @param numBits The integer length of the field in bits
	 */
	
	public void setTagLength(int numBits) {
		tagField.setLengthLabel(numBits);
	}
	
	public void setBlockLength(int numBits) {
		blockField.setLengthLabel(numBits);
	}
	
	public void setOffsetLength(int numBits) {
		offsetField.setLengthLabel(numBits);
	}
	
	public void setAddressLength(int numBits) {
		address.setLengthLabel(numBits);
	}
	
	/*
	 * Set the text in the address field labels.
	 * <p>
	 * Used by MainMemoryCalc to set the text labels of each block
	 * with its String binary value.
	 * @param binStr The binary string
	 */
	
	public void setTagLabel(String binStr) {
		tagField.setFieldLabel(binStr);
	}
	
	public void setBlockLabel(String binStr) {
		blockField.setFieldLabel(binStr);
	}
	
	public void setOffsetLabel(String binStr) {
		offsetField.setFieldLabel(binStr);
	}
}
