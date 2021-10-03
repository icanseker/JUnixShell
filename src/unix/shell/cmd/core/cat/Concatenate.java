package unix.shell.cmd.core.cat;

import unix.shell.cmd.UnixCommand;
import unix.shell.cmd.arg.mod.ArgumentAct;
import unix.shell.cmd.arg.type.BasicPath;
import unix.shell.cmd.outline.FieldMap;

/**
 * Concatenate files and print on the standard output
 */
public class Concatenate extends UnixCommand<ConcatenateOption> {

	public Concatenate() {
		super("cat");
	}

	/**
	 * cat [OPTION]... [FILE]...
	 */
	@Override
	protected FieldMap synopsis() {

		FieldMap synopsis = new FieldMap();
		synopsis.addArgumentGroup("file", ArgumentAct.OPTIONAL_MULTIPLE);

		return synopsis;
	}

	public Concatenate addTargetFile(String filePath) throws Exception {
		addArgument("file", new BasicPath(filePath));
		return this;
	}

	public void SHOW_ALL_CHARACTERS() throws Exception {
		addOption(ConcatenateOption.SHOW_ALL_CHARACTERS);
	}

	public void NUMBER_NONEMPTY_LINES() throws Exception {
		addOption(ConcatenateOption.NUMBER_NONBLANK);
	}

	public void SHOW_NONPRINTING_AND_LINE_BREAK() throws Exception {
		addOption(ConcatenateOption.SHOW_NONPRINTING_AND_ENDS);
	}

	public void SHOW_LINE_BREAK() throws Exception {
		addOption(ConcatenateOption.SHOW_ENDS);
	}

	public void NUMBER_ALL_LINES() throws Exception {
		addOption(ConcatenateOption.NUMBER);
	}

	public void SQUEZEE_BLANK_LINES() throws Exception {
		addOption(ConcatenateOption.SQUEZEE_BLANK);
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
