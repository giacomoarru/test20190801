/**
 * 
 */
package com.ppb.giaco;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tests the converter.
 *
 */
public class Test {

	private static final Logger log = LoggerFactory.getLogger(Test.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		List<String> inputTimes = new ArrayList<>();
		inputTimes.add("[PM] 0:00.000 Giaco");	// INVALID
		inputTimes.add("[PM] 0:00.000");		// 00:00 - PRE_MATCH
		inputTimes.add("[H1] 0:15.025");		// 00:15 - FIRST_HALF
		inputTimes.add("[H1] 3:07.513");		// 03:08 - FIRST_HALF
		inputTimes.add("[H1] 45:00.001");		// 45:00 +00:00 - FIRST_HALF
		inputTimes.add("[H1] 46:15.752");		// 45:00 +00:00 - FIRST_HALF
		inputTimes.add("[HT] 45:00.000");		// 45:00 +00:00 - FIRST_HALF
		inputTimes.add("[H2] 45:00.500");		// 45:01 - SECOND_HALF
		inputTimes.add("[H2] 90:00.908");		// 90:00 +00:01 - SECOND_HALF
		inputTimes.add("[FT] 90:00.000");		// 90:00 +00:00 - FULL_TIME
		inputTimes.add("90:00");				// INVALID
		inputTimes.add("[H3] 90:00.000");		// INVALID
		inputTimes.add("[PM] -10:00.000");		// INVALID
		inputTimes.add("FOO");					// INVALID

		MatchTimeParser matchTimeParser = new MatchTimeParser();
		
		for (String input : inputTimes) {
			String output = matchTimeParser.toPeriodString(input);
			log.info("\"{}\" -> \"{}\"", input, output);
		}

	}

}
