package unix.shell.redirect.mod;

import unix.shell.cmd.mod.StrCorrespond;
import unix.shell.operator.rd.RedirectionType;

public interface Redirection extends StrCorrespond {

	public RedirectionType type();

	public String redirectArgument();
}
