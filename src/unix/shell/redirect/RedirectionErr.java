package unix.shell.redirect;

import unix.shell.io.FileWrite;
import unix.shell.operator.rd.RedirectionType;

public class RedirectionErr extends UnixRedirection {

	public RedirectionErr(FileWrite writeType) {
		super(writeType == FileWrite.OVERWRITE ? RedirectionType.ERR_OVERWRITE : RedirectionType.ERR_APPEND);
	}

	public RedirectionErr(FileWrite writeType, String sourcePath) {
		this(writeType);
		setDestination(sourcePath);
	}

	public void setDestination(String sourcePath) {
		super.setRedirectArgument(sourcePath);
	}
}
