package com.donny.algorithm.sort;

/**
 * 堆排序
 * 时间复杂度：O(n log n)
 * 空间复杂度：O(1)
 * 稳定性：不稳定
 */
public class HeapSort {
    
    /**
     * 堆排序（升序）
     * @param arr 要排序的数组
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        
        int n = arr.length;
        
        // 构建最大堆
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        
        // 逐个从堆中取出元素
        for (int i = n - 1; i > 0; i--) {
            // 将根节点（最大值）与最后一个节点交换
            swap(arr, 0, i);
            
            // 重新调整堆（排除已排序的元素）
            heapify(arr, i, 0);
        }
    }
    
    /**
     * 调整堆，使其满足最大堆性质
     * @param arr 数组
     * @param n 堆的大小
     * @param i 要调整的节点索引
     */
    private static void heapify(int[] arr, int n, int i) {
        int largest = i;      // 初始化最大值为根节点
        int left = 2 * i + 1; // 左子节点
        int right = 2 * i + 2; // 右子节点
        
        // 如果左子节点大于根节点
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        
        // 如果右子节点大于当前最大值
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        
        // 如果最大值不是根节点，则交换并继续调整
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }
    
    /**
     * 交换数组中的两个元素
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

