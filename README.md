# 算法练习仓库 (Algorithm Practice)

> 这是一个用于算法和数据结构练习的仓库，包含各种经典算法实现、数据结构实现以及 LeetCode 等算法题目的解答。

## 📚 目录

- [项目简介](#项目简介)
- [目录结构](#目录结构)
- [数据结构](#数据结构)
- [算法](#算法)
- [练习计划](#练习计划)
- [刷题记录](#刷题记录)
- [学习资源](#学习资源)
- [如何开始](#如何开始)

## 🎯 项目简介

这个仓库主要用于：
- 📖 学习经典数据结构和算法
- 💻 实现常见算法和数据结构
- 🔍 练习 LeetCode、牛客网等平台的算法题目
- 📝 记录学习过程和心得体会
- 📊 总结算法模板和解题思路

## 📁 目录结构

```
Algorithm/
├── java/                    # Java 实现
│   ├── src/
│   │   ├── main/java/
│   │   │   └── com/donny/
│   │   │       ├── algorithm/      # 算法实现
│   │   │       │   ├── sort/        # 排序算法
│   │   │       │   ├── search/      # 搜索算法
│   │   │       │   ├── dp/          # 动态规划
│   │   │       │   ├── greedy/      # 贪心算法
│   │   │       │   ├── backtrack/   # 回溯算法
│   │   │       │   └── graph/       # 图算法
│   │   │       └── datastruct/       # 数据结构
│   │   │           ├── list/        # 链表
│   │   │           ├── array/       # 数组
│   │   │           ├── stack/       # 栈
│   │   │           ├── queue/       # 队列
│   │   │           ├── tree/        # 树
│   │   │           ├── graph/       # 图
│   │   │           └── hash/        # 哈希表
│   │   └── test/                    # 测试代码
│   └── pom.xml
├── python/                  # Python 实现（可选）
├── leetcode/               # LeetCode 题目解答
│   ├── easy/               # 简单题
│   ├── medium/             # 中等题
│   └── hard/               # 困难题
└── README.md
```

## 📊 数据结构

### 线性数据结构

- [ ] **数组 (Array)**
  - [ ] 动态数组实现
  - [ ] 数组常见操作
  - [ ] 数组相关算法题

- [ ] **链表 (Linked List)**
  - [x] 单链表实现
  - [ ] 双链表实现
  - [ ] 循环链表实现
  - [ ] 链表常见操作（反转、合并、删除等）

- [ ] **栈 (Stack)**
  - [ ] 数组实现栈
  - [ ] 链表实现栈
  - [ ] 栈的应用（表达式求值、括号匹配等）

- [ ] **队列 (Queue)**
  - [ ] 普通队列实现
  - [ ] 双端队列（Deque）
  - [ ] 优先队列（Priority Queue）
  - [ ] 循环队列

### 非线性数据结构

- [ ] **树 (Tree)**
  - [ ] 二叉树实现
  - [ ] 二叉搜索树（BST）
  - [ ] 平衡二叉树（AVL）
  - [ ] 红黑树
  - [ ] 堆（Heap）
  - [ ] 字典树（Trie）
  - [ ] 并查集（Union-Find）

- [ ] **图 (Graph)**
  - [ ] 图的表示（邻接表、邻接矩阵）
  - [ ] 图的遍历（DFS、BFS）
  - [ ] 最短路径算法
  - [ ] 最小生成树

### 其他数据结构

- [ ] **哈希表 (Hash Table)**
  - [ ] 哈希表实现
  - [ ] 哈希冲突处理
  - [ ] 一致性哈希

- [ ] **字符串 (String)**
  - [ ] KMP 算法
  - [ ] 字符串匹配算法
  - [ ] 字符串哈希

## 🔧 算法

### 排序算法

- [ ] **比较排序**
  - [ ] 冒泡排序（Bubble Sort）
  - [ ] 选择排序（Selection Sort）
  - [ ] 插入排序（Insertion Sort）
  - [ ] 希尔排序（Shell Sort）
  - [ ] 归并排序（Merge Sort）
  - [ ] 快速排序（Quick Sort）
  - [ ] 堆排序（Heap Sort）

- [ ] **非比较排序**
  - [ ] 计数排序（Counting Sort）
  - [ ] 桶排序（Bucket Sort）
  - [ ] 基数排序（Radix Sort）

### 搜索算法

- [ ] **基本搜索**
  - [ ] 线性搜索
  - [ ] 二分搜索
  - [ ] 三分搜索

- [ ] **图搜索**
  - [ ] 深度优先搜索（DFS）
  - [ ] 广度优先搜索（BFS）
  - [ ] A* 算法
  - [ ] 双向 BFS

### 动态规划 (DP)

- [ ] **基础 DP**
  - [ ] 背包问题（0-1背包、完全背包、多重背包）
  - [ ] 最长公共子序列（LCS）
  - [ ] 最长递增子序列（LIS）
  - [ ] 编辑距离
  - [ ] 股票买卖问题

- [ ] **区间 DP**
  - [ ] 矩阵链乘法
  - [ ] 石子合并问题

- [ ] **树形 DP**
  - [ ] 树的最大独立集
  - [ ] 树的重心

- [ ] **状态压缩 DP**
  - [ ] 旅行商问题（TSP）
  - [ ] 棋盘问题

### 贪心算法

- [ ] 活动选择问题
- [ ] 区间调度问题
- [ ] 霍夫曼编码
- [ ] 最小生成树（Prim、Kruskal）
- [ ] 最短路径（Dijkstra）

### 回溯算法

- [ ] N 皇后问题
- [ ] 全排列问题
- [ ] 组合问题
- [ ] 子集问题
- [ ] 数独求解
- [ ] 单词搜索

### 分治算法

- [ ] 归并排序
- [ ] 快速排序
- [ ] 大整数乘法
- [ ] 最近点对问题
- [ ] 逆序对问题

### 数学算法

- [ ] 最大公约数（GCD）
- [ ] 最小公倍数（LCM）
- [ ] 快速幂
- [ ] 素数筛选（埃拉托斯特尼筛法）
- [ ] 欧几里得算法
- [ ] 扩展欧几里得算法
- [ ] 同余方程

### 字符串算法

- [ ] KMP 算法
- [ ] Rabin-Karp 算法
- [ ] 字符串哈希
- [ ] Manacher 算法
- [ ] 后缀数组

### 图算法

- [ ] 最短路径
  - [ ] Dijkstra 算法
  - [ ] Bellman-Ford 算法
  - [ ] Floyd-Warshall 算法
- [ ] 最小生成树
  - [ ] Kruskal 算法
  - [ ] Prim 算法
- [ ] 拓扑排序
- [ ] 强连通分量（SCC）
- [ ] 网络流算法

## 📅 练习计划

### 第一周：基础数据结构
- [ ] 数组和链表的基本操作
- [ ] 实现栈和队列
- [ ] 完成 10 道 LeetCode 简单题

### 第二周：树和递归
- [ ] 实现二叉树
- [ ] 树的遍历（前序、中序、后序、层序）
- [ ] 完成 10 道树相关的题目

### 第三周：排序和搜索
- [ ] 实现常见排序算法
- [ ] 二分搜索及其变种
- [ ] 完成 10 道搜索相关题目

### 第四周：动态规划入门
- [ ] 学习 DP 基本思想
- [ ] 完成经典 DP 问题
- [ ] 完成 10 道 DP 入门题

### 第五周：图算法
- [ ] 图的表示和遍历
- [ ] 最短路径算法
- [ ] 完成 10 道图相关题目

### 第六周：高级算法
- [ ] 回溯算法
- [ ] 贪心算法
- [ ] 完成综合题目

## 📝 刷题记录

### LeetCode 题目分类

#### 数组 (Array)
- [ ] [两数之和](https://leetcode.cn/problems/two-sum/) - Easy
- [ ] [盛最多水的容器](https://leetcode.cn/problems/container-with-most-water/) - Medium
- [ ] [三数之和](https://leetcode.cn/problems/3sum/) - Medium
- [ ] [接雨水](https://leetcode.cn/problems/trapping-rain-water/) - Hard

#### 链表 (Linked List)
- [ ] [反转链表](https://leetcode.cn/problems/reverse-linked-list/) - Easy
- [ ] [合并两个有序链表](https://leetcode.cn/problems/merge-two-sorted-lists/) - Easy
- [ ] [环形链表](https://leetcode.cn/problems/linked-list-cycle/) - Easy
- [ ] [删除链表的倒数第 N 个结点](https://leetcode.cn/problems/remove-nth-node-from-end-of-list/) - Medium

#### 字符串 (String)
- [ ] [有效的括号](https://leetcode.cn/problems/valid-parentheses/) - Easy
- [ ] [最长回文子串](https://leetcode.cn/problems/longest-palindromic-substring/) - Medium
- [ ] [字符串转换整数](https://leetcode.cn/problems/string-to-integer-atoi/) - Medium

#### 树 (Tree)
- [ ] [二叉树的最大深度](https://leetcode.cn/problems/maximum-depth-of-binary-tree/) - Easy
- [ ] [对称二叉树](https://leetcode.cn/problems/symmetric-tree/) - Easy
- [ ] [二叉树的层序遍历](https://leetcode.cn/problems/binary-tree-level-order-traversal/) - Medium
- [ ] [从前序与中序遍历序列构造二叉树](https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/) - Medium

#### 动态规划 (DP)
- [ ] [爬楼梯](https://leetcode.cn/problems/climbing-stairs/) - Easy
- [ ] [买卖股票的最佳时机](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/) - Easy
- [ ] [最长递增子序列](https://leetcode.cn/problems/longest-increasing-subsequence/) - Medium
- [ ] [编辑距离](https://leetcode.cn/problems/edit-distance/) - Hard

#### 回溯 (Backtracking)
- [ ] [全排列](https://leetcode.cn/problems/permutations/) - Medium
- [ ] [组合总和](https://leetcode.cn/problems/combination-sum/) - Medium
- [ ] [N 皇后](https://leetcode.cn/problems/n-queens/) - Hard

#### 贪心 (Greedy)
- [ ] [跳跃游戏](https://leetcode.cn/problems/jump-game/) - Medium
- [ ] [分发糖果](https://leetcode.cn/problems/candy/) - Hard

#### 图 (Graph)
- [ ] [岛屿数量](https://leetcode.cn/problems/number-of-islands/) - Medium
- [ ] [课程表](https://leetcode.cn/problems/course-schedule/) - Medium

### 刷题统计
- **总题数**: 0
- **Easy**: 0
- **Medium**: 0
- **Hard**: 0
- **完成率**: 0%

## 📚 学习资源

### 推荐书籍
- 《算法导论》（Introduction to Algorithms）
- 《数据结构与算法分析》
- 《编程珠玑》（Programming Pearls）
- 《算法竞赛入门经典》

### 在线平台
- [LeetCode](https://leetcode.cn/) - 算法练习平台
- [牛客网](https://www.nowcoder.com/) - 算法题库
- [力扣](https://leetcode.cn/) - 中文算法平台
- [AcWing](https://www.acwing.com/) - 算法竞赛平台

### 视频教程
- [B站算法教程](https://www.bilibili.com/)
- [慕课网算法课程](https://www.imooc.com/)

### 算法可视化
- [VisuAlgo](https://visualgo.net/) - 算法可视化
- [Algorithm Visualizer](https://algorithm-visualizer.org/) - 算法动画演示

## 🚀 如何开始

### 1. 环境准备

#### Java 环境
```bash
# 确保已安装 JDK 8+
java -version

# 进入 Java 目录
cd java

# 使用 Maven 编译
mvn clean compile

# 运行测试
mvn test
```

#### Python 环境（可选）
```bash
# 确保已安装 Python 3.7+
python --version

# 创建虚拟环境
python -m venv venv
source venv/bin/activate  # Linux/Mac
# 或
venv\Scripts\activate  # Windows

# 安装依赖
pip install -r requirements.txt
```

### 2. 开始练习

1. **选择数据结构或算法**：从上面的清单中选择一个
2. **阅读相关文档**：了解算法原理和实现思路
3. **自己实现**：尝试独立实现，不要直接看答案
4. **编写测试**：确保实现的正确性
5. **优化改进**：思考如何优化时间和空间复杂度
6. **记录总结**：记录学习心得和常见套路

### 3. 提交代码

```bash
# 添加文件
git add .

# 提交更改
git commit -m "feat: 实现快速排序算法"

# 推送到远程仓库
git push origin main
```

## 📈 学习进度

### 已完成
- [x] 单链表实现
- [x] Paillier 同态加密算法

### 进行中
- [ ] 排序算法实现

### 待完成
- [ ] 动态规划系列
- [ ] 图算法系列

## 💡 学习建议

1. **循序渐进**：从基础数据结构开始，逐步深入
2. **多动手**：光看不练假把式，一定要自己实现
3. **多思考**：理解算法原理，不要死记硬背
4. **多总结**：记录常见题型和解题模板
5. **坚持练习**：每天至少完成 1-2 道题目
6. **定期复习**：定期回顾已学过的内容

## 🤝 贡献

欢迎提交 Issue 和 Pull Request！

## 📄 许可证

本项目基于 MIT 许可证开源。

---

**Happy Coding! 🎉**

*最后更新时间：2025-11-05*
