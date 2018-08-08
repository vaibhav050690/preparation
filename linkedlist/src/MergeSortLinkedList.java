public class MergeSortLinkedList {

    class Node {
        private int data;
        private Node next;

        public Node (int data){
            this.data = data;
            next = null;
        }
    }

    public Node sortedMergeRec(Node a, Node b){
        if(a == null){
            return b;
        }
        if(b == null){
            return a;
        }
        Node head = null;
        if(a.data < b.data){
            head = a;
            head.next = sortedMergeRec(a.next,b);
        }
        else {
            head = b;
            head.next = sortedMergeRec(a,b.next);
        }
        return head;
    }

    public Node findMiddle(Node node){
        if(node == null || node.next == null){
            return node;
        }
        Node curr = node;
        Node slow = curr;
        Node fast = curr.next;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public Node mergeSort(Node head){
        if(head == null || head.next == null){
            return head;
        }
        Node middle = findMiddle(head);
        Node nextMiddle = middle.next;
        middle.next = null;
        Node left = mergeSort(head);
        Node right = mergeSort(nextMiddle);
        return sortedMergeRec(left,right);
    }

    public void print(Node node){
        System.out.println();
        Node curr = node;
        while (curr != null){
            System.out.print(curr.data);
            if(curr.next != null){
                System.out.print(",");
            }
            curr = curr.next;
        }
        System.out.println();
    }

    public Node createLinkedList(){
        //10->2->13->4->3->9->20->8->11->23
        Node head = new Node(10);
        head.next = new Node(2);
        head.next.next = new Node(13);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(3);
        head.next.next.next.next.next = new Node(9);
        head.next.next.next.next.next.next = new Node(20);
        head.next.next.next.next.next.next.next = new Node(8);
        head.next.next.next.next.next.next.next.next = new Node(11);
        head.next.next.next.next.next.next.next.next.next = new Node(23);
        return head;







    }

    public static void main(String[] args) {
        MergeSortLinkedList mergeSortLinkedList = new MergeSortLinkedList();
        Node head = mergeSortLinkedList.createLinkedList();
        mergeSortLinkedList.print(mergeSortLinkedList.mergeSort(head));

    }

}