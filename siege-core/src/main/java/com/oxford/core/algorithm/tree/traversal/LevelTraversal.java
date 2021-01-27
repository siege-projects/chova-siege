package com.oxford.core.algorithm.tree.traversal;

import com.oxford.core.algorithm.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

import static com.oxford.core.algorithm.tree.TreeNode.visit;

/**
 * 层次遍历 - 二叉树
 *
 * @author Chova
 * @date 2021/01/27
 */
public class LevelTraversal {

    /**
     * 层次遍历二叉树
     *
     * @param treeNode 二叉树节点
     */
    public void levelTraversal(TreeNode treeNode) {
        Queue<TreeNode> treeQueue = new ArrayDeque<>();
        treeQueue.offer(treeNode);
        while (!treeQueue.isEmpty() && null != treeNode) {
            TreeNode tempNode = treeQueue.poll();
            visit(tempNode);
            if (null != tempNode.left) {
                treeQueue.offer(tempNode.left);
            }
            if (null != tempNode.right) {
                treeQueue.offer(tempNode.right);
            }
        }
    }
}
