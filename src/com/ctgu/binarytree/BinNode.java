package com.ctgu.binarytree;

/**
 * 表示树节点的类
 *
 * @param <T> 结点数据域类型
 * @author liyu1127641712@163.com
 */
public class BinNode<T> {

    private BinNode<T> leftNode;// 左结点
    private BinNode<T> rightNode;// 右结点
    private T dataValue;// 数据域

    public BinNode() {
    }

    public BinNode(T dataValue) {
        super();
        this.dataValue = dataValue;
    }

    public BinNode(BinNode<T> leftNode, BinNode<T> rightNode, T dataValue) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.dataValue = dataValue;
    }

    public BinNode<T> getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(BinNode<T> leftNode) {
        this.leftNode = leftNode;
    }

    public BinNode<T> getRightNode() {
        return rightNode;
    }

    public void setRightNode(BinNode<T> rightNode) {
        this.rightNode = rightNode;
    }

    public T getData() {
        return dataValue;
    }

    public void setData(T data) {
        this.dataValue = data;
    }

}