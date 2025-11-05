package com.donny.algorithm.graph;

import java.util.*;

/**
 * Dijkstra 最短路径算法（非负权图）
 */
public class Dijkstra {
	public static class Edge {
		public final int to;
		public final int weight;
		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	/**
	 * 计算从源点到各点的最短距离
	 * @param n 顶点数 [0..n-1]
	 * @param graph 邻接表（带权）
	 * @param src 源点
	 * @return 距离数组，无法到达为 Integer.MAX_VALUE
	 */
	public static int[] shortestPaths(int n, List<List<Edge>> graph, int src) {
		int[] dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src] = 0;

		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
		pq.offer(new int[]{src, 0});
		boolean[] visited = new boolean[n];

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int u = cur[0];
			if (visited[u]) continue;
			visited[u] = true;

			for (Edge e : graph.get(u)) {
				if (dist[u] != Integer.MAX_VALUE && dist[u] + e.weight < dist[e.to]) {
					dist[e.to] = dist[u] + e.weight;
					pq.offer(new int[]{e.to, dist[e.to]});
				}
			}
		}
		return dist;
	}
}
