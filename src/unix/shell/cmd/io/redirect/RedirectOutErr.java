package unix.shell.cmd.io.redirect;

import unix.shell.cmd.arg.type.FilePath;
import unix.shell.cmd.io.redirect.mod.SafeRedirection;
import unix.shell.io.FileWrite;

/**
 * This construct allows both the standard output (file descriptor 1) and the
 * standard error output (file descriptor 2) to be redirected/appended to the
 * file whose name is the expansion of word.
 * 
 * <p/>
 * There are two formats for redirecting standard output and standard error:
 * <b>&>word</b> and <b>>&word</b> Of the two forms, the first is preferred.
 * 
 * <br/>
 * This is semantically equivalent to
 * 
 * <b>>word 2>&1</b>
 * 
 * <p/>
 * This construct allows both the standard output (file descriptor 1) and the
 * standard error output (file descriptor 2) to be appended to the file whose
 * name is the expansion of word.
 * 
 * The format for appending standard output and standard error is:
 * 
 * <b>&>>word</b>
 * 
 * This is semantically equivalent to
 * 
 * <b>>>word 2>&1</b>
 */
public class RedirectOutErr implements UnixRedirection, SafeRedirection {

	private String destination;
	private FileWrite writeType;

	private boolean isSafe;

	public RedirectOutErr(String destination, FileWrite writeType) {
		this.destination = destination;
		this.writeType = writeType;
	}

	private boolean overwriteDest() {
		return this.writeType == FileWrite.OVERWRITE ? true : false;
	}

	@Override
	public String rdDescriptor() {
		return "&>";
	}

	@Override
	public int IODescriptor() {
		return -1;
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
