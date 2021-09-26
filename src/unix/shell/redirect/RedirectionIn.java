package unix.shell.redirect;

import unix.shell.operator.rd.RedirectionType;

public class RedirectionIn extends UnixRedirection {

	public RedirectionIn(String sourcePath) {
		super(RedirectionType.IN);
		super.setRedirectArgument(sourcePath);
	}
}
