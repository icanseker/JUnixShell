package unix.shell.cmd;

import unix.shell.cmd.arg.mod.ArgumentAct;
import unix.shell.cmd.arg.mod.ArgumentInterface;
import unix.shell.cmd.exitstat.StandardUnixExitStatus;
import unix.shell.cmd.exitstat.mod.ExitStatusInterface;
import unix.shell.cmd.io.redirect.RedirectionMap;
import unix.shell.cmd.io.redirect.UnixRedirection;
import unix.shell.cmd.mod.ClassIdentifier;
import unix.shell.cmd.mod.CommandLine;
import unix.shell.cmd.opt.CommandLineOption;
import unix.shell.cmd.opt.OptionMap;
import unix.shell.cmd.outline.FieldMap;

/**
 * Abstract definition of general Unix Command.
 */
public abstract class UnixCommand<OptionForm extends CommandLineOption<OptionForm>>
		implements ClassIdentifier, CommandLine {

	/**
	 * The official name of this program.
	 */
	private String name;

	/**
	 * Each command has an exit status-reason map, this will be Standard Exit status
	 * if not specified.
	 * 
	 * <p/>
	 * ExitStatDetector gets command exit status set and check if any reason defined
	 * on exit status.
	 */
	public static final Class<? extends ExitStatusInterface> exitStatusEnumSet = StandardUnixExitStatus.class;

	/**
	 * Default command synopsis is <b>COMMAND [OPTION]...</b> (multiple optional
	 * options)
	 */
	private static final ArgumentAct DEFAULT_OPTION_ACT = new ArgumentAct() {
		@Override
		public boolean require() {
			return false;
		}

		@Override
		public boolean optional() {
			return true;
		}

		@Override
		public boolean multiple() {
			return true;
		}
	};

	/**
	 * Holds options of command and arguments of these options
	 */
	private OptionMap<OptionForm> optionMap;

	/**
	 * Holds arguments of each field of the command, mapped by argument group
	 * (grouped by argument function)
	 */
	private FieldMap fieldMap;

	/**
	 * Redirections will be held here. And the order is critical (because of Unix
	 * shell behavior.
	 */
	private RedirectionMap redirectionMap;

	public UnixCommand(String name) {
		this.name = name;
		this.optionMap = new OptionMap<OptionForm>();
		this.fieldMap = synopsis();
		this.redirectionMap = new RedirectionMap();
	}

	protected ArgumentAct optionAct() {
		return UnixCommand.DEFAULT_OPTION_ACT;
	}

	/**
	 * field map demonstrate command synopsis (except options)
	 */
	protected abstract FieldMap synopsis();

	protected void addOption(CommandLineOption<OptionForm> option, ArgumentInterface argument) throws Exception {

		ArgumentAct optionAct = this.optionAct();

		if (!optionAct.require() && !optionAct.optional())
			System.err.println("WARNING: " + this.name + " does not accept any options.");
		else
			this.optionMap.addOption(option, argument);
	}

	protected void addOption(CommandLineOption<OptionForm> option) throws Exception {
		addOption(option, null);
	}

	protected void addArgumentGroup(String groupId, ArgumentAct groupAct) {
		if (this.fieldMap != null)
			this.fieldMap.addArgumentGroup(groupId, groupAct);
	}

	protected void addArgument(String groupId, ArgumentInterface... arguments) throws Exception {
		if (this.fieldMap != null)
			this.fieldMap.addArgument(groupId, arguments);
	}

	protected void removeArgumentGroup(String groupId) {
		if (this.fieldMap != null)
			this.fieldMap.removeArgumentGroup(groupId);
	}

	protected void resetArgumentGroup(String groupId, ArgumentAct groupAct) {
		if (this.fieldMap != null)
			this.fieldMap.resetArgumentGroup(groupId, groupAct);
	}

	protected void updateFieldMap(FieldMap fieldMap) {
		this.fieldMap = fieldMap;
	}

	@Override
	public void declareRedirection(UnixRedirection redirection) {
		this.redirectionMap.declareRedirection(redirection);
	}

	@Override
	public void openFile2ReadWrite(int fileDescriptor, String filePath) {
		this.redirectionMap.openFile2ReadWrite(fileDescriptor, filePath);
	}

	@Override
	public String correspond() throws Exception {

		String correspond = this.name;
		String optCorrespond = this.optionMap.correspond();

		if (!optCorrespond.equals(""))
			correspond += " " + optCorrespond;

		if (this.fieldMap != null) {

			String fieldMapCorr = this.fieldMap.correspond();
			if (!fieldMapCorr.equals("")) {

				if (fieldMapCorr.startsWith("-") && !fieldMapCorr.startsWith("--"))
					fieldMapCorr = "--" + fieldMapCorr;

				correspond += " " + fieldMapCorr;
			}
		}

		String redirectCorr = redirectionMap.correspond();
		if (!redirectCorr.equals(""))
			correspond += " " + redirectCorr;

		return correspond;
	}

	protected void resetOptions() {
		this.optionMap = new OptionMap<OptionForm>();
	}

	public String synopsisCorrespond() {

		String corr = this.name;

		String optCorr = ArgumentAct.actCorrespond("OPTION", optionAct());
		if (!optCorr.equals(""))
			corr += " " + optCorr;

		if (this.fieldMap != null) {
			String argCorr = this.fieldMap.fieldMapCorrespond();
			if (!argCorr.equals(""))
				corr += " " + argCorr;
		}

		return corr;
	}

	@Override
	public String toString() {
		try {
			return substitution();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
