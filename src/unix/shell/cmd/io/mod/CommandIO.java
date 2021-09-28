package unix.shell.cmd.io.mod;

public interface CommandIO {

	public int fileDescriptor();

	public default String file() {
		return null;
	}
}
