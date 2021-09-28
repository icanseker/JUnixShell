package unix.shell.cmd.io.mod;

public interface CommandIO {

	public int descriptor();

	public default String fileDescriptor() {
		return null;
	}
}
