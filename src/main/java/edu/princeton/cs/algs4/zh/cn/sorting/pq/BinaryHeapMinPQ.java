package edu.princeton.cs.algs4.zh.cn.sorting.pq;

/**
 * 基于(二叉)堆的优先队列
 * {@link BinaryHeapMaxPQ}
 * {@link edu.princeton.cs.algs4.MinPQ}
 *
 * @author FXYGR @date 2019-11-13
 */
public class BinaryHeapMinPQ<Key extends Comparable<Key>> {

	private Key[] priorityQ;
	/**
	 * 存储于priorityQ[1, ..., n]中，priorityQ[0]没有使用
	 */
	private int n = 0;

	/**
	 * 用a[]中的元素创建一个优先队列
	 *
	 * @param a
	 */
	public BinaryHeapMinPQ(Key[] a) {
		this(a.length);
		for (Key v : a) {
			insert(v);
		}
	}

	public BinaryHeapMinPQ(int maxN) {
		priorityQ = (Key[]) new Comparable[maxN + 1];
	}

	/**
	 * 向优先队列中插入一个元素
	 *
	 * @param v
	 */
	public void insert(Key v) {
		priorityQ[++n] = v;
		swim(n);
	}

	/**
	 * 返回最小元素
	 *
	 * @return
	 */
	public Key min() {
		return priorityQ[1];
	}

	/**
	 * 删除并返回最小元素
	 *
	 * @return
	 */
	public Key delMin() {
		Key min = priorityQ[1];
		priorityQ[1] = priorityQ[n];
		sink(1);
		priorityQ[n--] = null;
		return min;
	}

	/**
	 * 返回队列是否为空
	 *
	 * @return
	 */
	public boolean isEmpty() {
		return n < 1;
	}

	/**
	 * 返回优先队列中的元素个数
	 *
	 * @return
	 */
	public int size() {
		return n;
	}

	/**
	 * 上浮
	 *
	 * @param i
	 */
	private void swim(int i) {
		while (i > 1 && less(i, i / 2)) {
			exch(i, i / 2);
			i /= 2;
		}
	}

	/**
	 * 下沉
	 *
	 * @param i
	 */
	private void sink(int i) {
		for (int left = 2 * i; left <= n; left = 2 * i) {
			int smaller = left;
			if (left < n && less(left + 1, left)) {
				smaller = left + 1;
			}
			if (less(i, smaller)) {
				break;
			}// priorityQ[i] > priorityQ[smaller]
			exch(i, smaller);
			i = smaller;
		}
	}

	private boolean less(int i, int j) {
		return priorityQ[i].compareTo(priorityQ[j]) < 0;
	}

	private void exch(int i, int j) {
		priorityQ[0] = priorityQ[i];
		priorityQ[i] = priorityQ[j];
		priorityQ[j] = priorityQ[i];
	}

}
