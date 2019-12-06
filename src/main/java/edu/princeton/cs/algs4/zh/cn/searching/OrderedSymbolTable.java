package edu.princeton.cs.algs4.zh.cn.searching;

/**
 * 有序符号表(3.1.2)
 *
 * @author FXYGR @date 2019-12-03
 */
public interface OrderedSymbolTable<Key extends Comparable<Key>, Value>
		extends SymbolTable<Key, Value> {

	/**
	 * 最小的健
	 *
	 * @return
	 */
	Key min();

	/**
	 * 最大的键
	 *
	 * @return
	 */
	Key max();

	/**
	 * 小于等于 key 的最大值
	 *
	 * @param key
	 * @return
	 */
	Key floor(Key key);

	/**
	 * 大于等于 key 的最小值
	 *
	 * @param key
	 * @return
	 */
	Key ceiling(Key key);

	/**
	 * 小于 key 的健的数量
	 *
	 * @param key
	 * @return
	 */
	int rank(Key key);

	/**
	 * 排名为 i 的健
	 *
	 * @param i
	 * @return
	 */
	Key select(int i);

	/**
	 * 删除最小的键
	 */
	default void deletedMin() {
		Key minKey = min();

		Value value = delete(minKey);
	}

	/**
	 * 删除最大的健
	 */
	default void deleteMax() {
		Key maxKey = max();

		Value value = delete(maxKey);
	}

	/**
	 * [lo, ..., hi]之间键的数量
	 *
	 * @param lo
	 * @param hi
	 * @return
	 */
	default int size(Key lo, Key hi) {
		if (hi.compareTo(lo) < 0) {
			return 0;
		} else if (contains(hi)) {
			return rank(hi) - rank(lo) + 1;
		} else {
			return rank(hi) - rank(lo);
		}
	}

	/**
	 * [lo, ..., hi]之间的所有键，已排序
	 *
	 * @param lo
	 * @param hi
	 * @return
	 */
	Iterable<Key> keys(Key lo, Key hi);

	/**
	 * 表中所有键的集合，已排序
	 *
	 * @return
	 */
	@Override
	default Iterable<Key> keys() {
		return keys(min(), max());
	}

}
