package com.donny.algorithm.sort;

/**
 * 快速排序
 * 时间复杂度：平均 O(n log n)，最坏 O(n²)
 * 空间复杂度：O(log n)
 * 稳定性：不稳定
 */
public class QuickSort {
    
    /**
     * 快速排序（升序）
     * @param arr 要排序的数组
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        sort(arr, 0, arr.length - 1);
    }
    
    /**
     * 快速排序递归实现
     * @param arr 要排序的数组
     * @param low 起始索引
     * @param high 结束索引
     */
    private static void sort(int[] arr, int low, int high) {
        if (low < high) {
            // 分区操作，获取基准元素的正确位置
            int pivotIndex = partition(arr, low, high);
            
            // 递归排序基准元素左边的子数组
            sort(arr, low, pivotIndex - 1);
            
            // 递归排序基准元素右边的子数组
            sort(arr, pivotIndex + 1, high);
        }
    }
    
    /**
     * 分区操作
     * @param arr 数组
     * @param low 起始索引
     * @param high 结束索引
     * @return 基准元素的最终位置
     */
    private static int partition(int[] arr, int low, int high) {
        // 选择最后一个元素作为基准
        int pivot = arr[high];
        
        // 小于基准的元素应该放在左边
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            // 如果当前元素小于或等于基准
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        
        // 将基准元素放到正确位置
        swap(arr, i + 1, high);
        
        return i + 1;
    }
    
    /**
     * 交换数组中的两个元素
     * @param arr 数组
     * @param i 索引1
     * @param j 索引2
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

