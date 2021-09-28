package unix.shell.redirect.mod;

import unix.shell.io.FileWrite;
import unix.shell.redirect.UnixRedirection;

public interface RDMapping {
	public void declareRedirection(UnixRedirection redirection);

	public void redirectStdOutAndStdErr(String destination, FileWrite writeType);
}
