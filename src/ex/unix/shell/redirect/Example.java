package ex.unix.shell.redirect;

import unix.shell.cmd.core.date.Date;
import unix.shell.cmd.io.CommandIOFactory;
import unix.shell.io.FileWrite;
import unix.shell.redirect.RedirectIn;
import unix.shell.redirect.RedirectOut;
import unix.shell.redirect.TransferOut;

public class Example {

	public static void main(String[] args) throws Exception {

		Date date = new Date();

		date.declareRedirection(new RedirectIn(CommandIOFactory.STDIN, "in/source.file"));
		date.print();

		date.declareRedirection(new RedirectOut(CommandIOFactory.STDOUT, "out/dest.file", FileWrite.OVERWRITE));
		date.print();

		date.declareRedirection(new RedirectOut(CommandIOFactory.STDERR, "err/dest.file", FileWrite.APPEND));
		date.print();

		date.declareRedirection(
				new RedirectOut(CommandIOFactory.DECLARE.out(5), "out/custom5.file", FileWrite.OVERWRITE));
		date.print();

		date.declareRedirection(new TransferOut(CommandIOFactory.STDOUT, CommandIOFactory.STDERR));
		date.print();

		date.declareRedirection(new TransferOut(CommandIOFactory.STDOUT, CommandIOFactory.DECLARE.out(5)));
		date.print();
	}
}
