package com.swingler.concordance_generator;

import java.util.LinkedList;

public class WordOccurrance {
	private int frequency = 0;
	private LinkedList<Integer> sentences = null;
	
	public WordOccurrance() {
		frequency = 0;
		sentences = new LinkedList<Integer>();
	}
	
	public void insert(int sentence) {
		frequency++;
		sentences.add(sentence);
	}
	
	public String toString() {
		StringBuffer strbuf = new StringBuffer();
		strbuf.append("frequency: " + frequency + ", sentences: [");
		for(Integer sentence : sentences) {
			strbuf.append(sentence + ",");
		}
		strbuf.delete(strbuf.length() - 1, strbuf.length());
		strbuf.append("]");
		return strbuf.toString();
	}
}
