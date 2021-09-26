package unix.shell.cmd.opt;

import unix.shell.cmd.arg.mod.ArgumentBehavior;
import unix.shell.cmd.arg.mod.ArgumentInterface;
import unix.shell.cmd.mod.ClassIdentifier;
import unix.shell.cmd.opt.mod.OptionBehavior;
import unix.shell.cmd.opt.mod.SingularOption;

public interface UnixCommandOption<OptionType extends UnixCommandOption<OptionType>>
		extends ClassIdentifier, OptionBehavior<UnixCommandOption<OptionType>>, ArgumentBehavior {

	public Character symbol();

	public String paramName();

	public default String correspond(ArgumentInterface... arguments) throws Exception {

		String classId = this.classId();

		if (this.requiresArgument() && arguments.length == 0)
			throw new Exception(classId + ": requires an argument");

		if (this.acceptArgument() && arguments.length > 0) {

			String optParamCorr = paramName();
			String valueBinder;

			if (optParamCorr == null) {
				optParamCorr = "-" + this.symbol();
				valueBinder = " ";
			} else {
				optParamCorr = "--" + optParamCorr;
				valueBinder = "=";
			}

			if (!this.acceptMultiArgument() && arguments.length > 1) {
				System.err.println("WARNING: " + classId + " does not accept more than one argument");

				return optParamCorr + valueBinder + arguments[0].correspond();

			} else {

				String correspond = optParamCorr + valueBinder;

				for (ArgumentInterface argument : arguments)
					correspond += argument.correspond() + ",";

				return correspond.substring(0, correspond.length() - 1);
			}
		} else {

			if (arguments.length > 0)
				System.err.println("WARNING: " + classId + " does not accept an argument");

			return identifier();
		}
	}

	public default String identifier() {

		if (this instanceof SingularOption)
			return ((SingularOption) this).singularId();

		Character symbol = symbol();

		if (symbol == null)
			return "--" + paramName();
		else
			return "-" + String.valueOf(symbol);
	}
}
