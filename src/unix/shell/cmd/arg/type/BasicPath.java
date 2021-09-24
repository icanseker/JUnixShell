package unix.shell.cmd.arg.type;

import unix.valid.FileValidator;

public class BasicPath extends Text {

	public BasicPath(String path) throws Exception {
		super(path);
	}

	@Override
	public boolean formValidator(String correspond) throws Exception {
		return FileValidator.isValidFilePath(correspond);
	}
}
