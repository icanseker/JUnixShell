package unix.shell.cmd.io.redirect;

import unix.shell.cmd.arg.type.FilePath;
import unix.shell.cmd.io.mod.CommandIn;

/**
 * Redirection of input causes the file whose name results from the expansion of
 * word to be opened for reading on file descriptor n, or the standard input
 * (file descriptor 0) if n is not specified.
 * 
 * The general format for redirecting input is:
 * 
 * <b>[n]<word</b>
 */
public class RedirectIn implements UnixRedirection {

	private CommandIn in;
	private String source;

	public RedirectIn(CommandIn in, String ioSource) {
		this.in = in;
		this.source = ioSource;
	}

	@Override
	public String rdDescriptor() {
		int fd = in.fd();
		return (fd == 0 ? "" : fd) + "<";
		/*
		 * default descriptor of < is 0 (stdin)
		 */
	}

	@Override
	public int IODescriptor() {
		return in.fd();
	}

	@Override
	public String correspond() throws Exception {
		return rdDescriptor() + " " + new FilePath(source);
	}
}
