package com.swingler.concordance_generator;

public class ConcordanceImpl extends RedBlackTree<String, WordOccurrance> implements Concordance {
	public void print() { for (String string : keys()) System.out.println(string + ", " + get(string)); }
}
