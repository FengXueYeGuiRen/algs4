package edu.princeton.cs.algs4.zh.cn.sorting;

import edu.princeton.cs.algs4.StdIn;

/**
 * description
 *
 * @author FXYGR @date 2019-10-31
 */
public class MSort extends AbstractSort {

    public static void sort(Comparable[] a) {
        Comparable[] targets = new Comparable[a.length];

        sort(a, targets, 0, a.length - 1);

        a = targets;
    }

    private static void sort(Comparable[] sources, Comparable[] targets, int lo, int hi) {
        if (lo >= hi) {
            targets[hi] = sources[hi];
            return;
        }
        Comparable[] temps = new Comparable[hi + 1];
        int mid = (lo + hi) / 2;
        //  将左半边排序
        sort(sources, temps, lo, mid);
        //  将右半边排序
        sort(sources, temps, mid + 1, hi);
        if (less(sources[mid], sources[mid + 1])) {
            return;
        }
        //  归并结果
        merge(temps, targets, lo, mid, hi);
    }

    protected static void merge(Comparable[] sources, Comparable[] targets, int lo, int mid, int hi) {
        //  将a[lo, ..., mid]和a[mid+1, ..., hi]归并
        int i = lo, j = mid + 1;
        //  从a归并回到targets[lo, ..., hi]
        for (int k = lo; k <= hi; ++k) {
            if (i > mid) {
                targets[k] = sources[j++];
                continue;
            }
            if (j > hi) {
                targets[k] = sources[i++];
                continue;
            }
            if (less(sources[j], sources[i])) {
                //  a[j] <= a[i]
                targets[k] = sources[j++];
                continue;
            }
            //  a[i] <= a[j]
            targets[k] = sources[i++];
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        show(a);
        sort(a);
        show(a);

        assert isSorted(a);
    }

}
