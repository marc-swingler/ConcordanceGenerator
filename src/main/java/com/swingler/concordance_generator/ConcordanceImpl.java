package com.swingler.concordance_generator;

public class ConcordanceImpl extends RedBlackTree<String, WordOccurrance> implements Concordance {
	public void put(String key, int sentenceCounter) {
		WordOccurrance wordOccurrance = get(key);
		if(wordOccurrance == null) wordOccurrance = new WordOccurrance();
		wordOccurrance.insert(sentenceCounter);
		put(key, wordOccurrance);
	}
	public void print() { for (String string : keys()) System.out.println(string + ", " + get(string)); }
}
