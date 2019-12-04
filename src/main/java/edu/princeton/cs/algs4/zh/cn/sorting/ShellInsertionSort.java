package edu.princeton.cs.algs4.zh.cn.sorting;

import edu.princeton.cs.algs4.StdIn;

/**
 * 希尔插入排序(2.1.6 算法2.3)
 * {@link edu.princeton.cs.algs4.Shell}
 *
 * @author FXYGR @date 2019-10-25
 */
public class ShellInsertionSort extends AbstractSort {

    public static void sort(Comparable[] a) {
        int times = 3;
        int h = 1;
        while (h < (a.length / times)) {
            //  Knuth间隔序列
            //  1, 4, 13, 40, 121, 364, 1093, ...
            h = times * h + 1;
        }
        for (; h > 0; h /= times) {
            for (int i = h; i < a.length; ++i) {
                if (less(a[i - h], a[i])) {
                    continue;
                }
                Comparable temp = a[i];
                int j = i;
                for (; j >= h && less(temp, a[j - h]); j -= h) {
                    a[j] = a[j - h];
                }
                a[j] = temp;
            }
        }
    }

    /**
     * 将a[]按升序排列
     *
     * @param a
     */
    public static void exchSort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < (N / 3)) {
            //  Knuth间隔序列
            //  1, 4, 13, 40, 121, 364, 1093, ...
            h = 3 * h + 1;
        }
        while (h >= 1) {
            //  将数组变为h有序
            for (int i = h; i < N; ++i) {
                //  将a[i]插入到a[i=h], a[i-2*h], a[i-3h], ...之中
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        show(a);
        exchSort(a);
        show(a);
    }

}
