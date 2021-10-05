package unix.shell.cmd.core.grep.opt;

import unix.shell.cmd.opt.CommandLineOption;

public enum OutputControl implements CommandLineOption<GrepOption> {

	/**
	 * Suppress normal output; instead print a count of matching lines for each
	 * input file. With the -v (--invert-match) option, count non-matching lines.
	 * (-c is specified by POSIX.)
	 */
	COUNT('c', "count") {
		@Override
		public String identifier() { // custom identifier for option to exclude FILES_WITHOUT_MATCHES and SILENT_MODE
			return "out-control";
		}
	},

	/**
	 * for GNU-grep
	 * 
	 * Surround the matched (non-empty) strings, matching lines, context lines, file
	 * names, line numbers, byte offsets, and separators (for fields and groups of
	 * context lines) with escape sequences to display them in color on the
	 * terminal.
	 * 
	 * <p/>
	 * The colors are defined by the environment variable GREP_COLORS and default to
	 * ‘ms=01;31:mc=01;31:sl=:cx=:fn=35:ln=32:bn=32:se=36’ for bold red matched
	 * text, magenta file names, green line numbers, green byte offsets, cyan
	 * separators, and default terminal colors otherwise. The deprecated environment
	 * variable GREP_COLOR is still supported, but its setting does not have
	 * priority; it defaults to ‘01;31’ (bold red) which only covers the color for
	 * matched text. WHEN is ‘never’, ‘always’, or ‘auto’.
	 */
	COLOR("color") {
		@Override
		public boolean takeOptionalArgument() {
			return true;
		}
	},

	/**
	 * for GNU-grep
	 * 
	 * Suppress normal output; instead print the name of each input file from which
	 * no output would normally have been printed.
	 */
	FILES_WITHOUT_MATCH('L', "files-without-match"),

	/**
	 * Suppress normal output; instead print the name of each input file from which
	 * output would normally have been printed. Scanning each input file stops upon
	 * first match. (-l is specified by POSIX.)
	 */
	FILES_WITH_MATCHES('l', "files-with-matches") {
		@Override
		public String identifier() { // custom identifier for option to exclude COUNT and SILENT_MODE
			return "out-control";
		}
	},

	/**
	 * for GNU-grep
	 * 
	 * Stop after the first num selected lines. If the input is standard input from
	 * a regular file, and num selected lines are output, grep ensures that the
	 * standard input is positioned just after the last selected line before
	 * exiting, regardless of the presence of trailing context lines. This enables a
	 * calling process to resume a search.
	 * 
	 * <p/>
	 * When grep stops after num selected lines, it outputs any trailing context
	 * lines. When the -c or --count option is also used, grep does not output a
	 * count greater than num. When the -v or --invert-match option is also used,
	 * grep stops after outputting num non-matching lines.
	 */
	LIMIT_LINE_SELECTION('m', "max-count") {
		@Override
		public boolean requireArgument() {
			return true;
		}
	},

	/**
	 * for GNU-grep
	 * 
	 * Print only the matched (non-empty) parts of matching lines, with each such
	 * part on a separate output line. Output lines use the same delimiters as
	 * input, and delimiters are null bytes if -z (--null-data) is also used
	 */
	PRINT_ONLY_MATCHED('o', "only-matching"),

	/**
	 * Quiet; do not write anything to standard output. Exit immediately with zero
	 * status if any match is found, even if an error was detected. Also see the -s
	 * or --no-messages option. (-q is specified by POSIX.)
	 */
	SILENT_MODE('q', "silent") {
		@Override
		public String identifier() { // custom identifier for option to exclude COUNT and FILES_WITHOUT_MATCHES
			return "out-control";
		}
	},

	/**
	 * Suppress error messages about nonexistent or unreadable files. Portability
	 * note: unlike GNU grep, 7th Edition Unix grep did not conform to POSIX,
	 * because it lacked -q and its -s option behaved like GNU grep’s -q option.1
	 * USG-style grep also lacked -q but its -s option behaved like GNU grep’s.
	 * Portable shell scripts should avoid both -q and -s and should redirect
	 * standard and error output to /dev/null instead. (-s is specified by POSIX.)
	 */
	SUPPRESS_ERROR('s', "no-messages"),

	;

	private final Character symbol;
	private final String paramName;

	private OutputControl(Character symbol, String paramName) {
		this.symbol = symbol;
		this.paramName = paramName;
	}

	private OutputControl(Character symbol) {
		this(symbol, null);
	}

	private OutputControl(String paramName) {
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
