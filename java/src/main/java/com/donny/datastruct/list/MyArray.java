package com.donny.datastruct.list;

import com.donny.datastruct.abstractstruct.MyList;

/**
 * @author donnyyan
 * @date 2024/5/31
 */
public class MyArray implements MyList {
    private int[] data;
    private int size;

    // 默认构造函数，初始化一个容量为10的数组
    public MyArray() {
        this(10);
    }

    // 带参数的构造函数，可以自定义初始容量
    public MyArray(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    // 获取数组大小
    public int size() {
        return size;
    }

    // 判断数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void printList() {

    }

    // 在数组末尾添加元素
    public void add(int element) {
        if (size == data.length) {
            resize(2 * data.length); // 动态扩容
        }
        data[size++] = element;
    }

    // 获取指定位置的元素
    public int get(int index) {
        checkIndex(index);
        return data[index];
    }

    // 设置指定位置的元素
    public void set(int index, int element) {
        checkIndex(index);
        data[index] = element;
    }

    // 删除指定位置的元素，并返回删除的元素
    public int remove(int index) {
        checkIndex(index);
        int removedElement = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size] = 0; // 将最后一个元素设为0
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2); // 缩小容量
        }
        return removedElement;
    }

    // 检查索引是否越界
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }

    // 动态调整数组大小
    private void resize(int newCapacity) {
        int[] newData = new int[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    // 打印数组
    public void printArray() {
        for (int i = 0; i < size; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

}
