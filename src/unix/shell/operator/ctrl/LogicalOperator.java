package unix.shell.operator.ctrl;

public enum LogicalOperator implements ShellControlOperator {

	NOT("!".toCharArray()), //
	AND("&&".toCharArray()), //
	OR("||".toCharArray()), //

	;

	private final char[] symbol;

	private LogicalOperator(char[] symbol) {
		this.symbol = symbol;
	}

	@Override
	public char[] symbol() {
		// TODO Auto-generated method stub
		return this.symbol;
	}
}
