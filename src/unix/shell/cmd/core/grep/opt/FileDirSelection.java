package unix.shell.cmd.core.grep.opt;

import unix.shell.cmd.opt.CommandLineOption;

/**
 * all for GNU-grep
 */
public enum FileDirSelection implements CommandLineOption<GrepOption> {

	/**
	 * Process a binary file as if it were text; this is equivalent to the
	 * ‘--binary-files=text’ option.
	 */
	TEXT('a', "text"),

	/**
	 * --binary-files=type
	 * 
	 * <p/>
	 * If a file’s data or metadata indicate that the file contains binary data,
	 * assume that the file is of type "type". Non-text bytes indicate binary data;
	 * these are either output bytes that are improperly encoded for the current
	 * locale, or null input bytes when the -z (--null-data) option is not given.
	 * 
	 * <p/>
	 * By default, type is ‘binary’, and grep suppresses output after null input
	 * binary data is discovered, and suppresses output lines that contain
	 * improperly encoded data. When some output is suppressed, grep follows any
	 * output with a one-line message saying that a binary file matches.
	 * 
	 * <p/>
	 * If type is ‘without-match’, when grep discovers null input binary data it
	 * assumes that the rest of the file does not match; this is equivalent to the
	 * -I option.
	 * 
	 * <p/>
	 * If type is ‘text’, grep processes binary data as if it were text; this is
	 * equivalent to the -a option.
	 * 
	 * <p/>
	 * When type is ‘binary’, grep may treat non-text bytes as line terminators even
	 * without the -z (--null-data) option. This means choosing ‘binary’ versus
	 * ‘text’ can affect whether a pattern matches a file. For example, when type is
	 * ‘binary’ the pattern ‘q$’ might match ‘q’ immediately followed by a null
	 * byte, even though this is not matched when type is ‘text’. Conversely, when
	 * type is ‘binary’ the pattern ‘.’ (period) might not match a null byte.
	 * 
	 * <p/>
	 * Warning: The -a (--binary-files=text) option might output binary garbage,
	 * which can have nasty side effects if the output is a terminal and if the
	 * terminal driver interprets some of it as commands. On the other hand, when
	 * reading files whose text encodings are unknown, it can be helpful to use -a
	 * or to set ‘LC_ALL='C'’ in the environment, in order to find more matches even
	 * if the matches are unsafe for direct display.
	 */
	BINARY_FILES("binary-files") {
		@Override
		public boolean requireArgument() {
			return true;
		}
	},

	/**
	 * If an input file is a device, FIFO, or socket, use action to process it. If
	 * action is ‘read’, all devices are read just as if they were ordinary files.
	 * If action is ‘skip’, devices, FIFOs, and sockets are silently skipped. By
	 * default, devices are read if they are on the command line or if the -R
	 * (--dereference-recursive) option is used, and are skipped if they are
	 * encountered recursively and the -r (--recursive) option is used. This option
	 * has no effect on a file that is read via standard input.
	 */
	DEVICE_ACTION('D', "devices") {
		@Override
		public boolean requireArgument() {
			return true;
		}
	},

	/**
	 * If an input file is a directory, use action to process it. By default, action
	 * is ‘read’, which means that directories are read just as if they were
	 * ordinary files (some operating systems and file systems disallow this, and
	 * will cause grep to print error messages for every directory or silently skip
	 * them). If action is ‘skip’, directories are silently skipped. If action is
	 * ‘recurse’, grep reads all files under each directory, recursively, following
	 * command-line symbolic links and skipping other symlinks; this is equivalent
	 * to the -r option.
	 */
	DIRECTORY_ACTION('d', "directories") {
		@Override
		public boolean requireArgument() {
			return true;
		}
	},

	/**
	 * Skip any command-line file with a name suffix that matches the pattern glob,
	 * using wildcard matching; a name suffix is either the whole name, or a
	 * trailing part that starts with a non-slash character immediately after a
	 * slash (‘/’) in the name. When searching recursively, skip any subfile whose
	 * base name matches glob; the base name is the part after the last slash. A
	 * pattern can use ‘*’, ‘?’, and ‘[’...‘]’ as wildcards, and \ to quote a
	 * wildcard or backslash character literally.
	 */
	EXLUDE_GLOB("exclude") {
		@Override
		public boolean requireArgument() {
			return true;
		}
	},

	/**
	 * Skip files whose name matches any of the patterns read from file (using
	 * wildcard matching as described under --exclude).
	 */
	EXCLUDE_FROM_FILE("exclude-from") {
		@Override
		public boolean requireArgument() {
			return true;
		}
	},

	/**
	 * Skip any command-line directory with a name suffix that matches the pattern
	 * glob. When searching recursively, skip any subdirectory whose base name
	 * matches glob. Ignore any redundant trailing slashes in glob.
	 */
	EXCLUDE_DIR("exclude-dir") {
		@Override
		public boolean requireArgument() {
			return true;
		}
	},

	/**
	 * Process a binary file as if it did not contain matching data; this is
	 * equivalent to the ‘--binary-files=without-match’ option.
	 */
	PROCESS_BINARY_FILE_DIDNOT_CONTAIN_MATCH('I'),

	/**
	 * Search only files whose name matches glob, using wildcard matching as
	 * described under --exclude. If contradictory --include and --exclude options
	 * are given, the last matching one wins. If no --include or --exclude options
	 * match, a file is included unless the first such option is --include.
	 */
	INCLUDE_GLOB("include") {
		@Override
		public boolean requireArgument() {
			return true;
		}
	},

	/**
	 * For each directory operand, read and process all files in that directory,
	 * recursively. Follow symbolic links on the command line, but skip symlinks
	 * that are encountered recursively. Note that if no file operand is given, grep
	 * searches the working directory. This is the same as the
	 * ‘--directories=recurse’ option.
	 */
	RECURSIVE('r', "recursive"),

	/**
	 * For each directory operand, read and process all files in that directory,
	 * recursively, following all symbolic links.
	 */
	DEREFERENCE_RECURSIVE('R', "dereference-recursive"),

	;

	private final Character symbol;
	private final String paramName;

	private FileDirSelection(Character symbol, String paramName) {
		this.symbol = symbol;
		this.paramName = paramName;
	}

	private FileDirSelection(Character symbol) {
		this(symbol, null);
	}

	private FileDirSelection(String paramName) {
		this(null, paramName);
	}

	@Override
	public Character symbol() {
		return this.symbol;
	}

	@Override
	public String paramName() {
		return this.paramName;
	}
}
