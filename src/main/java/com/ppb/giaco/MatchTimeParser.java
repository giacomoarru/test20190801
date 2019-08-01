package com.ppb.giaco;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ppb.giaco.enums.MatchPeriod;



/**
 * Match Time Parser Class which builds a MatchTime class from raw strings.
 * Input validation is made by Regex pattern matching.
 * Valid input: [SHORT_MATCH_TIME] MM:SS.mmm
 * 
 * When a given period goes into additional time (i.e. > 45:00.000 for first half, > 90.00.000 for the second
 * half), the added minutes and seconds are represented separately in the format
 * normalTimeMinutes:normalTimeSeconds +additionalMinutes:additionalSeconds - period
 * Any input which does not meet the required input format should result in an output of INVALID
 * 
 * @author Giacomo
 *
 */
public class MatchTimeParser {

	/**
	 * Regex pattern for valid input match time
	 * [ + ENUM + ] + HH:MM:mmm
	 */
	private static final String MATCH_TIME_INPUT_FORMAT = 
			"\\[(FT|H1|H2|HT|PM)\\] {1}([0-9]|[1-9][0-9]):([0-5][0-9])\\.([0-9][0-9][0-9])";

	private Pattern pattern;
	
	/**
	 * Customized exception message in case input string is null
	 */
	private static final String INPUT_IS_NULL_EXCEPTION_MESSAGE = "Input string cannot be null";

	/**
	 * Represents the INVALID word
	 */
	private static final String INVALID_INPUT = "INVALID";

	public MatchTimeParser() {
		pattern = Pattern.compile(MATCH_TIME_INPUT_FORMAT);
	}

	/**
	 * 
	 * @param matchTime
	 * @param period
	 * @return humanized string
	 */
	public String toPeriodString(String matchTime) {
		if (matchTime == null)
			throw new UnsupportedOperationException(INPUT_IS_NULL_EXCEPTION_MESSAGE);

		Matcher matcher = pattern.matcher(matchTime);

		if (!matcher.matches())
			return INVALID_INPUT;

		MatchPeriod matchPeriod = MatchPeriod.valueOf(matchTime.substring(1,3));
		
		int minutes = 0;
		if (matchTime.length() == 14) {
			minutes = parseIntFromSubstring(matchTime, 9, 7);
		} else {
			minutes = parseIntFromSubstring(matchTime, 8, 7);
		}
		int seconds = parseIntFromSubstring(matchTime, 6, 4);
		int milliseconds = parseIntFromSubstring(matchTime, 3, 0);
		
		MatchTime matchTimeObj = new MatchTime(matchPeriod, minutes, seconds, milliseconds);
		
		return matchTimeObj.toString();
	}

	/**
	 * 
	 * @param inputString
	 * @param rightOffsetStart
	 * @param rightOffsetEnd
	 * @return substring with right offset
	 */
	private int parseIntFromSubstring(String inputString, int rightOffsetStart, int rightOffsetEnd) {
		return Integer.parseInt(
				inputString.substring(
						inputString.length()-rightOffsetStart, 
						inputString.length()-rightOffsetEnd)
				);
	}
}
