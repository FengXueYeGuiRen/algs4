package edu.princeton.cs.algs4.zh.cn.string;

/**
 * 以字符串为键的符号表的 API(5.2)
 *
 * @author FXYGR @date 2020-03-04
 */
public interface StringSymbolTable<Value> {

	/**
	 * 向表中插入键值对(如果值为 null 则删除键 key)
	 *
	 * @param key
	 * @param val
	 */
	void put(String key, Value val);

	/**
	 * 键 key 所对应的值(如果键不存在则返回 null)
	 *
	 * @param key
	 * @return
	 */
	Value get(String key);

	/**
	 * 删除键 key(和它的值)
	 *
	 * @param key
	 */
	void delete(String key);

	/**
	 * 表中是否保存着 key 的值
	 *
	 * @param key
	 * @return
	 */
	boolean contains(String key);

	/**
	 * 符号表是否为空
	 *
	 * @return
	 */
	boolean isEmpty();

	/**
	 * s 的前缀中最长的键
	 *
	 * @param s
	 * @return
	 */
	String longestPrefixOf(String s);

	/**
	 * 所有以 s 为前缀的键
	 *
	 * @param s
	 * @return
	 */
	Iterable<String> keysWithPrefix(String s);

	/**
	 * 所有和 s 匹配的键(其中"."能够匹配任意字符)
	 *
	 * @param s
	 * @return
	 */
	Iterable<String> keysThatMatch(String s);

	/**
	 * 键值对的数量
	 *
	 * @return
	 */
	int size();

	/**
	 * 符号表中的所有键
	 *
	 * @return
	 */
	Iterable<String> keys();

}
