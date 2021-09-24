package unix.cmd.arg.type;

import unix.cmd.arg.Argument;

public class TextBlock extends Argument {

	public TextBlock(String argument) throws Exception {
		super(argument);
	}

	@Override
	public String correspond() {

		String correspond = super.correspond();

		if (correspond == null)
			correspond = "";

		if (correspond.contains("'"))
			return "\"" + correspond + "\"";

		return "'" + correspond + "'";
	}
}