package unix.shell.redirect;

import unix.shell.cmd.io.mod.CommandIO;
import unix.shell.cmd.io.mod.CommandIn;
import unix.shell.cmd.io.mod.CommandOut;

public class CloseIO implements UnixRedirection {

	private CommandIO io;

	public UnixRedirection in(CommandIn in) {
		this.io = in;
		return this;
	}

	public UnixRedirection out(CommandOut out) {
		this.io = out;
		return this;
	}

	@Override
	public String rdDescriptor() {
		int fd = io.fd();
		return (fd > 1 ? fd : "") + (io instanceof CommandIn ? "<" : ">");
	}

	@Override
	public int IODescriptor() {
		return io.fd();
	}

	@Override
	public String correspond() throws Exception {
		return rdDescriptor() + "& -";
	}
}
