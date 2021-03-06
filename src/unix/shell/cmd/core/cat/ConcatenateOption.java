package unix.shell.cmd.core.cat;

import java.util.Arrays;
import java.util.HashSet;

import unix.shell.cmd.opt.CommandLineOption;

public enum ConcatenateOption implements CommandLineOption<ConcatenateOption> {

	/**
	 * equivalent to -vET (--show-nonprinting --show-ends --show-tabs)
	 */
	SHOW_ALL_CHARACTERS('A', "show-all") {
		@Override
		public HashSet<CommandLineOption<ConcatenateOption>> optionsEqualed() {
			return new HashSet<CommandLineOption<ConcatenateOption>>(Arrays.asList( //
					SHOW_NONPRINTING, //
					SHOW_ENDS, //
					SHOW_TABS, //
					SHOW_NONPRINTING_AND_ENDS, //
					SHOW_NONPRINTING_AND_TABS //
			));
		}
	},

	/**
	 * number nonempty output lines, overrides -n (--number NUMBER_ALL_LINES)
	 */
	NUMBER_NONBLANK('b', "number-nonblank") {

		@Override
		public HashSet<CommandLineOption<ConcatenateOption>> optionsOverridden() {
			return new HashSet<CommandLineOption<ConcatenateOption>>(Arrays.asList(NUMBER));
		}
	},

	/**
	 * equivalent to -vE (--show-nonprinting --show-ends)
	 */
	SHOW_NONPRINTING_AND_ENDS('e') {
		@Override
		public HashSet<CommandLineOption<ConcatenateOption>> optionsEqualed() {
			return new HashSet<CommandLineOption<ConcatenateOption>>(Arrays.asList(SHOW_NONPRINTING, SHOW_ENDS));
		}
	},

	/**
	 * display $ at end of each line
	 */
	SHOW_ENDS('E', "show-ends"),

	/**
	 * number all output lines
	 */
	NUMBER('n', "number"),

	/**
	 * suppress repeated empty output lines
	 */
	SQUEZEE_BLANK('s', "squeeze-blank"),

	/**
	 * equivalent to -vT (--show-nonprinting --show-tabs)
	 */
	SHOW_NONPRINTING_AND_TABS('t') {
		@Override
		public HashSet<CommandLineOption<ConcatenateOption>> optionsEqualed() {
			return new HashSet<CommandLineOption<ConcatenateOption>>(Arrays.asList(SHOW_NONPRINTING, SHOW_TABS));
		}
	},

	/**
	 * display TAB characters as ^I
	 */
	SHOW_TABS('T', "show-tabs"),

	/**
	 * use ^ and M- notation, except for LFD and TAB
	 */
	SHOW_NONPRINTING('v', "show-nonprinting"), //
	;

	private final Character symbol;
	private final String paramName;

	private ConcatenateOption(Character symbol, String paramName) {
		this.symbol = symbol;
		this.paramName = paramName;
	}

	private ConcatenateOption(Character symbol) {
		this(symbol, null);
	}

	private ConcatenateOption(String paramName) {
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