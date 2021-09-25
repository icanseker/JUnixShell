package unix.shell.expansion;

import unix.shell.expansion.mod.ExpansionInterface;

/**
 * Expansion is performed on the command line after it has been split into
 * tokens. There are seven kinds of expansion performed:
 * 
 * <ul>
 * <li>brace expansion</li>
 * <li>tilde expansion</li>
 * <li>parameter and variable expansion</li>
 * <li>command substitution</li>
 * <li>arithmetic expansion</li>
 * <li>word splitting</li>
 * <li>filename expansion</li>
 * </ul>
 * 
 * <p/>
 * The order of expansions is: brace expansion; tilde expansion, parameter and
 * variable expansion, arithmetic expansion, and command substitution (done in a
 * left-to-right fashion); word splitting; and filename expansion.
 * 
 * <p/>
 * On systems that can support it, there is an additional expansion available:
 * process substitution. This is performed at the same time as tilde, parameter,
 * variable, and arithmetic expansion and command substitution.
 * 
 * <p/>
 * Only brace expansion, word splitting, and filename expansion can increase the
 * number of words of the expansion; other expansions expand a single word to a
 * single word.
 * 
 * <p/>
 * After the preceding expansions, all unquoted occurrences of the characters
 * ‘\’, ‘'’, and ‘"’ that did not result from one of the above expansions are
 * removed.
 */
public abstract class ShellExpansion implements ExpansionInterface {

	private int priority;

	public ShellExpansion() {

		String thisClass = this.getClass().getSimpleName();

		switch (thisClass) {
		case "BraceExpansion":
			this.priority = 1;
			break;

		case "TildeExpansion":
		case "ArithmeticExpansion":
		case "CommandSubstitution":
		case "ProcessSubstitution":
			this.priority = 2;
			break;

		case "WordSplitting":
			this.priority = 3;
			break;

		case "FilenameExpansion":
			this.priority = 4;
			break;

		default:
			this.priority = 5;
			break;
		}
	}

	@Override
	public final int priority() {
		return this.priority;
	}

	@Override
	public String toString() {
		return correspond();
	}
}
