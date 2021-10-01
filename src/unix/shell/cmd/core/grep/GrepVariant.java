package unix.shell.cmd.core.grep;

import unix.shell.cmd.opt.SingularOption;

/**
 * grep searches the named input files for lines containing a match to the given
 * patterns. By default, grep prints the matching lines. A file named - stands
 * for standard input. If no input is specified, grep searches the working
 * directory . if given a command-line option specifying recursion; otherwise,
 * grep searches standard input. There are four major variants of grep,
 * controlled by the following options.
 * 
 * <p/>
 * In addition, two variant programs egrep and fgrep are available. egrep is the
 * same as ‘grep -E’. fgrep is the same as ‘grep -F’. Direct invocation as
 * either egrep or fgrep is deprecated, but is provided to allow historical
 * applications that rely on them to run unmodified.
 */
public enum GrepVariant implements SingularOption<GrepOption> {

	/**
	 * Interpret patterns as basic regular expressions (BREs). This is the default.
	 */
	BASIC_REGEX('G', "basic-regexp"),

	/**
	 * Interpret patterns as extended regular expressions (EREs). (-E is specified
	 * by POSIX.)
	 */
	EXTENDED_REGEX('E', "extended-regexp"),

	/**
	 * Interpret patterns as fixed strings, not regular expressions. (-F is
	 * specified by POSIX.)
	 */
	FIXED_STRING('F', "fixed-strings"),

	/**
	 * Interpret patterns as Perl-compatible regular expressions (PCREs). PCRE
	 * support is here to stay, but consider this option experimental when combined
	 * with the -z (--null-data) option, and note that ‘grep -P’ may warn of
	 * unimplemented features.
	 */
	PERL_REGEX('P', "perl-regexp"),

	;

	private final Character symbol;
	private final String paramName;

	private GrepVariant(Character symbol, String paramName) {
		this.symbol = symbol;
		this.paramName = paramName;
	}

	private GrepVariant(Character symbol) {
		this(symbol, null);
	}

	private GrepVariant(String paramName) {
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
