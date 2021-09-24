package unix.shell.cmd.mod.redirect;

public enum RedirectOperand {

	OVERRIDE_OUT(">".toCharArray()), //
	APPEND_OUT(">>".toCharArray()), //
	IN("<".toCharArray()), //
	;

	private final char[] symbol;

	private RedirectOperand(char[] symbol) {
		this.symbol = symbol;
	}

	public char[] symbol() {
		return this.symbol;
	}
}
