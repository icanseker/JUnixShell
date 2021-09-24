package unix.shell.cmd.core.passwd;

import java.util.Arrays;
import java.util.HashSet;

import unix.shell.cmd.opt.UnixCommandOption;

public enum PasswdOption implements UnixCommandOption<PasswdOption> {

	/**
	 * This option can be used only with -S and causes show status for all users.
	 */
	SHOW_STATUS_FOR_ALL_USERS('a', "all") {
		@Override
		public HashSet<UnixCommandOption<PasswdOption>> optionsRequired() {
			return new HashSet<UnixCommandOption<PasswdOption>>(Arrays.asList(DISPLAY_ACCOUNT_STATUS_INFO));
		}
	},

	/**
	 * Delete a user's password (make it empty). This is a quick way to disable a
	 * password for an account. It will set the named account passwordless.
	 */
	DELETE_PASSWORD('d', "delete"),

	/**
	 * Immediately expire an account's password. This in effect can force a user to
	 * change their password at the user's next login.
	 */
	EXPIRE_ACCOUNT_PASSWORD('e', "expire"),

	/**
	 * This option is used to disable an account after the password has been expired
	 * for a number of days. After a user account has had an expired password for
	 * INACTIVE days, the user may no longer sign on to the account.
	 */
	INACTIVE_AFTER_PASSWORD_EXPIRED('i', "inactive"),

	/**
	 * Indicate password change should be performed only for expired authentication
	 * tokens (passwords). The user wishes to keep their non-expired tokens as
	 * before.
	 */
	KEEP_TOKENS('k', "keep-tokens"),

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
	 */
	LOCK('l', "lock"),

	/**
	 * Set the minimum number of days between password changes to MIN_DAYS. A value
	 * of zero for this field indicates that the user may change their password at
	 * any time.
	 */
	MINDAYS_TO_PASSWORD_CHAHGE('n', "mindays"),

	QUIET_MODE('q', "quiet"),

	/**
	 * Specify the repository to which the operation is to be applied. Supported
	 * repositories include files, nis, and dce. If repository is not specified, the
	 * default is files.
	 * https://docstore.mik.ua/manuals/hp-ux/en/B2355-60130/passwd.1.html
	 */
	CHANGE_PASSWORD_IN_REPOSITORY('r', "repository"),

	/**
	 * Apply changes in the CHROOT_DIR directory and use the configuration files
	 * from the CHROOT_DIR directory.
	 */
	APPLY_CHANGES_IN_CHROOT_DIR('R', "root"),

	/**
	 * Display account status information. The status information consists of 7
	 * fields. The first field is the user's login name. The second field indicates
	 * if the user account has a locked password (L), has no password (NP), or has a
	 * usable password (P). The third field gives the date of the last password
	 * change. The next four fields are the minimum age, maximum age, warning
	 * period, and inactivity period for the password. These ages are expressed in
	 * days.
	 */
	DISPLAY_ACCOUNT_STATUS_INFO('S', "status"),

	/**
	 * Unlock the password of the named account. This option re-enables a password
	 * by changing the password back to its previous value (to the value before
	 * using the -l option).
	 */
	UNLOCK_PASSWORD('u', "unlock"),

	/**
	 * Set the number of days of warning before a password change is required. The
	 * WARN_DAYS option is the number of days prior to the password expiring that a
	 * user will be warned that their password is about to expire.
	 */
	WARNDAYS_BEFORE_PASSWORD_CHANGE('w', "warndays"),

	/**
	 * Set the maximum number of days a password remains valid. After MAX_DAYS, the
	 * password is required to be changed.
	 * 
	 * Passing the number -1 as MAX_DAYS will remove checking a password's validity.
	 */
	MAXDAYS_OF_PASSWORD_REMAINS_VALID('x', "maxdays"), //
	;

	private final Character symbol;
	private final String varName;

	private PasswdOption(Character symbol, String varName) {
		this.symbol = symbol;
		this.varName = varName;
	}

	private PasswdOption(Character symbol) {
		this(symbol, null);
	}

	private PasswdOption(String varName) {
		this(null, varName);
	}

	@Override
	public Character symbol() {
		return this.symbol;
	}

	@Override
	public String varName() {
		return this.varName;
	}
}
