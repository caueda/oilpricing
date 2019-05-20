package com.project.oilpricing.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.text.ParseException;

import org.junit.Test;

public class UtilTest {

	@Test
	public void testRound() {
		assertThat(12.57, equalTo(Util.round(12.56789)));
	}

	@Test(expected = java.text.ParseException.class)
	public void testGetDateBadFormatFail() throws ParseException {

		Util.getDate("01/12/2019 05:00");

	}
	
	@Test
	public void testGetDate() {
		try {
			Util.getDate("22/12/2019 05:00:00");
		} catch (ParseException e) {
			fail("Should not have thrown any exception");
		}
	}

}
