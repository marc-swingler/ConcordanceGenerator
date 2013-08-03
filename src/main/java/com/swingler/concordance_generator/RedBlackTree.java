package com.swingler.concordance_generator;

import java.util.Queue;
import java.util.LinkedList;

public class RedBlackTree<Key extends Comparable<Key>, Value> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private Node root = null;

	private class Node {
		private Key key = null;
		private Value value = null;
		private boolean color = RED;
		private Node left = null;
		private Node right = null;

		public Node(Key key, Value value, boolean color) {
			this.key = key;
			this.value = value;
			this.color = color;
			left = null;
			right = null;
		}
	}

	public RedBlackTree() { root = null; }
	
	public void put(Key key, Value value) {
		root = put(root, key, value);
		root.color = BLACK;
	}

	private Node put(Node node, Key key, Value value) {
		if(node == null) return new Node(key, value, RED);
		int cmp = key.compareTo(node.key);
		if(cmp < 0) node.left = put(node.left, key, value);
		else if(cmp > 0) node.right = put(node.right, key, value);
		else node.value = value;
		if(isRed(node.right) && !isRed(node.left)) node = rotateLeft(node);
		if(isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
		if(isRed(node.left) && isRed(node.right)) flipColors(node);
		return node;
	}

	private Node rotateRight(Node node) {
		Node leftChild = node.left;
		node.left = leftChild.right;
		leftChild.right = node;
		leftChild.color = node.color;
		node.color = RED;
		return leftChild;
	}

	private Node rotateLeft(Node node) {
		Node rightChild = node.right;
		node.right = rightChild.left;
		rightChild.left = node;
		rightChild.color = node.color;
		node.color = RED;
		return rightChild;
	}

	private void flipColors(Node node) {
		node.color = !node.color;
		node.left.color = !node.left.color;
		node.right.color = !node.right.color;
	}

	private boolean isRed(Node node) {
		if(node == null) return false;
		return(node.color == RED);
	}

	public Value get(Key key) { return get(root, key); }

	private Value get(Node node, Key key) {
		while(node != null) {
			int cmp = key.compareTo(node.key);
			if(cmp < 0) node = node.left;
			else if(cmp > 0) node = node.right;
			else return node.value;
		}
		return null;
	}

	public Iterable<Key> keys() {
		Queue<Key> queue = new LinkedList<Key>();
		if(isEmpty()) return queue;
		keys(root, queue, min(), max());
		return queue;
	}

	private void keys(Node node, Queue<Key> queue, Key min, Key max) {
		if(node == null) return;
		int cmpMin = min.compareTo(node.key);
		int cmpMax = max.compareTo(node.key);
		if(cmpMin < 0) keys(node.left, queue, min, max);
		if(cmpMin <= 0 && cmpMax >= 0) queue.add(node.key);
		if(cmpMax > 0) keys(node.right, queue, min, max);
	}

	private Key max() {
		if(isEmpty()) return null;
		return max(root).key;
	}

	private Node max(Node node) {
		if(node.right == null) return node;
		else return max(node.right);
	}

	private Key min() {
		if(isEmpty()) return null;
		return min(root).key;
	}

	private Node min(Node node) {
		if(node.left == null) return node;
		else return min(node.left);
	}

	private boolean isEmpty() { return root == null; }
}
