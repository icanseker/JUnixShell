package unix.shell.redirect;

import unix.shell.io.FileWrite;
import unix.shell.operator.rd.RedirectionType;

public class RedirectionOutErr extends UnixRedirection {

	public RedirectionOutErr(FileWrite writeType) {
		super(writeType == FileWrite.OVERWRITE ? RedirectionType.OUT_ERR_OVERWRITE : RedirectionType.OUT_ERR_APPEND);
	}

	public RedirectionOutErr(FileWrite writeType, String sourcePath) {
		this(writeType);
		setDestination(sourcePath);
	}

	public void setDestination(String sourcePath) {
		super.setRedirectArgument(sourcePath);
	}

	public RedirectionOut toggleOut() {
		FileWrite writeType = this.type() == RedirectionType.OUT_ERR_OVERWRITE ? FileWrite.OVERWRITE : FileWrite.APPEND;
		return new RedirectionOut(writeType, this.redirectArgument());
	}

	public RedirectionErr toggleErr() {
		FileWrite writeType = this.type() == RedirectionType.OUT_ERR_OVERWRITE ? FileWrite.OVERWRITE : FileWrite.APPEND;
		return new RedirectionErr(writeType, this.redirectArgument());
	}
}
