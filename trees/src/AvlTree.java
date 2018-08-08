/**
 * Created by vaibhavmishra on 26/11/17.
 */

/*
AVL tree is a self-balancing Binary Search Tree (BST) where the difference between heights of left and right subtrees cannot
be more than one for all nodes.

            12
           / \
          8  18
         / \   \
        5  11   19
       /
      4
Most of the BST operations (e.g., search, max, min, insert, delete.. etc) take O(h) time where h is the height of the BST.
The cost of these operations may become O(n) for a skewed Binary tree. If we make sure that height of the tree remains O(Logn)
after every insertion and deletion, then we can guarantee an upper bound of O(Logn) for all these operations. The height of an
AVL tree is always O(Logn) where n is the number of nodes in the tree

Insertion
To make sure that the given tree remains AVL after every insertion, we must augment the standard BST insert operation to
perform some re-balancing. Following are two basic operations that can be performed to re-balance a BST without violating the
BST property (keys(left) < key(root) < keys(right)). 1) Left Rotation 2) Right Rotation

T1, T2 and T3 are subtrees of the tree rooted with y (on left side)
or x (on right side)
                y                               x
               / \     Right Rotation          /  \
              x   T3   – – – – – – – >        T1   y
             / \       < - - - - - - -            / \
            T1  T2     Left Rotation            T2  T3
Keys in both of the above trees follow the following order
    keys(T1) < key(x) < keys(T2) < key(y) < keys(T3)
So BST property is not violated anywhere.


Steps to follow for insertion :-
Let the newly inserted node be w
1) Perform standard BST insert for w.
2) Starting from w, travel up and find the first unbalanced node. Let z be the first unbalanced node, y be the child of z that
comes on the path from w to z and x be the grandchild of z that comes on the path from w to z.
3) Re-balance the tree by performing appropriate rotations on the subtree rooted with z. There can be 4 possible cases that
needs to be handled as x, y and z can be arranged in 4 ways. Following are the possible 4 arrangements:
a) y is left child of z and x is left child of y (Left Left Case)
b) y is left child of z and x is right child of y (Left Right Case)
c) y is right child of z and x is right child of y (Right Right Case)
d) y is right child of z and x is left child of y (Right Left Case)
We only need to re-balance the subtree rooted with z and the complete tree becomes balanced as the height of subtree
(After appropriate rotations) rooted with z becomes same as it was before insertion.

a) Left Left Case:-
T1, T2, T3 and T4 are subtrees.
http://www.geeksforgeeks.org/wp-content/uploads/AVLINSERT2.jpg.jpg

         z                                      y
        / \                                   /   \
       y   T4      Right Rotate (z)          x      z
      / \          - - - - - - - - ->      /  \    /  \
     x   T3                               T1  T2  T3  T4
    / \
  T1   T2
b) Left Right Case
http://www.geeksforgeeks.org/wp-content/uploads/AVLINSERT4.jpg
     z                               z                           x
    / \                            /   \                        /  \
   y   T4  Left Rotate (y)        x    T4  Right Rotate(z)    y      z
  / \      - - - - - - - - ->    /  \      - - - - - - - ->  / \    / \
T1   x                          y    T3                    T1  T2 T3  T4
    / \                        / \
  T2   T3                    T1   T2
c) Right Right Case
http://www.geeksforgeeks.org/wp-content/uploads/AVLINSERT3.jpg
  z                                y
 /  \                            /   \
T1   y     Left Rotate(z)       z      x
    /  \   - - - - - - - ->    / \    / \
   T2   x                     T1  T2 T3  T4
       / \
     T3  T4
d) Right Left Case
http://www.geeksforgeeks.org/wp-content/uploads/AVLINSERT5.jpg

   z                            z                            x
  / \                          / \                          /  \
T1   y   Right Rotate (y)    T1   x      Left Rotate(z)   z      y
    / \  - - - - - - - - ->     /  \   - - - - - - - ->  / \    / \
   x   T4                      T2   y                  T1  T2  T3  T4
  / \                              /  \
T2   T3                           T3   T4



Time Complexity: The rotation operations (left and right rotate) take constant time as only few pointers are being changed there. Updating the height and getting the balance
factor also take constant time. So the time complexity of AVL insert remains same as BST insert which is O(h) where h is height of the tree. Since AVL tree is balanced,
the height is O(Logn). So time complexity of AVL insert is O(Logn).

Comparison with Red Black Tree
The AVL tree and other self balancing search trees like Red Black are useful to get all basic operations done in O(Log n) time. The AVL trees are more balanced compared to Red
Black Trees, but they may cause more rotations during insertion and deletion. So if your application involves many frequent insertions and deletions, then Red Black trees
should be preferred. And if the insertions and deletions are less frequent and search is more frequent operation, then AVL tree should be preferred over Red Black Tree.

 */


class AvlNode {
    public AvlNode(int data) {
        this.data = data;
        this.height = 1;
    }

    private int data;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public AvlNode getLeft() {
        return left;
    }

    public void setLeft(AvlNode left) {
        this.left = left;
    }

    public AvlNode getRight() {
        return right;
    }

    public void setRight(AvlNode right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    private AvlNode left;

    private AvlNode right;

    private int height;


}


public class AvlTree {

    AvlNode root;

    //returns height of a given node
    private int height(AvlNode node){
        if(node == null){
            return 0;
        }
        return node.getHeight();
    }

    //returns balance factor for a given node
    private int balanceFactor(AvlNode node){
        if(node == null){
            return 0;
        }
        return height(node.getLeft()) - height(node.getRight());
    }



    /*
                y                                x
               / \     Right Rotation          /  \
              x   T3   – – – – – – – >        T1   y
             / \       < - - - - - - -            / \
            T1  T2     Left Rotation            T2  T3
     */
    private AvlNode leftRotate(AvlNode x){
        AvlNode y = x.getRight();
        AvlNode t2 = y.getLeft();

        y.setLeft(x);
        x.setRight(t2);

        //update heights of node x and y since x is going down and y is coming up.
        x.setHeight(Math.max(height(x.getLeft()),height(x.getRight())) + 1);
        y.setHeight(Math.max(height(y.getLeft()),height(y.getRight())) + 1);
        return y;
    }

    private AvlNode rightRotate(AvlNode y){
        AvlNode x = y.getLeft();
        AvlNode t2 = x.getRight();

        x.setRight(y);
        y.setLeft(t2);

        //update heights of node x and y since y is going down and x is coming up.
        x.setHeight(Math.max(height(x.getLeft()),height(x.getRight())) + 1);
        y.setHeight(Math.max(height(y.getLeft()),height(y.getRight())) + 1);
        return x;
    }

    private AvlNode insert(AvlNode root, int data){
        if(root == null){
            return new AvlNode(data);
        }
        if(root.getData() > data){
            root.setLeft(insert(root.getLeft(),data));
        }
        else if(root.getData() < data){
            root.setRight(insert(root.getRight(),data));
        }
        else {
            return root;
        }
        //after insertion, update the height
        root.setHeight(Math.max(height(root.getLeft()),height(root.getRight())) + 1);
        int balance = balanceFactor(root);
        //left left case
        if(balance > 1 && root.getLeft().getData() > data){
            return rightRotate(root);
        }
        //left right case
        if(balance > 1 && root.getLeft().getData() < data){
            root.setLeft(leftRotate(root.getLeft()));
            return rightRotate(root);
        }
        //right right case
        if(balance < -1 && root.getRight().getData() < data){
            return leftRotate(root);
        }
        //right left case
        if(balance < -1 && root.getRight().getData() > data){
            root.setRight(rightRotate(root.getRight()));
            return leftRotate(root);
        }
        return root;
    }


    /*
    1) Perform the normal BST deletion.
2) The current node must be one of the ancestors of the deleted node. Update the height of the current node.
3) Get the balance factor (left subtree height – right subtree height) of the current node.
4) If balance factor is greater than 1, then the current node is unbalanced and we are either in Left Left case or Left Right case. To check whether it is Left Left case or
Left Right case, get the balance factor of left subtree. If balance factor of the left subtree is greater than or equal to 0,
then it is Left Left case, else Left Right case.
5) If balance factor is less than -1, then the current node is unbalanced and we are either in Right Right case or Right Left case. To check whether it is Right Right case or
Right Left case, get the balance factor of right subtree. If the balance factor of the right subtree is smaller than or equal to 0, then it is Right Right case,
else Right Left case.
     */
    private AvlNode delete(AvlNode root, int data){
        if(root == null){
            return root;
        }
        if(data > root.getData()){
            root.setRight(delete(root.getRight(),data));
        }
        else if(data < root.getData()){
            root.setLeft(delete(root.getLeft(),data));
        }
        else {
            if(root.getLeft() != null && root.getRight() != null){
                AvlNode temp = findMax(root.getLeft());
                root.setData(temp.getData());
                root.setLeft(delete(root.getLeft(),temp.getData()));
            }
            else {
                if(root.getLeft() != null){
                    root = root.getLeft();
                }
                else {
                    root = root.getRight();
                }
            }
        }
        //update height
        root.setHeight(Math.max(height(root.getLeft()),height(root.getRight())) + 1);
        int balance = balanceFactor(root);
        //left left case
        if(balance > 1 && balanceFactor(root.getLeft()) >= 0){
            return rightRotate(root);
        }
        //left right case
        if(balance > 1 && balanceFactor(root.getLeft()) < 0){
            root.setLeft(leftRotate(root.getLeft()));
            return rightRotate(root);
        }
        //right right
        if(balance < -1 && balanceFactor(root.getRight()) <= 0){
            return leftRotate(root);
        }
        //right left
        if(balance < -1 && balanceFactor(root.getRight()) > 0){
            root.setRight(rightRotate(root.getRight()));
            return leftRotate(root);
        }
        return root;
    }

    private AvlNode findMax(AvlNode root){
        while (root.getRight() != null){
            root = root.getRight();
        }
        return root;
    }


    public void delete(){
        createAVLTree(new int[] {9,5,10,0,6,11,-1,1,2});
        root = delete(root,10);
        System.out.println();
        System.out.println("Inorder traversal after deletion:");
        inorder(root);
    }



    public void createAVLTree(int[] arr){
        for(int i : arr){
            root = insert(root,i);
        }
        inorder(root);
    }

    private void preorder(AvlNode root){
        if(root != null){
            System.out.print(root.getData() + " ");
            preorder(root.getLeft());
            preorder(root.getRight());
        }
    }

    private void inorder(AvlNode root){
        if(root != null){
            inorder(root.getLeft());
            System.out.print(root.getData() + " ");
            inorder(root.getRight());
        }
    }




    public static void main(String[] args) {
        AvlTree tree = new AvlTree();
        //tree.createAVLTree(new int[] {10,20,30,40,50,25});
        tree.delete();
    }
}
