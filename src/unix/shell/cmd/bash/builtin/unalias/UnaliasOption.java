package unix.shell.cmd.bash.builtin.unalias;

import unix.shell.cmd.opt.CommandLineOption;

public enum UnaliasOption implements CommandLineOption<UnaliasOption> {

	/**
	 * Remove all alias definitions from the current shell execution environment.
	 */
	REMOVE_ALL('a'),

	;

	private final Character symbol;
	private final String paramName;

	private UnaliasOption(Character symbol, String paramName) {
		this.symbol = symbol;
		this.paramName = paramName;
	}

	private UnaliasOption(Character symbol) {
		this(symbol, null);
	}

	private UnaliasOption(String varName) {
		this(null, varName);
	}

	@Override
	public Character symbol() {
		return this.symbol;
	}

	@Override
	public String paramName() {
		return this.paramName;
	}
}
