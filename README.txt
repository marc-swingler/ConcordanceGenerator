This is a program called 'ConcordanceGenerator' that generates a concordance based on the contents of a file. The concordance it generates is an alphabetical list of all word occurrences, labeled with word frequencies, and sentence numbers in which each occurence appeared.

Building the program:
	You'll need the following tools installed and their bin directories in your path:
		Maven 3.0.5
		Java 1.7.0
	Command to compile:
		mvn -B -U clean package
	If the code compiles correctly, you will see a directory named 'target' created containing a zip file named 'concordance_generator-1.0.0-SNAPSHOT-final.zip'

Reviewing the source code:
	If you use Eclipse for java development, you can run the following command to generate IDE project files:
		mvn eclipse:eclipse

Running the program:
	You'll need the following tools installed and their bin directories in you path:
		Java 1.7.0
	1) Unzip the file 'target/concordance_generator-1.0.0-SNAPSHOT-final.zip'
	2) The program may be run via a Windows Batch file or a Bash Shell script.
		Windows Batch File: use concordance_generator.bat
		Bash Shell Script: use concordance_generator.sh
	3) Both concordance_generator.bat and concordance_generator.sh work with the same options and defaults. The following options are available:
		-f <filename>
			This is the filename concordance_generator will use to build a concordance from. It defaults to 'artirary.txt' if no -f option is specified.
			At most, one -f option may be used per run of concordance_generator.
		-h or -?
			Prints help information
	4) The generated concordance will be printed to a file named concordance.txt.

Design Consierations:
	I don't know what platform this program is intended be run on: Windows, *nix, or Mac. So I choose to implement in a common cross platofrm language: Java. I also had to consider implementing an English
	language parser of my own, or looking for a natural language parser library. I did briefly consider using OpenNLP. I decided against it because I think the purpose of the task was probably more to
	demonstrate general programmig skills rather than circumventing the problem through a library. So with that in mind, I opened my old 'Algorithms' text book and implemented a Red-Black tree to serve as
	the data structure backing my concordance. Next, I wrote a simple parser called 'EnglishParser' using Javacc. The parser has a static method, parse, which takes two arguments: an interface named
	Concordance and an InputStream. (Using an interface decouples classes ConcordanceImpl from the classes EnglishParser.) Please contact me if you have any questions.

Marc Swingler
marc.swingler@gmail.com
571-232-7444
