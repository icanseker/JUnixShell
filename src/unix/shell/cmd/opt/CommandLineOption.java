package unix.shell.cmd.opt;

import unix.shell.cmd.arg.mod.ArgumentBehavior;
import unix.shell.cmd.arg.mod.ArgumentInterface;
import unix.shell.cmd.mod.ClassIdentifier;
import unix.shell.cmd.opt.mod.OptionBehavior;

public interface CommandLineOption<OptionType extends CommandLineOption<OptionType>>
		extends ClassIdentifier, OptionBehavior<CommandLineOption<OptionType>>, ArgumentBehavior {

	public Character symbol();

	public String paramName();

	public default String correspond(ArgumentInterface... arguments) throws Exception {

		String classId = this.classId();

		if (this.requiresArgument() && arguments.length == 0)
			throw new Exception(classId + ": requires an argument");

		String descriptor = this.descriptor();

		if (this.acceptArgument() && arguments.length > 0) {

			String valueBinder = descriptor.startsWith("--") ? "=" : " ";

			if (!this.acceptMultiArgument() && arguments.length > 1) {
				System.err.println("WARNING: " + classId + " does not accept more than one argument");

				return descriptor + valueBinder + arguments[0].correspond();

			} else {

				String correspond = descriptor + valueBinder;

				for (ArgumentInterface argument : arguments)
					correspond += argument.correspond() + ",";

				return correspond.substring(0, correspond.length() - 1);
			}
		}

		return descriptor;
	}

	public default String identifier() {
		return descriptor();
	}

	public default String descriptor() {

		Character symbol = symbol();

		if (symbol == null)
			return "--" + paramName();
		else
			return "-" + String.valueOf(symbol);
	}
}
