package unix.shell.cmd.core.cat;

import java.util.Arrays;
import java.util.HashSet;

import unix.shell.cmd.opt.UnixCommandOption;

public enum ConcatenateOption implements UnixCommandOption<ConcatenateOption> {

	/**
	 * equivalent to -vET (--show-nonprinting --show-ends --show-tabs)
	 */
	SHOW_ALL_CHARACTERS('A', "show-all") {
		@Override
		public HashSet<UnixCommandOption<ConcatenateOption>> optionsEqualed() {
			return new HashSet<UnixCommandOption<ConcatenateOption>>(Arrays.asList( //
					SHOW_NONPRINTING, //
					SHOW_LINE_BREAK, //
					SHOW_TABS, //
					SHOW_NONPRINTING_AND_LINE_BREAK, //
					SHOW_NONPRINTING_AND_TABS //
			));
		}
	},

	/**
	 * number nonempty output lines, overrides -n (--number NUMBER_ALL_LINES)
	 */
	NUMBER_NONEMPTY_LINES('b', "number-nonblank") {

		@Override
		public HashSet<UnixCommandOption<ConcatenateOption>> optionsOverridden() {
			return new HashSet<UnixCommandOption<ConcatenateOption>>(Arrays.asList(NUMBER_ALL_LINES));
		}
	},

	/**
	 * equivalent to -vE (--show-nonprinting --show-ends)
	 */
	SHOW_NONPRINTING_AND_LINE_BREAK('e') {
		@Override
		public HashSet<UnixCommandOption<ConcatenateOption>> optionsEqualed() {
			return new HashSet<UnixCommandOption<ConcatenateOption>>(Arrays.asList(SHOW_NONPRINTING, SHOW_LINE_BREAK));
		}
	},

	/**
	 * display $ at end of each line
	 */
	SHOW_LINE_BREAK('E', "show-ends"),

	/**
	 * number all output lines
	 */
	NUMBER_ALL_LINES('n', "number"),

	/**
	 * suppress repeated empty output lines
	 */
	SQUEZEE_BLANK_LINES('s', "squeeze-blank"),

	/**
	 * equivalent to -vT (--show-nonprinting --show-tabs)
	 */
	SHOW_NONPRINTING_AND_TABS('t') {
		@Override
		public HashSet<UnixCommandOption<ConcatenateOption>> optionsEqualed() {
			return new HashSet<UnixCommandOption<ConcatenateOption>>(Arrays.asList(SHOW_NONPRINTING, SHOW_TABS));
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