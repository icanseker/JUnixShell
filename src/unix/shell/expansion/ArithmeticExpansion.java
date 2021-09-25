package unix.shell.expansion;

/**
 * Arithmetic expansion allows the evaluation of an arithmetic expression and
 * the substitution of the result. The format for arithmetic expansion is:
 * <b>$(( expression ))</b>
 *
 * <p/>
 * The expression is treated as if it were within double quotes, but a double
 * quote inside the parentheses is not treated specially. All tokens in the
 * expression undergo parameter and variable expansion, command substitution,
 * and quote removal. The result is treated as the arithmetic expression to be
 * evaluated. Arithmetic expansions may be nested.
 * 
 * <p/>
 * If the expression is invalid, Bash prints a message indicating failure to the
 * standard error and no substitution occurs.
 */
public class ArithmeticExpansion extends ShellExpansion {

	private String expression;

	public ArithmeticExpansion(String expression) throws Exception {
		this.expression = expression;
	}

	@Override
	public String correspond() {
		return "$(( " + expression + " ))";
	}
}
