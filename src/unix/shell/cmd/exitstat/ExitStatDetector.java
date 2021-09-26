package unix.shell.cmd.exitstat;

import java.lang.reflect.Field;
import java.util.function.Supplier;

import unix.shell.cmd.UnixCommand;
import unix.shell.cmd.exitstat.mod.ExitStatusInterface;

public class ExitStatDetector<Command extends UnixCommand<?>> {

	private Supplier<Command> supplier;

	public ExitStatDetector(Supplier<Command> supplier) {
		this.supplier = supplier;
	}

	public String meaningOf(int code)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

		if (code == 0)
			return "success";

		Class<?> cmdClass = supplier.get().getClass();
		Class<?> exitStatusEnumSet = null;

		Field exitStatusSetField = cmdClass.getField("exitStatusEnumSet");

		if (exitStatusSetField != null) {

			exitStatusEnumSet = (Class<?>) exitStatusSetField.get(exitStatusEnumSet);

			if (!exitStatusEnumSet.getCanonicalName().equals(StandardUnixExitStatus.class.getCanonicalName()))
				for (Object enumConstant : exitStatusEnumSet.getEnumConstants()) {

					ExitStatusInterface exitStatus = (ExitStatusInterface) enumConstant;

					if (exitStatus.code() == code)
						return exitStatus.meaning();
				}
		}

		// search for standard exit status
		for (StandardUnixExitStatus exitStatus : StandardUnixExitStatus.values())
			if (exitStatus.code() == code)
				return "According to Standard Exit Status: " + exitStatus.meaning();

		return "unknown status";
	}
}
