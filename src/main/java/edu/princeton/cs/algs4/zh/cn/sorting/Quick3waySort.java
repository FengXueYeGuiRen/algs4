package edu.princeton.cs.algs4.zh.cn.sorting;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 熵最优的排序(2.3.3.3 三向切分的快速排序)
 * {@link QuickSort}
 * {@link edu.princeton.cs.algs4.Quick3way}
 *
 * @author FXYGR @date 2019-11-28
 */
public class Quick3waySort extends AbstractSort {

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
		int lt = lo, i = lo + 1, gt = hi;
		Comparable v = a[lo];
		while (i <= gt) {
			int cmp = a[i].compareTo(v);
			if (cmp < 0) {
				exch(a, lt++, i++);
			} else if (cmp > 0) {
				exch(a, i, gt--);
			} else {
				++i;
			}
		}// 现在 a[lo, ..., lt-1] < v = a[lt, gt] < a[gt+1, ,,,, hi] 成立
		sort(a, lo, lt - 1);
		sort(a, gt + 1, hi);
		assert isSorted(a, lo, hi);
	}

	public static void main(String[] args) {
		String[] a = StdIn.readAllStrings();
		show(a);
		sort(a);
		show(a);

		assert isSorted(a);
	}

}
