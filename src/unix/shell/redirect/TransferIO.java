package unix.shell.redirect;

import unix.shell.cmd.io.mod.CommandIO;
import unix.shell.cmd.io.mod.CommandIn;
import unix.shell.cmd.io.mod.CommandOut;

public class TransferIO implements UnixRedirection {

	private CommandIO source;
	private CommandIO destination;

	public UnixRedirection in(CommandIn from, CommandIO to) {
		this.source = from;
		this.destination = to;
		return this;
	}

	public UnixRedirection out(CommandOut from, CommandIO to) {
		this.source = from;
		this.destination = to;
		return this;
	}

	@Override
	public String rdDescriptor() {
		return source.fd() + (source instanceof CommandIn ? "<" : ">") + "&";
	}

	@Override
	public int IODescriptor() {
		return source.fd();
	}

	@Override
	public String correspond() throws Exception {
		return rdDescriptor() + destination.fd();
	}
}
