package unix.shell.cmd.outline;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import unix.shell.cmd.arg.mod.ArgumentAct;
import unix.shell.cmd.arg.mod.ArgumentInterface;
import unix.shell.cmd.mod.StrCorrespond;
import unix.shell.cmd.opt.OptionParameter;

public class FieldMap implements StrCorrespond {

	/**
	 * Holds behavior of argument field groups, note that order is crucial.
	 */
	private LinkedHashMap<String, ArgumentAct> actMap;

	/**
	 * Holds arguments of the field, mapped by field group, note that order is
	 * crucial.
	 */
	private HashMap<String, LinkedHashSet<ArgumentInterface>> argumentMap;

	public FieldMap() {
		actMap = new LinkedHashMap<String, ArgumentAct>();
		argumentMap = new LinkedHashMap<String, LinkedHashSet<ArgumentInterface>>();
	}

	/**
	 * if argument field group is exist, it will change nothing.
	 */
	public void addArgumentGroup(String groupId, ArgumentAct groupAct) {

		if (!this.actMap.containsKey(groupId))
			this.actMap.put(groupId, groupAct);
		else
			System.err.println("WARNING: Already have an argument group: " + groupId);
	}

	/**
	 * if argument group is exist, it will be deleted with all arguments had been
	 * added.
	 */
	public void removeArgumentGroup(String groupId) {
		this.actMap.remove(groupId);
		this.argumentMap.remove(groupId);
	}

	/**
	 * if argument field group is exist, it will re-defined, all arguments added
	 * before will be deleted.
	 * 
	 * <p/>
	 * if argument field group is not exist, it will be defined.
	 */
	public void resetArgumentGroup(String groupId, ArgumentAct groupAct) {

		if (this.actMap.containsKey(groupId)) {
			removeArgumentGroup(groupId);
			System.err.println("WARNING: Argument field group has been reset: " + groupId);
		} else
			this.actMap.put(groupId, groupAct);
	}

	public void addArgument(String groupId, ArgumentInterface... arguments) throws Exception {

		if (arguments.length > 0) {

			if (this.actMap.containsKey(groupId)) {

				ArgumentAct act = this.actMap.get(groupId);

				if (act.equals(ArgumentAct.NONE))
					System.err.println("WARNING: " + groupId + " does not accept argument");
				else {

					if (!this.argumentMap.containsKey(groupId))
						this.argumentMap.put(groupId, new LinkedHashSet<ArgumentInterface>());

					if (!act.multiple()) {
						this.argumentMap.get(groupId).add(arguments[0]);
						System.err.println("WARNING: " + groupId + " does not accept more then one argument");
					} else
						this.argumentMap.get(groupId).addAll(Arrays.asList(arguments));
				}

			} else
				System.err.println("WARNING: Does not have an argument group: " + groupId);
		}
	}

	@Override
	public String correspond() throws Exception {

		String corr = "";

		/*
		 * We need to use a double-dash -- anytime our non-option arguments start with a
		 * hyphen. If we don't terminate option processing, commands will try to
		 * interpret non-option arguments as options, and most likely fail. So that, we
		 * have to check if the first argument is OptionParameter or not.
		 */
		Class<?> firstArgClass = null;

		/*
		 * Do not break the order
		 */
		for (Map.Entry<String, ArgumentAct> groupEntry : this.actMap.entrySet()) {

			String groupId = groupEntry.getKey();
			ArgumentAct argumentAct = groupEntry.getValue();
			LinkedHashSet<ArgumentInterface> groupArgs = this.argumentMap.get(groupId);

			if (groupArgs == null) {
				if (argumentAct.require()) {
					if (argumentAct.multiple())
						throw new Exception("Field group of \"" + groupId + "\" requires at least one argument");
					throw new Exception("Field group of \"" + groupId + "\" requires an argument");
				}
			} else {
				for (ArgumentInterface argument : this.argumentMap.get(groupId)) {
					corr += " " + argument.correspond();

					if (firstArgClass == null)
						firstArgClass = argument.getClass();
				}
			}
		}

		if (corr.equals(""))
			return "";

		corr = corr.substring(1);

		if (!firstArgClass.equals(OptionParameter.class)) {
			if (corr.startsWith("-") && !corr.startsWith("-- "))
				corr = "-- " + corr;
		}

		return corr;
	}

	public String fieldMapCorrespond() {

		String corr = "";

		for (Map.Entry<String, ArgumentAct> actEntry : this.actMap.entrySet())
			corr += " " + ArgumentAct.actCorrespond(actEntry.getKey(), actEntry.getValue());

		if (corr.equals(""))
			return "";

		return corr.substring(1);
	}
}