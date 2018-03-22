
package com.marketlogic.test;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Komal
 */
public class Encoder {

	static Logger LOGGER = LoggerFactory.getLogger(Encoder.class);
	private static StringBuilder stringBuilder; // to store the characters
	/**
	 * 
	 * Small and Capital Alphabet range
	 */
	private static final int LAST_LOWERCASE_ALPHABET = 122;
	private static final int FIRST_LOWERCASE_ALPHABET = 97;
	private static final int LAST_UPPERCASE_ALPHABET = 90;
	private static final int FIRST_UPPERCASE_ALPHABET = 65;
	private static final int NO_OF_ALPHABETS = 26;
	private static int OFFSET_VALUE = 0;

	/**
	 * @param args
	 * 			@args[0] - offset - Please provide the offset value, eg.
	 *            -1,-40,0,1,23 etc. @args[1] - str_to_encode - Please provide the
	 *            string to be encoded eg. qweew#%$%#$gfgASDASJBKA1234455
	 * @return prints the encoded string with the given offset. note does not encode
	 *         special characters or numbers.
	 */
	public static void main(String[] args) {
		Integer offset = null;
		String str_to_encode = null;
		Scanner input = null;
		try {
			if (offset == null && args.length == 0) {
				try {
					System.out.println("Please enter offset value:");
					if (input == null) {
						input = new Scanner(System.in);
					}
					offset = input.nextInt();
				} catch (Exception e) {
					LOGGER.error("Error reading input arguments");
					// System.out.println("Error reading input arguments");
				}
			} else
				offset = Integer.parseInt(args[0]);

			if (str_to_encode == null && args.length == 0) {
				try {
					if (input == null) {
						input = new Scanner(System.in);
					}
					System.out.println("Please enter String to encode:");
					str_to_encode = input.next();
				} catch (Exception exception) {
					LOGGER.error("Error in reading input arguments", exception);
				}
			} else
				str_to_encode = args[1];

			if (input != null)
				input.close();
			encode(offset, str_to_encode);
		} catch (Exception exception) {
			LOGGER.error("Invalind arguments provided to the program.", exception);
			System.exit(0);
		}
	}

	/**
	 * @param offset
	 *            - offset - Please provide the offset value, eg. -1,-40,0,1,23 etc.
	 * @param original_str
	 *            - str_to_encode - Please provide the string to be encoded eg.
	 *            qweew#%$%#$gfgASDASJBKA1234455
	 * @return Returns the encoded string with the given offset. note does not
	 *         encode special characters or numbers.
	 */
	public static String encode(Integer offset, String original_str) {
		try {
			System.out.println();
			LOGGER.info("Offset: {}", offset);
			System.out.println("Offset: " + offset);
			stringBuilder = new StringBuilder();
			OFFSET_VALUE = processOffSetValue(offset);
			for (char next_char : original_str.toCharArray()) {
				int ascii_val = (int) next_char;
				if ((ascii_val >= FIRST_UPPERCASE_ALPHABET && ascii_val <= LAST_UPPERCASE_ALPHABET)
						|| (ascii_val >= FIRST_LOWERCASE_ALPHABET && ascii_val <= LAST_LOWERCASE_ALPHABET)) {
					if (OFFSET_VALUE < 0)
						ascii_val = getPrevCharacter(ascii_val);
					else
						ascii_val = getNextCharacter(ascii_val);
					stringBuilder.append(Character.toString((char) (ascii_val + OFFSET_VALUE)));
				} else {
					stringBuilder.append(next_char);
				}
			}
			LOGGER.info("Entered String -> {}", original_str);
			System.out.println("Entered String -> " + original_str);
			LOGGER.info("Encoded String -> {}", stringBuilder.toString());
			System.out.println("Encoded String -> " + stringBuilder.toString());
		} catch (Exception exception) {
			LOGGER.error("Error while encode operation.",exception);
		}
		return stringBuilder.toString();
	}

	/**
	 * @param offset
	 * @return if the offset provided is larger than 26 i.e. the total no of
	 *         alphabets, then the offset is mod value is taken.
	 */
	private static int processOffSetValue(Integer offset) {
		return offset % NO_OF_ALPHABETS;
	}

	/**
	 * @param ascii_val
	 * @return manipulates the ASCII value between the alphabet range. Works on
	 *         looping to the last alphabet if first alphabet is encountered
	 */
	private static Integer getNextCharacter(int ascii_val) {
		int temp = ascii_val + OFFSET_VALUE;
		if (ascii_val >= FIRST_LOWERCASE_ALPHABET && ascii_val <= LAST_LOWERCASE_ALPHABET) {
			if (temp > LAST_LOWERCASE_ALPHABET) {
				ascii_val = ascii_val - NO_OF_ALPHABETS;
			}
		} else if (ascii_val >= FIRST_UPPERCASE_ALPHABET && ascii_val <= LAST_UPPERCASE_ALPHABET) {
			if (temp > LAST_UPPERCASE_ALPHABET) {
				ascii_val = ascii_val - NO_OF_ALPHABETS;
			}
		}
		return ascii_val;
	}

	/**
	 * @param ascii_val
	 * @return manipulates the ASCII value between the alphabet range. Works on
	 *         looping to the first alphabet if last alphabet is encountered
	 */
	private static Integer getPrevCharacter(int ascii_val) {
		int temp = ascii_val + OFFSET_VALUE;
		if (ascii_val >= FIRST_LOWERCASE_ALPHABET && ascii_val <= LAST_LOWERCASE_ALPHABET) {
			if (temp < FIRST_LOWERCASE_ALPHABET) {
				ascii_val = ascii_val + NO_OF_ALPHABETS;
			}
		} else if (ascii_val >= FIRST_UPPERCASE_ALPHABET && ascii_val <= LAST_UPPERCASE_ALPHABET) {
			if (temp < FIRST_UPPERCASE_ALPHABET) {
				ascii_val = ascii_val + NO_OF_ALPHABETS;
			}
		}
		return ascii_val;
	}
}