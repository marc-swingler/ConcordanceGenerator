package com.swingler.concordance_generator;

import java.io.PrintStream;

public interface Concordance {
	public void addWord(String token);
	public void addSentEnd(String token);
	public void addOther(String token);
	public void print(PrintStream out);
}