package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import calc.Address;
import calc.DirectMappedCache;

public class MainMemoryCalc {
	//------------------------------------------------------------------------------
	// CLASS SCOPE VARIABLES
	private JFrame frame = new JFrame();
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 400;
	
	Font bigFont = new Font("SansSerif", 0, 20);
	Color paleYellow = new Color(255, 255, 150);
	Color paleRed = new Color(255, 187, 187);
	Color paleBlue = new Color(170, 200, 255);
	Color medYellow = new Color(230, 230, 100);
	
	// container panels for sub-panels
	JPanel mainFramePanel = new JPanel();
	JPanel logParts = new JPanel();
	JPanel fieldDesc = new JPanel();
	JPanel hexAddr = new JPanel();
	JPanel statusPanel = new JPanel();
	
	// sub-panels
	LogarithmicPanel mainMemory;
	LogarithmicPanel cacheBlocks;
	LogarithmicPanel cacheBytes;
	JTextField hexAddrGiven = new JTextField(50);
	JLabel statusLabel = new JLabel();
	AddressFormatPanel addProp;
	
	// error handling for inputs
	Status statusMainMem;
	Status statusCacheBlocks;
	Status statusCacheBytes;
	Status statusHexAddr;
	
	//------------------------------------------------------------------------------
	// CONSTRUCTORS
	public MainMemoryCalc() {
		mainFramePanel.setLayout(new GridBagLayout());
		
		createLogParts();
		createHexAddressPanel();
		createStatusPanel();
		createAddressPanel();
		
		// add main to frame
		frame.add(mainFramePanel);
		frame.pack();
		frame.setTitle("Main Memory Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setVisible(true);
	}
	
	//------------------------------------------------------------------------------
	// PANEL/FRAME SECTIONS
	private void createLogParts() {
		logParts.setLayout(new GridLayout(1, 3));
		
		// SET-UP: byte-addressable main memory (bytes) # panel
		mainMemory = new LogarithmicPanel();
		statusMainMem = new Status("mainMemory");
		mainMemory.setBorder(new TitledBorder(new EtchedBorder(), "byte-addressable main memory"));
		mainMemory.setExponentBGColor(paleYellow);
		
		// SET-UP: cache blocks # panel
		cacheBlocks = new LogarithmicPanel();
		statusCacheBlocks = new Status("cacheBlocks");
		cacheBlocks.setBorder(new TitledBorder(new EtchedBorder(), "cache blocks"));
		cacheBlocks.setExponentBGColor(paleRed);
		
		// SET-UP: cache blocks # panel
		cacheBytes = new LogarithmicPanel();
		statusCacheBytes = new Status("cacheBytes");
		cacheBytes.setBorder(new TitledBorder(new EtchedBorder(), "bytes in a cache block"));
		cacheBytes.setExponentBGColor(paleBlue);
		
		// add to container panel
		logParts.add(mainMemory);
		logParts.add(cacheBlocks);
		logParts.add(cacheBytes);
		
		// arrange & add container panel to main frame panel
		GridBagConstraints gbcLog = new GridBagConstraints();
		gbcLog.fill = GridBagConstraints.HORIZONTAL;
		gbcLog.gridx = 0;
		gbcLog.gridy = 0;
		gbcLog.gridwidth = 2;
		mainFramePanel.add(logParts, gbcLog);
	}
	
	private void createHexAddressPanel() {
		hexAddr.setLayout(new BoxLayout(hexAddr, BoxLayout.X_AXIS));
		statusHexAddr = new Status("hexAddr");
		
		JLabel ohEx = new JLabel("   0x");
		
		JButton hexAddrReady = new JButton("Calculate!");
		AddHexAddrListener lsnrHexAddr = new AddHexAddrListener();
		hexAddrReady.addActionListener(lsnrHexAddr);
		
		hexAddr.add(hexAddrReady);
		hexAddr.add(ohEx);
		hexAddr.add(hexAddrGiven);
		
		// arrange & add container panel to main frame panel
		GridBagConstraints gbcHex = new GridBagConstraints();
		gbcHex.fill = GridBagConstraints.HORIZONTAL;
		gbcHex.gridx = 1;
		gbcHex.gridy = 1;
		gbcHex.weightx = 0.25;
		mainFramePanel.add(hexAddr, gbcHex);
	}
	
	private void createStatusPanel() {
		statusLabel.setText("Please enter the required given numbers.");
		statusLabel.setOpaque(true);
		
		statusPanel.add(statusLabel);
		
		// arrange & add container panel to main frame panel
		GridBagConstraints gbcStatus = new GridBagConstraints();
		gbcStatus.fill = GridBagConstraints.HORIZONTAL;
		gbcStatus.gridx = 0;
		gbcStatus.gridy = 1;
		gbcStatus.weightx = 0.25;
		mainFramePanel.add(statusPanel, gbcStatus);
	}
	
	private void createAddressPanel() {
		addProp = new AddressFormatPanel();
		
		// set exponent relation colors
		addProp.setAddressBGColor(paleYellow);
		addProp.setOffsetBGColor(paleBlue);
		addProp.setBlockBGColor(paleRed);
		addProp.setTagBGColor(medYellow);
		
		// arrange & add container panel to main frame panel
		GridBagConstraints gbcAddr = new GridBagConstraints();
		gbcAddr.fill = GridBagConstraints.HORIZONTAL;
		gbcAddr.gridx = 0;
		gbcAddr.gridy = 2;
		gbcAddr.gridwidth = 2;
		mainFramePanel.add(addProp, gbcAddr);
	}	
	
	//------------------------------------------------------------------------------
	// UPDATE METHODS
	private void updateAddressPanel() {
		// find field lengths
		int addressBits = mainMemory.getExponentNum();
		int blockBits = cacheBlocks.getExponentNum();
		int offsetBits = cacheBytes.getExponentNum();
		int tagBits = addressBits - offsetBits - blockBits;
		
		// address field lengths
		addProp.setTagLength(tagBits);
		addProp.setBlockLength(blockBits);
		addProp.setOffsetLength(offsetBits);
		addProp.setAddressLength(addressBits);
		
		// address field labels
		String hexGiven = hexAddrGiven.getText().toUpperCase();
		DirectMappedCache cacheAddress = new DirectMappedCache("HEX", hexGiven, blockBits, offsetBits);
		
		addProp.setTagLabel(cacheAddress.getTagField());
		addProp.setBlockLabel(cacheAddress.getBlockField());
		addProp.setOffsetLabel(cacheAddress.getOffsetField());
	}
	
	private void updateStatusPanel(int numErrors) {
		if ( numErrors > 0 ) {
			statusLabel.setBackground(paleRed);
			statusLabel.setOpaque(true);
		} else {
			statusLabel.setOpaque(false);
		}
		
		String statusText = "<html>";
		
		if ( statusMainMem.getStatusType() != StatusType.READY ) {
			statusText = statusText 
					+ "Left Logarithmic Panel: <br> - " 
					+ statusMainMem.getStatusMessage()
					+ "<br><br>";
		}
		
		if ( statusCacheBlocks.getStatusType() != StatusType.READY ) {
			statusText = statusText 
					+ "Center Logarithmic Panel: <br> - " 
					+ statusCacheBlocks.getStatusMessage()
					+ "<br><br>";
		}
		
		if ( statusCacheBytes.getStatusType() != StatusType.READY ) {
			statusText = statusText 
					+ "Right Logarithmic Panel: <br> - "
					+ statusCacheBytes.getStatusMessage()
					+ "<br><br>";
		}
		
		if ( statusHexAddr.getStatusType() != StatusType.READY ) {
			statusText = statusText 
					+ "Hexadecimal Address: <br> - "
					+ statusHexAddr.getStatusMessage()
					+ "<br><br>";
		}
		
		statusText = statusText + "</html>";
		
		statusLabel.setText(statusText);
	}
	
	private void resetStatus() {
		statusMainMem.setStatusType(StatusType.WAITING);
		statusCacheBlocks.setStatusType(StatusType.WAITING);
		statusCacheBytes.setStatusType(StatusType.WAITING);
		statusHexAddr.setStatusType(StatusType.WAITING);
	}
	
	//------------------------------------------------------------------------------
	// INPUT ERROR HANDLING
	/**
	 * Currently, there can only be one StatusType per Status, so the last
	 * of multiple errors will overwrite the others. Each helper errorPanel()
	 * method should check errors in order of ascending desired precedence, with
	 * the most urgent error checked last.
	 * 
	 * Check status
	 * 		"Soft" errors. Incorrect input that is a result of user typos or
	 * 			misunderstandings of the material.
	 * 		Example: Given hex address is smaller than calculated memory address size.
	 * 
	 * Error status
	 * 		"Hard" errors. Input errors that cause panels to have calculation or
	 * 			display errors.
	 * 		Example: Input field is blank or not selected.
	 */
	private int hasInputErrors() {
		int errorCount = 0;
		
		// -- modules to check -------------------------------------
		errorCount = errorCount 	
				+ errorMainMem()		// mainMemory logarithmic panel
				+ errorCacheBlocks()	// cacheBlocks logarithmic panel
				+ errorCacheBytes()		// cacheBytes logarithmic panel
				+ errorHexAddr();		// hexAddr textfield
		
		updateStatusPanel(errorCount);
		return errorCount;
	}
	
	private int errorMainMem() {
		int errorCount = 0;
		
		// Blank input
		int addressBits = mainMemory.getExponentNum();
		if ( isBlank(addressBits) ) {
			statusMainMem.setStatusType(StatusType.BLANK_INPUT);
			errorCount++;
		}
		
		// Exponent/Result not selected
		if ( !mainMemory.isRadioButtonSelected() ) {
			statusMainMem.setStatusType(StatusType.RADIO_CHOICE_INPUT);
			errorCount++;
		}
		
		// set status to READY if no errors
		if ( errorCount == 0 ) {
			statusMainMem.setStatusType(StatusType.READY);
		}
		
		return errorCount;
	}
	
	private int errorCacheBlocks() {
		int errorCount = 0;
		
		// Blank input
		int blockBits = cacheBlocks.getExponentNum();
		if ( isBlank(blockBits) ) {
			statusCacheBlocks.setStatusType(StatusType.BLANK_INPUT);
			errorCount++;
		}
		
		// Exponent/Result not selected
		if ( !cacheBlocks.isRadioButtonSelected() ) {
			statusCacheBlocks.setStatusType(StatusType.RADIO_CHOICE_INPUT);
			errorCount++;
		}
		
		// set status to READY if no errors
		if ( errorCount == 0 ) {
			statusCacheBlocks.setStatusType(StatusType.READY);
		}
		
		return errorCount;
	}
	
	private int errorCacheBytes() {
		int errorCount = 0;
		
		// Blank input
		int offsetBits = cacheBytes.getExponentNum();
		if ( isBlank(offsetBits) ) {
			statusCacheBytes.setStatusType(StatusType.BLANK_INPUT);
			errorCount++;
		}
		
		// Exponent/Result not selected
		if ( !cacheBytes.isRadioButtonSelected() ) {
			statusCacheBytes.setStatusType(StatusType.RADIO_CHOICE_INPUT);
			errorCount++;
		}
		
		// set status to READY if no errors
		if ( errorCount == 0 ) {
			statusCacheBytes.setStatusType(StatusType.READY);
		}

		return errorCount;
	}
	
	private int errorHexAddr() {
		int errorCount = 0;
		String hexGiven = hexAddrGiven.getText().toUpperCase();
		int addressBits = mainMemory.getExponentNum();
		
		// Hex address size does not match calculated memory address size.
		if ( addressBits < (hexGiven.length()*4) ) {  // *4 because hex2bin
			statusHexAddr.setStatusType(StatusType.ADDRESS_LARGE);
			errorCount++;
		}
		if ( addressBits > (hexGiven.length()*4) ) {  // *4 because hex2bin
			statusHexAddr.setStatusType(StatusType.ADDRESS_SMALL);
			errorCount++;
		}

		// Not a hexadecimal value
		int check = 0;

		for ( int i = 0; i < hexGiven.length(); i++ ) {
			int digit = hexGiven.charAt(i);
			if ( !isHex(digit) ) {
				check++;
			}
		}
		if ( check > 0 ) {
			statusHexAddr.setStatusType(StatusType.EXPECT_HEX);
			errorCount++;
		}
				
		// Blank input
		if ( isBlank(hexGiven.length()) ) { 
			statusHexAddr.setStatusType(StatusType.BLANK_INPUT);
			errorCount++;
		} 
			
		// set status to READY if no errors
		if ( errorCount == 0 ) {
			statusHexAddr.setStatusType(StatusType.READY);
		}
		
		return errorCount;
	}
	
	/**
	 * Checks if value is 0 (which can also be empty).
	 * @param value The value to be checked.
	 * @return
	 */
	private boolean isBlank(int value) {
		if ( value == 0 ) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if value is a valid hexadecimal digit using UTF-16b codes.
	 * @param value The value to be checked.
	 * @return
	 */
	private boolean isHex(int value) {
		boolean isHex = false;
		
		if ( value > 47 && value < 58 ) { // UTF 0048-0057 = char 0123456789
			isHex = true;
		}
		
		if ( value > 64 && value < 71 ) { // UTF 0065-0070 = char ABCDEF
			isHex = true;
		}
		
		// UTF 0097-0102 = char abcdef
		// however: hexGiven.getText().toUpperCase() works, 
		// so long as both code & error-check use it
		//		code:		 line 189, in updateAddressPanel()
		//		error-check: line 355, in errorHexAddr()
		
		return isHex;
	}
	
	//------------------------------------------------------------------------------
	// LISTENERS
	class AddHexAddrListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			resetStatus();
			if ( hasInputErrors() == 0 ) {
				updateAddressPanel();
			}
		}
	}
}
