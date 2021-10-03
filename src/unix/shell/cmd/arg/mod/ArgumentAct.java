package unix.shell.cmd.arg.mod;

public interface ArgumentAct {

	public boolean require();

	public boolean optional();

	public boolean multiple();

	public static final ArgumentAct NONE = new ArgumentAct() {

		@Override
		public boolean require() {
			return false;
		}

		@Override
		public boolean optional() {
			return false;
		}

		@Override
		public boolean multiple() {
			return false;
		}
	};

	/**
	 * []
	 */
	public static final ArgumentAct OPTIONAL_ONE = new ArgumentAct() {

		@Override
		public boolean require() {
			return false;
		}

		@Override
		public boolean optional() {
			return true;
		}

		@Override
		public boolean multiple() {
			return false;
		}
	};

	/**
	 * []...
	 */
	public static final ArgumentAct OPTIONAL_MULTIPLE = new ArgumentAct() {

		@Override
		public boolean require() {
			return false;
		}

		@Override
		public boolean optional() {
			return true;
		}

		@Override
		public boolean multiple() {
			return true;
		}
	};

	/**
	 * ...
	 */
	public static final ArgumentAct REQUIRE_MULTIPLE = new ArgumentAct() {

		@Override
		public boolean require() {
			return true;
		}

		@Override
		public boolean optional() {
			return false;
		}

		@Override
		public boolean multiple() {
			return true;
		}
	};

	public static String actCorrespond(String argumentName, ArgumentAct act) {

		if (act.require()) {
			if (!act.optional()) {
				if (act.multiple())
					return argumentName + "...";
				else
					return argumentName;
			}
		} else {
			if (act.optional()) {
				if (act.multiple())
					return "[" + argumentName + "]" + "...";
				else
					return "[" + argumentName + "]";
			}
		}

		return "";
	}
}
