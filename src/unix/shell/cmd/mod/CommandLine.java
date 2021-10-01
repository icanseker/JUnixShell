package unix.shell.cmd.mod;

import unix.shell.cmd.io.redirect.mod.IOMapping;
import unix.shell.expansion.CommandSubstitution;

public interface CommandLine extends StrCorrespond, IOMapping {

	public default String exitStatus() throws Exception {
		return correspond() + " >&/dev/null; echo $?";
	}

	public default String substitution() throws Exception {
		return "" + new CommandSubstitution(this);
	}
}
