package unix.shell.operator.rd;

/**
 * Linux command chaining is a technique of merging several commands such that
 * each of them can execute in sequence depending on the operator that separates
 * them and these operators decide how the commands will get executed. It allows
 * us to run multiple commands in succession or simultaneously.
 * 
 * @author icanseker@gmail.com
 *
 */
public enum RedirectOperator implements ShellRedirectOperator {

	AMPERSAND("&".toCharArray()), //
	AND("&&".toCharArray()), //
	OR("||".toCharArray()), //
	SEMICOLON(";".toCharArray()), //
	PIPE("|".toCharArray()), //

	;

	private final char[] symbol;

	private RedirectOperator(char[] symbol) {
		this.symbol = symbol;
	}

	@Override
	public char[] symbol() {
		// TODO Auto-generated method stub
		return this.symbol;
	}
}
