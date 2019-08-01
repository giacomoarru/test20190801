package com.ppb.giaco;


import com.ppb.giaco.enums.MatchPeriod;

/**
 * Class representing a Match Time.
 * @author Giacomo
 *
 */
public class MatchTime {

	/**
	 * DURATION OF HALF TIME
	 */
	private static final int HALF_TIME_LENGTH = 45;
	
	private MatchPeriod period;
	private int minutes;
	private int seconds;
	private int milliseconds;
	int additionalMinutes;
	int additionalSeconds;
	
	/**
	 * Creates a new instance if MatchTime given period, minutes, seconds, milliseconds.
	 * @param period
	 * @param minutes
	 * @param seconds
	 * @param milliseconds
	 */
	public MatchTime(MatchPeriod period, int minutes, int seconds, int milliseconds) {
		this.period = period;
		this.minutes = minutes;
		this.seconds = seconds;
		this.milliseconds = milliseconds;
		
		// applies milliseconds rounding
		if (milliseconds > 499)
			this.seconds++;
		
	}

	/**
	 * @param timeUnit
	 * @return formatted time unit with zero padding
	 */
	private String formatTimeUnit(int timeUnit) {
		return String.format("%02d", timeUnit);
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		boolean additional = checkAdditionalTime();
			
		
		stringBuilder.append(formatTimeUnit(minutes) 
				+ ":" + formatTimeUnit(seconds));
		
		if (additional) {
			stringBuilder.append(" +" + formatTimeUnit(additionalMinutes) 
					+ ":" + formatTimeUnit(additionalSeconds));
		}
		
		stringBuilder.append(" - " + period.getLongName());
		
		return stringBuilder.toString();
	}

	private boolean checkAdditionalTime() {
		// H1 - detects additional time
		if (period.equals(MatchPeriod.H1) && 
				(minutes >= HALF_TIME_LENGTH
				&& seconds >= 0 
				&& milliseconds >= 0)
				) {
			calcAdditionalTime(HALF_TIME_LENGTH);
			return true;
			// H2|FT - detects additional time
		} else if (
				(period.equals(MatchPeriod.H2)
						|| period.equals(MatchPeriod.FT)
						)
				&& (minutes >= HALF_TIME_LENGTH*2
				&& seconds >= 0 
				&& milliseconds >= 0)
				) {
			calcAdditionalTime(HALF_TIME_LENGTH*2);
			return true;
		}
		return false;
	}

	/**
	 * Calculates additional time if existent
	 * @param minutesLimit
	 */
	private void calcAdditionalTime(int minutesLimit) {
		additionalMinutes = minutes - minutesLimit;
		minutes = minutesLimit;
		additionalSeconds = seconds;
		seconds = 0;
	}
	

}
