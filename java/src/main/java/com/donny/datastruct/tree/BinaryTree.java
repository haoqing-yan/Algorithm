package com.donny.datastruct.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树实现
 * 支持前序、中序、后序、层序遍历
 */
public class BinaryTree<E> {
    
    /**
     * 二叉树节点
     */
    public static class TreeNode<E> {
        E data;
        TreeNode<E> left;
        TreeNode<E> right;
        
        public TreeNode(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    
    private TreeNode<E> root;
    
    /**
     * 构造函数
     */
    public BinaryTree() {
        root = null;
    }
    
    /**
     * 构造函数，使用根节点
     */
    public BinaryTree(E rootData) {
        root = new TreeNode<>(rootData);
    }
    
    /**
     * 获取根节点
     */
    public TreeNode<E> getRoot() {
        return root;
    }
    
    /**
     * 设置根节点
     */
    public void setRoot(TreeNode<E> root) {
        this.root = root;
    }
    
    /**
     * 前序遍历（根-左-右）
     * @return 遍历结果列表
     */
    public List<E> preorderTraversal() {
        List<E> result = new ArrayList<>();
        preorderTraversal(root, result);
        return result;
    }
    
    private void preorderTraversal(TreeNode<E> node, List<E> result) {
        if (node != null) {
            result.add(node.data);
            preorderTraversal(node.left, result);
            preorderTraversal(node.right, result);
        }
    }
    
    /**
     * 中序遍历（左-根-右）
     * @return 遍历结果列表
     */
    public List<E> inorderTraversal() {
        List<E> result = new ArrayList<>();
        inorderTraversal(root, result);
        return result;
    }
    
    private void inorderTraversal(TreeNode<E> node, List<E> result) {
        if (node != null) {
            inorderTraversal(node.left, result);
            result.add(node.data);
            inorderTraversal(node.right, result);
        }
    }
    
    /**
     * 后序遍历（左-右-根）
     * @return 遍历结果列表
     */
    public List<E> postorderTraversal() {
        List<E> result = new ArrayList<>();
        postorderTraversal(root, result);
        return result;
    }
    
    private void postorderTraversal(TreeNode<E> node, List<E> result) {
        if (node != null) {
            postorderTraversal(node.left, result);
            postorderTraversal(node.right, result);
            result.add(node.data);
        }
    }
    
    /**
     * 层序遍历（广度优先）
     * @return 遍历结果列表
     */
    public List<E> levelOrderTraversal() {
        List<E> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode<E> node = queue.poll();
            result.add(node.data);
            
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        
        return result;
    }
    
    /**
     * 获取树的最大深度
     * @return 树的最大深度
     */
    public int maxDepth() {
        return maxDepth(root);
    }
    
    private int maxDepth(TreeNode<E> node) {
        if (node == null) {
            return 0;
        }
        return Math.max(maxDepth(node.left), maxDepth(node.right)) + 1;
    }
    
    /**
     * 获取树的最小深度
     * @return 树的最小深度
     */
    public int minDepth() {
        return minDepth(root);
    }
    
    private int minDepth(TreeNode<E> node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        if (node.left == null) {
            return minDepth(node.right) + 1;
        }
        if (node.right == null) {
            return minDepth(node.left) + 1;
        }
        return Math.min(minDepth(node.left), minDepth(node.right)) + 1;
    }
    
    /**
     * 获取节点总数
     * @return 节点总数
     */
    public int size() {
        return size(root);
    }
    
    private int size(TreeNode<E> node) {
        if (node == null) {
            return 0;
        }
        return size(node.left) + size(node.right) + 1;
    }
    
    /**
     * 判断树是否为空
     * @return 是否为空
     */
    public boolean isEmpty() {
        return root == null;
    }
}

