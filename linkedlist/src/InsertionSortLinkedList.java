/*
Insertion Sort for Singly Linked List

Below is simple insertion sort algorithm for linked list.

1) Create an empty sorted (or result) list
2) Traverse the given list, do following for every node.
......a) Insert current node in sorted way in sorted or result list.
3) Change head of given linked list to head of sorted (or result) list.

 */


public class InsertionSortLinkedList {

    private static Node result;

    public static void insertionSort(Node head){
        result = null;
        Node curr = head;
        while (curr != null){
            Node next = curr.getNext();
            sortedInsert(curr);
            curr = next;
        }
        head = result;
        print(head);
    }

    private static void sortedInsert(Node node){
        if(result == null || result.getData() > node.getData()){
            node.setNext(result);
            result = node;
        }
        else {
            Node curr = result;
            while (curr.getNext() != null && curr.getNext().getData() < node.getData()){
                curr = curr.getNext();
            }
            node.setNext(curr.getNext());
            curr.setNext(node);
        }
    }

    public static void print(Node head){
        Node curr = head;
        while (curr != null){
            System.out.print(curr.getData() + " ");
            curr = curr.getNext();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(30);
        head.setNext(new Node(3));
        head.getNext().setNext(new Node(4));
        head.getNext().getNext().setNext(new Node(20));
        head.getNext().getNext().getNext().setNext(new Node(5));
        print(head);
        insertionSort(head);
    }

}