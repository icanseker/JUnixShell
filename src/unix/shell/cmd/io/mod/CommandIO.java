package unix.shell.cmd.io.mod;

public interface CommandIO {

	/**
	 * A file descriptor, is a positive integer that refers to an input/output
	 * source. For example, stdin is 0, stdout is 1, and stderr is 2. Those might
	 * seem like arbitrary numbers, because they are: the POSIX standard defines
	 * them as such, and many operating systems (like OS X and Linux) implement at
	 * least this part of the POSIX standard.
	 */
	public int fd();

	public default String ioSource() throws Exception {
		return null;
	}
}
