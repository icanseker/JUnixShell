package unix.shell.cmd.core.grep;

import unix.shell.cmd.UnixCommand;
import unix.shell.cmd.arg.mod.ArgumentAct;
import unix.shell.cmd.arg.type.FilePath;
import unix.shell.cmd.arg.type.TextBlock;
import unix.shell.cmd.core.grep.opt.GrepOption;
import unix.shell.cmd.core.grep.opt.MatchingControl;
import unix.shell.cmd.core.grep.opt.OutputControl;
import unix.shell.cmd.core.grep.opt.OutputLinePrefixControl;
import unix.shell.cmd.mod.VariformCommand;
import unix.shell.cmd.opt.OptionGroup;
import unix.shell.cmd.outline.FieldMap;

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
 * <p/>
 * Care should be taken when using characters in pattern_list that may also be
 * meaningful to the command interpreter. It is safest to enclose the entire
 * pattern_list argument in single-quotes: <b>'...'</b>
 * 
 * <p/>
 * The patterns are specified by the -e option, -f option, or the pattern_list
 * operand. The pattern_list's value shall consist of one or more patterns
 * separated by <newline> characters; the pattern_file's contents shall consist
 * of one or more patterns terminated by a <newline> character.
 * 
 * <p/>
 * Regular expression matching shall be based on text lines. Since a <newline>
 * separates or terminates patterns (see the -e and -f options below), regular
 * expressions cannot contain a <newline>. Similarly, since patterns are matched
 * against individual lines (excluding the terminating <newline> characters) of
 * the input, there is no way for a pattern to match a <newline> found in the
 * input.
 * 
 * <p/>
 * The following operands shall be supported:
 * 
 * <ul>
 * <li><b>pattern_list</b>: Specify one or more patterns to be used during the
 * search for input. This operand shall be treated as if it were specified as -e
 * pattern_list.</li>
 * 
 * <li><b>file</b>: A pathname of a file to be searched for the patterns. If no
 * file operands are specified, the standard input shall be used.</li>
 * </ul>
 * 
 * <p/>
 * The -e pattern_list option has the same effect as the pattern_list operand,
 * but is useful when pattern_list begins with the <hyphen-minus> delimiter. It
 * is also useful when it is more convenient to provide multiple patterns as
 * separate arguments.
 * 
 * <p/>
 * Multiple -e and -f options are accepted and grep uses all of the patterns it
 * is given while matching input text lines. (Note that the order of evaluation
 * is not specified. If an implementation finds a null string as a pattern, it
 * is allowed to use that pattern first, matching every line, and effectively
 * ignore any other patterns.)
 * 
 * <p/>
 * The -q option provides a means of easily determining whether or not a pattern
 * (or string) exists in a group of files. When searching several files, it
 * provides a performance improvement (because it can quit as soon as it finds
 * the first match) and requires less care by the user in choosing the set of
 * files to supply as arguments (because it exits zero if it finds a match even
 * if grep detected an access or read error on earlier file operands).
 * 
 * <p/>
 * When using grep to process pathnames, it is recommended that LC_ALL, or at
 * least LC_CTYPE and LC_COLLATE, are set to POSIX or C in the environment,
 * since pathnames can contain byte sequences that do not form valid characters
 * in some locales, in which case the utility's behavior would be undefined. In
 * the POSIX locale each byte is a valid single-byte character, and therefore
 * this problem is avoided.
 */
public class Grep extends UnixCommand<GrepOption> implements VariformCommand<GrepOption> {

	private GrepVariant form;

	public Grep() throws Exception { // default form
		super("grep");
	}

	/**
	 * synopsis of grep (from man)
	 * 
	 * grep [-E|-F] [-c|-l|-q] [-insvx] -e pattern_list [-e pattern_list]... [-f
	 * pattern_file]... [file...]
	 * 
	 * <br/>
	 * grep [-E|-F] [-c|-l|-q] [-insvx] [-e pattern_list]... -f pattern_file [-f
	 * pattern_file]... [file...]
	 * 
	 * <br/>
	 * grep [-E|-F] [-c|-l|-q] [-insvx] pattern_list [file...]
	 */
	@Override
	protected FieldMap synopsis() {

		/*
		 * general synopsis for grep is
		 * 
		 * grep [OPTIONS] [-e pattern_list]... [-f pattern_file]... [file...]
		 * 
		 * grep [OPTIONS] [-e|-f pattern]... [file...]
		 */

		FieldMap synopsis = new FieldMap();
		synopsis.addArgumentGroup("pattern", ArgumentAct.REQUIRE_MULTIPLE);
		synopsis.addArgumentGroup("file", ArgumentAct.OPTIONAL_MULTIPLE);

		return synopsis;
	}

	/**
	 * grep supports a few variants, for grep(1p) these are -E extended regex
	 * pattern or -F fixed string mode. GNU-grep supports some other variants also.
	 * 
	 * <p/>
	 * This function sends the variant option to the super class UnixCommand, if not
	 * selected, it will be default grep which is supports basic regex pattern.
	 */
	@Override
	public OptionGroup<GrepOption> variform() {
		return this.form;
	}

	/**
	 * Interpret patterns as basic regular expressions (BREs). This is the default.
	 */
	public void BasicRegexModeOn() {
		this.form = null;
	}

	/**
	 * Match using extended regular expressions. Treat each pattern specified as an
	 * ERE, as described in the Base Definitions volume of POSIX.1-2017, Section
	 * 9.4, Extended Regular Expressions. If any entire ERE pattern matches some
	 * part of an input line excluding the terminating <newline>, the line shall be
	 * matched. A null ERE shall match every line.
	 */
	public void extendedRegexModeOn() {
		this.form = GrepVariant.EXTENDED_REGEX;
	}

	/**
	 * Match using fixed strings. Treat each pattern specified as a string instead
	 * of a regular expression. If an input line contains any of the patterns as a
	 * contiguous sequence of bytes, the line shall be matched. A null string shall
	 * match every line.
	 */
	public void fixedStringModeOn() {
		this.form = GrepVariant.FIXED_STRING;
	}

	/**
	 * Write only a count of selected lines to standard output.
	 */
	public void countMatchingLines() throws Exception {
		addOption(OutputControl.COUNT);
	}

	/**
	 * The letter ell.) Write only the names of files containing selected lines to
	 * standard output. Pathnames shall be written once per file searched. If the
	 * standard input is searched, a pathname of "(standardinput)" shall be written,
	 * in the POSIX locale. In other locales, "standardinput" may be replaced by
	 * something more appropriate in those locales.
	 */
	public void matchingFileNames() throws Exception {
		addOption(OutputControl.FILES_WITH_MATCHES);
	}

	/**
	 * Quiet. Nothing shall be written to the standard output, regardless of
	 * matching lines. Exit with zero status if an input line is selected.
	 */
	public void silentMode() throws Exception {
		addOption(OutputControl.SILENT_MODE);
	}

	/**
	 * Perform pattern matching in searches without regard to case; see the Base
	 * Definitions volume of POSIX.1-2017, Section 9.2, Regular Expression General
	 * Requirements.
	 */
	public void ignoreCase() throws Exception {
		addOption(MatchingControl.IGNORE_CASE);
	}

	/**
	 * Precede each output line by its relative line number in the file, each file
	 * starting at line 1. The line number counter shall be reset for each file
	 * processed.
	 */
	public void lineNumbers() throws Exception {
		addOption(OutputLinePrefixControl.DISPLAY_LINE_NUMBERS);
	}

	/**
	 * Suppress the error messages ordinarily written for nonexistent or unreadable
	 * files. Other error messages shall not be suppressed.
	 */
	public void suppressErrors() throws Exception {
		addOption(OutputControl.SUPPRESS_ERROR);
	}

	/**
	 * Select lines not matching any of the specified patterns. If the -v option is
	 * not specified, selected lines shall be those that match any of the specified
	 * patterns.
	 */
	public void invertMatch() throws Exception {
		addOption(MatchingControl.INVERT_MATCHING);
	}

	/**
	 * Consider only input lines that use all characters in the line excluding the
	 * terminating <newline> to match an entire fixed string or regular expression
	 * to be matching lines.
	 */
	public void lineRegex() throws Exception {
		addOption(MatchingControl.LINE_REGEX);
	}

	/**
	 * Specify one or more patterns to be used during the search for input. The
	 * application shall ensure that patterns in pattern_list are separated by a
	 * <newline>. A null pattern can be specified by two adjacent <newline>
	 * characters in pattern_list. Unless the -E or -F option is also specified,
	 * each pattern shall be treated as a BRE, as described in the Base Definitions
	 * volume of POSIX.1-2017, Section 9.3, Basic Regular Expressions. Multiple -e
	 * and -f options shall be accepted by the grep utility. All of the specified
	 * patterns shall be used when matching lines, but the order of evaluation is
	 * unspecified.
	 * 
	 * @throws Exception
	 */
	public void addSearchPattern(String... patterns) throws Exception {
		addArgument("pattern", TextBlock.array(MatchingControl.PATTERNS, patterns));
	}

	/**
	 * Read one or more patterns from the file named by the pathname pattern_file.
	 * Patterns in pattern_file shall be terminated by a <newline>. A null pattern
	 * can be specified by an empty line in pattern_file. Unless the -E or -F option
	 * is also specified, each pattern shall be treated as a BRE, as described in
	 * the Base Definitions volume of POSIX.1-2017, Section 9.3, Basic Regular
	 * Expressions.
	 */
	public void addPatternFile(String... patternFiles) throws Exception {
		addArgument("pattern", TextBlock.array(MatchingControl.PATTERNS_FROM_FILE, patternFiles));
	}

	/**
	 * Pathnames of files to be searched for the patterns. If no file operands are
	 * specified, the standard input shall be used.
	 */
	public void addFileToSearch(String... filePaths) throws Exception {
		addArgument("file", FilePath.array(filePaths));
	}
}
