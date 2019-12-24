package edu.princeton.cs.algs4.zh.cn.searching;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * 顺序查找(基于无序链表)(3.1.4 算法3.1)
 * {@link edu.princeton.cs.algs4.SequentialSearchST}
 *
 * @author FXYGR @date 2019-12-04
 */
public class SequentialSearchSymbolTable<Key, Value>
		implements SymbolTable<Key, Value> {

	private int n;
	/**
	 * 链表首结点
	 */
	private Node first;

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
		//  查找给定的健，找到则更新其值，否则在表中新建结点
		Node node = getNode(key);
		if (node == null) {
			//  未命中，新建结点
			add(key, val);
			return;
		}
		// 命中
		if (val == null) {
			//  命中，删除
			delete(node);
			return;
		}
		//  命中，更新
		node.val = val;
	}

	private void delete(Node node) {
		if (node == null) {
			return;
		}
		if (node.previous != null) {
			node.previous.next = node.next;
		} else if (node == first) {
			first = null;
		}
		if (node.next != null) {
			node.next.previous = node.previous;
		}
		--n;
	}

	private void add(Key key, Value val) {
		if (val == null) {
			return;
		}
		first = new Node(key, val, first, null);
		if (first != null && first.next != null) {
			first.next.previous = first;
		}
		++n;
	}

	/**
	 * 获取健 key 对应的值(若健 key 不存在则返回空null)
	 *
	 * @param key
	 * @return
	 */
	@Override
	public Value get(Key key) {
		if (key == null) {
			return null;
		}
		//  查找给定的值，返回相关联的值
		Node node = getNode(key);
		if (node != null) {
			//  命中
			return node.val;
		}
		//  未命中
		return null;
	}

	private Node getNode(Key key) {
		for (Node node = first; node != null; node = node.next) {
			if (key.equals(node.key)) {
				//  命中
				return node;
			}
		}
		//  未命中
		return null;
	}

	/**
	 * 表中的健值对数量
	 *
	 * @return
	 */
	@Override
	public int size() {
		return n;
	}

	/**
	 * 表中的所有健的集合
	 *
	 * @return
	 */
	@Override
	public Iterable<Key> keys() {
		return new Iterable<Key>() {
			Node node = null;

			@Override
			public Iterator<Key> iterator() {

				return new Iterator<Key>() {
					@Override
					public boolean hasNext() {
						if (node == null && first != null) {
							return true;
						}
						return node != null && node.next != null;
					}

					@Override
					public Key next() {
						if (node == null) {
							node = first;
							return first.key;
						}
						node = node.next;
						return node.key;
					}
				};
			}
		};
	}

	/**
	 * 链表结点的定义
	 */
	private class Node {
		Key key;
		Value val;

		Node next;
		Node previous;

		public Node(Key key, Value val,
		            Node next, Node previous) {
			this.key = key;
			this.val = val;

			this.next = next;
			this.previous = previous;
		}

	}

	public static void main(String[] args) {
		SequentialSearchSymbolTable<String, Integer> st =
				new SequentialSearchSymbolTable();
		for (String key : st.keys()) {
			StdOut.println(key + " " + st.get(key));
		}
		for (int i = 0; !StdIn.isEmpty(); i++) {
			String key = StdIn.readString();
			st.put(key, i);
		}
		StdOut.println("输出: ");
		for (String key : st.keys()) {
			StdOut.println(key + " " + st.get(key));
		}
	}

}
