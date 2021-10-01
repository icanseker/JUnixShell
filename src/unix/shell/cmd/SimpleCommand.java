package unix.shell.cmd;

import unix.shell.cmd.opt.mod.None;

public abstract class SimpleCommand extends UnixCommand<None> {

	public SimpleCommand(String identifier) {
		super(identifier);
	}
}
