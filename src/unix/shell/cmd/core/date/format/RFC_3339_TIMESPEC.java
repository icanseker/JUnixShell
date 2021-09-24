package unix.shell.cmd.core.date.format;

public enum RFC_3339_TIMESPEC {

	/**
	 * Print just the full-date, e.g., ‘2005-09-14’. This is equivalent to the
	 * format ‘%Y-%m-%d’.
	 */
	DATE,

	/**
	 * Print the full-date and full-time separated by a space, e.g., ‘2005-09-14
	 * 00:56:06+05:30’. The output ends with a numeric time-offset; here the
	 * ‘+05:30’ means that local time is five hours and thirty minutes east of UTC.
	 * This is equivalent to the format ‘%Y-%m-%d %H:%M:%S%:z’.
	 */
	SECONDS,

	/**
	 * Like ‘seconds’, but also print nanoseconds, e.g., ‘2005-09-14
	 * 00:56:06.998458565+05:30’. This is equivalent to the format ‘%Y-%m-%d
	 * %H:%M:%S.%N%:z’.
	 */
	NANOSECONDS("ns"),

	;

	private final String identifier;

	private RFC_3339_TIMESPEC() {
		this.identifier = this.name().toLowerCase();
	}

	private RFC_3339_TIMESPEC(String identifier) {
		this.identifier = identifier;
	}

	public String identifier() {
		return this.identifier;
	}
}
