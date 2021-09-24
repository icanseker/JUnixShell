package unix.shell.cmd.opt.mod;

public interface ClassIdentifier {

	public default String classId() {

		String identifier = this.getClass().getName();

		if (this instanceof Enum) {

			Class<?> superClass = this.getClass().getSuperclass();
			if (superClass != Enum.class)
				identifier = superClass.getName();

			identifier += "/" + ((Enum<?>) this).name();
		}

		return identifier;
	}
}
