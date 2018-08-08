import java.util.Stack;

class LinkedListNode{
    public LinkedListNode getNext() {
        return next;
    }

    public void setNext(LinkedListNode next) {
        this.next = next;
    }

    public LinkedListNode(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    private LinkedListNode next;
    private int data;
}

class DoublyListNode {

    private int data;

    private DoublyListNode next;

    private DoublyListNode prev;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public DoublyListNode getNext() {
        return next;
    }

    public DoublyListNode(int data) {
        this.data = data;
    }

    public void setNext(DoublyListNode next) {
        this.next = next;
    }

    public DoublyListNode getPrev() {
        return prev;
    }

    public void setPrev(DoublyListNode prev) {
        this.prev = prev;
    }
}


public class BinarySearchTree {

    private Node root = null;

    private static Node prev = null;

    private static LinkedListNode head = null;

    private static DoublyListNode head1 = null;

    private static Node predecessor = null;

    private static Node successor = null;

    private static Node floor = null;

    private static Node celeing = null;

    public void insert(int[] arr){
        for (int data : arr){
            //root = insertRec(root,data);
            root = insertIt(root,data);
        }
        //inorder(root);

    }

    private Node insertRec(Node root, int data){
        if(root == null){
            root = new Node(data);
        }
        else {
            if(data > root.getData()){
                root.setRight(insertRec(root.getRight(),data));
            }
            else if(data < root.getData()){
                root.setLeft(insertRec(root.getLeft(),data));
            }
        }
        return root;
    }

    private Node insertIt(Node root, int data){
        if(root == null){
            root = new Node(data);
        }
        else {
            Node curr = root;
            while (curr != null){
                if(data > curr.getData()){
                    if(curr.getRight() == null){
                        curr.setRight(new Node(data));
                        break;
                    }
                    curr = curr.getRight();
                }
                else if(data < curr.getData()){
                    if(curr.getLeft() == null){
                        curr.setLeft(new Node(data));
                        break;
                    }
                    curr = curr.getLeft();
                }
            }
        }
        return root;
    }

    public void delete(){
        insert(new int[] {10,3,12,2,7,6,8,4,5});
        inorder(root);
        deleteRec(root,7);
        System.out.println();
        inorder(root);
    }

    /*
    Let x be the node to be deleted.
    1) if x is a leaf, make x as null
       if x has only one child, point x = x->child
    2) if x has 2 childrens
       temp = find the max element in the left subtree of x
       swap data of x with temp and delete temp
     */
    private Node deleteRec(Node root, int data){
        if(root == null){
            return null;
        }
        if(data > root.getData()){
            root.setRight(deleteRec(root.getRight(),data));
        }
        else if(data < root.getData()){
            root.setLeft(deleteRec(root.getLeft(),data));
        }
        else {
            //element found
            if(root.getLeft() != null && root.getRight() != null){
                Node temp = findMax(root.getLeft());
                //swap data of the max element in left sub tree with root's
                root.setData(temp.getData());
                //delete the max element in the left sub tree
                root.setLeft(deleteRec(root.getLeft(),root.getData()));
            }
            else {
                if(root.getLeft() == null){
                    root = root.getRight();
                }
                if(root.getRight() == null){
                    root = root.getLeft();
                }
            }
        }
        return root;
    }


    public void findMax(){
        insert(new int[] {10,4,12,7,5,8,6});
        System.out.println(findMax(root).getData());
    }

    private Node findMax(Node root){
        if(root == null){
            return null;
        }
        if(root.getRight() == null){
            return root;
        }
        return findMax(root.getRight());
    }

    private void inorder(Node root){
        if(root != null){
            inorder(root.getLeft());
            System.out.print(root.getData() + ",");
            inorder(root.getRight());
        }
    }

    public void lca(){
        insert(new int[] {20,8,22,4,12,10,14,});
        System.out.println("lca(root,10,14):"+lca(root,10,14).getData());
        System.out.println("lca(root,14,8):"+lca(root,14,8).getData());
        System.out.println("lca(root,10,22):"+lca(root,10,22).getData());
        System.out.println("lcaIt(root,10,14):"+lcaIt(root,10,14).getData());
        System.out.println("lcaIt(root,14,8):"+lcaIt(root,14,8).getData());
        System.out.println("lcaIt(root,10,22):"+lcaIt(root,10,22).getData());

    }

    /*
    LCA in BST:-
    Given values of two values n1 and n2 in a Binary Search Tree, find the Lowest Common Ancestor (LCA). You may assume that both the values exist in the tree.
            20
           / \
          8  22
         / \
        4  12
           / \
          10 14

    LCA of 10 and 14 is 12
    LCA of 14 and 8 is 8
    LCA of 10 and 22 is 20

    We can solve this problem using BST properties. We can recursively traverse the BST from root. The main idea of the solution is, while traversing from top to bottom,
    the first node n we encounter with value between n1 and n2, i.e., n1 < n < n2 or same as one of the n1 or n2, is LCA of n1 and n2 (assuming that n1 < n2).
    So just recursively traverse the BST in, if node’s value is greater than both n1 and n2 then our LCA lies in left side of the node, if it’s is smaller than both n1 and n2,
    then LCA lies on right side. Otherwise root is LCA (assuming that both n1 and n2 are present in BST)
     */
    //Time complexity - O(h), Space complexity - O(h)
    private Node lca(Node root, int n1, int n2){
        if(root == null){
            return null;
        }
        if(root.getData() > Math.max(n1,n2)){
            return lca(root.getLeft(),n1,n2);
        }
        if(root.getData() < Math.min(n1,n2)){
            return lca(root.getRight(),n1,n2);
        }
        return root;
    }

    //We can avoid extra recurrsion stack space using iterative solution.
    private Node lcaIt(Node root, int n1, int n2){
        Node curr = root;
        while (curr != null){
            if(curr.getData() > Math.max(n1,n2)){
                curr = curr.getLeft();
            }
            else if(curr.getData() < Math.min(n1,n2)){
                curr = curr.getRight();
            }
            else {
                break;
            }
        }
        return curr;
    }

    /*
    Check whether a given binary tree is a valid BST or not
            4
           / \
          2   5   isBst = true
         / \
        1   3

          6
         / \
        2   8  isBst = false as 9 is greater than 6
       / \
      1  9
     */
    public void isBST(){
        insert(new int[] {4,2,5,1,3});
        System.out.println("IsBST(4,2,5,1,3):" + isBst(root,Integer.MIN_VALUE,Integer.MAX_VALUE));
        System.out.println("IsBST(4,2,5,1,3):" + isBst(root));

    }

    private boolean isBst(Node root, int MIN, int MAX){
        if(root == null){
            return true;
        }
        return (root.getData() > MIN && root.getData() < MAX && isBst(root.getLeft(),MIN,root.getData()) && isBst(root.getRight(),root.getData(),MAX));
    }

    /*
    Using inorder traversal,since inorder traversal of a BST is always in sorted order.
     */
    private boolean isBst(Node root){
        if(root == null){
            return true;
        }
        //note that we cannot check like this - if(isBST(root.left)){return true} because it will never go up after completing the left sub tree.
        if(!isBst(root.getLeft())){
            return false;
        }
        if(prev != null && root.getData() < prev.getData()){
            return false;
        }
        prev = root;
        if(!isBst(root.getRight())){
            return false;
        }
        return true;

    }


    /*
    Convert a Binary Tree to a Circular Doubly Link List
    The left and right pointers in nodes are to be used as previous and next pointers respectively in converted
    Circular Linked List.
    The order of nodes in List must be same as Inorder of the given Binary Tree.
    The first node of Inorder traversal must be head node of the Circular List.

    Example :-
            10
           /  \
          12   15
         / \   /
        25 30 36

        25<->12<->30<->10<->36 <->15
        |_________________________|
     */
    public void bTreeToClist(){
        root = new Node(10);
        root.setLeft(new Node(12));
        root.setRight(new Node(15));
        root.getLeft().setLeft(new Node(25));
        root.getLeft().setRight(new Node(30));
        root.getRight().setLeft(new Node(36));
        inorder(root);
        System.out.println();
        root = bTreeToClist(root);
        //displayCList(root);

    }


    /*
            10
           /  \
          12   15
         / \   /
        25 30 36

     */

    private Node bTreeToClist(Node root){
        if(root == null){
            return null;
        }
        System.out.println("Entering Recurrsion stack for:"+root.getData());
        Node left = bTreeToClist(root.getLeft());
        Node right = bTreeToClist(root.getRight());

        //convert a single node to circular linked list
        root.setLeft(root);
        root.setRight(root);
        Node result = concat(concat(left,root),right);
        System.out.print("Converted to CLL:-");
        displayCList(result);
        System.out.println();
        System.out.println("Exiting Recurrsion stack for:"+root.getData());
        return result;
    }

    //Method to concat 2 circular linked list,returns head of the list
    private Node concat(Node left, Node right){
        if(left == null){
            return right;
        }
        if(right == null){
            return left;
        }
        Node leftLast = left.getLeft();
        Node rightLast = right.getLeft();

        leftLast.setRight(right);
        right.setLeft(leftLast);
        rightLast.setRight(left);
        left.setLeft(rightLast);
        return left;
    }

    private void displayCList(Node root){
        Node head = root;
        while (head.getRight() != root){
            System.out.print(head.getData() + "<->");
            head = head.getRight();
        }
        System.out.print(head.getData());
    }


    /*
    Sorted Linked List to Balanced BST

    Given a Singly Linked List which has data members sorted in ascending order. Construct a Balanced Binary Search Tree which has same data members as the given Linked List.

Examples:

Input:  Linked List 1->2->3
Output: A Balanced BST
     2
   /  \
  1    3


Input: Linked List 1->2->3->4->5->6->7
Output: A Balanced BST
        4
      /   \
     2     6
   /  \   / \
  1   3  4   7

Input: Linked List 1->2->3->4
Output: A Balanced BST
      3
    /  \
   2    4
 /
1

Input:  Linked List 1->2->3->4->5->6
Output: A Balanced BST
      4
    /   \
   2     6
 /  \   /
1   3  5
     */
    public void createBalancedBSTFromASortedLinkedList(){
        head = new LinkedListNode(1);
        head.setNext(new LinkedListNode(2));
        head.getNext().setNext(new LinkedListNode(3));
        head.getNext().getNext().setNext(new LinkedListNode(4));
        head.getNext().getNext().getNext().setNext(new LinkedListNode(5));
        head.getNext().getNext().getNext().getNext().setNext(new LinkedListNode(6));
        head.getNext().getNext().getNext().getNext().getNext().setNext(new LinkedListNode(7));
        head.getNext().getNext().getNext().getNext().getNext().getNext().setNext(new LinkedListNode(8));

        Node root = createBSTFromSinglyList(countNodes(head),"root");

    }

    private int countNodes(LinkedListNode head){
        LinkedListNode curr = head;
        int cnt = 0;
        while (curr != null){
            curr = curr.getNext();
            cnt++;
        }
        return cnt;
    }

    /*
     We construct the tree bottom up(from leaves to root).The idea is to insert nodes in BST in the same order as the appear in
     Linked List, so that the tree can be constructed in O(n) time complexity. We first count the number of nodes in the given
     Linked List. Let the count be n. After counting nodes, we take left n/2 nodes and recursively construct the left subtree.
     After left subtree is constructed, we allocate memory for root and link the left subtree with root. Finally, we recursively
     construct the right subtree and link it with root.
     While constructing the BST, we also keep moving the list head pointer to next so that we have the appropriate pointer in
     each recursive call.
     Time complexity: O(n) where n is the number of nodes in Linked List.
     */
    private Node createBSTFromSinglyList(int n,String subtree){
        if(n <= 0 ){
            return null;
        }
        System.out.println("Entering recursion stack for n = " + n + " for subtree :" + subtree);
        //recursively create the left sub tree
        Node left = createBSTFromSinglyList(n/2,"left");
        //create root
        System.out.println("Creating root:"+head.getData());
        Node root = new Node(head.getData());
        System.out.println("Setting left child:"+(left != null ? left.getData() : -1) + "for root :" + root.getData());
        root.setLeft(left);
        head = head.getNext() != null ? head.getNext() : head;
        //number of nodes in right subtree = n - number of nodes in left sub tree -1(for root)
        Node right = createBSTFromSinglyList((n - n/2 -1),"right");
        System.out.println("Setting right child:"+(right != null ? right.getData() : -1) + "for root :" + root.getData());
        root.setRight(right);
        System.out.print("Created tree as:");
        inorder(root);
        System.out.println();
        System.out.println("Exiting recursion stack for n = " + n + " for subtree :" + subtree);
        return root;
    }


    /*
    In-place conversion of Sorted DLL to Balanced BST
    Given a Doubly Linked List which has data members sorted in ascending order.
    Construct a Balanced Binary Search Tree which has same data members as the given Doubly Linked
    List. The tree must be constructed in-place (No new node should be allocated for tree conversion)

    Solution is almost similar to singly list to Balanced BST solution.
    Time complexity O(n). We are creating the tree in bottom up manner.
     */
    public void constructBalancedBSTFromASortedDoublyList(){
        push(7);
        push(6);
        push(5);
        push(4);
        push(3);
        push(2);
        push(1);
        System.out.println("Doubly Linked List:");
        printList(head1);
        System.out.println();
        DoublyListNode root = constructBalancedBSTFromASortedDoublyList(countNodes(head1));
        System.out.println("Preorder traversal of converted Balanced BST:");
        preOrder(root);

    }

    private DoublyListNode constructBalancedBSTFromASortedDoublyList(int n){
        if(n <= 0){
            return null;
        }
        DoublyListNode left = constructBalancedBSTFromASortedDoublyList(n/2);
        DoublyListNode root = new DoublyListNode(head1.getData());
        root.setPrev(left);
        head1 = head1.getNext();
        DoublyListNode right = constructBalancedBSTFromASortedDoublyList(n - n/2 -1);
        root.setNext(right);
        return root;
    }



    /* Function to print nodes in a given doubly linked list */
    private void printList(DoublyListNode head)
    {
        DoublyListNode node = head;
        while (node != null)
        {
            System.out.print(node.getData() + " ");
            node = node.getNext();
        }
    }

    /* Function to count nodes in a given doubly linked list */
    private int countNodes(DoublyListNode head)
    {
        DoublyListNode node = head;
        int cnt = 0;
        while (node != null)
        {
            cnt ++;
            node = node.getNext();
        }
        return cnt;
    }

    /* A utility function to print preorder traversal of BST using DoublyListNode */
    private void preOrder(DoublyListNode node)
    {
        if (node != null){
            System.out.print(node.getData() + " ");
            preOrder(node.getPrev());
            preOrder(node.getNext());
        }

    }


    /* Function to insert a node at the beginging of
       the Doubly Linked List */
    private void push(int data)
    {
        DoublyListNode node = new DoublyListNode(data);

        /* since we are adding at the begining,
           prev is always NULL */
        node.setPrev(null);

        /* link the old list off the new node */
        node.setNext(head1);

        /* change prev of head node to new node */
        if (head1 != null)
            head1.setPrev(node);

        /* move the head to point to the new node */
        head1 = node;
    }

    /*
    Given a sorted array, convert it to a BST

   1) Get the Middle of the array and make it root.
   2) Recursively do same for left half and right half.
      a) Get the middle of left half and make it left child of the root
          created in step 1.
      b) Get the middle of right half and make it right child of the
          root created in step 1.

   Algo is similar to binary search algo
   Time complexity - O(n)
     */
    public void sortedArrayToBst(){
        Node root = sortedArrayToBst(new int[] {1,2,3,4,5,6,7},0,6);
        inorder(root);
    }

    private Node sortedArrayToBst(int[] arr, int start, int end){
        if(start > end){
            return null;
        }
        Node root = new Node();
        if(start == end){
            root.setData(arr[start]);
            root.setLeft(null);
            root.setRight(null);
        }
        else {
            int mid = start + (end - start) / 2;
            root.setData(arr[mid]);
            root.setLeft(sortedArrayToBst(arr,start,mid-1));
            root.setRight(sortedArrayToBst(arr,mid + 1,end));
        }
        return root;
    }

    /*
    Find the kth smallest element in a Binary Search Tree
     */
    public void findKthSmallest(){
        insert(new int[] {1,2,3,4,5,6});
        System.out.println(findKthSmallest(root,3,0).getData());
    }

    //do inorder traversal and return the node when k == count
    private Node findKthSmallest(Node root, int k, int count){
        if(root == null){
            return null;
        }
        Node left = findKthSmallest(root.getLeft(),k,count);
        if(left != null){
            return left;
        }
        if(++count == k){
            return root;
        }
        return findKthSmallest(root.getRight(),k,count);
    }

    public void kthSmallest(){
        insert(new int[] {10,5,2,1,3,6,15,12,11,14,16});
        int k = 7;
        System.out.println(kthSmallest(root,k).getData());

        /*
        Method 2: Augmented  Tree Data Structure.

The idea is to maintain rank of each node. We can keep track of elements in a subtree of any node while building the tree. Since we need K-th smallest element, we can maintain
number of elements of left subtree in every node.

Assume that the root is having N nodes in its left subtree. If K = N + 1, root is K-th node. If K < N, we will continue our search (recursion) for the Kth smallest element in the left subtree of root. If K > N + 1, we continue our search in the right subtree for the (K – N – 1)-th smallest element. Note that we need the count of elements in left subtree only.

Time complexity: O(h) where h is height of tree.

Algorithm:

start:
if K = root.leftElement + 1
   root node is the K th node.
   goto stop
else if K > root.leftElements
   K = K - (root.leftElements + 1)
   root = root.right
   goto start
else
   root = root.left
   goto srart

stop:

         */
    }


    private Node kthSmallest(Node root, int k){
        Stack<Node> st = new Stack<>();
        int count = 0;
        Node kthSmallest = null;
        Node curr = root;
        st.push(curr);
        while (curr.getLeft() != null){
            curr = curr.getLeft();
            st.push(curr);
        }
        while (!st.isEmpty()){
            Node node = st.pop();
            count++;
            if(count == k){
                kthSmallest = node;
                break;
            }
            if(node.getRight() != null){
                node = node.getRight();
                st.push(node);
                while (node.getLeft() != null){
                    node = node.getLeft();
                    st.push(node);
                }
            }
        }
        return kthSmallest;
    }


    /*
    Floor and Ceil from a BST
    Ceil Value Node: Node with smallest data larger than or equal to key value.
    Floor Value Node: Node with largest data smaller than or equal to key value.
     */
    public void floorAndCeilInBST(){
        insert(new int[] {8,4,12,2,6,10,14});
        for(int i =0;i<=15;i++){
            System.out.println("Floor for i = " + i + " is :" + floor(root,i));
        }
        for(int i =0;i<=15;i++){
            System.out.println("Ceil for i = " + i + " is :" + ceil(root,i));
        }
    }

    /*
    A) Root data is equal to key. We are done, root data is ceil value.

B) Root data < key value, certainly the ceil value can’t be in left subtree. Proceed to search on right subtree as reduced problem instance.

C) Root data > key value, the ceil value may be in left subtree. We may find a node with is larger data than key value in left subtree, if not the root itself will be ceil node.
     */
    private int ceil(Node root, int key){
        if(root == null){
            return -1;
        }
        if(root.getData() == key){
            return root.getData();
        }
        // If root's key is smaller, ceil must be in right subtree
        if(root.getData() < key){
            return ceil(root.getRight(),key);
        }
        // Else, either left subtree or root has the ceil value
        int ceil = ceil(root.getLeft(),key);
        return ceil >= key ? ceil : root.getData();

    }

    private int floor(Node root, int key){
        if(root == null){
            return Integer.MAX_VALUE;
        }
        if(root.getData() == key){
            return root.getData();
        }
        if(root.getData() > key){
            return floor(root.getLeft(),key);
        }
        int floor = floor(root.getRight(),key);
        return floor <= key ? floor : root.getData();
    }




    /*
    Given a key find inorder predecessor and successor in BST.
     */
    public void inorderPreSuccInBST(){
        insert(new int[] {10,6,12,3,9,2,5,8,7});
        System.out.println("Inorder traversal of given tree:");
        inorder(root);
        inorderPreSuccInBST(root,6);
        System.out.println();
        System.out.println((predecessor != null) ? predecessor.getData() : -1);
        System.out.println((successor != null) ? successor.getData() : -1);
        predecessor = null;
        successor = null;
        inorderPreSuccInBST(root,5);
        System.out.println((predecessor != null) ? predecessor.getData() : -1);
        System.out.println((successor != null) ? successor.getData() : -1);
        predecessor = null;
        successor = null;
        inorderPreSuccInBST(root,12);
        System.out.println((predecessor != null) ? predecessor.getData() : -1);
        System.out.println((successor != null) ? successor.getData() : -1);
    }

    /*
    1. If root is NULL
      then return
2. if key is found then
    a. If its left subtree is not null
        Then predecessor will be the right most
        child of left subtree or left child itself.
    b. If its right subtree is not null
        The successor will be the left most child
        of right subtree or right child itself.
    return
3. If key is smaller then root node
        set the successor as root
        search recursively into left subtree
    else
        set the predecessor as root
        search recursively into right subtree
     */
    private void inorderPreSuccInBST(Node root, int key){
        if(root != null){
            if(root.getData() == key){
                if(root.getLeft() != null){
                    Node temp = root.getLeft();
                    while (temp.getRight() != null){
                        temp = temp.getRight();
                    }
                    predecessor = temp;
                }
                if(root.getRight() != null){
                    Node temp = root.getRight();
                    while (temp.getLeft() != null){
                        temp = temp.getLeft();
                    }
                    successor = temp;
                }
            }
            else {
                if(root.getData() > key){
                    successor = root;
                    inorderPreSuccInBST(root.getLeft(),key);
                }
                else if(root.getData() < key){
                    predecessor = root;
                    inorderPreSuccInBST(root.getRight(),key);
                }
            }
        }
    }




    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        //tree.insert(new int[] {10,4,12,7,5,8,6});
        //tree.findMax();
        //tree.delete();
        //tree.lca();
        //tree.isBST();
        //tree.bTreeToClist();
        //tree.createBalancedBSTFromASortedLinkedList();
        //tree.constructBalancedBSTFromASortedDoublyList();
        //tree.sortedArrayToBst();
        //tree.findKthSmallest();
        //tree.inorderPreSuccInBST();
        //tree.floorAndCeilInBST();
        tree.kthSmallest();
    }
}