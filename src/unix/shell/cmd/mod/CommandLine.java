package unix.shell.cmd.mod;

import unix.shell.expansion.CommandSubstitution;

public interface CommandLine {

	public String commandLine() throws Exception;

	public default String exitStatus() throws Exception {
		return commandLine() + " >&/dev/null; echo $?";
	}

	public default String substitution() throws Exception {
		return "" + new CommandSubstitution(this);
	}

	public default void print() throws Exception {
		System.out.println(commandLine());
	}
}
