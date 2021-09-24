package unix.cmd.arg.type;

import unix.cmd.arg.Argument;

public class NumericValue extends Argument {

	public NumericValue(int argument) throws Exception {
		super(String.valueOf(argument));
	}
}
