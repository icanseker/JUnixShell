package unix.cmd.exitstat.mod;

public interface ExitStatusInterface {

	public default int code() {
		return -1;
	}

	public String meaning();
}
