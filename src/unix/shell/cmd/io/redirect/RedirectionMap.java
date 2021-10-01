package unix.shell.cmd.io.redirect;

import java.util.LinkedHashMap;

import unix.shell.cmd.arg.type.FilePath;
import unix.shell.cmd.io.redirect.mod.IOMapping;
import unix.shell.cmd.mod.StrCorrespond;

/**
 * Before a command is executed, its input and output may be redirected using a
 * special notation interpreted by the shell. Redirection allows commands’ file
 * handles to be duplicated, opened, closed, made to refer to different files,
 * and can change the files the command reads from and writes to. Redirection
 * may also be used to modify file handles in the current shell execution
 * environment.
 * 
 * <p/>
 * In the following descriptions, if the file descriptor number is omitted, and
 * the first character of the redirection operator is ‘<’, the redirection
 * refers to the standard input (file descriptor 0). If the first character of
 * the redirection operator is ‘>’, the redirection refers to the standard
 * output (file descriptor 1).
 * 
 * <p/>
 * The word following the redirection operator in the following descriptions,
 * unless otherwise noted, is subjected to brace expansion, tilde expansion,
 * parameter expansion, command substitution, arithmetic expansion, quote
 * removal, filename expansion, and word splitting. If it expands to more than
 * one word, Bash reports an error.
 * 
 * <p/>
 * Note that the order of redirections is significant. For example, the command
 * <b>ls > dirlist 2>&1</b> directs both standard output (file descriptor 1) and
 * standard error (file descriptor 2) to the file dirlist, while the command
 * <b>ls 2>&1 > dirlist</b> directs only the standard output to file dirlist,
 * because the standard error was made a copy of the standard output before the
 * standard output was redirected to dirlist.
 */
public class RedirectionMap implements IOMapping, StrCorrespond {

	/**
	 * Held redirections as mapped by redirection rdDescriptor
	 */
	private LinkedHashMap<String, UnixRedirection> rdMap;

	public RedirectionMap() {
		this.rdMap = new LinkedHashMap<String, UnixRedirection>();
	}

	@Override
	public void declareRedirection(UnixRedirection redirection) {

		String rId = redirection.rdDescriptor();

		if (rId != null) {

			if (redirection instanceof RedirectOutErr) {
				rdMap.remove(">");
				rdMap.remove("1>");
				rdMap.remove("2>");
			}

			else if (redirection.rdDescriptor().endsWith("<>")) // openFile2ReadWrite
				rdMap.remove(redirection.IODescriptor() + ">");

			rdMap.remove(rId); // ! the order of redirections is significant
			rdMap.put(rId, redirection);
		}
	}

	/**
	 * The redirection operator
	 * 
	 * <b>[n]<>word</b>
	 * 
	 * causes the file whose name is the expansion of word to be opened for both
	 * reading and writing on file descriptor n, or on file descriptor 0 if n is not
	 * specified. If the file does not exist, it is created.
	 */
	@Override
	public void openFile2ReadWrite(int fileDescriptor, String filePath) {

		UnixRedirection rOpen = new UnixRedirection() {
			@Override
			public String correspond() throws Exception {
				return rdDescriptor() + " " + new FilePath(filePath);
			}

			@Override
			public String rdDescriptor() {
				return fileDescriptor + "<>";
			}

			@Override
			public int IODescriptor() {
				return fileDescriptor;
			}
		};

		declareRedirection(rOpen);
	}

	@Override
	public String correspond() throws Exception {

		String correspond = "";

		for (UnixRedirection redirection : rdMap.values())
			correspond += " " + redirection.correspond();

		if (correspond.startsWith(" "))
			return correspond.substring(1);

		return correspond;
	}
}
