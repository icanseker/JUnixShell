package unix.shell.cmd.arg.type;

import unix.shell.cmd.arg.Argument;

public class Text extends Argument {

	public Text(String argument) throws Exception {
		super(argument);
	}

	@Override
	public String correspond() {
		return super.correspond().replace(" ", "\\ ");
	}
}