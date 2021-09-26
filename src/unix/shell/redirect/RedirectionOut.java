package unix.shell.redirect;

import unix.shell.io.FileWrite;
import unix.shell.operator.rd.RedirectionType;

public class RedirectionOut extends UnixRedirection {

	public RedirectionOut(FileWrite writeType) {
		super(writeType == FileWrite.OVERWRITE ? RedirectionType.OUT_OVERWRITE : RedirectionType.OUT_APPEND);
	}

	public RedirectionOut(FileWrite writeType, String sourcePath) {
		this(writeType);
		setDestination(sourcePath);
	}

	public void setDestination(String sourcePath) {
		super.setRedirectArgument(sourcePath);
	}
}
