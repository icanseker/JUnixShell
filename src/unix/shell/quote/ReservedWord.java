package unix.shell.quote;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Reserved words are words that have special meaning to the shell. They are
 * used to begin and end the shell�s compound commands.
 * 
 * <p/>
 * The following words are recognized as reserved when unquoted and the first
 * word of a command (see below for exceptions):
 * 
 * <p/>
 * "in" is recognized as a reserved word if it is the third word of a case or
 * select command. "in" and "do" are recognized as reserved words if they are
 * the third word in a for command.
 */
public class ReservedWord {

	public static final Set<String> set = new HashSet<String>(Arrays.asList(//
			"if", "then", "elif", "else", "fi", "time", //
			"for", "in", "until", "while", "do", "done", //
			"case", "esac", //
			"coproc", //
			"select", //
			"function", //
			"{", "}", //
			"[[", "]]", //
			"!" //
	));
}
