package com.lysong.binarysorttree;

/**
 * @Author: LySong
 * @Date: 2020/3/17 13:40
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7,3,10,12,5,1,9,2};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环的添加结点到二叉排序树
        for(int i = 0; i < arr.length; i++){
            binarySortTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历");
        binarySortTree.infixOrder();
        System.out.println("查询测试");
        Node node = binarySortTree.search(9);
        System.out.println(node.toString());
        System.out.println("删除测试");
        binarySortTree.delNode(5);
        binarySortTree.infixOrder();

    }
}

/**
 * 创建二叉排序树
 */
class BinarySortTree{
    private Node root;

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
