package unix.shell.cmd.arg.type;

import unix.shell.cmd.arg.Argument;

public class CharChain extends Argument {

	public CharChain(char[] sequence) throws Exception {
		super(sequence.toString());
	}
}
