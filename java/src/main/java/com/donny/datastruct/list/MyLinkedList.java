package com.donny.datastruct.list;

/**
 * 单链表实现
 * 提供基本的链表操作：添加、删除、查找、遍历等
 */
public class MyLinkedList<E> {
    
    /**
     * 链表节点
     */
    private static class Node<E> {
        E data;
        Node<E> next;
        
        Node(E data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private Node<E> head;
    private int size;
    
    /**
     * 构造函数
     */
    public MyLinkedList() {
        head = null;
        size = 0;
    }
    
    /**
     * 在链表头部添加元素
     * @param data 要添加的数据
     */
    public void addFirst(E data) {
        Node<E> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
        size++;
    }
    
    /**
     * 在链表尾部添加元素
     * @param data 要添加的数据
     */
    public void addLast(E data) {
        Node<E> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }
    
    /**
     * 在指定位置插入元素
     * @param index 插入位置
     * @param data 要插入的数据
     * @throws IndexOutOfBoundsException 如果索引超出范围
     */
    public void add(int index, E data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        if (index == 0) {
            addFirst(data);
            return;
        }
        
        Node<E> newNode = new Node<>(data);
        Node<E> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }
    
    /**
     * 删除第一个元素
     * @return 被删除的元素
     * @throws IllegalStateException 如果链表为空
     */
    public E removeFirst() {
        if (head == null) {
            throw new IllegalStateException("LinkedList is empty");
        }
        E data = head.data;
        head = head.next;
        size--;
        return data;
    }
    
    /**
     * 删除最后一个元素
     * @return 被删除的元素
     * @throws IllegalStateException 如果链表为空
     */
    public E removeLast() {
        if (head == null) {
            throw new IllegalStateException("LinkedList is empty");
        }
        
        if (head.next == null) {
            E data = head.data;
            head = null;
            size--;
            return data;
        }
        
        Node<E> current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        E data = current.next.data;
        current.next = null;
        size--;
        return data;
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
        
        if (index == 0) {
            return removeFirst();
        }
        
        Node<E> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        E data = current.next.data;
        current.next = current.next.next;
        size--;
        return data;
    }
    
    /**
     * 删除指定元素
     * @param data 要删除的数据
     * @return 是否删除成功
     */
    public boolean remove(E data) {
        if (head == null) {
            return false;
        }
        
        if (head.data.equals(data)) {
            head = head.next;
            size--;
            return true;
        }
        
        Node<E> current = head;
        while (current.next != null) {
            if (current.next.data.equals(data)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
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
        
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }
    
    /**
     * 设置指定位置的元素
     * @param index 索引位置
     * @param data 新的数据
     * @return 原来的数据
     * @throws IndexOutOfBoundsException 如果索引超出范围
     */
    public E set(int index, E data) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        E oldData = current.data;
        current.data = data;
        return oldData;
    }
    
    /**
     * 查找元素是否存在
     * @param data 要查找的数据
     * @return 是否存在
     */
    public boolean contains(E data) {
        Node<E> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    /**
     * 获取链表大小
     * @return 链表大小
     */
    public int size() {
        return size;
    }
    
    /**
     * 判断链表是否为空
     * @return 是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * 清空链表
     */
    public void clear() {
        head = null;
        size = 0;
    }
    
    /**
     * 反转链表
     */
    public void reverse() {
        Node<E> prev = null;
        Node<E> current = head;
        Node<E> next = null;
        
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }
    
    /**
     * 转换为数组
     * @return 数组
     */
    @SuppressWarnings("unchecked")
    public E[] toArray() {
        E[] array = (E[]) new Object[size];
        Node<E> current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        return array;
    }
    
    /**
     * 打印链表
     */
    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}

