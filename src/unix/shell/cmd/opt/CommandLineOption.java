package unix.shell.cmd.opt;

import unix.shell.cmd.mod.ClassIdentifier;
import unix.shell.cmd.opt.mod.OptionInteraction;

public interface CommandLineOption<OptionType extends CommandLineOption<OptionType>>
		extends ClassIdentifier, OptionInteraction<CommandLineOption<OptionType>> {

	public Character symbol();

	public String paramName();

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

	public default boolean requireArgument() {
		return false;
	}

	public default boolean takeOptionalArgument() {
		return requireArgument() || false;
	}
}
