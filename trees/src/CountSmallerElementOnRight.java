/**
 * Created by vaibhavmishra on 29/11/17.
 */

 /*
    Count smaller elements on right side
    Given an unsorted array arr[] of distinct integers, construct another array countSmaller[] such that countSmaller[i]
    contains count of smaller elements on right side of each element arr[i] in array.

Examples:

Input:   arr[] =  {12, 1, 2, 3, 0, 11, 4}
Output:  countSmaller[]  =  {6, 1, 1, 1, 0, 1, 0}

(Corner Cases)
Input:   arr[] =  {5, 4, 3, 2, 1}
Output:  countSmaller[]  =  {4, 3, 2, 1, 0}

Input:   arr[] =  {1, 2, 3, 4, 5}
Output:  countSmaller[]  =  {0, 0, 0, 0, 0}


Solution:- We maintain a new field say size with the node similar to height
Traverse the array from right to left and keep inserting it into avl tree.While inserting a new key in an AVL tree,
we first compare the key with root. If key is greater than root, then it is greater than all the nodes in left subtree of root.
So we add the size of left subtree to the count of smaller element for the key being inserted. We recursively follow the same
approach for all nodes down the root.

Time complexity - nlog(n) , n * (log(n) for inserting an elements in a tree)

 */

class AvlTreeNode{
    private int data;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSize() {
        return size;
    }

    public AvlTreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.height = 1;
        this.size = 1;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public AvlTreeNode getLeft() {
        return left;
    }

    public void setLeft(AvlTreeNode left) {
        this.left = left;
    }

    public AvlTreeNode getRight() {
        return right;
    }

    public void setRight(AvlTreeNode right) {
        this.right = right;
    }

    private int height;
    private int size;
    private AvlTreeNode left;
    private AvlTreeNode right;

}


public class CountSmallerElementOnRight {

    AvlTreeNode root = null;

    private int height(AvlTreeNode root){
        if(root == null){
            return 0;
        }
        return root.getHeight();
    }

    private int size(AvlTreeNode root){
        if(root == null){
            return 0;
        }
        return root.getSize();
    }

    private int balanceFactor(AvlTreeNode root){
        if(root == null){
            return 0;
        }
        return height(root.getLeft()) - height(root.getRight());
    }


    /*
                x                                        y
               / \           left rotate                / \
              t1 y          <-------------->           x   t3
                / \          right rotate             / \
               t2  t3                               t1  t2


     */
    private AvlTreeNode leftRotate(AvlTreeNode x){
        AvlTreeNode y = x.getRight();
        AvlTreeNode t2 = y.getLeft();
        y.setLeft(x);
        x.setRight(t2);
        x.setHeight(Math.max(height(x.getLeft()),height(x.getRight())) + 1);
        y.setHeight(Math.max(height(y.getLeft()),height(y.getRight())) + 1);
        x.setSize(size(x.getLeft()) + size(x.getRight()) + 1);
        y.setSize(size(y.getLeft()) + size(y.getRight()) + 1);
        return y;
    }

    private AvlTreeNode rightRotate(AvlTreeNode y){
        AvlTreeNode x = y.getLeft();
        AvlTreeNode t2 = x.getRight();
        y.setLeft(t2);
        x.setRight(y);
        x.setHeight(Math.max(height(x.getLeft()),height(x.getRight())) + 1);
        y.setHeight(Math.max(height(y.getLeft()),height(y.getRight())) + 1);
        x.setSize(size(x.getLeft()) + size(x.getRight()) + 1);
        y.setSize(size(y.getLeft()) + size(y.getRight()) + 1);
        return x;
    }



    private AvlTreeNode insert(AvlTreeNode root, int data, int[] countSmaller,int i){
        if(root == null){
            return new AvlTreeNode(data);
        }
        if(root.getData() < data){
            countSmaller[i] = countSmaller[i] + size(root.getLeft()) + 1;
            root.setRight(insert(root.getRight(),data,countSmaller,i));
        }
        else if(root.getData() > data){
            root.setLeft(insert(root.getLeft(),data,countSmaller,i));
        }
        root.setHeight(Math.max(height(root.getLeft()),height(root.getRight())) + 1);
        root.setSize(size(root.getLeft()) + size(root.getRight()) + 1);
        int balance = balanceFactor(root);
        //left left
        if(balance > 1 && root.getLeft().getData() > data){
            return rightRotate(root);
        }
        //left right
        else if(balance > 1 && root.getLeft().getData() < data){
            root.setLeft(leftRotate(root.getLeft()));
            return rightRotate(root);
        }
        //right right
        else if(balance < -1 && root.getRight().getData() < data){
            return leftRotate(root);
        }
        //right left
        else if(balance < -1 && root.getRight().getData() > data){
            root.setRight(rightRotate(root.getRight()));
            return leftRotate(root);
        }
        return root;
    }

    public void constructCountSmallerOnRightArray(int [] arr){
        int [] countSmaller = new int[arr.length];
        /*for(int i = arr.length -1; i >= 0 ; i--){
            root = insert(root,arr[i],countSmaller,i);
        }*/
        for(int i = 0 ; i > arr.length ; i++){
            root = insert(root,arr[i],countSmaller,i);
        }

        for(int i : countSmaller){
            System.out.println(i);
        }
        preorder(root);
        System.out.println();
        inorder(root);
    }

    private void preorder(AvlTreeNode root){
        if(root != null){
            System.out.print("Data:" + root.getData() + ",height:" + root.getHeight() + ",size:" + root.getSize() + "  ");
            preorder(root.getLeft());
            preorder(root.getRight());
        }
    }

    private void inorder(AvlTreeNode root){
        if(root != null){
            inorder(root.getLeft());
            System.out.print("Data:" + root.getData() + ",height:" + root.getHeight() + ",size:" + root.getSize() + "  ");
            inorder(root.getRight());
        }
    }

    public static void main(String[] args) {
        CountSmallerElementOnRight countSmallerElementOnRight = new CountSmallerElementOnRight();
        countSmallerElementOnRight.constructCountSmallerOnRightArray(new int[] {12, 1, 2, 3, 0, 11, 4});
    }


}
