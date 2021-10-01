package unix.shell.cmd.core.date;

import java.util.EnumSet;
import java.util.HashSet;

import unix.shell.cmd.opt.CommandLineOption;

public enum DateOption implements CommandLineOption<DateOption> {

	/**
	 * Set the date and time to datestr.
	 * 
	 * <p/>
	 * Because of this error <b>"date: the options to print and set the time may not
	 * be used together"</b> all option and arguments related date specification
	 * will be removed.
	 * 
	 */
	SET('s', "set") {
		@Override
		public boolean requiresArgument() {
			return true;
		}

		@Override
		public HashSet<CommandLineOption<DateOption>> optionsEqualed() {
			return new HashSet<CommandLineOption<DateOption>>(EnumSet.allOf(DateSpec.class));
		}
	},

	/**
	 * Annotate the parsed date, display the effective time zone, and warn about
	 * potential misuse.
	 */
	DEBUG("debug"),

	/**
	 * Use Universal Time by operating as if the TZ environment variable were set to
	 * the string ‘UTC0’. UTC stands for Coordinated Universal Time, established in
	 * 1960. Universal Time is often called “Greenwich Mean Time” (GMT) for
	 * historical reasons. Typically, systems ignore leap seconds and thus implement
	 * an approximation to UTC rather than true UTC.
	 */
	USE_UTC('u', "utc"),

	;

	private final Character symbol;
	private final String paramName;

	private DateOption(Character symbol, String paramName) {
		this.symbol = symbol;
		this.paramName = paramName;
	}

	private DateOption(Character symbol) {
		this(symbol, null);
	}

	private DateOption(String paramName) {
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
