package unix.cmd.arg.type;

import unix.cmd.arg.Argument;

public class CharChain extends Argument {

	public CharChain(char[] sequence) throws Exception {
		super(sequence.toString());
	}
}
