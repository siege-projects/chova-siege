package com.oxford.core.algorithm.tree;

import java.util.Objects;

/**
 * 树的节点结构 - 二叉树
 *
 * @author Chova
 */
public class TreeNode {

    /**
     * 树的节点的值
     */
    public int value;

    /**
     * 树的左节点
     */
    public TreeNode left;

    /**
     * 树的右节点
     */
    public TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }

    public static void visit(TreeNode treeNode) {
        System.out.println(treeNode.value + " ");
    }

    @Override
    public String toString() {
        return "value：" + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TreeNode treeNode = (TreeNode) o;
        return value == treeNode.value &&
                Objects.equals(left, treeNode.left) &&
                Objects.equals(right, treeNode.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, left, right);
    }
}
