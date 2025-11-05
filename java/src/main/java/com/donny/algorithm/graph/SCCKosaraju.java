package com.donny.algorithm.graph;

import java.util.*;

/**
 * 强连通分量（Kosaraju算法）
 */
public class SCCKosaraju {
	public static List<List<Integer>> scc(int n, List<List<Integer>> graph) {
		boolean[] visited = new boolean[n];
		Deque<Integer> order = new ArrayDeque<>();
		for (int i = 0; i < n; i++) if (!visited[i]) dfs1(graph, i, visited, order);
		// 构建转置图
		List<List<Integer>> rg = new ArrayList<>();
		for (int i = 0; i < n; i++) rg.add(new ArrayList<>());
		for (int u = 0; u < n; u++) for (int v : graph.get(u)) rg.get(v).add(u);
		Arrays.fill(visited, false);
		List<List<Integer>> comps = new ArrayList<>();
		while (!order.isEmpty()) {
			int v = order.pop();
			if (!visited[v]) {
				List<Integer> comp = new ArrayList<>();
				dfs2(rg, v, visited, comp);
				comps.add(comp);
			}
		}
		return comps;
	}
	private static void dfs1(List<List<Integer>> g, int u, boolean[] vis, Deque<Integer> st) {
		vis[u] = true;
		for (int v : g.get(u)) if (!vis[v]) dfs1(g, v, vis, st);
		st.push(u);
	}
	private static void dfs2(List<List<Integer>> g, int u, boolean[] vis, List<Integer> comp) {
		vis[u] = true; comp.add(u);
		for (int v : g.get(u)) if (!vis[v]) dfs2(g, v, vis, comp);
	}
}
