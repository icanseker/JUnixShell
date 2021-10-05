package unix.shell.cmd.arg.type;

import unix.shell.cmd.arg.Argument;
import unix.shell.cmd.opt.CommandLineOption;
import unix.shell.cmd.opt.OptionParameter;

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

	public static TextBlock[] array(String... arguments) throws Exception {

		TextBlock[] set = new TextBlock[arguments.length];

		for (int i = 0; i < arguments.length; i++)
			set[i] = new TextBlock(arguments[i]);

		return set;
	}

	public static OptionParameter[] array(CommandLineOption<?> option, String... arguments) throws Exception {

		OptionParameter[] set = new OptionParameter[arguments.length];

		for (int i = 0; i < arguments.length; i++)
			set[i] = new OptionParameter(option, new TextBlock(arguments[i]));

		return set;
	}
}