package unix.shell.redirect;

import unix.shell.cmd.mod.CommandLine;
import unix.shell.operator.rd.RedirectionType;
import unix.shell.redirect.mod.Redirection;

/**
 * Before a command is executed, its input and output may be redirected using a
 * special notation interpreted by the shell.
 * 
 * <p/>
 * Redirection allows commands’ file handles to be duplicated, opened, closed,
 * made to refer to different files, and can change the files the command reads
 * from and writes to. Redirection may also be used to modify file handles in
 * the current shell execution environment. The following redirection operators
 * may precede or appear anywhere within a simple command or may follow a
 * command. Redirections are processed in the order they appear, from left to
 * right (Note that the order of redirections is significant).
 * 
 * <p/>
 * Each redirection that may be preceded by a file descriptor number may instead
 * be preceded by a word of the form {varname}. In this case, for each
 * redirection operator except >&- and <&-, the shell will allocate a file
 * descriptor greater than 10 and assign it to {varname}. If >&- or <&- is
 * preceded by {varname}, the value of varname defines the file descriptor to
 * close. If {varname} is supplied, the redirection persists beyond the scope of
 * the command, allowing the shell programmer to manage the file descriptor’s
 * lifetime manually.
 * 
 * <p/>
 * The word following the redirection operator in the following descriptions,
 * unless otherwise noted, is subjected to brace expansion, tilde expansion,
 * parameter expansion, command substitution, arithmetic expansion, quote
 * removal, filename expansion, and word splitting. If it expands to more than
 * one word, Bash reports an error. In the following descriptions, if the file
 * descriptor number is omitted, and the first character of the redirection
 * operator is ‘<’, the redirection refers to the standard input (file
 * descriptor 0). If the first character of the redirection operator is ‘>’, the
 * redirection refers to the standard output (file descriptor 1).
 */
public abstract class UnixRedirection implements Redirection {

	private RedirectionType rdType;
	private String rdArgument;

	protected UnixRedirection(RedirectionType rdType) {
		this.rdType = rdType;
	}

	protected void setRedirectArgument(String rdArgument) {
		this.rdArgument = rdArgument;
	}

	protected void setRedirectArgument(CommandLine commandLine) throws Exception {
		this.rdArgument = commandLine.correspond();
	}

	@Override
	public RedirectionType type() {
		return rdType;
	}

	@Override
	public String redirectArgument() {
		return this.rdArgument;
	}

	@Override
	public String correspond() {
		return rdType.symbol() + " " + rdArgument;
	}

	@Override
	public String toString() {
		return correspond();
	}
}