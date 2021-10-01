package unix.shell.cmd.io.redirect.pipe.mod;

import unix.shell.cmd.UnixCommand;

public interface PipeMapping {

	public void pipe(Pipeline pipeline);

	public default void pipe(UnixCommand<?> destination) {
		pipe(new Pipeline(destination));
	}

	public default void pipe(UnixCommand<?> destination, boolean pipeErr) {
		pipe(new Pipeline(destination).redirectErrAlso(pipeErr));
	}
}
