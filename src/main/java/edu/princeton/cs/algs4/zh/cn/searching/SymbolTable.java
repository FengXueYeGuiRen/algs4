package edu.princeton.cs.algs4.zh.cn.searching;

/**
 * 符号表(3.1.1)
 *
 * @author FXYGR @date 2019-12-03
 */
public interface SymbolTable<Key, Value> {

	/**
	 * 将健值对存入表中(若值为空则将健 key 从表中删除)
	 *
	 * @param key
	 * @param val
	 */
	void put(Key key, Value val);

	/**
	 * 获取健 key 对应的值(若健 key 不存在则返回空null)
	 *
	 * @param key
	 * @return
	 */
	Value get(Key key);

	/**
	 * 从表中删去健 key(及其对应的值)
	 *
	 * @param key
	 */
	default Value delete(Key key) {
		if (key == null) {
			return null;
		}
		Value value = get(key);

		put(key, null);

		return value;
	}

	/**
	 * 健 key 在表中是否有对应的值(健 key 是否存在于表中)
	 *
	 * @param key
	 * @return
	 */
	default boolean contains(Key key) {
		return key != null && get(key) != null;
	}

	/**
	 * 表是否为空
	 *
	 * @return
	 */
	default boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * 表中的健值对数量
	 *
	 * @return
	 */
	int size();

	/**
	 * 表中的所有健的集合
	 *
	 * @return
	 */
	Iterable<Key> keys();

}
