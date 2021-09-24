package unix.cmd.core.passwd;

import unix.cmd.exitstat.mod.ExitStatusInterface;

public enum PasswdExitStatus implements ExitStatusInterface {

	PERMISSION_DENIED(1, "permission denied"), //
	INVALID_OPTIONS_COMBINATION(2, "invalid combination of options"), //
	UNEXPECTED_FAILURE(3, "unexpected failure, nothing done"), //
	PASSWD_FILE_MISSING(4, "unexpected failure, passwd file missing"), //
	PASSWD_FILE_BUSY(5, "passwd file busy, try again"), //
	INVALID_ARGUMENT(6, "invalid argument to option"), //

	;

	private final int code;
	private final String meaning;

	private PasswdExitStatus(int code) {
		this.code = code;
		this.meaning = null;
	}

	private PasswdExitStatus(int code, String meaning) {
		this.code = code;
		this.meaning = meaning;
	}

	@Override
	public int code() {
		return code;
	}

	@Override
	public String meaning() {
		return meaning;
	}
}
