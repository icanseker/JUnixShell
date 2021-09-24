package unix.shell.cmd.core.date;

import unix.shell.cmd.UnixCommand;
import unix.shell.cmd.arg.type.DateIdentifier;
import unix.shell.cmd.arg.type.FilePath;
import unix.shell.cmd.arg.type.Text;
import unix.shell.cmd.arg.type.TextBlock;
import unix.shell.cmd.core.date.format.DateFormat;
import unix.shell.cmd.core.date.format.ISO_8601_TIMESPEC;
import unix.shell.cmd.core.date.format.RFC_3339_TIMESPEC;

/**
 * Display the current time in the given FORMAT, or set the system date.
 */
public class Date extends UnixCommand<DateOption> {

	public Date() {
		super("date");
	}

	/**
	 * Display time described by STRING, not 'now'
	 * 
	 * <p/>
	 * A date is a string, possibly empty, containing many items separated by
	 * whitespace. The whitespace may be omitted when no ambiguity arises.
	 * 
	 * <p/>
	 * The empty string means the beginning of today (i.e., midnight).
	 * 
	 * <p/>
	 * Order of the items is immaterial. A date string may contain many flavors of
	 * items:
	 * 
	 * <ul>
	 * <li>calendar date items</li>
	 * <li>time of day items</li>
	 * <li>time zone items</li>
	 * <li>combined date and time of day items</li>
	 * <li>day of the week items</li>
	 * <li>relative items</li>
	 * <li>pure numbers</li>
	 * </ul>
	 * 
	 * <p/>
	 * A few ordinal numbers may be written out in words in some contexts. This is
	 * most useful for specifying day of the week items or relative items (see
	 * below). Among the most commonly used ordinal numbers, the word ‘last’ stands
	 * for -1, ‘this’ stands for 0, and ‘first’ and ‘next’ both stand for 1. Because
	 * the word ‘second’ stands for the unit of time there is no way to write the
	 * ordinal number 2, but for convenience ‘third’ stands for 3, ‘fourth’ for 4,
	 * ‘fifth’ for 5, ‘sixth’ for 6, ‘seventh’ for 7, ‘eighth’ for 8, ‘ninth’ for 9,
	 * ‘tenth’ for 10, ‘eleventh’ for 11 and ‘twelfth’ for 12.
	 * 
	 * <p/>
	 * When a month is written this way, it is still considered to be written
	 * numerically, instead of being “spelled in full”; this changes the allowed
	 * strings.
	 * 
	 * <p/>
	 * In the current implementation, only English is supported for words and
	 * abbreviations like ‘AM’, ‘DST’, ‘EST’, ‘first’, ‘January’, ‘Sunday’,
	 * ‘tomorrow’, and ‘year’.
	 * 
	 * <p/>
	 * A calendar date item specifies a day of the year. It is specified
	 * differently, depending on whether the month is specified numerically or
	 * literally. All these strings specify the same calendar date:
	 * <ul>
	 * <li>1972-09-24 # ISO 8601.</li>
	 * <li>72-9-24 # Assume 19xx for 69 through 99, 20xx for 00 through 68.</li>
	 * <li>72-09-24 # Leading zeros are ignored.</li>
	 * <li>9/24/72 # Common U.S. writing.</li>
	 * <li>24 September 1972</li>
	 * <li>24 Sept 72 # September has a special abbreviation.</li>
	 * <li>24 Sep 72 # Three-letter abbreviations always allowed.</li>
	 * <li>Sep 24, 1972</li>
	 * <li>24-sep-72</li>
	 * <li>24sep72</li>
	 * </ul>
	 * 
	 * <p/>
	 * For numeric months, the ISO 8601 format ‘year-month-day’ is allowed, where
	 * year is any positive number, month is a number between 01 and 12, and day is
	 * a number between 01 and 31. A leading zero must be present if a number is
	 * less than ten. If year is 68 or smaller, then 2000 is added to it; otherwise,
	 * if year is less than 100, then 1900 is added to it. The construct
	 * ‘month/day/year’, popular in the United States, is accepted. Also
	 * ‘month/day’, omitting the year.
	 * 
	 * <p/>
	 * Literal months may be spelled out in full: ‘January’, ‘February’, ‘March’,
	 * ‘April’, ‘May’, ‘June’, ‘July’, ‘August’, ‘September’, ‘October’, ‘November’
	 * or ‘December’. Literal months may be abbreviated to their first three
	 * letters, possibly followed by an abbreviating dot. It is also permitted to
	 * write ‘Sept’ instead of ‘September’.
	 * 
	 * When months are written literally, the calendar date may be given as any of
	 * the following:
	 * 
	 * <ul>
	 * <li>"day month year"</li>
	 * <li>"day month"</li>
	 * <li>"month day year"</li>
	 * <li>"day-month-year"</li>
	 * </ul>
	 *
	 * <p/>
	 * Or, omitting the year: <b>"month day"</b>
	 * 
	 * <p/>
	 * Days of the week may be spelled out in full: ‘Sunday’, ‘Monday’, ‘Tuesday’,
	 * ‘Wednesday’, ‘Thursday’, ‘Friday’ or ‘Saturday’. Days may be abbreviated to
	 * their first three letters, optionally followed by a period. The special
	 * abbreviations ‘Tues’ for ‘Tuesday’, ‘Wednes’ for ‘Wednesday’ and ‘Thur’ or
	 * ‘Thurs’ for ‘Thursday’ are also allowed.
	 * 
	 * <p/>
	 * A number may precede a day of the week item to move forward supplementary
	 * weeks. It is best used in expression like ‘third monday’. In this context,
	 * ‘last day’ or ‘next day’ is also acceptable; they move one week before or
	 * after the day that day by itself would represent.
	 * 
	 * <p/>
	 * Relative items adjust a date (or the current date if none) forward or
	 * backward. The effects of relative items accumulate. Here are some examples:
	 * 
	 * <ul>
	 * <li>"1 year"</li>
	 * <li>"1 year ago"</li>
	 * <li>"3 years"</li>
	 * <li>"2 days"</li>
	 * </ul>
	 * 
	 * <p/>
	 * The unit of time displacement may be selected by the string ‘year’ or ‘month’
	 * for moving by whole years or months. These are fuzzy units, as years and
	 * months are not all of equal duration. More precise units are ‘fortnight’
	 * which is worth 14 days, ‘week’ worth 7 days, ‘day’ worth 24 hours, ‘hour’
	 * worth 60 minutes, ‘minute’ or ‘min’ worth 60 seconds, and ‘second’ or ‘sec’
	 * worth one second. An ‘s’ suffix on these units is accepted and ignored.
	 * 
	 * <p/>
	 * The unit of time may be preceded by a multiplier, given as an optionally
	 * signed number. Unsigned numbers are taken as positively signed. No number at
	 * all implies 1 for a multiplier. Following a relative item by the string ‘ago’
	 * is equivalent to preceding the unit by a multiplier with value -1.
	 * 
	 * <p/>
	 * The string ‘tomorrow’ is worth one day in the future (equivalent to ‘day’),
	 * the string ‘yesterday’ is worth one day in the past (equivalent to ‘day
	 * ago’).
	 * 
	 * <p/>
	 * If you precede a number with ‘@’, it represents an internal timestamp as a
	 * count of seconds. The number can contain an internal decimal point (either
	 * ‘.’ or ‘,’); any excess precision not supported by the internal
	 * representation is truncated toward minus infinity. Such a number cannot be
	 * combined with any other date item, as it specifies a complete timestamp.
	 * 
	 * <p/>
	 * see other formats: <br/>
	 * <a>https://www.gnu.org/software/coreutils/manual/html_node/Date-input-formats.html</a>
	 * 
	 * @param date
	 * @throws Exception
	 */
	public void specify(String datestr) throws Exception {
		addOption(DateSpec.CUSTOM, new DateIdentifier(datestr));
	}

	public void specify(String datestr, TimeZone tz) throws Exception {

		String date = "TZ=" + "\"" + tz.id() + "\"";
		date += datestr == null || datestr.equals("") ? "" : " " + datestr;

		addOption(DateSpec.CUSTOM, new DateIdentifier(date));
	}

	public void specify(Date date) throws Exception {
		if (date != null)
			addOption(DateSpec.CUSTOM, new DateIdentifier(date.execute()));
	}

	public void specify(Date date, String... datestr) throws Exception {
		if (date != null)
			addOption(DateSpec.CUSTOM, new DateIdentifier(date.execute()));
	}

	/**
	 * like --date; once for each line of DATEFILE
	 * 
	 * @throws Exception
	 */
	public void sourceFile(String filePath) throws Exception {
		addOption(DateSpec.SOURCE_FILE, new FilePath(filePath));
	}

	/**
	 * Display the date and time of the last modification of file, instead of the
	 * current date and time.
	 * 
	 * @throws Exception
	 */
	public void referenceFile(String filePath) throws Exception {
		addOption(DateSpec.REFERENCE_FILE, new FilePath(filePath));
	}

	/**
	 * Output an ISO 8601 compliant date/time string., '%Y-%m-%d'.
	 * 
	 * @throws Exception
	 */
	public void format_ISO_8601() throws Exception {
		addOption(DateFormat.ISO_8601);
	}

	/**
	 * Display the date using an ISO 8601 format, ‘%Y-%m-%d’.
	 * 
	 * <p />
	 * The argument timespec specifies the number of additional terms of the time to
	 * include. It can be one of the following:
	 * 
	 * <ul>
	 * <li>auto</li>
	 * <li>hours</li>
	 * <li>minutes</li>
	 * <li>seconds</li>
	 * <li>ns (nanoseconds)</li>
	 * </ul>
	 * 
	 * <p />
	 * If showing any time terms, then include the time zone using the format ‘%:z’.
	 * This format is always suitable as input for the --date (-d) and --file (-f)
	 * options, regardless of the current locale.
	 */
	public void format_ISO_8601(ISO_8601_TIMESPEC timespec) throws Exception {
		addOption(DateFormat.ISO_8601, new Text(timespec.identifier()));
	}

	/**
	 * Display the date and time using the format ‘%a, %d %b %Y %H:%M:%S %z’,
	 * evaluated in the C locale so abbreviations are always in English. For
	 * example:
	 * 
	 * <p />
	 * Fri, 09 Sep 2005 13:51:39 -0700 This format conforms to Internet RFCs 5322,
	 * 2822 and 822, the current and previous standards for Internet email. For
	 * compatibility with older versions of date, --rfc-2822 and --rfc-822 are
	 * aliases for --rfc-email.
	 */
	public void format_RFC_5322() throws Exception {
		addOption(DateFormat.RFC_5322);
	}

	/**
	 * Display the date using a format specified by Internet RFC 3339. This is like
	 * --iso-8601, except that a space rather than a ‘T’ separates dates from times.
	 * This format is always suitable as input for the --date (-d) and --file (-f)
	 * options, regardless of the current locale.
	 * 
	 * <p />
	 * The argument timespec specifies how much of the time to include. It can be
	 * one of the following:
	 * 
	 * <ul>
	 * <li>date</li>
	 * <li>seconds</li>
	 * <li>ns (nanoseconds)</li>
	 * </ul>
	 */
	public void format_RFC_3339(RFC_3339_TIMESPEC timespec) throws Exception {
		addOption(DateFormat.RFC_3339, new Text(timespec.identifier()));
	}

	/**
	 * Specify output format by DateForm and Padding options
	 */
	public void customizePrint(String dateForm) throws Exception {
		addOption(DateFormat.CUSTOM, new TextBlock(dateForm));
	}

	/**
	 * Set time described by STRING
	 */
	public void set(String datestr) throws Exception {
		addOption(DateOption.SET, new DateIdentifier(datestr));
	}

	/**
	 * Set time described by STRING and Time zone option
	 */
	public void set(String datestr, TimeZone tz) throws Exception {

		String date = "TZ=" + "\"" + tz.id() + "\"";
		date += datestr == null || datestr.equals("") ? "" : " " + datestr;

		addOption(DateOption.SET, new DateIdentifier(date));
	}

	/**
	 * Set time described by other Date
	 */
	public void set(Date date) throws Exception {

		if (date != null)
			addOption(DateOption.SET, new DateIdentifier(date.execute()));
	}

	/**
	 * Set time described by other Date
	 */
	public void set(Date date, String... datestr) throws Exception {

		if (date != null)
			addOption(DateOption.SET, new DateIdentifier(date.execute()));
	}

	/**
	 * Print or set Coordinated Universal Time
	 * 
	 * @throws Exception
	 */
	public void printUtc() throws Exception {
		addOption(DateOption.USE_UTC);
	}
}
