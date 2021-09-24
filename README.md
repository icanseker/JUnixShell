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
package ex.unix.shell.cmd.date;

...

	Date date = new Date();

	date.specify("15 Dec 2013");
	date.print();
```

The output is
```
date --date='15 Dec 2013'
```
