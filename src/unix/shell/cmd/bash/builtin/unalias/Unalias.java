package unix.shell.cmd.bash.builtin.unalias;

import unix.shell.cmd.UnixCommand;
import unix.shell.cmd.arg.mod.ArgumentAct;
import unix.shell.cmd.arg.type.CharChain;
import unix.shell.cmd.exitstat.mod.ExitStatusInterface;
import unix.shell.cmd.outline.FieldMap;

/**
 * The alias utility shall create or redefine alias definitions or write the
 * values of existing alias definitions to standard output.
 * 
 * An alias definition shall affect the current shell execution environment and
 * the execution environments of the subshells of the current shell.
 */
public class Unalias extends UnixCommand<UnaliasOption> {

	public static final Class<? extends ExitStatusInterface> exitStatusEnumSet = UnaliasExitStatus.class;

	public Unalias() {
		super("unalias");
	}

	/**
	 * unalias has two different synopsis: <b>unalias alias-name...<b/> (will be
	 * default) and <b>unalias -a</b>
	 * 
	 * <p/>
	 * When -a option is added to unalias, the synopsis will be updated.
	 */
	@Override
	protected FieldMap synopsis() {

		FieldMap synopsis = new FieldMap();
		synopsis.addArgumentGroup("alias-name", ArgumentAct.REQUIRE_MULTIPLE);

		return synopsis;
	}

	/**
	 * The first word of each simple command, if unquoted, is checked to see if it
	 * has an alias. If so, that word is replaced by the text of the alias. The
	 * alias name and the replacement text may contain any valid shell input,
	 * including shell metacharacters, with the exception that the alias name may
	 * not contain `='.
	 * 
	 * So, 'alias sample\ name ...' or 'alias "sample name" ...' do not work
	 */
	public void removeAlias(String name) throws Exception {

		if (name.contains(" ") || name.contains("="))
			return;
		addArgument("alias-name", new CharChain(name.toCharArray()));
	}

	public void removeAll() throws Exception {
		removeArgumentGroup("alias-name");
		addOption(UnaliasOption.REMOVE_ALL);
	}
}
