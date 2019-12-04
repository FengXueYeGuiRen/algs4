package edu.princeton.cs.algs4.zh.cn.sorting;

import edu.princeton.cs.algs4.StdIn;

/**
 * 堆(大顶堆)排序(2.4.5 算法2.7)
 *
 * @author FXYGR @date 2019-11-03
 */
public class HeapSort extends AbstractSort {

	public static void sort(Comparable[] a) {
		//  创建大顶堆
		for (int i = a.length / 2; i >= 0; --i) {
			sink(a, i, a.length - 1);
		}
		for (int i = a.length - 1; i > 0; ) {
			exch(a, 0, i);
			assert isSorted(a, i, a.length);
			sink(a, 0, --i);
		}
		assert isSorted(a, 0, a.length);
	}

	public static void exchSort(Comparable[] a) {
		//  创建大顶堆
		for (int i = a.length / 2; i >= 0; --i) {
			heapAdjustByExch(a, i, a.length - 1);
		}
		for (int i = a.length - 1; i > 0; ) {
			exch(a, 0, i);
			assert isSorted(a, i, a.length);
			heapAdjustByExch(a, 0, --i);
		}
		assert isSorted(a, 0, a.length);
	}

	private static void heapAdjustByExch(Comparable[] a, int lo, int hi) {
		if (lo >= hi) {
			return;
		}
		//  大顶堆
		int parent = lo;
		while (parent <= (hi - 1) / 2) {
			int left = 2 * parent + 1;
			int right = left + 1;

			int biggerBrother = left;
			if (left < hi && less(a[left], a[right])) {
				biggerBrother = right;
			}
			if (less(a[biggerBrother], a[parent])) {
				break;
			}
			if (less(a[parent], a[biggerBrother])) {
				exch(a, parent, biggerBrother);
			}
			parent = biggerBrother;
		}
	}

	private static void sink(Comparable[] a, int lo, int hi) {
		//  大顶堆
		Comparable temp = a[lo];

		int parent = lo;
		for (int left = 2 * lo + 1; left <= hi; ) {
			int bigger = left;
			if (left < hi) {
				int right = left + 1;
				if (less(a[left], a[right])) {
					bigger = right;
				}
			}
			if (less(a[bigger], temp)) {
				break;
			}
			if (less(temp, a[bigger])) {
				a[parent] = a[bigger];
			}
			parent = bigger;
			left = 2 * bigger + 1;
		}
		a[parent] = temp;
	}

	public static void main(String[] args) {
		String[] a = StdIn.readAllStrings();
		show(a);
		exchSort(a);
		show(a);

		assert isSorted(a);
	}

}
