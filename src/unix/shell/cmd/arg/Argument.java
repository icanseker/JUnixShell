package unix.shell.cmd.arg;

import unix.shell.cmd.arg.mod.ArgumentInterface;

public abstract class Argument implements ArgumentInterface {

	private String correspond;

	public Argument(String correspond) throws Exception {

		try {

			if (formValidator(correspond))
				this.correspond = correspond;
			else
				throw new Exception("The argument is not formed correctly: " + correspond);

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public String correspond() {
		return this.correspond;
	}

	public boolean formValidator(String correspond) throws Exception {
		return true;
	}

	public static Argument[] setOf(String... arguments) throws Exception {

		Argument[] set = new Argument[arguments.length];

		for (int i = 0; i < arguments.length; i++)
			set[i] = new Argument(arguments[i]) {
			};

		return set;
	}

	@Override
	public String toString() {
		return correspond();
	}
}
