package edu.princeton.cs.algs4.zh.cn.sorting;

/**
 * 归并排序-对小规模子数组使用插入排序
 * {@link MergeSort}
 *
 * @author FXYGR @date 2019-10-31
 */
public class MergeInsertionSort extends DefaultMergeSort {

	public static void sort(Comparable[] a) {
		aux = new Comparable[a.length];

		sort(a, 0, a.length - 1);
	}

	private static void sort(Comparable[] a, int lo, int hi) {
		int smallSubArrayLength = 15;
		if (hi - lo < smallSubArrayLength) {
			InsertionSort.sort(a, lo, hi + 1);
			return;
		}
		int mid = (lo + hi) / 2;

		sort(a, lo, mid);
		sort(a, mid + 1, hi);

		merge(a, lo, mid, hi);
	}

}
