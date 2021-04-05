package com.oxford.core.algorithm.sort;

/**
 * 插入排序
 *
 * @author Chova
 * @date 2021/02/04
 */
public class InsertionSort {

    /**
     * 使用插入排序算法对数组进行排序
     * - 第一个元素默认已经是排序的，从第一个元素开始取下一个元素，在已经排序的序列中从后向前扫描
     * - 如果扫描的元素大于取出的元素，则将序列中扫描的元素向后移动一个位置，直到找到扫描的元素小于或者等于取出的z新元素的位置
     * - 将取出的元素插入到这个位置
     * - 直到整个序列中的所有元素都完成这样的操作，整个序列就是有序的
     *
     * @param array 数组
     */
    public static void insertionSort(int[] array) {
        insertionSort(array, 0, array.length - 1);
    }

    /**
     * 使用插入排序算法对从位置left到right的数组进行排序
     *
     * @param array 数组
     * @param left  数组左边位置
     * @param right 数组右边位置
     */
    public static void insertionSort(int[] array, int left, int right) {
        if (null != array && array.length > 0 && left < right) {
            int length = array.length;

            /**
             * sortedNum 已经排序好的元素个数
             */
            int sortedNum = 0;

            /**
             * insertIndex 需要插入的元素的索引
             */
            int insertIndex = 0;

            /**
             * insertValue 需要插入的元素的值
             */
            int insertValue = 0;

            for (sortedNum = 1; sortedNum < length; sortedNum++) {
                insertValue = array[sortedNum];

                // 已经排序完成的元素的个数
                insertIndex = sortedNum - 1;
                while (insertIndex >= 0 && array[insertIndex] > insertValue) {
                    // 将需要插入的元素和已经排序好的元素进行从后向前比较，将大于需要插入的数向后移动一位
                    array[insertIndex + 1] = array[insertIndex];
                    insertIndex--;
                }
                // 因为上面insertIndex的值最后一步减了1
                array[insertIndex + 1] = insertValue;
            }
        }
    }
}
