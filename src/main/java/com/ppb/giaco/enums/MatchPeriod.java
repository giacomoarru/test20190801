/**
 * 
 */
package com.ppb.giaco.enums;

/**
 * <p>An enum representing <em>Periods</em> of a match.
 *
 */
public enum MatchPeriod {

	PM("PRE_MATCH"),
	H1("FIRST_HALF"),
	HT("HALF_TIME"),
	H2("SECOND_HALF"),
	FT("FULL_TIME");
	
	private String longName;
	
	private MatchPeriod(String longName) {
		this.longName = longName;
	}
	
	public String getLongName() {
		return this.longName;
	}
	
}
