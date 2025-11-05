package com.donny.datastruct;

import com.donny.datastruct.array.DynamicArray;
import com.donny.datastruct.list.MyLinkedList;
import com.donny.datastruct.queue.ArrayQueue;
import com.donny.datastruct.stack.ArrayStack;

/**
 * 数据结构测试类
 */
public class DataStructureTest {
    
    public static void main(String[] args) {
        System.out.println("========== 数据结构测试 ==========");
        
        testLinkedList();
        testDynamicArray();
        testStack();
        testQueue();
        
        System.out.println("========== 测试完成 ==========");
    }
    
    /**
     * 测试链表
     */
    private static void testLinkedList() {
        System.out.println("\n--- 测试链表 ---");
        MyLinkedList<Integer> list = new MyLinkedList<>();
        
        // 添加元素
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        System.out.println("添加元素后: " + list);
        
        // 在头部添加
        list.addFirst(0);
        System.out.println("头部添加后: " + list);
        
        // 在指定位置插入
        list.add(2, 99);
        System.out.println("位置2插入99后: " + list);
        
        // 获取元素
        System.out.println("索引1的元素: " + list.get(1));
        
        // 反转链表
        list.reverse();
        System.out.println("反转后: " + list);
        
        // 删除元素
        list.remove(2);
        System.out.println("删除索引2后: " + list);
        
        System.out.println("链表大小: " + list.size());
    }
    
    /**
     * 测试动态数组
     */
    private static void testDynamicArray() {
        System.out.println("\n--- 测试动态数组 ---");
        DynamicArray<Integer> array = new DynamicArray<>(5);
        
        // 添加元素
        for (int i = 0; i < 10; i++) {
            array.add(i);
        }
        System.out.println("添加10个元素后: " + array);
        System.out.println("数组大小: " + array.size());
        System.out.println("数组容量: " + array.capacity());
        
        // 在指定位置插入
        array.add(5, 99);
        System.out.println("位置5插入99后: " + array);
        
        // 删除元素
        array.remove(5);
        System.out.println("删除位置5后: " + array);
        
        // 查找元素
        System.out.println("查找元素5的索引: " + array.indexOf(5));
        System.out.println("是否包含元素99: " + array.contains(99));
    }
    
    /**
     * 测试栈
     */
    private static void testStack() {
        System.out.println("\n--- 测试栈 ---");
        ArrayStack<Integer> stack = new ArrayStack<>();
        
        // 入栈
        for (int i = 1; i <= 5; i++) {
            stack.push(i);
        }
        System.out.println("入栈5个元素后: " + stack);
        
        // 查看栈顶
        System.out.println("栈顶元素: " + stack.peek());
        
        // 出栈
        System.out.print("出栈顺序: ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }
    
    /**
     * 测试队列
     */
    private static void testQueue() {
        System.out.println("\n--- 测试队列 ---");
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        
        // 入队
        for (int i = 1; i <= 5; i++) {
            queue.enqueue(i);
        }
        System.out.println("入队5个元素后: " + queue);
        
        // 查看队首
        System.out.println("队首元素: " + queue.peek());
        
        // 出队
        System.out.print("出队顺序: ");
        while (!queue.isEmpty()) {
            System.out.print(queue.dequeue() + " ");
        }
        System.out.println();
    }
}

