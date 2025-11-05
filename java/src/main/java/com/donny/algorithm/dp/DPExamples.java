package com.donny.algorithm.dp;

/**
 * 动态规划算法示例
 */
public class DPExamples {
    
    /**
     * 斐波那契数列（动态规划）
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public static long fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        
        long prev2 = 0;
        long prev1 = 1;
        
        for (int i = 2; i <= n; i++) {
            long current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
    
    /**
     * 0-1背包问题
     * 时间复杂度：O(n * W)
     * 空间复杂度：O(W)
     * @param weights 物品重量数组
     * @param values 物品价值数组
     * @param capacity 背包容量
     * @return 最大价值
     */
    public static int knapsack01(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[] dp = new int[capacity + 1];
        
        for (int i = 0; i < n; i++) {
            for (int w = capacity; w >= weights[i]; w--) {
                dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
            }
        }
        
        return dp[capacity];
    }
    
    /**
     * 最长公共子序列（LCS）
     * 时间复杂度：O(m * n)
     * 空间复杂度：O(m * n)
     * @param s1 字符串1
     * @param s2 字符串2
     * @return LCS的长度
     */
    public static int longestCommonSubsequence(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[m][n];
    }
    
    /**
     * 最长递增子序列（LIS）
     * 时间复杂度：O(n²)
     * 空间复杂度：O(n)
     * @param nums 数组
     * @return LIS的长度
     */
    public static int longestIncreasingSubsequence(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int maxLen = 1;
        
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        
        return maxLen;
    }
    
    /**
     * 爬楼梯问题
     * 每次可以爬1或2个台阶，求到达第n个台阶的方法数
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public static int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        
        int prev2 = 1;
        int prev1 = 2;
        
        for (int i = 3; i <= n; i++) {
            int current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
    
    /**
     * 编辑距离（Levenshtein距离）
     * 时间复杂度：O(m * n)
     * 空间复杂度：O(m * n)
     * @param word1 单词1
     * @param word2 单词2
     * @return 编辑距离
     */
    public static int editDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        // 初始化
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j], dp[i][j - 1]),
                        dp[i - 1][j - 1]
                    ) + 1;
                }
            }
        }
        
        return dp[m][n];
    }
}

