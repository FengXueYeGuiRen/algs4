package edu.princeton.cs.algs4.zh.cn.sorting;

import edu.princeton.cs.algs4.StdOut;

/**
 * {@link Example}
 * {@link edu.princeton.cs.algs4.Selection}
 * {@link edu.princeton.cs.algs4.Insertion}
 * {@link edu.princeton.cs.algs4.Shell}
 * {@link edu.princeton.cs.algs4.Quick}
 *
 * @author FXYGR @date 2019-10-11
 */
public abstract class AbstractSort {

	/**
	 * 对元素进行比较
	 */
	protected static boolean less(
			Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	/**
	 * 将元素交换位置
	 */
	protected static void exch(Object[] a, int i, int j) {
		if (i == j) {
			return;
		}
		Object t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	protected static void show(Comparable[] a) {
		//  在单行中打印数组
		for (int i = 0; i < a.length; ++i) {
			StdOut.print(a[i] + " ");
		}
		StdOut.println();
	}

	public final static boolean isSorted(Comparable[] a) {
		//  测试数组元素是否有序
		return isSorted(a, 0, a.length - 1);
	}

	public final static boolean isSorted(
			Comparable[] a, int lo, int hi) {
		//  测试数组元素是否有序
		for (int i = lo + 1; i <= hi; ++i) {
			if (less(a[i], a[i - 1])) {
				return false;
			}
		}
		return true;
	}

}
