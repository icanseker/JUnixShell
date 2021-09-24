package unix.shell.cmd.example;

import unix.shell.cmd.bash.builtin.alias.Alias;

public class Example {

	public static void main(String[] args) throws Exception {

		Alias alias = new Alias();
		alias.print();

		alias.addAlias("deneme", "code");
		alias.print();

		alias.addAlias("deneme2", "code2");
		alias.print();
	}
}
