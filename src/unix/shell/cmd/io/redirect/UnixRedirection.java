package unix.shell.cmd.io.redirect;

import unix.shell.cmd.mod.StrCorrespond;

public interface UnixRedirection extends StrCorrespond {

	public String rdDescriptor();

	public int IODescriptor();
}
