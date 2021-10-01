package unix.shell.cmd.opt.mod;

import unix.shell.cmd.opt.CommandLineOption;

/**
 * If a command doesn't have any options, it will be defined with this option
 * class, which shows this.
 */
public interface None extends CommandLineOption<None> {
}
