package com.marketlogic.test;

import com.marketlogic.test.Encoder;

import junit.framework.TestCase;

/**
 * @author Komal
 *
 */
public class EncoderTest extends TestCase {
	// test encode with negative offset when offset eg :: -1
	public final void testEncodeNegativeOffset() {
		int offset = -1;
		String encodeString = "ADD!@#&ssdsfd2334csdf644267bhhyys6,.,>>a2267";
		String decodedString = Encoder.encode(offset, encodeString);
		String decodedStringExpected = "ZCC!@#&rrcrec2334brce644267aggxxr6,.,>>z2267";
		assertEquals(decodedStringExpected, decodedString);
	}

	// test encode with positive offset when offset eg :: 1
	public final void testEncodePositiveOffset() {

		int offset = 1;
		String encodeString = "ADD!@#&ssdsfd2334csdf644267bhhyys6,.,>>a2267";
		String decodedString = Encoder.encode(offset, encodeString);
		String decodedStringExpected = "BEE!@#&ttetge2334dteg644267ciizzt6,.,>>b2267";
		assertEquals(decodedStringExpected, decodedString);
	}

	// test encode with negative offset when offset eg :: -20
	public final void testEncodeNegativeOffsetHigherThanOne() {
		int offset = -20;
		String encodeString = "ADD!@#&ssdsfd2334csdf644267bhhyys6,.,>>a2267";
		String decodedString = Encoder.encode(offset, encodeString);
		String decodedStringExpected = "GJJ!@#&yyjylj2334iyjl644267hnneey6,.,>>g2267";
		assertEquals(decodedStringExpected, decodedString);
	}

	// test encode with negative offset when offset eg :: 46
	public final void testEncodePositiveOffsetHigherThanOne() {
		int offset = 46;
		String encodeString = "ADD!@#&ssdsfd2334csdf644267bhhyys6,.,>>a2267";
		String decodedString = Encoder.encode(offset, encodeString);
		String decodedStringExpected = "UXX!@#&mmxmzx2334wmxz644267vbbssm6,.,>>u2267";
		assertEquals(decodedStringExpected, decodedString);
	}

}
