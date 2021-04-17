package com.oxford.core.algorithm.sort;

import java.util.*;

/**
 * 桶排序
 *
 * @author Chova
 * @date 2021/04/12
 */
public class BucketSort {

    /**
     * 使用桶排序算法对数组进行排序：
     * - 首先初始化一个待排序数组长度的集合作为桶用于存放待排序数组中的元素
     * - 遍历待排序数组，根据桶排序的映射函数，将待排序数组的元素放入到对应的桶中
     * - 对于非空的桶进行排序，可以使用其余的排序算法，也可以递归使用桶排序
     * - 将非空的桶中排序好的元素拼接起来，完成待排序数组的排序
     *
     * @param array 数组
     */
    public static void bucketSort(int[] array) {
        bucketSort(array, 0, array.length - 1);
    }

    /**
     * 使用桶排序算法对从位置left到right的数组进行排序
     *
     * @param array 数组
     * @param left  数组的左边位置
     * @param right 数组的右边位置
     */
    public static void bucketSort(int[] array, int left, int right) {
        if (null != array && array.length > 0 && left < right) {
            /**
             * 桶数组的索引，待排序数组中的元素所在的桶的位置
             */
            int index = 0;
            int length = array.length;
            // 初始化一个桶
            int bucketLength = array.length;
            LinkedList<Integer>[] bucket = new LinkedList[bucketLength];
            // 获取待排序数组中的最大值，用于桶排序的映射函数
            int maxValue = Arrays.stream(array).max().getAsInt();

            // 将待排序数组中的元素根据桶排序的映射函数装入各个桶中
            for (int i = 0; i < length; i++) {
                // 根据桶排序的映射函数(array[i] * length)/(maxValue + 1)获取待排序数组中的元素所在的桶的位置
                index = (array[i] * length) / (maxValue + 1);
                // 如果索引值所在的位置桶不存在，则新建桶
                if (null == bucket[index]) {
                    bucket[index] = new LinkedList<>();
                }
                // 将待排序数组的元素加入桶中
                bucket[index].add(array[i]);
            }
            // 将桶排序后非空桶的元素按照顺序存放到临时列表中
            List<Integer> tempList = new ArrayList<>(length);
            // 桶排序过程中，桶数组中可能新建桶，所以不能使用之前定义的桶数组的长度参与循环遍历
            for (int i = 0; i < bucket.length; i++) {
                if (null != bucket[i]) {
                    Collections.sort(bucket[i]);
                    tempList.addAll(bucket[i]);
                }
            }
            // 将临时列表中的元素写入原待排序数组中
            for (int i = 0; i < length; i++) {
                array[i] = tempList.get(i);
            }
        }
    }
}
