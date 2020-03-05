package edu.princeton.cs.algs4.zh.cn.string;

import edu.princeton.cs.algs4.Queue;

/**
 * 基于单词查找树的符号表(5.2.1 算法 5.4)
 *
 * @author FXYGR @date 2020-03-04
 */
public class TrieSymbolTable<Value> implements StringSymbolTable<Value> {

	/**
	 * 基数
	 */
	private static int R = 256;
	/**
	 * 单词查找树的跟结点
	 */
	private Node root;

	private static class Node<Value> {
		private Value val;
		private Node[] nexts = new Node[R];
	}

	/**
	 * 向表中插入键值对(如果值为 null 则删除键 key)
	 *
	 * @param key
	 * @param val
	 */
	@Override
	public void put(String key, Value val) {
		if (key == null || "".equals(key.trim())) {
			return;
		}
		root = put(root, key.trim(), val, 0);
	}

	private Node put(Node node, String key, Value val, int d) {
		if (node == null) {
			node = new Node();
		}
		//  如果 key 存在于以 x 为根结点的子单词查找树中则更新与它相关联的值
		if (key.length() == d) {
			node.val = val;
			return node;
		}
		//  找到第 d 个字符所对应的子单词查找树
		char ch = key.charAt(d);
		node.nexts[ch] = put(node.nexts[ch], key, val, d + 1);
		return node;
	}

	/**
	 * 键 key 所对应的值(如果键不存在则返回 null)
	 *
	 * @param key
	 * @return
	 */
	@Override
	public Value get(String key) {
		if (key == null || "".equals(key.trim())) {
			return null;
		}
		Node node = get(root, key.trim(), 0);
		if (node == null) {
			return null;
		}
		return (Value) node.val;
	}

	private Node get(Node node, String key, int d) {
		//  返回以 x 作为根结点的子单词查找树
		if (node == null || key.length() == d) {
			return node;
		}
		//  找到第 d 个字符所对应的子单词查找树
		char ch = key.charAt(d);
		return get(node.nexts[ch], key, d + 1);
	}

	/**
	 * 删除键 key(和它的值)
	 *
	 * @param key
	 */
	@Override
	public void delete(String key) {

	}

	/**
	 * 表中是否保存着 key 的值
	 *
	 * @param key
	 * @return
	 */
	@Override
	public boolean contains(String key) {
		return false;
	}

	/**
	 * 符号表是否为空
	 *
	 * @return
	 */
	@Override
	public boolean isEmpty() {
		return false;
	}

	/**
	 * s 的前缀中最长的键
	 *
	 * @param s
	 * @return
	 */
	@Override
	public String longestPrefixOf(String s) {
		return null;
	}

	/**
	 * 所有以 s 为前缀的键
	 *
	 * @param s
	 * @return
	 */
	@Override
	public Iterable<String> keysWithPrefix(String s) {
		Queue<String> q = new Queue();
		if (s == null) {
			return q;
		}
		collect(get(root, s, 0), s, q);
		return q;
	}

	private void collect(Node node, String pre, Queue<String> q) {
		if (node == null) {
			return;
		}
		if (node.val != null) {
			q.enqueue(pre);
		}
		for (char ch = 0; ch < R; ++ch) {
			collect(node.nexts[ch], pre + ch, q);
		}
	}

	/**
	 * 所有和 s 匹配的键(其中"."能够匹配任意字符)
	 *
	 * @param s
	 * @return
	 */
	@Override
	public Iterable<String> keysThatMatch(String s) {
		return null;
	}

	/**
	 * 键值对的数量
	 *
	 * @return
	 */
	@Override
	public int size() {
		return 0;
	}

	/**
	 * 符号表中的所有键
	 *
	 * @return
	 */
	@Override
	public Iterable<String> keys() {
		return keysWithPrefix("");
	}

}
