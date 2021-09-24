package unix.shell.cmd.mod;

public interface CommandLine {

	public String commandLine() throws Exception;

	public default String exitStatus() throws Exception {
		return commandLine() + " >&/dev/null; echo $?";
	}

	public default String execute() throws Exception {
		return "$( " + commandLine() + " )";
	}

	public default void print() throws Exception {
		System.out.println(commandLine());
	}
}
