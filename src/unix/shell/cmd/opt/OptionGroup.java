package unix.shell.cmd.opt;

import unix.shell.cmd.mod.SingularIdentifier;

/**
 * Some command options can be excluded by a command option. When grouped as
 * OptionGroup with alternatives, all of these options are mutually exclusive.
 */
public interface OptionGroup<OptionType extends CommandLineOption<OptionType>>
		extends CommandLineOption<OptionType>, SingularIdentifier {

	@Override
	public default String identifier() {
		return this.singularId();
	}
}
