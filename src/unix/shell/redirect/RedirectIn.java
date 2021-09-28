package unix.shell.redirect;

import unix.shell.cmd.io.mod.CommandIn;

public class RedirectIn implements UnixRedirection {

	private CommandIn in;
	private String source;

	public RedirectIn(CommandIn in, String source) {
		this.in = in;
		this.source = source;
	}

	@Override
	public String rdDescriptor() {

		int descriptor = in.fileDescriptor();

		/**
		 * default descriptor of < is 0 (stdin)
		 */
		return (descriptor == 0 ? "" : descriptor) + "<";
	}

	@Override
	public int IODescriptor() {
		return in.fileDescriptor();
	}

	@Override
	public String correspond() throws Exception {
		return rdDescriptor() + " " + source;
	}
}
