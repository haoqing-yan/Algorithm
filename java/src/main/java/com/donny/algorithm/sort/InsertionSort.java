package com.donny.algorithm.sort;

/**
 * 插入排序
 * 时间复杂度：最好 O(n)，最坏 O(n²)，平均 O(n²)
 * 空间复杂度：O(1)
 * 稳定性：稳定
 */
public class InsertionSort {
    
    /**
     * 插入排序（升序）
     * @param arr 要排序的数组
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            
            // 将大于 key 的元素向右移动
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
    
    /**
     * 插入排序（降序）
     * @param arr 要排序的数组
     */
    public static void sortDesc(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            
            while (j >= 0 && arr[j] < key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}

