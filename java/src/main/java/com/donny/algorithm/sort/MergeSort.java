package com.donny.algorithm.sort;

/**
 * 归并排序
 * 时间复杂度：O(n log n)
 * 空间复杂度：O(n)
 * 稳定性：稳定
 */
public class MergeSort {
    
    /**
     * 归并排序（升序）
     * @param arr 要排序的数组
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int[] temp = new int[arr.length];
        sort(arr, temp, 0, arr.length - 1);
    }
    
    /**
     * 归并排序递归实现
     * @param arr 要排序的数组
     * @param temp 临时数组
     * @param left 左边界
     * @param right 右边界
     */
    private static void sort(int[] arr, int[] temp, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            // 递归排序左半部分
            sort(arr, temp, left, mid);
            
            // 递归排序右半部分
            sort(arr, temp, mid + 1, right);
            
            // 合并两个有序子数组
            merge(arr, temp, left, mid, right);
        }
    }
    
    /**
     * 合并两个有序子数组
     * @param arr 原数组
     * @param temp 临时数组
     * @param left 左边界
     * @param mid 中间位置
     * @param right 右边界
     */
    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        // 复制数据到临时数组
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }
        
        int i = left;      // 左子数组的起始索引
        int j = mid + 1;   // 右子数组的起始索引
        int k = left;      // 合并后数组的起始索引
        
        // 合并两个有序子数组
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                arr[k++] = temp[i++];
            } else {
                arr[k++] = temp[j++];
            }
        }
        
        // 复制剩余元素
        while (i <= mid) {
            arr[k++] = temp[i++];
        }
        
        while (j <= right) {
            arr[k++] = temp[j++];
        }
    }
}

