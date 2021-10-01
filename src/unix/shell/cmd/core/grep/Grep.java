package unix.shell.cmd.core.grep;

import unix.shell.cmd.VariformCommand;

/**
 * Given one or more patterns, grep (globally search for a regular expression
 * and print matching lines) searches input files for matches to the patterns.
 * When it finds a match in a line, it copies the line to standard output (by
 * default), or produces whatever other sort of output you have requested with
 * options.
 *
 * <p/>
 * Though grep expects to do the matching on text, it has no limits on input
 * line length other than available memory, and it can match arbitrary
 * characters within a line. If the final byte of an input file is not a
 * newline, grep silently supplies one. Since newline is also a separator for
 * the list of patterns, there is no way to match newline characters in a text.
 * 
 * <p/>
 * The general synopsis of the grep command line is <b>grep [option...]
 * [patterns] [file...]</b>
 * 
 * There can be zero or more option arguments, and zero or more file arguments.
 * The patterns argument contains one or more patterns separated by newlines,
 * and is omitted when patterns are given via the ‘-e patterns’ or ‘-f file’
 * options. Typically patterns should be quoted when grep is used in a shell
 * command.
 * 
 */
public class Grep extends VariformCommand<GrepVariant, GrepOption> {

	public Grep(GrepVariant variant) throws Exception { // variant selection
		super(variant, "grep");
		addOption(GrepOption.BINARY);
	}

	public Grep() { // default form
		super("grep");
	}

	/**
	 * There can be zero or more option arguments, and zero or more file arguments.
	 */
	@Override
	public boolean acceptMultiArgument() {
		return true;
	}
}
