package unix.shell.cmd.mod;

import unix.shell.cmd.opt.CommandLineOption;
import unix.shell.cmd.opt.OptionGroup;

/**
 * Some commands can be in a variety of forms (program). For example "grep"
 * command takes basic regex pattern as default, but on other form could take
 * extended regex patterns.
 */
public interface VariformCommand<OptionForm extends CommandLineOption<OptionForm>> {

	public OptionGroup<OptionForm> variform();
}
