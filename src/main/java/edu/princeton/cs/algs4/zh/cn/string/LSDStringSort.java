package edu.princeton.cs.algs4.zh.cn.string;

import java.util.Arrays;

/**
 * 低位优先的字符串排序(5.1.2 算法 5.1)
 * {@link edu.princeton.cs.algs4.LSD}
 *
 * @author FXYGR @date 2020-02-23
 */
public class LSDStringSort {

	public static String[] sort(String[] a) {
		if (a == null) {
			return a;
		}
		return sort(a, a[0].length());
	}

	public static String[] sort(String[] a, int w) {
		//  通过后 w 个字符将 a[] 排序
		return sort(a, w, 256);
	}

	public static String[] sort(String[] a, int w, int r) {
		if (a == null) {
			return a;
		}
		int aLength = a.length;
		String[] aux = new String[aLength];
		int[] counts;
		for (int d = w - 1; d >= 0; --d) {
			//  根据第 d 个字符用键索引计数法排序
			counts = new int[r + 1];
			//  计算出现频率
			for (int i = 0; i < aLength; ++i) {
				++counts[a[i].charAt(d) + 1];
			}
			//  将频率转换为索引
			for (int i = 0; i < r; ++i) {
				counts[i + 1] += counts[i];
			}
			//  将元素分类
			for (int i = 0; i < aLength; ++i) {
				aux[counts[a[i].charAt(d)]++] = a[i];
			}
			//  回写
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
