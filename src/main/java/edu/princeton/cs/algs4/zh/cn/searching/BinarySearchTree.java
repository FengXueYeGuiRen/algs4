package edu.princeton.cs.algs4.zh.cn.searching;

import edu.princeton.cs.algs4.StdRandom;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

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

		return minNode == null ? null : minNode.key;
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

		return maxNode == null ? null : maxNode.key;
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
		//  key == node.key
		if (cmp == 0) {
			return node;
		}
		//  key < node.key
		if (cmp < 0) {
			return floor(node.left, key);
		}
		//  key > node.key
		Node right = floor(node.right, key);
		return right == null ? node : right;
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
		//  key == node.key
		if (cmp == 0) {
			return node;
		}
		//  key > node.key
		if (cmp > 0) {
			return ceiling(node.right, key);
		}
		//  key < node.key
		Node left = ceiling(node.left, key);
		return left == null ? node : left;
	}

	/**
	 * 小于 key 的健的数量
	 *
	 * @param key
	 * @return
	 */
	@Override
	public int rank(Key key) {
		if (key == null) {
			return 0;
		}
		return rank(key, root);
	}

	/**
	 * 返回以 node 为根结点的子树中小于 node.key 的键的数量
	 *
	 * @param key
	 * @param node
	 * @return
	 */
	private int rank(Key key, Node node) {
		if (node == null) {
			return 0;
		}
		int cmp = key.compareTo(node.key);
		//  key == node.key
		if (cmp == 0) {
			return size(node.left);
		}
		//  key < node.key
		if (cmp < 0) {
			return rank(key, node.left);
		}
		//  key > node.key
		return size(node.left) + 1 + rank(key, node.right);
	}

	/**
	 * 排名为 i 的健
	 *
	 * @param i
	 * @return
	 */
	@Override
	public Key select(int i) {
		Node node = select(root, i);
		return node == null ? null : node.key;
	}

	/**
	 * 返回排名为 i 的结点
	 *
	 * @param node
	 * @param i
	 * @return
	 */
	private Node select(Node node, int i) {
		if (node == null || i < 0) {
			return null;
		}
		int leftN = size(node.left);
		if (i == leftN) {
			return node;
		}
		if (i < leftN) {
			return select(node.left, i);
		}
		//  i > node.n
		return select(node.right, i - leftN - 1);
	}

	/**
	 * 删除最小的键
	 */
	@Override
	public void deleteMin() {
		root = deleteMin(root);
	}

	private Node deleteMin(Node node) {
		if (node == null) {
			return null;
		}
		if (node.left == null) {
			return node.right;
		}
		node.left = deleteMin(node.left);
		node.n = size(node.left) + size(node.right) + 1;
		return node;
	}

	/**
	 * 删除最大的健
	 */
	@Override
	public void deleteMax() {
		root = deleteMax(root);
	}

	private Node deleteMax(Node node) {
		if (node == null) {
			return null;
		}
		if (node.right == null) {
			return node.left;
		}
		node.right = deleteMax(node.right);
		node.n = size(node.left) + size(node.right) + 1;
		return node;
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
		if ((lo == null && hi == null)
				|| isEmpty()) {
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
		if (val == null) {
			delete(key);
			return;
		}
		root = put(root, key, val);
	}

	/**
	 * 查找key，找到则更新它的值，否则为它创建一个新的结点
	 * 如果 key 存在于以 node 为跟结点的子树中则更新它的值；
	 * 否则将以 key 和 val 为健值对的新结点插入到该子树中
	 *
	 * @param node
	 * @param key
	 * @param val
	 * @return
	 */
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
		} else {
			//  key == node.key && val != null
			node.val = val;
		}
		node.n = size(node.left) + 1 + size(node.right);
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

	/**
	 * 在以 node 为跟结点的子树中查找并返回 key 所对应的值；
	 * 如果找不到则返回 null
	 *
	 * @param node
	 * @param key
	 * @return @Nullable
	 */
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
	 * 从表中删去健 key(及其对应的值)
	 *
	 * @param key
	 */
	@Override
	public Value delete(Key key) {
		if (key == null) {
			return null;
		}
		root = delete(key, root);
		return null;
	}

	private Node delete(Key key, Node node) {
		if (key == null || node == null) {
			return node;
		}
		int cmp = key.compareTo(node.key);
		if (cmp < 0) {
			//  key < node.key
			node.left = delete(key, node.left);
		} else if (cmp > 0) {
			//  key > node.key
			node.right = delete(key, node.right);
		} else {
			//  key == node.key

			if (node.left == null) {
				return node.right;
			}
			if (node.right == null) {
				return node.left;
			}
			//  node.left != null && node.right != null
			Node deleting = node;
			node = min(deleting.right);
			node.left = deleting.left;
			node.right = deleteMin(deleting.right);
		}
		node.n = size(node.left) + size(node.right) + 1;
		return node;
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

		select(binarySearchTree);
		rank(binarySearchTree);
		delete(binarySearchTree);
		keys(binarySearchTree);

		for (Integer param : params) {
			binarySearchTree.put(param, Integer.toString(param));
		}
		select(binarySearchTree);
		rank(binarySearchTree);
		delete(binarySearchTree);

		for (Integer param : params) {
			binarySearchTree.put(param, Integer.toString(param));
		}
		for (Integer param : params) {
			binarySearchTree.put(param, Integer.toString(param));
		}
		keys(binarySearchTree);
	}

	private static void select(BinarySearchTree<Integer, String> binarySearchTree) {
		System.out.print(
				"select(" + Integer.MIN_VALUE + "): "
						+ binarySearchTree.select(Integer.MIN_VALUE));
		if (binarySearchTree == null || binarySearchTree.root == null) {
			System.out.println("\n");
			return;
		}
		for (int i = 0; i < binarySearchTree.root.n; ++i) {
			if (i > 0) {
				System.out.print("; ");
			}
			System.out.print("select(" + i + "): "
					+ binarySearchTree.select(i));
		}
		System.out.print(
				"select(" + Integer.MAX_VALUE + "): "
						+ binarySearchTree.select(Integer.MAX_VALUE));
		System.out.println("\n");
	}

	private static void rank(BinarySearchTree<Integer, String> binarySearchTree) {
		Integer randomKey = new Random().nextInt();
		System.out.print("rank(" + randomKey + "): " + binarySearchTree.rank(randomKey) + "; ");

		for (Integer key : binarySearchTree.keys()) {
			System.out.print("rank(" + key + "): " + binarySearchTree.rank(key) + "; ");
		}
		System.out.println("\n");
	}

	private static void delete(BinarySearchTree<Integer, String> binarySearchTree) {
		System.out.println("min(): " + binarySearchTree.min() + ". deleteMin");
		binarySearchTree.deleteMin();
		System.out.println("max(): " + binarySearchTree.max() + ". deleteMax");
		binarySearchTree.deleteMax();

		System.out.println("put(" + 66 + ", null)");
		binarySearchTree.put(66, null);

		if (binarySearchTree.root == null) {
			System.out.println();
			return;
		}
		int i = binarySearchTree.root.n / 2;
		Integer key = binarySearchTree.select(i);
		System.out.println("select(" + i + "): " + key + ", delete");
		binarySearchTree.delete(key);

		i = binarySearchTree.root.n / 2;
		key = binarySearchTree.select(i);
		System.out.println("select(" + i + "): " + key + ", put(" + key + ", null)");
		binarySearchTree.put(key, null);

		System.out.println();
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
		System.out.println();
	}

}
