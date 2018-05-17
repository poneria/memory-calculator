package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class LogarithmicPanel extends JPanel {
	//------------------------------------------------------------------------------
	// CLASS SCOPE VARIABLES
	private int exponentNum;
	private boolean radioButtonSelected;
	
	JPanel exponentPanel = new JPanel();
	JLabel baseTwo = new JLabel("2^");
	JLabel equalsSign = new JLabel("=");
	JLabel exponent = new JLabel("");
	JLabel expResult = new JLabel("");
	
	JPanel givensPanel = new JPanel();
	JTextField givenLog = new JTextField(200);
	JLabel givenLabel = new JLabel("given");
	ButtonGroup rbgLogarithmic = new ButtonGroup();
	JRadioButton rbExponent = new JRadioButton("as the exponent");
	JRadioButton rbExpResult = new JRadioButton("as the result");
	
	//------------------------------------------------------------------------------
	// CONSTRUCTORS
	public LogarithmicPanel() {
		setLayout(new GridLayout(2,4));
		
		createGivensPanel();
		add(givensPanel);
		
		createExponentPanel();
		add(exponentPanel);
	}
	
	//----------------------------------------------------------------------------
	// GETTERS
	public int getExponentNum() {
		return exponentNum;
	}
	
	public boolean isRadioButtonSelected() {
		return radioButtonSelected;
	}
	
	//----------------------------------------------------------------------------
	// SETTERS
	private void setExponentNum(double num) {
		exponentNum = (int)(num);
	}
	
	//----------------------------------------------------------------------------
	// PANEL BACKGROUND COLOR SETTERS
	/**
	 * Set the background color of the exponent panel.
	 * <p>
	 * Useful for demonstrating relationships between logarithmic 
	 * parts and other properties, like tags, cache blocks, and offsets.
	 * @param bgColor The desired background color
	 */
	public void setExponentBGColor(Color bgColor) {
		exponent.setOpaque(true);
		exponent.setBackground(bgColor);
	}
	
	//----------------------------------------------------------------------------
	// PANELS
	private void createGivensPanel() {
		givensPanel.setLayout(new GridLayout(2, 2));
		
		ActionListener lsnrExponent = new AddExponentListener();
		rbExponent.setActionCommand("exponent");
		rbExponent.addActionListener(lsnrExponent);
		
		ActionListener lsnrExpResult = new AddExponentListener();
		rbExpResult.setActionCommand("result");
		rbExpResult.addActionListener(lsnrExpResult);
		
		rbgLogarithmic.add(rbExponent);
		rbgLogarithmic.add(rbExpResult);
		
		// row 1
		givensPanel.add(givenLabel);
		givensPanel.add(rbExponent);
		
		// row 2
		givensPanel.add(givenLog);
		givensPanel.add(rbExpResult);
	}
	
	private void createExponentPanel() {
		exponentPanel.setLayout(new FlowLayout());
		
		Font bigFont = new Font("SansSerif", 0, 20);
		Font biggerFont = new Font("SansSerif", 0, 28);
		
		exponent.setFont(bigFont);
		
		baseTwo.setFont(biggerFont);
		equalsSign.setFont(biggerFont);
		expResult.setFont(biggerFont);
		
		exponentPanel.add(baseTwo);
		exponentPanel.add(exponent);
		exponentPanel.add(equalsSign);
		exponentPanel.add(expResult);
	}

	//----------------------------------------------------------------------------
	// INTERACTIVE
	class AddExponentListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {		
			try {
				// calculate & arrange labels for a correct expression
				if ( event.getActionCommand().compareTo("exponent") == 0 ) {
					radioButtonSelected = true;
					double exponentGiven = Double.parseDouble(givenLog.getText());
					double resultAnswer = Math.pow(2, exponentGiven);
					
					exponent.setText("" + exponentGiven);
					expResult.setText("" + resultAnswer);
					setExponentNum(exponentGiven);
					
					rbExponent.setBackground(new Color(58, 215, 120));
					rbExpResult.setBackground(null);
				} else if ( event.getActionCommand().compareTo("result") == 0 ) {
					radioButtonSelected = true;
					double resultGiven = Double.parseDouble(givenLog.getText());
					double exponentAnswer = Math.log10(resultGiven) / Math.log10(2);
					
					exponent.setText("" + exponentAnswer);
					expResult.setText("" + resultGiven);
					setExponentNum(exponentAnswer);
					
					rbExponent.setBackground(null);
					rbExpResult.setBackground(new Color(58, 215, 120));
				} else { // radio button not selected
					radioButtonSelected = false;
				}
				
			} catch (NumberFormatException nfe) {
				System.out.println("Logarithmic panel has no text input to process value.");
			}
		}
	}
}
