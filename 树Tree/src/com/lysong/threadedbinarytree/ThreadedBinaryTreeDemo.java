package com.lysong.threadedbinarytree;

/**
 * @Author: LySong
 * @Date: 2020/3/14 12:12
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试
        Node root = new Node(1, 1);
        Node node2 = new Node(3, 3);
        Node node3 = new Node(6, 6);
        Node node4 = new Node(8, 8);
        Node node5 = new Node(10, 10);
        Node node6 = new Node(14, 14);
        //创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        //以10号结点测试
        Node left = node5.getLeft();
        Node right = node5.getRight();
        System.out.println("10号结点的前驱结点" + left);
        System.out.println("10号结点的后继结点" + right);
        System.out.println("使用线索化遍历二叉树");
        threadedBinaryTree.threadedList();


    }
}

/**
 * 定义一个ThreadedBinaryTree 二叉树
 */
class ThreadedBinaryTree{
    private Node root;
    /**
     * 为了实现线索化，需要创建指向当前节点的前驱结点的指针
     * 在递归进行线索化时，pre总是保留前一个结点
     */
    private Node pre;

    public void setRoot(Node root) {
        this.root = root;
    }

    public void threadedNodes(){
        this.threadedNodes(root);
    }
    //遍历线索化二叉树的方法
    public void threadedList(){
        //定义一个变量，存储当前遍历的结点
        Node node = root;
        while (node != null){
            //循环到leftType == 1的结点
            //后面随着遍历而变化,leftType == 1时，说明该结点按照线索化
            //处理后的有效结点
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }
            //打印当前这个结点
            System.out.println(node);
            //如果当前节点的右指针指向后继结点1就一直输出
            while (node.getRightType() == 1){
                //获取到当前结点的后继结点
                node = node.getRight();
                System.out.println(node);
            }
            //替换遍历的结点
            node = node.getRight();
        }
    }

    /**
     *
     * @param node 当前需要线索化的结点
     */
    public void threadedNodes(Node node){
        //如果node==null，不能线索化
        if(node == null){
            return;
        }
        //先线索化左子树
        threadedNodes(node.getLeft());
        //线索化当前结点
        //处理当前结点的当前节点
        if(node.getLeft() == null){
            //让当前结点的做指针指向前驱结点
            node.setLeft(pre);
            //修改当前结点左指针的类型
            node.setLeftType(1);
        }
        //处理后继结点
        if(pre != null && pre.getRight() == null){
            //让前驱结点的有指针指向当前节点
            pre.setRight(node);
            //修改前驱节点的后指针类型
            pre.setRightType(1);
        }
        //处理一个节点后，让当前节点是下一个节点的前驱结点
        pre = node;
        //处理右子树
        threadedNodes(node.getRight());
    }


    /**
     * 删除结点
     * @param no
     */
    public void deleteNode(int no){
        //判断根节点是否为空
        if(root != null){
            //如果根节点就是需要删除的结点，直接将根节点置空
            if(root.getNo() == no){
                root = null;
            }else {
                //递归删除
                root.deleteNode(no);
            }
        }else {
            System.out.println("空树");
        }
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        if(this.root != null){
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }

    /**
     * 前序遍历查找
     * @param no
     * @return
     */
    public Node preOrderSearch(int no){
        if(root != null){
            return root.preOrderSearch(no);
        }else {
            return null;
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        if(this.root != null){
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }

    /**
     * 中序遍历查找
     * @param no
     * @return
     */
    public Node infixOrderSearch(int no){
        if(root != null){
            return root.infixOrderSearch(no);
        }else {
            return null;
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder(){
        if(this.root != null){
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }

    /**
     * 后序遍历查找
     * @param no
     * @return
     */
    public Node postOrderSearch(int no){
        if(root != null){
            return root.postOrderSearch(no);
        }else {
            return null;
        }
    }

}



//创建Node
/**
 * 先创建结点
 */
class Node{
    private int no;
    private int data;
    private Node left;
    private Node right;
    /**
     * 如果lefttype == 0 表示指向左子树，如果1表示指向前驱结点
     * righttype同理
     */
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public Node(int no, int data) {
        this.no = no;
        this.data = data;
    }

    public int getNo() {
        return no;
    }

    public int getData() {
        return data;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", data=" + data +
                '}';
    }


    /**
     * 递归删除结点
     * @param no
     */
    public void deleteNode(int no){
        if(this.left != null && this.left.no == no){
            this.left = null;
            return;
        }
        if(this.right != null && this.right.no == no){
            this.right = null;
            return;
        }
        if(this.left != null){
            this.left.deleteNode(no);
        }
        if(this.right != null){
            this.right.deleteNode(no);
        }
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        //先输出父节点
        System.out.println(this);
        //递归向左子树前序遍历
        if(this.left != null){
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if(this.right != null){
            this.right.preOrder();
        }
    }

    /**
     * 前序遍历查找
     * @param no no
     * @return
     */
    public Node preOrderSearch(int no){
        System.out.println("进入前序遍历");
        //比较当前结点是否相同
        if(this.no == no){
            return this;
        }
        Node resNode = null;
        //判断左节点是否空
        if(this.left != null){
            resNode = this.left.preOrderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        if(this.right != null){
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        //先递归向左子树
        if(this.left != null){
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);
        //递归向右子树中序遍历
        if(this.right != null){
            this.right.infixOrder();
        }
    }

    /**
     * 中序查找
     * @param no 编号
     * @return
     */
    public Node infixOrderSearch(int no){
        Node resNode = null;
        if(this.left != null){
            resNode = this.left.infixOrderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        System.out.println("中序查找");
        //如果找到则返回
        if(this.no == no){
            return this;
        }
        //否则右递归
        if(this.right != null){
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 后序遍历
     */
    public void postOrder(){
        //先递归向左子树
        if(this.left != null){
            this.left.postOrder();
        }
        if(this.right != null){
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 后序遍历查找
     * @param no
     * @return
     */
    public Node postOrderSearch(int no){
        Node resNode = null;
        //向左递归查找
        if(this.left != null){
            resNode = this.left.postOrderSearch(no);
        }
        //说明在左子树找到
        if(resNode != null){
            return resNode;
        }
        //向右递归查找
        if(this.right != null){
            resNode = this.right.postOrderSearch(no);
        }
        //说明在右子树找到
        if(resNode != null){
            return resNode;
        }
        System.out.println("进入后序查找");
        //如果找到
        if(this.no == no){
            return this;
        }
        //如果没找到
        return null;
    }
}
