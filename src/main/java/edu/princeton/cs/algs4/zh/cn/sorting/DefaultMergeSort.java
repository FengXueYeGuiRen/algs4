package edu.princeton.cs.algs4.zh.cn.sorting;

/**
 * 归并排序
 *
 * @author FXYGR @date 2019-10-29
 */
public class DefaultMergeSort extends AbstractSort {

	/**
	 * 归并所需的辅助数组
	 */
	protected static Comparable[] aux;

	/**
	 * 原地归并方法
	 *
	 * @param a
	 * @param lo
	 * @param mid
	 * @param hi
	 */
	protected static void merge(Comparable[] a, int lo, int mid, int hi) {
		//  将a[lo, ..., hi]复制到aux[lo, ..., hi]
		for (int k = lo; k <= hi; ++k) {
			aux[k] = a[k];
		}
		//  将a[lo, ..., mid]和a[mid+1, ..., hi]归并
		int i = lo, j = mid + 1;
		//  从aux归并回到a[lo, ..., hi]
		for (int k = lo; k <= hi; ++k) {
			if (i > mid) {
				a[k] = aux[j++];
				continue;
			}
			if (j > hi) {
				a[k] = aux[i++];
				continue;
			}
			if (less(aux[j], aux[i])) {
				//  aux[j] <= aux[i]
				a[k] = aux[j++];
				continue;
			}
			//  aux[i] <= aux[j]
			a[k] = aux[i++];
		}
	}

	protected static void mergea(Comparable[] a, int lo, int mid, int hi) {
		//  将a[lo, ..., hi]复制到aux[lo, ..., hi]
		for (int k = lo; k <= hi; ++k) {
			aux[k] = a[k];
		}
		//  将a[lo, ..., mid]和a[mid+1, ..., hi]归并
		int i = lo, j = mid + 1;
		//  从aux归并回到a[lo, ..., hi]
		int k = i;
		while (i <= mid && j <= hi) {
			if (less(aux[j], aux[i])) {
				a[k++] = aux[j++];
			} else {
				a[k++] = aux[i++];
			}
		}
		while (i > mid) {
			a[k++] = aux[j++];
		}
		while (j > hi) {
			a[k++] = aux[i++];
		}
	}

}
