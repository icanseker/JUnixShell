package unix.shell.operator.rd;

public enum RedirectionType implements ShellRedirectOperator {

	/**
	 * ">" Redirect the standard output to a file or device, creating the file if it
	 * does not exist and overwriting the file if it does exist.
	 */
	OUT_OVERWRITE_FILE(">"),

	/**
	 * ">>" Redirect the standard output to a file or device, appending the output
	 * to the end of the file.
	 */
	OUT_APPEND_FILE(">>"),

	/**
	 * "/dev/tty" error Redirect output to screen
	 */
	OUT_TO_SCREEN("/dev/tty"),

	/**
	 * "<" Take input for cmd from file
	 */
	IN_FROM_FILE("<"),

	/**
	 * "/dev/stdin" Take input from keyboard
	 */
	IN_FROM_KEYBOARD("/dev/stdin"), //

	/**
	 * "2>" Redirect the standard error to a file or device.
	 */
	ERR_OVERWRITE_FILE("2>"),

	/**
	 * "2>>" Redirect and append the standard error to a file or device.
	 */
	ERR_APPEND_FILE("2>>"),

	/**
	 * "2>&1" Send standard error to the same place as standard output
	 * 
	 * <p/>
	 * Redirecting Standard Error in csh and tcsh <br/>
	 * The C shell and extended C shell also allow you to redirect standard error,
	 * but a little differently than the Bourne-compatible shells. To redirect both
	 * standard output and standard error to the same file, use the symbol ">&" (or
	 * ">>&", to append to the file),
	 */
	OUT_ERR_TO_FILE("2>&1"), //

	/**
	 * "|" Pipe the standard output of one command as input for another command.
	 */
	OUT_PIPE("|"), //

	/**
	 * "|&" Pipe the standard error as input to another command.
	 */
	ERR_PIPE("|&"),

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
