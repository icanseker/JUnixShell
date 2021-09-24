package unix.shell.cmd.core.date.format;

public class DateForm {

	private static final char prefix = '%';

	/* Date */

	/**
	 * date; same as %m/%d/%y (MONTH_NUM/DAYOF_MONTH/YEAR_2DIGIT)
	 */
	public static final String DATE = prefix + "D";

	/**
	 * locale's date representation (e.g., 12/31/99)
	 */
	public static final String DATE_LOCALE = prefix + "x";

	/**
	 * locale's date and time (e.g., Thu Mar 3 23:05:25 2005)
	 */
	public static final String DATE_TIME = prefix + "c";

	/**
	 * full date; like %+4Y-%m-%d
	 */
	public static final String FULLDATE = prefix + "F";

	/* Year */

	/**
	 * century; like %Y, except omit last two digits (e.g., 20)
	 */
	public static final String CENTURY = prefix + "C";

	/**
	 * quarter of year (1..4)
	 */
	public static final String QUARTEROF_YEAR = prefix + "q";

	/**
	 * last two digits of year (00..99)
	 */
	public static final String YEAR_2DIGIT = prefix + "y";

	/**
	 * year in 4-digit format (2015)
	 */
	public static final String YEAR_4DIGIT = prefix + "Y";

	/**
	 * year of ISO week number (see %V); normally useful only with %V
	 */
	public static final String YEAROF_ISO_WEEKNUM = prefix + "G";

	/**
	 * last two digits of year of ISO week number (see %G)
	 */
	public static final String YEAROF_ISO_WEEKNUM_2DIGIT = prefix + "g";

	/* Month */

	/**
	 * locale's abbreviated month name (e.g., Jan)
	 */
	public static final String MONTHNAME_SHORT = prefix + "b";

	/**
	 * locale's full month name (e.g., January)
	 */
	public static final String MONTHNAME = prefix + "B";

	/**
	 * month (01..12)
	 */
	public static final String MONTH_NUM = prefix + "m";

	/* Week */

	/**
	 * week number of year, with Sunday as first day of week (00..53)
	 */
	public static final String WEEKNUM_1_SUN = prefix + "U";

	/**
	 * ISO week number, with Monday as first day of week (01..53)
	 */
	public static final String ISO_WEEKNUM = prefix + "V";

	/**
	 * week number of year, with Monday as first day of week (00..53)
	 */
	public static final String WEEKNUM_1_MON = prefix + "W";

	/* Day */

	/**
	 * locale's abbreviated weekday name (e.g., Sun)
	 */
	public static final String DAYNAME_SHORT = prefix + "a";

	/**
	 * locale's full weekday name (e.g., Sunday)
	 */
	public static final String DAYNAME = prefix + "A";

	/**
	 * day of month (e.g., 01)
	 */
	public static final String DAYOF_MONTH = prefix + "d";

	/**
	 * day of week (1..7); 1 is Monday
	 */
	public static final String DAYOF_WEEK_1_MON = prefix + "u";

	/**
	 * day of week (0..6); 0 is Sunday
	 */
	public static final String DAYOF_WEEK_0_SUN = prefix + "w";

	/**
	 * day of month, space padded; same as %_d
	 */
	public static final String DAYOF_MONTH_SPACED = prefix + "e";

	/**
	 * day of year (001..366)
	 */
	public static final String DAYOF_YEAR = prefix + "j";

	/* Time */

	/**
	 * locale's equivalent of either AM or PM; blank if not known
	 */
	public static final String MERIDIEM = prefix + "p";

	/**
	 * like %p, but lower case
	 */
	public static final String MERIDIEM_LC = prefix + "P";

	/**
	 * locale's 12-hour clock time (e.g., 11:11:04 PM)
	 */
	public static final String H12_LOCAL = prefix + "r";

	/**
	 * 24-hour hour and minute; same as %H:%M
	 */
	public static final String H24_MIN = prefix + "R";

	/**
	 * time; same as %H:%M:%S
	 */
	public static final String TIME_H24MINSEC = prefix + "T";

	/**
	 * locale's time representation (e.g., 23:13:48)
	 */
	public static final String TIME_LOCALE = prefix + "X";

	/* Hour */

	/**
	 * hour (00..23)
	 */
	public static final String H24 = prefix + "H";

	/**
	 * hour (01..12)
	 */
	public static final String H12 = prefix + "I";

	/**
	 * hour, space padded ( 0..23); same as %_H
	 */
	public static final String H24_SPACED = prefix + "k";

	/**
	 * hour, space padded ( 1..12); same as %_I
	 */
	public static final String H12_SPACED = prefix + "l";

	/* Minute - Ssecond */

	/**
	 * minute (00..59)
	 */
	public static final String MIN = prefix + "M";

	/**
	 * nanoseconds (000000000..999999999)
	 */
	public static final String NS = prefix + "N";

	/**
	 * second (00..60)
	 */
	public static final String SEC = prefix + "S";

	/**
	 * seconds since 1970-01-01 00:00:00 UTC
	 */
	public static final String SEC_SINCE_EPOCH = prefix + "s";

	/* Time zone */
	/**
	 * +hhmm numeric time zone (e.g., -0400)
	 */
	public static final String TZ_NUMERIC = prefix + "z";

	/**
	 * +hh:mm numeric time zone (e.g., -04:00)
	 */
	public static final String TZ_NUMERIC_COL = prefix + ":z";

	/**
	 * +hh:mm:ss numeric time zone (e.g., -04:00:00)
	 */
	public static final String TZ_NUMERIC_COL_WITH_SECOND = prefix + "::z";

	/**
	 * numeric time zone with : to necessary precision (e.g., -04, +05:30)
	 */
	public static final String TZ_NUMERIC_PRECISION = prefix + ":::z";

	/**
	 * alphabetic time zone abbreviation (e.g., EDT)
	 */
	public static final String TZ_ALPHA = prefix + "Z";

	/* Literal */

	/**
	 * a newline
	 */
	public static final String NEW_LINE = prefix + "n";

	/**
	 * a tab
	 */
	public static final String TAB = prefix + "t";

	/**
	 * a literal %
	 */
	public static final String PERCENT_SIGN = prefix + "%";

	private CharSequence spec;

	private DateFormPadding padding;
	private int padCount;

	private boolean upperCase;
	private boolean oppositeCase;

	public DateForm(CharSequence spec) {

		if (spec.length() > 1 && spec.charAt(0) == prefix)
			spec = spec.subSequence(1, spec.length());

		this.spec = spec;
	}

	public DateForm padding(DateFormPadding padding, int count) {
		this.padding = padding;
		this.padCount = count;
		return this;
	}

	public DateForm padding(DateFormPadding padding) {
		return padding(padding, 1);
	}

	/**
	 * use upper case if possible
	 */
	public DateForm upperCase() {
		this.upperCase = true;
		this.oppositeCase = false;
		return this;
	}

	/**
	 * use opposite case if possible
	 */
	public DateForm oppositeCase() {
		this.upperCase = false;
		this.oppositeCase = true;
		return this;
	}

	@Override
	public String toString() {

		StringBuilder correspond = new StringBuilder().append(prefix);

		if (padding != null && padCount > 0) {

			correspond.append(padding.symbol());
			if (padCount > 1)
				correspond.append(padCount);
		}

		if (this.upperCase)
			correspond.append('^');

		if (this.oppositeCase)
			correspond.append('#');

		correspond.append(this.spec);

		return correspond.toString();
	}
}
