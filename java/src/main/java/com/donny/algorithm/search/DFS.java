package com.donny.algorithm.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 深度优先搜索（DFS）算法
 * 用于图的遍历和路径搜索
 */
public class DFS {
    
    /**
     * 图的DFS遍历（递归实现）
     * @param graph 邻接表表示的图
     * @param start 起始节点
     * @return 遍历结果
     */
    public static List<Integer> dfsRecursive(List<List<Integer>> graph, int start) {
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[graph.size()];
        dfsHelper(graph, start, visited, result);
        return result;
    }
    
    private static void dfsHelper(List<List<Integer>> graph, int node, 
                                  boolean[] visited, List<Integer> result) {
        visited[node] = true;
        result.add(node);
        
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfsHelper(graph, neighbor, visited, result);
            }
        }
    }
    
    /**
     * 图的DFS遍历（迭代实现）
     * @param graph 邻接表表示的图
     * @param start 起始节点
     * @return 遍历结果
     */
    public static List<Integer> dfsIterative(List<List<Integer>> graph, int start) {
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[graph.size()];
        Stack<Integer> stack = new Stack<>();
        
        stack.push(start);
        
        while (!stack.isEmpty()) {
            int node = stack.pop();
            
            if (!visited[node]) {
                visited[node] = true;
                result.add(node);
                
                // 将邻居节点逆序入栈（保证遍历顺序）
                for (int i = graph.get(node).size() - 1; i >= 0; i--) {
                    int neighbor = graph.get(node).get(i);
                    if (!visited[neighbor]) {
                        stack.push(neighbor);
                    }
                }
            }
        }
        
        return result;
    }
}

