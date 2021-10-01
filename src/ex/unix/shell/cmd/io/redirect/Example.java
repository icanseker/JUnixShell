package ex.unix.shell.cmd.io.redirect;

import unix.shell.cmd.SimpleCommand;
import unix.shell.cmd.io.CommandIOFactory;
import unix.shell.cmd.io.redirect.CloseIO;
import unix.shell.cmd.io.redirect.RedirectIn;
import unix.shell.cmd.io.redirect.RedirectOut;
import unix.shell.cmd.io.redirect.RedirectOutErr;
import unix.shell.io.FileWrite;

public class Example {

	public static void main(String[] args) throws Exception {

		SimpleCommand command = new SimpleCommand("cmd") {
		};

		/*
		 * redirect standard input from a source
		 */
		command.declareRedirection(new RedirectIn(CommandIOFactory.STDIN, "in.file"));
		command.print();

		/*
		 * declare (easier way) standard input, will override previous in redirection
		 * 
		 * redirections mapped by UnixRedirection.rdDescriptor(), so new declaration
		 * will override previous one if exist
		 */
		command.declareRedirection(CommandIOFactory.DECLARE.in(0), "in0.file");
		command.print();

		/*
		 * declare other input redirection
		 */
		command.declareRedirection(new RedirectIn(CommandIOFactory.DECLARE.in(3), "in3.file"));
		command.print();

		/*
		 * redirect standard output to a destination with file write option
		 */
		command.declareRedirection(new RedirectOut(CommandIOFactory.STDOUT, "out.file", FileWrite.OVERWRITE));
		command.print();

		/*
		 * declare (easier way) standard output, will override previous redirection
		 * 
		 * redirections mapped by UnixRedirection.rdDescriptor(), so new declaration
		 * will override previous one if exist
		 */
		command.declareRedirection(CommandIOFactory.DECLARE.out(1), "out1.file", FileWrite.OVERWRITE);
		command.print();

		/*
		 * declare other output redirection with appending and safety options
		 */
		command.declareRedirection(
				new RedirectOut(CommandIOFactory.DECLARE.out(4), "out4.file", FileWrite.APPEND).safety(true));
		command.print();

		/*
		 * if remove safety option and change write type as overwrite, still previous
		 * redirection will be overridden.
		 */
		command.declareRedirection(new RedirectOut(CommandIOFactory.DECLARE.out(4), "out4.file", FileWrite.OVERWRITE));
		command.print();

		/*
		 * declare an input transfer
		 */
		command.declareRedirection(CommandIOFactory.STDIN, CommandIOFactory.DECLARE.in(5));
		command.print();

		/*
		 * transfer standard error to standard out
		 */
		command.declareRedirection(CommandIOFactory.STDERR, CommandIOFactory.STDOUT);
		command.print();

		/*
		 * transfer standard error to new out, will override previous
		 */
		command.declareRedirection(CommandIOFactory.STDERR, CommandIOFactory.DECLARE.out(6));
		command.print();

		/*
		 * open a file to read/write
		 */
		command.openFile2ReadWrite(7, "seven.file");
		command.print();

		/*
		 * redirect both standard out and standard error, will override previously
		 * declared >, 1>, 2>
		 */
		command.declareRedirection(new RedirectOutErr("both.file", FileWrite.APPEND));
		command.print();

		/*
		 * redirect both standard out and standard error with safety
		 */
		command.declareRedirection(new RedirectOutErr("both.file", FileWrite.APPEND).safety(true));
		command.print();

		/*
		 * close (suspend) an input will override 3< and 3<&
		 */
		command.declareRedirection(new CloseIO().in(CommandIOFactory.DECLARE.in(3)));
		command.print();

		/*
		 * close (suspend) an output will override 4> and 4>> and 4>&
		 */
		command.declareRedirection(new CloseIO().out(CommandIOFactory.DECLARE.out(4)));
		command.print();
	}
}
