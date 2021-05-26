package com.oxford.core.algorithm.tree.traversal;


import com.oxford.core.algorithm.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import static com.oxford.core.algorithm.tree.TreeNode.visit;

/**
 * 前序遍历 - 二叉树
 *
 * @author Chova
 * @date 2021/01/24
 */
public class PreOrderTraversal {

    /**
     * 以递归的方式前序遍历二叉树
     *
     * @param treeNode 二叉树节点
     */
    public void preOrderTraversalInRecursion(TreeNode treeNode) {
        if (null != treeNode) {
            visit(treeNode);
            if (null != treeNode.left) {
                preOrderTraversalInRecursion(treeNode.left);
            }
            if (null != treeNode.right) {
                preOrderTraversalInRecursion(treeNode.right);
            }
        }
    }

    /**
     * 以非递归的方式前序遍历二叉树。使用LinkedList双向链表实现栈
     * 前序遍历的顺序：根节点 -> 左子树 -> 右子树。对于任意二叉树，先访问节点，然后判断左子树是否为空，如果不为空则重复上面的步骤，直至左子树
     * 为空；当左子树为空时，则开始访问右子树。
     * 因为访问左孩子之后需要反过来访问右孩子，所以这种数据结构需要堆栈的支持，使用LinkedList双向链表实现栈，具体实现步骤如下：
     * 1. 访问二叉树的节点，并将节点Node入栈，然后将当前节点置为左孩子
     * 2. 判断节点是否为空，如果为空则取出栈顶节点并出栈，将当前节点置为右孩子；如果不为空则重复步骤1直到节点为空或者栈为空
     *
     * @param treeNode 二叉树节点
     */
    public void preOrderTraversalInNonrecursionLinkedList(TreeNode treeNode) {
        LinkedList<TreeNode> treeStack = new LinkedList<>();
        TreeNode tempTreeNode = treeNode;

        while (null != tempTreeNode || !treeStack.isEmpty()) {
            if (null != tempTreeNode) {
                visit(treeNode);
                treeStack.push(tempTreeNode);
                tempTreeNode = treeNode.left;
            } else {
                // null == tempTreeNode && !treeStack.isEmpty()
                TreeNode node = treeStack.pop();
                tempTreeNode = node.right;
            }
        }
    }

    /**
     * 以非递归的方式前序遍历二叉树。使用Stack实现栈
     * 栈的特点是FILO，即先进后出
     *
     * @param treeNode 二叉树节点
     */
    public void preOrderTraversalInNonrecursionStack(TreeNode treeNode) {
        // 遍历列表，用来保存遍历二叉树的节点的值
        List<TreeNode> traversalList = new ArrayList<>();
        Stack<TreeNode> treeStack = new Stack<>();

        treeStack.push(treeNode);
        while (!treeStack.isEmpty() && null != treeNode) {
            TreeNode tempTreeNode = treeStack.pop();
            if (null != tempTreeNode) {
                traversalList.add(tempTreeNode);
                /*
                 * 因为栈的特点是先进后出，如果要先访问左子树的节点，则要使得左子树后进才会先出，所以先入栈右子树，再入栈左子树
                 */
                treeStack.push(tempTreeNode.right);
                treeStack.push(tempTreeNode.left);
            }
        }
        // 访问节点的值，输出遍历的结果
        if (null != traversalList) {
            traversalList.forEach(TreeNode::visit);
        }
    }
}
