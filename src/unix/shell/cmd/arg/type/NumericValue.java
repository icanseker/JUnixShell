package unix.shell.cmd.arg.type;

import unix.shell.cmd.arg.Argument;

public class NumericValue extends Argument {

	public NumericValue(int argument) throws Exception {
		super(String.valueOf(argument));
	}
}
