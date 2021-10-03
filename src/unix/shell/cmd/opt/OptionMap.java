package unix.shell.cmd.opt;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import unix.shell.cmd.arg.mod.ArgumentInterface;
import unix.shell.cmd.mod.StrCorrespond;

public class OptionMap<OptionForm extends CommandLineOption<OptionForm>> implements StrCorrespond {

	/**
	 * Holds options of command, mapped by option (unique) id, because of singular
	 * options' ids are the same, it will override each other.
	 */
	private LinkedHashMap<String, CommandLineOption<OptionForm>> options;

	/**
	 * Holds argument of option, mapped by option (unique) id. As default, an option
	 * is a variable, so an option can take only one argument
	 */
	private HashMap<String, ArgumentInterface> arguments;

	/**
	 * Each option implements OptionBehavior, which means it contains
	 * exclude/override/equalized option sets (maybe), which will be held here. If
	 * an option from the ignored set tries to be added to the command option map,
	 * it will be ignored.
	 * 
	 * <p/>
	 * if an option is singular and is ignored, then all other options have same id
	 * will be ignored. if we don't need this, we can change this.
	 */
	private HashSet<String> optionsIgnored; // mapped by unique id of option

	public OptionMap() {
		options = new LinkedHashMap<String, CommandLineOption<OptionForm>>();
		arguments = new HashMap<String, ArgumentInterface>();
		optionsIgnored = new LinkedHashSet<String>();
	}

	public void addOption(CommandLineOption<OptionForm> option, ArgumentInterface argument) throws Exception {

		String descriptor = option.descriptor();

		if (argument == null) {
			if (option.requireArgument())
				throw new Exception(descriptor + ": requires an argument");
		} else {

			/*
			 * if the option is Singular, it is identifier will be singular identifier
			 */
			options.put(option.identifier(), option);

			if (option.takeOptionalArgument() || option.requireArgument())
				arguments.put(option.identifier(), argument);

			else
				System.err.println("WARNING: " + descriptor + " does not accept an argument");
		}

		// work on option interaction

		HashSet<CommandLineOption<OptionForm>> optionsRelated = null;

		optionsRelated = option.optionsExcluded();
		if (optionsRelated != null)
			for (CommandLineOption<?> optionExcluded : optionsRelated) {

				options.remove(optionExcluded.identifier());
				arguments.remove(optionExcluded.identifier());
			}

		optionsRelated = option.optionsOverridden();
		if (optionsRelated != null)
			for (CommandLineOption<?> optionOverridden : optionsRelated)
				optionsIgnored.add(optionOverridden.identifier());

		optionsRelated = option.optionsEqualed();
		if (optionsRelated != null)
			for (CommandLineOption<?> optionEqualed : optionsRelated)
				optionsIgnored.add(optionEqualed.identifier());

		optionsRelated = option.optionsRequired();
		if (optionsRelated != null)
			for (CommandLineOption<OptionForm> optionRequired : optionsRelated)
				addOption(optionRequired, null);

		optionsRelated = null;
	}

	public void addOption(CommandLineOption<OptionForm> option) throws Exception {
		addOption(option, null);
	}

	@Override
	public String correspond() throws Exception {

		for (String optId : optionsIgnored) {
			options.remove(optId);
			arguments.remove(optId);
		}

		String corr = "";

		for (CommandLineOption<?> option : options.values()) {

			corr += " " + option.descriptor();

			String optIdentifier = option.identifier();
			if (arguments.containsKey(optIdentifier))
				corr += " " + arguments.get(optIdentifier).correspond();
		}

		if (corr.equals(""))
			return "";

		return corr.substring(1);
	}
}