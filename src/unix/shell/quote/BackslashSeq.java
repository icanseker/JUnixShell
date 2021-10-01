package unix.shell.quote;

public enum BackslashSeq {

	ALERT('a'), //
	BACKSPACE('b'), //
	ESCAPE('e'), //
	FORM_FEED('f'), //
	NEWLINE('n'), //
	CARRIAGE_RETURN('r'), //
	HORIZONTAL_TAB('t'), //
	VERTICAL_TAB('v'), //
	BACKSLASH('\\'), //
	SINGLE_QUOTE('\''), //
	DOUBLE_QUOTE('"'), //
	QUESTION_MARK('?'), //

	;

	private final char symbol;

	private BackslashSeq(char symbol) {
		this.symbol = symbol;
	}

	public CharSequence symbol() {
		return "\\" + this.symbol;
	}
}
