package gui;

public class Status {
	//------------------------------------------------------------------------------
	// CLASS SCOPE VARIABLES
	StatusType type;
	String message;
	String moduleName;
	
	//------------------------------------------------------------------------------
	// CONSTRUCTORS
	public Status(String name) {
		moduleName = name;
		type = StatusType.WAITING;
		updateMessage();
	}

	//------------------------------------------------------------------------------
	// GETTERS
	public StatusType getStatusType() {
		return type;
	}
	
	public String getStatusMessage() {
		return message;
	}
	
	public String getModule() {
		return moduleName;
	}
	
	public boolean isReady() {
		if (type == StatusType.READY) { return true; }
		// else: 
		return false;
	}

	//------------------------------------------------------------------------------
	// SETTERS
	public void setStatusType(StatusType statusType) {
		this.type = statusType;
		updateMessage();
	}
	
	private void updateMessage() {
		switch (type) {
			// ERRORS -------------------------------------
			case ADDRESS_LARGE:
				message = "Error: Your given hex address is too big.";
				break;
			case ADDRESS_SMALL:
				message = "Check: Your given hex address is smaller than expected.";
				break;
			case BLANK_INPUT:
				message = "Error: An input field is blank.";
				break;
			case EXPECT_HEX:
				message = "Error: Expecting input to be in hexadecimal (0-F).";
				break;
			case RADIO_CHOICE_INPUT:
				message = "Error: No radio button selected.";
				break;
				
			// NON-ERRORS -------------------------------------
			case READY:
				message = "Ready!";
				break;
			default: // WAITING
				message = "Waiting for inputs.";
				break;
		}
	}
}
