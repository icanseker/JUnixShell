package unix.cmd;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import unix.cmd.arg.mod.ArgumentBehavior;
import unix.cmd.arg.mod.ArgumentInterface;
import unix.cmd.arg.type.BasicPath;
import unix.cmd.exitstat.StandardUnixExitStatus;
import unix.cmd.exitstat.mod.ExitStatusInterface;
import unix.cmd.mod.CommandLine;
import unix.cmd.mod.redirect.RedirectInterface;
import unix.cmd.mod.redirect.RedirectOperand;
import unix.cmd.mod.redirect.RedirectOutType;
import unix.cmd.opt.mod.ClassIdentifier;
import unix.cmd.opt.mod.UnixCommandOption;

public abstract class UnixCommand<CommandOption extends UnixCommandOption<CommandOption>>
		implements ClassIdentifier, ArgumentBehavior, CommandLine, RedirectInterface {

	/**
	 * 
	 */
	private String name;

	/**
	 * Holds options of command, mapped by option (unique) id
	 */
	private LinkedHashMap<String, UnixCommandOption<CommandOption>> options;
	/**
	 * Holds arguments of option, mapped by option (unique) id
	 */
	private HashMap<String, Set<ArgumentInterface>> optionArguments;

	private LinkedHashSet<ArgumentInterface> arguments;

	private Map.Entry<RedirectOperand, BasicPath> redirectOutEntry;
	private Map.Entry<RedirectOperand, BasicPath> redirectInEntry;

	public static final Class<? extends ExitStatusInterface> exitStatusEnumSet = StandardUnixExitStatus.class;

	private HashSet<String> optionsIgnored; // String = unique id of option

	public UnixCommand(String name) {

		this.name = name;

		this.options = new LinkedHashMap<String, UnixCommandOption<CommandOption>>();
		this.optionArguments = new HashMap<String, Set<ArgumentInterface>>();
		this.arguments = new LinkedHashSet<ArgumentInterface>();

		this.optionsIgnored = new HashSet<String>();
	}

	protected void addOption(UnixCommandOption<CommandOption> option, ArgumentInterface... arguments) throws Exception {

		String optIdentifier = option.identifier();

		options.put(optIdentifier, option);

		if (arguments.length != 0 && option.acceptArgument()) {

			optionArguments.put(optIdentifier, new HashSet<ArgumentInterface>());

			if (!option.acceptMultiArgument())
				optionArguments.get(optIdentifier).add(arguments[0]);

			else
				optionArguments.get(optIdentifier).addAll(Arrays.asList(arguments));
		} else
			optionArguments.remove(optIdentifier);

		HashSet<UnixCommandOption<CommandOption>> optionsRelated = null;

		optionsRelated = option.optionsExcluded();
		if (optionsRelated != null)
			for (UnixCommandOption<?> optionExcluded : optionsRelated) {

				options.remove(optionExcluded.identifier());
				optionArguments.remove(optionExcluded.identifier());
			}

		optionsRelated = option.optionsOverridden();
		if (optionsRelated != null)
			for (UnixCommandOption<?> optionOverridden : optionsRelated)
				optionsIgnored.add(optionOverridden.identifier());

		optionsRelated = option.optionsEqualed();
		if (optionsRelated != null)
			for (UnixCommandOption<?> optionEqualed : optionsRelated)
				optionsIgnored.add(optionEqualed.identifier());

		optionsRelated = option.optionsRequired();
		if (optionsRelated != null)
			for (UnixCommandOption<CommandOption> optionRequired : optionsRelated)
				addOption(optionRequired);

		optionsRelated = null;
	}

	protected void addArgument(ArgumentInterface... arguments) throws Exception {

		String classId = this.classId();

		if (this.requiresArgument() && arguments.length == 0)
			throw new Exception(classId + ": requires an argument");

		if (this.acceptArgument() && arguments.length > 0) {

			if (!this.acceptMultiArgument() && arguments.length > 1) {
				System.err.println("WARNING: " + classId + " does not accept more than one argument");

				this.arguments.clear();
				this.arguments.add(arguments[0]);
			} else
				this.arguments.addAll(Arrays.asList(arguments));

		} else if (arguments.length > 0)
			System.err.println("WARNING: " + classId + " does not accept an argument");
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
	public void redirectInFrom(String filePath) throws Exception {
		this.redirectInEntry = //
				new AbstractMap.SimpleEntry<RedirectOperand, BasicPath>//
				(//
						RedirectOperand.IN, new BasicPath(filePath)//
				);
	}

	@Override
	public String commandLine() throws Exception {

		for (String optCorrespond : optionsIgnored) {
			options.remove(optCorrespond);
			optionArguments.remove(optCorrespond);
		}

		String correspond = this.name;

		for (Map.Entry<String, UnixCommandOption<CommandOption>> optionEntry : options.entrySet()) {

			UnixCommandOption<CommandOption> commandOption = optionEntry.getValue();
			String optionId = optionEntry.getKey();

			Set<ArgumentInterface> optionArgs = this.optionArguments.get(optionId);

			if (optionArgs != null)
				correspond += " "
						+ commandOption.correspond(optionArgs.toArray(new ArgumentInterface[optionArgs.size()]));
			else
				correspond += " " + commandOption.correspond();
		}

		String commandArgs = "";
		for (ArgumentInterface argument : arguments) {

			String commandArg = argument.correspond();

			/**
			 * Specify two dashes (––) to separate the options from the non option
			 * arguments; –– means that there are no more options. Thus, if you really have
			 * a directory named –t, you could specify: ls –– –t to list the contents of
			 * that directory.
			 */

			if (commandArg.startsWith("-") && !commandArgs.startsWith("--"))
				commandArgs = "--" + commandArgs;

			commandArgs += " " + commandArg;
		}

		if (!commandArgs.equals(""))
			correspond += " " + commandArgs.trim();

		if (redirectInEntry != null)
			correspond += " " + String.copyValueOf(redirectInEntry.getKey().symbol()) + " "
					+ redirectInEntry.getValue().correspond();

		if (redirectOutEntry != null)
			correspond += " " + String.copyValueOf(redirectOutEntry.getKey().symbol()) + " "
					+ redirectOutEntry.getValue().correspond();

		return correspond;
	}

	protected void resetOptions() {

		this.options.clear();
		this.optionArguments.clear();
		this.optionsIgnored.clear();
	}

	protected void resetArguments() {
		this.arguments.clear();
	}

	@Override
	public String toString() {
		try {
			return execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
