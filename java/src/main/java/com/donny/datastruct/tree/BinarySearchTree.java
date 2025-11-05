package com.donny.datastruct.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜索树（BST）实现
 * 支持插入、删除、查找、遍历等操作
 */
public class BinarySearchTree {
    
    /**
     * 二叉搜索树节点
     */
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
    
    private TreeNode root;
    
    /**
     * 构造函数
     */
    public BinarySearchTree() {
        root = null;
    }
    
    /**
     * 插入节点
     * @param val 要插入的值
     */
    public void insert(int val) {
        root = insert(root, val);
    }
    
    private TreeNode insert(TreeNode node, int val) {
        if (node == null) {
            return new TreeNode(val);
        }
        
        if (val < node.val) {
            node.left = insert(node.left, val);
        } else if (val > node.val) {
            node.right = insert(node.right, val);
        }
        // 如果值相等，不插入（或者可以插入到右子树）
        
        return node;
    }
    
    /**
     * 删除节点
     * @param val 要删除的值
     */
    public void delete(int val) {
        root = delete(root, val);
    }
    
    private TreeNode delete(TreeNode node, int val) {
        if (node == null) {
            return null;
        }
        
        if (val < node.val) {
            node.left = delete(node.left, val);
        } else if (val > node.val) {
            node.right = delete(node.right, val);
        } else {
            // 找到要删除的节点
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                // 有两个子节点：找到右子树的最小值节点
                TreeNode minNode = findMin(node.right);
                node.val = minNode.val;
                node.right = delete(node.right, minNode.val);
            }
        }
        
        return node;
    }
    
    /**
     * 查找最小节点
     */
    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    /**
     * 查找节点
     * @param val 要查找的值
     * @return 是否存在
     */
    public boolean search(int val) {
        return search(root, val);
    }
    
    private boolean search(TreeNode node, int val) {
        if (node == null) {
            return false;
        }
        
        if (val == node.val) {
            return true;
        } else if (val < node.val) {
            return search(node.left, val);
        } else {
            return search(node.right, val);
        }
    }
    
    /**
     * 中序遍历（BST的中序遍历是有序的）
     * @return 遍历结果列表
     */
    public List<Integer> inorderTraversal() {
        List<Integer> result = new ArrayList<>();
        inorderTraversal(root, result);
        return result;
    }
    
    private void inorderTraversal(TreeNode node, List<Integer> result) {
        if (node != null) {
            inorderTraversal(node.left, result);
            result.add(node.val);
            inorderTraversal(node.right, result);
        }
    }
    
    /**
     * 获取最小值
     * @return 最小值
     */
    public int getMin() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        return findMin(root).val;
    }
    
    /**
     * 获取最大值
     * @return 最大值
     */
    public int getMax() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        TreeNode node = root;
        while (node.right != null) {
            node = node.right;
        }
        return node.val;
    }
    
    /**
     * 获取树的高度
     * @return 树的高度
     */
    public int height() {
        return height(root);
    }
    
    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }
    
    /**
     * 判断树是否为空
     * @return 是否为空
     */
    public boolean isEmpty() {
        return root == null;
    }
}

