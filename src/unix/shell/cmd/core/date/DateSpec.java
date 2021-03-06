package unix.shell.cmd.core.date;

import unix.shell.cmd.opt.OptionGroup;

/**
 * Specify datestr: string, source-file, file modification date etc.
 *
 */
public enum DateSpec implements OptionGroup<DateOption> {

	/**
	 * Display the date and time specified in datestr instead of the current date
	 * and time. datestr can be in almost any common format. It can contain month
	 * names, time zones, ?am? and ?pm?, ?yesterday?, etc.
	 * 
	 * <p />
	 * For example, --date="2004-02-27 14:19:13.489392193 +0530" specifies the
	 * instant of time that is 489,392,193 nanoseconds after February 27, 2004 at
	 * 2:19:13 PM in a time zone that is 5 hours and 30 minutes east of UTC.
	 * 
	 * <p />
	 * Note: input currently must be in locale independent format. E.g., the
	 * LC_TIME=C below is needed to print back the correct date in many locales:
	 * 
	 * <p />
	 * <b>date -d "$(LC_TIME=C date)"</b>
	 */
	CUSTOM('d', "date") {
		@Override
		public boolean requireArgument() {
			return true;
		}
	},

	/**
	 * Parse each line in datefile as with -d and display the resulting date and
	 * time. If datefile is ?-?, use standard input. This is useful when you have
	 * many dates to process, because the system overhead of starting up the date
	 * executable many times can be considerable.
	 */
	SOURCE_FILE('f', "file") {
		@Override
		public boolean requireArgument() {
			return true;
		}
	},

	/**
	 * Display the date and time of the last modification of file, instead of the
	 * current date and time.
	 */
	REFERENCE_FILE('r', "reference") {
		@Override
		public boolean requireArgument() {
			return true;
		}
	},

	;

	private final Character symbol;
	private final String paramName;

	private DateSpec(Character symbol, String paramName) {
		this.symbol = symbol;
		this.paramName = paramName;
	}

	private DateSpec(Character symbol) {
		this(symbol, null);
	}

	private DateSpec(String paramName) {
		this(null, paramName);
	}

	@Override
	public Character symbol() {
		return this.symbol;
	}

	@Override
	public String paramName() {
		return this.paramName;
	}
}
