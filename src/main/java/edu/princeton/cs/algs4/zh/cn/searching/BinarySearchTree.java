package edu.princeton.cs.algs4.zh.cn.searching;

import edu.princeton.cs.algs4.StdRandom;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 基于二叉查找树的符号表(3.2 算法3.3)
 * {@link edu.princeton.cs.algs4.BST}
 *
 * @author FXYGR @date 2019-12-09
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value>
		implements OrderedSymbolTable<Key, Value> {

	private Node root;

	/**
	 * 最小的健
	 *
	 * @return
	 */
	@Override
	public Key min() {
		Node minNode = min(root);
		if (minNode == null) {
			return null;
		}
		return minNode.key;
	}

	private Node min(Node node) {
		if (node == null) {
			return null;
		}
		return node.left == null ? node : min(node.left);
	}

	/**
	 * 最大的键
	 *
	 * @return
	 */
	@Override
	public Key max() {
		Node maxNode = max(root);
		if (maxNode == null) {
			return null;
		}
		return maxNode.key;
	}

	private Node max(Node node) {
		if (node == null) {
			return null;
		}
		return node.right == null ? node : max(node.right);
	}

	/**
	 * 小于等于 key 的最大值
	 *
	 * @param key
	 * @return
	 */
	@Override
	public Key floor(Key key) {
		Node node = floor(root, key);
		return node == null ? null : node.key;
	}

	private Node floor(Node node, Key key) {
		if (node == null || key == null) {
			return null;
		}
		int cmp = key.compareTo(node.key);
		//  key <= node.key
		if (cmp <= 0) {
			return node;
		}
		//  key > node.key
		return floor(node.right, key);
	}

	/**
	 * 大于等于 key 的最小值
	 *
	 * @param key
	 * @return
	 */
	@Override
	public Key ceiling(Key key) {
		Node node = ceiling(root, key);
		return node == null ? null : node.key;
	}

	private Node ceiling(Node node, Key key) {
		if (node == null || key == null) {
			return null;
		}
		int cmp = key.compareTo(node.key);
		//  key >= node.key
		if (cmp >= 0) {
			return node;
		}
		//  key < node.key
		return ceiling(node.left, key);
	}

	/**
	 * 小于 key 的健的数量
	 *
	 * @param key
	 * @return
	 */
	@Override
	public int rank(Key key) {
		Node node = floor(root, key);
		if (node == null) {
			return 0;
		}
		int cmp = key.compareTo(node.key);
		if (cmp == 0) {
			return node.n - 1;
		}
		return node.n;
	}

	/**
	 * 排名为 i 的健
	 *
	 * @param i
	 * @return
	 */
	@Override
	public Key select(int i) {
		return null;
	}

	/**
	 * [lo, ..., hi]之间的所有键，已排序
	 *
	 * @param lo
	 * @param hi
	 * @return
	 */
	@Override
	public Iterable<Key> keys(Key lo, Key hi) {
		Queue<Key> queue = new LinkedList();
		if (lo == null && hi == null) {
			return queue;
		}
		if (lo == null) {
			lo = min();
		}
		if (hi == null) {
			hi = max();
		}
		if (lo.compareTo(hi) > 0) {
			return queue;
		}
		queue = keys(root, lo, hi, queue);

		return queue;
	}

	private Queue<Key> keys(Node node, Key lo, Key hi,
	                        Queue<Key> queue) {
		if (node == null) {
			return queue;
		}
		int cmplo = lo.compareTo(node.key);
		int cmphi = hi.compareTo(node.key);
		//  lo < node.key
		if (cmplo < 0) {
			queue = keys(node.left, lo, hi, queue);
		}
		//  lo <= node.key && hi >= node.key
		if (cmplo <= 0 && cmphi >= 0) {
			queue.offer(node.key);
		}
		//  hi > node.key
		if (cmphi > 0) {
			queue = keys(node.right, lo, hi, queue);
		}
		return queue;
	}

	/**
	 * 将健值对存入表中(若值为空则将健 key 从表中删除)
	 *
	 * @param key
	 * @param val
	 */
	@Override
	public void put(Key key, Value val) {
		if (key == null) {
			return;
		}
		root = put(root, key, val);
	}

	private Node put(Node node, Key key, Value val) {
		if (node == null) {
			return new Node(key, val, 1);
		}
		int cmp = key.compareTo(node.key);
		if (cmp < 0) {
			//  key < node.key
			node.left = put(node.left, key, val);
		} else if (cmp > 0) {
			//  key > node.key
			node.right = put(node.right, key, val);
		} else if (val == null) {
			//  key == node.key && val == null
			// TODO: 2019-12-09 val == null 时删除结点
		} else {
			//  key == node.key && val != null
			node.val = val;
		}
		node.n = size(node.left) + size(node.right) + 1;
		return node;
	}

	/**
	 * 获取健 key 对应的值(若健 key 不存在则返回空null)
	 *
	 * @param key
	 * @return @Nullable
	 */
	@Override
	public Value get(Key key) {
		if (key == null) {
			return null;
		}
		return get(root, key);
	}

	private Value get(Node node, Key key) {
		if (node == null) {
			return null;
		}
		int cmp = key.compareTo(node.key);
		//  key < node.key
		if (cmp < 0) {
			return get(node.left, key);
		}
		//  key > node.key
		if (cmp > 0) {
			return get(node.right, key);
		}
		//  key == node.key
		return node.val;
	}

	/**
	 * 表中的健值对数量
	 *
	 * @return
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(Node node) {
		return node == null ? 0 : node.n;
	}

	private class Node {
		Key key;
		Value val;

		Node left, right;

		int n;

		public Node(Key key, Value val, int n) {
			this.key = key;
			this.val = val;

			this.n = n;
		}

	}

	public static void main(String[] args) {
		Integer[] params = {0, 11, 22, 33, 44, 5, 66, 77, 88, 99};
		StdRandom.shuffle(params);

		BinarySearchTree<Integer, String> binarySearchTree = new BinarySearchTree();
		for (Integer param : params) {
			binarySearchTree.put(param, Integer.toString(param));
		}
		keys(binarySearchTree);
	}

	private static void keys(BinarySearchTree<Integer, String> binarySearchTree) {
		Iterable<Integer> iterable = binarySearchTree.keys();
		System.out.print("keys(): ");
		iterable.forEach((key) -> System.out.print(key + " "));
		System.out.println();

		iterable = binarySearchTree.keys(-999, null);
		System.out.print("keys(-999, null): ");
		iterable.forEach((key) -> System.out.print(key + " "));
		System.out.println();

		iterable = binarySearchTree.keys(null, 999);
		System.out.print("keys(null, 999): ");
		iterable.forEach((key) -> System.out.print(key + " "));
		System.out.println();

		iterable = binarySearchTree.keys(0, 11);
		System.out.print("keys(0, 11): ");
		iterable.forEach((key) -> System.out.print(key + " "));
		System.out.println();

		iterable = binarySearchTree.keys(88, 99);
		System.out.print("keys(88, 99): ");
		iterable.forEach((key) -> System.out.print(key + " "));
		System.out.println();

		iterable = binarySearchTree.keys(-1, 0);
		System.out.print("keys(-1, 0): ");
		iterable.forEach((key) -> System.out.print(key + " "));
		System.out.println();

		iterable = binarySearchTree.keys(99, 999);
		System.out.print("keys(99, 999): ");
		iterable.forEach((key) -> System.out.print(key + " "));
		System.out.println();

		iterable = binarySearchTree.keys(55, 55);
		System.out.print("keys(55, 55): ");
		iterable.forEach((key) -> System.out.print(key + " "));
		System.out.println();

		iterable = binarySearchTree.keys(5, 5);
		System.out.print("keys(5, 5): ");
		iterable.forEach((key) -> System.out.print(key + " "));
		System.out.println();
	}

}
