package com.ppb.giaco;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ppb.giaco.enums.MatchPeriod;



/**
 * Match Time Parser Class which builds a MatchTime class from raw strings
 * @author Giacomo
 *
 */
public class MatchTimeParser {

	/**
	 * Regex pattern for valid input match time
	 * [ + ENUM + ] + HH:MM:mmm
	 */
	private static final String MATCH_TIME_INPUT_FORMAT = "\\[(FT|H1|H2|HT|PM)\\] {1}([0-9]|[1-9][0-9]):([0-5][0-9])\\.([0-9][0-9][0-9])";

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
			minutes = Integer.parseInt(matchTime.substring(matchTime.length()-9, matchTime.length()-7));
		} else {
			minutes = Integer.parseInt(matchTime.substring(matchTime.length()-8, matchTime.length()-7));
		}
		int seconds = Integer.parseInt(matchTime.substring(matchTime.length()-6, matchTime.length()-4));
		int milliseconds = Integer.parseInt(matchTime.substring(matchTime.length() -3, matchTime.length()));
		
		MatchTime matchTimeObj = new MatchTime(matchPeriod, minutes, seconds, milliseconds);
		
		return matchTimeObj.toString();
	}

}
