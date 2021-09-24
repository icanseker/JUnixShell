package unix.cmd.bash.builtin.alias;

import unix.cmd.exitstat.mod.ExitStatusInterface;

public enum AliasExitStatus implements ExitStatusInterface {

	ALIAS_COULD_NOT_SET(1, "failure because an alias could not be set"), //
	INCORRECT_OPTION(2, "failure because of an incorrect command-line option"), //

	;

	private final int code;
	private final String meaning;

	private AliasExitStatus(int code) {
		this.code = code;
		this.meaning = null;
	}

	private AliasExitStatus(int code, String meaning) {
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
