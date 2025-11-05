package com.donny.datastruct.graph;

import java.util.*;

/**
 * 图数据结构实现（邻接表表示）
 * 支持有向图和无向图
 */
public class Graph {
    
    private final int vertices;
    private final List<List<Integer>> adjList;
    private final boolean directed;
    
    /**
     * 构造函数
     * @param vertices 顶点数
     * @param directed 是否为有向图
     */
    public Graph(int vertices, boolean directed) {
        this.vertices = vertices;
        this.directed = directed;
        this.adjList = new ArrayList<>();
        
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }
    
    /**
     * 添加边
     * @param from 起始顶点
     * @param to 目标顶点
     */
    public void addEdge(int from, int to) {
        if (from < 0 || from >= vertices || to < 0 || to >= vertices) {
            throw new IllegalArgumentException("Invalid vertex index");
        }
        
        adjList.get(from).add(to);
        
        // 如果是无向图，添加反向边
        if (!directed) {
            adjList.get(to).add(from);
        }
    }
    
    /**
     * 删除边
     * @param from 起始顶点
     * @param to 目标顶点
     */
    public void removeEdge(int from, int to) {
        if (from < 0 || from >= vertices || to < 0 || to >= vertices) {
            throw new IllegalArgumentException("Invalid vertex index");
        }
        
        adjList.get(from).removeIf(v -> v == to);
        
        if (!directed) {
            adjList.get(to).removeIf(v -> v == from);
        }
    }
    
    /**
     * 深度优先搜索（DFS）
     * @param start 起始顶点
     * @return 遍历结果
     */
    public List<Integer> dfs(int start) {
        if (start < 0 || start >= vertices) {
            throw new IllegalArgumentException("Invalid start vertex");
        }
        
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[vertices];
        dfsHelper(start, visited, result);
        return result;
    }
    
    private void dfsHelper(int vertex, boolean[] visited, List<Integer> result) {
        visited[vertex] = true;
        result.add(vertex);
        
        for (int neighbor : adjList.get(vertex)) {
            if (!visited[neighbor]) {
                dfsHelper(neighbor, visited, result);
            }
        }
    }
    
    /**
     * 广度优先搜索（BFS）
     * @param start 起始顶点
     * @return 遍历结果
     */
    public List<Integer> bfs(int start) {
        if (start < 0 || start >= vertices) {
            throw new IllegalArgumentException("Invalid start vertex");
        }
        
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();
        
        visited[start] = true;
        queue.offer(start);
        
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            result.add(vertex);
            
            for (int neighbor : adjList.get(vertex)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
        
        return result;
    }
    
    /**
     * 获取邻接列表
     * @return 邻接列表
     */
    public List<List<Integer>> getAdjList() {
        return adjList;
    }
    
    /**
     * 获取顶点数
     * @return 顶点数
     */
    public int getVertices() {
        return vertices;
    }
    
    /**
     * 判断是否为有向图
     * @return 是否为有向图
     */
    public boolean isDirected() {
        return directed;
    }
}

