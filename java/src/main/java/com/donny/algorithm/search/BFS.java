package com.donny.algorithm.search;

import java.util.*;

/**
 * 广度优先搜索（BFS）算法
 * 用于图的遍历和最短路径搜索
 */
public class BFS {
    
    /**
     * 图的BFS遍历
     * @param graph 邻接表表示的图
     * @param start 起始节点
     * @return 遍历结果
     */
    public static List<Integer> bfs(List<List<Integer>> graph, int start) {
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> queue = new LinkedList<>();
        
        visited[start] = true;
        queue.offer(start);
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
        
        return result;
    }
    
    /**
     * 使用BFS查找最短路径（无权图）
     * @param graph 邻接表表示的图
     * @param start 起始节点
     * @param end 目标节点
     * @return 最短路径，如果不存在返回null
     */
    public static List<Integer> shortestPath(List<List<Integer>> graph, int start, int end) {
        if (start == end) {
            return Arrays.asList(start);
        }
        
        boolean[] visited = new boolean[graph.size()];
        int[] parent = new int[graph.size()];
        Arrays.fill(parent, -1);
        Queue<Integer> queue = new LinkedList<>();
        
        visited[start] = true;
        queue.offer(start);
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            
            if (node == end) {
                // 重构路径
                List<Integer> path = new ArrayList<>();
                int current = end;
                while (current != -1) {
                    path.add(current);
                    current = parent[current];
                }
                Collections.reverse(path);
                return path;
            }
            
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    parent[neighbor] = node;
                    queue.offer(neighbor);
                }
            }
        }
        
        return null; // 路径不存在
    }
}

