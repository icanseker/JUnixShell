package unix.shell.cmd.param;

import unix.shell.cmd.arg.Argument;
import unix.shell.cmd.param.mod.ParameterInterface;

public class Parameter implements ParameterInterface {

	private String name;
	private String value;

	private char[] binder;

	public Parameter(String name, Argument value) throws Exception {
		this.name = name;
		this.value = value.correspond();
	}

	@Override
	public String name() {
		return this.name;
	}

	@Override
	public String value() {
		return this.value;
	}

	@Override
	public char[] binder() {
		return binder == null ? ParameterInterface.super.binder() : binder;
	}

	public void setBinder(char[] binder) {
		this.binder = binder;
	}
}
