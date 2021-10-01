package unix.shell.cmd.io.redirect.mod;

import unix.shell.cmd.io.mod.CommandIO;
import unix.shell.cmd.io.mod.CommandIn;
import unix.shell.cmd.io.mod.CommandOut;
import unix.shell.cmd.io.redirect.RedirectIn;
import unix.shell.cmd.io.redirect.RedirectOut;
import unix.shell.cmd.io.redirect.TransferIO;
import unix.shell.cmd.io.redirect.UnixRedirection;
import unix.shell.io.FileWrite;

public interface IOMapping {

	public default void declareRedirection(CommandIn in, String source) {
		declareRedirection(new RedirectIn(in, source));
	}

	public default void declareRedirection(CommandOut out, String destination, FileWrite writeType) {
		declareRedirection(new RedirectOut(out, destination, writeType));
	}

	public default void declareRedirection(CommandIn from, CommandIO to) {
		declareRedirection(new TransferIO().in(from, to));
	}

	public default void declareRedirection(CommandOut from, CommandIO to) {
		declareRedirection(new TransferIO().out(from, to));
	}

	public void declareRedirection(UnixRedirection redirection);

	public void openFile2ReadWrite(int fileDescriptor, String ioSource);
}
