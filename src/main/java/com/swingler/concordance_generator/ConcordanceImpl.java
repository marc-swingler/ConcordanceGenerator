package com.swingler.concordance_generator;

import java.io.PrintStream;
import java.util.LinkedList;

public class ConcordanceImpl implements Concordance {
	private static class WordOccurrance {
		private int frequency = 0;
		private LinkedList<Integer> sentences = null;

		private WordOccurrance() {
			frequency = 0;
			sentences = new LinkedList<Integer>();
		}

		private void at(int sentence) {
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

	private RedBlackTree<String, WordOccurrance> redBlackTree = null;
	private int sentenceCounter = 0;

	public ConcordanceImpl() {
		redBlackTree = new RedBlackTree<String, WordOccurrance>();
		sentenceCounter = 1;
	}

	public void addSentEnd(String token) { sentenceCounter++; }

	public void addOther(String token) {}

	public void addWord(String token) {
		String key = token.toLowerCase();
		WordOccurrance wordOccurrance = redBlackTree.get(key);
		if(wordOccurrance == null) wordOccurrance = new WordOccurrance();
		wordOccurrance.at(sentenceCounter);
		redBlackTree.put(key, wordOccurrance);
	}

	public void print(PrintStream out) {
		for (String string : redBlackTree.keys()) out.println(string + ", " + redBlackTree.get(string));
		out.flush();
	}
}