package com.swingler.concordance_generator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;

public class ConcordanceGenerator {	
	public static void generateConcordance(File source, PrintStream out) throws FileNotFoundException, ParseException, IOException {
		InputStream istream = null;
		try {
			istream = new FileInputStream(source);
			generateConcordance(istream, out);
		}
		finally { if(istream != null) istream.close(); }
	}

	public static void generateConcordance(URL source, PrintStream out) throws ParseException, IOException {
		InputStream istream = null;
		try {
			istream = source.openStream();
			generateConcordance(istream, out);
		}
		finally { if(istream != null) istream.close(); }
	}

	public static void generateConcordance(InputStream istream, PrintStream out) throws ParseException {
		Concordance concordance = new ConcordanceImpl();
		EnglishParser.parse(concordance, istream);		
		concordance.print(out);
	}
}