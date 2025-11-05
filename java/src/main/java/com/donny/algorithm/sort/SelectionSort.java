package com.donny.algorithm.sort;

/**
 * 选择排序
 * 时间复杂度：O(n²)
 * 空间复杂度：O(1)
 * 稳定性：不稳定
 */
public class SelectionSort {
    
    /**
     * 选择排序（升序）
     * @param arr 要排序的数组
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            
            // 找到未排序部分的最小元素
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            
            // 将最小元素交换到当前位置
            if (minIndex != i) {
                swap(arr, i, minIndex);
            }
        }
    }
    
    /**
     * 选择排序（降序）
     * @param arr 要排序的数组
     */
    public static void sortDesc(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        
        for (int i = 0; i < arr.length - 1; i++) {
            int maxIndex = i;
            
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }
            
            if (maxIndex != i) {
                swap(arr, i, maxIndex);
            }
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

