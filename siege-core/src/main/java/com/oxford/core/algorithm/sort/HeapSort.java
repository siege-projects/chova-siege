package com.oxford.core.algorithm.sort;

/**
 * 堆排序
 *
 * @author Chova
 * @date 2021/03/08
 */
public class HeapSort {

    /**
     * 使用堆排序算法对数组进行排序
     * - 将初始的待排序关键字序列R1，R2，...，Rn构建成大顶堆，这是一个初始的无序区
     * - 将堆顶元素R1与最后一个元素Rn交换位置，得到一个新的无序区R1，R2，...，Rn和一个新的有序区Rn，并且满足R1，R2，...，Rn-1均小于Rn
     * - 因为交换后新的堆顶R1可能不符合堆的性质，所以需要对新的无序区R1，R2，...Rn-1调整为新的大顶堆，然后再一次将新的无序区中的堆顶元素R1
     * 与无序区最后一个元素Rn-1交换，得到一个新的无序区R1，R2，...，Rn-2和一个新的有序区Rn-1，Rn-2
     * - 重复这样的过程，直到整个待排序的序列都排序完成
     *
     * @param array 数组
     */
    public static void heapSort(int[] array) {
        heapSort(array, 0, array.length - 1);
    }

    /**
     * 使用堆排序算法对从位置left到right的数组进行排序
     *
     * @param array 数组
     * @param left  数组左边位置
     * @param right 数组右边位置
     */
    public static void heapSort(int[] array, int left, int right) {
        if (null != array && array.length > 0 && left < right) {
            int length = right - left + 1;

            // 调整构建大顶堆
            for (int index = (length >> 1) - 1; index >= 0; index--) {
                // 叶子节点不用调整，从第一个非叶子节点开始，从下至上，从右至左调整构建大顶堆
                adjustHeap(array, index, length);
            }

            // 交换堆顶元素，重复进行调整构建大顶堆
            for (int newLength = length - 1; newLength > 0; newLength--) {
                // 交换堆顶元素和整个序列的末尾元素
                int temp = array[newLength];
                array[newLength] = array[0];
                array[0] = temp;
                // 对交换之前的元素重复调整构建大顶堆
                adjustHeap(array, 0, newLength);
            }
        }
    }

    /**
     * 调整构建大顶堆
     *
     * @param array      进行构建大顶堆的序列数组
     * @param startIndex 大顶堆的节点索引
     * @param length     进行构建大顶堆序列的长度
     */
    private static void adjustHeap(int[] array, int startIndex, int length) {
        // 首先取出父节点元素的值
        int parentValue = array[startIndex];
        // 从父节点的左节点，也就是完全二叉树的数组位置的startIndex * 2 + 1处开始
        for (int childNode = (startIndex << 1) + 1; childNode < length; childNode = (childNode << 1) + 1) {
            if (childNode + 1 < length && array[childNode] < array[childNode + 1]) {
                // 如果左子节点小于右子节点，那么子节点索引指向右子节点
                childNode++;
            }
            if (array[childNode] > parentValue) {
                // 如果子节点的值大于父节点的值，则将子节点的值赋值给父节点
                array[startIndex] = array[childNode];
                // 当子节点的值大于父节点的值时，将子节点的索引赋值给父节点，用于确定父节点的值的最终位置
                startIndex = childNode;
            } else {
                break;
            }
        }
        // 将父节点的值放置到最终索引位置
        array[startIndex] = parentValue;
    }
}
