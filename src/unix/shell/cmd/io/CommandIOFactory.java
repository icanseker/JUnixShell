package unix.shell.cmd.io;

import unix.shell.cmd.arg.type.FilePath;
import unix.shell.cmd.io.mod.CommandIn;
import unix.shell.cmd.io.mod.CommandOut;

public class CommandIOFactory {

	public static CommandIn STDIN = new CommandIn() {
		@Override
		public int fd() {
			return 0;
		}

		@Override
		public String ioSource() {
			return "/dev/stdin";
		};
	};

	public static CommandOut STDOUT = new CommandOut() {
		@Override
		public int fd() {
			return 1;
		}

		@Override
		public String ioSource() {
			return "/dev/stdout";
		}
	};

	public static CommandOut STDERR = new CommandOut() {
		@Override
		public int fd() {
			return 2;
		}

		@Override
		public String ioSource() {
			return "/dev/stderr";
		};
	};

	public static CommandIOFactory DECLARE = new CommandIOFactory();

	private CommandIOFactory() {
	}

	public CommandIn in(int fileDescriptor, String ioSource) {
		return new CommandIn() {
			@Override
			public int fd() {
				return fileDescriptor;
			}

			@Override
			public String ioSource() throws Exception {
				return new FilePath(ioSource).toString();
			}
		};
	}

	public CommandIn in(int fileDescriptor) {
		return in(fileDescriptor, null);
	}

	public CommandOut out(int fileDescriptor, String ioSource) {
		return new CommandOut() {
			@Override
			public int fd() {
				return fileDescriptor;
			}

			@Override
			public String ioSource() throws Exception {
				return new FilePath(ioSource).toString();
			}
		};
	}

	public CommandOut out(int fileDescriptor) {
		return out(fileDescriptor, null);
	}
}
