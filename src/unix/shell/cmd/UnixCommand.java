package unix.shell.cmd;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import unix.shell.cmd.arg.mod.ArgumentBehavior;
import unix.shell.cmd.arg.mod.ArgumentInterface;
import unix.shell.cmd.exitstat.StandardUnixExitStatus;
import unix.shell.cmd.exitstat.mod.ExitStatusInterface;
import unix.shell.cmd.mod.ClassIdentifier;
import unix.shell.cmd.mod.CommandLine;
import unix.shell.cmd.opt.UnixCommandOption;
import unix.shell.io.FileWrite;
import unix.shell.redirect.RedirectionMap;
import unix.shell.redirect.UnixRedirection;

public abstract class UnixCommand<CommandOption extends UnixCommandOption<CommandOption>>
		implements ClassIdentifier, ArgumentBehavior, CommandLine {

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
	 * Holds arguments of the command if it accepts arguments
	 */
	private LinkedHashSet<ArgumentInterface> arguments;

	/**
	 * Holds options of command, mapped by option (unique) id
	 */
	private LinkedHashMap<String, UnixCommandOption<CommandOption>> options;

	/**
	 * Holds arguments of option, mapped by option (unique) id
	 */
	private HashMap<String, Set<ArgumentInterface>> optionArguments;

	/**
	 * Each option implements OptionBehavior, which means it contains
	 * exclude/override/equalized option sets (maybe), which will be held here. If
	 * an option from the ignored set tries to be added to the command options set,
	 * it will be ignored.
	 */
	private HashSet<String> optionsIgnored; // String = unique id of option

	/**
	 * Redirections will be held here. And the order is critical (because of Unix
	 * shell behavior.
	 */
	private RedirectionMap redirectionMap;

	public UnixCommand(String name) {

		this.name = name;
		this.arguments = new LinkedHashSet<ArgumentInterface>();

		this.options = new LinkedHashMap<String, UnixCommandOption<CommandOption>>();
		this.optionArguments = new HashMap<String, Set<ArgumentInterface>>();
		this.optionsIgnored = new HashSet<String>();

		this.redirectionMap = new RedirectionMap();
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
	public void declareRedirection(UnixRedirection redirection) {
		this.redirectionMap.declareRedirection(redirection);
	}

	@Override
	public void redirectStdOutAndStdErr(String destination, FileWrite writeType) {
		this.redirectionMap.redirectStdOutAndStdErr(destination, writeType);
	}

	@Override
	public String correspond() throws Exception {

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

		if (!arguments.isEmpty()) {

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

			correspond += " " + commandArgs.substring(1);
		}

		String redirectCorr = redirectionMap.correspond();
		if (!redirectCorr.equals(""))
			correspond += " " + redirectCorr;

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
			return substitution();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
