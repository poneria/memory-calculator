package calc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DirectMappedCacheTest {

	@BeforeEach
	void setUp() throws Exception {
		// Using book:
		// Essentials of Computer Organization and Architecture, 4th edition
		// Linda Null & Julia Nobur
	}
	
	@Test
	void test_ex6d2() {
		// p352, Example 6.2
		
		DirectMappedCache dmc = new DirectMappedCache("HEX", "0000", 4, 3);
		
		assertEquals(14, dmc.getBinString().length());
		assertEquals("0000000", dmc.getTagField());
		assertEquals("0000", dmc.getBlockField());
		assertEquals("000", dmc.getOffsetField());
	}

	@Test
	void test_ex6d6() {
		// p360, Example 6.6
		
		DirectMappedCache dmc = new DirectMappedCache("HEX", "326A0", 5, 4);
		
		assertEquals(20, dmc.getBinString().length());
		assertEquals("00110010011", dmc.getTagField());
		assertEquals("01010", dmc.getBlockField());
		assertEquals("0000", dmc.getOffsetField());
	}
	
	@Test
	void test_p2() {
		// p392, Problem #2
		
		DirectMappedCache dmc = new DirectMappedCache("HEX", "000063FA", 10, 5);
		
		assertEquals(32, dmc.getBinString().length());
		assertEquals("00000000000000000", dmc.getTagField());
		assertEquals("1100011111", dmc.getBlockField());
		assertEquals("11010", dmc.getOffsetField());
	}
	
	@Test
	void test_p3() {
		// p392, Problem #3
		
		DirectMappedCache dmc = new DirectMappedCache("HEX", "13A4498A", 9, 6);
		
		assertEquals(32, dmc.getBinString().length());
		assertEquals("00010011101001000", dmc.getTagField());
		assertEquals("100100110", dmc.getBlockField());
		assertEquals("001010", dmc.getOffsetField());
	}

}
