package unix.shell.redirect;

import unix.shell.cmd.mod.CommandLine;
import unix.shell.operator.rd.RedirectionType;
import unix.shell.redirect.mod.Pipeline;

public class PipeErr extends UnixRedirection implements Pipeline {

	public PipeErr(CommandLine commandLine) throws Exception {
		super(RedirectionType.ERR_PIPE);
		setRedirectArgument(commandLine);
	}
}
