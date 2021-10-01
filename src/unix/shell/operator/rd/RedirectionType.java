package unix.shell.operator.rd;

/**
 * Redirection of input causes the file whose name results from the expansion of
 * word to be opened for reading on file descriptor n, or the standard input
 * (file descriptor 0) if n is not specified.
 * 
 * <p/>
 * The general format for redirecting input is: [n]< word
 * 
 * <p/>
 * ---
 * <p/>
 * 
 * Redirection of output (overwrite) causes the file whose name results from the
 * expansion of word to be opened for writing on file descriptor n, or the
 * standard output (file descriptor 1) if n is not specified. If the file does
 * not exist it is created; if it does exist it is truncated to zero size.
 * 
 * <p/>
 * The general format for redirecting output (overwrite) is: <b>[n]> [|]word</b>
 * 
 * <p/>
 * If the redirection operator is ‘>’, and the noclobber option to the set
 * builtin has been enabled, the redirection will fail if the file whose name
 * results from the expansion of word exists and is a regular file. If the
 * redirection operator is ‘>|’, or the redirection operator is ‘>’ and the
 * noclobber option is not enabled, the redirection is attempted even if the
 * file named by word exists.
 * 
 * <p/>
 * ---
 * <p/>
 * 
 * Redirection of output (append) in this fashion causes the file whose name
 * results from the expansion of word to be opened for appending on file
 * descriptor n, or the standard output (file descriptor 1) if n is not
 * specified. If the file does not exist it is created.
 * 
 * <p/>
 * The general format for appending output is: <b>[n]>> word</b>
 * 
 * <p/>
 * ---
 * <p/>
 * 
 * This construct (overwrite) allows both the standard output (file descriptor
 * 1) and the standard error output (file descriptor 2) to be redirected to the
 * file whose name is the expansion of word.
 * 
 * <p/>
 * There are two formats for redirecting standard output and standard error:
 * <b>&>word</b> and <b>>&word</b>
 * 
 * <p/>
 * Of the two forms, the first is preferred. This is semantically equivalent to
 * <b>>word 2>&1</b>
 * 
 * <p/>
 * ---
 * <p/>
 * 
 * This construct (append) allows both the standard output (file descriptor 1)
 * and the standard error output (file descriptor 2) to be appended to the file
 * whose name is the expansion of word.
 * 
 * <p/>
 * The format for appending standard output and standard error is:
 * <b>&>>word</b>
 * 
 * <p/>
 * This is semantically equivalent to <b>>>word 2>&1</b>
 * 
 * <p/>
 * ---
 * <p/>
 * 
 * Here Document redirection instructs the shell to read input from the current
 * source until a line containing only word (with no trailing blanks) is seen.
 * All of the lines read up to that point are then used as the standard input
 * (or file descriptor n if n is specified) for a command.
 * 
 * <p/>
 * The format of here-documents is: <b>[n]<<[-]word here-document delimiter</b>
 * 
 * <p/>
 * No parameter and variable expansion, command substitution, arithmetic
 * expansion, or filename expansion is performed on word. If any part of word is
 * quoted, the delimiter is the result of quote removal on word, and the lines
 * in the here-document are not expanded. If word is unquoted, all lines of the
 * here-document are subjected to parameter expansion, command substitution, and
 * arithmetic expansion, the character sequence \newline is ignored, and ‘\’
 * must be used to quote the characters ‘\’, ‘$’, and ‘`’.
 * 
 * <p/>
 * If the redirection operator is ‘<<-’, then all leading tab characters are
 * stripped from input lines and the line containing delimiter. This allows
 * here-documents within shell scripts to be indented in a natural fashion.
 * 
 * <p/>
 * ---
 * <p/>
 * 
 * Here String is a variant of here documents, the format is: <b>[n]<<< word</b>
 * 
 * <p/>
 * The word undergoes tilde expansion, parameter and variable expansion, command
 * substitution, arithmetic expansion, and quote removal. Filename expansion and
 * word splitting are not performed. The result is supplied as a single string,
 * with a newline appended, to the command on its standard input (or file
 * descriptor n if n is specified).
 * 
 * <p/>
 * ---
 * <p/>
 * 
 * OutPipe redirects the standard output of one command as input for another
 * command. <br/>
 * Pipe the standard error as input to another command.
 */
public enum RedirectionType implements ShellRedirectOperator {

	IN("<"), //
	OUT_OVERWRITE(">"), //
	OUT_APPEND(">>"), //
	ERR_OVERWRITE("2>"), //
	ERR_APPEND("2>>"), //
	OUT_ERR_OVERWRITE("&>"), //
	OUT_ERR_APPEND("&>>"), //
	HERE_DOC("<<"), //
	HERE_STR("<<<"), //
	OUT_PIPE("|"), //
	ERR_PIPE("|&"), //

	;

	private final CharSequence symbol;

	private RedirectionType(CharSequence symbol) {
		this.symbol = symbol;
	}

	@Override
	public CharSequence symbol() {
		return this.symbol;
	}
}
