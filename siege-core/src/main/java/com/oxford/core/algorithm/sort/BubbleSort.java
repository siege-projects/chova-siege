package com.oxford.core.algorithm.sort;

/**
 * 冒泡排序
 *
 * @author Chova
 * @date 2021/02/03
 */
public class BubbleSort {

    /**
     * 使用冒泡排序算法对数组进行排序
     * - 比较两个相邻的元素，如果第一个元素比第二个元素大，则交换两个元素的顺序
     * - 对每一对相邻的元素进行相同的操作，从开始的第一对到最后一对，这样每一轮比较结束后，在最后的元素就是这一轮中最大的元素
     * - 然后对每一轮最后一个元素之前所有的元素序列进行这样的比较，直到排序完成
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
            int length = array.length;

            /**
             * round 控制比较的轮数
             */
            int round;

            /**
             * time 控制每轮比较的次数
             */
            int time;

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
