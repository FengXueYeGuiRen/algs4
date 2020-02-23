package edu.princeton.cs.algs4.zh.cn.string;

import java.util.Arrays;

/**
 * 低位优先的字符串排序(5.1.2 算法 5.1)
 * {@link edu.princeton.cs.algs4.LSD}
 *
 * @author FXYGR @date 2020-02-23
 */
public class LSDStringSort {

	public static String[] sort(String[] a, int sw) {
		return sort(a, sw, 256);
	}

	public static String[] sort(String[] a, int sw, int r) {
		if (a == null) {
			return a;
		}
		int aLength = a.length;
		String[] aux = new String[a.length];
		int[] counts;
		for (int w = sw - 1; w >= 0; --w) {
			counts = new int[r + 1];
			for (int i = 0; i < aLength; ++i) {
				++counts[a[i].charAt(w) + 1];
			}
			for (int i = 0; i < r; ++i) {
				counts[i + 1] += counts[i];
			}
			for (int i = 0; i < aLength; ++i) {
				aux[counts[a[i].charAt(w)]++] = a[i];
			}
			for (int i = 0; i < aLength; ++i) {
				a[i] = aux[i];
			}
		}
		return a;
	}

	public static void main(String[] args) {
		String[] a = {"09", "00", "08", "11", "07", "22", "06", "33", "05", "44", "04", "55", "03", "66", "02", "77", "01", "88", "00", "99"};

		System.out.println("sources: " + Arrays.toString(a));
		a = sort(a, a[0].length());
		System.out.println("targets: " + Arrays.toString(a));
	}

}
