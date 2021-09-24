package unix.cmd.bash.builtin.echo;

import unix.cmd.UnixCommand;
import unix.cmd.arg.type.Text;
import unix.cmd.opt.mod.None;

public class Echo extends UnixCommand<None> {

	public Echo() {
		super("echo");
	}

	public Echo addArgument(String argument) throws Exception {
		addArgument(new Text(argument));
		return this;
	}
}
