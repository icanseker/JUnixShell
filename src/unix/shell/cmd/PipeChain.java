package unix.shell.cmd;

import java.util.AbstractMap;
import java.util.LinkedHashSet;
import java.util.Map;

import unix.shell.cmd.arg.type.BasicPath;
import unix.shell.cmd.mod.CommandLine;
import unix.shell.cmd.mod.redirect.RedirectOperand;
import unix.shell.cmd.mod.redirect.RedirectOutInterface;
import unix.shell.cmd.mod.redirect.RedirectOutType;

public class PipeChain implements CommandLine, RedirectOutInterface {

	private LinkedHashSet<UnixCommand<?>> pipeChain;

	private Map.Entry<RedirectOperand, BasicPath> redirectOutEntry;

	public PipeChain(UnixCommand<?> outputSupplier) {
		this.pipeChain = new LinkedHashSet<UnixCommand<?>>();
		pipeline(outputSupplier);
	}

	public void pipeline(UnixCommand<?> unixCommand) {
		this.pipeChain.add(unixCommand);
	}

	@Override
	public void redirectOutTo(String filePath, RedirectOutType redirectType) throws Exception {
		this.redirectOutEntry = //
				new AbstractMap.SimpleEntry<RedirectOperand, BasicPath>//
				(//
						redirectType == RedirectOutType.APPEND_FILE ? RedirectOperand.APPEND_OUT
								: RedirectOperand.OVERRIDE_OUT, //
						new BasicPath(filePath)//
				);
	}

	@Override
	public String commandLine() {

		String correspond = "";

		for (UnixCommand<?> unixCommand : pipeChain)
			correspond += "| " + unixCommand + " ";

		correspond = correspond.substring(2, correspond.length() - 1);

		if (redirectOutEntry != null)
			correspond += " " + String.copyValueOf(redirectOutEntry.getKey().symbol()) + " "
					+ redirectOutEntry.getValue().correspond();

		return correspond;
	}

	@Override
	public String toString() {
		return commandLine();
	}
}
