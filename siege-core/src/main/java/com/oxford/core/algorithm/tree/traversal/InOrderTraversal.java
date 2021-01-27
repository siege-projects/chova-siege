package com.oxford.core.algorithm.tree.traversal;

import com.oxford.core.algorithm.tree.TreeNode;

import java.util.*;

import static com.oxford.core.algorithm.tree.TreeNode.visit;

/**
 * 中序遍历 - 二叉树
 *
 * @author Chova
 * @date 2021/01/25
 */
public class InOrderTraversal {

    /**
     * 以递归的方式中序遍历二叉树
     *
     * @param treeNode 二叉树节点
     */
    public void inOrderTraversalInRecursion(TreeNode treeNode) {
        if (null != treeNode) {
            if (null != treeNode.left) {
                inOrderTraversalInRecursion(treeNode.left);
            }
            visit(treeNode);
            if (null != treeNode.right) {
                inOrderTraversalInRecursion(treeNode.right);
            }
        }
    }

    /**
     * 以非递归的方式中序遍历二叉树
     *
     * @param treeNode 二叉树节点
     */
    public void inOrderTraversalInNonrecursion(TreeNode treeNode) {
        Stack<TreeNode> treeStack = new Stack<>();
        TreeNode tempNode = treeNode;

        while (!treeStack.isEmpty() || null != tempNode) {
            if (null != tempNode) {
                treeStack.push(tempNode);
                tempNode = tempNode.left;
            } else {
                // null == tempNode && !treeStack.isEmpty()
                TreeNode node = treeStack.pop();
                visit(node);
                tempNode = tempNode.right;
            }
        }
    }

    /**
     * 以非递归的方式中序遍历二叉树，使用Set实现备忘录
     * 先将根节点入栈，然后一直往左，将左孩子入栈，直到节点没有左孩子，则访问这个节点。如果这个节点有右孩子，则将右孩子入栈，重复找左孩子的方法。
     * 这个通过Set备忘录来判断节点是否已经被访问
     *
     * @param treeNode 二叉树节点
     */
    public void inOrderTraversalInNonrecursionMemo(TreeNode treeNode) {
        // 遍历列表，用来保存遍历二叉树的节点的值
        List<TreeNode> traversalList = new ArrayList<>();
        Stack<TreeNode> treeStack = new Stack<>();
        // 备忘录，用来保存已经被访问的节点
        Set<TreeNode> memo = new HashSet<>();

        treeStack.push(treeNode);
        while (!treeStack.isEmpty() && null != treeNode) {
            TreeNode tempNode = treeStack.peek();
            // 如果有左孩子，则将左孩子入栈，直到没有左孩子
            while (null != tempNode.left) {
                if (memo.contains(tempNode.left)) {
                    break;
                }
                treeStack.push(tempNode.left);
                tempNode = tempNode.left;
            }
            tempNode = treeStack.pop();
            traversalList.add(tempNode);
            memo.add(tempNode);
            if (null != treeNode.right) {
                treeStack.push(treeNode.right);
            }
        }

        // 访问节点的值，输出遍历的结果
        if (null != traversalList) {
            traversalList.forEach(node -> {
                visit(node);
            });
        }
    }
}
