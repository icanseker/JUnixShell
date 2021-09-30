package unix.shell.cmd.mod;

public interface StrCorrespond {
	public String correspond() throws Exception;

	public default void print() throws Exception {
		System.out.println(correspond());
	}
}
