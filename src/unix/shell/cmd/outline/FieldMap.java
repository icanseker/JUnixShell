package unix.shell.cmd.outline;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import unix.shell.cmd.arg.mod.ArgumentAct;
import unix.shell.cmd.arg.mod.ArgumentInterface;
import unix.shell.cmd.mod.StrCorrespond;

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

		if (this.actMap.containsKey(groupId)) {

			ArgumentAct act = this.actMap.get(groupId);

			if (arguments.length == 0) {

				if (act.require()) {

					if (act.multiple())
						throw new Exception(groupId + " requires at least one argument");

					throw new Exception(groupId + " requires an argument");
				}
			} else {

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

	@Override
	public String correspond() throws Exception {

		String corr = "";

		/*
		 * Do not break the order
		 */
		for (String groupId : this.actMap.keySet())
			for (ArgumentInterface argument : this.argumentMap.get(groupId))
				corr += argument.correspond();

		if (corr.equals(""))
			return "";

		return corr.substring(1);
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