/**
 * @author: Justin Peterson
 * @email: Jmp3833@rit.edu
 * RecurringAppointment.java handles format strings for recurring appointments.
 * This class was generated to break up the complexity of the AppointmentUtility 
 * class.  
 */

package com.std.model.appointment;

import java.text.SimpleDateFormat;

import com.std.model.pattern.DayOfWeekPattern;
import com.std.model.pattern.NDaysPattern;
import com.std.model.pattern.RecurrencePattern;

public class RecurringAppointment {

	// Simple recurring format
	private static final SimpleDateFormat FORMAT = new SimpleDateFormat(
			"EEE, d MMM yyyy 'at' h:mm aa");
	// Not a recurring event
	public static final String NO_RECUR = "does not recur";

	/**
	 * Generates a text description of a recurrign event in the calendar.
	 * 
	 * @return the string representation of the event
	 */
	public static String createPatternText(RecurrencePattern pattern) {
		String text = "";

		if (pattern != null) {

			if (pattern instanceof NDaysPattern) {

				// generate the description string for NDaysPattern
				NDaysPattern ptt = (NDaysPattern) pattern;
				if (ptt.instanceEvery() == 1)
					text = "recurs every day";
				else if (ptt.instanceEvery() > 1)
					text = "recurs every " + ptt.instanceEvery() + " days ";

			} else if (pattern instanceof DayOfWeekPattern) {

				// generate the description string for DayOfWeekPattern
				DayOfWeekPattern ptt = (DayOfWeekPattern) pattern;
				if (ptt.onSunday())
					text += (text.length() == 0 ? "" : ", ") + "Sunday";
				if (ptt.onMonday())
					text += (text.length() == 0 ? "" : ", ") + "Monday";
				if (ptt.onTuesday())
					text += (text.length() == 0 ? "" : ", ") + "Tuesday";
				if (ptt.onWednesday())
					text += (text.length() == 0 ? "" : ", ") + "Wednesday";
				if (ptt.onThursday())
					text += (text.length() == 0 ? "" : ", ") + "Thursday";
				if (ptt.onFriday())
					text += (text.length() == 0 ? "" : ", ") + "Friday";
				if (ptt.onSaturday())
					text += (text.length() == 0 ? "" : ", ") + "Saturday";
				if (text.length() > 0)
					text = "recurs on " + text;
			}

			// append the RecurrencePattern dateRange data
			if (text.length() > 0)
				text += " from "
						+ FORMAT.format(pattern.getRange().getStartDate())
						+ " to "
						+ FORMAT.format(pattern.getRange().getEndDate());
		}

		// Pattern represents a non-recurring event.
		if (text.length() == 0)
			text = NO_RECUR;
		return text;
	}
}
