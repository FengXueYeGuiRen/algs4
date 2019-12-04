package edu.princeton.cs.algs4.zh.cn.sorting;

import edu.princeton.cs.algs4.*;

/**
 * 比较两种排序算法
 *
 * @author FXYGR @date 2019-10-27
 */
public class SortCompare {

	public static double time(String alg, Comparable[] a) {
		Stopwatch timer = new Stopwatch();
		//  选择排序
		if (alg.equalsIgnoreCase("Selection")) {
			Selection.sort(a);
		}
		if (alg.equalsIgnoreCase("SelectionSort")) {
			SelectionSort.sort(a);
		}
		//  插入排序
		if (alg.equalsIgnoreCase("Insertion")) {
			Insertion.sort(a);
		}
		if (alg.equalsIgnoreCase("InsertionExchSort")) {
			//  采用交换方式的插入排序
			InsertionSort.exchSort(a);
		}
		if (alg.equalsIgnoreCase("InsertionInsertionSort")) {
			InsertionSort.insertionSort(a);
		}
		if (alg.equalsIgnoreCase("InsertionSort")) {
			InsertionSort.sort(a);
		}
		//  希尔插入排序
		if (alg.equalsIgnoreCase("Shell")) {
			Shell.sort(a);
		}
		if (alg.equalsIgnoreCase("ShellExchSort")) {
			//  采用交换方式的希尔排序
			ShellInsertionSort.exchSort(a);
		}
		if (alg.equalsIgnoreCase("ShellInsertionSort")) {
			ShellInsertionSort.sort(a);
		}
		//  快速排序
		if (alg.equalsIgnoreCase("Quick")) {
			Quick.sort(a);
		}
		if (alg.equalsIgnoreCase("QuickSort")) {
			QuickSort.sort(a);
		}
		//  三向切分的快速排序
		if (alg.equalsIgnoreCase("Quick3way")) {
			Quick3way.sort(a);
		}
		if (alg.equalsIgnoreCase("Quick3waySort")) {
			Quick3waySort.sort(a);
		}
		return timer.elapsedTime();
	}

	/**
	 * 使用算法将T个长度为N的数组排序
	 *
	 * @param alg 算法名
	 * @param N   数组长度
	 * @param T   数组个数
	 * @return
	 */
	public static double timeRandomInput(String alg, int N, int T) {
		double total = 0.0;
		Double[] a = new Double[N];
		for (int t = 0; t < T; ++t) {
			//  进行一次测试(生成一个数组并排序)
			for (int i = 0; i < N; ++i) {
				a[i] = StdRandom.uniform();
			}
			total += time(alg, a);
		}
		return total;
	}

	public static void main(String[] args) {
		String alg1 = args[0];
		String alg2 = args[1];

		int N = Integer.parseInt(args[2]);
		int times = Integer.parseInt(args[3]);

		//  算法1的总时间
		double t1 = timeRandomInput(alg1, N, times);
		StdOut.printf("%s: %f(seconds, %d times)\n", alg1, t1, times);
		//  算法2的总时间
		double t2 = timeRandomInput(alg2, N, times);
		StdOut.printf("%s: %f(seconds, %d times)\n", alg2, t2, times);

		StdOut.printf("For %d random Doubles\n %s is ", N, alg1);
		StdOut.printf("%.1f times faster than %s\n", t1 / t2, alg2);

		StdOut.printf("For %d random Doubles\n %s is ", N, alg2);
		StdOut.printf("%.1f times faster than %s\n", t2 / t1, alg1);
	}

}
