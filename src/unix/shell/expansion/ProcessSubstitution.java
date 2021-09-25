package unix.shell.expansion;

/**
 * Process substitution allows a processís input or output to be referred to
 * using a filename. It takes the form of <b><(list)</b> or <b>>(list)</b>
 * 
 * <p/>
 * The process list is run asynchronously, and its input or output appears as a
 * filename. This filename is passed as an argument to the current command as
 * the result of the expansion. If the >(list) form is used, writing to the file
 * will provide input for list. If the <(list) form is used, the file passed as
 * an argument should be read to obtain the output of list. Note that no space
 * may appear between the < or > and the left parenthesis, otherwise the
 * construct would be interpreted as a redirection. Process substitution is
 * supported on systems that support named pipes (FIFOs) or the /dev/fd method
 * of naming open files.
 * 
 * <p/>
 * When available, process substitution is performed simultaneously with
 * parameter and variable expansion, command substitution, and arithmetic
 * expansion.
 */
public class ProcessSubstitution extends ShellExpansion {

	@Override
	public String correspond() {
		return null;
	}
}
