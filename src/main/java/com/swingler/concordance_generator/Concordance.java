package com.swingler.concordance_generator;

public interface Concordance {
	public void put(String key, int sentenceCounter);
	public void print();
}
