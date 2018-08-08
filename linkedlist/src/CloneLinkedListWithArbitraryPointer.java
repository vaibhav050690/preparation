import java.util.HashMap;

class LinkedNode {
    int data;
    LinkedNode next;
    LinkedNode arbitrary;

    public LinkedNode(int data){
        this.data = data;
        next = null;
        arbitrary = null;
    }

}



public class CloneLinkedListWithArbitraryPointer {

    public void printListWithRandomPointer(LinkedNode head){
        System.out.println();
        LinkedNode curr = head;
        while (curr != null){
            System.out.print(curr.data + "-Arb->" + (curr.arbitrary != null ? curr.arbitrary.data : curr.arbitrary));
            if(curr.next != null){
                System.out.print(",");
            }
            curr = curr.next;
        }
        System.out.println();
    }


    public LinkedNode createList(){
        LinkedNode a = new LinkedNode(1);
        a.next = new LinkedNode(2);
        a.next.next = new LinkedNode(3);
        a.next.next.next = new LinkedNode(4);
        a.next.next.next.next =new LinkedNode(5);
        a.arbitrary = a.next.next;
        a.next.arbitrary = a;
        a.next.next.next.arbitrary = a.next.next;
        a.next.next.arbitrary = a.next.next.next.next;
        a.next.next.next.next.arbitrary = a.next;
        printListWithRandomPointer(a);
        return a;
    }

    public void cloneList(LinkedNode head){
        System.out.println(head == head);
        LinkedNode clonedList = new LinkedNode(-1);
        LinkedNode curr = head;
        LinkedNode curr1 = clonedList;

        HashMap<LinkedNode,LinkedNode> map = new HashMap<>();
        while (curr != null){
            curr1.next = new LinkedNode(curr.data);
            map.put(curr,curr.next);
            curr = curr.next;
            curr1 = curr1.next;
        }
        clonedList = clonedList.next;
        curr = head;
        curr1 = clonedList;
        while (curr != null){
            LinkedNode next = curr.next;
            curr.next = curr1;
            curr = next;
        }
        curr1 = clonedList;
        curr = head;
        while (curr1 != null){
            curr1.arbitrary = curr;
            curr1 = curr1.next;
            curr = map.get(curr);
        }
        curr1 = clonedList;
        curr = head;
        while (curr1 != null){
            curr1.arbitrary = curr1.arbitrary.arbitrary.next;
            curr.next = map.get(curr);
            curr1 = curr1.next;
        }
        printListWithRandomPointer(clonedList);
    }

    public static void main(String[] args) {
        CloneLinkedListWithArbitraryPointer obj = new CloneLinkedListWithArbitraryPointer();
        obj.cloneList(obj.createList());

        //using hashing ,https://www.geeksforgeeks.org/clone-linked-list-next-arbit-pointer-set-2/
        //using constant space, https://www.geeksforgeeks.org/clone-linked-list-next-random-pointer-o1-space/
    }


}