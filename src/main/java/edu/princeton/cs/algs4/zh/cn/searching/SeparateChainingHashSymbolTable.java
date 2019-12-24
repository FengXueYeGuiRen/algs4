package edu.princeton.cs.algs4.zh.cn.searching;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * 基于拉链法的散列表(3.4.2 算法3.5)
 * {@link edu.princeton.cs.algs4.SeparateChainingHashST}
 *
 * @author FXYGR @date 2019-12-24
 */
public class SeparateChainingHashSymbolTable<Key, Value>
		implements SymbolTable<Key, Value> {

	/**
	 * 健值对总数
	 */
	private int n;
	/**
	 * 散列表的大小
	 */
	private int m;
	/**
	 * 存放链表对象的数组
	 */
	private SequentialSearchSymbolTable<Key, Value>[] st;

	public SeparateChainingHashSymbolTable(int m) {
		this.m = m;

		st = (SequentialSearchSymbolTable<Key, Value>[]) new SequentialSearchSymbolTable[m];

		for (int i = 0; i < m; ++i) {
			st[i] = new SequentialSearchSymbolTable();
		}
	}

	public SeparateChainingHashSymbolTable() {
		this(997);
	}

	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % m;
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
		SequentialSearchSymbolTable<Key, Value> s = st[hash(key)];
		int size = s.size();

		s.put(key, val);

		n += (s.size() - size);
	}

	/**
	 * 获取健 key 对应的值(若健 key 不存在则返回空null)
	 *
	 * @param key
	 * @return
	 */
	@Override
	public Value get(Key key) {
		if (key == null || isEmpty()) {
			return null;
		}
		return st[hash(key)].get(key);
	}

	/**
	 * 从表中删去健 key(及其对应的值)
	 *
	 * @param key
	 */
	@Override
	public Value delete(Key key) {
		if (key == null || isEmpty()) {
			return null;
		}
		SequentialSearchSymbolTable<Key, Value> s = st[hash(key)];
		int size = s.size();

		Value deletedValue = s.delete(key);

		n += (s.size() - size);
		return deletedValue;
	}

	/**
	 * 健 key 在表中是否有对应的值(健 key 是否存在于表中)
	 *
	 * @param key
	 * @return
	 */
	@Override
	public boolean contains(Key key) {
		if (key == null || isEmpty()) {
			return false;
		}
		return st[hash(key)].contains(key);
	}

	/**
	 * 表是否为空
	 *
	 * @return
	 */
	@Override
	public boolean isEmpty() {
		return size() < 1;
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
		Queue<Key> queue = new LinkedList();
		for (SequentialSearchSymbolTable<Key, Value> s : st) {
			for (Key key : s.keys()) {
				queue.offer(key);
			}
		}
		return queue;
	}

	public static void main(String[] args) {
		println(null);

		println(new SeparateChainingHashSymbolTable(0));

		int[] nums = {0, 11, 22, 33, 44, 5, 66, 77, 88, 99};

		SeparateChainingHashSymbolTable<Integer, String> separateChainingHashST =
				put(nums);
		println(separateChainingHashST);

		deletes(nums, separateChainingHashST);

		separateChainingHashST = put(nums);
		println(separateChainingHashST);
	}

	private static SeparateChainingHashSymbolTable<Integer, String> put(int[] nums) {
		System.out.print("Input: ");
		if (nums == null || nums.length < 1) {
			System.out.println(nums);
			return null;
		}
		for (int num : nums) {
			System.out.print(num + " ");
		}
		System.out.println();
		SeparateChainingHashSymbolTable<Integer, String> separateChainingHashST =
				new SeparateChainingHashSymbolTable(2);
		for (int num : nums) {
			separateChainingHashST.put(num, String.valueOf(num));
		}
		return separateChainingHashST;
	}

	private static void println(
			SeparateChainingHashSymbolTable<Integer, String> separateChainingHashST) {
		System.out.print("Output: ");
		if (separateChainingHashST == null || separateChainingHashST.isEmpty()) {
			System.out.println();
			return;
		}
		System.out.print("(size: " + separateChainingHashST.size() + ")");
		for (Integer key : separateChainingHashST.keys()) {
			if (separateChainingHashST.contains(key)) {
				System.out.print("{" + key + ", " + separateChainingHashST.get(key) + "}");
			} else {
				System.out.print("{" + key + ", not contain}");
			}
		}
		System.out.println("\n\n");
	}

	private static void deletes(
			int[] nums, SeparateChainingHashSymbolTable<Integer, String> separateChainingHashST) {
		if (separateChainingHashST == null || separateChainingHashST.isEmpty()) {
			return;
		}
		for (int num : nums) {
			separateChainingHashST.delete(num);
			System.out.print("deleted: " + num + "; ");
			println(separateChainingHashST);
		}
		int num = new Random().nextInt();
		separateChainingHashST.delete(num);
		System.out.print("deleted: " + num + "; ");
		println(separateChainingHashST);
	}

}
