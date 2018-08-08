/**
 * Created by vaibhavmishra on 30/10/17.
 */
public class Node {

    private int data;

    private Node left;

    private Node right;

    public Node getNextRight() {
        return nextRight;
    }

    public void setNextRight(Node nextRight) {
        this.nextRight = nextRight;
    }

    private Node nextRight;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node() {
    }

    @Override
    public String toString() {
        int leftData = left != null ? left.getData() : -1;
        int rightData = right != null ? right.getData() : -1;
        int nextRightData = nextRight != null ? nextRight.getData() : -1;
        return "Node{" +
                "data=" + data +
                ", left=" + leftData +
                ", right=" + rightData +
                ", nextRight=" + nextRightData +
                '}';

    }

    public Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }


    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public Node getLeft() {

        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
