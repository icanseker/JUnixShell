package unix.shell.cmd.mod;

public interface SingularIdentifier {

	public default String singularId() {

		if (this instanceof Enum) {
			Class<?> superClass = this.getClass().getSuperclass();
			if (superClass != Enum.class)
				return superClass.getName();
		}

		return this.getClass().getName();
	}
}
