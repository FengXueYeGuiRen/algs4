package edu.princeton.cs.algs4.zh.cn.sorting.pq;

/**
 * 索引优先队列
 *
 * @author FXYGR @date 2019-11-21
 */
public interface IndexPriorityQueue<Item extends Comparable<Item>> {

	/**
	 * 插入一个元素，将它和索引 i 相关联
	 *
	 * @param i
	 * @param item
	 */
	void insert(int i, Item item);

	/**
	 * 将索引为 i 的元素设为 item
	 *
	 * @param i
	 * @param item
	 */
	void change(int i, Item item);

	/**
	 * 是否存在索引为 i 的元素
	 *
	 * @param i
	 * @return
	 */
	boolean contains(int i);

	/**
	 * 删去索引为 i 及其相关联的元素
	 *
	 * @param i
	 */
	Item delete(int i);

	/**
	 * 优先队列是否为空
	 *
	 * @return
	 */
	boolean isEmpty();

	/**
	 * 优先队列中的元素数量
	 *
	 * @return
	 */
	int size();

}
