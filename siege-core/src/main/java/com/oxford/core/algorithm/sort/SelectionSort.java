package com.oxford.core.algorithm.sort;

/**
 * 选择排序
 *
 * @author Chova
 * @date 2021/02/03
 */
public class SelectionSort {

    /**
     * 使用选择排序算法对数组进行排序
     * - 初始时，无序区为R[1, ..., n]，有序区为空
     * - 第i轮排序开始时，当前有序区为R[1, ..., i - 1]，无序区为R[i, ..., n]。此时这轮排序从当前无序区中选出关键字最小的记录R[k]，将最
     * 小的记录R[k]与无序区的第1个元素进行交换，使得有序区R[1, ..., i - 1]和无序区R[i, ..., n]分别变为记录个数增加1个的新的有序区和记录
     * 个数减少1个的新的无序区
     * - n-1轮结束后,数组序列就是有序化的
     *
     * @param array 数组
     */
    public static void selectionSort(int[] array) {
        selectionSort(array, 0, array.length - 1);
    }

    /**
     * 使用选择排序算法对从位置left到right的数组进行排序
     *
     * @param array 数组
     * @param left  数组左边位置
     * @param right 数组右边位置
     */
    public static void selectionSort(int[] array, int left, int right) {
        if (null != array && array.length > 0 && left < right) {
            int length = array.length;

            /**
             * round 控制比较的轮数
             */
            int round;

            /**
             * index 控制比较的索引
             */
            int index;

            for (round = 0; round < length - 1; round++) {
                int minValueIndex = round;
                for (index = minValueIndex + 1; index < length; index++) {
                    if (array[minValueIndex] > array[index]) {
                        minValueIndex = index;
                    }
                }
                if (round != minValueIndex) {
                    int tempValue = array[round];
                    array[round] = array[minValueIndex];
                    array[minValueIndex] = tempValue;
                }
            }
        }
    }
}
