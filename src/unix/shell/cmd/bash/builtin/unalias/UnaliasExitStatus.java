package unix.shell.cmd.bash.builtin.unalias;

import unix.shell.cmd.exitstat.mod.ExitStatusInterface;

/**
 * The unalias utility shall remove the definition for each alias name
 * specified.
 * 
 * @author Developer
 *
 */
public enum UnaliasExitStatus implements ExitStatusInterface {

	ALIAS_COULD_NOT_SET(1, "failure because an alias could not be set"), //
	INCORRECT_OPTION(2, "failure because of an incorrect command-line option"), //

	;

	private final int code;
	private final String meaning;

	private UnaliasExitStatus(int code) {
		this.code = code;
		this.meaning = null;
	}

	private UnaliasExitStatus(int code, String meaning) {
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
