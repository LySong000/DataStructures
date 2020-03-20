package com.lysong;

/**
 * @Author: LySong
 * @Date: 2020/3/14 10:35
 */
public class ArrayBinaryTreeDemp {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        //创建一一个树
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder();
    }
}

/**
 * 编写一个ArrayBinaryTree，实现顺序存储二叉树遍历
 */
class ArrayBinaryTree{
    private int[] arr;

    public ArrayBinaryTree(int[] arr){
        this.arr = arr;
    }

    public void preOrder(){
        this.preOrder(0);
    }

    /**
     * 编写一个方法，完成顺序存储二叉树的前序遍历
     * @param index
     */
    public void preOrder(int index){
        //如果数组为空，或者数组长度为0
        if(arr == null || arr.length == 0){
            System.out.println("数组为空");
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        //向左递归遍历
        if((index * 2 + 1) < arr.length){
            preOrder(2 * index + 1);
        }
        //向右递归遍历
        if((index * 2 + 2) < arr.length){
            preOrder(2 * index + 2);
        }
    }
}