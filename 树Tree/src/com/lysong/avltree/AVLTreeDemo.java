package com.lysong.avltree;

import javax.swing.text.StyledEditorKit;
import javax.xml.stream.events.NotationDeclaration;

/**
 * @Author: LySong
 * @Date: 2020/3/18 16:17
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {10,12,8,9,7,6};
        int[] arr = {10,11,7,6,8,9};
//        int[] arr  = {1,2,3,4,5,6,7};
        //创建一个AVLTree
        AVLTree avlTree = new AVLTree();
        //添加结点
        for (int i:
             arr) {
            avlTree.add(new Node(i));
        }
        //遍历输出
        avlTree.infixOrder();

        System.out.println("平衡处理后");
        System.out.println("树的高度" + avlTree.getRoot().height());
        System.out.println("左子树树的高度" + avlTree.getRoot().leftHeight());
        System.out.println("右子树树的高度" + avlTree.getRoot().rightHeight());
        System.out.println(avlTree.getRoot().toString());
    }
}


class AVLTree{
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node searchNode(int value){
        if(root == null){
            System.out.println("树为空");
            return null;
        }else {
            Node node = root.searchNode(value);
            if(node != null){
                return node;
            }else {
                System.out.println("没有找到");
                return null;
            }
        }
    }

    /**
     * 查找需要删除的结点
     * @param value
     * @return
     */
    public Node search(int value){
        if(root == null){
            return null;
        }else {
            return root.search(value);
        }
    }

    /**
     * 查找父节点
     * @param value
     * @return
     */
    public Node searchParent(int value){
        if(root == null){
            return null;
        }else {
            return root.searchParent(value);
        }
    }

    /**
     * 返回以node为根节点的二叉排序树的最小结点的值
     * @param node
     * @return
     */
    public int delRightTreeMin(Node node){
        Node target = node;
        //循环查找左节点
        while (target.left != null){
            target = target.left;
        }
        //这时指向了最小的结点
        delNode(target.value);
        return target.value;
    }



    /**
     * 删除结点
     * @param value
     */
    public void delNode(int value){
        if(root == null){
            return;
        }else {
            //寻找需要删除的节点
            Node targetNode = search(value);
            //若果没找到
            if(targetNode == null){
                return;
            }
            //如果发现当前这颗二叉排序树只有一个结点
            if(root.left == null && root.right == null){
                root = null;
                return;
            }
            //寻找targetNode的父节点
            Node parent = searchParent(value);
            //如果要删除的结点是叶子节点
            if(targetNode.left == null && targetNode.right == null){
                //判断targetNode是父节点的左子节点还是右子节点
                if(parent.left != null && parent.left.value == value){
                    //是左子节点
                    parent.left = null;
                }else if(parent.right != null && parent.right.value == value){
                    //是右子节点
                    parent.right = null;
                }
            }else if(targetNode.left != null && targetNode.right != null){
                //说明这个结点右左右两个子树
                int min = delRightTreeMin(targetNode.right);
                targetNode.value = min;
            }else {
                //删除只有一颗子树的结点
                //如果要删除的结点有左子节点
                if(targetNode.left != null){
                    //是左子节点
                    if(parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {
                            //是右子节点
                            parent.right = targetNode.right;
                        }
                    }else {
                        root = targetNode;
                    }
                }else {
                    //要删除的结点右右子节点
                    if(parent != null){
                        if(parent.left.value == value){
                            parent.left = targetNode.right;
                        }else {
                            parent.right = targetNode.right;
                        }
                    }else {
                        root = targetNode;
                    }
                }
            }
        }
    }

    /**
     * 添加结点
     * @param node
     */
    public void add(Node node){
        if(root != null){
            root.add(node);
        }else {
            root = node;
        }
    }

    public void infixOrder(){
        if(root != null){
            root.infixOrder();
        }else {
            System.out.println("树为空");
        }
    }
}


/**
 * 创建node结点
 */
class Node{
    /**
     * 值
     */
    int value;
    /**
     * 左子节点
     */
    Node left;
    /**
     * 右子节点
     */
    Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 右旋转方法
     */
    private void rightRotate(){
        //创建新的结点
        Node newNode = new Node(this.value);
        //将新节点的rights设为跟的right
        newNode.right = this.right;
        //将新节点的left设为根节点的left的right
        newNode.left = this.left.right;
        //将根节点的值换成根节点的left的值
        this.value = this.left.value;
        //将根节点的left指向left的left
        this.left = this.left.left;
        //将根节点的right指向newNode
        this.right = newNode;
    }

    /**
     * 左旋转方法
     */
    private void leftRotate(){
        //创建新的结点
        Node newNode = new Node(value);
        //把新的结点的左子树设置成当前结点的左子树
        newNode.left = left;
        //把新的结点的根节点的右子节点的左子节点设置成右子树
        newNode.right = right.left;
        //把根节点的值设为根节点的右子节点
        this.value = this.right.value;
        //将根节点的值指向右子节点的右子节点
        this.right = this.right.right;
        //把根节点的左子树设置为新节点
        this.left = newNode;
    }

    /**
     *
     * @return 返回左子树的高度
     */
    public int leftHeight(){
        if(left == null){
            return 0;
        }
        return left.height();
    }

    /**
     *
     * @return 返回右子树的高度
     */
    public int rightHeight(){
        if(right == null){
            return 0;
        }
        return right.height();
    }

    /**
     *
     * @return 返回以当前结点为根节点的树的高度
     */
    public int height(){
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    /**
     * 结点的搜索
     * @param value
     * @return
     */
    public Node searchNode(int value){
        //判断是否相等
        if(this.value == value){
            return this;
        }else if(this.value > value){
            //如果大于传入值且左子树不为空，则向左递归
            if(this.left != null) {
                return this.left.searchNode(value);
            }
        }else if (this.value < value){
            //如果小于传入值且右子树不为空，则向右递归
            if(this.right != null) {
                return this.right.searchNode(value);
            }
        }
        return null;
    }


    /**
     * 查找需要删除的结点
     * @param value 希望删除的值
     * @return
     */
    public Node search(int value){
        if(value == this.value){
            return this;
        }else if(value < this.value){
            //如果左子节点为空
            if(this.left == null){
                return null;
            }
            //向左子树递归查找
            return this.left.search(value);
        }else {
            //判断右子树是否为空
            if(this.right == null){
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找删除结点的父节点
     * @param value 要找的结点的值
     * @return 返回要删除结点的父节点
     */
    public Node searchParent(int value){
        //如果当前结点就是要删除的父节点
        if((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)){
            return this;
        }else {
            //如果查找的值小于当前结点的值,并且当前结点的左子节点不为空
            if(value < this.value && this.left != null){
                return this.left.searchParent(value);
            }else if(value >= this.value && this.right != null){
                //向右子树递归查找
                return this.right.searchParent(value);
            }else {
                return null;
            }
        }
    }


    /**
     * 递归的形式添加结点，满足二叉排序树的要求
     * @param node
     */
    public void add(Node node){
        if(node == null){
            return;
        }
        //判断传入的结点的值和当前子树的跟结点的值的关系
        if(node.value < this.value){
            //如果当前结点左子节点为空，直接挂上去
            if(this.left == null){
                this.left = node;
            }else {
                //递归的向左子树添加
                this.left.add(node);
            }
        }else {
            //添加的结点大于当前节点的值
            if(this.right == null){
                //如果右子树为空，直接挂上去
                this.right = node;
            }else {
                //递归向右子树添加
                this.right.add(node);
            }
        }
        //当添加结点后，（右子树的高度 - 左子树的高度） > 1，左旋转
        if(rightHeight() - leftHeight() > 1){
            if(right != null && right.leftHeight() > right.rightHeight()){
                //如果当前的右子树的左子树高度大于右子树高度
                //先右旋转
                this.right.rightHeight();
                //再左旋转
                this.leftRotate();
            }else {
                //左旋转
                leftRotate();
            }
            return;
        }
        //当添加结点后， （左子树的高度 - 右子树的高度）> 1，有旋转
        if(leftHeight() - rightHeight() > 1){
            //如果他的左子树的右子树的高度大于它的左子树的高度
            if(left != null && left.rightHeight() > left.leftHeight()){
                //先对当前结点的左节点进行左旋转
                this.left.leftRotate();
                //右旋转
                rightRotate();
            }else {
                rightHeight();
            }
            return;
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        if(this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this.value);
        if(this.right != null){
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
