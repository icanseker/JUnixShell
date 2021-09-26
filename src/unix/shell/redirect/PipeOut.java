package unix.shell.redirect;

import unix.shell.cmd.mod.CommandLine;
import unix.shell.operator.rd.RedirectionType;
import unix.shell.redirect.mod.Pipeline;

public class PipeOut extends UnixRedirection implements Pipeline {

	public PipeOut(CommandLine commandLine) throws Exception {
		super(RedirectionType.OUT_PIPE);
		setRedirectArgument(commandLine);
	}
}
