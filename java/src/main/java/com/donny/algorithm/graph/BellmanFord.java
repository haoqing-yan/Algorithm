package com.donny.algorithm.graph;

import java.util.List;

/**
 * Bellman-Ford 最短路径算法（可处理负权边，检测负环）
 */
public class BellmanFord {
	public static class Edge {
		public final int u;
		public final int v;
		public final int w;
		public Edge(int u, int v, int w) {
			this.u = u; this.v = v; this.w = w;
		}
	}

	public static class Result {
		public final int[] dist;
		public final boolean hasNegativeCycle;
		public Result(int[] dist, boolean hasNegativeCycle) {
			this.dist = dist;
			this.hasNegativeCycle = hasNegativeCycle;
		}
	}

	/**
	 * 计算单源最短路并检测负环
	 * @param n 顶点数
	 * @param edges 边列表
	 * @param src 源点
	 */
	public static Result shortestPaths(int n, List<Edge> edges, int src) {
		int INF = Integer.MAX_VALUE / 4;
		int[] dist = new int[n];
		for (int i = 0; i < n; i++) dist[i] = INF;
		dist[src] = 0;

		// 松弛 n-1 轮
		for (int i = 0; i < n - 1; i++) {
			boolean updated = false;
			for (Edge e : edges) {
				if (dist[e.u] != INF && dist[e.u] + e.w < dist[e.v]) {
					dist[e.v] = dist[e.u] + e.w;
					updated = true;
				}
			}
			if (!updated) break;
		}

		// 检测负环
		boolean hasNegCycle = false;
		for (Edge e : edges) {
			if (dist[e.u] != INF && dist[e.u] + e.w < dist[e.v]) {
				hasNegCycle = true; break;
			}
		}
		return new Result(dist, hasNegCycle);
	}
}
