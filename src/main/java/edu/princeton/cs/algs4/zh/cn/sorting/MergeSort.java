package edu.princeton.cs.algs4.zh.cn.sorting;

/**
 * 自顶向下的归并排序(2.2.2 算法2.4)
 *
 * @author FXYGR @date 2019-10-29
 */
public class MergeSort extends DefaultMergeSort {

    public static void sort(Comparable[] a) {
        //  一次性分配空间
        aux = new Comparable[a.length];

        sort(a, 0, a.length - 1);
    }

    /**
     * 将数组a[lo, ..., hi]排序
     *
     * @param a
     * @param lo
     * @param hi
     */
    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = (lo + hi) / 2;
        //  将左半边排序
        sort(a, lo, mid);
        //  将右半边排序
        sort(a, mid + 1, hi);
        if (less(a[mid], a[mid + 1])) {
            return;
        }
        //  归并结果
        merge(a, lo, mid, hi);
    }

}
