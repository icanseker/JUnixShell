package unix.shell.cmd.core.cat;

import unix.shell.cmd.UnixCommand;
import unix.shell.cmd.arg.type.BasicPath;

/**
 * Concatenate files and print on the standard output
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

	public void SHOW_ALL_CHARACTERS() throws Exception {
		addOption(ConcatenateOption.SHOW_ALL_CHARACTERS);
	}

	public void NUMBER_NONEMPTY_LINES() throws Exception {
		addOption(ConcatenateOption.NUMBER_NONEMPTY_LINES);
	}

	public void SHOW_NONPRINTING_AND_LINE_BREAK() throws Exception {
		addOption(ConcatenateOption.SHOW_NONPRINTING_AND_LINE_BREAK);
	}

	public void SHOW_LINE_BREAK() throws Exception {
		addOption(ConcatenateOption.SHOW_LINE_BREAK);
	}

	public void NUMBER_ALL_LINES() throws Exception {
		addOption(ConcatenateOption.NUMBER_ALL_LINES);
	}

	public void SQUEZEE_BLANK_LINES() throws Exception {
		addOption(ConcatenateOption.SQUEZEE_BLANK_LINES);
	}

	public void SHOW_NONPRINTING_AND_TABS() throws Exception {
		addOption(ConcatenateOption.SHOW_NONPRINTING_AND_TABS);
	}

	public void SHOW_TABS() throws Exception {
		addOption(ConcatenateOption.SHOW_TABS);
	}

	public void SHOW_NONPRINTING() throws Exception {
		addOption(ConcatenateOption.SHOW_NONPRINTING);
	}
}
