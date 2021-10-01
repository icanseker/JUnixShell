package unix.shell.cmd.core.grep;

import unix.shell.cmd.exitstat.mod.ExitStatusInterface;

public enum GrepExitStatus implements ExitStatusInterface {

	NO_LINE_SELECTED(1, "no lines were selected"), //

	;

	private final int code;
	private final String meaning;

	private GrepExitStatus(int code) {
		this.code = code;
		this.meaning = null;
	}

	private GrepExitStatus(int code, String meaning) {
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
