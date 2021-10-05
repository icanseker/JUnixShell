package unix.shell.cmd.core.grep.opt;

import unix.shell.cmd.opt.CommandLineOption;

/**
 * all for GNU-grep
 * 
 * Context lines are non-matching lines that are near a matching line. They are
 * output only if one of the following options are used. Regardless of how these
 * options are set, grep never outputs any given line more than once. If the -o
 * (--only-matching) option is specified, these options have no effect and a
 * warning is given upon their use.
 * 
 * <p/>
 * Here are some points about how grep chooses the separator to print between
 * prefix fields and line content:
 * 
 * <ol>
 * <li>Matching lines normally use ‘:’ as a separator between prefix fields and
 * actual line content.</li>
 * 
 * <li>Context (i.e., non-matching) lines use ‘-’ instead.</li>
 * 
 * <li>When context is not specified, matching lines are simply output one right
 * after another.</li>
 * 
 * <li>When context is specified, lines that are adjacent in the input form a
 * group and are output one right after another, while by default a separator
 * appears between non-adjacent groups.</li>
 * 
 * <li>The default separator is a ‘--’ line; its presence and appearance can be
 * changed with the options above.</li>
 * 
 * <li>Each group may contain several matching lines when they are close enough
 * to each other that two adjacent groups connect and can merge into a single
 * contiguous one.</li>
 * </ol>
 */
public enum ContextLineControl implements CommandLineOption<GrepOption> {

	/**
	 * Print num lines of trailing context after matching lines.
	 */
	TRAILING_CONTEXT('A', "after-context") {
		@Override
		public boolean requireArgument() {
			return true;
		}
	},

	/**
	 * Print num lines of leading context before matching lines.
	 */
	LEADING_CONTEXT('B', "before-context") {
		@Override
		public boolean requireArgument() {
			return true;
		}
	},

	/**
	 * Print num lines of leading and trailing output context.
	 */
	LEADING_AND_TRAILING_CONTEXT('C', "context"),

	/**
	 * When -A, -B or -C (LEADING AND/OR/BOTH TRAILING CONTEXT) are in use, print
	 * string instead of -- between groups of lines.
	 */
	GROUP_SPERATOR("group-separator") {
		@Override
		public boolean requireArgument() {
			return true;
		}
	},

	/**
	 * When -A, -B or -C (LEADING AND/OR/BOTH TRAILING CONTEXT) are in use, do not
	 * print a separator between groups of lines.
	 */
	NO_GROUP_SEPARATOR("no-group-separator"),

	;

	private final Character symbol;
	private final String paramName;

	private ContextLineControl(Character symbol, String paramName) {
		this.symbol = symbol;
		this.paramName = paramName;
	}

	private ContextLineControl(Character symbol) {
		this(symbol, null);
	}

	private ContextLineControl(String paramName) {
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
