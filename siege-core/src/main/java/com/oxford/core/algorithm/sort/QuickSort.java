package com.oxford.core.algorithm.sort;

/**
 * 快速排序
 *
 * @author Chova
 * @date 2021/02/01
 */
public class QuickSort {

    /**
     * 使用快速排序算法对数组进行排序
     * - 使用分治法将一个串分为两个子串
     * - 从数列中选取一个元素作为基准元素pivot
     * - 使用分区操作partition重新排列数列，将所有比基准元素小的元素放在基准前面，将所有比基准元素大的元素放在基准后面，相同元素位置任意。在这个分区退出后，
     * 基准元素刚好就处于数列的"中间"位置
     * - 递归地将小于基准值的数列和大于基准值的数列进行分区操作，直到排序完成
     *
     * @param array 数组
     */
    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    /**
     * 使用快速排序算法对从位置left到位置right的数组进行排序
     *
     * @param array 数组
     * @param left  数组左边位置
     * @param right 数组右边位置
     */
    public static void quickSort(int[] array, int left, int right) {
        if (null != array && array.length > 0 && left < right) {
            int pivot = partition(array, left, right);
            quickSort(array, left, pivot);
            quickSort(array, pivot + 1, right);
        }
    }

    /**
     * 对数组array从位置left到right进行分组
     *
     * @param array 数组
     * @param left  数组左边位置
     * @param right 数组右边位置
     * @return int 基准位置
     */
    private static int partition(int[] array, int left, int right) {
        int pivot = array[left];

        while (left < right) {
            // 将基准值和从数组右边位置开始的值进行比较分组
            while (pivot < array[right] && left < right) {
                --right;
            }
            if (left < right) {
                array[left] = array[right];
                ++left;
            }
            // 将基准值和从数组左边位置开始的值进行比较分组
            while (pivot >= array[left] && left < right) {
                ++left;
            }
            if (left < right) {
                array[right] = array[left];
                --right;
            }
        }
        array[left] = pivot;
        return left;
    }
}
