package com.swingler.concordance_generator;

public interface Concordance {
	public void addWord(String token);
	public void addSentEnd(String token);
	public void addOther(String token);
	public void print();
}