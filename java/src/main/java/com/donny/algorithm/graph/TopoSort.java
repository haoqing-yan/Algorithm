package com.donny.algorithm.graph;

import java.util.*;

/**
 * 拓扑排序（Kahn算法）- 仅适用于有向无环图（DAG）
 */
public class TopoSort {
	public static List<Integer> sort(int n, List<List<Integer>> graph) {
		int[] indeg = new int[n];
		for (int u = 0; u < n; u++) {
			for (int v : graph.get(u)) indeg[v]++;
		}
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < n; i++) if (indeg[i] == 0) q.offer(i);
		List<Integer> order = new ArrayList<>();
		while (!q.isEmpty()) {
			int u = q.poll();
			order.add(u);
			for (int v : graph.get(u)) if (--indeg[v] == 0) q.offer(v);
		}
		return order.size() == n ? order : Collections.emptyList(); // 空表示有环
	}
}
