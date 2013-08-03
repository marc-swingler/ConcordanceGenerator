package com.swingler.concordance_generator;

public interface Concordance {
	public void put(String key, WordOccurrance value);
	public WordOccurrance get(String key);
	public Iterable<String> keys();
	public void print();
}
