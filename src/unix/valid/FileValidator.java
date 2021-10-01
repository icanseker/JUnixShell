package unix.valid;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public class FileValidator {

	public static boolean isValidFilePath(String path) {

		try {
			Paths.get(path);
		} catch (InvalidPathException | NullPointerException ex) {
			return false;
		}
		return true;
	}
}
