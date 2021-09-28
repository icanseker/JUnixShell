package unix.shell.cmd.io;

import unix.shell.cmd.io.mod.CommandIn;
import unix.shell.cmd.io.mod.CommandOut;

public class CommandIOFactory {

	public static CommandIn STDIN = new CommandIn() {
		@Override
		public int fileDescriptor() {
			return 0;
		}

		@Override
		public String file() {
			return "/dev/stdin";
		};
	};

	public static CommandOut STDOUT = new CommandOut() {
		@Override
		public int fileDescriptor() {
			return 1;
		}

		@Override
		public String file() {
			return "/dev/stdout";
		}
	};

	public static CommandOut STDERR = new CommandOut() {
		@Override
		public int fileDescriptor() {
			return 2;
		}

		@Override
		public String file() {
			return "/dev/stderr";
		};
	};

	public static CommandIOFactory DECLARE = new CommandIOFactory();

	private CommandIOFactory() {
	}

	public CommandIn in(int descriptor, String fileDescriptor) {
		return new CommandIn() {
			@Override
			public int fileDescriptor() {
				return descriptor;
			}

			@Override
			public String file() {
				return fileDescriptor;
			}
		};
	}

	public CommandIn in(int descriptor) {
		return in(descriptor, null);
	}

	public CommandOut out(int descriptor, String fileDescriptor) {
		return new CommandOut() {
			@Override
			public int fileDescriptor() {
				return descriptor;
			}

			@Override
			public String file() {
				return fileDescriptor;
			}
		};
	}

	public CommandOut out(int descriptor) {
		return out(descriptor, null);
	}
}
