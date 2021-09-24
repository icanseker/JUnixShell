package unix.cmd.arg;

import unix.cmd.arg.mod.ArgumentInterface;

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
}
