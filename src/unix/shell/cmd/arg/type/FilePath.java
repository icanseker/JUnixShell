package unix.shell.cmd.arg.type;

public class FilePath extends BasicPath {

	public FilePath(String path) throws Exception {
		super(path);
	}

	public static FilePath[] array(String... arguments) throws Exception {

		FilePath[] set = new FilePath[arguments.length];

		for (int i = 0; i < arguments.length; i++)
			set[i] = new FilePath(arguments[i]);

		return set;
	}

	public static final String CURRENT_DIR = ".";
}
