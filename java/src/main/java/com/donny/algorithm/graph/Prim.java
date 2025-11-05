package com.donny.algorithm.graph;

import java.util.*;

/**
 * Prim 最小生成树算法（无向连通图）
 */
public class Prim {
	public static class Edge {
		public final int to;
		public final int w;
		public Edge(int to, int w) { this.to = to; this.w = w; }
	}

	public static int mstWeight(int n, List<List<Edge>> graph) {
		boolean[] inMST = new boolean[n];
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
		pq.offer(new int[]{0, 0}); // {node, weight}
		int total = 0; int count = 0;
		while (!pq.isEmpty() && count < n) {
			int[] cur = pq.poll();
			int u = cur[0], w = cur[1];
			if (inMST[u]) continue;
			inMST[u] = true; count++;
			total += w;
			for (Edge e : graph.get(u)) {
				if (!inMST[e.to]) pq.offer(new int[]{e.to, e.w});
			}
		}
		return count == n ? total : -1; // -1表示图不连通
	}
}
