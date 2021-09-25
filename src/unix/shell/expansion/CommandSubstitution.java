package unix.shell.expansion;

import unix.shell.cmd.mod.CommandLine;

/**
 * Command substitution allows the output of a command to replace the command
 * itself. Command substitution occurs when a command is enclosed as follows:
 * <b>$(command)</b> or <b>`command`</b>
 * 
 * <p/>
 * Bash performs the expansion by executing command in a subshell environment
 * and replacing the command substitution with the standard output of the
 * command, with any trailing newlines deleted. Embedded newlines are not
 * deleted, but they may be removed during word splitting.
 * 
 * <p/>
 * When using the $(command) form, all characters between the parentheses make
 * up the command; none are treated specially.
 * 
 * <p/>
 * If the substitution appears within double quotes, word splitting and filename
 * expansion are not performed on the results.
 */
public class CommandSubstitution extends ShellExpansion {

	private String commandLine;

	public CommandSubstitution(CommandLine commandLine) throws Exception {
		this.commandLine = commandLine.commandLine();
	}

	@Override
	public String correspond() {
		return "$( " + commandLine + " )";
	}
}
