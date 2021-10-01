package ex.unix.shell.cmd.grep;

import unix.shell.cmd.core.grep.Grep;
import unix.shell.cmd.core.grep.GrepVariant;

public class Example {

	public static void main(String[] args) throws Exception {

		Grep grepCommand = new Grep(GrepVariant.EXTENDED_REGEX);

		grepCommand.print();
	}
}
