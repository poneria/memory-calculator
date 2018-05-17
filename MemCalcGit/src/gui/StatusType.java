package gui;

public enum StatusType {
	// ERRORS ------------------------------------------------
	ADDRESS_LARGE, // given hex address binary length . memory address size
	ADDRESS_SMALL, // given hex address binary length < memory address size
	BLANK_INPUT, // input box is empty
	EXPECT_HEX,	// input is not properly hexadecimal
	RADIO_CHOICE_INPUT,	// input radio button not selected
	
	// NON-ERRORS --------------------------------------------
	READY,		// everything is good
	WAITING, 	// default / waiting for input
}
