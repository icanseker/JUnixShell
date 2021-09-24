JUnixShell is a Java library that implements the Unix shell (commands, options, functions, parameters, expansions, redirections, scripts, operators and so on) programming language.

The major purpose of JUnixShell is to create a structure that is compatible with Unix syntax, commands, and other shell features. 

Easy to create shell command lines (expansions, pipelines, fields, and so on).

Currently supported shell features: (The project is still in the works.)
* Shell syntax
* ShellCommands (option overriding/excluding/requiring/equaling carried out dynamically)
* Shell parameters
* Pipelines
* Shell Expansions
	* Brace Expansion
	* Tilde Expansion
	* Shell Parameter Expansion
	* Command Substitution
	* Arithmetic Expansion
	* Process Substitution
	* Word Splitting
	* Filename Expansion
	* Quote Removal
* Redirections (stdin, stdout, stderr)

Currently supported shell commands: (The project is still in the works.)
* Concatenate (cat)
* Date
* Passwd
* Alias
* Unalias

Date command example:

```java
import unix.shell.cmd.core.date.Date;

...

	Date date = new Date();

	date.specify("15 Dec 2013");
	date.print();
```

The output is
```
date --date='15 Dec 2013'
```

...
Other date str specifying examples:

```java
import unix.shell.cmd.core.date.Date;

...

	Date date = new Date();

		// day
		date.specify("15 Dec 2013");
		date.specify("15dec2013");
		date.specify("15-dec-2013");
		date.specify("24 September 2003");
		date.specify("Sep 24, 2003");
		date.specify("2004-02-29");
		date.specify("72-9-24"); // Assume 19xx for 69 through 99
		date.specify("45-9-24"); // 20xx for 00 through 68
		date.specify("9/24/72");

		// The year can also be omitted. In this case, the last specified year is used, or the current year if none.
		date.specify("9/24");
		date.specify("sep24"); // or "sep 24"

		// time
		date.specify("19:59"); // = 19:59:00
		date.specify("19:59:34");
		date.specify("08:52 a.m."); // or am
		date.specify("08:52pm"); // or p.m.

		// combine both day and time
		date.specify("2004-02-29 08:52pm"); // or p.m.
		date.specify("2004-02-29 19:59:34");

		// time zone
		date.specify("TZ=\"Europe/Paris\"");

		// combine time zone and day and/or time
		date.specify("TZ=\"Europe/Paris\" 24 September 2003 08:52pm"); // meaning: What date is it if it's September 24, 2003, at 8:52 p.m. in Europe/Paris?
```
