package com.donny.datastruct.stack;

/**
 * 基于数组实现的栈
 * 栈是一种后进先出（LIFO）的数据结构
 */
public class ArrayStack<E> {
    private static final int DEFAULT_CAPACITY = 10;
    
    private E[] data;
    private int top;
    private int capacity;
    
    /**
     * 构造函数
     */
    @SuppressWarnings("unchecked")
    public ArrayStack() {
        capacity = DEFAULT_CAPACITY;
        data = (E[]) new Object[capacity];
        top = -1;
    }
    
    /**
     * 指定容量的构造函数
     * @param initialCapacity 初始容量
     */
    @SuppressWarnings("unchecked")
    public ArrayStack(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative: " + initialCapacity);
        }
        capacity = initialCapacity > 0 ? initialCapacity : DEFAULT_CAPACITY;
        data = (E[]) new Object[capacity];
        top = -1;
    }
    
    /**
     * 入栈操作
     * @param element 要入栈的元素
     */
    public void push(E element) {
        if (top == capacity - 1) {
            resize(capacity * 2);
        }
        data[++top] = element;
    }
    
    /**
     * 出栈操作
     * @return 栈顶元素
     * @throws IllegalStateException 如果栈为空
     */
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        E element = data[top];
        data[top--] = null; // 帮助GC
        return element;
    }
    
    /**
     * 查看栈顶元素（不出栈）
     * @return 栈顶元素
     * @throws IllegalStateException 如果栈为空
     */
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return data[top];
    }
    
    /**
     * 判断栈是否为空
     * @return 是否为空
     */
    public boolean isEmpty() {
        return top == -1;
    }
    
    /**
     * 获取栈的大小
     * @return 栈的大小
     */
    public int size() {
        return top + 1;
    }
    
    /**
     * 清空栈
     */
    public void clear() {
        for (int i = 0; i <= top; i++) {
            data[i] = null;
        }
        top = -1;
    }
    
    /**
     * 调整栈容量
     * @param newCapacity 新容量
     */
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, top + 1);
        data = newData;
        capacity = newCapacity;
    }
    
    /**
     * 打印栈
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i <= top; i++) {
            sb.append(data[i]);
            if (i < top) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}

