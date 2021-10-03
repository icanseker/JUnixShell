package unix.shell.cmd.opt.mod;

import java.util.HashSet;

public interface OptionInteraction<OptionType> {

	public default HashSet<OptionType> optionsExcluded() {
		return null;
	}

	public default HashSet<OptionType> optionsOverridden() {
		return null;
	}

	public default HashSet<OptionType> optionsEqualed() {
		return null;
	}

	public default HashSet<OptionType> optionsRequired() {
		return null;
	}
}
