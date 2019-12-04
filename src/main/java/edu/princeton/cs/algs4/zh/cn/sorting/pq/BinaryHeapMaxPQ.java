package edu.princeton.cs.algs4.zh.cn.sorting.pq;

/**
 * 基于(二叉)堆的优先队列(2.4.4 算法2.6)
 * {@link edu.princeton.cs.algs4.MaxPQ}
 *
 * @author FXYGR @date 2019-11-13
 */
public class BinaryHeapMaxPQ<Key extends Comparable<Key>> {

	/**
	 * 基于堆的完全二叉树
	 */
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
	public BinaryHeapMaxPQ(Key[] a) {
		this(a.length);
		for (Key v : a) {
			insert(v);
		}
	}

	public BinaryHeapMaxPQ(int maxN) {
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
	 * 返回最大元素
	 *
	 * @return
	 */
	public Key max() {
		return priorityQ[1];
	}

	/**
	 * 删除并返回最大元素
	 *
	 * @return
	 */
	public Key delMax() {
		//  从跟结点得到最大元素
		Key max = priorityQ[1];
		//  将其和最后一个结点交换
		priorityQ[1] = priorityQ[n];
		//  (下沉)恢复堆的有序性
		sink(1);
		//  防止越界
		priorityQ[n--] = null;
		return max;
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

	//  由下至上的堆有序化（上浮）
	private void swim(int i) {
		while (i > 1 && less(i / 2, i)) {
			exch(i / 2, i);
			i /= 2;
		}
	}

	//  由上至下的堆有序化（下沉）
	private void sink(int i) {
		for (int left = 2 * i; left <= n; left = 2 * i) {
			int bigger = left;
			if (left < n && less(left, left + 1)) {
				bigger = left + 1;
			}
			// priorityQ[bigger] < priorityQ[i]
			if (less(bigger, i)) {
				break;
			}// priorityQ[i] < priorityQ[bigger]
			exch(i, bigger);
			i = bigger;
		}
	}

	private boolean less(int i, int j) {
		return priorityQ[i].compareTo(priorityQ[j]) < 0;
	}

	private void exch(int i, int j) {
		priorityQ[0] = priorityQ[i];
		priorityQ[i] = priorityQ[j];
		priorityQ[j] = priorityQ[0];
	}

}
