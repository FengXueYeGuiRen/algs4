package edu.princeton.cs.algs4.zh.cn.searching;

/**
 * 红黑二叉查找树(3.3.2 算法3.4)
 * {@link edu.princeton.cs.algs4.RedBlackBST}
 *
 * @author FXYGR @date 2019-12-13
 */
public class RedBlackBinarySearchTree<Key extends Comparable<Key>, Value>
		implements OrderedSymbolTable<Key, Value> {

	private Node root;

	private class Node {
		//  健
		Key key;
		//  相关联的值
		Value val;
		//  左右子树
		Node left, right;
		//  这棵子树重的结点总数
		int n;
		//  由其父结点指向它的链接的颜色
		boolean isRed;

		public Node(Key key, Value val, int n, boolean isRed) {
			this.key = key;
			this.val = val;
			this.n = n;
			this.isRed = isRed;
		}

	}

	private boolean isRed(Node node) {
		return node == null ? false : node.isRed;
	}

	private Node rotateLeft(Node node) {
		if (node == null || !isRed(node.right)) {
			return node;
		}
		Node t = node;
		node = node.right;
		node.isRed = t.isRed;

		t.right = node.left;

		node.left = t;
		t.isRed = true;

		t.n = size(t.left) + 1 + size(t.right);
		node.n = size(node.left) + 1 + size(node.right);
		return node;
	}

	private Node rotateRight(Node node) {
		if (node == null || !isRed(node.left)) {
			return node;
		}
		Node t = node;
		node = node.left;
		node.isRed = t.isRed;

		t.left = node.right;

		node.right = t;
		t.isRed = true;

		t.n = size(t.left) + 1 + size(t.right);
		node.n = size(node.left) + 1 + size(node.right);

		if (isRed(node.left) && isRed(node.right)) {
			flipColors(node);
		}
		return node;
	}

	private void flipColors(Node node) {
		if (node == null
				|| !isRed(node.left)
				|| !isRed(node.right)) {
			return;
		}
		node.left.isRed = false;
		node.right.isRed = false;

		node.isRed = true;
		if (node == root) {
			node.isRed = false;
		}
	}

	/**
	 * 最小的健
	 *
	 * @return
	 */
	@Override
	public Key min() {
		return null;
	}

	/**
	 * 最大的键
	 *
	 * @return
	 */
	@Override
	public Key max() {
		return null;
	}

	/**
	 * 小于等于 key 的最大值
	 *
	 * @param key
	 * @return
	 */
	@Override
	public Key floor(Key key) {
		return null;
	}

	/**
	 * 大于等于 key 的最小值
	 *
	 * @param key
	 * @return
	 */
	@Override
	public Key ceiling(Key key) {
		return null;
	}

	/**
	 * 小于 key 的健的数量
	 *
	 * @param key
	 * @return
	 */
	@Override
	public int rank(Key key) {
		return 0;
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
	 * 删除最小的键
	 */
	@Override
	public void deleteMin() {

	}

	/**
	 * 删除最大的健
	 */
	@Override
	public void deleteMax() {

	}

	/**
	 * [lo, ..., hi]之间键的数量
	 *
	 * @param lo
	 * @param hi
	 * @return
	 */
	@Override
	public int size(Key lo, Key hi) {
		return 0;
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
		return null;
	}

	/**
	 * 表中所有键的集合，已排序
	 *
	 * @return
	 */
	@Override
	public Iterable<Key> keys() {
		return null;
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
			// TODO: 2019-12-19 delete
			return;
		}
		root = put(root, key, val);
		root.isRed = false;
	}

	private Node put(Node node, Key key, Value val) {
		if (node == null) {
			//  标准的插入操作，和父结点用红链接相连
			return new Node(key, val, 1, true);
		}
		int cmp = key.compareTo(node.key);
		if (cmp < 0) {
			node.left = put(node.left, key, val);
		} else if (cmp > 0) {
			node.right = put(node.right, key, val);
		} else {
			node.val = val;
		}
		if (isRed(node.left) && isRed(node.right)) {
			flipColors(node);
		}
		if (isRed(node.right) && !isRed(node.left)) {
			node = rotateLeft(node);
		}
		if (isRed(node.left) && isRed(node.left.left)) {
			node = rotateRight(node);
		}
		node.n = size(node.left) + 1 + size(node.right);
		return node;
	}

	/**
	 * 获取健 key 对应的值(若健 key 不存在则返回空null)
	 *
	 * @param key
	 * @return
	 */
	@Override
	public Value get(Key key) {
		return null;
	}

	/**
	 * 从表中删去健 key(及其对应的值)
	 *
	 * @param key
	 */
	@Override
	public Value delete(Key key) {
		return null;
	}

	/**
	 * 健 key 在表中是否有对应的值(健 key 是否存在于表中)
	 *
	 * @param key
	 * @return
	 */
	@Override
	public boolean contains(Key key) {
		return false;
	}

	/**
	 * 表是否为空
	 *
	 * @return
	 */
	@Override
	public boolean isEmpty() {
		return false;
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

	public static void main(String[] args) {
		RedBlackBinarySearchTree<String, String> redBlackBinarySearchTree;

		redBlackBinarySearchTree =
				put(new String[]{"S", "E", "A", "R", "C", "H", "X", "M", "P", "L" });
		System.out.println(redBlackBinarySearchTree);
	}

	private static RedBlackBinarySearchTree<String, String> put(String[] strs) {
		RedBlackBinarySearchTree<String, String> redBlackBinarySearchTree =
				new RedBlackBinarySearchTree();
		for (String str : strs) {
			redBlackBinarySearchTree.put(str, str);
		}
		return redBlackBinarySearchTree;
	}

}
