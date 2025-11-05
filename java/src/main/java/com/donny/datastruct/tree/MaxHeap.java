package com.donny.datastruct.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 最大堆实现
 * 堆是一种完全二叉树，父节点的值总是大于或等于子节点的值
 */
public class MaxHeap {
    private List<Integer> heap;
    
    /**
     * 构造函数
     */
    public MaxHeap() {
        heap = new ArrayList<>();
    }
    
    /**
     * 构造函数，从数组构建堆
     * @param arr 数组
     */
    public MaxHeap(int[] arr) {
        heap = new ArrayList<>();
        for (int val : arr) {
            heap.add(val);
        }
        buildHeap();
    }
    
    /**
     * 构建堆
     */
    private void buildHeap() {
        for (int i = heap.size() / 2 - 1; i >= 0; i--) {
            heapifyDown(i);
        }
    }
    
    /**
     * 插入元素
     * @param val 要插入的值
     */
    public void insert(int val) {
        heap.add(val);
        heapifyUp(heap.size() - 1);
    }
    
    /**
     * 删除并返回最大值
     * @return 最大值
     * @throws IllegalStateException 如果堆为空
     */
    public int extractMax() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        
        int max = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        
        if (!isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        
        return max;
    }
    
    /**
     * 获取最大值（不删除）
     * @return 最大值
     * @throws IllegalStateException 如果堆为空
     */
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap.get(0);
    }
    
    /**
     * 向上调整堆
     */
    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index) <= heap.get(parentIndex)) {
                break;
            }
            swap(index, parentIndex);
            index = parentIndex;
        }
    }
    
    /**
     * 向下调整堆
     */
    private void heapifyDown(int index) {
        int size = heap.size();
        while (true) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int largest = index;
            
            if (leftChild < size && heap.get(leftChild) > heap.get(largest)) {
                largest = leftChild;
            }
            
            if (rightChild < size && heap.get(rightChild) > heap.get(largest)) {
                largest = rightChild;
            }
            
            if (largest == index) {
                break;
            }
            
            swap(index, largest);
            index = largest;
        }
    }
    
    /**
     * 交换两个元素
     */
    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
    
    /**
     * 判断堆是否为空
     * @return 是否为空
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }
    
    /**
     * 获取堆的大小
     * @return 堆的大小
     */
    public int size() {
        return heap.size();
    }
    
    /**
     * 转换为数组
     * @return 数组
     */
    public int[] toArray() {
        int[] arr = new int[heap.size()];
        for (int i = 0; i < heap.size(); i++) {
            arr[i] = heap.get(i);
        }
        return arr;
    }
}

