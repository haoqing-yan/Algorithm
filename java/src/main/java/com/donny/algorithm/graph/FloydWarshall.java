package com.donny.algorithm.graph;

/**
 * Floyd-Warshall 全源最短路径算法
 */
public class FloydWarshall {
	/**
	 * 计算所有点对的最短路径
	 * @param dist 距离矩阵，dist[i][j] 为 i->j 的初始权重，不连通用 INF 表示（如 Integer.MAX_VALUE/4）
	 * @return 最短路径矩阵
	 */
	public static int[][] allPairsShortestPaths(int[][] dist) {
		int n = dist.length;
		int[][] d = new int[n][n];
		for (int i = 0; i < n; i++) {
			System.arraycopy(dist[i], 0, d[i], 0, n);
		}
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (d[i][k] + d[k][j] < d[i][j]) {
						d[i][j] = d[i][k] + d[k][j];
					}
				}
			}
		}
		return d;
	}
}
