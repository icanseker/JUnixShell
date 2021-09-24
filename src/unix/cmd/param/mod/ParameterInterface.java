package unix.cmd.param.mod;

import unix.cmd.arg.mod.ArgumentInterface;

public interface ParameterInterface extends ArgumentInterface {

	public String name();

	public String value();

	public default char[] binder() {
		return "=".toCharArray();
	}

	@Override
	public default String correspond() {
		return name() + String.valueOf(binder()) + value();
	}
}
