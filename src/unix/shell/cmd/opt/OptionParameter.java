package unix.shell.cmd.opt;

import unix.shell.cmd.arg.Argument;
import unix.shell.cmd.param.Parameter;

public class OptionParameter extends Parameter {

	public OptionParameter(CommandLineOption<?> option, Argument value) throws Exception {

		super(option.descriptor(), value);

		if (!name().startsWith("--"))
			setBinder(" ".toCharArray());
	}
}
