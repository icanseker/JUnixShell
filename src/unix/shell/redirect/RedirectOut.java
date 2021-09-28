package unix.shell.redirect;

import unix.shell.cmd.io.mod.CommandOut;
import unix.shell.io.FileWrite;

public class RedirectOut implements UnixRedirection {

	private CommandOut out;
	private String destination;
	private FileWrite writeType;

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

		int descriptor = out.descriptor();

		/**
		 * default descriptor of > / >> is 1 (stdout)
		 */
		return (descriptor == 1 ? "" : descriptor) + (overwriteDest() ? ">" : ">>");
	}

	@Override
	public String correspond() throws Exception {
		return rdDescriptor() + " " + destination;
	}
}
