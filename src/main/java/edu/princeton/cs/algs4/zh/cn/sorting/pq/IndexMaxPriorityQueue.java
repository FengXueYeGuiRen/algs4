package edu.princeton.cs.algs4.zh.cn.sorting.pq;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * 索引优先队列(2.4.4.6)
 * 关联索引的泛型优先队列
 * {@link edu.princeton.cs.algs4.IndexMaxPQ}
 *
 * @author FXYGR @date 2019-11-21
 */
public class IndexMaxPriorityQueue<Item extends Comparable<Item>>
		implements IndexPriorityQueue<Item> {

	private int maxN;

	/**
	 * 基于堆的完全二叉树
	 */
	private int[] priorityQ;
	/**
	 * 存储于priorityQ[1, ..., n]中，priorityQ[0]没有使用
	 */
	private int n;

	private Item[] items;
	/**
	 * pqIndex[itemIndex] = priorityQIndex
	 */
	private int[] pqIndexes;

	/**
	 * 创建一个最大容量为maxN的优先队列，索引的取值范围为 0 至 maxN-1
	 *
	 * @param maxN
	 */
	public IndexMaxPriorityQueue(int maxN) {
		if (maxN < 0) {
			throw new IllegalArgumentException();
		}
		this.maxN = maxN;

		this.n = 0;
		priorityQ = new int[maxN + 1];
		priorityQ[maxN] = -1;

		this.items = (Item[]) new Comparable[maxN];
		this.pqIndexes = new int[maxN];

		for (int i = 0; i < maxN; ++i) {
			priorityQ[i] = -1;
			pqIndexes[i] = -1;
		}
	}

	public IndexMaxPriorityQueue(Item[] items) {
		this(2 * items.length);

		for (int i = 0; i < items.length; ++i) {
			insert(i, items[i]);
		}
	}

	/**
	 * 插入一个元素，将它和索引 i 相关联
	 *
	 * @param i    item index
	 * @param item
	 */
	@Override
	public void insert(int i, Item item) {
		if (contains(i) || item == null) {
			return;
		}
		items[i] = item;
		pqIndexes[i] = n;

		priorityQ[++n] = i;

		swim(n);
	}

	/**
	 * 将索引为 i 的元素设为 item
	 *
	 * @param i    item index
	 * @param item
	 */
	@Override
	public void change(int i, Item item) {
		if (!contains(i) || item == null
				|| item.compareTo(items[i]) == 0) {
			return;
		}
		Item itemed = items[i];
		items[i] = item;
		if (less(item, itemed)) {
			sink(pqIndexes[i]);
		}
		if (less(itemed, item)) {
			swim(pqIndexes[i]);
		}
	}

	/**
	 * 是否存在索引为 i 的元素
	 *
	 * @param i item index
	 * @return
	 */
	@Override
	public boolean contains(int i) {
		return i >= 0 && i < maxN && items[i] != null;
	}

	/**
	 * 删去索引为 i 及其相关联的元素
	 *
	 * @param i item index
	 * @return @Nullable
	 */
	@Override
	public Item delete(int i) {
		if (!contains(i)) {
			return null;
		}
		Item item = items[i];

		priorityQ[pqIndexes[i]] = priorityQ[n];
		pqIndexes[priorityQ[n--]] = pqIndexes[i];

		sink(pqIndexes[i]);

		items[i] = null;
		pqIndexes[i] = -1;

		priorityQ[n + 1] = -1;

		return item;
	}

	/**
	 * 删除最大元素并放回它的索引
	 *
	 * @return
	 */
	public int delMax() {
		if (isEmpty()) {
			return -1;
		}
		int maxIndex = priorityQ[1];

		delete(maxIndex);

		return maxIndex;
	}

	/**
	 * 返回最大元素
	 *
	 * @return @Nullable
	 */
	public Item max() {
		if (isEmpty()) {
			return null;
		}
		return items[priorityQ[1]];
	}

	/**
	 * 返回最大元素的索引
	 *
	 * @return
	 */
	public int maxIndex() {
		if (isEmpty()) {
			return -1;
		}
		return priorityQ[1];
	}

	/**
	 * 优先队列是否为空
	 *
	 * @return
	 */
	@Override
	public boolean isEmpty() {
		return n == 0;
	}

	/**
	 * 优先队列中的元素数量
	 *
	 * @return
	 */
	@Override
	public int size() {
		return n;
	}

	private boolean less(Item item1, Item item2) {
		return item1.compareTo(item2) < 0;
	}

	/**
	 * @param i pqIndexes index
	 * @param j pqIndexes index
	 * @return
	 */
	private boolean less(int i, int j) {
		return less(items[priorityQ[i]], items[priorityQ[j]]);
	}

	/**
	 * @param i priorityQueue index
	 */
	private void swim(int i) {
		Item item = items[priorityQ[i]];
		int itemIndex = priorityQ[i];

		while (i > 1 && less(items[priorityQ[i / 2]], item)) {
			priorityQ[i] = priorityQ[i / 2];

			pqIndexes[priorityQ[i / 2]] = i;

			i /= 2;
		}
		priorityQ[i] = itemIndex;

		pqIndexes[itemIndex] = i;
	}

	/**
	 * @param i priorityQueue index
	 */
	private void sink(int i) {
		Item item = items[priorityQ[i]];
		int itemIndex = priorityQ[i];

		while (i <= n / 2) {
			int left = 2 * i;
			int bigger = left;
			if (left < n && less(bigger, left + 1)) {
				bigger = left + 1;
			}
			if (less(items[priorityQ[bigger]], item)) {
				break;
			}
			priorityQ[i] = priorityQ[bigger];

			pqIndexes[priorityQ[bigger]] = i;

			i = bigger;
		}
		priorityQ[i] = itemIndex;

		pqIndexes[itemIndex] = i;
	}


	public static void main(String[] args) {
		Integer[] ints = {11, 22, 33, 44, 55, 55, 66, 77, 88, 99};

		IndexMaxPriorityQueue<Integer> indexMaxPriorityQueue =
				new IndexMaxPriorityQueue<>(ints);
		print(indexMaxPriorityQueue);

		StdOut.println("\nchange: [7]77 -> [7]77 * 7(539)");
		indexMaxPriorityQueue.change(7, ints[7] * 7);
		print(indexMaxPriorityQueue);

		StdOut.println("\nchange: [7]539 -> [7]77 / 7(11)");
		indexMaxPriorityQueue.change(7, ints[7] / 7);
		print(indexMaxPriorityQueue);

		StdOut.println("\nchange: [1]11 -> [1]11 * 8(88)");
		indexMaxPriorityQueue.change(1, ints[1] * 8);
		print(indexMaxPriorityQueue);

		StdOut.println("\nchange: [1]88 -> [1]11 / 8(1)");
		indexMaxPriorityQueue.change(1, ints[1] / 8);
		print(indexMaxPriorityQueue);

		StdOut.println("\ndelete: [6]66");
		indexMaxPriorityQueue.delete(6);
		print(indexMaxPriorityQueue);

		StdOut.println("\ndelete: [3]44");
		indexMaxPriorityQueue.delete(3);
		print(indexMaxPriorityQueue);

		StdOut.println("\ndelMax:");
		indexMaxPriorityQueue.delMax();
		print(indexMaxPriorityQueue);

		StdOut.println("\ndelMaxs:");
		while (!indexMaxPriorityQueue.isEmpty()) {
			int i = indexMaxPriorityQueue.delMax();
			StdOut.println("[" + i + "]");
		}

		ints = new Integer[]{11, 222, 33, 44, 55, 55, 66, 77, 88, 9};
		indexMaxPriorityQueue =
				new IndexMaxPriorityQueue<>(ints);

		StdOut.println("\ndelMins:");
		while (!indexMaxPriorityQueue.isEmpty()) {
			int i = indexMaxPriorityQueue.delMax();
			StdOut.println(i + " " + ints[i]);
		}
	}

	private static void print(IndexMaxPriorityQueue indexMaxPriorityQueue) {
		for (int i = 0; i < indexMaxPriorityQueue.items.length; ++i) {
			StdOut.print("[" + i + "]" + indexMaxPriorityQueue.items[i] + ", ");
		}
		StdOut.println();
		for (int i = 1; i <= indexMaxPriorityQueue.size(); ++i) {
			//  item index
			int queueValue = indexMaxPriorityQueue.priorityQ[i];
			StdOut.print("(" + indexMaxPriorityQueue.pqIndexes[queueValue] + ")" +
					"[" + queueValue + "]" +
					indexMaxPriorityQueue.items[queueValue] + ", ");
		}
		StdOut.println();
		StdOut.println("priorityQueue; " + Arrays.toString(indexMaxPriorityQueue.priorityQ));
	}

}
