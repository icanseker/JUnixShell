package unix.shell.cmd.core.grep;

import unix.shell.cmd.UnixCommand;
import unix.shell.cmd.arg.type.Text;
import unix.shell.cmd.opt.mod.None;

public class Grep extends UnixCommand<None> {

	public Grep() {
		super("grep");
	}

	public Grep addMatchPattern(String argument) throws Exception {
		addArgument(new Text(argument));
		return this;
	}
}
