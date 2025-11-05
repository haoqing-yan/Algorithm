package com.donny.datastruct.array;

/**
 * 动态数组实现
 * 支持自动扩容和缩容
 */
public class DynamicArray<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double LOAD_FACTOR = 0.75;
    private static final double SHRINK_FACTOR = 0.25;
    
    private E[] data;
    private int size;
    private int capacity;
    
    /**
     * 构造函数
     */
    @SuppressWarnings("unchecked")
    public DynamicArray() {
        capacity = DEFAULT_CAPACITY;
        data = (E[]) new Object[capacity];
        size = 0;
    }
    
    /**
     * 指定容量的构造函数
     * @param initialCapacity 初始容量
     */
    @SuppressWarnings("unchecked")
    public DynamicArray(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative: " + initialCapacity);
        }
        capacity = initialCapacity > 0 ? initialCapacity : DEFAULT_CAPACITY;
        data = (E[]) new Object[capacity];
        size = 0;
    }
    
    /**
     * 添加元素到末尾
     * @param element 要添加的元素
     */
    public void add(E element) {
        add(size, element);
    }
    
    /**
     * 在指定位置插入元素
     * @param index 插入位置
     * @param element 要插入的元素
     * @throws IndexOutOfBoundsException 如果索引超出范围
     */
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        // 检查是否需要扩容
        if (size >= capacity * LOAD_FACTOR) {
            resize(capacity * 2);
        }
        
        // 移动元素
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        
        data[index] = element;
        size++;
    }
    
    /**
     * 删除指定位置的元素
     * @param index 要删除的位置
     * @return 被删除的元素
     * @throws IndexOutOfBoundsException 如果索引超出范围
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        E element = data[index];
        
        // 移动元素
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        
        data[size - 1] = null; // 帮助GC
        size--;
        
        // 检查是否需要缩容
        if (size < capacity * SHRINK_FACTOR && capacity > DEFAULT_CAPACITY) {
            resize(Math.max(DEFAULT_CAPACITY, capacity / 2));
        }
        
        return element;
    }
    
    /**
     * 删除指定元素
     * @param element 要删除的元素
     * @return 是否删除成功
     */
    public boolean remove(E element) {
        int index = indexOf(element);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }
    
    /**
     * 获取指定位置的元素
     * @param index 索引位置
     * @return 元素值
     * @throws IndexOutOfBoundsException 如果索引超出范围
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return data[index];
    }
    
    /**
     * 设置指定位置的元素
     * @param index 索引位置
     * @param element 新的元素
     * @return 原来的元素
     * @throws IndexOutOfBoundsException 如果索引超出范围
     */
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        E oldElement = data[index];
        data[index] = element;
        return oldElement;
    }
    
    /**
     * 查找元素第一次出现的位置
     * @param element 要查找的元素
     * @return 索引位置，如果不存在返回-1
     */
    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (data[i] != null && data[i].equals(element)) {
                return i;
            } else if (data[i] == null && element == null) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * 查找元素最后一次出现的位置
     * @param element 要查找的元素
     * @return 索引位置，如果不存在返回-1
     */
    public int lastIndexOf(E element) {
        for (int i = size - 1; i >= 0; i--) {
            if (data[i] != null && data[i].equals(element)) {
                return i;
            } else if (data[i] == null && element == null) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * 判断是否包含指定元素
     * @param element 要查找的元素
     * @return 是否包含
     */
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }
    
    /**
     * 获取数组大小
     * @return 数组大小
     */
    public int size() {
        return size;
    }
    
    /**
     * 获取数组容量
     * @return 数组容量
     */
    public int capacity() {
        return capacity;
    }
    
    /**
     * 判断数组是否为空
     * @return 是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * 清空数组
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }
    
    /**
     * 转换为数组
     * @return 数组
     */
    @SuppressWarnings("unchecked")
    public E[] toArray() {
        E[] array = (E[]) new Object[size];
        System.arraycopy(data, 0, array, 0, size);
        return array;
    }
    
    /**
     * 调整数组容量
     * @param newCapacity 新容量
     */
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
        capacity = newCapacity;
    }
    
    /**
     * 打印数组
     */
    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}

