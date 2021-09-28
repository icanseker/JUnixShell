package unix.shell.redirect;

import unix.shell.cmd.io.mod.CommandOut;

public class TransferOut implements UnixRedirection {

	private CommandOut source;
	private CommandOut destination;

	public TransferOut(CommandOut from, CommandOut to) {
		this.source = from;
		this.destination = to;
	}

	@Override
	public String rdDescriptor() {
		return source.descriptor() + ">&";
	}

	@Override
	public String correspond() throws Exception {
		return rdDescriptor() + destination.descriptor();
	}
}
