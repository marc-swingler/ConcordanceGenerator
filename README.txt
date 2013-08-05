This is a program called 'ConcordanceGenerator' that generates a concordance based on the contents of a file or url. The concordance it generates is an alphabetical list of all word occurrences, labeled with word frequencies, and sentence numbers in which each occurrence appeared.

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
	You'll need the following tools installed and their bin directories in you path: Java 1.7.0
	Unzip the file 'target/concordance_generator-1.0.0-SNAPSHOT-final.zip'
	The program may be run as a Windows Batch file or as a Bash Shell script.
		Windows Batch File: use concordance_generator.bat
		Bash Shell Script: use concordance_generator.sh
	Both concordance_generator.bat and concordance_generator.sh work with the same options and defaults. The following options are available:
		-f <filename>
			This is the name of a document concordance_generator will use to build a concordance. The file must have a plain text format. At most, one -f or -u option may be used.
		-u <urlString>
			This is the url of a document concordance_generator will use to build a concordance. The file must have a plain text format. At most, one -f or -u option may be used.
			Example URLs:
				http://tinyurl.com/lh33p5u ('Hamlet' in text format)
				http://tinyurl.com/6da9puc ('War and Peace' in text format)
		-o <outfilename>
			The file the concordance will be printed to. When not specified, the concordance will be printed to stdout. At most, one -o option may be used.
		-h
			Prints a help information message.

Design Considerations:

	I don't know what platform this program is intended be run on: Windows, *nix, or Mac. So I choose to implement in a common cross platofrm language: Java. I also had to consider implementing an English language parser of my own, or looking for a natural language parser library. I did briefly consider using OpenNLP. I decided against it because I think the purpose of the task was probably more to demonstrate general programming skills rather than circumventing the problem through a library. So with that in mind, I opened my old 'Algorithms' text book and implemented a Red-Black tree to serve as the data structure backing my concordance. Next, I wrote a simple parser called 'EnglishParser' using Javacc. The parser has a static method, parse, which takes two arguments: an interface named Concordance and an InputStream. (Using an interface decouples classes ConcordanceImpl from the classes EnglishParser.) Also, I won't pretend the EnglishParser class actually parses English. Sentences such as "It's good to see you Dr. David Evans will see you now.", pose a serious issue for someone wanting to generalize on the structure of sentences. Probably the best way to handle ambiguous sentences with words like "e.g." is to simply list them out as exceptions. Also, there is the matter of numbers and other special characters. Consider the following: "39th", "&" (and), "@" (at): depending on how you think about it, all are words. So the parser I wrote is very liberal with it's definition of a "word."


Please contact me if you have any questions,

Marc Swingler
marc.swingler@gmail.com
571-232-7444
