package unix.shell.cmd.exitstat;

import unix.shell.cmd.exitstat.mod.ExitStatusInterface;

public enum StandardUnixExitStatus implements ExitStatusInterface {

	GENERAL_ERROR(1, "Miscellaneous errors, such as \"divide by zero\" and other impermissible operations"), //
	MISUSE_OF_SHELL_BUILTINS(2,
			"Missing keyword or command, or permission problem (and diff return code on a failed binary file comparison)"), //
	CANNOT_EXECUTE(126, "Permission problem or command is not an executable"), //
	COMMAND_NOT_FOUND(127, "Possible problem with $PATH or a typo"), //
	INVALID_ARGUMENT_TO_EXIT(128,
			"The exit command only takes a positive integer as its argument. This means any negative, fractional, or non-numeric values aren’t allowed."), //
	EXIT_STATUS_OUT_OF_RANGE(255, "Exit Status takes only integer args in the range 0 - 255"), //
	;

	private final int exitStatusValue;
	private final String meaning;

	private StandardUnixExitStatus(int exitStatusValue) {
		this.exitStatusValue = exitStatusValue;
		this.meaning = null;
	}

	private StandardUnixExitStatus(int exitStatusValue, String meaning) {
		this.exitStatusValue = exitStatusValue;
		this.meaning = meaning;
	}

	@Override
	public int code() {
		return exitStatusValue;
	}

	@Override
	public String meaning() {
		return meaning;
	}
}
