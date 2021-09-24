package unix.shell.cmd.core.date.format;

public enum ISO_8601_TIMESPEC {

	/**
	 * Print just the date. This is the default if timespec is omitted.
	 */
	DATE,

	/**
	 * Append the hour of the day to the date.
	 */
	HOURS,

	/**
	 * Append the hours and minutes.
	 */
	MINUTES,

	/**
	 * Append the hours, minutes and seconds.
	 */
	SECONDS,

	/**
	 * Append the hours, minutes, seconds and nanoseconds.
	 */
	NANOSECONDS("ns"),

	;

	private final String identifier;

	private ISO_8601_TIMESPEC() {
		this.identifier = this.name().toLowerCase();
	}

	private ISO_8601_TIMESPEC(String identifier) {
		this.identifier = identifier;
	}

	public String identifier() {
		return this.identifier;
	}
}
