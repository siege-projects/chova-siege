package com.oxford.core.algorithm.sort;

import java.util.Arrays;

/**
 * 基数排序
 *
 * @author Chova
 * @date 2021/04/17
 */
public class RadixSort {

    /**
     * 使用基数排序算法对数组进行排序：
     * - 获取待排序数组中元素最大的数字，计算数字的位数
     * - 从最低位开始按照待排序数组元素指定位上的数字对待排序数组中的元素进行基数排序
     * - 每一轮完成一个位数上的排序，直到完成所有的位数上的排序后，完成待排序数组的排序
     *
     * @param array
     */
    public static void radixSort(int[] array) {
        radixSort(array, 0, array.length - 1);
    }

    /**
     * 使用基数排序算法对从位置left到right的数组进行排序
     *
     * @param array 数组
     * @param left  数组左边位置
     * @param right 数组右边位置
     */
    public static void radixSort(int[] array, int left, int right) {
        if (null != array && array.length > 0 && left < right) {
            int length = array.length;
            /**
             * 基数为0-9，定义基数的值为10
             */
            int radix = 10;
            /**
             * 统计基数出现的次数
             */
            int[] count = new int[length + 1];
            /**
             * 用于存放基数排序每一轮后的数组的值
             */
            int[] tempArray = new int[length];
            /**
             * 待排序数组中元素的最大位数
             * 最大位数表示基数排序需要进行的轮数
             * 对于待排序数组中元素的位数小于最大位数时，在超过位数的基数排序轮数时，这些元素的位数的值使用0替代
             */
            int maxDigit = Arrays.stream(array).map(value -> String.valueOf(value).length()).max().getAsInt();

            for (int d = 0; d < maxDigit; d++) {
                // 从最低位开始获取指定位数上的基数的数字，并统计次数，出现一次，统计次数增加1
                for (int i = 0; i < length; i++) {
                    count[((int) ((array[i] / Math.pow(10, d)) % 10)) + 1]++;
                }
                // 统计基数的出现次数完成后，计算每个元素经过一轮排序后在待排序数组中的索引位置
                for (int i = 0; i < radix; i++) {
                    count[i + 1] += count[i];
                }
                // 使用一个临时数组存入每一轮基数排序后的值
                for (int i = 0; i < length; i++) {
                    int index = count[(int) ((array[i] / Math.pow(10, d)) % 10)];
                    tempArray[count[(int) ((array[i] / Math.pow(10, d)) % 10)]++] = array[i];
                }
                // 每一轮基数排序完成后，将排序好的数据写入到待排序数组中，对每一轮基数排序后的数组继续进行基数排序，直到基数排序完成
                for (int i = 0; i < length; i++) {
                    array[i] = tempArray[i];
                }
                // 初始化count数组的值为0，用于下一轮的基数排序
                for (int i = 0; i < count.length; i++) {
                    count[i] = 0;
                }
            }
        }
    }
}
