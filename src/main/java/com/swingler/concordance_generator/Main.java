package com.swingler.concordance_generator;

import gnu.getopt.Getopt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
	public static final String DEFAULT_FILENAME = "arbitrary.txt";

	public static void main(String[] args) {
		Getopt getopt = new Getopt("Main", args, "f:h?");
		int c = Integer.MIN_VALUE;
		String filename = null;
		while ((c = getopt.getopt()) != -1) {
			switch(c) {
			case 'f':
				if(filename != null) {
					System.out.println("At most one -f option may be specified.");
					System.exit(1);
				}
				filename = getopt.getOptarg();
				break;
			case 'h':
			case '?':
				printHelp();
				break;
			default:
				System.out.println("Invalid option: -" + c);
				System.exit(1);
			}
		}

		if(filename == null) filename = DEFAULT_FILENAME;
		
		File file = new File(filename);
		if(!file.exists()) {
			System.out.println(filename + " does not exist.");
			System.exit(1);
		}

		try { printConcordance(filename); }
		catch(Exception ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}

	private static void printHelp() {
		System.out.println("The program may be run as a Windows Batch file or as a Bash Shell script.");
		System.out.println("	Windows Batch File: use concordance_generator.bat");
		System.out.println("	Bash Shell Script: use concordance_generator.sh");
		System.out.println("Both concordance_generator.bat and concordance_generator.sh work with the same options and defaults.");
		System.out.println("The following options are available:");
		System.out.println("	-f <filename>");
		System.out.println("		This is the name of the document concordance_generator will use to build a concordance.");
		System.out.println("		The file must have a plain text format. It defaults to 'artirary.txt' if no -f option is specified.");
		System.out.println("		At most, one -f option may be used per run of concordance_generator.");
		System.out.println("	-h or -?");
		System.out.println("		Prints help information message");
		System.out.println("The generated concordance will be printed to a file named concordance.txt.");
	}

	public static void printConcordance(String filename) throws FileNotFoundException, ParseException {
		FileInputStream fistream = new FileInputStream(filename);
		Concordance concordance = new ConcordanceImpl();
		EnglishParser.parse(concordance, fistream);
		try { fistream.close(); }
		catch(IOException ex) { ex.printStackTrace(); }
		concordance.print();
	}
}
