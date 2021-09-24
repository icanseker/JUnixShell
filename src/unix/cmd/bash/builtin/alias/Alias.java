package unix.cmd.bash.builtin.alias;

import unix.cmd.UnixCommand;
import unix.cmd.arg.type.Text;
import unix.cmd.exitstat.mod.ExitStatusInterface;
import unix.cmd.opt.mod.None;
import unix.cmd.param.Parameter;

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
public class Alias extends UnixCommand<None> {

	public static final Class<? extends ExitStatusInterface> exitStatusEnumSet = AliasExitStatus.class;

	public Alias() {
		super("alias");
	}

	@Override
	public boolean acceptMultiArgument() {
		return true;
	}

	public void addAlias(String name, String definition) throws Exception {

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
			throw new IllegalArgumentException("There must be no spaces or equality (=) characters in an alias name.");

		addArgument(new Parameter(name, new Text(definition)));
	}

	public void addAlias(String name, UnixCommand<?> definition) throws Exception {

		addArgument(new Parameter(name, new Text(definition.commandLine())));
	}

	public void all() {

		/**
		 * With no options, the alias command will return a list of all aliases in the
		 * environment.
		 */

		resetOptions();
		resetArguments();
	}
}
