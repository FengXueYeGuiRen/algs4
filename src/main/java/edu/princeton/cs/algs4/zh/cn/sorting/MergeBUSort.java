package edu.princeton.cs.algs4.zh.cn.sorting;

/**
 * 自底向上的归并排序(2.2.3)
 * {@link edu.princeton.cs.algs4.MergeBU}
 *
 * @author FXYGR @date 2019-10-31
 */
public class MergeBUSort extends DefaultMergeSort {

	public static void sort(Comparable[] a) {
		aux = new Comparable[a.length];
		//  进行lgN次两两归并
		int N = a.length;
		//  sz子数组大小
		for (int sz = 1; sz < N; sz = sz + sz) {
			//  lo子数组索引
			for (int lo = 0; lo < N - sz; lo += sz + sz) {
				int mid = lo + sz - 1;
				int hi = Math.min(lo + sz + sz - 1, N - 1);
				merge(a, lo, mid, hi);
			}
		}
	}

	public static void s(Comparable[] a) {
		aux = new Comparable[a.length];

		for (int i = 1; i <= a.length / 2; ++i) {
			for (int lo = 0; lo < a.length; lo += i) {
				int hi = lo + i;
				int mid = (lo + hi) / 2;
				merge(a, lo, mid, hi);
			}
		}
	}

}
