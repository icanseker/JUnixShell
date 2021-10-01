package unix.shell.cmd;

import unix.shell.cmd.opt.CommandLineOption;
import unix.shell.cmd.opt.SingularOption;

public abstract class VariformCommand<Variant extends SingularOption<OptionForm>, OptionForm extends CommandLineOption<OptionForm>>
		extends UnixCommand<OptionForm> {

	public VariformCommand(Variant variant, String identifier) throws Exception {
		this(identifier);
		addOption(variant);
	}

	/**
	 * use for default form
	 */
	public VariformCommand(String identifier) {
		super(identifier);
	}
}
