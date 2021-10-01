package unix.shell.cmd.io.redirect.pipe;

import java.util.LinkedHashSet;

import unix.shell.cmd.UnixCommand;
import unix.shell.cmd.io.redirect.pipe.mod.PipeMapping;
import unix.shell.cmd.io.redirect.pipe.mod.Pipeline;
import unix.shell.cmd.mod.StrCorrespond;

public class PipeChain implements PipeMapping, StrCorrespond {

	private LinkedHashSet<Pipeline> chain;
	private UnixCommand<?> source;

	public PipeChain(UnixCommand<?> source) {
		chain = new LinkedHashSet<Pipeline>();
		this.source = source;
	}

	@Override
	public void pipe(Pipeline pipeline) {
		this.chain.add(pipeline);
	}

	@Override
	public String correspond() throws Exception {

		String corr = this.source.correspond();

		for (Pipeline pipeline : this.chain)
			corr += " " + pipeline.correspond();

		return corr;
	}
}
