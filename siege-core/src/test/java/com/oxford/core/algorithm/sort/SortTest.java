package com.oxford.core.algorithm.sort;

import java.util.Arrays;

/**
 * 数据结构排序算法 - 测试类
 *
 * @author Chova
 * @date 2021/02/01
 */
public class SortTest {
    public static void main(String[] args) {
        int[] array = {3, 62, 66, 5, 6, 26, 22, 36, 8, 92, 16, 52};

        // 快速排序
        // quickSort(array);

        // 归并排序
        // mergeSort(array);

        // 冒泡排序
        // bubbleSort(array);

        // 选择排序
        // selectionSort(array);

        // 插入排序
        // insertionSort(array);

        // 希尔排序
        // shellSort(array);

        // 堆排序
        // heapSort(array);

        // 计数排序
        // countingSort(array);

        Arrays.stream(array).forEach(i -> System.out.print(i + ","));
    }
}
