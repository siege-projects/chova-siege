package com.oxford.core.algorithm.sort;

/**
 * 计数排序
 *
 * @author Chova
 * @date 2021/03/19
 */
public class CountingSort {

    /**
     * 使用计数排序算法对数组进行排序
     * - 找出待排序数组的最大元素和最小元素
     * - 统计待排序数组中每个偏移量值为i的元素出现的次数，存入计数数组的索引位置为i处
     * - 对统计完成的计数数组中每个索引位置的值累加，得到待排序数组每个值存放的位置
     * - 填充目标数组：将排序好的计数数组中的值按照次序填充到待排序数组中
     *
     * @param array 数组
     */
    public static void countingSort(int[] array) {
        countingSort(array, 0, array.length - 1);
    }

    /**
     * 使用计数排序算法对从位置left到right的数组进行排序
     *
     * @param array 数组
     * @param left  数组的左边位置
     * @param right 数组的右边位置
     */
    public static void countingSort(int[] array, int left, int right) {
        if (null != array && array.length > 0 && left < right) {
            int length = array.length;

            // 获取待排数组的最大最小值用来计算计数数组的长度
            int maxValue = array[0];
            int minValue = array[0];
            /**
             * 偏移量
             */
            int offset;
            for (int i = 0; i < length; i++) {
                if (maxValue < array[i]) {
                    maxValue = array[i];
                }
                if (minValue > array[i]) {
                    minValue = array[i];
                }
            }
            offset = maxValue - minValue + 1;

            // 创建计数数组并计算各个位置的值
            int[] countArray = new int[offset + 1];
            for (int i = 0; i < length; i++) {
                countArray[array[i] - minValue]++;
            }
            // 计算待排序数组每个偏移量代表的待排序数所放置的位置
            for (int i = 1; i < offset; i++) {
                countArray[i + 1] += countArray[i];
            }
            // 按照计数数组索引的值对待排序的数组进行排序
            int[] tempArray = new int[length];
            for (int i = 0; i < length; i++) {
                tempArray[countArray[array[i] - minValue]++] = array[i];
            }
            // 将排序好的数组的值写入到原待排序数组中
            for (int i = 0; i < length; i++) {
                array[i] = tempArray[i];
            }
        }
    }
}
