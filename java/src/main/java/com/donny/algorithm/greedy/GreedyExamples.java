package com.donny.algorithm.greedy;

import java.util.*;

/**
 * 贪心算法示例
 */
public class GreedyExamples {
    
    /**
     * 活动选择问题
     * 选择最多的不重叠活动
     * 时间复杂度：O(n log n)
     * @param activities 活动列表，每个活动包含[start, end]
     * @return 选中的活动索引列表
     */
    public static List<Integer> activitySelection(int[][] activities) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < activities.length; i++) {
            list.add(new int[]{activities[i][0], activities[i][1], i});
        }
        
        // 按结束时间排序
        list.sort(Comparator.comparingInt(a -> a[1]));
        
        List<Integer> result = new ArrayList<>();
        int lastEnd = -1;
        
        for (int[] activity : list) {
            if (activity[0] >= lastEnd) {
                result.add(activity[2]);
                lastEnd = activity[1];
            }
        }
        
        return result;
    }
    
    /**
     * 找零钱问题（贪心算法）
     * 注意：这只适用于某些货币系统（如人民币），不适用于所有情况
     * 时间复杂度：O(n)
     * @param amount 总金额
     * @param coins 硬币面额数组（降序）
     * @return 需要的硬币数量
     */
    public static int coinChangeGreedy(int amount, int[] coins) {
        int count = 0;
        
        for (int coin : coins) {
            if (amount >= coin) {
                count += amount / coin;
                amount %= coin;
            }
        }
        
        return amount == 0 ? count : -1; // -1表示无法找零
    }
    
    /**
     * 区间调度问题
     * 选择最多的不重叠区间
     * 时间复杂度：O(n log n)
     * @param intervals 区间数组，每个区间包含[start, end]
     * @return 选中的区间数量
     */
    public static int intervalScheduling(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        
        // 按结束时间排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        
        int count = 1;
        int lastEnd = intervals[0][1];
        
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= lastEnd) {
                count++;
                lastEnd = intervals[i][1];
            }
        }
        
        return count;
    }
    
    /**
     * 跳跃游戏（能否到达最后一个位置）
     * 时间复杂度：O(n)
     * @param nums 数组，nums[i]表示从位置i最多可以跳的步数
     * @return 是否能到达最后一个位置
     */
    public static boolean canJump(int[] nums) {
        int maxReach = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) {
                return false;
            }
            maxReach = Math.max(maxReach, i + nums[i]);
            if (maxReach >= nums.length - 1) {
                return true;
            }
        }
        
        return true;
    }
}

