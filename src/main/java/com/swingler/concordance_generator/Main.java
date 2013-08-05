package com.swingler.concordance_generator;

import gnu.getopt.Getopt;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
	private static String helpMessage = null;

	public static void main(String[] args) {
		try { helpMessage = getHelpMessage(); }
		catch(IOException ex) { programError(ex); }

		Getopt getopt = new Getopt("Main", args, ":u:f:o:h");
		getopt.setOpterr(false);
		int c = Integer.MIN_VALUE;
		String urlString = null;
		String filename = null;
		String outfilename = null;
		boolean help = false;
		boolean sourceSpecified = false;
		while ((c = getopt.getopt()) != -1) {
			switch(c) {
			case 'u':
				if(sourceSpecified) userError("At most one -f or -u option may be specified.");
				urlString = getopt.getOptarg();
				sourceSpecified = true;
				break;
			case 'f':
				if(sourceSpecified) userError("At most one -f or -u option may be specified.");
				filename = getopt.getOptarg();
				sourceSpecified = true;
				break;
			case 'o':
				if(outfilename != null) userError("At most one -o option may be specified.");
				outfilename =getopt.getOptarg();
				break;
			case 'h':
				help = true;
				break;
			case ':':
				userError("Valid option: -" + (char)getopt.getOptopt() + ", but requires an argument.");
				break;
			default:
				userError("Invalid option: -" + (char)getopt.getOptopt());
			}
		}

		StringBuffer invalidArgs = new StringBuffer();
		for (int i = getopt.getOptind(); i < args.length ; i++) {
			invalidArgs.append(args[i] + " ");
		}
		if(invalidArgs.length() > 0) userError("Invalid Arguments: " + invalidArgs.toString());

		if(!sourceSpecified && outfilename != null) userError("Output file specified: " + outfilename + ", but no source.");

		URL url = null;
		if(urlString != null) {
			try { url = new URL(urlString); }
			catch(MalformedURLException ex) { userError(urlString + " is not a valid URL."); }
		}

		File file = null;
		if(filename != null) {
			file = new File(filename);
			if(!file.exists()) userError(filename + " does not exist.");
		}

		File outfile = null;
		if(outfilename != null) outfile = new File(outfilename);

		try {
			if(file != null && outfile != null && outfile.getCanonicalPath().equals(file.getCanonicalPath()))
				userError("filename and outfilename refer to the same file: " + file.getCanonicalPath());
		}
		catch(IOException ex) { programError(ex); }

		if(help || !sourceSpecified){
			System.out.println(helpMessage);
			if(!sourceSpecified) System.exit(0);
		}
		
		try { generateConcordance(file, url, outfile); }
		catch(Exception ex) { programError(ex); }
	}

	private static String getHelpMessage() throws IOException {
		StringBuffer strbuf = new StringBuffer();
		InputStream istream = null;
		InputStreamReader ireader = null;
		BufferedReader breader = null;
		try {
			istream = System.class.getClass().getResourceAsStream("/help.txt");
			ireader = new InputStreamReader(istream);
			breader = new BufferedReader(ireader);
			while(breader.ready()) { strbuf.append(breader.readLine() + System.getProperty("line.separator")); }
		}
		finally {
			if(breader != null) breader.close();
			if(ireader != null) ireader.close();
			if(istream != null) istream.close();
		}
		return strbuf.toString();
	}

	private static void programError(Exception ex) {
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("!!!PROGRAM ERROR!!!");
		System.out.println("-------------------------------------------------------------------------------");
		ex.printStackTrace();
		System.out.println("-------------------------------------------------------------------------------");
		System.exit(-1);		
	}

	private static void userError(String message) {
		System.err.println("-------------------------------------------------------------------------------");
		System.err.println("USER ERROR:");
		System.err.println("-------------------------------------------------------------------------------");
		System.err.println(message);
		System.err.println(helpMessage);
		System.exit(1);		
	}

	private static void generateConcordance(File file, URL url, File outfile) throws FileNotFoundException, ParseException, IOException {
		PrintStream out = null;		
		try {
			if(outfile != null) {
				if(outfile.exists()) outfile.delete();
				outfile.createNewFile();
				out = new PrintStream(outfile);
			}
			else out = System.out;
			if(file != null) ConcordanceGenerator.generateConcordance(file, out);
			if(url != null) ConcordanceGenerator.generateConcordance(url, out);
		}
		finally { if(outfile != null && out != null) out.close(); }
	}
}