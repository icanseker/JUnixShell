package unix.cmd.arg.mod;

public interface ArgumentBehavior {

	public default boolean requiresArgument() {
		return false;
	}

	public default boolean acceptArgument() {
		return requiresArgument() || acceptMultiArgument();
	}

	public default boolean acceptMultiArgument() {
		return false;
	}
}
