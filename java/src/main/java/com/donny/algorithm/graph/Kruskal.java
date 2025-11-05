package com.donny.algorithm.graph;

import com.donny.datastruct.graph.UnionFind;
import java.util.*;

/**
 * Kruskal 最小生成树算法（无向连通图）
 */
public class Kruskal {
	public static class Edge {
		public final int u, v, w;
		public Edge(int u, int v, int w) { this.u = u; this.v = v; this.w = w; }
	}

	public static List<Edge> mst(int n, List<Edge> edges) {
		List<Edge> result = new ArrayList<>();
		edges.sort(Comparator.comparingInt(e -> e.w));
		UnionFind uf = new UnionFind(n);
		for (Edge e : edges) {
			if (uf.union(e.u, e.v)) {
				result.add(e);
				if (result.size() == n - 1) break;
			}
		}
		return result;
	}
}
