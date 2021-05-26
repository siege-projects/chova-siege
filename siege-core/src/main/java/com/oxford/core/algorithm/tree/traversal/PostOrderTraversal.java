package com.oxford.core.algorithm.tree.traversal;

import com.oxford.core.algorithm.tree.TreeNode;

import java.util.*;

import static com.oxford.core.algorithm.tree.TreeNode.visit;

/**
 * 后序遍历 - 二叉树
 *
 * @author Chova
 * @date 2021/01/26
 */
public class PostOrderTraversal {

    /**
     * 以递归的方式后序遍历二叉树
     *
     * @param treeNode 二叉树节点
     */
    public void postOrderTravesalInRecursion(TreeNode treeNode) {
        if (null != treeNode) {
            if (null != treeNode.left) {
                postOrderTravesalInRecursion(treeNode.left);
            }
            if (null != treeNode.right) {
                postOrderTravesalInRecursion(treeNode.right);
            }
            visit(treeNode);
        }
    }

    /**
     * 以非递归的方式后序遍历二叉树，采用类似前序遍历的方式反转实现
     * 先采用类似前序遍历的方式，因为后序遍历的顺序是：左子树 -> 右子树 -> 根节点，这样可以先遍历根节点 -> 右子树 -> 左子树，然后将遍历的序
     * 列逆转则可以得到后序遍历的序列
     *
     * @param treeNode 二叉树节点
     */
    public void postOrderTraversalInNonrecursionReverse(TreeNode treeNode) {
        // 遍历列表，用来保存遍历二叉树的节点的值
        List<TreeNode> traversalList = new ArrayList<>();
        Stack<TreeNode> treeStack = new Stack<>();

        treeStack.push(treeNode);
        while (!treeStack.isEmpty() && null != treeNode) {
            TreeNode tempNode = treeStack.pop();
            if (null != tempNode) {
                traversalList.add(tempNode);
                treeStack.push(treeNode.left);
                treeStack.push(treeNode.right);
            }
        }

        // 将遍历列表反转后访问节点的值，输出遍历的结果
        if (!traversalList.isEmpty()) {
            Collections.reverse(traversalList);
            traversalList.forEach(TreeNode::visit);
        }
    }

    /**
     * 以非递归的方式后序遍历二叉树，使用Set实现备忘录
     *
     * @param treeNode 二叉树节点
     */
    public void postOrderTraversalInNonRecusrionMemo(TreeNode treeNode) {
        // 遍历列表，用来保存遍历二叉树的节点的值
        List<TreeNode> traversalList = new ArrayList<>();
        Stack<TreeNode> treeStack = new Stack<>();
        // 备忘录，用来保存已经被访问的节点
        Set<TreeNode> memo = new HashSet<>();

        treeStack.push(treeNode);
        while (!treeStack.isEmpty() && null != treeNode) {
            TreeNode tempNode = treeStack.peek();
            if (null != tempNode.left && null != tempNode.right) {
                tempNode = treeStack.pop();
                traversalList.add(tempNode);
                memo.add(tempNode);
                continue;
            } else if (memo.contains(tempNode.left) && memo.contains(tempNode.right)) {
                tempNode = treeStack.pop();
                traversalList.add(tempNode);
                memo.add(tempNode);
                continue;
            }
            if (null != tempNode.left && !memo.contains(tempNode.left)) {
                treeStack.push(tempNode.left);
                tempNode = tempNode.left;
            }
            if (null != tempNode.right && !memo.contains(tempNode.right)) {
                treeStack.push(tempNode.right);
            }
        }

        // 访问节点的值，输出遍历的结果
        if (!traversalList.isEmpty()) {
            traversalList.forEach(TreeNode::visit);
        }

    }
}
