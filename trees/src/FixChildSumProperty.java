import java.util.Stack;

public class FixChildSumProperty {

    class Node {
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
            left = null;
            right = null;
        }
    }

    public Node createTestTree(){
        Node root = new Node(50);
        root.left = new Node(7);
        root.right = new Node(2);
        root.left.left = new Node(3);
        root.left.right = new Node(5);
        root.right.left = new Node(1);
        root.right.right = new Node(30);
        return root;
    }

    public void inorder(Node root){
        if(root != null){
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    public void fixChildSum(){
        Node root = createTestTree();
        inorder(root);
        System.out.println();
        fixChildSum(root);
        inorder(root);
    }

    public void fixChildSum(Node root){
        Stack<Node> st = new Stack<>();
        st.push(root);
        Node prev = null;
        while (!st.isEmpty()){
            Node curr = st.peek();
            if(prev == null || prev.left == curr || prev.right == curr){
                if(curr.left != null){
                    st.push(curr.left);
                }
                else if(curr.right != null){
                    st.push(curr.right);
                }
                else {
                    st.pop();
                }
            }
            else if(curr.left == prev){
                if(curr.right != null){
                    st.push(curr.right);
                }
                else {
                    Node node = st.pop();
                    checkAndFixChildSum(node);
                }
            }
            else if(curr.right == prev){
                Node node = st.pop();
                checkAndFixChildSum(node);
            }
            prev = curr;
        }
    }

    public void checkAndFixChildSum(Node root){
        int left = root.left != null ? root.left.data : 0;
        int right = root.right != null ? root.right.data : 0;
        int diff = root.data - (left + right);
        if(diff < 0){
            root.data = root.data + (-diff);
        }
        else if(diff > 0){
            increment(root,diff);
        }

    }

    public void increment(Node node, int diff){
        if(node.left != null){
            node.left.data = node.left.data + diff;
            increment(node.left,diff);
        }
        else if(node.right != null){
            node.right.data = node.right.data + diff;
            increment(node.right,diff);
        }
    }

    public static void main(String[] args) {
        FixChildSumProperty fixChildSumProperty = new FixChildSumProperty();
        fixChildSumProperty.fixChildSum();
    }
}