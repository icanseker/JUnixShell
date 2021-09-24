package unix.shell.cmd.exitstat;

import unix.shell.cmd.exitstat.mod.ExitStatusInterface;

/**
 * When a program exits, it can return to the parent process a small amount of
 * information about the cause of termination, using the exit status. This is a
 * value between 0 and 255 that the exiting process passes as an argument to
 * exit.
 * 
 * @author icanseker@gmail.com
 *
 */
public class ExitStatus implements ExitStatusInterface {

	private int code;
	private String meaning;

	public ExitStatus(int code, String meaning) {

		if (code < 0 || code > 255)
			throw new IllegalArgumentException(
					"An exit code must be between 0 and 255 that the exiting process passes as an argument to exit.");

		this.code = code;
		this.meaning = meaning;
	}

	@Override
	public int code() {
		return this.code;
	}

	@Override
	public String meaning() {
		return this.meaning;
	}
}
