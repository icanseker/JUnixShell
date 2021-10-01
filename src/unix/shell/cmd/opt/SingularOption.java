package unix.shell.cmd.opt;

import unix.shell.cmd.mod.SingularIdentifier;

/**
 * Some command options can be excluded by a command option. When alternatives
 * are grouped as SingularOption, all of these options are mutually exclusive.
 */
public interface SingularOption<OptionType extends CommandLineOption<OptionType>>
		extends CommandLineOption<OptionType>, SingularIdentifier {

	@Override
	default String identifier() {
		return this.singularId();
	}
}
