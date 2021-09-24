JUnixShell is a Java library that implements the Unix shell (commands, options, functions, parameters, expansions, redirections, scripts, operators and so on) programming language.

The major purpose of JUnixShell is to create a structure that is compatible with Unix syntax, commands, and other shell features. 

Easy to create shell command lines (expansions, pipelines, fields, and so on).

Currently supported shell features: (The project is still in the works.)
* Shell Syntax
* Shell Commands (option overriding/excluding/requiring/equaling carried out dynamically)
* Shell Parameters
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

Specify time zone with JUnixShell TimeZone enum based on IANA 2021a (Released 2021-01-24)

```java

import unix.shell.cmd.core.date.Date;
import unix.shell.cmd.core.date.TimeZone;
...
	Date date = new Date();
	date.specify("24 September 2003 08:52pm", TimeZone.EUROPE.PARIS);
```

Unix date command also supports some relative expressions:

```java

import unix.shell.cmd.core.date.Date;
...
	Date date = new Date();
	
	date.specify("first fri");
	date.specify("next tuesday");
	date.specify("last tuesday");
	date.specify("twelfth monday");
	date.specify("this sunday");
	date.specify("tomorrow");
	date.specify("3 days ago");
	date.specify("30000 days ago");
	date.specify("-2 month");
	date.specify("+300 year");

	// To print the date of the day three months and one day hence:
	date.specify("3 months 1 day");

	date.specify("-2 month -3 days");
	date.specify("-5 years -2 month +3 days");

	// seconds since the Epoch (Epoch = date -d '@0')
	date.specify("@0"); // gives Epoch
	date.specify("@1"); // or @+1 gives Epoch + 1 second
	date.specify("@-10"); // gives Epoch - 10 second
	date.specify("@915148800");
```

OptionBehavior is used to implement command options, so if one overrides/excludes/requires another, the library will dynamically organize dependencies, as follows: 

```java
package unix.shell.cmd.opt.mod;

import java.util.HashSet;

public interface OptionBehavior<OptionType> {

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
```

See an example about option behavior:

```java
import unix.shell.cmd.core.date.Date;
import unix.shell.cmd.core.date.format.RFC_3339_TIMESPEC;
...
	Date date = new Date();
	date.format_RFC_5322();

	date.format_RFC_3339(RFC_3339_TIMESPEC.SECONDS);
	date.format_RFC_3339(RFC_3339_TIMESPEC.NANOSECONDS);
	
	date.print();
```

The output will be (see --rfc-3339 excludes --rfc-email)
```
date --rfc-3339=ns
```

You can design date print format very easily (All types of arguments are separated by the library.)

```java
import unix.shell.cmd.core.date.Date;
import unix.shell.cmd.core.date.TimeZone;
import unix.shell.cmd.core.date.format.DateForm;
import unix.shell.cmd.core.date.format.DateFormPadding;
...
	Date date = new Date();
	date.specify("24 September 2003 08:52pm", TimeZone.ANTARCTICA.CASEY);
	
	date.customizePrint(DateForm.DAYNAME + " " + DateForm.DAYOF_MONTH);
	date.print();
	
	date.customizePrint(DateForm.DAYNAME + " " + new DateForm(DateForm.DAYOF_MONTH).padding(DateFormPadding.NO_PADDING));
	date.print();
	
	date.customizePrint(new DateForm(DateForm.DAYNAME_SHORT).upperCase() + " " + DateForm.DAYOF_MONTH);
	date.print();
	
	date.specify("24 Sept 2003 08:52pm", TimeZone.ANTARCTICA.CASEY);
	date.customizePrint(DateForm.H24_MIN + " (" + DateForm.H12 + ":" + DateForm.MIN + " " + DateForm.MERIDIEM_LC + ")");
	date.print();
	
	date.customizePrint("The time was " + DateForm.TIME_H24MINSEC);
	date.print();
	
	// To print the current full month name and the day of the month:
	date.specify("now");
	date.customizePrint(DateForm.MONTHNAME + " " + DateForm.DAYOF_MONTH);
	date.print();
```

The outputs will be:
```
date --date='TZ="Antarctica/Casey" 24 September 2003 08:52pm' '+%A %d'
date --date='TZ="Antarctica/Casey" 24 September 2003 08:52pm' '+%A %-d'
date --date='TZ="Antarctica/Casey" 24 September 2003 08:52pm' '+%^a %d'
date --date='TZ="Antarctica/Casey" 24 Sept 2003 08:52pm' '+%R (%I:%M %P)'
date --date='TZ="Antarctica/Casey" 24 Sept 2003 08:52pm' '+The time was %T'
date --date='now' '+%B %d'
```

An example of command substitution (Command substitution allows the output of a command to replace the command itself):

```java
import unix.shell.cmd.core.date.Date;
import unix.shell.cmd.core.date.format.DateForm;
...
	Date date = new Date();
	Date otherDate = new Date();
	otherDate.specify("15 June 2013 10:12am");

	date.specify(otherDate + " -5 days");
	date.customizePrint(DateForm.MONTHNAME + " " + DateForm.DAYOF_MONTH);
	
	date.print();
```

The output will be:
```
date --date="$( date --date='15 June 2013 10:12am' ) -5 days" '+%B %d'
```

Date set examples:

```java
import unix.shell.cmd.core.date.Date;
...
	Date date = new Date();
	
	/**
	 * Because of this error "date: the options to print and set the time may not be
	 * used together" all option and arguments related date specification will be
	 * removed. (option behavior implementation)
	 */
	date.specify("15 June 2013 10:12am");
	date.print();
	
	date.set("-2 days");
	date.print();
	
	// use other date to set
	date = new Date();
	Date aDate = new Date();
	aDate.specify("15 June 2013 10:12am");
	
	date.set(aDate + "+5 days");
	date.print();
```

The outputs will be:
```
date --date='15 June 2013 10:12am'
date --set='-2 days'
date --set="$( date --date='15 June 2013 10:12am' )+5 days"
```

Use java LocalDateTime class to specify date:

```java
import unix.shell.cmd.core.date.Date;
...
	Date date = new Date();
	
	date.specify(LocalDateTime.now().minusYears(5).minusDays(25).toString());
	date.print();
```

The output will be:
```
date --date='2016-08-30T23:20:53.505916100'
```