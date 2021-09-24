package unix.shell.cmd.core.passwd;

import unix.shell.cmd.UnixCommand;
import unix.shell.cmd.arg.type.NumericValue;
import unix.shell.cmd.arg.type.Text;
import unix.shell.cmd.exitstat.mod.ExitStatusInterface;

/**
 * 
 * The passwd command changes passwords for user accounts. A normal user may
 * only change the password for their own account, while the superuser may
 * change the password for any account. passwd also changes the account or
 * associated password validity period.
 * 
 * @author icanseker@gmail.com
 *
 */
public class Passwd extends UnixCommand<PasswdOption> {

	public static final Class<? extends ExitStatusInterface> exitStatusEnumSet = PasswdExitStatus.class;

	public Passwd() {
		super("passwd");
	}

	@Override
	public boolean acceptArgument() {
		return true;
	}

	public Passwd forDifferentUser(String username) throws Exception {

		addArgument(new Text(username));
		return this;
	}

	/**
	 * This option can be used only with -S and causes show status for all users.
	 * 
	 * @throws Exception
	 */
	public void SHOW_STATUS_FOR_ALL_USERS() throws Exception {
		addOption(PasswdOption.SHOW_STATUS_FOR_ALL_USERS);
	}

	/**
	 * Delete a user's password (make it empty). This is a quick way to disable a
	 * password for an account. It will set the named account passwordless.
	 * 
	 * @throws Exception
	 */
	public void DELETE_PASSWORD() throws Exception {
		addOption(PasswdOption.DELETE_PASSWORD);
	}

	/**
	 * Immediately expire an account's password. This in effect can force a user to
	 * change their password at the user's next login.
	 * 
	 * @throws Exception
	 */
	public void EXPIRE_ACCOUNT_PASSWORD() throws Exception {
		addOption(PasswdOption.EXPIRE_ACCOUNT_PASSWORD);
	}

	/**
	 * This option is used to disable an account after the password has been expired
	 * for a number of days. After a user account has had an expired password for
	 * INACTIVE days, the user may no longer sign on to the account.
	 * 
	 * @throws Exception
	 */
	public void INACTIVE_AFTER_PASSWORD_EXPIRED(int INACTIVE_DAYS) throws Exception {
		addOption(PasswdOption.INACTIVE_AFTER_PASSWORD_EXPIRED, new NumericValue(INACTIVE_DAYS));
	}

	/**
	 * Indicate password change should be performed only for expired authentication
	 * tokens (passwords). The user wishes to keep their non-expired tokens as
	 * before.
	 * 
	 * @throws Exception
	 */
	public void KEEP_TOKENS() throws Exception {
		addOption(PasswdOption.KEEP_TOKENS);
	}

	/**
	 * Lock the password of the named account. This option disables a password by
	 * changing it to a value which matches no possible encrypted value (it adds a
	 * ´!´ at the beginning of the password).
	 * 
	 * Note that this does not disable the account. The user may still be able to
	 * login using another authentication token (e.g. an SSH key). To disable the
	 * account, administrators should use usermod --expiredate 1 (this set the
	 * account's expire date to Jan 2, 1970).
	 * 
	 * Users with a locked password are not allowed to change their password.
	 * 
	 * @throws Exception
	 */
	public void LOCK() throws Exception {
		addOption(PasswdOption.LOCK);
	}

	/**
	 * Set the minimum number of days between password changes to MIN_DAYS. A value
	 * of zero for this field indicates that the user may change their password at
	 * any time.
	 * 
	 * @throws Exception
	 */
	public void MINDAYS_TO_PASSWORD_CHAHGE(int MIN_DAYS) throws Exception {
		addOption(PasswdOption.MINDAYS_TO_PASSWORD_CHAHGE, new NumericValue(MIN_DAYS));
	}

	public void QUIET_MODE() throws Exception {
		addOption(PasswdOption.QUIET_MODE);
	}

	/**
	 * Specify the repository to which the operation is to be applied. Supported
	 * repositories include files, nis, and dce. If repository is not specified, the
	 * default is files.
	 * https://docstore.mik.ua/manuals/hp-ux/en/B2355-60130/passwd.1.html
	 * 
	 * @throws Exception
	 */
	public void CHANGE_PASSWORD_IN_REPOSITORY(String REPOSITORY) throws Exception {
		addOption(PasswdOption.CHANGE_PASSWORD_IN_REPOSITORY, new Text(REPOSITORY));
	}

	/**
	 * Apply changes in the CHROOT_DIR directory and use the configuration files
	 * from the CHROOT_DIR directory.
	 * 
	 * @throws Exception
	 */
	public void APPLY_CHANGES_IN_CHROOT_DIR(String CHROOT_DIR) throws Exception {
		addOption(PasswdOption.APPLY_CHANGES_IN_CHROOT_DIR, new Text(CHROOT_DIR));
	}

	/**
	 * Display account status information. The status information consists of 7
	 * fields. The first field is the user's login name. The second field indicates
	 * if the user account has a locked password (L), has no password (NP), or has a
	 * usable password (P). The third field gives the date of the last password
	 * change. The next four fields are the minimum age, maximum age, warning
	 * period, and inactivity period for the password. These ages are expressed in
	 * days.
	 * 
	 * @throws Exception
	 */
	public void DISPLAY_ACCOUNT_STATUS_INFO() throws Exception {
		addOption(PasswdOption.DISPLAY_ACCOUNT_STATUS_INFO);
	}

	/**
	 * Unlock the password of the named account. This option re-enables a password
	 * by changing the password back to its previous value (to the value before
	 * using the -l option).
	 * 
	 * @throws Exception
	 */
	public void UNLOCK_PASSWORD() throws Exception {
		addOption(PasswdOption.UNLOCK_PASSWORD);
	}

	/**
	 * Set the number of days of warning before a password change is required. The
	 * WARN_DAYS option is the number of days prior to the password expiring that a
	 * user will be warned that their password is about to expire.
	 * 
	 * @throws Exception
	 */
	public void WARNDAYS_BEFORE_PASSWORD_CHANGE(int WARN_DAYS) throws Exception {
		addOption(PasswdOption.WARNDAYS_BEFORE_PASSWORD_CHANGE, new NumericValue(WARN_DAYS));
	}

	/**
	 * Set the maximum number of days a password remains valid. After MAX_DAYS, the
	 * password is required to be changed.
	 * 
	 * Passing the number -1 as MAX_DAYS will remove checking a password's validity.
	 * 
	 * @throws Exception
	 */
	public void MAXDAYS_OF_PASSWORD_REMAINS_VALID(int MAX_DAYS) throws Exception {
		addOption(PasswdOption.MAXDAYS_OF_PASSWORD_REMAINS_VALID, new NumericValue(MAX_DAYS));
	}
}
