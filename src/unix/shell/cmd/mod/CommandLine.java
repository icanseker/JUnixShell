package unix.shell.cmd.mod;

import unix.shell.expansion.CommandSubstitution;
import unix.shell.redirect.mod.RDMapping;

public interface CommandLine extends StrCorrespond, RDMapping {

	public default String exitStatus() throws Exception {
		return correspond() + " >&/dev/null; echo $?";
	}

	public default String substitution() throws Exception {
		return "" + new CommandSubstitution(this);
	}

	public default void print() throws Exception {
		System.out.println(correspond());
	}
}
