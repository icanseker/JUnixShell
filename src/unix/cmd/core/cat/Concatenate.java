package unix.cmd.core.cat;

import unix.cmd.UnixCommand;
import unix.cmd.arg.type.BasicPath;

/**
 * Concatenate files and print on the standard output
 * 
 * @author icanseker@gmail.com
 */
public class Concatenate extends UnixCommand<ConcatenateOption> {

	public Concatenate() {
		super("cat");
	}

	@Override
	public boolean acceptMultiArgument() {
		return true;
	}

	public Concatenate addTargetFile(String filePath) throws Exception {
		addArgument(new BasicPath(filePath));
		return this;
	}

	/**
	 * equivalent to -vET (--show-nonprinting --show-ends --show-tabs)
	 * 
	 * @throws Exception
	 */
	public void SHOW_ALL_CHARACTERS() throws Exception {
		addOption(ConcatenateOption.SHOW_ALL_CHARACTERS);
	}

	/**
	 * number nonempty output lines, overrides -n (--number NUMBER_ALL_LINES)
	 * 
	 * @throws Exception
	 */
	public void NUMBER_NONEMPTY_LINES() throws Exception {
		addOption(ConcatenateOption.NUMBER_NONEMPTY_LINES);
	}

	/**
	 * equivalent to -vE (--show-nonprinting --show-ends)
	 * 
	 * @throws Exception
	 */
	public void SHOW_NONPRINTING_AND_LINE_BREAK() throws Exception {
		addOption(ConcatenateOption.SHOW_NONPRINTING_AND_LINE_BREAK);
	}

	/**
	 * display $ at end of each line
	 * 
	 * @throws Exception
	 */
	public void SHOW_LINE_BREAK() throws Exception {
		addOption(ConcatenateOption.SHOW_LINE_BREAK);
	}

	/**
	 * number all output lines
	 * 
	 * @throws Exception
	 */
	public void NUMBER_ALL_LINES() throws Exception {
		addOption(ConcatenateOption.NUMBER_ALL_LINES);
	}

	/**
	 * suppress repeated empty output lines
	 * 
	 * @throws Exception
	 */
	public void SQUEZEE_BLANK_LINES() throws Exception {
		addOption(ConcatenateOption.SQUEZEE_BLANK_LINES);
	}

	/**
	 * equivalent to -vT (--show-nonprinting --show-tabs)
	 * 
	 * @throws Exception
	 */
	public void SHOW_NONPRINTING_AND_TABS() throws Exception {
		addOption(ConcatenateOption.SHOW_NONPRINTING_AND_TABS);
	}

	/**
	 * display TAB characters as ^I
	 * 
	 * @throws Exception
	 */
	public void SHOW_TABS() throws Exception {
		addOption(ConcatenateOption.SHOW_TABS);
	}

	/**
	 * use ^ and M- notation, except for LFD and TAB
	 * 
	 * @throws Exception
	 */
	public void SHOW_NONPRINTING() throws Exception {
		addOption(ConcatenateOption.SHOW_NONPRINTING);
	}
}
