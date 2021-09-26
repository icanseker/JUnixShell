package unix.shell.io;

/**
 * Bash handles several filenames specially when they are used in redirections,
 * as described.
 * 
 * <p/>
 * If the operating system on which Bash is running provides these special
 * files, bash will use them; otherwise it will emulate them internally with the
 * behavior described.
 */
public enum UnixStandardIO {

	IN(0, "/dev/stdin"), //
	OUT(1, "/dev/stdout"), //
	ERR(2, "/dev/stderr")//
	;

	private final int descriptor;
	private final String fileDescriptor;

	private UnixStandardIO(int descriptor, String fileDescriptor) {
		this.descriptor = descriptor;
		this.fileDescriptor = fileDescriptor;
	}

	public int descriptor() {
		return this.descriptor;
	}

	public String fileDescriptor() {
		return this.fileDescriptor;
	}
}
