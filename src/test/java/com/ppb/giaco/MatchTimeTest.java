package com.ppb.giaco;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class MatchTimeTest {

	MatchTimeParser matchTimeParser;
	
	@BeforeEach
	void init() {
		matchTimeParser = new MatchTimeParser();
	}
	
	
	@DisplayName("MatchTime Basic: Testing 0, 0, 0")
	@Test
	void testMatchTimeBasic() {
		String input = "[PM] 0:00.000";
		String output = matchTimeParser.toPeriodString(input);
		assertEquals(output, "00:00 - PRE_MATCH");
	}
	
	@DisplayName("Milliseconds rounding test")
	@Nested
	class MillisecTest
	{
		@DisplayName("Milliseconds rounding down - Testing 0, 15, 25")
		@Test
		void testMillisecRoundDown() {
			String input = "[H1] 0:15.025";
			String output = matchTimeParser.toPeriodString(input);
			assertEquals(output, "00:15 - FIRST_HALF");
		}	
		
		@DisplayName("Milliseconds rounding up - Testing 0, 15, 500")
		@Test
		void testMillisecRoundUp() {
			String input = "[H1] 0:15.500";
			String output = matchTimeParser.toPeriodString(input);
			assertEquals(output, "00:16 - FIRST_HALF");
		}	
	}
	
	@DisplayName("Additional time test")
	@Nested
	class AdditionalTime
	{
		@DisplayName("Small additional time - millisec round down - H1 - Testing 45, 0, 1")
		@Test
		void testAdditionalSmallH1MillisecRoundDown() {
			String input = "[H1] 45:00.001";
			String output = matchTimeParser.toPeriodString(input);
			assertEquals(output, "45:00 +00:00 - FIRST_HALF");
		}	
		
		@DisplayName("Small additional time - millisec round up - H1 - Testing 45, 0, 500")
		@Test
		void testAdditionalSmallH1MillisecRoundUp() {
			String input = "[H1] 45:00.500";
			String output = matchTimeParser.toPeriodString(input);
			assertEquals(output, "45:00 +00:01 - FIRST_HALF");
		}	
		
		@DisplayName("Large additional time - millisec round down - H1 - Testing 49, 15, 1")
		@Test
		void testAdditionalLargeH1MillisecRoundDown() {
			String input = "[H1] 49:15.001";
			String output = matchTimeParser.toPeriodString(input);
			assertEquals(output, "45:00 +04:15 - FIRST_HALF");
		}	
		
		@DisplayName("Large additional time - millisec round up - H1 - Testing 49, 15, 999")
		@Test
		void testAdditionalLargeH1MillisecRoundUp() {
			String input = "[H1] 49:15.999";
			String output = matchTimeParser.toPeriodString(input);
			assertEquals(output, "45:00 +04:16 - FIRST_HALF");
		}	
	}

}
