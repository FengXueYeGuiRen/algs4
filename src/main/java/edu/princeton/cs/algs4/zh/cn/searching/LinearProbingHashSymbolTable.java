package edu.princeton.cs.algs4.zh.cn.searching;

import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

/**
 * 基于线性探测的散列符号表(3.4.3 算法3.6)
 * {@link edu.princeton.cs.algs4.LinearProbingHashST}
 *
 * @author FXYGR @date 2019-12-25
 */
public class LinearProbingHashSymbolTable<Key, Value>
		implements SymbolTable<Key, Value> {

	private static final int INIT_CAPACITY = 4;

	/**
	 * 符号表中健值对的总数
	 */
	private int n;
	/**
	 * 线性探测表的大小
	 */
	private int m;
	/**
	 * 健
	 */
	private Key[] keys;
	/**
	 * 值
	 */
	private Value[] vals;

	public LinearProbingHashSymbolTable() {
		this(INIT_CAPACITY);
	}

	public LinearProbingHashSymbolTable(int capacity) {
		this.n = 0;
		this.m = capacity;
		this.keys = (Key[]) new Object[m];
		this.vals = (Value[]) new Object[m];
	}

	/**
	 * 调整线性探测散列表
	 *
	 * @param capacity
	 */
	private void resize(int capacity) {
		LinearProbingHashSymbolTable<Key, Value> t =
				new LinearProbingHashSymbolTable<>(capacity);
		for (int i = 0; i < this.m; ++i) {
			if (keys[i] != null) {
				t.put(keys[i], vals[i]);
			}
		}
		this.m = t.m;

		this.keys = t.keys;
		this.vals = t.vals;
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
		if (val == null) {
			delete(key);
			return;
		}
		if (n == m / 2) {
			//  将 m 加倍
			resize(m * 2);
		}
		int i = hash(key);
		while (keys[i] != null && !keys[i].equals(key)) {
			i = (++i) % m;
		}
		keys[i] = key;
		vals[i] = val;
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
		if (key == null || isEmpty()) {
			return null;
		}
		Value value = null;
		for (int i = hash(key); keys[i] != null; i = (++i) % m) {
			if (keys[i].equals(key)) {
				value = vals[i];
				break;
			}
		}
		return value;
	}

	/**
	 * 从表中删去健 key(及其对应的值)
	 *
	 * @param key
	 */
	@Override
	public Value delete(Key key) {
		if (key == null || !contains(key)) {
			return null;
		}
		int i = hash(key);
		while (!key.equals(keys[i])) {
			i = (++i) % m;
		}
		Value val = vals[i];

		keys[i] = null;
		vals[i] = null;

		i = (++i) % m;
		while (keys[i] != null) {
			Key keyToRedo = keys[i];
			Value valToRedo = vals[i];
			keys[i] = null;
			vals[i] = null;

			--n;
			put(keyToRedo, valToRedo);

			i = (++i) % m;
		}
		--n;
		if (n > 0 && n == m / 8) {
			resize(m / 2);
		}
		return val;
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
		return get(key) != null;
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
		for (Key key : keys) {
			if (key != null) {
				queue.offer(key);
			}
		}
		return queue;
	}

	public static void main(String[] args) {
		println(null);

		println(new LinearProbingHashSymbolTable(0));

		String[] strs = {"0", "11", "22", "33", "44", "5", "66", "77", "88", "99"};

		LinearProbingHashSymbolTable<String, String> linearProbingHashST = put(strs);
		println(linearProbingHashST);

		deletes(strs, linearProbingHashST);

		linearProbingHashST = put(strs);
		linearProbingHashST = put(strs);
		println(linearProbingHashST);

		for (String str : strs) {
			linearProbingHashST.put(str, null);
			System.out.print("put(" + str + ", null): ");
			println(linearProbingHashST);
		}
	}

	private static LinearProbingHashSymbolTable<String, String> put(String[] strs) {
		System.out.print("Input: ");
		if (strs == null || strs.length < 1) {
			System.out.println(strs);
			return null;
		}
		for (String str : strs) {
			System.out.print(str + " ");
		}
		System.out.println();
		LinearProbingHashSymbolTable<String, String> linearProbingHashST =
				new LinearProbingHashSymbolTable(1);
		for (String str : strs) {
			linearProbingHashST.put(str, str);
		}
		return linearProbingHashST;
	}

	private static void println(
			LinearProbingHashSymbolTable<String, String> linearProbingHashST) {
		System.out.print("Output: ");
		if (linearProbingHashST == null || linearProbingHashST.isEmpty()) {
			System.out.println("\n");
			return;
		}
		System.out.print("(size: " + linearProbingHashST.size() + ")");
		for (String key : linearProbingHashST.keys()) {
			if (linearProbingHashST.contains(key)) {
				System.out.print("{" + key + ", " + linearProbingHashST.get(key) + "}");
			} else {
				System.out.print("{" + key + ", not contain}");
			}
		}
		System.out.println("\n");
	}

	private static void deletes(
			String[] strs, LinearProbingHashSymbolTable<String, String> linearProbingHashST) {
		if (linearProbingHashST == null || linearProbingHashST.isEmpty()) {
			return;
		}
		for (String str : strs) {
			linearProbingHashST.delete(str);
			System.out.print("deleted: " + str + "; ");
			println(linearProbingHashST);
		}
		String str = UUID.randomUUID().toString();
		linearProbingHashST.delete(str);
		System.out.print("deleted: " + str + "; ");
		println(linearProbingHashST);
	}

}
