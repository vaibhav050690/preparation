import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by vaibhavmishra on 30/10/17.
 */

class ListNode {
    int data;
    ListNode next;
    ListNode prev;
}


class ExpressionNode {
    char value;
    ExpressionNode left, right;

    ExpressionNode(char item) {
        value = item;
        left = right = null;
    }
}



public class Trees {

    private static int preIndex = 0;
    private static boolean v1= false;
    private static boolean v2= false;


    /*
                    1
                   / \
                  2  3
                 / \ / \
                4 5  6  7
               /\ /\ /\ /\
              8 9      10 11
                            \
                            12
     */
    private Node createBinaryTree(){

        Node root = new Node(1);
        root.setLeft(new Node(2));
        root.setRight(new Node(3));
        root.getLeft().setLeft(new Node(4));
        root.getLeft().setRight(new Node(5));
        root.getRight().setLeft(new Node(6));
        root.getRight().setRight(new Node(7));
        root.getLeft().getLeft().setLeft(new Node(8));
        root.getLeft().getLeft().setRight(new Node(9));
        root.getRight().getRight().setLeft(new Node(10));
        root.getRight().getRight().setRight(new Node(11));
        root.getRight().getRight().getRight().setRight(new Node(12));
        return root;

        /*
        Node root = new Node(1);
        root.setLeft(new Node(2));
        root.setRight(new Node(3));
        root.getLeft().setRight(new Node(4));
        root.getRight().setRight(new Node(7));
        root.getLeft().getRight().setLeft(new Node(5));
        root.getLeft().getRight().setRight(new Node(6));
        root.getRight().getRight().setLeft(new Node(8));
        return root;
        */

    }

    /*
    Preorder traversal is used to create a copy of the tree.
    Preorder traversal is also used to get prefix expression on of an expression tree.eg expression 5 − (6 × 7) can be written as
    − 5 × 6 7
        -
       / \
      5   *
          /\
         6 7
     */
    public void preorder(){
        preOrderRec(createBinaryTree());
        System.out.println();
        preOrderIt(createBinaryTree());
    }


    //time complexity - O(n)
    private void preOrderRec(Node root){
        if(root != null){
            System.out.print(root.getData() + ",");
            preOrderRec(root.getLeft());
            preOrderRec(root.getRight());
        }
    }

    //time complexity - O(n)
    private void preOrderIt(Node root){
        if(root == null){
            return;
        }
        List<Integer> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node node = stack.pop();
            result.add(node.getData());
            if(node.getRight() != null){
                stack.push(node.getRight());
            }
            if(node.getLeft() != null){
                stack.push(node.getLeft());
            }
        }
        System.out.print(result);
    }

    /*
    application of preorder traversal
     */
    public void cloneTree(){
        Node clonedNode = clone(createBinaryTree());
        inOrderRec(clonedNode);
    }


    public Node clone(Node root){
        if(root == null){
            return null;
        }
        Node root1 = new Node(root.getData());
        root1.setLeft(clone(root.getLeft()));
        root1.setRight(clone(root.getRight()));
        return root1;
    }



    /*
    In case of binary search trees (BST), Inorder traversal gives nodes in ascending order.
     */
    public void inorder(){
        inOrderRec(createBinaryTree());
        System.out.println();
        inOrderIt(createBinaryTree());

    }

    //time complexity - O(n)
    private void inOrderRec(Node root){
        if(root != null){
            inOrderRec(root.getLeft());
            System.out.print(root.getData() + ",");
            inOrderRec(root.getRight());
        }
    }

    //time complexity - O(n)
    private void inOrderIt(Node root){
        if(root == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        Node curr = root;
        while (curr != null){
            stack.push(curr);
            curr = curr.getLeft();
        }
        while (!stack.isEmpty()){
            Node node = stack.pop();
            result.add(node.getData());
            if(node.getRight() != null){
                node = node.getRight();
                while (node != null){
                    stack.push(node);
                    node = node.getLeft();
                }
            }
        }
        System.out.println(result);
    }


    /*
    Postorder traversal is used to delete the tree.
    Postorder traversal is also useful to get the postfix expression of an expression tree.
     */
    public void postorder(){
        postOrderRec(createBinaryTree());
        System.out.println();
        postOrderIt2Stacks(createBinaryTree());
        postOrderItSingleStack(createBinaryTree());

    }

    //time complexity - O(n)
    private void postOrderRec(Node root){
        if(root != null){
            postOrderRec(root.getLeft());
            postOrderRec(root.getRight());
            System.out.print(root.getData() + ",");
        }
    }

    /*
    Iterative postorder traversal is more complex than the other two traversals (due to its nature of non-tail recursion, there is an extra statement after the final recursive call
    to itself).

    The postorder traversal can easily be done using two stacks though. The idea is to push reverse postorder traversal to a stack. Once we have reverse postorder traversal in a
    stack, we can just pop all items one by one from the stack and print them, this order of printing will be in postorder because of LIFO property of stacks.

    Now the question is, how to get reverse post order elements in a stack – the other stack is used for this purpose. For example, in the following tree,
                    1
                   / \
                  2   3
                 / \ / \
                4 5 6  7
    we need to get 1, 3, 7, 6, 2, 5, 4 in a stack. If take a closer look at this sequence, we can observe that this sequence is very similar to preorder traversal.
    The only difference is right child is visited before left child and therefore sequence is “root right left” instead of “root left right”. So we can do something like iterative
    preorder traversal with following differences.
    a) Instead of printing an item, we push it to a stack.
    b) We push left subtree before right subtree.
     */

    private void postOrderIt2Stacks(Node root){
        if(root == null) {
            return;
        }
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        List<Integer> result = new ArrayList<>();
        Node curr = root;
        s1.push(curr);
        while (!s1.isEmpty()){
            Node node = s1.pop();
            s2.push(node);
            if(node.getLeft() != null){
                s1.push(node.getLeft());
            }
            if(node.getRight() != null){
                s1.push(node.getRight());
            }
        }
        while (!s2.isEmpty()){
            result.add(s2.pop().getData());
        }
        System.out.println(result);

    }


    /*
    In post order traversal, each node is visited twice. Once after processing the left sub tree, we visit the current node and again after processing the right subtree, we visit
    the same current node, but we print it only after completing the right subtree. The problem is how to differentiate whether node is visited after processing left subtree or
    right subtree.We use a previous variable to keep track of the earlier traversed node.

    If previous is current's parent, we are going down the tree.In this case we traverse current's left child if available. If it is not available, we look at current's right
    child. If both left and right child are null ie leaf node, we print current's value and pop it off the stack.

    If previous is current's left child, we are traversing up the tree from the left.In this case we look at current's right child,if it is available then traverse down the right
    child else print current's value and pop it off the stack.

    If previous is current's right child, we are traversing up the tree from the right.In this case we print current's value and pop it off the stack.

                    1
                   / \
                  2   3
                 / \ / \
                4  5 6  7

     */
    private void postOrderItSingleStack(Node root){
        if(root == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        Node prev = null;
        stack.push(root);
        while (!stack.isEmpty()){
            Node current = stack.peek();
            /* go down the tree in search of a leaf an if so process it and pop stack otherwise move down */
            if (prev == null || prev.getLeft() == current ||
                    prev.getRight() == current) {
                if (current.getLeft() != null) {
                    stack.push(current.getLeft());
                }
                else if (current.getRight() != null){
                    stack.push(current.getRight());
                }
                else {
                    stack.pop();
                    result.add(current.getData());
                }
            }
            /* go up the tree from left node, if the child is right push it onto stack otherwise process parent and pop stack */
            else if (current.getLeft() == prev) {
                if(current.getRight() != null){
                    stack.push(current.getRight());
                }
                else {
                    stack.pop();
                    result.add(current.getData());
                }

            }
            /* go up the tree from right node and after coming back from right node process parent and pop stack */
            else if (current.getRight() == prev) {
                stack.pop();
                result.add(current.getData());
            }
            prev = current;
        }
        System.out.println(result);
    }

    public void height(){
        //System.out.println(heightRec(createBinaryTree()));
        //System.out.println(heightIt(createBinaryTree()));
        /*
                    1
                   / \
                  2   3
                 / \
                4  5
                  / \
                 6  7

         height = 4

         */
        Node root = new Node(1);
        root.setLeft(new Node(2));
        root.setRight(new Node(3));
        root.getLeft().setLeft(new Node(4));
        root.getLeft().setRight(new Node(5));
        root.getLeft().getRight().setLeft(new Node(6));
        root.getLeft().getRight().setRight(new Node(7));
        System.out.println(heightItStack(root));
    }

    //time complexity - O(n)
    private int heightRec(Node root){
        if(root == null){
            return 0;
        }
        int h1 = heightRec(root.getLeft());
        int h2 = heightRec(root.getRight());
        return 1 + Math.max(h1,h2);
    }

    //time complexity - O(n)
    private int heightIt(Node root){
        int height = 0;
        if(root == null){
            return height;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);
        while (!queue.isEmpty()){
            Node temp = queue.poll();
            if(temp != null){
                if(temp.getLeft() != null){
                    queue.offer(temp.getLeft());
                }
                if(temp.getRight() != null){
                    queue.offer(temp.getRight());
                }
            }
            else {
                height++;
                if(!queue.isEmpty()){
                    queue.offer(null);
                }
            }
        }
        return height;
    }

  /*
   * height of a tree using single stack. Using postorder single stack traversal logic. Time and
   * space complexity - O(n)
   */
  private int heightItStack(Node root) {
    if (root == null) {
      return 0;
    }
    if (root.getLeft() == null && root.getRight() == null) {
      return 1;
    }
    int height = 1;
    Stack<Node> stack = new Stack<>();
    stack.push(root);
    Node prev = null;
    List<Integer> postorder = new ArrayList<>();
    while (!stack.isEmpty()) {
      Node curr = stack.peek();
      if (prev == null || prev.getLeft() == curr || prev.getRight() == curr) {
        if (curr.getLeft() != null) {
          stack.push(curr.getLeft());
        } else if (curr.getRight() != null) {
          stack.push(curr.getRight());
        } else {
            stack.pop();
            postorder.add(curr.getData());
        }
      } else if (curr.getLeft() == prev) {
        if (curr.getRight() != null) {
          stack.push(curr.getRight());
        } else {
            stack.pop();
            postorder.add(curr.getData());
        }
      } else if (curr.getRight() == prev) {
          stack.pop();
          postorder.add(curr.getData());
          height++;
      }
      prev = curr;
    }
    System.out.println(postorder);
    return height;
  }

    public void levelorder(){
        levelOrderRec(createBinaryTree());
        System.out.println();
        levelOrderIt(createBinaryTree());
    }

    //time complexity - O(n^2)
    private void levelOrderRec(Node root){
        if(root == null){
            return;
        }
        int ht = heightRec(root);
        for (int i = 1; i<=ht ; i++){
            printGivenLevel(root,i);
        }
    }

    private void printGivenLevel(Node root , int level){
        if(root == null){
            return;
        }
        if(level == 1){
            System.out.print(root.getData() + ",");
        }
        else if(level > 1) {
            printGivenLevel(root.getLeft(), level - 1);
            printGivenLevel(root.getRight(), level - 1);
        }
    }

    //time complexity - O(n)
    private void levelOrderIt(Node root){
        if(root == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);
        int level = 1;
        HashMap<Integer,ArrayList<Integer>> result = new HashMap<>();
        while (!queue.isEmpty()){
            Node temp = queue.poll();
            if(temp == null){
                level++;
                if(!queue.isEmpty()){
                    queue.offer(null);
                }
                continue;
            }
            else if(result.containsKey(level)){
                ArrayList<Integer> data = result.get(level);
                data.add(temp.getData());
                result.put(level,data);
            }
            else {
                ArrayList<Integer> data = new ArrayList<>();
                data.add(temp.getData());
                result.put(level,data);
            }
            if(temp.getLeft() != null){
                queue.offer(temp.getLeft());
            }
            if(temp.getRight() != null){
                queue.offer(temp.getRight());
            }
        }
        System.out.println(result);
    }

    /*
                1
               / \
              2   3
             / \
            4  5
    Reverse Level order traversal of the above tree is “4 5 2 3 1”.
     */
    public void reverseLevelOrder(){
        reverseLevelOrderRec(createBinaryTree());
        System.out.println();
        reverseLevelOrderIt(createBinaryTree());
    }

    private void reverseLevelOrderRec(Node root){
        if(root == null){
            return;
        }
        int height = heightRec(root);
        for(int i = height ; i >= 1 ; i--){
            printGivenLevel(root, i);
        }
    }

    private void reverseLevelOrderIt(Node root){
        if(root == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        Stack<Integer> stack = new Stack();
        queue.offer(root);
        while (!queue.isEmpty()){
            Node temp = queue.poll();
            if(temp.getLeft() != null){
                queue.offer(temp.getLeft());
            }
            if(temp.getRight() != null){
                queue.offer(temp.getRight());
            }
            stack.push(temp.getData());
        }
        while (!stack.isEmpty()){
            System.out.print(stack.pop() + ",");
        }
    }


    /*
        Write a function to print spiral order traversal of a tree. For below tree, function should print 1, 2, 3, 4, 5, 6, 7.
                                        1
                                       / \
                                      2   3

                                     / \ / \
                                    7  6 5 4
     */
    public void spiralLevelOrder(){
        spiralLevelOrderRec(createBinaryTree());

    }

    private void spiralLevelOrderRec(Node root){
        if(root == null){
            return;
        }
        int height = heightRec(root);
        boolean flag = true;
        for(int i = 1 ; i < height ; i++){
            printGivenLevelSpiral(root, i, flag);
            flag = !flag;
        }
    }

    private void printGivenLevelSpiral(Node root , int level, boolean flag){
        if(root == null){
            return;
        }
        if(level == 1){
            System.out.print(root.getData() + ",");
        }
        else if(level > 1) {
            if(flag){
                printGivenLevelSpiral(root.getLeft(),level-1, flag);
                printGivenLevelSpiral(root.getRight(),level-1, flag);
            }
            else {
                printGivenLevelSpiral(root.getRight(),level-1, flag);
                printGivenLevelSpiral(root.getLeft(),level-1, flag);
            }
        }
    }

    //give an algo for finding maximum element in a binary tree
    public void findMaxElementInBT(){
        Node root = new Node(1);
        root.setLeft(new Node(2));
        root.setRight(new Node(3));
        root.getLeft().setLeft(new Node(4));
        root.getLeft().setRight(new Node(5));
        root.getRight().setLeft(new Node(60));
        root.getRight().setRight(new Node(7));
        root.getLeft().getLeft().setLeft(new Node(81));
        root.getLeft().getLeft().setRight(new Node(900));
        root.getRight().getRight().setLeft(new Node(100));
        root.getRight().getRight().setRight(new Node(11));
        root.getRight().getRight().getRight().setRight(new Node(12));
        System.out.println(findMaxElementInBTRec(root));
        root = new Node(1);
        root.setLeft(new Node(2));
        root.setRight(new Node(3));
        root.getLeft().setLeft(new Node(4));
        root.getLeft().setRight(new Node(5));
        root.getRight().setLeft(new Node(60));
        root.getRight().setRight(new Node(7));
        root.getLeft().getLeft().setLeft(new Node(81));
        root.getLeft().getLeft().setRight(new Node(900));
        root.getRight().getRight().setLeft(new Node(100));
        root.getRight().getRight().setRight(new Node(11));
        root.getRight().getRight().getRight().setRight(new Node(12));
        System.out.println(findMaxElementInBTIt(root));
    }

    //time complexity - O(n)
    private int findMaxElementInBTRec(Node root){
        int maxVal = Integer.MIN_VALUE;
        if(root != null){
            int leftMax = findMaxElementInBTRec(root.getLeft());
            int rightMax = findMaxElementInBTRec(root.getRight());
            if(leftMax > rightMax){
                maxVal = leftMax;
            }
            else {
                maxVal = rightMax;
            }
            if(root.getData() > maxVal){
                maxVal = root.getData();
            }
        }
        return maxVal;
    }

    //time complexity - O(n)
    // traverse in any order and find the max
    private int findMaxElementInBTIt(Node root){
        int maxVal = Integer.MIN_VALUE;
        if(root != null) {
            //doing preOrder traversal
            Stack<Node> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()){
                Node temp = stack.pop();
                if(temp.getData() >= maxVal){
                    maxVal = temp.getData();
                }
                if(temp.getRight() != null){
                    stack.push(temp.getRight());
                }
                if(temp.getLeft() != null){
                    stack.push(temp.getLeft());
                }
            }
        }
        return maxVal;
    }

    //search an element in binary tree
    public void searchElementInBT(){
        System.out.println(searchElementRec(createBinaryTree(),6));
    }

    //time complexity o(n)
    private boolean searchElementRec(Node root, int data){
        if(root == null){
            return false;
        }
        if(root.getData() == data){
            return true;
        }
        return searchElementRec(root.getLeft(),data) || searchElementRec(root.getRight(),data);
    }

    //find the size of the binary tree
    public void size(){
        System.out.println(size(createBinaryTree()));
    }

    //time complexity O(n)
    private int size(Node root){
        if(root == null){
            return 0;
        }
        return 1 + size(root.getLeft()) + size(root.getRight());
    }

    /*
    Given a binary tree, find its minimum depth. The minimum depth is the number of nodes along the shortest path from root node down to the nearest leaf node.
                     1
                   /   \
                  2     3
                /  \
               4    5
              / \
             6   7


                     1
                   /
                  2
                /  \
               4    5
              / \
             6   7

             Minimum depth = 3
     */
    public void minimumDepth(){
        Node root = new Node(1);
        root.setLeft(new Node(2));
        root.setRight(new Node(3));
        root.getLeft().setLeft(new Node(4));
        root.getLeft().setRight(new Node(5));
        root.getLeft().getLeft().setLeft(new Node(6));
        root.getLeft().getLeft().setRight(new Node(7));

        Node root1 = new Node(1);
        root1.setLeft(new Node(2));
        root1.getLeft().setLeft(new Node(4));
        root1.getLeft().setRight(new Node(5));
        root1.getLeft().getLeft().setLeft(new Node(6));
        root1.getLeft().getLeft().setRight(new Node(7));

        Node root2 = new Node(1);

        System.out.println(minimumDepthRec(root));
        System.out.println(minimumDepthRec(root1));
        System.out.println(minimumDepthRec(root2));
        System.out.println(minDepthRec1(root));
        System.out.println(minDepthRec1(root1));
        System.out.println(minDepthRec1(root2));


        //for iterative version, do a level order traversal and mainatin a count of levels and break the loop when the first leaf node is encountered.
    }

    /*
    Algo:- For every node, check if it is a leaf node. If yes, then return 1. If not leaf node then if left subtree is NULL,then recur for right subtree. And if right subtree is
    NULL, then recur for left subtree. If both left and right subtrees are not NULL, then take the minimum of two heights.
    time complexity O(n)
     */
    private int minimumDepthRec(Node root){
        if(root == null){
            return 0;
        }
        if(root.getLeft() == null && root.getRight() == null){
            return 1;
        }
        if(root.getRight() == null){
            return minimumDepthRec(root.getLeft()) + 1;
        }
        if(root.getLeft() == null){
            return minimumDepthRec(root.getRight()) + 1;
        }
        return Math.min(minimumDepthRec(root.getLeft()),minimumDepthRec(root.getRight())) + 1;
    }

    private int minDepthRec1(Node root){
        if(root == null){
            return 0;
        }
        if(root.getLeft() == null && root.getRight() == null){
            return 1;
        }
        int leftDepth = root.getLeft() == null ? Integer.MAX_VALUE : 1 + minimumDepthRec(root.getLeft());
        int rightDepth = root.getRight() == null ? Integer.MAX_VALUE : 1+ minimumDepthRec(root.getRight());
        return Math.min(leftDepth,rightDepth);
    }


    /*
    Given a binary tree, find the deep­est node in it.
    Examples:
    Input :
            1
          /   \
         2      3
        / \    / \
       4   5  6   7
                   \
                    8
    Output : 8

    Input :
            1
          /   \
         2      3
               /
              6
    Output : 6
     */

    //iterative version can be done using level order traversal, the last node processed in queue will be the deepest node.
    //
    public void deepestNodeInBT(){
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;
        deepestNodeRec(createTestingTree(),0,result);
        System.out.println("level:" + result[0]);
        System.out.println("deepest node:" + result[1]);
    }

    public Node createTestingTree(){
        /*Node root = new Node(1);
        root.setLeft(new Node(2));
        root.setRight(new Node(3));
        root.getLeft().setLeft(new Node(4));
        root.getLeft().setRight(new Node(5));
        root.getRight().setLeft(new Node(6));
        root.getRight().setRight(new Node(7));
        root.getRight().getRight().setRight(new Node(8));*/

        Node root = new Node(1);
        root.setLeft(new Node(2));
        root.setRight(new Node(3));
        root.getRight().setLeft(new Node(6));

        return root;

    }

    /*
    do an inorder traversal of given binary tree. While doing Inorder traversal, we pass level of current node also. We keep track of maximum level seen so far and value of
    deepest node seen so far.
     */

    /*
             Input : Root of below tree
            1
          /   \
         2      3
        / \    / \
       4   5  6   7
                   \
                    8
Output : 8

Input : Root of below tree
            1
          /   \
         2      3
               /
              6
Output : 6

 */



    private void deepestNodeRec(Node root,int level,int [] result){
        if(root != null){
            deepestNodeRec(root.getLeft(),++level,result);
            if(level > result[0]){
                result[0] = level;
                result[1] = root.getData();
            }
            deepestNodeRec(root.getRight(),++level,result);
        }
    }

    /*
    count the number of leaf nodes
     */
    public void countLeafNodes(){
        Node root = new Node(1);
        root.setLeft(new Node(2));
        root.setRight(new Node(3));
        root.getLeft().setLeft(new Node(4));
        root.getLeft().setRight(new Node(5));
        root.getLeft().getLeft().setLeft(new Node(6));
        root.getLeft().getLeft().setRight(new Node(7));
        System.out.println(countLeafNodesRec(root));
    }

    private int countLeafNodesRec(Node root){
        if(root != null){
            if(root.getLeft() == null && root.getRight() == null){
                return 1;
            }
            return countLeafNodesRec(root.getLeft()) + countLeafNodesRec(root.getRight());
        }
        else {
            return 0;
        }

    }


    /*
    Check if 2 trees are identical
     */
    public void checkIdentical(){
        Node root = new Node(1);
        root.setLeft(new Node(2));
        root.setRight(new Node(3));
        root.getLeft().setLeft(new Node(4));
        root.getLeft().setRight(new Node(5));
        root.getLeft().getLeft().setLeft(new Node(6));
        root.getLeft().getLeft().setRight(new Node(7));

        Node root1 = new Node(1);
        root1.setLeft(new Node(2));
        root1.setRight(new Node(3));
        root1.getLeft().setLeft(new Node(4));
        root1.getLeft().setRight(new Node(5));
        root1.getLeft().getLeft().setLeft(new Node(6));
        root1.getLeft().getLeft().setRight(new Node(7));
        System.out.println(checkIdentical(root,root1));

    }

    private boolean checkIdentical(Node root1, Node root2){
        if(root1 == null && root2 == null){
            return true;
        }
        if (root1 != null && root2 != null) {
            return root1.getData() == root2.getData() && checkIdentical(root1.getLeft(), root2.getLeft()) && checkIdentical(root1.getRight(), root2.getRight());
        }
        return false;
    }


    /*
    find the diameter of a tree
    The diameter of a tree (sometimes called the width) is the number of nodes on the longest path between two leaves in the tree.

    The diameter of a tree T is the largest of the following quantities:
    * the diameter of T’s left subtree
    * the diameter of T’s right subtree
    * the longest path between leaves that goes through the root of T (this can be computed from the heights of the subtrees of T)

                                        1
                                       / \
                                      2   3
                                     / \   \
                                    4  5    8
                                      / \    \
                                     6  7     9
                                             / \
                                            10 11
                                           / \
                                         12  13

                                         Diameter, 9 nodes through 12-rooot-7
    */
    /*
    Diameter of a tree can be calculated by only using the height function, because the diameter of a tree is nothing but maximum value of (left_height + right_height + 1) for
    each node. So we need to calculate this value (left_height + right_height + 1) for each node and update the result.
    */
    //time complexity - O(n)
    public void diameter(){
        int[] result = new int[1];
        result [0] = -1;
        height(createBinaryTree(),result);
        System.out.println(result[0]);
    }

    private int height(Node root, int[] result){
        if(root == null){
            return 0;
        }
        int leftHeight = height(root.getLeft(),result);
        int rightHeight = height(root.getRight(),result);

        result[0] = Math.max(result[0], leftHeight + rightHeight + 1);
        return 1 + Math.max(leftHeight,rightHeight);
    }


    /*
    Maximum width of a binary tree
    Given a binary tree, write a function to get the maximum width of the given tree. Width of a tree is maximum of widths of all levels.

    Let us consider the below example tree.

         1
        /  \
       2    3
     /  \     \
    4    5     8
              /  \
             6    7
    For the above tree,
    width of level 1 is 1,
    width of level 2 is 2,
    width of level 3 is 3
    width of level 4 is 2.

    So the maximum width of the tree is 3.
     */
    public void width(){
        Node root = new Node(1);
        root.setLeft(new Node(2));
        root.setRight(new Node(3));
        root.getLeft().setLeft(new Node(4));
        root.getLeft().setRight(new Node(5));
        root.getRight().setLeft(new Node(9));
        root.getRight().setRight(new Node(8));
        root.getRight().getRight().setLeft(new Node(6));
        root.getRight().getRight().setRight(new Node(7));
        widthUsingLevelOrderRec(root);
        System.out.println(widthUsingPreorder(root));
    }

    //Time complexity - O(n^2)
    // it can be done in O(n) using queue level order
    private void widthUsingLevelOrderRec(Node root){
        if(root == null){
            return;
        }
        int height = heightRec(root);
        int[] result = new int[1];
        int width = 0;
        for(int i = 1; i <= height ; i++){
            result[0] = 0;
            findWidthAtEachLevel(root,i,result);
            width = Math.max(width,result[0]);
        }
        System.out.println(width);
    }

    private void findWidthAtEachLevel(Node root, int level, int[] width){
        if(root == null){
            return;
        }
        if(level == 1){
            width[0] = width[0] + 1;
        }
        findWidthAtEachLevel(root.getLeft(),level-1,width);
        findWidthAtEachLevel(root.getRight(),level-1,width);

    }

    /*
    In this method we create a temporary array count[] of size equal to the height of tree. We initialize all values in count as 0. We traverse the tree using preorder traversal
    and fill the entries in count so that the count array contains count of nodes at each level in Binary Tree.
    Time Complexity = O(n)
     */
    private int widthUsingPreorder(Node root){
        if(root == null){
            return 0;
        }
        int[] count = new int[heightRec(root)];
        widthPreorder(root,count,0);
        return findMax(count);
    }

    private void widthPreorder(Node root, int[] count, int level){
        if(root != null){
            count[level]++;
            widthPreorder(root.getLeft(),count,level+1);
            widthPreorder(root.getRight(),count,level+1);
        }
    }

    private int findMax(int[] arr){
        if(arr.length <= 0){
            return -1;
        }
        else {
            int max = arr[0];
            for(int i: arr){
                if(i > max){
                    max = i;
                }
            }
            return max;
        }
    }


    /*
    Given a binary tree, print all root-to-leaf paths

    For the below example tree, all root-to-leaf paths are:
            10
           /  \
          8    2
         / \    \
        3  5 `  2
    10 –> 8 –> 3
    10 –> 8 –> 5
    10 –> 2 –> 2
    */
    public void printAllRootToLeafPath(){
        int[] path = new int[1000];
        Node root = new Node(1);
        root.setLeft(new Node(2));
        root.setRight(new Node(3));
        root.getLeft().setLeft(new Node(4));
        root.getLeft().setRight(new Node(5));
        root.getRight().setLeft(new Node(9));
        root.getRight().setRight(new Node(8));
        root.getRight().getRight().setLeft(new Node(6));
        root.getRight().getRight().setRight(new Node(7));
        //rootToLeafPath(root,path,0);
        rootToLeafPathIt(root);
    }

    private void rootToLeafPathIt(Node root){
        if(root == null || (root.getLeft() == null && root.getRight() == null)){
            return;
        }
        int path[] = new int[100];
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        int level = 0;
        while (!stack.isEmpty()){
            Node node = stack.pop();
            if(node == null){
                level--;
                continue;
            }
            path[level] = node.getData();
            if(node.getLeft() == null && node.getRight() == null){
                printPath(path,level);
            }
            else {
                //to track level
                stack.push(null);
                if(node.getRight() != null){
                    stack.push(node.getRight());
                }
                if(node.getLeft() != null){
                    stack.push(node.getLeft());
                }
                level ++;
            }
        }
    }

    private void rootToLeafPath(Node root, int[] path, int level){
        if(root != null){
            path[level] = root.getData();
            level ++;
            if(root.getLeft() == null && root.getRight() == null){
                printPath(path);
            }
            else {
                rootToLeafPath(root.getLeft(),path,level);
                rootToLeafPath(root.getRight(),path,level);
            }
        }
    }

    private void printPath(int[] path){
        StringBuilder sb = new StringBuilder("");
        for(int i : path){
            if(i == 0){
                break;
            }
            sb.append(i + "->");
        }
        System.out.println(sb.delete(sb.length()-2,sb.length()));
    }

    private void printPath(int[] path,int level){
        StringBuilder sb = new StringBuilder("");
        for(int i : path){
            if(i == 0 || level < 0){
                break;
            }
            level--;
            sb.append(i + "->");
        }
        System.out.println(sb.delete(sb.length()-2,sb.length()));
    }

    /*
    Given a binary tree, write a function that returns true if the tree satisfies below property.
    For every node, data value must be equal to sum of data values in left and right children. Consider data value as 0 for NULL children. Below tree is an example
                10
               /  \
              8    2
             / \    \
            5  3     2


     */
    public void childSumProperty(){
        Node root = new Node(10);
        root.setLeft(new Node(8));
        root.setRight(new Node(2));
        root.getLeft().setLeft(new Node(5));
        root.getLeft().setRight(new Node(3));
        root.getRight().setRight(new Node(2));
        System.out.println(childSumProperty(root));
    }


    /*
    preorder traversal:- Check for root and then left and right subtree
     */
    private boolean childSumProperty(Node root){
        if(root == null || (root.getLeft() == null && root.getRight() == null)){
            return true;
        }
        int leftData = root.getLeft() != null ? root.getLeft().getData() : 0;
        int rightData = root.getRight() != null ? root.getRight().getData() : 0;
        if(root.getData() == leftData + rightData && childSumProperty(root.getLeft()) && childSumProperty(root.getRight())){
            return true;
        }
        return false;
    }

    /*
    convert an arbitrary binary tree to a tree that holds child sum property. You can only increment data values in any node (You cannot change structure of tree and cannot
    decrement value of any node).
                    50
                   / \
                  7   2
                 / \ / \
                3  5 1  30

                [14, 19, 5, 50, 1, 31, 30]
     */
    public void convertBinaryTreeToHoldChildSumProperty(){
        Node root = new Node(50);
        root.setLeft(new Node(7));
        root.setRight(new Node(2));
        root.getLeft().setLeft(new Node(3));
        root.getLeft().setRight(new Node(5));
        root.getRight().setLeft(new Node(1));
        root.getRight().setRight(new Node(30));
        System.out.println("Inorder Traversal before conversion:");
        inOrderIt(root);
        convertTreeIt(root);
        System.out.println("Inorder Traversal after conversion:");
        inOrderIt(root);
    }


    //time complexity - O(n)
    private void convertTree(Node root){
        if(root == null || (root.getLeft() == null && root.getRight() == null)){
            return;
        }
        convertTree(root.getLeft());
        convertTree(root.getRight());
        int left = root.getLeft() != null ? root.getLeft().getData() : 0;
        int right = root.getRight() != null ? root.getRight().getData() : 0;
        int diff =  (left + right) - root.getData();
        if(diff > 0){
            root.setData(root.getData() + diff);
        }
        if(diff < 0){
            increment(root, -diff);
        }
    }

    private void convertTreeIt(Node root){
        if(root == null || (root.getLeft() == null && root.getRight() == null)){
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node prev = null;
        stack.push(root);
        while (!stack.isEmpty()){
            Node curr = stack.peek();
            if(prev == null || prev.getLeft() == curr || prev.getRight() == curr){
                if(curr.getLeft() != null){
                    stack.push(curr.getLeft());
                }
                else if(curr.getRight() != null){
                    stack.push(curr.getRight());
                }
                else {
                    stack.pop();
                }
            }
            else if(curr.getLeft() == prev){
                if(curr.getRight() != null){
                    stack.push(curr.getRight());
                }
                else {
                    Node node = stack.pop();
                    int left = node.getLeft() != null ? node.getLeft().getData() : 0;
                    int right = node.getRight() != null ? node.getRight().getData() : 0;
                    int diff = (left + right) - node.getData();
                    if(diff > 0){
                        node.setData(node.getData() + diff);
                    }
                    else if(diff < 0){
                        incrementIt(node,-diff);
                    }
                }
            }
            else if(curr.getRight() == prev){
                Node node = stack.pop();
                int left = node.getLeft() != null ? node.getLeft().getData() : 0;
                int right = node.getRight() != null ? node.getRight().getData() : 0;
                int diff = (left + right) - node.getData();
                if(diff > 0){
                    node.setData(node.getData() + diff);
                }
                else if(diff < 0){
                    incrementIt(node,-diff);
                }

            }
            prev = curr;
        }
    }

    private void increment(Node root, int diff){
        if(root.getLeft() != null){
            root.getLeft().setData(root.getLeft().getData() + diff);
            increment(root.getLeft(),diff);
        }
        else if(root.getRight() != null){
            root.getRight().setData(root.getRight().getData() + diff);
            increment(root.getRight(),diff);
        }
    }

    private void incrementIt(Node root, int diff){
        if(root == null){
            return;
        }
        Node curr = root;
        while (curr.getLeft() != null || curr.getRight() != null){
            if(curr.getLeft() != null){
                curr = curr.getLeft();
            }
            else if(curr.getRight() != null){
                curr = curr.getRight();
            }
            curr.setData(curr.getData() + diff);
        }
    }

    /*
    Given a sum, find whether there exist a path from root to any node with given sum.
                    2
                   / \
                  7   2
                 / \ / \
                3  5 8  10

                sum = 14
     */
    public void findPathWithGivenSum(){
        Node root = new Node(2);
        root.setLeft(new Node(7));
        root.setRight(new Node(2));
        root.getLeft().setLeft(new Node(3));
        root.getLeft().setRight(new Node(5));
        root.getRight().setLeft(new Node(8));
        root.getRight().setRight(new Node(10));
        int[] path = new int[255];
        //findPathWithGivenSum(root,path,14,0);
        //findPathWithGivenSum(root,path,12,0);
        //findPathWithGivenSum(root,path,9,0);
    }

    private void checkAndPrintPath(int[] path,int sum,int level){
        StringBuilder sb = new StringBuilder("");
        for(int i=0;i<=level;i++){
            sum = sum - path[i];
            sb.append(path[i] + "->");
        }
        if(sum == 0){
            sb.deleteCharAt(sb.length()-1);
            sb.deleteCharAt(sb.length()-1);
            System.out.println(sb);
        }
    }

    private void findPathWithGivenSum(Node root, int[] path, int sum, int level){
        if(root != null) {
            path[level] = root.getData();
            checkAndPrintPath(path,sum,level);
            findPathWithGivenSum(root.getLeft(), path, sum, level + 1);
            findPathWithGivenSum(root.getRight(), path, sum, level + 1);
        }
    }


    /*
    Mirror image of a tree

                    2
                   / \
                  7   6
                 / \ / \
                3  5 8  10

     Mirror image :-
                    2
                   / \
                  6   7
                 / \ / \
                10 8 5  3
     */
    public void mirrorImageOfTree(){
        Node root = new Node(2);
        root.setLeft(new Node(7));
        root.setRight(new Node(6));
        root.getLeft().setLeft(new Node(3));
        root.getLeft().setRight(new Node(5));
        root.getRight().setLeft(new Node(8));
        root.getRight().setRight(new Node(10));
        preOrderRec(root);
        System.out.println();
        //mirrorRec(root);
        mirrorIt(root);
        preOrderRec(root);

    }

    // do any of the post order or preorder tree traversal and swap the nodes.Here we are doing post-order traversal
    private void mirrorRec(Node root){
        if(root != null){
            mirrorRec(root.getLeft());
            mirrorRec(root.getRight());
            Node temp = root.getLeft();
            root.setLeft(root.getRight());
            root.setRight(temp);

        }
    }

    private void mirrorIt(Node root){
        if(root != null){
            Stack<Node> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()){
                Node node = stack.pop();
                if(node.getRight() != null){
                    stack.push(node.getRight());
                }
                if(node.getLeft() != null){
                    stack.push(node.getLeft());
                }
                Node temp = node.getLeft();
                node.setLeft(node.getRight());
                node.setRight(temp);

            }
        }
    }

    /*
    check whether given trees are mirror image of each other

                    1
                   / \
                  2   3
                 / \ / \
                4  56   7

                    1
                   / \
                  3   2
                 / \ / \
                7  65   4


     */
    public void checkMirror(){
        Node root = new Node(1);
        root.setLeft(new Node(2));
        root.setRight(new Node(3));
        root.getLeft().setLeft(new Node(4));
        root.getLeft().setRight(new Node(5));
        root.getRight().setLeft(new Node(6));
        root.getRight().setRight(new Node(7));

        Node root1 = new Node(1);
        root1.setLeft(new Node(3));
        root1.setRight(new Node(2));
        root1.getLeft().setLeft(new Node(7));
        root1.getLeft().setRight(new Node(6));
        root1.getRight().setLeft(new Node(5));
        root1.getRight().setRight(new Node(4));

        System.out.println(checkMirror(root,root1));


    }

  private boolean checkMirror(Node root1, Node root2) {
    if (root1 == null && root2 == null) {
      return true;
    }
    if (root1 == null || root2 == null) {
      return false;
    }
    return root1.getData() == root2.getData() && checkMirror(root1.getLeft(), root2.getRight())
        && checkMirror(root1.getRight(), root2.getLeft());
  }

    /*
    Contruct a binary tree using given inorder and preorder traversal.
    Inorder sequence: D B E A F C
    Preorder sequence: A B D E C F

    In a Preorder sequence, leftmost element is the root of the tree. So we know ‘A’ is root for given sequences. By searching
    ‘A’ in Inorder sequence, we can find out all elements on left side of ‘A’ are in left subtree and elements on right are in
    right subtree. So we know below structure now.

                 A
               /   \
             /       \
           D B E     F C

    We recursively follow above steps and get the following tree.

         A
       /   \
     /       \
    B         C
   / \        /
 /     \    /
D       E  F


     */
    public void constructBinaryTreeFromInorderAndPreorderTraversal(){
        int[] pre = {1,2,4,5,3,6,7};
        int [] in = {4,2,5,1,6,3,7};
        if(pre.length != in.length){
            return;
        }
        postOrderRec(constructBTFromInAndPre(pre,in,0,in.length-1));

    }

    private int searchElementInArray(int[] arr, int data){
        for(int i =0; i< arr.length ; i++){
            if(arr[i] == data){
                return i;
            }
        }
        throw new RuntimeException("Tree cannot be formed with given sequence");
    }
    //time complexity O(n^2)
    private Node constructBTFromInAndPre(int[] pre, int[] in, int inStart, int inEnd){
        if(inStart > inEnd){
            return null;
        }
        Node root = new Node(pre[preIndex]);
        preIndex++;
        if(inStart == inEnd){
            return root;
        }
        int inIndex = searchElementInArray(in,root.getData());
        root.setLeft(constructBTFromInAndPre(pre,in,inStart,inIndex-1));
        root.setRight(constructBTFromInAndPre(pre,in,inIndex+1,inEnd));
        return root;
    }


    /*
    Contruct a binary tree using given inorder and postorder traversal.
     */
    public void constructBinaryTreeFromInorderAndPostorderTraversal(){
        int[] post = {4,5,2,6,7,3,1};
        int [] in = {4,2,5,1,6,3,7};
        if(post.length != in.length){
            return;
        }
        preOrderRec(constructBTFromInAndPost(post,in,0,in.length-1,new int[]{post.length-1}));

    }

    //time complexity O(n^2)
    private Node constructBTFromInAndPost(int[] post, int[] in, int inStart, int inEnd,int[] postIndex){
        if(inStart > inEnd){
            return null;
        }
        Node root = new Node(post[postIndex[0]]);
        postIndex[0]--;
        if(inStart == inEnd){
            return root;
        }
        int inIndex = searchElementInArray(in,root.getData());
        //we recursively call for right subtree before left subtree since we are traversing postorder sequence from end so right subtree will come before left.
        root.setRight(constructBTFromInAndPost(post,in,inIndex+1,inEnd,postIndex));
        root.setLeft(constructBTFromInAndPost(post,in,inStart,inIndex-1,postIndex));
        return root;
    }


    /*
    Print Ancestors of a given node in Binary Tree

    For example, if the given tree is following Binary Tree and key is 7, then your function should print 4, 2 and 1.


              1
            /   \
          2      3
        /  \
      4     5
     /
    7
   */

    public void printAllAncestors(){
        Node root = new Node(1);
        root.setLeft(new Node(2));
        root.setRight(new Node(3));
        root.getLeft().setLeft(new Node(4));
        root.getLeft().setRight(new Node(5));
        root.getLeft().getLeft().setLeft(new Node(7));
        printAncestors(root,7);
    }

    private boolean printAncestors(Node root, int data){
        if(root == null){
            return false;
        }
        if(root.getData() == data){
            return true;
        }
        if(printAncestors(root.getLeft(),data) || printAncestors(root.getRight(),data)){
            System.out.print(root.getData() + " ");
            return true;
        }
        return false;
    }

    /*
    Vertical Sum in a given Binary Tree

    find vertical sum of the nodes that are in same vertical line. Print all sums through different vertical lines.
    Examples:

      1
    /   \
  2      3
 / \    / \
4   5  6   7
The tree has 5 vertical lines

Vertical-Line-1 has only one node 4 => vertical sum is 4
Vertical-Line-2: has only one node 2=> vertical sum is 2
Vertical-Line-3: has three nodes: 1,5,6 => vertical sum is 1+5+6 = 12
Vertical-Line-4: has only one node 3 => vertical sum is 3
Vertical-Line-5: has only one node 7 => vertical sum is 7

So expected output is 4, 2, 12, 3 and 7
     */
    public void verticalSumTree(){
        Node root = new Node(1);
        root.setLeft(new Node(2));
        root.setRight(new Node(3));
        root.getLeft().setLeft(new Node(4));
        root.getLeft().setRight(new Node(5));
        root.getRight().setLeft(new Node(6));
        root.getRight().setRight(new Node(7));
        /*
        Map<Integer,List<Integer>> verticalMap = new HashMap<>();
        verticalSumTree(verticalMap,root,0);
        System.out.println(verticalMap);
        */
        ListNode listNode = new ListNode();
        verticalSumTreeSpaceOptimized(root,listNode);
        while (listNode.prev != null){
            listNode = listNode.prev;
        }
        while (listNode != null){
            System.out.println(listNode.data);
            listNode = listNode.next;
        }
    }

    /*
    Hashing Solution
    We need to check the Horizontal Distances from root for all nodes. If two nodes have the same Horizontal Distance (HD), then they are on same vertical line.
    The idea of HD is simple. HD for root is 0, a right edge (edge connecting to right subtree) is considered as +1 horizontal distance and a left edge is considered as -1
    horizontal distance. For example, in the given tree, HD for Node 4 is at -2, HD for Node 2 is -1, HD for 5 and 6 is 0 and HD for node 7 is +2.
     */
    private void verticalSumTree(Map<Integer,List<Integer>> verticalMap, Node root, int verticalLevel){
        if(root != null){
            List<Integer> nodes = new ArrayList<>();
            nodes.add(root.getData());
            if(verticalMap.containsKey(verticalLevel)){
                nodes.addAll(verticalMap.get(verticalLevel));
            }
            verticalMap.put(verticalLevel,nodes);
            verticalSumTree(verticalMap,root.getLeft(),verticalLevel-1);
            verticalSumTree(verticalMap,root.getRight(),verticalLevel+1);
        }
    }

    /*
    Hashing based solution requires a Hash Table to be maintained. We know that hashing requires more space than the number of entries in it.
    Doubly Linked List based solution is discussed. The solution discussed here requires only n nodes of linked list where n is total number of vertical lines in binary tree.
     */
    private void verticalSumTreeSpaceOptimized(Node root, ListNode lNode){
        lNode.data = lNode.data + root.getData();
        if(root.getLeft() != null){
            if(lNode.prev == null){
                lNode.prev = new ListNode();
                lNode.prev.next = lNode;
            }
            verticalSumTreeSpaceOptimized(root.getLeft(),lNode.prev);
        }
        if(root.getRight() != null){
            if(lNode.next == null){
                lNode.next = new ListNode();
                lNode.next.prev = lNode;
            }
            verticalSumTreeSpaceOptimized(root.getRight(),lNode.next);
        }
    }


    /*
    Find the path of the given node from root in a tree.

      1
    /   \
  2      3
 / \    / \
4   5  6   7
     */
    public void findPath(){
        Node root = new Node(1);
        root.setLeft(new Node(2));
        root.setRight(new Node(3));
        root.getLeft().setLeft(new Node(4));
        root.getLeft().setRight(new Node(5));
        root.getRight().setLeft(new Node(6));
        root.getRight().setRight(new Node(7));
        ArrayList<Integer> path = new ArrayList<>();
        if(findPath(root,path,6)){
            System.out.print(path);
            path.clear();
        }
        if(findPath(root,path,2)){
            System.out.print(path);
            path.clear();
        }
        if(findPath(root,path,5)){
            System.out.print(path);
            path.clear();
        }
    }

    private boolean findPath(Node root, List<Integer> path, int n){
        if(root == null){
            return false;
        }
        path.add(root.getData());
        if(root.getData() == n){
            return true;
        }
        if(root.getLeft() != null && findPath(root.getLeft(),path,n)){
            return true;
        }
        if(root.getRight() != null && findPath(root.getRight(),path,n)){
            return true;
        }
        path.remove(path.size() -1);
        return false;
    }



    /*
    Lowest Common Ancestor in a Binary Tree
    Let T be a rooted tree.The lowest common ancestor between two nodes n1 and n2 is defined as the lowest node in T that has both n1 and n2 as descendants (where we allow a node
    to be a descendant of itself).The LCA of n1 and n2 in T is the shared ancestor of n1 and n2 that is located farthest from the root.
    Application:- Computation of lowest common ancestors may be useful, for instance, as part of a procedure for determining the distance between pairs of nodes in a tree:
    the distance from n1 to n2 can be computed as the distance from the root to n1, plus the distance from the root to n2, minus twice the distance from the root to their
    lowest common ancestor.

     Example:-

      1
    /   \
  2      3
 / \    / \
4   5  6   7

      LCA(4,5) = 2
      LCA(4,6) = 1
      LCA(3,4) = 1
      LCA(2,4) = 2
     */
    public void lowestCommonAncestorInBinaryTree(){
        Node root = new Node(1);
        root.setLeft(new Node(2));
        root.setRight(new Node(3));
        root.getLeft().setLeft(new Node(4));
        root.getLeft().setRight(new Node(5));
        root.getRight().setLeft(new Node(6));
        root.getRight().setRight(new Node(7));
        /*
        lca1(root,4,5);
        lca1(root,4,6);
        lca1(root,3,4);
        lca1(root,2,4);
        */

        /*
        Node lca = lca2(root,4,5);
        System.out.println("lca2(root,4,5):" + lca.getData());
        lca = lca2(root,4,6);
        System.out.println("lca2(root,4,6):" + lca.getData());
        lca = lca2(root,3,4);
        System.out.println("lca2(root,3,4):" + lca.getData());
        lca = lca2(root,2,4);
        System.out.println("lca2(root,2,4):" + lca.getData());
        lca = lca2(root,5,8);
        // lca will be 5 as only 5 is present, ideally it should be -1 or not found
        System.out.println("lca2(root,5,8):" + lca.getData());
        lca = lca2(root,10,8);
        // lca will be null here.
        //System.out.println("lca2(root,10,8):" + lca.getData());
        */
        lca3(root);
    }

    /*
    Method 1 (By Storing root to n1 and root to n2 paths):
Following is simple O(n) algorithm to find LCA of n1 and n2.
1) Find path from root to n1 and store it in a vector or array.
2) Find path from root to n2 and store it in another vector or array.
3) Traverse both paths till the values in arrays are same. Return the common element just before the mismatch.

Time Complexity: Time complexity of the above solution is O(n). The tree is traversed twice, and then path arrays are compared.
     */
    private void lca1(Node root,int n1,int n2){
        List<Integer> pathN1 = new ArrayList<>();
        List<Integer> pathN2 = new ArrayList<>();
        if(findPath(root,pathN1,n1) && findPath(root,pathN2,n2)){
            System.out.println("Path n1:" + pathN1);
            System.out.println("Path n2:" + pathN2);
            int i = 0;
            while(i < pathN1.size() && i < pathN2.size() && pathN1.get(i) == pathN2.get(i)){
                i++;
            }
            System.out.println("LCA:" + pathN1.get(i-1));
        }
    }


    /*
    Single traversal and O(n) complexity.Method assumes that both n1 and n2 are present in the tree.
    The idea is to traverse the tree starting from root. If any of the given keys (n1 and n2) matches with root, then root is LCA (assuming that both keys are present).
    If root doesn’t match with any of the keys, we recur for left and right subtree. The node which has one key present in its left subtree and the other key present in right
    subtree is the LCA. If both keys lie in left subtree, then left subtree has LCA also, otherwise LCA lies in right subtree.

    Note that the above method assumes that keys are present in Binary Tree. If one key is present and other is absent, then it returns the present key as LCA (Ideally should have
    returned NULL).We can extend this method to handle all cases by passing two boolean variables v1 and v2. v1 is set as true when n1 is present in tree and v2 is set as true if
    n2 is present in tree.
     */
    private Node lca2(Node root,int n1,int n2){
        if(root == null){
            return null;
        }
        //if root matches with any of the key, then root is the lca(assuming both the keys are present in the tree)
        if(root.getData() == n1 || root.getData() == n2){
            return root;
        }
        // Look for keys in left and right subtrees
        Node leftLca = lca2(root.getLeft(),n1,n2);
        Node rightLca = lca2(root.getRight(),n1,n2);

        /*
        if both leftLca and rightLca are not null,then one key is present in the left subtree and other in the right subtree,so the current node is LCA.
         */
        if(leftLca != null && rightLca != null){
            return root;
        }
        // Otherwise check if left subtree or right subtree is LCA
        return leftLca != null ? leftLca : rightLca;
    }


    /*
    handles key not present case
     */
    private void lca3(Node root){
        v1 = false;
        v2 = false;
        Node lca = lca3(root,4,5);
        if(v1 && v2){
            System.out.println("LCA:" + lca.getData());
        }
        v1 = false;
        v2 = false;
        lca = lca3(root,4,9);
        if(v1 && v2){
            System.out.println("LCA:" + lca.getData());
        }
        v1 = false;
        v2 = false;
        lca = lca3(root,10,9);
        if(v1 && v2){
            System.out.println("LCA:" + lca.getData());
        }
    }

    private Node lca3(Node root, int n1, int n2){
        if(root == null){
            return null;
        }
        if(root.getData() == n1){
            v1 = true;
            return root;
        }
        if(root.getData() == n2){
            v2 = true;
            return root;
        }

        Node leftLca = lca3(root.getLeft(),n1,n2);
        Node rightLca = lca3(root.getRight(),n1,n2);

        if(leftLca != null && rightLca != null){
            return root;
        }
        return leftLca != null ? leftLca : rightLca;
    }


    /*
      1
    /   \
  2      3
 / \    / \
4   5  6   7

     */

    class LcaNode{
        int data;
        LcaNode left;
        LcaNode right;
        LcaNode lca1;
        LcaNode lca2;

        public LcaNode(int data){
            this.data = data;
        }
    }


    public void lcaItTest(){
        LcaNode root = new LcaNode(1);
        root.left = new LcaNode(2);
        root.right = new LcaNode(3);
        root.left.left = new LcaNode(4);
        root.left.right = new LcaNode(5);
        root.right.left = new LcaNode(6);
        root.right.right = new LcaNode(7);
        root.left.right.left = new LcaNode(8);
        root.left.right.right = new LcaNode(9);
        root.left.right.left.right = new LcaNode(10);
        root.left.right.right.left = new LcaNode(11);

        //lcaIterative(root,4,5);
        //lcaIterative(root,6,7);
        //lcaIterative(root,2,3);
        //lcaIterative(root,4,3);
        lcaIterative(root,10,11);
        //lcaIterative(root,4,11);
        //lcaIterative(root,2,4);


    }


    public void lcaIterative(LcaNode root,int n1,int n2){
        Stack<LcaNode> postorderStack = new Stack<>();
        postorderStack.push(root);
        LcaNode prev = null;
        while (!postorderStack.isEmpty()){
            LcaNode curr = postorderStack.peek();
            if(prev == null || prev.left == curr || prev.right == curr){
                if(curr.left != null){
                    postorderStack.push(curr.left);
                }
                else if(curr.right != null){
                    postorderStack.push(curr.right);
                }
                else {
                    if(curr.data == n1){
                        curr.lca1 = curr;
                    }
                    else if(curr.data == n2){
                        curr.lca2 = curr;
                    }
                    postorderStack.pop();
                }
            }
            if(curr.left == prev){
                if(prev.lca1 != null){
                    curr.lca1 = prev.lca1;
                }
                if(prev.lca2 != null){
                    curr.lca2 = prev.lca2;
                }

                if(curr.lca1 != null && curr.lca2 != null){
                    System.out.println(curr.data);
                    break;
                }
                if(curr.right != null){
                    postorderStack.push(curr.right);
                }
                else {
                    if(curr.data == n1){
                        curr.lca1 = curr;
                    }
                    else if(curr.data == n2){
                        curr.lca2 = curr;
                    }
                    postorderStack.pop();
                }
            }
            if(curr.right == prev){
                if(prev.lca1 != null){
                    curr.lca1 = prev.lca1;
                }
                if(prev.lca2 != null){
                    curr.lca2 = prev.lca2;
                }
                if(curr.data == n1){
                    curr.lca1 = curr;
                }
                else if(curr.data == n2){
                    curr.lca2 = curr;
                }
                postorderStack.pop();
                if(curr.lca1 != null && curr.lca2 != null){
                    System.out.println(curr.data);
                    break;
                }

            }
            prev = curr;
        }
    }

    /*
    Given a tree with special property where all the nodes have either 0 or 2 childs and a leaf node is identified by 0 and a non leaf node is identified by 1.
    given a preorder traversal of the tree, construct the tree out of it.

    preorder - 1,0,1,0,0

            1
           / \
          0  1
            / \
           0  0
     */
    public void constructTreeFromPreorderArray01(){
        int [] preorder = {1,0,1,0,0};
        Node root = constructTreeFromPreorder(preorder,0);
        inOrderRec(root);
    }

    private Node constructTreeFromPreorder(int[] preorder, int index){
        if(preorder.length == 0){
            return null;
        }
        //boundary condition
        if(index >= preorder.length){
            return null;
        }
        Node root = new Node(preorder[index]);
        //if leaf node, return the node and move up the tree
        if(root.getData() == 0){
            return root;
        }
        //else set the node left and right child recurrsively
        index = index + 1;
        root.setLeft(constructTreeFromPreorder(preorder,index));
        index = index + 1;
        root.setRight(constructTreeFromPreorder(preorder,index));
        return root;
    }
    /*
    Connect nodes at same level
Write a function to connect all the adjacent nodes at the same level in a binary tree. Structure of the given Binary Tree node is like following.

struct node{
  int data;
  struct node* left;
  struct node* right;
  struct node* nextRight;
}
Initially, all the nextRight pointers point to garbage values. Your function should set these pointers to point next right for each node.

Example

Input Tree
       A
      / \
     B   C
    / \   \
   D   E   F


Output Tree
       A--->NULL
      / \
     B-->C-->NULL
    / \   \
   D-->E-->F-->NULL

             1
          /    \
        2        3
       / \      /  \
      4   5    6    7
     / \           / \
    8   9        10   11

   */
    public void connectNodesAtSameLevel(){
        /*
        Node root = new Node(1);
        root.setLeft(new Node(2));
        root.setRight(new Node(3));
        root.getLeft().setLeft(new Node(4));
        root.getLeft().setRight(new Node(5));
        root.getLeft().getLeft().setLeft(new Node(8));
        root.getLeft().getLeft().setRight(new Node(9));
        root.getRight().setLeft(new Node(6));
        root.getRight().setRight(new Node(7));
        root.getRight().getRight().setLeft(new Node(10));
        root.getRight().getRight().setRight(new Node(11));
        connectNodesAtSameLevelUsingLevelOrder(root);
        printTreeWithNextRight(root);
        */
        /*
        Node root = new Node(10);
        root.setLeft(new Node(8));
        root.setRight(new Node(2));
        root.getLeft().setLeft(new Node(3));
        connectNodesAtSameLevelUsingPreOrder(root);
        printTreeWithNextRight(root);
        */
        /*
        Node root = new Node(1);
        root.setLeft(new Node(2));
        root.setRight(new Node(3));
        root.getLeft().setLeft(new Node(4));
        root.getLeft().setRight(new Node(5));
        root.getLeft().getLeft().setLeft(new Node(8));
        root.getLeft().getLeft().setRight(new Node(9));
        root.getRight().setLeft(new Node(6));
        root.getRight().setRight(new Node(7));
        root.getRight().getRight().setLeft(new Node(10));
        root.getRight().getRight().setRight(new Node(11));
        connectNodeAtSameLevelConstantSpace(root);
        printTreeWithNextRight(root);
        */
        Node root = new Node(1);
        root.setLeft(new Node(2));
        root.setRight(new Node(3));
        root.getLeft().setLeft(new Node(4));
        root.getLeft().setRight(new Node(5));
        root.getLeft().getLeft().setLeft(new Node(8));
        root.getLeft().getLeft().setRight(new Node(9));
        root.getRight().setLeft(new Node(6));
        root.getRight().setRight(new Node(7));
        root.getRight().getRight().setLeft(new Node(10));
        root.getRight().getRight().setRight(new Node(11));
        connectNodeAtSameLevelRec(root);
    }

    private void connectNodesAtSameLevelUsingLevelOrder(Node root){
        if(root == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);
        while (!queue.isEmpty()){
            Node node = queue.poll();
            if(node != null){
                node.setNextRight(queue.peek());
                if(node.getLeft() != null){
                    queue.offer(node.getLeft());
                }
                if(node.getRight() != null){
                    queue.offer(node.getRight());
                }
            }
            else {
                if(!queue.isEmpty()){
                    queue.offer(null);
                }
            }
        }
    }


    /*
    Only works for complete binary tree.
    In case of incomplete binary tree it will not work because here nextRight of 9 will be set as NULL which is incorrect.
             1
          /    \
        2        3
       / \      /  \
      4  5     6    7
     / \           / \
    8   9->null   10   11

    Sample Input:-
             10
            /  \
          8     2
         /
        3
     */
    private void connectNodesAtSameLevelUsingPreOrder(Node root){
        if(root != null){
            if(root.getLeft() != null){
                root.getLeft().setNextRight(root.getRight());
            }
            if(root.getRight() != null){
                root.getRight().setNextRight(root.getNextRight() != null ? root.getNextRight().getLeft() : null);
            }
            connectNodesAtSameLevelUsingPreOrder(root.getLeft());
            connectNodesAtSameLevelUsingPreOrder(root.getRight());
        }
    }

    private void printTreeWithNextRight(Node root){
        if(root == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);
        while (!queue.isEmpty()){
            Node node = queue.poll();
            if(node != null){
                System.out.println(node);
                if(node.getLeft() != null){
                    queue.offer(node.getLeft());
                }
                if(node.getRight() != null){
                    queue.offer(node.getRight());
                }
            }
            else {
                if(!queue.isEmpty()){
                    queue.offer(null);
                }
            }

        }
    }



    /*
    https://www.youtube.com/watch?v=OLvzM1ZcbtQ
    Here we traverse the nextRight node before the left and right children,then we can make sure that all nodes at level i have the nextRight set, before the level i+1 nodes.
    Let us consider the following example.
             1
          /    \
        2        3
       / \      /  \
      4  5     6    7
     / \           / \
    8   9        10   11

    The preorder solution fails for right child of node 4. In this method, we make sure that all nodes at the 4’s level (level 2) have nextRight set, before we try to set the
    nextRight of 9. So when we set the nextRight of 9, we search for a nonleaf node on right side of node 4.

    Here we use 2 nested loop. The outer loop, goes through all the levels and the inner loop goes through all the nodes at every level.

    Algo:-

     method getNextRight(Node p){
        this method should return the leftmost child of the nextRight node of p(Node at same level of p).
     }

     method connect(Node p) {   //pass root to this method
        while(p is not null) { //this loop goes through all levels
            Node q = p;
            while (q is not null) { // this loop goes through all the nodes at each level
                if(q.left is not null){
                    // if q has a right child, then q.left.nextRight is q.right else q.left.nextRight = getNextRight(q)
                }
                if(q.right is not null) {
                    q.nextRight = getNextRight(q)
                }
                q = q.nextRight
            }
            // start from the first node of next level
            if(p.left is not null)
                p = p.left
            else if(p.right is not null)
                p = p.right
            else
                p = getNextRight(p)
        }
     }
     */
    //Time Complexity - O(n), Space complexity - O(1)
    private void connectNodeAtSameLevelConstantSpace(Node root){
        if(root == null){
            return;
        }
        Node p = root;
        p.setNextRight(null);
        while (p != null){
            Node q = p;
            while (q != null){
                if(q.getLeft() != null){
                    if(q.getRight() != null){
                        q.getLeft().setNextRight(q.getRight());
                    }
                    else {
                        q.getLeft().setNextRight(getNextRight(q));
                    }
                }
                if(q.getRight() != null){
                    q.getRight().setNextRight(getNextRight(q));
                }
                // point q to the next node at same level
                q = q.getNextRight();
            }
            //point p to the first node of next level
            if(p.getLeft() != null){
                p = p.getLeft();
            }
            else if(p.getRight() != null){
                p = p.getRight();
            }
            else {
                p = getNextRight(p);
            }
        }
    }

    //returns the leftmost child of the nextRight Node of p
    private Node getNextRight(Node p){
        Node temp = p.getNextRight();
        while(temp != null){
            if(temp.getLeft() != null){
                return temp.getLeft();
            }
            if(temp.getRight() != null){
                return temp.getRight();
            }
            temp = temp.getNextRight();
        }
        return null;
    }

    //Time O(n), Space - O(n) for recurrsion stack
    private void connectNodeAtSameLevelRec(Node root){
        if(root != null){
            System.out.println("Entering Recurrsion stack:"+root.getData());
            if(root.getNextRight() != null){
                //setting next right before traversing the down the level
                connectNodeAtSameLevelRec(root.getNextRight());
            }
            if(root.getLeft() != null){
                if(root.getRight() != null){
                    System.out.println("Setting nextRight for " + root.getLeft().getData() + " as " + root.getRight().getData());
                    root.getLeft().setNextRight(root.getRight());
                    Node nextRight = getNextRight(root);
                    System.out.println("Setting nextRight for " + root.getRight().getData() + " as " + (nextRight != null ? nextRight.getData() : -1));
                    root.getRight().setNextRight(nextRight);
                }
                else {
                    Node nextRight = getNextRight(root);
                    System.out.println("Setting nextRight for " + root.getLeft().getData() + " as " + (nextRight != null ? nextRight.getData() : -1));
                    root.getLeft().setNextRight(nextRight);
                }
                //recuursively call for left subtree.Note that call to left subtree will also handles setting nextRight of right child.
                connectNodeAtSameLevelRec(root.getLeft());
            }
            else if(root.getRight() != null){
                Node nextRight = getNextRight(root);
                System.out.println("Setting nextRight for " + root.getRight().getData() + " as " + (nextRight != null ? nextRight.getData() : -1));
                root.getRight().setNextRight(nextRight);
                connectNodeAtSameLevelRec(root.getRight());
            }
            else {
                connectNodeAtSameLevelRec(getNextRight(root));
            }
            System.out.println("Exiting Recurrsion stack:"+root.getData());
        }
    }








    /*
    given a postfix expression of a expression tree, construct the expression tree
            /
          /  \
        +     4
       / \
      1   *
         / \
        2   3

        postfix = 123*+4/
   */
    public void constructExpressionTreeFromPostfix(){
        String postfix = "ab+ef*g*-";
        char[] charArray = postfix.toCharArray();
        ExpressionNode root = constructExpressionTreeFromPostfixExpression(charArray);
        inorderExpressionTree(root);
    }

    private boolean isOperator(char c){
        if (c == '+' || c == '-'
                || c == '*' || c == '/'
                || c == '^') {
            return true;
        }
        return false;
    }

    private void inorderExpressionTree(ExpressionNode root){
        if(root != null){
            inorderExpressionTree(root.left);
            System.out.print(root.value + ",");
            inorderExpressionTree(root.right);
        }
    }

    /*
    in case of prefix expression, traverse the array from right to left and set the left child first and then right
     */
    private ExpressionNode constructExpressionTreeFromPostfixExpression(char[] postfix){
        ExpressionNode t,t1,t2;
        Stack<ExpressionNode> stack = new Stack<>();
        for(char c : postfix){
            if(!isOperator(c)){
                t = new ExpressionNode(c);
                stack.push(t);
            }
            else {
                t = new ExpressionNode(c);
                t1 = stack.pop();
                t2 = stack.pop();
                //notice the ordering, its because its postfix expression
                t.right = t1;
                t.left = t2;
                stack.push(t);
            }
        }
        //  only element will be root of expression tree
        return stack.pop();
    }


    /*
    convert infix expression to postfix
    Algorithm
1. Scan the infix expression from left to right.
2. If the scanned character is an operand, output it.
3. Else,
…..3.1 If the precedence of the scanned operator is greater than the precedence of the operator in the stack(or the stack is empty), push it.
(precedence(+,-) < precedence(*,/) and precedence(+) = precedence(-) and precedence(*) = precedence(/)
…..3.2 Else, Pop the operator from the stack until the precedence of the scanned operator is less-equal to the precedence of the operator residing on the top of the stack.
 Push the scanned operator to the stack.
6. Repeat steps 2-6 until infix expression is scanned.
7. Pop and output from the stack until it is not empty.
     */
    public String infixToPostfix(char[] infix){
        String postfix = "";
        Stack<Character> stack = new Stack<>();
        for(char c : infix){
            if(!isOperator(c)){
                postfix += c;
            }
            else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())){
                    postfix += stack.pop();
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()){
            postfix += stack.pop();
        }
        return postfix;
    }

    public int precedence(char c){
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1;
     }

    static int maxLevel = -1;

    void leftView() {
        Node root = new Node(10);
        root.setLeft(new Node(20));
        root.setRight(new Node(30));
        root.getLeft().setLeft(new Node(40));
        root.getLeft().setRight(new Node(60));
        leftView(root);
    }

    void leftView(Node root)
    {
        leftView(root,0);
    }

    void leftView(Node root, int level){
        if(root!= null){
            if(level > maxLevel){
                System.out.print(root.getData() + " ");
                maxLevel = level;
            }
            leftView(root.getLeft(), level+1);
            leftView(root.getRight(), level+1);
        }
    }









    public static void main(String[] args) {
        Trees tree = new Trees();
        //tree.preorder();
        //tree.cloneTree();
        //tree.inorder();
        //tree.postorder();
        //tree.height();
        //tree.levelorder();
        //tree.reverseLevelOrder();
        //tree.spiralLevelOrder();
        //tree.findMaxElementInBT();
        //tree.searchElementInBT();
        //tree.size();
        //tree.minimumDepth();
        //tree.deepestNodeInBT();
        //tree.countLeafNodes();
        //tree.checkIdentical();
        //tree.diameter();
        //tree.width();
        //tree.printAllRootToLeafPath();
        //tree.childSumProperty();
        //tree.convertBinaryTreeToHoldChildSumProperty();
        //tree.findPathWithGivenSum();
        //tree.mirrorImageOfTree();
        //tree.checkMirror();
        //tree.constructBinaryTreeFromInorderAndPreorderTraversal();
        //tree.constructBinaryTreeFromInorderAndPostorderTraversal();
        //tree.printAllAncestors();
        //tree.verticalSumTree();
        //tree.findPath();
        //tree.lowestCommonAncestorInBinaryTree();
        //tree.constructTreeFromPreorderArray01();
        //tree.connectNodesAtSameLevel();
        //tree.constructExpressionTreeFromPostfix();
        //System.out.print(tree.infixToPostfix("a+b*c".toCharArray()));
        //tree.lcaItTest();
        tree.leftView();
    }
}
