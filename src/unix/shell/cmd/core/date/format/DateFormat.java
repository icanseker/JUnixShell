package unix.shell.cmd.core.date.format;

import unix.shell.cmd.arg.mod.ArgumentInterface;
import unix.shell.cmd.core.date.DateOption;
import unix.shell.cmd.opt.UnixCommandOption;
import unix.shell.cmd.opt.mod.SingularOption;

public enum DateFormat implements UnixCommandOption<DateOption>, SingularOption {

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
	ISO_8601('I', "iso-8601") {
		@Override
		public boolean acceptArgument() {
			return true;
		}
	},

	/**
	 * Display the date and time using the format ‘%a, %d %b %Y %H:%M:%S %z’,
	 * evaluated in the C locale so abbreviations are always in English. For
	 * example:
	 * <p />
	 * Fri, 09 Sep 2005 13:51:39 -0700 This format conforms to Internet RFCs 5322,
	 * 2822 and 822, the current and previous standards for Internet email. For
	 * compatibility with older versions of date, --rfc-2822 and --rfc-822 are
	 * aliases for --rfc-email.
	 */
	RFC_5322('R', "rfc-email"),

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
	RFC_3339("rfc-3339") {
		@Override
		public boolean requiresArgument() {
			return true;
		}
	},

	/**
	 * When user specify its own date format, the other format options must be
	 * excluded. This option was not a real option, but if used other format options
	 * will be automatically excluded.
	 */
	CUSTOM("+") {
		@Override
		public boolean requiresArgument() {
			return true;
		}

		@Override
		public String correspond(ArgumentInterface... arguments) throws Exception {

			String correspond = super.correspond(arguments); // --+='...' get this form
			return correspond.replace("--+='", "'+");
		}
	},

	;

	private final Character symbol;
	private final String varName;

	private DateFormat(Character symbol) {
		this.symbol = symbol;
		this.varName = null;
	}

	private DateFormat(Character symbol, String varName) {
		this.symbol = symbol;
		this.varName = varName;
	}

	private DateFormat(String varName) {
		this.symbol = null;
		this.varName = varName;
	}

	@Override
	public Character symbol() {
		return this.symbol;
	}

	@Override
	public String varName() {
		return this.varName;
	}
}
