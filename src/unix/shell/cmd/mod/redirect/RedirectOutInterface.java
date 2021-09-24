package unix.shell.cmd.mod.redirect;

public interface RedirectOutInterface {

	public void redirectOutTo(String filePath, RedirectOutType redirectType) throws Exception;
}
