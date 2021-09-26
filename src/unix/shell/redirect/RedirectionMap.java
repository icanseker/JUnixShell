package unix.shell.redirect;

import java.util.LinkedHashSet;

import unix.shell.cmd.mod.StrCorrespond;
import unix.shell.redirect.mod.Pipeline;
import unix.shell.redirect.mod.Redirection;

/**
 * Redirections will be held here. And the order is critical (because of Unix
 * shell behavior, but this library standardized the order as in, out, err,
 * pipes --pipes adding in order of definition).
 */
public class RedirectionMap implements StrCorrespond {

	private RedirectionIn in;
	private RedirectionOut out;
	private RedirectionErr err;
	private RedirectionOutErr outErr;

	/**
	 * Pipes will be held here with adding in order of definition
	 */
	private LinkedHashSet<Pipeline> pipes;

	public RedirectionMap() {
		this.pipes = new LinkedHashSet<Pipeline>();
	}

	public void addRedirection(Redirection redirection) {

		String rClass = redirection.getClass().getSimpleName();
		switch (rClass) {
		case "RedirectionIn":
			addRedirection((RedirectionIn) redirection);
			break;

		case "RedirectionOut":
			addRedirection((RedirectionOut) redirection);
			break;

		case "RedirectionErr":
			addRedirection((RedirectionErr) redirection);
			break;

		case "RedirectionOutErr":
			addRedirection((RedirectionOutErr) redirection);
			break;

		case "PipeOut":
		case "PipeErr":
			addRedirection((Pipeline) redirection);
			break;

		default:
			break;
		}
	}

	public void addRedirection(RedirectionIn in) {
		this.in = in;
	}

	public void addRedirection(RedirectionOut out) {
		this.out = out;

		/**
		 * Turns RedirectOutErr to RedirectErr with same redirect argument
		 */
		if (outErr != null) {
			this.err = outErr.toggleErr();
			this.outErr = null;
		}
	}

	public void addRedirection(RedirectionErr err) {
		this.err = err;

		/**
		 * Turns RedirectOutErr to RedirectOut with same redirect argument
		 */
		if (outErr != null) {
			this.out = outErr.toggleOut();
			this.outErr = null;
		}
	}

	/**
	 * OutErr redirection will exclude Out and Err redirection.
	 */
	public void addRedirection(RedirectionOutErr outErr) {
		this.outErr = outErr;

		this.out = null;
		this.err = null;
	}

	public void addRedirection(Pipeline pipe) {
		pipes.add(pipe);
	}

	@Override
	public String correspond() throws Exception {

		String correspond = "";

		if (this.in != null)
			correspond += " " + in.correspond();

		if (this.outErr != null)
			correspond += " " + outErr.correspond();

		else {
			if (this.out != null)
				correspond += " " + out.correspond();

			if (this.err != null)
				correspond += " " + err.correspond();
		}

		for (Pipeline pipeline : pipes)
			correspond += " " + pipeline.correspond();

		if (correspond.equals(""))
			return correspond;

		return correspond.substring(1);
	}
}
