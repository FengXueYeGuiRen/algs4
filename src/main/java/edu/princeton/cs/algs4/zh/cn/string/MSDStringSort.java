package edu.princeton.cs.algs4.zh.cn.string;

import java.util.Arrays;

/**
 * 高位优先的字符串排序(5.1.3 算法 5.2)
 * {@link edu.princeton.cs.algs4.MSD}
 *
 * @author FXYGR @date 2020-02-25
 */
public class MSDStringSort {

	/**
	 * 基数
	 */
	private static int R;
	/**
	 * 数据分类的辅助数组
	 */
	private static String[] aux;

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
		//  以第 d 个字符为键将 a[lo] 至 a[hi] 排序
		if (lo >= hi) {
			return a;
		}
		int[] counts = new int[R + 2];
		//  计算频率
		for (int i = lo; i <= hi; ++i) {
			++counts[charAt(a[i], d) + 2];
		}
		//  将频率转换为索引
		for (int r = 0; r < R + 1; ++r) {
			counts[r + 1] += counts[r];
		}
		//  数据分类
		for (int i = lo; i <= hi; ++i) {
			aux[counts[charAt(a[i], d) + 1]++] = a[i];
		}
		//  回写
		for (int i = lo; i <= hi; ++i) {
			a[i] = aux[i - lo];
		}
		//  递归的以每个字符为键进行排序
		for (int r = 0; r < R; ++r) {
			int l = lo + counts[r];
			int h = lo + counts[r + 1] - 1;
			if (l < h) {
				a = sort(a, l, h, d + 1);
			}
		}
		return a;
	}

	public static void main(String[] args) {
		String[] a = {"09", "00", "08", "11", "07", "22", "06", "33", "5", "44", "04", "55", "03", "66", "02", "77", "01", "88", "00", "99"};

		System.out.println("sources: " + Arrays.toString(a));
		sort(a);
		System.out.println("targets: " + Arrays.toString(a));
	}

}
