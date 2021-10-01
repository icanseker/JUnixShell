package unix.shell.cmd.core.date.format;

public enum DateFormPadding {

	/**
	 * (hyphen) Do not pad the field; useful if the output is intended for human
	 * consumption. This is a GNU extension.
	 */
	NO_PADDING('-'),

	/**
	 * (underscore) Pad with spaces; useful if you need a fixed number of characters
	 * in the output, but zeros are too distracting. This is a GNU extension.
	 */
	SPACE('_'),

	/**
	 * (zero) Pad with zeros even if the conversion specifier would normally pad
	 * with spaces.
	 */
	ZERO('0'),

	/**
	 * Pad with zeros, like ‘0’. In addition, precede any year number with ‘+’ if it
	 * exceeds 9999 or if its field width exceeds 4; similarly, precede any century
	 * number with ‘+’ if it exceeds 99 or if its field width exceeds 2. This
	 * supports ISO 8601 formats for dates far in the future; for example, the
	 * command date --date=12019-02-25 +%+13F outputs the string ‘+012019-02-25’.
	 */
	ZERO_WITH_PLUS('+'),

	;

	private final char symbol;

	private DateFormPadding(char symbol) {
		this.symbol = symbol;
	}

	public char symbol() {
		return this.symbol;
	}
}
