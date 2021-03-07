package com.oxford.core.algorithm.sort;

import java.util.Arrays;

import static com.oxford.core.algorithm.sort.ShellSort.shellSort;

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
        shellSort(array);
        Arrays.stream(array).forEach(i -> System.out.print(i + ","));
    }
}
