package unix.shell.cmd.core.grep.opt;

import unix.shell.cmd.opt.CommandLineOption;

/**
 * When several prefix fields are to be output, the order is always file name,
 * line number, and byte offset, regardless of the order in which these options
 * were specified.
 */
public enum OutputLinePrefixControl implements CommandLineOption<GrepOption> {

	/**
	 * for GNU-grep
	 * 
	 * Print the 0-based byte offset within the input file before each line of
	 * output. If -o (--only-matching) is specified, print the offset of the
	 * matching part itself.
	 */
	PRINT_BYTE_OFFSET('b', "byte-offset"),

	/**
	 * for GNU-grep
	 * 
	 * Print the file name for each match. This is the default when there is more
	 * than one file to search.
	 */
	PRINT_WITH_FILENAME('H', "with-filename"),

	/**
	 * for GNU-grep
	 * 
	 * Suppress the prefixing of file names on output. This is the default when
	 * there is only one file (or only standard input) to search.
	 */
	SUPPRESS_FILENAME('h', "no-filename"),

	/**
	 * for GNU-grep
	 * 
	 * Display input actually coming from standard input as input coming from file
	 * LABEL. This can be useful for commands that transform a file’s contents
	 * before searching; e.g.:
	 * 
	 * <b>gzip -cd foo.gz | grep --label=foo -H 'some pattern'</b>
	 */
	DISPLAY_LABEL("label") {
		@Override
		public boolean requireArgument() {
			return true;
		}
	},

	/**
	 * Prefix each line of output with the 1-based line number within its input
	 * file. (-n is specified by POSIX.)
	 */
	DISPLAY_LINE_NUMBERS('n', "line-number"),

	/**
	 * for GNU-grep
	 * 
	 * Make sure that the first character of actual line content lies on a tab stop,
	 * so that the alignment of tabs looks normal. This is useful with options that
	 * prefix their output to the actual content: -H, -n, and -b. This may also
	 * prepend spaces to output line numbers and byte offsets so that lines from a
	 * single file all start at the same column.
	 */
	INITIAL_TAB('T', "initial-tab"),

	/**
	 * for GNU-grep
	 * 
	 * Output a zero byte (the ASCII NUL character) instead of the character that
	 * normally follows a file name. For example, ‘grep -lZ’ outputs a zero byte
	 * after each file name instead of the usual newline. This option makes the
	 * output unambiguous, even in the presence of file names containing unusual
	 * characters like newlines. This option can be used with commands like ‘find
	 * -print0’, ‘perl -0’, ‘sort -z’, and ‘xargs -0’ to process arbitrary file
	 * names, even those that contain newline characters.
	 */
	DISPLAY_NULL('Z', "null"),

	;

	private final Character symbol;
	private final String paramName;

	private OutputLinePrefixControl(Character symbol, String paramName) {
		this.symbol = symbol;
		this.paramName = paramName;
	}

	private OutputLinePrefixControl(Character symbol) {
		this(symbol, null);
	}

	private OutputLinePrefixControl(String paramName) {
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
