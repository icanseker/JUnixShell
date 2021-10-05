package ex.unix.shell.cmd.grep;

import unix.shell.cmd.core.grep.Grep;

public class Example {

	public static void main(String[] args) throws Exception {

		Grep grep = new Grep();

		/*
		 * if any search pattern/pattern-file is not provided, a synopsis error will be
		 * thrown.
		 */

		/*
		 * can provide any number of search pattern
		 */
		grep.addSearchPattern("interface", "route");

		/*
		 * can provide any number of file that will be searched for given patterns.
		 */
		grep.addFileToSearch("text.file", "text.file.copy");

		/*
		 * can provide any number of pattern file (each line will be different pattern)
		 */
		grep.addPatternFile("pattern.file", "pattern.file.copy");

		/*
		 * can specify grep variant
		 */
		grep.extendedRegexModeOn();
		grep.fixedStringModeOn();
		grep.BasicRegexModeOn(); // this is default mode

		/*
		 * control matching
		 */
		grep.ignoreCase();
		grep.invertMatch();
		grep.lineRegex();

		/*
		 * control output line prefix
		 */
		grep.lineNumbers();

		/*
		 * control the output, these options are mutual exclusive
		 */
		grep.silentMode();
		grep.countMatchingLines();
		grep.matchingFileNames();

		/*
		 * suppress error; non-readable, non-exist
		 */
		grep.suppressErrors();

		grep.print();
	}
}
