package unix.shell.cmd.io.redirect.pipe.mod;

import unix.shell.cmd.UnixCommand;
import unix.shell.cmd.mod.StrCorrespond;

public class Pipeline implements StrCorrespond {

	private UnixCommand<?> destination;
	private boolean pipeErr;

	public Pipeline(UnixCommand<?> destination) {
		this.destination = destination;
	}

	public Pipeline redirectErrAlso(boolean pipeErr) {
		this.pipeErr = pipeErr;
		return this;
	}

	@Override
	public String correspond() throws Exception {
		return "|" + (pipeErr ? "& " : "") + destination.correspond();
	}
}
