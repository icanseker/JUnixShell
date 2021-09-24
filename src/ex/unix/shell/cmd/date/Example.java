package ex.unix.shell.cmd.date;

import java.time.LocalDate;
import java.time.LocalDateTime;

import unix.shell.cmd.core.date.Date;
import unix.shell.cmd.core.date.TimeZone;
import unix.shell.cmd.core.date.format.DateForm;
import unix.shell.cmd.core.date.format.DateFormPadding;
import unix.shell.cmd.core.date.format.ISO_8601_TIMESPEC;
import unix.shell.cmd.core.date.format.RFC_3339_TIMESPEC;

public class Example {

	public static void main(String[] args) throws Exception {

		Date date = new Date();

		// day
		date.specify("15 Dec 2013");
//		date.specify("15dec2013");
//		date.specify("15-dec-2013");
//		date.specify("24 September 2003");
//		date.specify("Sep 24, 2003");
//		date.specify("2004-02-29");
//		date.specify("72-9-24"); // Assume 19xx for 69 through 99
//		date.specify("45-9-24"); // 20xx for 00 through 68
//		date.specify("9/24/72");
//
//		// The year can also be omitted. In this case, the last specified year is used,
//		// or the current year if none.
//		date.specify("9/24");
//		date.specify("sep24"); // or "sep 24"
//
//		// time
//		date.specify("19:59"); // = 19:59:00
//		date.specify("19:59:34");
//		date.specify("08:52 a.m."); // or am
//		date.specify("08:52pm"); // or p.m.
//
//		// combine both day and time
//		date.specify("2004-02-29 08:52pm"); // or p.m.
//		date.specify("2004-02-29 19:59:34");
//
//		// time zone
//		date.specify("TZ=\"Europe/Paris\"");
//
//		// combine time zone and day and/or time
//		date.specify("TZ=\"Europe/Paris\" 24 September 2003 08:52pm"); // meaning: What date is it if it's September
//																		// 24, 2003, at 8:52 p.m. in Europe/Paris?
//		date.specify("TZ=\"Africa/Addis_Ababa\" 2004-10-31 06:30");
//
//		// define time zone with TimeZone class
//		date.specify("24 September 2003 08:52pm", TimeZone.EUROPE.PARIS);
//
//		// relative items
//		date.specify("first fri");
//		date.specify("next tuesday");
//		date.specify("last tuesday");
//		date.specify("twelfth monday");
//		date.specify("this sunday");
//		date.specify("tomorrow");
//		date.specify("3 days ago");
//		date.specify("30000 days ago");
//		date.specify("-2 month");
//		date.specify("+300 year");
//
//		// To print the date of the day three months and one day hence:
//		date.specify("3 months 1 day");
//
//		date.specify("-2 month -3 days");
//		date.specify("-5 years -2 month +3 days");
//
//		// seconds since the Epoch (Epoch = date -d '@0')
//		date.specify("@0"); // gives Epoch
//		date.specify("@1"); // or @+1 gives Epoch + 1 second
//		date.specify("@-10"); // gives Epoch - 10 second
//		date.specify("@915148800");
//
//		// specify a file includes a 'datestr' each line
//		date.sourceFile("/file/path of datestr each line.ext");
//
//		// reference a file to specify date as file's last modification date
//		date.referenceFile("/file/reference.ext");
//
//		date.specify("24 September 2003 08:52pm", TimeZone.ANTARCTICA.CASEY);
//
//		// format rfc 5322
//		date.format_RFC_5322();
//
//		// format rfc 3339
//		date.format_RFC_3339(RFC_3339_TIMESPEC.SECONDS);
//		date.format_RFC_3339(RFC_3339_TIMESPEC.NANOSECONDS);
//
//		// format iso 8601
//		date.format_ISO_8601(); // same as date.format_ISO_8601(ISO_8601_TIMESPEC.DATE);
//		date.format_ISO_8601(ISO_8601_TIMESPEC.HOURS);
//		date.format_ISO_8601(ISO_8601_TIMESPEC.DATE);
//
//		// lets design out date format
//		date.specify("now");
//
//		/**
//		 * command line: date --date='now' '+%a'
//		 */
//		date.customizePrint(DateForm.DAYNAME_SHORT);
//
//		date.customizePrint(DateForm.DAYNAME + " " + DateForm.DAYOF_MONTH); // Saturday 01
//		//
//
//		/**
//		 * command line: date --date='now' '+%A %-d'
//		 */
//		date.customizePrint( // Saturday 1
//				DateForm.DAYNAME + " " + //
//						new DateForm(DateForm.DAYOF_MONTH).padding(DateFormPadding.NO_PADDING) //
//		);
//
//		/**
//		 * command line: date --date='now' '+%^a %d'
//		 */
//		date.customizePrint(//
//				new DateForm(DateForm.DAYNAME_SHORT).upperCase() + //
//						" " + DateForm.DAYOF_MONTH//
//		); // THU 23
//
//		date.specify("01/05/651");
//
//		/**
//		 * command line:
//		 * 
//		 * date --date='01/05/651' '+%A %-d, %04Y'
//		 */
//		date.customizePrint( // Saturday 1
//				DateForm.DAYNAME + " " + //
//						new DateForm(DateForm.DAYOF_MONTH).padding(DateFormPadding.NO_PADDING) + ", " + //
//						new DateForm(DateForm.YEAR_4DIGIT).padding(DateFormPadding.ZERO, 4)//
//		);
//
//		date.specify("24 Sept 2003 08:52pm", TimeZone.ANTARCTICA.CASEY);
//
//		/**
//		 * command line:
//		 * 
//		 * date --date='TZ="Antarctica/Casey" 24 Sept 2003 08:52pm' '+%R (%I:%M %P)'
//		 */
//		date.customizePrint(
//				DateForm.H24_MIN + " (" + DateForm.H12 + ":" + DateForm.MIN + " " + DateForm.MERIDIEM_LC + ")");
//
//		/**
//		 * command line:
//		 * 
//		 * date --date='TZ="Antarctica/Casey" 24 Sept 2003 08:52pm' '+The time was %T'
//		 */
//		date.customizePrint("The time was " + DateForm.TIME_H24MINSEC);
//
//		// To print the current full month name and the day of the month:
//		date.specify("now");
//
//		/**
//		 * command line:
//		 * 
//		 * date --date='now' '+%B %d'
//		 */
//		date.customizePrint(DateForm.MONTHNAME + " " + DateForm.DAYOF_MONTH);
//
//		// send a date command output as input
//		Date otherDate = new Date();
//		otherDate.specify("15 June 2013 10:12am");
//
//		date.specify(otherDate);
//		date.specify(otherDate + " -5 days");
//
//		/**
//		 * command line:
//		 * 
//		 * date --date="$( date --date='15 June 2013 10:12am' ) -5 days" '+%B %d, %Y'
//		 */
//		date.customizePrint(DateForm.MONTHNAME + " " + DateForm.DAYOF_MONTH + ", " + DateForm.YEAR_4DIGIT);
//
//		// set date to two days ago
//		date = new Date();
//		/**
//		 * Because of this error "date: the options to print and set the time may not be
//		 * used together" all option and arguments related date specification will be
//		 * removed.
//		 * 
//		 * command line:
//		 * 
//		 * date --set='-2 days'
//		 */
//		date.specify("15 June 2013 10:12am");
//		date.set("-2 days");
//
//		// use other date to set
//		Date aDate = new Date();
//		aDate.specify("15 June 2013 10:12am");
//
//		/**
//		 * command line:
//		 * 
//		 * date --set="$( date --date='15 June 2013 10:12am' )+5 days"
//		 */
//		date.set(aDate + "+5 days");
//
//		/**
//		 * use java local DateTime class to specify date
//		 */
//		date = new Date();
//		date.specify(LocalDateTime.now().minusYears(5).minusDays(25).toString());
//		date.set(LocalDate.now().minusMonths(3).toString());

		date.print();
	}
}
