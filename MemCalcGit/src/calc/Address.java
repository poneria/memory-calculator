package calc;

public class Address {
	//-----------------------------------------------------------------------------------
	// CLASS SCOPE VARIABLES
	private String binString;
	private String hexString;
	
	private String[] binValues = { // 0000-1111
			"0000", 
			"0001", 
			"0010", 
			"0011",
			"0100", 
			"0101", 
			"0110", 
			"0111",
			"1000", 
			"1001", 
			"1010", 
			"1011",
			"1100", 
			"1101", 
			"1110", 
			"1111"
	};
	private String[] hexValues = { // 0-F
			"0", 
			"1", 
			"2", 
			"3", 
			"4", 
			"5", 
			"6", 
			"7", 
			"8", 
			"9", 
			"A", 
			"B", 
			"C", 
			"D", 
			"E", 
			"F"
	};
	
	//-----------------------------------------------------------------------------------
	// CONSTRUCTORS
	/**
	 * Empty constructor. Sets all number base strings to "".
	 */
	public Address() {
		setBinString("");
		setHexString("");
	} 
	
	/**
	 * Constructs an address of a given base.
	 * @param baseType The given base, either BIN (base 2), DEC (base 10), or HEX (base 16).
	 * @param numberString The address in its base, without the initial 0x.
	 */
	public Address(String baseType, String numberString) {
		if ( baseType.toUpperCase().equals("BIN") ) {
			setBinString(numberString);
			convertBin2HexString();
		} else if ( baseType.toUpperCase().equals("HEX") ) {
			setHexString(numberString);
			convertHex2BinString();
		} else {
			System.out.println("Base type not supported for Address(" + baseType.toUpperCase() 
												+ ", " + numberString + ").");
		}
	}
	
	//-----------------------------------------------------------------------------------
	// GETTERS
	public String getBinString() {
		return binString;
	}
	
	public String getHexString() {
		return hexString;
	}
	
	//-----------------------------------------------------------------------------------
	// SETTERS
	private void setBinString(String binString) {
		this.binString = binString;
	}
	
	private void setHexString(String hexString) {
		this.hexString = hexString;
	}
	
	//-----------------------------------------------------------------------------------
	// PRIVATE METHODS
	private String reverseString(String str) {
		// Reverse the string, to flip working "backwards" to "forwards"
		String reverseStr = "";
		for( int i = 0; i < str.length(); i++ ) {
			String digitSubstring;
			
			if ( i + 1 >= str.length() ) { // if last digit
				digitSubstring = str.substring(i);
			} else {
				digitSubstring = str.substring(i, i+1);
			}
			
			// put that digit at the end
			reverseStr = digitSubstring + reverseStr;
		}
		return reverseStr;
	}
	
	/**
	 * Converts hexString to binString
	 * <p>
	 * Rewritten instead of using a stock hex2bin() 
	 * because operating on String-based digits for purpose of display,
	 * not on integer values for purpose of mathematical comparison.
	 */
	private void convertHex2BinString() {
		String reverseHex = reverseString(hexString);
		String binChunksTotal = "";
		
		for ( int i = 0; i < reverseHex.length(); i++ ) {
			int indexStart = i; // inclusive start
			int indexEnd = i + 1; // exclusive end, chunk = 1 digit
			if ( indexEnd > reverseHex.length() ) {
				indexEnd = i;
			}
			
			String chunk = reverseHex.substring(indexStart, indexEnd);
			
			int hexIndex = -1;
			// find in hexValues[]
			for ( int h = 0; h < hexValues.length; h++ ) {
				if ( chunk.equals(hexValues[h]) ) {
					hexIndex = h;
				}
			}
			
			// find in binValues[]
			String binChunk = "";
			try {
				binChunk = binValues[hexIndex];
			} catch (Exception e) {
				System.out.println("ERROR: Incorrect hex value detected.");
			}
			
			binChunksTotal = binChunk + binChunksTotal;
		}
		setBinString(binChunksTotal);
	}
	
	/**
	 * Converts binString to hexString
	 * <p>
	 * Rewritten instead of using a stock bin2hex() 
	 * because operating on String-based digits for purpose of display,
	 * not on integer values for purpose of mathematical comparison.
	 */
	private void convertBin2HexString() {
		String hexChunksTotal = "";
		
		for ( int i = 0; i < binString.length(); i = i + 4 ) {
			int indexStart = i; // inclusive start
			int indexEnd = i + 4; // exclusive end, chunk = 4 digits
			if ( indexEnd > binString.length() ) {
				indexEnd = i;
			}
			
			String chunk = binString.substring(indexStart, indexEnd);
			
			int binIndex = -1;
			// find in binValues[]
			for ( int b = 0; b < binValues.length; b++ ) {
				if ( chunk.equals(binValues[b]) ) {
					binIndex = b;
				}
			}
			
			// find in hexValues[]
			String hexChunk = "";
			try {
				hexChunk = hexValues[binIndex];
			} catch (Exception e) {
				System.out.println("ERROR: Cannot find hex value. Please make sure your binary chunk is 4 digits long.");
			}
			
			hexChunksTotal = hexChunksTotal + hexChunk;
		}
		setHexString(hexChunksTotal);
	}
	
	
}
