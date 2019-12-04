package edu.princeton.cs.algs4.zh.cn.sorting;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 快速排序(2.3.1 算法2.5)
 * {@link edu.princeton.cs.algs4.Quick}
 *
 * @author FXYGR @date 2019-11-03
 */
public class QuickSort extends AbstractSort {

	public static void sort(Comparable[] a) {
		//  消除对输入的依赖
		StdRandom.shuffle(a);

		sort(a, 0, a.length - 1);

		assert isSorted(a);
	}

	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo) {
			return;
		}
		//  切分
		int j = partition(a, lo, hi);
		//  将左半部分a[lo, ..., j-1]排序
		sort(a, lo, j - 1);
		//  将右半部分a[j+1, ..., hi]排序
		sort(a, j + 1, hi);

		assert isSorted(a, lo, hi);
	}

	/**
	 * 快速排序的切分
	 *
	 * @param a
	 * @param lo
	 * @param hi
	 * @return
	 */
	private static int partition(Comparable[] a, int lo, int hi) {
		//  将数组切分为a[lo, ..., j-1], a[j], a[j+1, ..., hi]
		//  左右扫描指针
		int i = lo, j = hi + 1;
		Comparable v = a[lo];
		while (true) {
			//  扫描左右，检查扫描是否结束并交换元素
			while (i < j - 1 && less(a[++i], v)) {
			}// i == j
			while (j >= i && less(v, a[--j])) {
			}// j == i
			if (i >= j) {
				break;
			}
			exch(a, i, j);
		}
		//  将v = a[j]放入正确的位置
		exch(a, lo, j);
		//  a[lo, ..., j-1] <= a[j] <= a[j+1, ..., hi]达成
		return j;
	}

	public static void main(String[] args) {
		String[] a = StdIn.readAllStrings();
		show(a);
		sort(a);
		show(a);

		assert isSorted(a);
	}

}
