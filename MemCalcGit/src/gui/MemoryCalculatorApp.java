package gui;

/**
 * Memory Calculation Tool
 * 		Interactive Java program for looking at 
 * 		how a direct-mapped cache memory address
 * 		is formed given specific inputs from textbook 
 * 		problems. 
 * 
 * @author Megan O'Neill, mro241@email.vccs.edu
 * When: 27 March 2018 - 8 May 2018
 * Related college classes at NRCC (Mall site):
 * 		CSC-205 Computer Organization
 * 		CSC-202 Computer Science II
 * 
 * About: I created this project as an extra "see if I could" 
 * alternative to using MS Excel as a demonstration tool. I 
 * intended to construct other address-mapping formats, 
 * but with class-graded projects happening, I only finished 
 * fully implementing direct-mapped cache.
 */

public class MemoryCalculatorApp {
	//------------------------------------------------------------------------------
	// MAIN
	public static void main(String[] args) {
		MainMemoryCalc memcalc = new MainMemoryCalc();
	}
}
