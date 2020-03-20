package com.lysong;

/**
 * @Author: LySong
 * @Date: 2020/3/13 14:00
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的结点
        Node root = new Node(1,1);
        binaryTree.setRoot(root);
        Node node2 = new Node(2,2);
        Node node3 = new Node(3,3);
        Node node4 = new Node(4,4);
        Node node5 = new Node(5,5);
        Node node6 = new Node(6,6);
        //先手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setRight(node5);
        node2.setLeft(node4);
        node3.setLeft(node6);

//        //测试前序遍历
//        System.out.println("前序遍历");
//        binaryTree.preOrder();
        //测试中序遍历
        System.out.println("中序遍历");
        binaryTree.infixOrder();
//        //后序遍历
//        System.out.println("后序遍历");
//        binaryTree.postOrder();
//        //前序遍历搜索
//        System.out.println("前序搜索");
//        com.lysong.Node node = binaryTree.preOrderSearch(5);
//        if(node != null){
//            System.out.println(node);
//        }else {
//            System.out.println("没找到");
//        }
//        //中序遍历搜索
//        System.out.println("中序搜索");
//        com.lysong.Node node = binaryTree.infixOrderSearch(5);
//        if(node != null){
//            System.out.println(node);
//        }else {
//            System.out.println("没找到");
//        }
//        //后序遍历搜索
//        System.out.println("后序搜索");
//        com.lysong.Node node = binaryTree.postOrderSearch(15);
//        if(node != null){
//            System.out.println(node);
//        }else {
//            System.out.println("没找到");
//        }
        System.out.println("删除前");
        binaryTree.preOrder();
//        binaryTree.deleteNode(5);
        binaryTree.deleteNode(2);
        System.out.println("删除后");
        binaryTree.preOrder();
    }
}

/**
 * 定义一个BinaryTree 二叉树
 */
class BinaryTree{
    private Node root;

    public void setRoot(Node root) {
        this.root = root;
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


/**
 * 先创建结点
 */
class Node{
    private int no;
    private int data;
    private Node left;
    private Node right;

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
        return "com.lysong.Node{" +
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
