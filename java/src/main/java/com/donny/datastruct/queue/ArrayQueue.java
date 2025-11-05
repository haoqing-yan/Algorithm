package com.donny.datastruct.queue;

/**
 * 基于数组实现的循环队列
 * 队列是一种先进先出（FIFO）的数据结构
 */
public class ArrayQueue<E> {
    private static final int DEFAULT_CAPACITY = 10;
    
    private E[] data;
    private int front;
    private int rear;
    private int size;
    private int capacity;
    
    /**
     * 构造函数
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue() {
        capacity = DEFAULT_CAPACITY;
        data = (E[]) new Object[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }
    
    /**
     * 指定容量的构造函数
     * @param initialCapacity 初始容量
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative: " + initialCapacity);
        }
        capacity = initialCapacity > 0 ? initialCapacity : DEFAULT_CAPACITY;
        data = (E[]) new Object[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }
    
    /**
     * 入队操作
     * @param element 要入队的元素
     */
    public void enqueue(E element) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        rear = (rear + 1) % capacity;
        data[rear] = element;
        size++;
    }
    
    /**
     * 出队操作
     * @return 队首元素
     * @throws IllegalStateException 如果队列为空
     */
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        E element = data[front];
        data[front] = null; // 帮助GC
        front = (front + 1) % capacity;
        size--;
        return element;
    }
    
    /**
     * 查看队首元素（不出队）
     * @return 队首元素
     * @throws IllegalStateException 如果队列为空
     */
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return data[front];
    }
    
    /**
     * 判断队列是否为空
     * @return 是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * 获取队列的大小
     * @return 队列的大小
     */
    public int size() {
        return size;
    }
    
    /**
     * 清空队列
     */
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            data[i] = null;
        }
        front = 0;
        rear = -1;
        size = 0;
    }
    
    /**
     * 调整队列容量
     * @param newCapacity 新容量
     */
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % capacity];
        }
        data = newData;
        front = 0;
        rear = size - 1;
        capacity = newCapacity;
    }
    
    /**
     * 打印队列
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            sb.append(data[index]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}

