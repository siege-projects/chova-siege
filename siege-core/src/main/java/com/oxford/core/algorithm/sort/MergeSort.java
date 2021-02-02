package com.oxford.core.algorithm.sort;

/**
 * 归并排序
 *
 * @author Chova
 * @date 2021/02/02
 */
public class MergeSort {

    /**
     * 使用归并算法对数组进行排序
     * - 将数组分割成两个长度一致的子数组序列
     * - 将这两个子数组序列分别进行归并排序
     * - 将两个排序好的子数组序列合并成一个排好序的数组序列
     *
     * @param array 数组
     */
    public static void mergeSort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    /**
     * 使用归并排序算法对从位置left到right的数组进行排序
     *
     * @param array 数组
     * @param left  数组左边位置
     * @param right 数组右边位置
     */
    public static void mergeSort(int[] array, int left, int right) {
        if (null != array && array.length > 0 && left < right) {
            // 加法运算的优先级高于移位运算
            int mid = left + ((right - left) >> 1);
            // 对左侧子数组序列进行排序
            mergeSort(array, left, mid);
            // 对右侧子数组序列进行排序
            mergeSort(array, mid + 1, right);
            // 合并左右两侧子序列
            merge(array, left, mid, right);
        }
    }

    /**
     * 合并数组[left, mid]和数组[mid + 1, right]为[left, right]
     *
     * @param array 数组
     * @param left  数组左边位置
     * @param mid   数组中间位置
     * @param right 数组右边位置
     */
    private static void merge(int[] array, int left, int mid, int right) {
        int[] tempArray = new int[right - left + 1];
        // 数组的索引
        int i = 0;
        // 分割的左子数组的开始索引
        int leftStart = left;
        // 分割的右子数组的开始索引
        int rightStart = mid + 1;

        // 比较两个子数组中的元素，将较小的元素放入到临时数组中
        while (leftStart <= mid && rightStart <= right) {
            tempArray[i++] = array[leftStart] < array[rightStart] ? array[leftStart++] : array[rightStart++];
        }
        while (leftStart <= mid) {
            tempArray[i++] = array[leftStart++];
        }
        while (rightStart <= right) {
            tempArray[i++] = array[rightStart++];
        }

        // 将临时数组的值复制给原数组
        for (i = 0; i < tempArray.length; i++) {
            array[left + i] = tempArray[i];
        }
    }
}
