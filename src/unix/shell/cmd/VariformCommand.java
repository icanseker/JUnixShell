package unix.shell.cmd;

import unix.shell.cmd.opt.CommandLineOption;
import unix.shell.cmd.opt.SingularOption;

public abstract class VariformCommand<Variant extends SingularOption<OptionForm>, OptionForm extends CommandLineOption<OptionForm>>
		extends UnixCommand<OptionForm> {

	public VariformCommand(Variant variant, String name) throws Exception {
		this(name);
		addOption(variant);
	}

	/**
	 * use for default form
	 */
	public VariformCommand(String name) {
		super(name);
	}
}
