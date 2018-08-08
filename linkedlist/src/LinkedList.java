import sun.org.mozilla.javascript.ast.WhileLoop;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by vaibhavmishra on 25/9/17.
 */
public class LinkedList {

    private int size;

    private Node head;

    private Node left;

    private static Node maxNode = null;

    public static int count = 0;

    public LinkedList(){
        this.size = 0;
        this.head = null;
    }

    public void insertAtBegining(int data){
        Node node = new Node(data);
        node.setNext(head);
        head = node;
        size++;
    }

    private int resetPosition(int position){
        if(position < 0){
            position = 0;
        }
        if(position >= this.getSize()-1){
            position = this.getSize()-1;
        }
        return position;
    }

    public int getSize(){
        return this.size;
    }

    public Node getHead(){
        return this.head;
    }

    public void insert(int data, int position){
        position = resetPosition(position);
        if(position == this.getSize()){
            insertAtEnd(data);
            return;
        }
        if(position == 0){
            insertAtBegining(data);
            return;
        }
        Node node = new Node(data);
        Node curr = head;
        for(int i=1;i<position;i++){
            curr = curr.getNext();
        }
        node.setNext(curr.getNext());
        curr.setNext(node);
        size++;
        return;
    }

    public void insertAtEnd(int data){
        Node node = new Node(data);
        if(head ==  null){
            head = node;
        }
        else {
            Node curr = head;
            while (curr.getNext() != null){
                curr = curr.getNext();
            }
            curr.setNext(node);
        }
        size++;
    }

    public void deleteAtBegining(){
        if(size <= 1){
            head = null;
        }
        else {
            Node next = head.getNext();
            head.setNext(null);
            head = next;
        }
        size--;
    }

    public void deleteAtEnd(){
        if(size <= 1){
            head = null;
        }
        else {
            Node curr = head;
            for(int i =0 ; i < size-1 ;i++){
                curr = curr.getNext();
            }
            curr.setNext(null);
        }
        size--;
    }

    public void remove(int position){
        position = resetPosition(position);
        if(head == null){
            return;
        }
        if(position == 0){
            deleteAtBegining();
            return;
        }
        if(position == this.getSize() - 1){
            deleteAtEnd();
            return;
        }
        Node curr = this.getHead();
        for(int i =1; i<position ; i++){
            curr = curr.getNext();
        }
        curr.setNext(curr.getNext().getNext());
        size--;
    }

    public void removeMatched(int data){
        if(head == null){
            return;
        }
        if(head.getData() == data){
            head = head.getNext();
            size--;
            return;
        }
        Node curr = getHead();
        Node next = curr.getNext();
        while(next != null){
            if(next != null && next.getData() == data){
                curr.setNext(next.getNext());
                next = null;
                break;
            }
            curr = curr.getNext();
            next = next.getNext();
        }
    }

    public void clear(){
        this.head = null;
        this.size = 0;
    }


    public int getPosition(int data){
        if(head == null){
            return -1;
        }
        Node curr = head;
        int position = 0;
        while(curr != null){
            if(curr.getData() == data){
                return position;
            }
            position++;
            curr = curr.getNext();
        }
        return -1;
    }

    public void print(){
        System.out.println();
        Node curr = head;
        while (curr != null){
            System.out.print(curr.getData());
            if(curr.getNext() != null){
                System.out.print(",");
            }
            curr = curr.getNext();
        }
        System.out.println();
    }

    public void print(Node node){
        System.out.println();
        Node curr = node;
        while (curr != null){
            System.out.print(curr.getData());
            if(curr.getNext() != null){
                System.out.print(",");
            }
            curr = curr.getNext();
        }
        System.out.println();
    }

    public void createLinkedList(){
        clear();
        head = new Node(1);
        head.setNext(new Node(2));
        head.getNext().setNext(new Node(3));
        head.getNext().getNext().setNext(new Node(4));
        head.getNext().getNext().getNext().setNext(new Node(5));
        head.getNext().getNext().getNext().getNext().setNext(new Node(6));

    }

    //given a linkedlist,find length of it recurrsive
    public void getLengthReccOfLinkedList(){
        createLinkedList();
        print();
        System.out.print("Length="+ getLengthRec(head,0));
        clear();
    }

    public int getLengthRec(Node node,int length){
        if(node != null){
            length++;
            return getLengthRec(node.getNext(),length);
        }
        return length;
    }

    public void searchElementInLinkedList(){
        createLinkedList();
        print();
        int data = 3;
        System.out.println("searchElementInLinkedList "+ data + ":" + searchElementRec(head,data));
    }

    public boolean searchElementRec(Node node, int data){
        if(node == null){
            return false;
        }
        if(node.getData() == data){
            return true;
        }
        return searchElementRec(node.getNext(),data);
    }

/*
Swap nodes in a linked list without swapping data
Given a linked list and two keys in it, swap nodes for two given keys. Nodes should be swapped by changing links. Swapping data of nodes may be expensive in many situations
when data contains many fields.It may be assumed that all keys in linked list are distinct.
Examples:

Input:  10->15->12->13->20->14,  x = 12, y = 20
Output: 10->15->20->13->12->14

Input:  10->15->12->13->20->14,  x = 10, y = 20
Output: 20->15->12->13->10->14

Input:  10->15->12->13->20->14,  x = 12, y = 13
Output: 10->15->13->12->20->14
*/
    public void swap(){
        createLinkedList();
        System.out.println("before swapping:");
        print();
        swapNode(2,5);
        System.out.println("after swapping:");
        print();
    }

    public void swapNode(int x, int y){
        if(x == y){
            return;
        }
        Node prevX = null,currX=head;
        while(currX != null){
            if(currX.getData() == x){
                break;
            }
            prevX = currX;
            currX = currX.getNext();
        }
        Node prevY=null,currY=head;
        while(currY != null){
            if(currY.getData() == y){
                break;
            }
            prevY = currY;
            currY = currY.getNext();
        }
        if(currX == null || currY == null){
            return;
        }
        if(prevX != null){
            prevX.setNext(currY);
        }
        else {
            head = currY;
        }
        if(prevY != null){
            prevY.setNext(currX);
        }
        else {
            head = currX;
        }
        Node temp = currX.getNext();
        currX.setNext(currY.getNext());
        currY.setNext(temp);

    }

    /*
Given a singly linked list, find middle of the linked list. For example, if given linked list is 1->2->3->4->5 then output should be 3.
If there are even nodes, then there would be two middle nodes, we need to print second middle element. For example, if given linked list is 1->2->3->4->5->6 then output should be 4.
   */
    public void printMiddle(){
        createLinkedList();
        print();
        System.out.println("Middle element:" + getMiddle());
    }

    public int getMiddle(){
        Node slow = head;
        Node fast = head;
        while(fast!= null && fast.getNext() != null){
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow.getData();
    }

    /*
Given a Linked List and a number n, write a function that returns the value at the n’th node from end of the Linked List.
     */
    public void getNthNodeFromEndOfALinkedList(){
        createLinkedList();
        print();
        int n=5;
        //System.out.print(n + "th node from end is:"+getNthNodeFromEnd(n));
        int [] data = new int[1];
        getNthNodeFromEndRec(head,n,data);
        System.out.print(n + "th node from end is:"+data[0]);

    }

    public int getNthNodeFromEnd(int n){
        Node p = head;
        Node q= head;
        for(int i = 0;i< n; i++){
            if(p != null){
                p = p.getNext();
            }
            else {
                break;
            }
        }
        while (p != null){
            p = p.getNext();
            q= q.getNext();
        }
        return q.getData();
    }

    public Node getNthNodeFromEndRec(Node curr,int n,int[] data){
        if(curr != null){
            getNthNodeFromEndRec(curr.getNext(),n,data);
            count++;
            if(n==count){
                data[0] = curr.getData();
            }
        }
        return null;
    }

    /*
    Reverse a linked list
     */
    public void reverseLinkedList(){
        createLinkedList();
        print();
        //reverseIterative();
        reverseRec(head,null);
        print();

    }

    public void reverseIterative(){
        Node curr = head;
        Node prev = null;
        Node next = null;
        while(curr != null){
            next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    public void reverseRec1(Node curr,Node prev){
        if(curr == null){
            head = prev;
        }
        Node next = curr.getNext();
        curr.setNext(prev);
        prev = curr;
        reverseRec1(next,prev);
    }



    public Node reverseRec(Node curr, Node prev){
        //base case
        if(curr.getNext() == null){
            head = curr;
            curr.setNext(prev);
            return null;
        }
        Node next = curr.getNext();
        //fix curr
        curr.setNext(prev);
        reverseRec(next,curr);
        return null;
    }

    /*
    Detect loop in a linked list
     */
    public void detectLoopInList(){
        createListWithCycle();
        printListWithCycle();
        System.out.println("Loop Present at :"+detectLoop());
    }

    private int detectLoop(){
        boolean found = false;
        int loopBeginNode = -1;
        Node slow = head;
        Node fast = head;
        while (fast.getNext() != null && fast.getNext().getNext()!= null){
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            if(slow.getData() == fast.getData()){
                found = true;
                break;
            }
        }
        if(found == true){
            loopBeginNode = getloopBeginNode(head,fast);
            //loopBeginNode = getloopBeginNode1();
        }
        return loopBeginNode;
    }

    private int getloopBeginNode(Node node1,Node node2){
        while (node1.getData() !=  node2.getData()){
            node1 = node1.getNext();
            node2 = node2.getNext();
        }
        return node1.getData();
    }

    private int getloopBeginNode1(){
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        Node curr = head;
        while (true){
            if(hashMap.containsKey(curr.getData())){
                break;
            }
            hashMap.put(curr.getData(),curr.getData());
            curr = curr.getNext();
        }
        return curr.getData();
    }

    private void createListWithCycle(){
        clear();
        head = new Node(1);
        head.setNext(new Node(2));
        head.getNext().setNext(new Node(3));
        head.getNext().getNext().setNext(new Node(4));
        head.getNext().getNext().getNext().setNext(new Node(5));
        head.getNext().getNext().getNext().getNext().setNext(new Node(6));
        head.getNext().getNext().getNext().getNext().getNext().setNext(head.getNext().getNext().getNext());
    }

    private void printListWithCycle(){
        System.out.println();
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        Node curr = head;
        while (curr != null){
            if(hashMap.get(curr.getData()) == null){
                System.out.print(curr.getData());
            }
            else {
                System.out.print(curr.getData());
                break;
            }
            hashMap.put(curr.getData(),curr.getData());
            curr = curr.getNext();
        }
        System.out.println();
    }


    /*
    Merge 2 sorted list in sorted order
     */
    public void merge2SortedListInSortedOrder(){
        Node a = new Node(1);
        a.setNext(new Node(2));
        a.getNext().setNext(new Node(5));
        a.getNext().getNext().setNext(new Node(10));
        a.getNext().getNext().getNext().setNext(new Node(15));
        a.getNext().getNext().getNext().getNext().setNext(new Node(20));

        Node b = new Node(1);
        b.setNext(new Node(2));
        b.getNext().setNext(new Node(5));
        b.getNext().getNext().setNext(new Node(9));
        b.getNext().getNext().getNext().setNext(new Node(18));

        print(a);
        print(b);
        //print(sortedMerge(a,b));
        print(sortedMergeRec(a,b));
    }


    /* Takes two lists sorted in increasing order, and splices
   their nodes together to make one big sorted list which
   is returned.  */
    public Node sortedMerge(Node a, Node b){
        if(a == null){
            return b;
        }
        if(b == null){
            return a;
        }
        Node result = new Node(0);
        Node curr = result;
        while (a != null && b!= null){
            if(a.getData() <= b.getData()){
                curr.setNext(new Node(a.getData()));
                a= a.getNext();
                curr = curr.getNext();
            }
            else if(a.getData() > b.getData()){
                curr.setNext(new Node(b.getData()));
                b = b.getNext();
                curr = curr.getNext();
            }
        }
        if(a != null){
            curr.setNext(a);
        }
        else if (b != null){
            curr.setNext(b);
        }
        return result.getNext();
    }

    private Node sortedMergeRec(Node a, Node b){
        if(a== null){
            return b;
        }
        if(b == null){
            return a;
        }
        Node head = new Node(0);
        if(a.getData() <= b.getData()){
            head = a;
            head.setNext(sortedMergeRec(a.getNext(),b));
        }
        else {
            head = b;
            head.setNext(sortedMergeRec(b.getNext(),a));
        }
        return head;
    }

    /*
    Add 1 to a number represented as linked list
Number is represented in linked list such that each digit corresponds to a node in linked list. Add 1 to it. For example 1999 is represented as (1-> 9-> 9 -> 9) and adding 1 to
it should change it to (2->0->0->0)
     */
    public void addOneToLinkedList(){
        clear();
        this.head = new Node(1);
        this.head.setNext(new Node(9));
        this.head.getNext().setNext(new Node(9));
        this.head.getNext().getNext().setNext(new Node(9));
        print();
    /*  reverseIterative();
        Node curr = head;
        int sum = 0;
        int carry = 0;
        int rem =0;
        while (true){
            sum = curr.getData() + 1;
            carry = sum % 10;
            rem = sum/10;
            curr.setData(carry);
            if(rem >= 1){
                curr = curr.getNext();
            }
            else {
                break;
            }
        }
        reverseIterative();
    */
        addOneToLinkedListRec(head);
        print();
    }

    private int addOneToLinkedListRec(Node node){
        if(node == null){
            return 1;
        }
        int sum = node.getData() + addOneToLinkedListRec(node.getNext());
        node.setData(sum%10);
        return sum/10;
    }

    /*
    given 2 sorted linked list,write an algorithm to print the common elements
     */
    public void printCommonIn2SortedList(){
        Node a = new Node(4);
        a.setNext(new Node(7));
        a.getNext().setNext(new Node(10));
        a.getNext().getNext().setNext(new Node(13));
        a.getNext().getNext().getNext().setNext(new Node(16));
        a.getNext().getNext().getNext().getNext().setNext(new Node(20));

        Node b = new Node(2);
        b.setNext(new Node(7));
        b.getNext().setNext(new Node(12));
        b.getNext().getNext().setNext(new Node(13));
        b.getNext().getNext().getNext().setNext(new Node(20));
        print(a);
        print(b);
        print(getCommonElementsListFrom2SortedList(a,b));
    }

    private Node getCommonElementsListFrom2SortedList(Node a, Node b){
        Node res = new Node(-1);
        Node curr = res;
        if(a == null){
            return b;
        }
        if(b == null){
            return a;
        }
        while (a != null && b!= null){
            if(a.getData() == b.getData()){
                curr.setNext(new Node(a.getData()));
                curr = curr.getNext();
                a = a.getNext();
                b = b.getNext();
            }
            else if(a.getData() > b.getData()){
                b = b.getNext();
            }
            else if(a.getData() < b.getData()){
                a = a.getNext();
            }
        }
        return res.getNext();
    }

    /*
    Given a linked list with even and odd numbers, create an algo for making change in a list such that all even numbers comes before odd.
    -create a new list oddList and remove all the odd numbers from the original list to the oddList such that after traversing, original list has all the even number and
    oddList has odd numbers
    -Append oddlist in front of the original list.
     */
    public void moveEvenToBegeningThenOdd(){
        head = new Node(2);
        head.setNext(new Node(4));
        head.getNext().setNext(new Node(5));
        head.getNext().getNext().setNext(new Node(7));
        head.getNext().getNext().getNext().setNext(new Node(8));
        head.getNext().getNext().getNext().getNext().setNext(new Node(24));
        head.getNext().getNext().getNext().getNext().getNext().setNext(new Node(25));
        head.getNext().getNext().getNext().getNext().getNext().getNext().setNext(new Node(30));
        head.getNext().getNext().getNext().getNext().getNext().getNext().getNext().setNext(new Node(31));

        Node curr = head;
        Node evenHead = null;
        Node evenCurr = null;
        Node oddHead = new Node(-1);
        Node oddCurr = oddHead;
        while (curr != null){
            Node next = curr.getNext();
            if(curr.getData() % 2 != 0){
                oddCurr.setNext(curr);
                curr.setNext(null);
                oddCurr = oddCurr.getNext();
            }
            else {
                if(evenHead == null) {
                    evenHead = curr;
                    evenCurr = curr;
                }
                else {
                    evenCurr.setNext(curr);
                    curr.setNext(null);
                    evenCurr = evenCurr.getNext();
                }
            }
            curr = next;
        }
        evenCurr.setNext(oddHead.getNext());
        print(evenHead);
    }


    /*
    Given a linked list and a value k. Partition the list in such a way that all node less than k should come before nodes greater than or equal to k.You should maintain the
    orignal order of the list. eg 1->4->3->2->5->2 and k =3 should give 1->2->2->4->3->5
     */
    public void partionListLesserNumberComesBefore(){
        head = new Node(1);
        head.setNext(new Node(4));
        head.getNext().setNext(new Node(3));
        head.getNext().getNext().setNext(new Node(2));
        head.getNext().getNext().getNext().setNext(new Node(5));
        head.getNext().getNext().getNext().getNext().setNext(new Node(2));
        int k = 3;
        print();
        System.out.println("k="+k);
        print(partition(head,k));
    }



    private Node partition(Node head,int k){
        Node lesser = null;
        Node greater = new Node(-1);
        Node lesserCurr = lesser;
        Node greaterCurr = greater;
        Node curr = head;
        while (curr != null){
            Node next = curr.getNext();
            if(curr.getData() >= k){
                greaterCurr.setNext(curr);
                curr.setNext(null);
                greaterCurr = greaterCurr.getNext();
            }
            else {
                if(lesser == null){
                    lesser = curr;
                    lesserCurr = curr;
                }
                else {
                    lesserCurr.setNext(curr);
                    curr.setNext(null);
                    lesserCurr = lesserCurr.getNext();
                }
            }
            curr = next;
        }
        lesserCurr.setNext(greater.getNext());
        return lesser;
    }

    /*
    Given 2 singly linked list which intersect at some point and become a single linked list.Find the intersection point.Note both the list may have duplicate values.

        1->2->3->4->5->6\
                        \
                        ->10->12->13
                      /
            3->5->6-/

      Here 10 is the intersection point.
      Algo:-
      ->iterate through both the list and find length m and n
      -> calculate difference d = m-n
      -> iterate the bigger list d places and then iterate both the lists at same pace, the next common node will be the intersection point

     */
    public void findIntersectingNode(){
        Node c = new Node(10);
        c.setNext(new Node(12));
        c.getNext().setNext(new Node(13));

        Node a = new Node(1);
        a.setNext(new Node(2));
        a.getNext().setNext(new Node(3));
        a.getNext().getNext().setNext(new Node(4));
        a.getNext().getNext().getNext().setNext(new Node(5));
        a.getNext().getNext().getNext().getNext().setNext(new Node(6));
        a.getNext().getNext().getNext().getNext().getNext().setNext(c);

        Node b = new Node(3);
        b.setNext(new Node(5));
        b.getNext().setNext(new Node(6));
        b.getNext().getNext().setNext(c);


        print(a);
        print(b);

        if(a != null && b != null){
            Node intersection = findIntersectingNode(a,b);
            print(intersection);
        }


    }

    private Node findIntersectingNode(Node a,Node b){
        int m =0;
        int n= 0;
        Node aPtr = a;
        Node bPtr = b;
        while(aPtr != null){
            m++;
            aPtr= aPtr.getNext();
        }
        while (bPtr != null){
            n++;
            bPtr= bPtr.getNext();
        }
        aPtr = a;
        bPtr = b;
        int d = Math.abs(m -n);
        if(m > n){
            for(int i =0; i<d; i++){
                aPtr = aPtr.getNext();
            }
        }
        else {
            for(int i =0; i<d; i++){
                bPtr = bPtr.getNext();
            }
        }
        while (aPtr != null && bPtr != null){
            if(aPtr == bPtr){
                return aPtr;
            }
            aPtr = aPtr.getNext();
            bPtr = bPtr.getNext();
        }
        return null;
    }

    /*
    pairwise swap of linked list eg, 1->2->3->4->5->x should give 2->1->4->3->x->5
     */
    public void pairWiseSwap(){
        Node a = new Node(1);
        a.setNext(new Node(2));
        a.getNext().setNext(new Node(3));
        a.getNext().getNext().setNext(new Node(4));
        a.getNext().getNext().getNext().setNext(new Node(5));
        a.getNext().getNext().getNext().getNext().setNext(new Node(6));
        print(a);
        //print(pairWiseSwap(a));
        print(pairWiseRec(a));
    }

    /*
    https://www.youtube.com/watch?v=jiLloHVmLDc
     */
    private Node pairWiseSwap(Node head){
        Node prev = head;
        Node curr = head.getNext();
        head = curr;
        int i = 1;
        while (true){
            Node next = curr.getNext();
            curr.setNext(prev);
            if(next == null || next.getNext() == null){
                prev.setNext(next);
                break;
            }
            prev.setNext(next.getNext());
            // update prev and curr
            prev = next;
            curr = prev.getNext();
        }
        return head;
    }



  private Node pairWiseRec(Node node) {
      if(node == null || node.getNext() == null){
          return node;
      }
      Node prev = node;
      Node curr = prev.getNext();
      node = curr;
      Node next = curr.getNext();
      curr.setNext(prev);
      prev.setNext(pairWiseRec(next));
      return node;
  }

    /*
    Split a circular linked list into 2 equal parts,if the number of nodes are odd,make first list one extra node then other.
    eg,             1->2->3->4->5->6
                    ^
                    |-------------|

                    should give,

                    1->2->3->1  and 4->5->6->4

     */
    public void splitCircularLinkedListIn2Halfes(){
        Node a = new Node(1);
        a.setNext(new Node(2));
        a.getNext().setNext(new Node(3));
        a.getNext().getNext().setNext(new Node(4));
        a.getNext().getNext().getNext().setNext(new Node(5));
        a.getNext().getNext().getNext().getNext().setNext(a);
        printCircularLinkedList(a);
        splitsplitCircularLinkedListIn2Halfes(a);
    }

    private void splitsplitCircularLinkedListIn2Halfes(Node node){
        Node slow = node;
        Node fast = node;
        Node head = null;
        Node head1 = null;
        while (fast.getNext() != node && fast.getNext().getNext() != node){
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        if(fast.getNext().getNext() == node){
            fast = fast.getNext();
        }
        head = node;
        head1 = slow.getNext();
        fast.setNext(head1);
        slow.setNext(head);
        printCircularLinkedList(head);
        printCircularLinkedList(head1);

    }


    private void printCircularLinkedList(Node node){
        System.out.println();
        Node curr = node;
        while (curr.getNext() != node){
            System.out.print(curr.getData());
            if(curr.getNext() != node){
                System.out.print(",");
            }
            curr = curr.getNext();
        }
        System.out.print(curr.getData()+",");
        System.out.print(curr.getNext().getData());
        System.out.println();
    }


    /*
    check whether a given linked list is palindrome or not
     */
    public void checkPalindrome(){
        Node a = new Node(1);
        a.setNext(new Node(2));
        a.getNext().setNext(new Node(3));
        a.getNext().getNext().setNext(new Node(2));
        a.getNext().getNext().getNext().setNext(new Node(1));

       /* Node a = new Node(1);
        a.setNext(new Node(2));
        a.getNext().setNext(new Node(2));
        a.getNext().getNext().setNext(new Node(1));
*/
        print(a);
     //   System.out.println("Is Palindrome:"+ checkPalindromeList(a));
        this.head = a;
        System.out.println("Is Palindrome:"+ checkPalindromeRec(a));
        print(a);
    }


    /*
    Use two pointers left and right. Move right and left using recursion and check for following in each recursive call.
1) Sub-list is palindrome.
2) Value at current left and right are matching.

If both above conditions are true then return true.

The idea is to use function call stack as container. Recursively traverse till the end of list. When we return from last NULL, we will be at last node. The last node to be compared
with first node of list.
In order to access first node of list, we need list head to be available in the last call of recursion. Hence we pass head also to the recursive function. If they both match we
need to compare (2, n-2) nodes. Again when recursion falls back to (n-2)nd node, we need reference to 2nd node from head. We advance the head pointer in previous call,
to refer to next node in the list.

However, the trick in identifying double pointer. Passing single pointer is as good as pass-by-value, and we will pass the same pointer again and again. We need to pass the address of head pointer for reflecting the changes in parent recursive calls.
     */
    private boolean checkPalindromeRec(Node right){
        left = this.head;
        if(right == null){
            return true;
        }
        boolean isP = checkPalindromeRec(right.getNext());
        if(isP == false){
            return isP;
        }
        boolean isP1 = (right.getData() == left.getData());
        System.out.println(right.getData() + "," + left.getData() + "," + isP1);
        left = left.getNext();
        return isP1;
    }

    private boolean checkPalindromeList(Node head){
        boolean isPalindrome = false;
        if(head != null && head.getNext() != null){
            Node slow = head;
            Node fast = head;
            Node prevSlow = null;
            //to handle odd size list
            Node midNode = null;
            Node secondHalf = null;
            while(fast != null && fast.getNext()!= null){
                fast = fast.getNext().getNext();
                prevSlow = slow;
                slow = slow.getNext();
            }
        /*
        if list is odd then we have to skip the middle element otherwise not
         */
            if(fast != null){
                midNode = slow;
                slow = slow.getNext();
            }
            secondHalf = slow;
            prevSlow.setNext(null);
            secondHalf = reverse(secondHalf);
            isPalindrome = compareList(head,secondHalf);
            secondHalf = reverse(secondHalf);
            if(midNode != null){
                prevSlow.setNext(midNode);
                prevSlow = prevSlow.getNext();
            }
            prevSlow.setNext(secondHalf);
        }
        return isPalindrome;
    }

    private Node reverse(Node head){
        Node curr = head;
        Node prev = null;
        while (curr != null){
            Node next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }
        return prev;
    }

    private boolean compareList(Node head, Node head1){
        Node curr = head;
        Node curr1 = head1;
        if(curr == null || curr1 == null){
            return false;
        }
        while (curr != null && curr1 != null){
            if(curr.getData() == curr1.getData()){
                curr = curr.getNext();
                curr1 = curr1.getNext();
            }
            else {
                return false;
            }
        }
        if(curr == null && curr1 == null){
         return true;
        }
        return false;
    }

    /*
    Pairwise swap data of a given linked list
     */
    public void pairwiseSwapData(){
        Node a = new Node(1);
        a.setNext(new Node(2));
        a.getNext().setNext(new Node(3));
        a.getNext().getNext().setNext(new Node(4));
        a.getNext().getNext().getNext().setNext(new Node(5));
        a.getNext().getNext().getNext().getNext().setNext(new Node(6));
        print(a);
        //pairWiseSwapData(a);
        pairwiseSwapDataRec(a);
        print(a);


    }

    private void pairWiseSwapData(Node head){
        if(head == null || head.getNext() == null){
            return;
        }
        Node curr = head;
        int temp = -1;
        while (curr!= null && curr.getNext() != null){
            temp = curr.getData();
            curr.setData(curr.getNext().getData());
            curr.getNext().setData(temp);
            curr = curr.getNext().getNext();
        }
    }

    private void pairwiseSwapDataRec(Node head){
        if(head != null && head.getNext() != null){
            int temp = head.getData();
            head.setData(head.getNext().getData());
            head.getNext().setData(temp);
            pairwiseSwapDataRec(head.getNext().getNext());
        }

    }


    /*
    For a given value of k,reverse k blocks in the list eg, list = 1->2->3->4->5->6->7->8->9->10
    for k =2,output = 2->1->4->3->6->5->8->7->10->9
    for k =3,output = 3->2->1->6->5->4->9->8->7->10
    so on
     */

    public void reverseKBlocksInList(){
        Node a = new Node(1);
        a.setNext(new Node(2));
        a.getNext().setNext(new Node(3));
        a.getNext().getNext().setNext(new Node(4));
        a.getNext().getNext().getNext().setNext(new Node(5));
        a.getNext().getNext().getNext().getNext().setNext(new Node(6));
        a.getNext().getNext().getNext().getNext().getNext().setNext(new Node(7));
        a.getNext().getNext().getNext().getNext().getNext().getNext().setNext(new Node(8));
        a.getNext().getNext().getNext().getNext().getNext().getNext().getNext().setNext(new Node(9));
        a.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().setNext(new Node(10));
        print(a);
        int k = 6;
        //print(reverseKBlocksInList(a,k));
        print(reverseKBlocksInListRec(a,k));
    }

    private Node reverseKBlocksInList(Node head, int n){
        Node curr = head;
        Node prevCurr = head;
        Node prevTail = null;
        while(curr != null){
            int count = n;
            //reverse n blocks
            Node prev = null;
            while (curr != null && count > 0){
                Node next = curr.getNext();
                curr.setNext(prev);
                prev = curr;
                curr = next;
                count--;
            }
            if(prevTail != null){
                //in case of next subsequent blocks, link the previous block with the next reversed block
                prevTail.setNext(prev);
            }
            else {
                //first block is reversed, so store the head position which needs to be returned
                head = prev;
            }
            //set prevTail and prevCurr
            prevTail = prevCurr;
            prevCurr = curr;
        }
        return head;
    }

    private Node reverseKBlocksInListRec(Node head,int k){
        if(head == null || head.getNext() == null){
            return head;
        }
        Node curr = head;
        Node prev = null;
        int count = k;
        while (curr != null && count > 0){
            Node next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
            count--;
        }
        head.setNext(reverseKBlocksInListRec(curr,k));
        return prev;
    }





    /*
    JOSEPHUS CIRCLE:-N persons have  decided to elect a leader by arranging themselves in a circle and eliminating every Mth person,Find which persone will be last on remaining
     */
    public void josephusCircle(){
        Node a = new Node(1);
        a.setNext(new Node(2));
        a.getNext().setNext(new Node(3));
        a.getNext().getNext().setNext(new Node(4));
        a.getNext().getNext().getNext().setNext(new Node(5));
        a.getNext().getNext().getNext().getNext().setNext(new Node(6));
        a.getNext().getNext().getNext().getNext().getNext().setNext(new Node(7));
        a.getNext().getNext().getNext().getNext().getNext().getNext().setNext(new Node(8));
        a.getNext().getNext().getNext().getNext().getNext().getNext().getNext().setNext(new Node(9));
        a.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().setNext(new Node(10));
        a.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().setNext(a);
        int m = 3;
        printCircularLinkedList(a);
        System.out.println(josephusCircle(a,6).getData());

    }

    private Node josephusCircle(Node head, int m){
        Node curr = head;
        while (curr.getNext() != curr){
            for (int i = 0 ; i < m-1 ; ++i){
                curr = curr.getNext();
            }
            curr.setNext(curr.getNext().getNext());
        }
        return curr;
    }



    /*
    Given a linked list l1->l2->l3->...ln-2->ln-1->ln, reorder it to l1->ln->l2->ln-1->l3->ln-2...
    Algo:- Find the middle of list using fast ptr and split the list in 2 halves,
           Reverse the second half.
           Do alternate merge of first and second halves.
    */
    public void reorderLinkedList(){
        Node a = new Node(1);
        a.setNext(new Node(2));
        a.getNext().setNext(new Node(3));
        a.getNext().getNext().setNext(new Node(4));
        a.getNext().getNext().getNext().setNext(new Node(5));
        a.getNext().getNext().getNext().getNext().setNext(new Node(6));
        a.getNext().getNext().getNext().getNext().getNext().setNext(new Node(7));
        a.getNext().getNext().getNext().getNext().getNext().getNext().setNext(new Node(8));
        a.getNext().getNext().getNext().getNext().getNext().getNext().getNext().setNext(new Node(9));
        a.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().setNext(new Node(10));
        print(a);
        print(reorderLinkedList(a));
    }

    private Node reorderLinkedList(Node head){
        if(head == null || head.getNext() == null || head.getNext().getNext() == null){
            return head;
        }
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.getNext() != null){
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        Node otherHalf = slow.getNext();
        slow.setNext(null);
        slow = head;
        otherHalf = reverse(otherHalf);
        Node result = new Node(0);
        Node resultPtr = result;
        /*while (slow != null || otherHalf != null){
            if(slow != null){
                resultPtr.setNext(slow);
                resultPtr = resultPtr.getNext();
                slow = slow.getNext();
            }
            if(otherHalf != null){
                resultPtr.setNext(otherHalf);
                resultPtr = resultPtr.getNext();
                otherHalf = otherHalf.getNext();
            }
        }*/
        while (slow != null && otherHalf != null){
            resultPtr.setNext(slow);
            resultPtr = resultPtr.getNext();
            slow = slow.getNext();
            resultPtr.setNext(otherHalf);
            resultPtr = resultPtr.getNext();
            otherHalf = otherHalf.getNext();
        }
        if(slow != null){
            resultPtr.setNext(slow);
        }
        else if(otherHalf != null){
            resultPtr.setNext(otherHalf);
        }
        return result.getNext();
    }


    /*
    Given a linked list, rotate it to right by k places.eg, 1->2->3->4->5->null ,k =2 should give 4->5->1->2->3->null

    Algo:- To rotate the linked list, we need to change next of kth node to NULL, next of last node to previous head node, and finally change head to (k+1)th node.
    So we need to get hold of three nodes: kth node, (k+1)th node and last node.
    */
    public void rotateListRightByK(){
        Node a = new Node(1);
        a.setNext(new Node(2));
        a.getNext().setNext(new Node(3));
        a.getNext().getNext().setNext(new Node(4));
        a.getNext().getNext().getNext().setNext(new Node(5));
        print(a);
        int k = 2;
        print(rotateListRightByK(a,k));

    }


    // it is assumed that k is less than the length of the list
    public Node rotateListRightByK(Node head, int k){
        if(head == null || head.getNext() == null){
            return head;
        }
        Node curr = head;
        while (curr != null && k > 0){
            curr = curr.getNext();
            k--;
        }
        if (curr == null)
            return head;

        Node kthNode = curr;

        while (curr.getNext() != null){
            curr = curr.getNext();
        }
        curr.setNext(head);
        head = kthNode.getNext();
        kthNode.setNext(null);
        return head;
    }


    /*
    Add 2 numbers, 3->4->3 and 5->6->4 should give 8->0->8, digits are stored in reverse order and each node can contain a single digit.
     */
    public void add2Numbers(){
        Node a = new Node(9);
        a.setNext(new Node(9));
        a.getNext().setNext(new Node(9));

        Node b = new Node(1);
        /*
        b.setNext(new Node(6));
        b.getNext().setNext(new Node(4));
        */
        print(a);
        print(b);
        print(add2Numbers(a,b));

    }

    private Node add2Numbers(Node head, Node head1){
        if(head == null && head1 == null){
            return head;
        }
        if(head == null){
            return head1;
        }
        if(head1 == null){
            return head;
        }
        // append 0 in the shorter list till its length becomes equal to the other list
        Node curr = head;
        int i = 0;
        while(curr != null && curr.getNext() != null){
            curr = curr.getNext().getNext();
            i++;
        }
        if(curr == null){
            i = 2*i;
        }
        else {
            i = (2*i) + 1;
        }
        curr = head1;
        int j =0;
        while (curr != null && curr.getNext() != null){
            curr = curr.getNext().getNext();
            j++;
        }
        if(curr == null){
            j = 2*j;
        }
        else {
            j = (2*j) + 1;
        }
        Node bigger = null;
        Node small = null;
        int d = Math.abs(i-j);
        if(i >= j){
            bigger = head;
            small = head1;
        }
        else if(j > i){
            bigger = head1;
            small = head;
        }
        head = small;
        while (small.getNext() != null){
            small = small.getNext();
        }
        for(int k =0;k<d;k++){
            small.setNext(new Node(0));
            small = small.getNext();
        }
        head1 = bigger;
        int sum = 0;
        int carry = 0;
        int data = 0;
        Node result = new Node(-1);
        curr = result;
        while (head != null && head1 != null){
            sum = head.getData() + head1.getData() + carry;
            data = sum % 10;
            carry = sum / 10;
            curr.setNext(new Node(data));
            curr = curr.getNext();
            head = head.getNext();
            head1 = head1.getNext();
        }
        if(carry != 0){
            curr.setNext(new Node(carry));
        }
        return result.getNext();

    }


    /*
    Swap Kth node from beginning with Kth node from end in a Linked List
    eg,
    Original Linked List: 1 2 3 4 5 6 7 8

Modified List for k = 1
8 2 3 4 5 6 7 1

Modified List for k = 2
1 7 3 4 5 6 2 8

Modified List for k = 3
1 2 6 4 5 3 7 8

Algo:-
Let X be the kth node from beginning and Y be the kth node from end. Following are the interesting cases that must be handled.
1) Y is next to X
2) X is next to Y
3) X and Y are same
4) X and Y don’t exist (k is more than number of nodes in linked list)
     */

    public void swapKthNodeFromBeginingToEnd(){
        Node a = new Node(1);
        a.setNext(new Node(2));
        a.getNext().setNext(new Node(3));
        a.getNext().getNext().setNext(new Node(4));
        a.getNext().getNext().getNext().setNext(new Node(5));
        a.getNext().getNext().getNext().getNext().setNext(new Node(6));
        a.getNext().getNext().getNext().getNext().getNext().setNext(new Node(7));
        a.getNext().getNext().getNext().getNext().getNext().getNext().setNext(new Node(8));
        print(a);
        int k =8;
        print(swapKthNodeFromBeginingToEnd(a,k));
    }

    private Node swapKthNodeFromBeginingToEnd(Node head, int k){
        if(head == null || head.getNext() == null){
            return head;
        }
        int n = calculateLength(head);
        if(k > n){
            return head;
        }
        //if k is the middle
        if((2 * k) -1 == n){
            return head;
        }
        Node x = head;
        Node prevX = null;
        Node y = head;
        Node prevY = null;
        for(int i = 1; i<k; i++){
            prevX = x;
            x = x.getNext();
        }
        for(int i = 1; i<n-k+1; i++){
            prevY = y;
            y = y.getNext();
        }
        if(prevX != null){
            prevX.setNext(y);
        }
        if(prevY != null){
            prevY.setNext(x);
        }
        Node temp = x.getNext();
        x.setNext(y.getNext());
        y.setNext(temp);
        if(k == 1){
            head = y;
        }
        if(k == n){
            head = x;
        }
        return head;

    }

    private int calculateLength(Node head){
        int length = 0;
        Node curr = head;
        while (curr != null && curr.getNext() != null){
            curr = curr.getNext().getNext();
            length ++;
        }
        if(curr != null){
            length = (2 * length) + 1;
        }
        else {
            length = (2 * length);
        }
        return length;
    }



    /*
    Clone a linked list with next and random pointer in O(1) space
Given a linked list having two pointers in each node. The first one points to the next node of the list, however the other pointer is random and can point to any node of the list.
 Write a program that clones the given list in O(1) space, i.e., without any extra space.
     */
    public void cloneLinkList(){
        Node a = new Node(1);
        a.setNext(new Node(2));
        a.getNext().setNext(new Node(3));
        a.getNext().getNext().setNext(new Node(4));
        a.getNext().getNext().getNext().setNext(new Node(5));
        a.setRandom(a.getNext().getNext());
        a.getNext().getNext().setRandom(a.getNext().getNext().getNext().getNext());
        a.getNext().getNext().getNext().getNext().setRandom(a.getNext());
        printListWithRandomPointer(a);
        //printListWithRandomPointer(cloneLinkListExtraSpace(a));
        printListWithRandomPointer(cloneLinkListConstantSpace(a));
        printListWithRandomPointer(a);

    }

    private Node cloneLinkListExtraSpace(Node head){
        Node curr = head;
        HashMap<Node,Node> map = new HashMap<>();
        while (curr != null){
            map.put(curr, new Node(curr.getData()));
            curr = curr.getNext();
        }
        curr = head;
        Node clone = null;
        while (curr != null){
            clone = map.get(curr);
            clone.setNext(curr.getNext());
            clone.setRandom(curr.getRandom());
            curr = curr.getNext();
        }
        return map.get(head);
    }

    /*
    Algo:-
    ->Create the copy of node 1 and insert it between node 1 & node 2 in original Linked List, create the copy of 2 and insert it between 2 & 3.. Continue in this fashion,
    add the copy of N afte the Nth node
    ->Now copy the random link in this fashion
      original->next->random= original->random->next;  TRAVERSE TWO NODES
      This works because original->next is nothing but copy of original and Original->random->next is nothing but copy of random.
     ->Now restore the original and copy linked lists in this fashion in a single loop.
        original->next = original->next->next;
        copy->next = copy->next->next;
     */
    private Node cloneLinkListConstantSpace(Node head){
        Node curr = head;
        while (curr != null){
            Node temp = curr.getNext();
            curr.setNext(new Node(curr.getData()));
            curr.getNext().setNext(temp);
            curr = temp;
        }
        curr = head;
        while (curr != null){
            if(curr.getRandom() != null){
                curr.getNext().setRandom(curr.getRandom().getNext());
            }
            curr = (curr.getNext() != null ? curr.getNext().getNext() : curr.getNext());
        }
        Node original = head;
        Node clone = head.getNext();
        Node temp = clone;
        while (original != null && temp != null){
            original.setNext((original.getNext() != null ? original.getNext().getNext() : original.getNext()));
            temp.setNext((temp.getNext() != null ? temp.getNext().getNext() : temp.getNext()));
            original = original.getNext();
            temp = temp.getNext();
        }
        return clone;

    }

    private void printListWithRandomPointer(Node head){
        System.out.println();
        Node curr = head;
        while (curr != null){
            System.out.print(curr.getData() + "-R->" + (curr.getRandom() != null ? curr.getRandom().getData() : curr.getRandom()));
            if(curr.getNext() != null){
                System.out.print(",");
            }
            curr = curr.getNext();
        }
        System.out.println();
    }


    /*
    Delete last occurrence of an item from linked list. List may have duplicates.
    Input:   1->2->3->5->2->10, key = 2
    Output:  1->2->3->5->10
     */
    public void deleteLastOccurrenceOfKeyFromList(){
        Node a = new Node(1);
        a.setNext(new Node(2));
        a.getNext().setNext(new Node(3));
        a.getNext().getNext().setNext(new Node(5));
        a.getNext().getNext().getNext().setNext(new Node(2));
        a.getNext().getNext().getNext().getNext().setNext(new Node(10));
        print(a);
        print(deleteLastOccurrenceOfKeyFromList(a,2));

    }

    private Node deleteLastOccurrenceOfKeyFromList(Node head, int key){
        Node curr = head;
        Node lastestKeyPtr = null;
        Node lastestKeyPtrPrev = null;
        Node prev = null;
        while (curr != null){
            if(curr.getData() == key){
                lastestKeyPtrPrev = prev;
                lastestKeyPtr = curr;
            }
            prev = curr;
            curr = curr.getNext();
        }
        if(lastestKeyPtrPrev != null && lastestKeyPtr != null){
            if(lastestKeyPtr.getNext() != null){
                lastestKeyPtrPrev.setNext(lastestKeyPtr.getNext());
            }
            else {
                lastestKeyPtrPrev.setNext(null);
            }
        }
        //lastestKeyPtr is the first node;
        else if(lastestKeyPtrPrev == null) {
            head = lastestKeyPtr.getNext();
        }
        return head;
    }


    /*
    Given a linked list which is sorted based on absolute values. Sort the list based on actual values.
    Input : 1 -> -2 -> -3 -> 4 -> -5
    output: -5 -> -3 -> -2 -> 1 -> 4

    All the negative numbers are sorted in reverse order so we can traverse the list and detach the number and add it to the head.
     */
    public void sortOnActualValue(){
        /*Node a = new Node(1);
        a.setNext(new Node(-2));
        a.getNext().setNext(new Node(-3));
        a.getNext().getNext().setNext(new Node(4));
        a.getNext().getNext().getNext().setNext(new Node(6));
        a.getNext().getNext().getNext().getNext().setNext(new Node(-7));
        */
        Node a = new Node(-5);
        a.setNext(new Node(-10));
        a.getNext().setNext(new Node(-15));
        a.getNext().getNext().setNext(new Node(0));

        print(a);
        print(sortOnActualValue(a));
    }

    private Node sortOnActualValue(Node head){
        if(head == null || head.getNext() == null){
            return head;
        }
        Node prev = head;
        Node curr = head.getNext();
        while (curr != null){
            if(curr.getData() < prev.getData()){
                prev.setNext(curr.getNext());
                curr.setNext(head);
                head = curr;
                curr = prev;
            }
            else {
                prev = curr;
            }
            curr = curr.getNext();
        }
        return head;
    }


    /*
    Point arbit pointer to greatest value right side node in a linked list
    Given singly linked list with every node having an additional “arbitrary” pointer that currently points to NULL. We need to make the “arbitrary” pointer to greatest value
    node in a linked list on its right side.

    Input :- 5->10->2->3
    Output:
Node    Next Pointer    Arbit Pointer
5               10              10
10              2               3
2               3               3
3               NULL            NULL
    ALgo:-
    -Reverse given linked list.
    -Start traversing linked list and store maximum value node encountered so far. Make arbit of every node to point to max. If the
    data in current node is more than max node so far, update max.
    -Reverse modified linked list and return head.
     */
    public void populateArbitPointer(){
        Node a = new Node(5);
        a.setNext(new Node(10));
        a.getNext().setNext(new Node(2));
        a.getNext().getNext().setNext(new Node(3));
        printListWithRandomPointer(a);
//        printListWithRandomPointer(populateArbitPointer(a));
        populateArbitPointerRec(a);
        printListWithRandomPointer(a);
    }

    private Node populateArbitPointer(Node head){
        if(head == null || head.getNext() == null){
           return head;
        }
        head = reverse(head);
        Node curr = head;
        Node maxValueNode = head;
        while (curr != null){
            curr.setRandom(maxValueNode);
            if(curr.getData() > maxValueNode.getData()){
                maxValueNode = curr;
            }
            curr = curr.getNext();
        }
        return reverse(head);
    }

    private void populateArbitPointerRec(Node head){
        if(head == null){
            return;
        }
        if(head.getNext() == null){
            maxNode = head;
            return;
        }
        populateArbitPointerRec(head.getNext());
        head.setRandom(maxNode);
        if(head.getData() > maxNode.getData()){
            maxNode = head;
        }
        return;
    }

/*
    Rearrange a Linked List in Zig-Zag fashion
    Given a linked list, rearrange it such that converted list should be of the form a < b > c < d > e < f .. where a, b, c.. are consecutive data node of linked list. Examples :

    Input:  1->2->3->4
    Output: 1->3->2->4

    Input:  11->15->20->5->10
    Output: 11->20->5->15->10
*/
    public void zigZagOrder(){
    /*    Node a = new Node(11);
        a.setNext(new Node(15));
        a.getNext().setNext(new Node(20));
        a.getNext().getNext().setNext(new Node(5));
        a.getNext().getNext().getNext().setNext(new Node(10));
   */
        Node a = new Node(1);
        a.setNext(new Node(2));
        a.getNext().setNext(new Node(3));
        a.getNext().getNext().setNext(new Node(4));
        print(a);
        print(zigZag(a));

    }

    private Node zigZag(Node head){
        if(head == null || head.getNext() == null){
            return head;
        }
        boolean order = true;
        Node curr = head;
        Node prev = null;
        while (curr != null && curr.getNext() != null){
            Node next = curr.getNext();
            if(order){
                if(curr.getData() > next.getData()){
                    if(prev != null){
                        prev.setNext(next);
                    }
                    curr.setNext(next.getNext());
                    next.setNext(curr);
                    prev = next;
                }
                else {
                    prev = curr;
                    curr = curr.getNext();
                }
            }
            else {
                if(curr.getData() < next.getData()){
                    prev.setNext(next);
                    curr.setNext(next.getNext());
                    next.setNext(curr);
                    prev = next;
                }
                else {
                    prev = curr;
                    curr = curr.getNext();
                }
            }
            order = !order;
        }
        return head;
    }

    /*
    Sort a linked list that is sorted alternating ascending and descending orders?
Given a Linked List. The Linked List is in alternating ascending and descending orders. Sort the list efficiently.

Example:

Input List:   10->40->53->30->67->12->89->NULL
Output List:  10->12->30->40->53->67->89->NULL

Algo:-
->seperate the 2 list
10-53-67-89
40-30-12
->reverse second
12-30-40
->compare data and merge
10->12->30->40->53->67->89->NULL


     */


    /*
    Reverse alternate K nodes in a Singly Linked List
Given a linked list, write a function to reverse every alternate k nodes (where k is an input to the function) in an efficient way. Give the complexity of your algorithm.

Example:
Inputs:   1->2->3->4->5->6->7->8->9->NULL and k = 3
Output:   3->2->1->4->5->6->9->8->7->NULL.
     */
    public void reverseAlternateKNodes(){
        Node a = new Node(1);
        a.setNext(new Node(2));
        a.getNext().setNext(new Node(3));
        a.getNext().getNext().setNext(new Node(4));
        a.getNext().getNext().getNext().setNext(new Node(5));
        a.getNext().getNext().getNext().getNext().setNext(new Node(6));
        a.getNext().getNext().getNext().getNext().getNext().setNext(new Node(7));
        a.getNext().getNext().getNext().getNext().getNext().getNext().setNext(new Node(8));
        a.getNext().getNext().getNext().getNext().getNext().getNext().getNext().setNext(new Node(9));
        a.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().setNext(new Node(10));
        print(a);
        print(reverseAlternateKNodesRec(a,3));
    }

    private Node reverseAlternateKNodesRec(Node head,int k){
        if(head == null){
            return null;
        }
        Node curr = head;
        Node prev = null;
        int count = k;
        while(curr != null && count > 0){
            Node next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
            count--;
        }
        if(head != null){
            head.setNext(curr);

        }
        count = k;
        while (curr != null && count > 0){
            curr = curr.getNext();
            count--;
        }
        if(curr != null){
            curr.setNext(reverseAlternateKNodesRec(curr,k));
        }
        return prev;

    }


    /*
    Delete alternate nodes of a Linked List with recurrsion
Given a Singly Linked List, starting from the second node delete all alternate nodes of it. For example, if the given linked list is
1->2->3->4->5 then your function should
convert it to 1->3->5, and if the given linked list is 1->2->3->4 then convert it to 1->3.
     */
    public void deleteAlternate(){
        Node a = new Node(1);
        a.setNext(new Node(2));
        a.getNext().setNext(new Node(3));
        a.getNext().getNext().setNext(new Node(4));
        a.getNext().getNext().getNext().setNext(new Node(5));
        a.getNext().getNext().getNext().getNext().setNext(new Node(6));
        a.getNext().getNext().getNext().getNext().getNext().setNext(new Node(7));
        a.getNext().getNext().getNext().getNext().getNext().getNext().setNext(new Node(8));
        a.getNext().getNext().getNext().getNext().getNext().getNext().getNext().setNext(new Node(9));
        a.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().setNext(new Node(10));
        print(a);
        deleteAlternateRec(a);
        print(a);
    }

    private void deleteAlternateRec(Node head){
        if(head == null || head.getNext() == null){
            return;
        }
        head.setNext(head.getNext().getNext());
        deleteAlternateRec(head.getNext());
        return;
    }

    /*
    Merge sort is often preferred for sorting a linked list. The slow random-access performance of a linked list makes some other
    algorithms (such as quicksort) perform poorly,
    and others (such as heapsort) completely impossible.
    Merge Sort of Linked List
    algo:-
    MergeSort(headRef)
    1) If head is NULL or there is only one element in the Linked List
    then return.
    2) Else divide the linked list into two halves.
      FrontBackSplit(head, &a, &b);
    3) Sort the two halves a and b.
            MergeSort(a);
            MergeSort(b);
    4) Merge the sorted a and b (using SortedMerge())
    and update the head pointer using headRef.
    *headRef = SortedMerge(a, b);
    */
    //10->2->13->4->3->9->20->8->11->23
    public void mergeSort(){
        Node a = new Node(10);
        a.setNext(new Node(5));
        a.getNext().setNext(new Node(13));
        a.getNext().getNext().setNext(new Node(1));
        a.getNext().getNext().getNext().setNext(new Node(9));
        a.getNext().getNext().getNext().getNext().setNext(new Node(2));
        print(a);
        print(mergeSort(a));
    }

    private Node mergeSort(Node head){
        if(head == null || head.getNext() == null){
            return head;
        }
        Node middle = getMiddle(head);
        Node nextMiddle = middle.getNext();
        middle.setNext(null);
        Node left = mergeSort(head);
        Node right = mergeSort(nextMiddle);
        return sortedMerge(left,right);
    }

    private Node getMiddle(Node head){
        if(head == null || head.getNext() == null){
            return head;
        }
        Node slow = head;
        Node fast = head.getNext();
        while (fast != null && fast.getNext() != null){
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }

    /*
    Flattening a Linked List
Given a linked list where every node represents a linked list and contains two pointers of its type:
(i) Pointer to next node in the main list (we call it ‘right’ pointer in below code)
(ii) Pointer to a linked list where this node is head (we call it ‘down’ pointer in below code).
All linked lists are sorted. See the following example

       5 -> 10 -> 19 -> 28
       |    |     |     |
       V    V     V     V
       7    20    22    35
       |          |     |
       V          V     V
       8          50    40
       |                |
       V                V
       30               45
Write a function flatten() to flatten the lists into a single linked list. The flattened linked list should also be sorted.
For example, for the above input list, output list should be 5->7->8->10->19->20->22->28->30->35->40->45->50.

Algo:-
The idea is to use Merge() process of merge sort for linked lists. We use merge() to merge lists one by one.
We recursively merge() the current list with already flattened list.
The down pointer is used to link nodes of the flattened list.


     */

    public void flattenLinkedList(){
        ListNode head = new ListNode(5);
        head.setRight(new ListNode(10));
        head.getRight().setRight(new ListNode(19));
        head.getRight().getRight().setRight(new ListNode(28));
        head.setDown(new ListNode(7));
        head.getDown().setDown(new ListNode(8));
        head.getDown().getDown().setDown(new ListNode(30));
        head.getRight().setDown(new ListNode(20));
        head.getRight().getRight().setDown(new ListNode(22));
        head.getRight().getRight().getDown().setDown(new ListNode(50));
        head.getRight().getRight().getRight().setDown(new ListNode(35));
        head.getRight().getRight().getRight().getDown().setDown(new ListNode(40));
        head.getRight().getRight().getRight().getDown().getDown().setDown(new ListNode(45));
        //printListNode(flatten(head));
        printListNode(flattenIt(head));




    }

    private void printListNode(ListNode node){
        System.out.println();
        ListNode curr = node;
        while (curr != null){
            System.out.print(curr.getData());
            if(curr.getDown() != null){
                System.out.print(",");
            }
            curr = curr.getDown();
        }
        System.out.println();
    }

    /*
       5 -> 10 -> 19 -> 28
       |    |     |     |
       V    V     V     V
       7    20    22    35
       |          |     |
       V          V     V
       8          50    40
       |                |
       V                V
       30               45
     */
    private ListNode flattenIt(ListNode head){
        if(head == null || head.getRight() == null){
            return head;
        }
        ListNode result = null;
        ListNode curr = head;
        while (curr != null ){
            ListNode right = curr.getRight();
            if(result == null){
                result = mergeIt(curr,right);
            }
            else {
                result = mergeIt(result, right);
            }
            curr = right;
        }
        return result;
    }

    private ListNode flatten(ListNode head){
        if(head == null || head.getRight() == null){
            return head;
        }
        head.setRight(flatten(head.getRight()));
        //head = merge(head,head.getRight());
        head = mergeIt(head,head.getRight());
        return head;
    }

    private ListNode mergeIt(ListNode head, ListNode head1){
        ListNode a = head;
        ListNode b = head1;
        if(a == null){
            return b;
        }
        if(b == null){
            return a;
        }
        ListNode result = new ListNode(-1);
        ListNode curr = result;
        while (a != null && b != null){
            if(a.getData() <= b.getData()){
                curr.setDown(new ListNode(a.getData()));
                curr = curr.getDown();
                a = a.getDown();
            }
            else {
                curr.setDown(new ListNode(b.getData()));
                curr = curr.getDown();
                b = b.getDown();
            }
        }
        if(a != null){
            curr.setDown(a);
        }
        else if(b != null){
            curr.setDown(b);
        }
        return result.getDown();
    }

    private ListNode merge(ListNode a, ListNode b){
        if(a == null){
            return b;
        }
        if(b == null){
            return a;
        }
        ListNode result;
        if(a.getData() < b.getData()){
            result = a;
            result.setDown(merge(a.getDown(),b));
        }
        else {
            result = b;
            result.setDown(merge(a,b.getDown()));
        }
        return result;
    }


    /*
    Find a triplet from three linked lists with sum equal to a given number.
    Given three linked lists, say a, b and c, find one node from each list such that the sum of the values of the nodes is equal to \
    a given number.
    For example, if the three linked lists are 12->6->29, 23->5->8 and 90->20->59, and the given number is 101, the output should be
    tripel “6 5 90”.

    A simple method to solve this problem is to run three nested loops. The outermost loop picks an element from list a, the middle loop picks an element from b and the
    innermost loop picks from c. The innermost loop also checks whether the sum of values of current nodes of a, b and c is equal to given number.
    The time complexity of this method will be O(n^3).

    Sorting can be used to reduce the time complexity to O(n*n). Following are the detailed steps.
    1) Sort list b in ascending order, and list c in descending order.
    2) After the b and c are sorted, one by one pick an element from list a and find the pair by traversing both b and c.
     */
    public void findTripletFrom3LinkedListsWhoseSumEqualsN(){
        Node a = new Node(12);
        a.setNext(new Node(6));
        a.getNext().setNext(new Node(29));

        Node b = new Node(23);
        b.setNext(new Node(5));
        b.getNext().setNext(new Node(8));

        Node c = new Node(90);
        c.setNext(new Node(20));
        c.getNext().setNext(new Node(59));

        print(a);
        print(b);
        print(c);

        //sort b in ascending order
        b = mergeSort(b);
        //sort c in descending order
        c = reverse(mergeSort(c));
        print(isSumSorted(a,b,c,101));


    }

    // function assumes that b is sorted in ascending order and c is sorted in desending order
    private Node isSumSorted(Node a, Node b, Node c, int sum){
        Node result = new Node(-1);
        Node curr = a;
        while (curr != null){
            Node temp = b;
            Node temp1 = c;
            while (temp != null && temp1 != null){
                int total = curr.getData() + temp.getData() + temp1.getData();
                if(total == sum){
                    result.setNext(new Node(curr.getData()));
                    result.getNext().setNext(new Node(temp.getData()));
                    result.getNext().getNext().setNext(new Node(temp1.getData()));
                    return result;
                }
                temp = temp.getNext();
                temp1 = temp1.getNext();
            }
            curr = curr.getNext();
        }
        return result.getNext();
    }

    /*
    Construct a Maximum Sum Linked List out of two Sorted Linked Lists having some Common nodes
    Given two sorted linked lists, construct a linked list that contains maximum sum path from start to end. The result list may
    contain nodes from both input lists. When constructing the result list, we may switch to the other input list only at the point
    of intersection (which mean the two node with the same value in the lists). You are allowed to use O(1) extra space.
    Input:
    List1 =  1->3->30->90->120->240->511
    List2 =  0->3->12->32->90->125->240->249

    Output: Following is maximum sum linked list out of two input lists
    list =  1->3->12->32->90->125->240->511
    we switch at 3 and 240 to get above maximum sum linked list

The idea here in the below solution is to adjust next pointers after common nodes.
1. Start with head of both linked lists and find first common node. Use merging technique of sorted linked list for that.
2. Keep track of sum of the elements too while doing this and set head of result list based on greater sum till first common node.
3. After this till the current pointers of both lists don’t become NULL we need to adjust the next of prev pointers based on greater sum.

This way it can be done in-place with constant extra space.
Time complexity of the below solution is O(n).
     */

    /*
     List1 =  1->3->30->90->120->240->511
    List2 =  0->3->12->32->90->125->240->249
     */
    public void maxSumList(){
        Node a = new Node(1);
        a.setNext(new Node(3));
        a.getNext().setNext(new Node(30));
        a.getNext().getNext().setNext(new Node(90));
        a.getNext().getNext().getNext().setNext(new Node(120));
        a.getNext().getNext().getNext().getNext().setNext(new Node(240));
        a.getNext().getNext().getNext().getNext().getNext().setNext(new Node(511));

        Node b = new Node(0);
        b.setNext(new Node(3));
        b.getNext().setNext(new Node(12));
        b.getNext().getNext().setNext(new Node(32));
        b.getNext().getNext().getNext().setNext(new Node(90));
        b.getNext().getNext().getNext().getNext().setNext(new Node(125));
        b.getNext().getNext().getNext().getNext().getNext().setNext(new Node(240));
        b.getNext().getNext().getNext().getNext().getNext().getNext().setNext(new Node(249));

        print(a);
        print(b);
        print(finalMaxSumList(a,b));

    }


    // Method to adjust pointers and print final list
    private Node finalMaxSumList(Node a, Node b)
    {
        Node result = null;
        Node headA = a;
        Node headB = b;

        Node prevA = headA;
        Node prevB = headB;

        while (headA != null || headB != null){
            int sumA = 0;
            int sumB = 0;

            while (headA != null && headB != null && headA.getData() != headB.getData()){
                if(headA.getData() < headB.getData()){
                    sumA += headA.getData();
                    headA = headA.getNext();
                }
                else {
                    sumB += headB.getData();
                    headB = headB.getNext();
                }
            }
            if(headA == null){
                while (headB != null){
                    sumB += headB.getData();
                    headB = headB.getNext();
                }
            }
            if(headB == null){
                while (headA != null){
                    sumA += headA.getData();
                    headA = headA.getNext();
                }
            }
            if(prevA == a && prevB == b){
                if(sumA > sumB){
                    result = prevA;
                }
                else {
                    result = prevB;
                }
            }
            else {
                if(sumA > sumB){
                    prevB.setNext(prevA.getNext());
                }
                else {
                    prevA.setNext(prevB.getNext());
                }
            }
            prevA = headA;
            prevB = headB;

            if(headA != null){
                headA = headA.getNext();
            }

            if(headB != null){
                headB = headB.getNext();
            }

        }
        return result;
    }

    /*
    Merge 2 sorted doubly linked list
    Input:-
    5<->10<->15<->20
    4<->8<->12<->16
    Output:-
    4<->5<->8<->10<->12<->15<->16<->20
     */
    public void merge2SortedDoublyLinkedList(){
        Node a = new Node(5);
        a.setNext(new Node(10));
        a.getNext().setPrev(a);
        a.getNext().setNext(new Node(15));
        a.getNext().getNext().setPrev(a.getNext());
        a.getNext().getNext().setNext(new Node(20));
        a.getNext().getNext().getNext().setPrev(a.getNext().getNext());

        Node b = new Node(5);
        b.setNext(new Node(10));
        b.getNext().setPrev(a);
        b.getNext().setNext(new Node(15));
        b.getNext().getNext().setPrev(a.getNext());
        b.getNext().getNext().setNext(new Node(20));
        b.getNext().getNext().getNext().setPrev(a.getNext().getNext());

        Node result = merge2SortedDoublyLinkedList(a,b);
        System.out.println();
        while (result.getNext() != null){
            System.out.print(result.getData());
            result = result.getNext();
        }
        System.out.println();
        while (result.getPrev() != null){

            System.out.print(result.getData());
            result = result.getPrev();
        }


    }

    private Node merge2SortedDoublyLinkedList(Node a, Node b){
        if(a == null){
            return b;
        }
        if( b == null){
            return a;
        }
        Node result = null;
        if(a.getData() < b.getData()){
            result = a;
            result.setNext(merge2SortedDoublyLinkedList(a.getNext(),b));
            result.getNext().setPrev(result);
        }
        else {
            result = b;
            result.setNext(merge2SortedDoublyLinkedList(a,b.getNext()));
            result.getNext().setPrev(result);
        }
        return result;
    }

    public void reverseDoublyLinkedList(){
        head = new Node(1);
        head.setNext(new Node(2));
        head.getNext().setPrev(head);
        head.getNext().setNext(new Node(3));
        head.getNext().getNext().setPrev(head.getNext());
        head.getNext().getNext().setNext(new Node(4));
        head.getNext().getNext().getNext().setPrev(head.getNext().getNext());
        head.getNext().getNext().getNext().setNext(new Node(5));
        head.getNext().getNext().getNext().getNext().setPrev(head.getNext().getNext().getNext());
        reverseDoublyLinkedList(head);
        print(head);

    }

    public void reverseDoublyLinkedList(Node node){
        if(node == null){
            return;
        }
        if(node.getNext() == null){
            head = node;
        }
        Node next = node.getNext();
        node.setNext(node.getPrev());
        node.setPrev(next);
        reverseDoublyLinkedList(next);
    }



    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        //list.getLengthReccOfLinkedList();
        //list.searchElementInLinkedList();
        //list.swap();
        //list.printMiddle();
        //list.getNthNodeFromEndOfALinkedList();
        //list.reverseLinkedList();
        //list.detectLoopInList();
        //list.addOneToLinkedList();
        //list.printCommonIn2SortedList();
        //list.moveEvenToBegeningThenOdd();
        //list.partionListLesserNumberComesBefore();
        //list.findIntersectingNode();
        //list.merge2SortedListInSortedOrder();
        //list.pairWiseSwap();
        //list.splitCircularLinkedListIn2Halfes();
        //list.checkPalindrome();
        //list.pairwiseSwapData();
        //list.reverseKBlocksInList();
        //list.josephusCircle();
        //list.reorderLinkedList();
        //list.rotateListRightByK();
        //list.add2Numbers();
        //list.swapKthNodeFromBeginingToEnd();
        //list.cloneLinkList();
        //list.deleteLastOccurrenceOfKeyFromList();
        //list.sortOnActualValue();
        //list.populateArbitPointer();
        //list.zigZagOrder();
        //list.reverseAlternateKNodes();
        //list.deleteAlternate();
        //list.mergeSort();
        //list.flattenLinkedList();
        //list.findTripletFrom3LinkedListsWhoseSumEqualsN();
        //list.maxSumList();
        //list.merge2SortedDoublyLinkedList();
        list.reverseDoublyLinkedList();
    }
}
