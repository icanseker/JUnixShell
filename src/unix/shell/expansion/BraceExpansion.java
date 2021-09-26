package unix.shell.expansion;

import java.util.Arrays;
import java.util.LinkedHashSet;

import unix.shell.cmd.arg.mod.ArgumentInterface;

/**
 * Brace expansion is a mechanism by which arbitrary strings may be generated.
 * 
 * <p/>
 * Brace expansions may be nested. The results of each expanded string are not
 * sorted; left to right order is preserved. For example,
 * 
 * <br/>
 * <b>bash$ echo a{d,c,b}e</b> gives <b>ade ace abe</b>
 * 
 * <p/>
 * Brace expansion is performed before any other expansions, and any characters
 * special to other expansions are preserved in the result. It is strictly
 * textual. Bash does not apply any syntactic interpretation to the context of
 * the expansion or the text between the braces.
 * 
 * <p/>
 * This construct is typically used as shorthand when the common prefix of the
 * strings to be generated is longer than in the above example: <br/>
 * <b>mkdir /usr/local/src/bash/{old,new,dist,bugs}</b>
 */
public class BraceExpansion extends ShellExpansion {

	private LinkedHashSet<ArgumentInterface> arguments;

	public BraceExpansion(ArgumentInterface... arguments) {
		this.arguments = new LinkedHashSet<ArgumentInterface>();
		addArgument(arguments);
	}

	public BraceExpansion addArgument(ArgumentInterface... arguments) {
		this.arguments.addAll(Arrays.asList(arguments));
		return this;
	}

	@Override
	public String correspond() throws Exception {

		if (arguments.isEmpty())
			return "{}";

		String correspond = "{";

		for (ArgumentInterface argument : arguments)
			correspond += argument.correspond() + ",";

		return correspond.substring(0, correspond.length() - 1) + "}";
	}
}
