package unix.shell.cmd.core.grep;

import unix.shell.cmd.opt.CommandLineOption;

public enum GrepOption implements CommandLineOption<GrepOption> {

	/**
	 * Use line buffering for standard output, regardless of output device. By
	 * default, standard output is line buffered for interactive devices, and is
	 * fully buffered otherwise. With full buffering, the output buffer is flushed
	 * when full; with line buffering, the buffer is also flushed after every output
	 * line. The buffer size is system dependent.
	 */
	LINE_BUFFERED("line-buffered"),

	/**
	 * On platforms that distinguish between text and binary I/O, use the latter
	 * when reading and writing files other than the user’s terminal, so that all
	 * input bytes are read and written as-is. This overrides the default behavior
	 * where grep follows the operating system’s advice whether to use text or
	 * binary I/O. On MS-Windows when grep uses text I/O it reads a carriage
	 * return–newline pair as a newline and a Control-Z as end-of-file, and it
	 * writes a newline as a carriage return–newline pair.
	 * 
	 * <p/>
	 * When using text I/O --byte-offset (-b) counts and --binary-files heuristics
	 * apply to input data after text-I/O processing. Also, the --binary-files
	 * heuristics need not agree with the --binary option; that is, they may treat
	 * the data as text even if --binary is given, or vice versa. See
	 * FileDirSelection options.
	 * 
	 * <p/>
	 * This option has no effect on GNU and other POSIX-compatible platforms, which
	 * do not distinguish text from binary I/O.
	 */
	BINARY('U', "binary"),

	/**
	 * Treat input and output data as sequences of lines, each terminated by a zero
	 * byte (the ASCII NUL character) instead of a newline. Like the -Z or --null
	 * option, this option can be used with commands like ‘sort -z’ to process
	 * arbitrary file names.
	 */
	NULL_DATA('z', "null-data"),

	;

	private final Character symbol;
	private final String paramName;

	private GrepOption(Character symbol, String paramName) {
		this.symbol = symbol;
		this.paramName = paramName;
	}

	private GrepOption(Character symbol) {
		this(symbol, null);
	}

	private GrepOption(String paramName) {
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
