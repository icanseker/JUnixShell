package unix.cmd.arg.type;

import unix.cmd.arg.Argument;

public class Text extends Argument {

	public Text(String argument) throws Exception {
		super(argument);
	}

	@Override
	public String correspond() {
		return super.correspond().replace(" ", "\\ ");
	}
}