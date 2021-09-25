package unix.shell.cmd.bash.builtin.echo;

import unix.shell.cmd.UnixCommand;
import unix.shell.cmd.arg.type.Text;
import unix.shell.cmd.opt.mod.None;

public class Echo extends UnixCommand<None> {

	public Echo() {
		super("echo");
	}

	@Override
	public boolean acceptArgument() {
		return true;
	}

	public Echo addArgument(String argument) throws Exception {
		addArgument(new Text(argument));
		return this;
	}
}
