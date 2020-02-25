package edu.princeton.cs.algs4.zh.cn.string;

import java.util.Arrays;

/**
 * 高位优先的字符串排序(5.1.3 算法 5.2)
 * {@link edu.princeton.cs.algs4.MSD}
 *
 * @author FXYGR @date 2020-02-25
 */
public class MSDStringSort {

	private static int R;

	private static String[] aux;
	private static int[] counts;

	public static String[] sort(String[] a, int r) {
		if (a == null) {
			return a;
		}
		R = r <= 0 ? 256 : r;
		aux = new String[a.length];
		sort(a, 0, a.length - 1, 0);
		return a;
	}

	public static String[] sort(String[] a) {
		if (a == null) {
			return a;
		}
		return sort(a, 256);
	}

	private static int charAt(String s, int d) {
		return d < 0 || d >= s.length() ? -1 : s.charAt(d);
	}

	private static String[] sort(String[] a, int lo, int hi, int d) {
		if (lo >= hi) {
			return a;
		}
		counts = new int[R + 2];
		for (int i = lo; i <= hi; ++i) {
			++counts[charAt(a[i], d) + 2];
		}
		for (int r = 0; r < R + 1; ++r) {
			counts[r + 1] = counts[r];
		}
		for (int i = lo; i <= hi; ++i) {
			aux[counts[charAt(a[i], d) + 1]++] = a[i];
		}
		for (int i = lo; i <= hi; ++i) {
			a[i] = aux[i - lo];
		}
		for (int r = 0; r < R; ++r) {
			a = sort(a, lo + counts[r], lo + counts[r + 1] - 1, d + 1);
		}
		return a;
	}

	public static void main(String[] args) {
		String[] a = {"09", "01"};

		System.out.println("sources: " + Arrays.toString(a));
		sort(a);
		System.out.println("targets: " + Arrays.toString(a));
	}

}
