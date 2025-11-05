package com.donny.algorithm.search;

/**
 * 二分搜索
 * 时间复杂度：O(log n)
 * 空间复杂度：O(1)
 * 前提条件：数组必须是有序的
 */
public class BinarySearch {
    
    /**
     * 二分搜索（升序数组）
     * @param arr 有序数组
     * @param target 目标值
     * @return 目标值的索引，如果不存在返回-1
     */
    public static int search(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2; // 防止溢出
            
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }
    
    /**
     * 查找第一个等于目标值的位置
     * @param arr 有序数组
     * @param target 目标值
     * @return 第一个等于目标值的索引，如果不存在返回-1
     */
    public static int searchFirst(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = arr.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                result = mid;
                right = mid - 1; // 继续在左边查找
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    /**
     * 查找最后一个等于目标值的位置
     * @param arr 有序数组
     * @param target 目标值
     * @return 最后一个等于目标值的索引，如果不存在返回-1
     */
    public static int searchLast(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = arr.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                result = mid;
                left = mid + 1; // 继续在右边查找
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    /**
     * 查找第一个大于等于目标值的位置
     * @param arr 有序数组
     * @param target 目标值
     * @return 第一个大于等于目标值的索引，如果不存在返回-1
     */
    public static int searchFirstGreaterOrEqual(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = arr.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] >= target) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return result;
    }
}

