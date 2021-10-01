package unix.shell.cmd.core.grep;

import unix.shell.cmd.opt.CommandLineOption;

public enum MatchingControl implements CommandLineOption<GrepOption> {

	/**
	 * Use patterns as one or more patterns; newlines within patterns separate each
	 * pattern from the next. If this option is used multiple times or is combined
	 * with the -f (--file) option, search for all patterns given. Typically
	 * patterns should be quoted when grep is used in a shell command. (-e is
	 * specified by POSIX.)
	 */
	PATTERNS('e', "regexp") {
		@Override
		public boolean requiresArgument() {
			return true;
		}
	},

	/**
	 * Obtain patterns from file, one per line. If this option is used multiple
	 * times or is combined with the -e (--regexp) option, search for all patterns
	 * given. The empty file contains zero patterns, and therefore matches nothing.
	 * (-f is specified by POSIX.)
	 */
	PATTERNS_FROM_FILE('f', "file") {
		@Override
		public boolean requiresArgument() {
			return true;
		}
	},

	/**
	 * Ignore case distinctions in patterns and input data, so that characters that
	 * differ only in case match each other. Although this is straightforward when
	 * letters differ in case only via lowercase-uppercase pairs, the behavior is
	 * unspecified in other situations.
	 */
	IGNORE_CASE('i', "ignore-case"),

	/**
	 * Invert the sense of matching, to select non-matching lines. (-v is specified
	 * by POSIX.)
	 */
	INVERT_MATCHING('v', "invert-match"),

	/**
	 * Select only those lines containing matches that form whole words. The test is
	 * that the matching substring must either be at the beginning of the line, or
	 * preceded by a non-word constituent character. Similarly, it must be either at
	 * the end of the line or followed by a non-word constituent character. Word
	 * constituent characters are letters, digits, and the underscore. This option
	 * has no effect if -x is also specified.
	 * 
	 * <p/>
	 * Because the -w option can match a substring that does not begin and end with
	 * word constituents, it differs from surrounding a regular expression with ‘\<’
	 * and ‘\>’. For example, although ‘grep -w @’ matches a line containing only
	 * ‘@’, ‘grep '\<@\>'’ cannot match any line because ‘@’ is not a word
	 * constituent.
	 */
	WORD_REGEX('w', "word-regexp"),

	/**
	 * Select only those matches that exactly match the whole line. For regular
	 * expression patterns, this is like parenthesizing each pattern and then
	 * surrounding it with ‘^’ and ‘$’. (-x is specified by POSIX.)
	 */
	LINE_REGEX('x', "line-regexp"),

	;

	private final Character symbol;
	private final String paramName;

	private MatchingControl(Character symbol, String paramName) {
		this.symbol = symbol;
		this.paramName = paramName;
	}

	private MatchingControl(Character symbol) {
		this(symbol, null);
	}

	private MatchingControl(String paramName) {
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
