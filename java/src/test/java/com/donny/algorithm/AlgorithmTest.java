package com.donny.algorithm;

import com.donny.algorithm.search.BinarySearch;
import com.donny.algorithm.sort.BubbleSort;
import com.donny.algorithm.sort.MergeSort;
import com.donny.algorithm.sort.QuickSort;

import java.util.Arrays;

/**
 * 算法测试类
 */
public class AlgorithmTest {
    
    public static void main(String[] args) {
        System.out.println("========== 算法测试 ==========");
        
        testSorting();
        testSearch();
        
        System.out.println("========== 测试完成 ==========");
    }
    
    /**
     * 测试排序算法
     */
    private static void testSorting() {
        System.out.println("\n--- 测试排序算法 ---");
        
        int[] arr1 = {64, 34, 25, 12, 22, 11, 90};
        int[] arr2 = arr1.clone();
        int[] arr3 = arr1.clone();
        
        System.out.println("原数组: " + Arrays.toString(arr1));
        
        // 冒泡排序
        BubbleSort.sort(arr1);
        System.out.println("冒泡排序后: " + Arrays.toString(arr1));
        
        // 快速排序
        QuickSort.sort(arr2);
        System.out.println("快速排序后: " + Arrays.toString(arr2));
        
        // 归并排序
        MergeSort.sort(arr3);
        System.out.println("归并排序后: " + Arrays.toString(arr3));
    }
    
    /**
     * 测试搜索算法
     */
    private static void testSearch() {
        System.out.println("\n--- 测试搜索算法 ---");
        
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("有序数组: " + Arrays.toString(arr));
        
        int target = 7;
        int index = BinarySearch.search(arr, target);
        System.out.println("查找元素 " + target + " 的索引: " + index);
        
        // 测试查找第一个
        int[] arr2 = {1, 2, 2, 2, 3, 4, 5};
        System.out.println("\n数组: " + Arrays.toString(arr2));
        int firstIndex = BinarySearch.searchFirst(arr2, 2);
        System.out.println("查找第一个2的索引: " + firstIndex);
        
        int lastIndex = BinarySearch.searchLast(arr2, 2);
        System.out.println("查找最后一个2的索引: " + lastIndex);
    }
}

