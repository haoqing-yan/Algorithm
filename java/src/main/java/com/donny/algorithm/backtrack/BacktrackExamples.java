package com.donny.algorithm.backtrack;

import java.util.*;

/**
 * 回溯算法示例
 */
public class BacktrackExamples {
    
    /**
     * 全排列
     * 时间复杂度：O(n! * n)
     * 空间复杂度：O(n)
     * @param nums 数组
     * @return 所有排列
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrackPermute(nums, path, used, result);
        return result;
    }
    
    private static void backtrackPermute(int[] nums, List<Integer> path, 
                                        boolean[] used, List<List<Integer>> result) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            backtrackPermute(nums, path, used, result);
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
    
    /**
     * 组合
     * 从n个数中选择k个数的所有组合
     * 时间复杂度：O(C(n,k))
     * @param n 总数
     * @param k 选择数
     * @return 所有组合
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtrackCombine(n, k, 1, path, result);
        return result;
    }
    
    private static void backtrackCombine(int n, int k, int start, 
                                        List<Integer> path, List<List<Integer>> result) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        
        for (int i = start; i <= n; i++) {
            path.add(i);
            backtrackCombine(n, k, i + 1, path, result);
            path.remove(path.size() - 1);
        }
    }
    
    /**
     * N皇后问题
     * 在N×N的棋盘上放置N个皇后，使得它们不能相互攻击
     * 时间复杂度：O(N!)
     * @param n 棋盘大小
     * @return 所有解决方案
     */
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        int[] queens = new int[n]; // queens[i]表示第i行皇后所在的列
        Arrays.fill(queens, -1);
        backtrackNQueens(queens, 0, result);
        return result;
    }
    
    private static void backtrackNQueens(int[] queens, int row, List<List<String>> result) {
        int n = queens.length;
        if (row == n) {
            result.add(generateBoard(queens));
            return;
        }
        
        for (int col = 0; col < n; col++) {
            if (isValid(queens, row, col)) {
                queens[row] = col;
                backtrackNQueens(queens, row + 1, result);
                queens[row] = -1;
            }
        }
    }
    
    private static boolean isValid(int[] queens, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (queens[i] == col || 
                queens[i] - i == col - row || 
                queens[i] + i == col + row) {
                return false;
            }
        }
        return true;
    }
    
    private static List<String> generateBoard(int[] queens) {
        int n = queens.length;
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
    
    /**
     * 子集生成
     * 生成数组的所有子集
     * 时间复杂度：O(2^n)
     * @param nums 数组
     * @return 所有子集
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtrackSubsets(nums, 0, path, result);
        return result;
    }
    
    private static void backtrackSubsets(int[] nums, int start, 
                                        List<Integer> path, List<List<Integer>> result) {
        result.add(new ArrayList<>(path));
        
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            backtrackSubsets(nums, i + 1, path, result);
            path.remove(path.size() - 1);
        }
    }
}

