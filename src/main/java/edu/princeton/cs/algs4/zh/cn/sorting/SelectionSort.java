package edu.princeton.cs.algs4.zh.cn.sorting;

import edu.princeton.cs.algs4.StdIn;

/**
 * 选择排序(2.1.2 算法2.1)
 * {@link edu.princeton.cs.algs4.Selection}
 *
 * @author FXYGR @date 2019-10-21
 */
public class SelectionSort extends AbstractSort {

	/**
	 * 将a[]按升序排序
	 *
	 * @param a
	 */
	public static void sort(Comparable[] a) {
		//  数组长度
		int N = a.length;
		for (int i = 0; i < N - 1; ++i) {
			//  将a[i]和a[i+1,...,n]中最小的元素交换
			//  最小元素的索引
			int min = i;
			for (int j = i + 1; j < N; ++j) {
				if (less(a[j], a[min])) {
					min = j;
				}
			}
			if (min != i) {
				exch(a, i, min);
			}
			assert isSorted(a, 0, i);
		}
		assert isSorted(a);
	}

	public static void main(String[] args) {
		String[] a = StdIn.readAllStrings();
		sort(a);
		show(a);
	}

}
