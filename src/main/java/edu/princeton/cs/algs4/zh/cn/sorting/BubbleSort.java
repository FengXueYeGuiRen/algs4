package edu.princeton.cs.algs4.zh.cn.sorting;

/**
 * 冒泡排序
 *
 * @author FXYGR @date 2019-10-27
 */
public class BubbleSort extends AbstractSort {

	/**
	 * forward
	 * backward
	 * n -> 0
	 *
	 * @param a
	 */
	public static void forBackwardSort(Comparable[] a) {
		for (int i = 0; i < a.length - 1; ++i) {
			for (int j = a.length; j > i; --j) {
				if (less(a[j], a[j - 1])) {
					exch(a, j, j - 1);
				}
			}
		}
	}

	/**
	 * backward
	 * forward
	 * 0 -> n
	 *
	 * @param a
	 */
	public static void backForwardSort(Comparable[] a) {
		for (int i = a.length - 1; i > 1; --i) {
			for (int j = 0; j < i; ++j) {
				if (less(a[j + 1], a[j])) {
					exch(a, j + 1, j);
				}
			}
		}
	}

	/**
	 * forward
	 * forwarod
	 * 0 -> n
	 *
	 * @param a
	 */
	public static void forForwardSort(Comparable[] a) {
		for (int i = 0; i < a.length; ++i) {
			for (int j = 1; j < a.length - i; ++j) {
				if (less(a[j], a[j - 1])) {
					exch(a, j, j - 1);
				}
			}
		}
	}

	/**
	 * backward
	 * backward
	 * n -> 0
	 *
	 * @param a
	 */
	public static void backBackwardSort(Comparable[] a) {
		for (int i = a.length - 1; i > 0; --i) {
			for (int j = a.length - 1; j >= a.length - i; --j) {
				if (less(a[j], a[j - 1])) {
					exch(a, j, j - 1);
				}
			}
		}
	}

	/**
	 * SelectionSort -> BubbleSort
	 *
	 * @param a
	 */
	public static void sort(Comparable[] a) {
		for (int i = 0; i < a.length - 1; ++i) {
			for (int j = i + 1; j < a.length; ++j) {
				if (less(a[j], a[i])) {
					exch(a, j, i);
				}
			}
		}
	}

}
