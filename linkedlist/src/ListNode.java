/**
 * Created by vaibhavmishra on 9/10/17.
 */
public class ListNode {
    @Override
    public String toString() {
        return "ListNode{" +
                "right=" + getRight() +
                ", down=" + getDown() +
                ", data=" + getData() +
                '}';
    }

    private ListNode right;

    private  ListNode down;

    public ListNode(int data) {
        this.data = data;
        this.right = null;
        this.down = null;
    }

    private int data;

    public ListNode getRight() {
        return right;
    }

    public void setRight(ListNode right) {
        this.right = right;
    }

    public ListNode getDown() {
        return down;
    }

    public void setDown(ListNode down) {
        this.down = down;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
