package unix.shell.expansion;

/**
 * After word splitting, unless the -f option has been set (see The Set
 * Builtin), Bash scans each word for the characters ‘*’, ‘?’, and ‘[’. If one
 * of these characters appears, and is not quoted, then the word is regarded as
 * a pattern, and replaced with an alphabetically sorted list of filenames
 * matching the pattern.
 * 
 * <p/>
 * If no matching filenames are found, and the shell option nullglob is
 * disabled, the word is left unchanged. If the nullglob option is set, and no
 * matches are found, the word is removed. If the failglob shell option is set,
 * and no matches are found, an error message is printed and the command is not
 * executed. If the shell option nocaseglob is enabled, the match is performed
 * without regard to the case of alphabetic characters.
 * 
 * <p/>
 * When a pattern is used for filename expansion, the character ‘.’ at the start
 * of a filename or immediately following a slash must be matched explicitly,
 * unless the shell option dotglob is set. The filenames ‘.’ and ‘..’ must
 * always be matched explicitly, even if dotglob is set. In other cases, the ‘.’
 * character is not treated specially.
 * 
 * <p/>
 * https://www.gnu.org/software/bash/manual/html_node/Filename-Expansion
 */
public class FilenameExpansion extends ShellExpansion {
}
