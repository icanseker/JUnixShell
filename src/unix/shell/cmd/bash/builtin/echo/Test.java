package unix.shell.cmd.bash.builtin.echo;

import unix.shell.cmd.arg.type.Text;
import unix.shell.expansion.BraceExpansion;

public class Test {

	public static void main(String[] args) throws Exception {

		Echo echo = new Echo();

		echo.addArgument("de ne me" + new BraceExpansion(new Text("1 1"), new Text("2 2 2")) + "x ve x");
		
		echo.print();

		System.out.println(echo.substitution());
	}
}