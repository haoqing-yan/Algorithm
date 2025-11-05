package com.donny.datastruct.graph;

/**
 * 并查集（Disjoint Set Union / Union-Find）
 * 支持按秩合并与路径压缩
 */
public class UnionFind {
	private final int[] parent;
	private final int[] rank;

	public UnionFind(int n) {
		this.parent = new int[n];
		this.rank = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			rank[i] = 0;
		}
	}

	public int find(int x) {
		if (parent[x] != x) {
			parent[x] = find(parent[x]); // 路径压缩
		}
		return parent[x];
	}

	public boolean union(int x, int y) {
		int rx = find(x);
		int ry = find(y);
		if (rx == ry) return false;
		if (rank[rx] < rank[ry]) {
			parent[rx] = ry;
		} else if (rank[rx] > rank[ry]) {
			parent[ry] = rx;
		} else {
			parent[ry] = rx;
			rank[rx]++;
		}
		return true;
	}
}
