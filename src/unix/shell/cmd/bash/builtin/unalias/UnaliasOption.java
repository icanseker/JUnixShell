package unix.shell.cmd.bash.builtin.unalias;

import unix.shell.cmd.opt.UnixCommandOption;

public enum UnaliasOption implements UnixCommandOption<UnaliasOption> {

	/**
	 * Remove all alias definitions from the current shell execution environment.
	 */
	REMOVE_ALL('a'),

	;

	private final Character symbol;
	private final String varName;

	private UnaliasOption(Character symbol, String varName) {
		this.symbol = symbol;
		this.varName = varName;
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
	public String varName() {
		return this.varName;
	}
}
