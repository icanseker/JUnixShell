package unix.cmd.bash.builtin.unalias;

import unix.cmd.UnixCommand;
import unix.cmd.arg.type.CharChain;
import unix.cmd.exitstat.mod.ExitStatusInterface;

/**
 * The alias utility shall create or redefine alias definitions or write the
 * values of existing alias definitions to standard output.
 * 
 * An alias definition shall affect the current shell execution environment and
 * the execution environments of the subshells of the current shell.
 * 
 * @author icanseker@gmail.com
 *
 */
public class Unalias extends UnixCommand<UnaliasOption> {

	public static final Class<? extends ExitStatusInterface> exitStatusEnumSet = UnaliasExitStatus.class;

	public Unalias() {
		super("unalias");
	}

	@Override
	public boolean acceptMultiArgument() {
		return true;
	}

	public void removeAlias(String name) throws Exception {

		resetOptions();

		/**
		 * The first word of each simple command, if unquoted, is checked to see if it
		 * has an alias. If so, that word is replaced by the text of the alias. The
		 * alias name and the replacement text may contain any valid shell input,
		 * including shell metacharacters, with the exception that the alias name may
		 * not contain `='.
		 * 
		 * So, 'alias sample\ name ...' or 'alias "sample name" ...' do not work
		 */

		if (name.contains(" ") || name.contains("="))
			return;

		addArgument(new CharChain(name.toCharArray()));
	}

	public void removeAll() throws Exception {

		resetArguments();
		addOption(UnaliasOption.REMOVE_ALL);
	}
}