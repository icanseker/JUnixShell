package unix.shell.cmd.io.redirect.mod;

import unix.shell.cmd.io.redirect.UnixRedirection;

/**
 * If the redirection operator is ‘>’, and the noclobber option to the set
 * builtin has been enabled, the redirection will fail if the file whose name
 * results from the expansion of word exists and is a regular file. If the
 * redirection operator is ‘>|’, or the redirection operator is ‘>’ and the
 * noclobber option is not enabled, the redirection is attempted even if the
 * file named by word exists.
 * 
 * <p/>
 * >| same as > except that the file is truncated to zero length if it exists,
 * regardless of CLOBBER.
 * 
 * <p/>
 * >>| same as >>, except that the file is created if it does not exist,
 * regardless of CLOBBER and APPEND_CREATE.
 */
public interface SafeRedirection {

	public UnixRedirection safety(boolean safety);

	public default boolean isSafe() {
		return false;
	}
}
