package com.oxford.core.algorithm.sort;

/**
 * 希尔排序
 *
 * @author Chova
 * @date 2021/02/05
 */
public class ShellSort {

    /**
     * 使用希尔排序算法对数组进行排序
     * - 将整个待排序的序列按照步长分割成若干个子序列分别进行直接插入排序
     * - 选择一个增量序列作为步长序列，增量序列的值应该介于1和数组长度减1之间
     * - 按照增量序列的个数k对序列进行k趟排序
     * - 在每趟排序中，根据对应的增量序列的步长值，将待排序序列分割成若干个长度相等的子序列，分别对各个子序列进行直接插入排序
     * - 仅当增量序列的步长值为1时，将序列作为一整个序列排序处理，排序完成后整个序列就是有序的
     *
     * @param array 数组
     */
    public static void shellSort(int[] array) {
        shellSort(array, 0, array.length - 1);
    }

    /**
     * 使用希尔排序算法对从位置left到位置right的数组的进行排序
     *
     * @param array 数组
     * @param left  数组左边位置
     * @param right 数组右边位置
     */
    public static void shellSort(int[] array, int left, int right) {
        if (null != array && array.length > 0 && left < right) {
            int length = array.length;

            /**
             * 当前比较位置
             */
            int currentIndex;

            /**
             * 当前比较位置的值
             */
            int currentValue;

            /**
             * 步长
             * 希尔排序推荐的步长为需要比较的数组的长度的二分之一，但是仍然有减少平均时间和最差时间的余地
             * 在大数组中可以使用步长值为斐波那契数列除去0和1将剩余的数以黄金分割比的两倍的幂进行运算得到的数列
             */
            int gap = length >> 1;

            while (gap > 0) {
                for (currentIndex = gap; currentIndex < length; currentIndex++) {
                    currentValue = array[currentIndex];
                    int mutableIndex = currentIndex - gap;
                    /* 运用插入排序的思想
                     * 将当前值与上一步长的值进行比较，如果上一步长的值大于当前值则向后移动一个步长的上一步长的值
                     * 直到找到比当前值小的值的位置，然后将当前值放到上一次比较的位置
                     */
                    while (mutableIndex >= 0 && array[mutableIndex] > currentValue) {
                        array[mutableIndex + gap] = array[mutableIndex];
                        mutableIndex -= gap;
                    }
                    // 将当前值放到上一次比较的位置
                    array[mutableIndex + gap] = currentValue;
                }
                gap >>= 1;
            }
        }
    }
}
