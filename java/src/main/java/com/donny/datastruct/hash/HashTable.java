package com.donny.datastruct.hash;

import java.util.LinkedList;

/**
 * 哈希表实现（使用链地址法解决冲突）
 */
public class HashTable<K, V> {
    
    /**
     * 键值对节点
     */
    private static class Entry<K, V> {
        K key;
        V value;
        
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    
    private LinkedList<Entry<K, V>>[] buckets;
    private int size;
    private int capacity;
    
    /**
     * 构造函数
     */
    @SuppressWarnings("unchecked")
    public HashTable() {
        capacity = DEFAULT_CAPACITY;
        buckets = new LinkedList[capacity];
        size = 0;
    }
    
    /**
     * 构造函数，指定初始容量
     */
    @SuppressWarnings("unchecked")
    public HashTable(int initialCapacity) {
        capacity = initialCapacity > 0 ? initialCapacity : DEFAULT_CAPACITY;
        buckets = new LinkedList[capacity];
        size = 0;
    }
    
    /**
     * 插入键值对
     * @param key 键
     * @param value 值
     */
    public void put(K key, V value) {
        if (size >= capacity * LOAD_FACTOR) {
            resize();
        }
        
        int index = hash(key);
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }
        
        // 检查是否已存在该键
        for (Entry<K, V> entry : buckets[index]) {
            if (entry.key.equals(key)) {
                entry.value = value; // 更新值
                return;
            }
        }
        
        // 添加新键值对
        buckets[index].add(new Entry<>(key, value));
        size++;
    }
    
    /**
     * 获取值
     * @param key 键
     * @return 值，如果不存在返回null
     */
    public V get(K key) {
        int index = hash(key);
        if (buckets[index] == null) {
            return null;
        }
        
        for (Entry<K, V> entry : buckets[index]) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        
        return null;
    }
    
    /**
     * 删除键值对
     * @param key 键
     * @return 被删除的值，如果不存在返回null
     */
    public V remove(K key) {
        int index = hash(key);
        if (buckets[index] == null) {
            return null;
        }
        
        for (Entry<K, V> entry : buckets[index]) {
            if (entry.key.equals(key)) {
                buckets[index].remove(entry);
                size--;
                return entry.value;
            }
        }
        
        return null;
    }
    
    /**
     * 判断是否包含键
     * @param key 键
     * @return 是否包含
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }
    
    /**
     * 获取哈希表大小
     * @return 大小
     */
    public int size() {
        return size;
    }
    
    /**
     * 判断哈希表是否为空
     * @return 是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * 清空哈希表
     */
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            if (buckets[i] != null) {
                buckets[i].clear();
            }
        }
        size = 0;
    }
    
    /**
     * 计算哈希值
     */
    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }
    
    /**
     * 扩容
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        int oldCapacity = capacity;
        capacity *= 2;
        LinkedList<Entry<K, V>>[] oldBuckets = buckets;
        buckets = new LinkedList[capacity];
        size = 0;
        
        // 重新插入所有元素
        for (int i = 0; i < oldCapacity; i++) {
            if (oldBuckets[i] != null) {
                for (Entry<K, V> entry : oldBuckets[i]) {
                    put(entry.key, entry.value);
                }
            }
        }
    }
}

