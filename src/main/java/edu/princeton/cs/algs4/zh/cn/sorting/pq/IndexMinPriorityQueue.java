package edu.princeton.cs.algs4.zh.cn.sorting.pq;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * 索引优先队列(2.4.4.6)
 * 关联索引的泛型优先队列
 * {@link edu.princeton.cs.algs4.IndexMinPQ}
 *
 * @author FXYGR @date 2019-11-20
 */
public class IndexMinPriorityQueue<Item extends Comparable<Item>>
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
	 * pqIndexes[itemIndex] = priorityQIndex
	 */
	private int[] pqIndexes;

	/**
	 * 创建一个最大容量为maxN的优先队列，索引的取值范围为 1 至 maxN
	 *
	 * @param maxN
	 */
	public IndexMinPriorityQueue(int maxN) {
		if (maxN < 0) {
			throw new IllegalArgumentException();
		}
		this.maxN = maxN;
		priorityQ = new int[maxN + 1];
		n = 0;

		items = (Item[]) new Comparable[maxN];
		pqIndexes = new int[maxN];

		for (int i = 0; i < maxN; ++i) {
			priorityQ[i] = -1;
			pqIndexes[i] = -1;
		}
		priorityQ[maxN] = -1;
	}

	public IndexMinPriorityQueue(Item[] items) {
		this(2 * items.length);

		for (int i = 0; i < items.length; ++i) {
			insert(i, items[i]);
		}
	}

	/**
	 * 插入一个元素，将它和索引 i 相关联
	 *
	 * @param i    item index
	 * @param item item
	 */
	@Override
	public void insert(int i, Item item) {
		if (contains(i) || item == null) {
			return;
		}
		priorityQ[++n] = i;

		items[i] = item;
		pqIndexes[i] = n;

		swim(n);
	}

	/**
	 * 将索引为 i 的元素设为 item
	 *
	 * @param i    item index
	 * @param item item
	 */
	@Override
	public void change(int i, Item item) {
		if (!contains(i) || item == null) {
			return;
		}
		Item itemed = items[i];
		items[i] = item;
		if (less(item, itemed)) {
			swim(pqIndexes[i]);
		}
		if (less(itemed, item)) {
			sink(pqIndexes[i]);
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
		return i >= 0 && i < maxN && pqIndexes[i] != -1;
	}

	/**
	 * 删去索引为 i 及其相关联的元素
	 *
	 * @param i item index
	 * @return
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

		priorityQ[n + 1] = -1;

		items[i] = null;
		pqIndexes[i] = -1;

		return item;
	}

	/**
	 * 删除最小元素并放回它的索引
	 *
	 * @return item index
	 */
	public int delMin() {
		if (isEmpty()) {
			return -1;
		}
		int minIndex = priorityQ[1];

		delete(minIndex);

		return minIndex;
	}

	/**
	 * 返回最小元素
	 *
	 * @return @Nullable
	 */
	public Item min() {
		if (isEmpty()) {
			return null;
		}
		return items[priorityQ[1]];
	}

	/**
	 * 返回最小元素的索引
	 *
	 * @return item index
	 */
	public int minIndex() {
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

	/**
	 * @param i priorityQueue index
	 */
	private void swim(int i) {
		Item item = items[priorityQ[i]];
		int itemIndex = priorityQ[i];
		while (i > 1) {
			int parent = i / 2;
			if (less(items[priorityQ[parent]], item)) {
				break;
			}
			priorityQ[i] = priorityQ[parent];

			pqIndexes[priorityQ[parent]] = i;
			i = parent;
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
			int smaller = left;
			if (left < n && less(left + 1, left)) {
				smaller = left + 1;
			}
			if (less(item, items[priorityQ[smaller]])) {
				break;
			}
			priorityQ[i] = priorityQ[smaller];

			pqIndexes[priorityQ[smaller]] = i;

			i = smaller;
		}
		priorityQ[i] = itemIndex;

		pqIndexes[itemIndex] = i;
	}

	private boolean less(Item item1, Item item2) {
		return item1.compareTo(item2) < 0;
	}

	/**
	 * @param i priorityQueue index
	 * @param j priorityQueue index
	 * @return
	 */
	private boolean less(int i, int j) {
		return less(items[priorityQ[i]], items[priorityQ[j]]);
	}

	public static void main(String[] args) {
		Integer[] ints = {99, 88, 77, 66, 55, 55, 44, 33, 22, 11};

		IndexMinPriorityQueue<Integer> indexMinPriorityQueue =
				new IndexMinPriorityQueue<>(ints);
		print(indexMinPriorityQueue);

		StdOut.println("\nchange: [7]22 -> [7]22 * 7(154)");
		indexMinPriorityQueue.change(7, ints[7] * 7);
		print(indexMinPriorityQueue);

		StdOut.println("\nchange: [7]154 -> [7]22 / 7(3)");
		indexMinPriorityQueue.change(7, ints[7] / 7);
		print(indexMinPriorityQueue);

		StdOut.println("\nchange: [1]88 -> [1]88 * 8(704)");
		indexMinPriorityQueue.change(1, ints[1] * 8);
		print(indexMinPriorityQueue);

		StdOut.println("\nchange: [1]704 -> [1]88 / 8(11)");
		indexMinPriorityQueue.change(1, ints[1] / 8);
		print(indexMinPriorityQueue);

		StdOut.println("\ndelete: [6]33");
		indexMinPriorityQueue.delete(6);
		print(indexMinPriorityQueue);

		StdOut.println("\ndelete: [3]66");
		indexMinPriorityQueue.delete(3);
		print(indexMinPriorityQueue);

		StdOut.println("\ndelMin:");
		indexMinPriorityQueue.delMin();
		print(indexMinPriorityQueue);

		StdOut.println("\ndelMins:");
		while (!indexMinPriorityQueue.isEmpty()) {
			int i = indexMinPriorityQueue.delMin();
			StdOut.println("[" + i + "]");
		}

		ints = new Integer[]{9, 88, 77, 66, 55, 55, 44, 33, 222, 11};
		indexMinPriorityQueue =
				new IndexMinPriorityQueue<>(ints);

		StdOut.println("\ndelMins:");
		while (!indexMinPriorityQueue.isEmpty()) {
			int i = indexMinPriorityQueue.delMin();
			StdOut.println(i + " " + ints[i]);
		}
	}

	private static void print(IndexMinPriorityQueue indexMinPriorityQueue) {
		for (int i = 0; i < indexMinPriorityQueue.items.length; ++i) {
			StdOut.print("[" + i + "]" + indexMinPriorityQueue.items[i] + ", ");
		}
		StdOut.println();
		for (int i = 1; i <= indexMinPriorityQueue.size(); ++i) {
			//  item index
			int queueValue = indexMinPriorityQueue.priorityQ[i];
			StdOut.print("(" + indexMinPriorityQueue.pqIndexes[queueValue] + ")" +
					"[" + queueValue + "]" +
					indexMinPriorityQueue.items[queueValue] + ", ");
		}
		StdOut.println();
		StdOut.println("priorityQueue; " + Arrays.toString(indexMinPriorityQueue.priorityQ));
	}

}
