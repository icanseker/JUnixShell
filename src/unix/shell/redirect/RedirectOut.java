package unix.shell.redirect;

import unix.shell.cmd.arg.type.FilePath;
import unix.shell.cmd.io.mod.CommandOut;
import unix.shell.io.FileWrite;
import unix.shell.redirect.mod.SafeRedirection;

/**
 * Redirection of output causes the file whose name results from the expansion
 * of word to be opened for writing on file descriptor n, or the standard output
 * (file descriptor 1) if n is not specified. If the file does not exist it is
 * created; if it does exist it is truncated to zero size.
 * 
 * The general format for redirecting output is:
 * 
 * <b>[n]>[|]word</b>
 * 
 * <p/>
 * If the redirection operator is ‘>’, and the noclobber option to the set
 * builtin has been enabled, the redirection will fail if the file whose name
 * results from the expansion of word exists and is a regular file. If the
 * redirection operator is ‘>|’, or the redirection operator is ‘>’ and the
 * noclobber option is not enabled, the redirection is attempted even if the
 * file named by word exists.
 * 
 * <p/>
 * Redirection of output in this fashion causes the file whose name results from
 * the expansion of word to be opened for appending on file descriptor n, or the
 * standard output (file descriptor 1) if n is not specified. If the file does
 * not exist it is created.
 * 
 * The general format for appending output is:
 * 
 * <b>[n]>>word</b>
 * 
 */
public class RedirectOut implements UnixRedirection, SafeRedirection {

	private CommandOut out;
	private String destination;
	private FileWrite writeType;

	private boolean isSafe;

	public RedirectOut(CommandOut out, String destination, FileWrite writeType) {
		this.out = out;
		this.destination = destination;
		this.writeType = writeType;
	}

	private boolean overwriteDest() {
		return this.writeType == FileWrite.OVERWRITE ? true : false;
	}

	@Override
	public String rdDescriptor() {
		int fd = out.fd();
		return (fd == 1 ? "" : fd) + ">";
		/*
		 * default descriptor of > / >> is 1 (stdout)
		 */
	}

	@Override
	public int IODescriptor() {
		return out.fd();
	}

	@Override
	public String correspond() throws Exception {
		return rdDescriptor() + (overwriteDest() ? "" : ">") + (this.isSafe ? "!" : "") + " "
				+ new FilePath(destination);
	}

	@Override
	public UnixRedirection safety(boolean safety) {
		this.isSafe = safety;
		return this;
	}

	@Override
	public boolean isSafe() {
		return this.isSafe;
	}
}
