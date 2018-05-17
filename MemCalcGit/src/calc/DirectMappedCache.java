package calc;

public class DirectMappedCache extends Address {
	//-----------------------------------------------------------------------------------
	// CLASS SCOPE VARIABLES
	private String tagField = "";
	private String blockField = "";
	private String offsetField = "";
	
	//-----------------------------------------------------------------------------------
	// CONSTRUCTORS
	public DirectMappedCache() {
		super();
	}

	public DirectMappedCache(String baseType, String memAddrString, 
								int blockLength, int offsetLength) {
		super(baseType, memAddrString);
		
		spliceFields(blockLength, offsetLength);
	}

	//-----------------------------------------------------------------------------------
	// GETTERS
	public String getTagField() {
		return tagField;
	}

	public String getBlockField() {
		return blockField;
	}

	public String getOffsetField() {
		return offsetField;
	}
	
	//-----------------------------------------------------------------------------------
	// SETTERS
	public void setTagField(String tagField) {
		this.tagField = tagField;
	}

	public void setBlockField(String blockField) {
		this.blockField = blockField;
	}

	public void setOffsetField(String offsetField) {
		this.offsetField = offsetField;
	}
	
	//-----------------------------------------------------------------------------------
	// PRIVATE METHODS
	private void spliceFields(int blockLength, int offsetLength) {
		String binStr = this.getBinString();
		
		int tagLength = binStr.length() - blockLength - offsetLength;
		
		// slice off tag field
		String tagSlice = binStr.substring(0, (tagLength-1));
		setTagField(tagSlice);
		
		// slice off block field
		String blockSlice = binStr.substring(tagLength, (tagLength + blockLength - 1));
		setBlockField(blockSlice);
		
		// slice off offset field
		String offsetSlice = binStr.substring((tagLength + blockLength));
		setOffsetField(offsetSlice);
	}
}
