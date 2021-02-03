package com.oxford.core.algorithm.sort;

/**
 * 冒泡排序
 *
 * @author Chova
 * @date 2021/02/03
 */
public class BubbleSort {

    /**
     * 使用冒泡排序对数组进行排序
     *
     * @param array 数组
     */
    public static void bubbleSort(int[] array) {
        bubbleSort(array, 0, array.length - 1);
    }

    /**
     * 使用冒泡排序算法对从位置left到right的数组进行排序
     *
     * @param array 数组
     * @param left  数组的左边位置
     * @param right 数组的右边位置
     */
    public static void bubbleSort(int[] array, int left, int right) {
        if (null != array && array.length > 0 && left < right) {
            int length = right - left + 1;

            /**
             * round 控制比较的轮数
             */
            int round = 0;

            /**
             * time 控制每轮比较的次数
             */
            int time = 0;

            for (round = 0; round < length; round++) {
                // length - 1 - round 用于 减少比较的次数。每轮会比较得出一个最大数，下一轮只要比较上一轮最大数前面的数组序列
                for (time = 0; time < length - 1 - round; time++) {
                    if (array[time] > array[time + 1]) {
                        int tempValue = array[time];
                        array[time] = array[time + 1];
                        array[time + 1] = tempValue;
                    }
                }
            }
        }
    }
}
