package unix.cmd.core.grep;

import unix.cmd.UnixCommand;
import unix.cmd.arg.type.Text;
import unix.cmd.opt.mod.None;

public class Grep extends UnixCommand<None> {

	public Grep() {
		super("grep");
	}

	public Grep addMatchPattern(String argument) throws Exception {
		addArgument(new Text(argument));
		return this;
	}
}
