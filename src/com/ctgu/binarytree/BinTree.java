package com.ctgu.binarytree;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 简单的二叉树构造
 *
 * @param <T>
 * @author liyu1127641712@163.com
 */
public class BinTree<T> {

    private BinNode<T> rootNode;// 根节点

    public BinTree() {
    }

    public BinTree(BinNode<T> rootNode) {
        this.rootNode = rootNode;
    }

    /**
     * 得到整棵树的高度
     *
     * @return
     */
    public int getHeight() {
        return getHeight(rootNode);
    }

    /**
     * 利用递归得到某一结点的高度
     *
     * @param binNode 目标结点
     * @return
     */
    public int getHeight(BinNode<T> binNode) {
        if (binNode == null)
            return 0;
        int i = getHeight(binNode.getLeftNode());
        int j = getHeight(binNode.getRightNode());
        return i < j ? j + 1 : i + 1;
    }

    /**
     * 递归得到整棵树的size(即结点数)
     *
     * @return
     */
    public int getSize() {
        return getSize(rootNode);
    }

    /**
     * 得到某一子树的size(即结点数)
     *
     * @param binNode
     * @return
     */
    public int getSize(BinNode<T> binNode) {
        if (binNode == null)
            return 0;
        return 1 + getSize(binNode.getLeftNode()) + getSize(binNode.getRightNode());
    }

    /**
     * 判断二叉树是否为空树
     *
     * @return
     */
    public boolean isEmpty() {
        return rootNode == null;
    }

    /**
     * 得到整棵树下某一结点的双亲结点
     *
     * @param binNode 某一结点
     * @return
     */
    public BinNode<T> getParent(BinNode<T> binNode) {
        return (binNode == null || binNode == rootNode) ? null : getParent(rootNode, binNode);
    }

    /**
     * 递归得到某子树下某结点的双亲结点
     *
     * @param subTree 某子树
     * @param binNode 某结点
     * @return
     */
    public BinNode<T> getParent(BinNode<T> subTree, BinNode<T> binNode) {
        if (subTree == null || binNode == null || subTree == binNode || binNode == rootNode)
            return null;
        if (subTree.getLeftNode() == binNode || subTree.getRightNode() == binNode)
            return subTree;
        BinNode<T> b;
        if (getParent((b = subTree.getLeftNode()), binNode) != null)
            return b;
        return getParent(subTree.getRightNode(), binNode);
    }

    /**
     * 得到某一结点的左孩子结点
     *
     * @param binNode
     * @return
     */
    public BinNode<T> getLeftBinNode(BinNode<T> binNode) {
        return (binNode == null) ? null : binNode.getLeftNode();
    }

    /**
     * 得到某一结点的右孩子结点
     *
     * @param binNode
     * @return
     */
    public BinNode<T> getRightBinNode(BinNode<T> binNode) {
        return (binNode == null) ? null : binNode.getRightNode();
    }

    public BinNode<T> getRoot() {
        return this.rootNode;
    }

    /**
     * 删除整棵树
     */
    public void deleteTree() {
        deleteTree(rootNode);
    }

    /**
     * 删除某子树（同时将其左右子树全部删除）
     *
     * @param binNode
     */
    public void deleteTree(BinNode<T> binNode) {
        if (binNode == null)
            return;
        deleteTree(binNode.getLeftNode());
        deleteTree(binNode.getRightNode());
        binNode = null;
    }

    /**
     * 访问某结点
     *
     * @param subTree
     */
    public void visted(BinNode<T> subTree) {
        System.out.println("--name:" + subTree.getData());
    }

    /**
     * 先序遍历（递归形式）
     *
     * @param subTree
     */
    public void preOrderTraverse(BinNode<T> subTree) {
        if (subTree == null)
            return;
        visted(subTree);
        preOrderTraverse(subTree.getLeftNode());
        preOrderTraverse(subTree.getRightNode());
    }

    /**
     * 中序遍历(递归形式)
     *
     * @param subTree
     */
    public void middleOrderTraverse(BinNode<T> subTree) {
        if (subTree == null)
            return;
        middleOrderTraverse(subTree.getLeftNode());
        visted(subTree);
        middleOrderTraverse(subTree.getRightNode());
    }

    /**
     * 后序遍历(递归形式)
     *
     * @param subTree
     */
    public void postOrderTraverse(BinNode<T> subTree) {
        if (subTree == null)
            return;
        postOrderTraverse(subTree.getLeftNode());
        postOrderTraverse(subTree.getRightNode());
        visted(subTree);
    }

    /**
     * 先序遍历（非递归形式,效率更高）
     *
     * @param subTree
     */
    public void nrPreOrderTraverse(BinNode<T> subTree) {
        if (subTree == null)
            return;
        Stack<BinNode<T>> stack = new Stack<>();
        while (true) {
            while (subTree != null) {
                visted(subTree);
                stack.push(subTree);
                subTree = subTree.getLeftNode();
            }
            if (stack.isEmpty())
                break;
            subTree = stack.pop();
            subTree = subTree.getRightNode();
        }
    }

    /**
     * 中序遍历（非递归形式）
     *
     * @param subTree
     */
    public void nrMiddleOrderTraverse(BinNode<T> subTree) {
        if (subTree == null)
            return;
        Stack<BinNode<T>> stack = new Stack<>();
        while (true) {
            while (subTree != null) {
                stack.push(subTree);
                subTree = subTree.getLeftNode();
            }
            if (stack.isEmpty())
                break;
            subTree = stack.pop();
            visted(subTree);
            subTree = subTree.getRightNode();
        }
    }

    /**
     * 后序遍历（非递归）
     *
     * @param subTree
     */
    public void nrPostOrderTraverse(BinNode<T> subTree) {
        if (subTree == null)
            return;
        Stack<BinNode<T>> stack = new Stack<>();
        BinNode<T> lastNode = null;// 表示最近一次访问的节点
        while (true) {
            while (subTree != null) {
                stack.push(subTree);
                subTree = subTree.getLeftNode();
            }
            if (stack.isEmpty())
                break;
            subTree = stack.peek();
            if (subTree.getRightNode() == null || subTree.getRightNode() == lastNode) {
                visted(subTree);
                subTree = stack.pop();
                lastNode = subTree;
                subTree = null;
            } else {
                subTree = subTree.getRightNode();
            }
        }
    }

    /**
     * 层次遍历（队列实现）
     *
     * @param subTree
     */
    public void levelTraverse(BinNode<T> subTree) {
        if (subTree == null)
            return;
        Queue<BinNode<T>> queue = new LinkedBlockingQueue<>();
        queue.add(subTree);
        while (!queue.isEmpty()) {
            BinNode<T> binNode = queue.poll();
            if (binNode != null) {
                visted(binNode);
                if (binNode.getLeftNode() != null)
                    queue.add(binNode.getLeftNode());
                if (binNode.getRightNode() != null)
                    queue.add(binNode.getRightNode());
            }
        }
    }

    public static void main(String[] args) {
        BinTree<String> binTree = new BinTree<>(new BinNode<String>("A"));
        /**
         * 创建一棵二叉树
         *
         * <pre>
         *
         *           A
         *     B          C
         *  D     E            F
         * </pre>
         *
         */
        BinNode<String> rootNode = binTree.getRoot();
        BinNode<String> nodeB = new BinNode<>("B");
        BinNode<String> nodeC = new BinNode<>("C");
        BinNode<String> nodeD = new BinNode<>("D");
        BinNode<String> nodeE = new BinNode<>("E");
        BinNode<String> nodeF = new BinNode<>("F");
        rootNode.setLeftNode(nodeB);
        rootNode.setRightNode(nodeC);
        nodeB.setLeftNode(nodeD);
        nodeB.setRightNode(nodeE);
        nodeC.setRightNode(nodeF);

        System.out.println("*******先序遍历（递归形式）*******");
        binTree.preOrderTraverse(rootNode);
        System.out.println("*******中序遍历（递归形式）*******");
        binTree.middleOrderTraverse(rootNode);
        System.out.println("*******后序遍历（递归形式）*******");
        binTree.postOrderTraverse(rootNode);
        System.out.println("*******先序遍历（非递归形式）*******");
        binTree.nrPreOrderTraverse(rootNode);
        System.out.println("*******中序遍历（非递归形式）*******");
        binTree.nrMiddleOrderTraverse(rootNode);
        System.out.println("*******后序遍历（非递归形式）*******");
        binTree.nrPostOrderTraverse(rootNode);
        System.out.println("*******层次遍历（队列实现）*******");
        binTree.levelTraverse(rootNode);
        System.out.println("**********我是分割线**********");
        System.out.println("该二叉树的高度为：" + binTree.getHeight());
        System.out.println("该二叉树的size为：" + binTree.getSize());
    }
}
