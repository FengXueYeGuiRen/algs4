package edu.princeton.cs.algs4.zh.cn.sorting;

import edu.princeton.cs.algs4.StdIn;

/**
 * 插入排序(2.1.3 算法2.2)
 * {@link edu.princeton.cs.algs4.Insertion}
 *
 * @author FXYGR @date 2019-10-23
 */
public class InsertionSort extends AbstractSort {

	/**
	 * 将a[]按升序排列
	 *
	 * @param a
	 */
	public static void sort(Comparable[] a) {
		Comparable temp;
		for (int i = 1; i < a.length; ++i) {
			if (less(a[i - 1], a[i])) {
				continue;
			}
			temp = a[i];
			int j = i;
			for (; j > 0 && less(temp, a[j - 1]); --j) {
				//  后移
				a[j] = a[j - 1];
			}
			a[j] = temp;
			assert isSorted(a, 0, i);
		}
		assert isSorted(a);
	}

	public static void sort(
			Comparable[] a, int sortingSubArrayIndexLow, int sortingSubArrayLength) {
		Comparable temp;
		for (int i = sortingSubArrayIndexLow + 1; i < sortingSubArrayLength; ++i) {
			if (less(a[i - 1], a[i])) {
				continue;
			}
			temp = a[i];
			int j = i;
			for (; j > sortingSubArrayIndexLow && less(temp, a[j - 1]); --j) {
				//  后移
				a[j] = a[j - 1];
			}
			a[j] = temp;
		}
		assert isSorted(a, sortingSubArrayIndexLow, sortingSubArrayLength);
	}

	public static void insertionSort(Comparable[] a) {
		for (int i = 1; i < a.length; ++i) {
			Comparable temp = a[i];
			int j = i - 1;
			for (; j >= 0 && less(temp, a[j]); --j) {
				//  后移
				a[j + 1] = a[j];
			}
			a[j + 1] = temp;
		}
	}

	public static void sort(String[] a, int lo, int hi, int d) {
		//  从第 d 个字符开始对 a[lo] 到 a[hi] 排序
		if (a == null) {
			return;
		}
		lo = lo >= 0 ? lo : 0;
		hi = hi < a.length ? hi : (a.length - 1);
		if (lo >= hi) {
			return;
		}
		for (int i = lo; i < hi; ++i) {
			if (substring(a[i], d).compareTo(substring(a[i + 1], d)) <= 0) {
				continue;
			}
			int j = i + 1;
			String temp = a[j];
			for (; j > lo && less(temp, substring(a[j - 1], d)); --j) {
				a[j] = a[j - 1];
			}
			a[j] = temp;
		}
	}

	private static String substring(String s, int beginIndex) {
		if (s == null || s.length() <= beginIndex) {
			return "";
		}
		return s.substring(beginIndex);
	}

	public static void exchSort(Comparable[] a) {
		//  数组长度
		int N = a.length;
		for (int i = 1; i < N; ++i) {
			//  将a[i]插入到a[i-1],a[i-2],a[i-3],...之中
			for (int j = i; j > 0 && less(a[j], a[j - 1]); --j) {
				exch(a, j, j - 1);
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
