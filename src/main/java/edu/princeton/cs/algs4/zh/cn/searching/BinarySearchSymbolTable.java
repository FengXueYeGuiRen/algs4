package edu.princeton.cs.algs4.zh.cn.searching;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * 二分查找(基于有序数组)(3.1.5 算法3.2)
 * {@link edu.princeton.cs.algs4.BinarySearchST}
 *
 * @author FXYGR @date 2019-12-04
 */
public class BinarySearchSymbolTable<Key extends Comparable<Key>, Value>
		implements OrderedSymbolTable<Key, Value> {

	private Key[] keys;
	private Value[] vals;

	private int n;

	public BinarySearchSymbolTable(int capacity) {
		keys = (Key[]) new Comparable[capacity];
		vals = (Value[]) new Object[capacity];
	}

	/**
	 * 最小的健
	 *
	 * @return @Nullable
	 */
	@Override
	public Key min() {
		if (size() < 1) {
			return null;
		}
		return keys[0];
	}

	/**
	 * 最大的键
	 *
	 * @return @Nullable
	 */
	@Override
	public Key max() {
		if (size() < 1) {
			return null;
		}
		return keys[size() - 1];
	}

	/**
	 * 小于等于 key 的最大值
	 *
	 * @param key
	 * @return
	 */
	@Override
	public Key floor(Key key) {
		if (key == null) {
			return null;
		}
		int i = rank(key);
		Key iKey = select(i);
		if (i <= 0 || key.compareTo(iKey) >= 0) {
			return iKey;
		}
		return select(--i);
	}

	/**
	 * 大于等于 key 的最小值
	 *
	 * @param key
	 * @return
	 */
	@Override
	public Key ceiling(Key key) {
		if (key == null) {
			return null;
		}
		int i = rank(key);
		Key iKey = select(i);
		if (i >= size() - 1 || key.compareTo(iKey) <= 0) {
			return iKey;
		}
		return select(++i);
	}

	/**
	 * 小于 key 的健的数量
	 * 二分查找(迭代)
	 *
	 * @param key
	 * @return
	 */
	@Override
	public int rank(Key key) {
		if (key == null || size() < 1) {
			return 0;
		}
		int lo = 0, hi = size() - 1;

		while (lo <= hi) {
			int mid = (lo + hi) / 2;

			int cmp = key.compareTo(keys[mid]);

			if (cmp < 0) {
				hi = mid - 1;
			} else if (cmp > 0) {
				lo = mid + 1;
			} else {
				lo = mid;
				break;
			}
		}
		return lo >= size() ? size() - 1 : lo;
	}

	/**
	 * 排名为 i 的健
	 *
	 * @param i
	 * @return
	 */
	@Override
	public Key select(int i) {
		if (i < 0 || i >= size()) {
			return null;
		}
		return keys[i];
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
		return new Iterable<Key>() {
			int i = rank(lo);

			@Override
			public Iterator<Key> iterator() {

				return new Iterator<Key>() {
					@Override
					public boolean hasNext() {
						return i < size() && select(i).compareTo(keys[size() - 1]) <= 0;
					}

					@Override
					public Key next() {
						return select(i++);
					}
				};
			}
		};
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
		//  查找键，找到则更新值，否则创建新的元素
		int i = rank(key);
		if (i < 0 || i > size()) {
			return;
		}
		//  未命中
		if (keys[i] == null
				|| keys[i].compareTo(key) != 0) {
			insert(i, key, val);
			return;
		}//  命中
		//  命中 && val == null
		if (val == null) {
			delete(i);
			return;
		}// 命中 && val != null
		vals[i] = val;
	}

	private void insert(int index, Key key, Value val) {
		if (index >= keys.length
				|| val == null
				|| key == null) {
			return;
		}
		for (int i = size(); i > index; --i) {
			keys[i] = keys[i - 1];
			vals[i] = vals[i - 1];
		}
		keys[index] = key;
		vals[index] = val;
		++n;
	}

	private void delete(int index) {
		if (index >= size()) {
			return;
		}
		while (index < size() - 1) {
			keys[index] = keys[index + 1];
			vals[index] = vals[index + 1];

			++index;
		}
		--n;
		keys[n] = null;
		vals[n] = null;
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
		int i = rank(key);
		if (i < size()
				&& keys[i].compareTo(key) == 0) {
			return vals[i];
		}
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

	public static void main(String[] args) {
		BinarySearchSymbolTable<Integer, String> st =
				new BinarySearchSymbolTable(10);
		for (Integer key : st.keys()) {
			StdOut.println(key + " " + st.get(key));
		}
		st.put(44, null);
		st.put(99, "99");
		st.put(88, "88");
		st.put(77, "77");
		st.put(66, "66");
		st.put(44, "44");
		st.put(55, "55");
		st.put(33, "00");
		st.put(33, "00");
		st.put(33, null);
		st.put(33, "33");
		st.put(22, "22");
		st.put(11, "11");
		st.put(00, "00");
		st.put(99, null);
		for (Integer key : st.keys()) {
			StdOut.println("[" + st.rank(key) + "]" + key + " " + st.get(key));
		}

		st = new BinarySearchSymbolTable(10);
		st.delete(999);
		st.put(99, "999999999");
		st.put(88, "88888888");
		st.put(77, "7777777");
		st.put(66, "666666");
		st.put(44, "4444");
		st.put(55, "55555");
		st.put(33, "0");
		st.put(33, "0000000000");
		st.put(33, "333");
		st.put(22, "22");
		st.put(11, "1");
		st.put(00, "0");
		st.put(88, null);
		StdOut.println("输出: ");
		for (Integer key : st.keys()) {
			StdOut.println(key + " " + st.get(key));
		}
		StdOut.println();
		StdOut.println("ceiling(66)66: " + st.ceiling(66));
		StdOut.println("floor(66)66: " + st.floor(66));

		StdOut.println("ceiling(88)99: " + st.ceiling(88));
		StdOut.println("floor(88)77: " + st.floor(88));

		StdOut.println("ceiling(0)0: " + st.ceiling(0));
		StdOut.println("floor(0)0: " + st.floor(0));

		StdOut.println("ceiling(99)99: " + st.ceiling(99));
		StdOut.println("floor(99)99: " + st.floor(99));

		StdOut.println("ceiling(-1)0: " + st.ceiling(-1));
		StdOut.println("floor(-1)0: " + st.floor(-1));

		StdOut.println("ceiling(999)99: " + st.ceiling(999));
		StdOut.println("floor(999)99: " + st.floor(999));
	}

}
