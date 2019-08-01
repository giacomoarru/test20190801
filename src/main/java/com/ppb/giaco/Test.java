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
		inputTimes.add("[PM] 0:00.000 Giaco");
		inputTimes.add("[PM] 0:00.000");
		inputTimes.add("[H1] 0:15.025");
		inputTimes.add("[H1] 3:07.513");
		inputTimes.add("[H1] 45:00.001");
		inputTimes.add("[H1] 46:15.752");
		inputTimes.add("[HT] 45:00.000");
		inputTimes.add("[H2] 45:00.500");
		inputTimes.add("[H2] 90:00.908");
		inputTimes.add("[FT] 90:00.000");
		inputTimes.add("90:00");
		inputTimes.add("[H3] 90:00.000");
		inputTimes.add("[PM] -10:00.000");
		inputTimes.add("FOO");

		MatchTimeParser matchTimeParser = new MatchTimeParser();
		
		for (String input : inputTimes) {
			String output = matchTimeParser.toPeriodString(input);
			log.info("\"{}\" -> \"{}\"", input, output);
		}

	}

}
