public class MyAvlTree {

    class Node{
        int data;
        Node left;
        Node right;
        int height;

        public Node(int data){
            this.data = data;
            height = 1;
            left = null;
            right = null;
        }
    }

    Node root = null;

    public int getHeight(Node node){
        if(node == null){
            return 0;
        }
        return node.height;
    }


    public Node rightRotate(Node y){
        Node x = y.left;
        Node t2 = x.right;
        x.right = y;
        y.left = t2;

        x.height = Math.max(getHeight(x.left),getHeight(x.right)) + 1;
        y.height = getHeight(y.left) + getHeight(y.right);

        return x;
    }

    public Node leftRotate(Node x){
        Node y = x.right;
        Node t2 = y.left;

        y.left = x;
        x.right = t2;
        x.height = getHeight(x.left) + getHeight(x.right);
        y.height = getHeight(y.left) + getHeight(y.right);
        return y;
    }

    public int balanceFactor(Node node){
        return getHeight(node.left) - getHeight(node.right);
    }

    public Node insert(Node root, int key){
        if(root == null){
            return new Node(key);
        }
        if(root.data > key){
            root.left = insert(root.left,key);
        }
        if(root.data < key){
            root.right = insert(root.right,key);
        }
        root.height = Math.max(getHeight(root.left),getHeight(root.right)) + 1;
        int balance = balanceFactor(root);
        if(balance > 1 && root.left.data > key){
            root = rightRotate(root);
        }
        else if(balance > 1 && root.left.data < key){
            root.left = leftRotate(root.left);
            root = rightRotate(root);
        }
        else if(balance < -1 && root.right.data < key){
            root = leftRotate(root);
        }
        else if(balance <- 1 && root.right.data > key){
            root.right = rightRotate(root.right);
            root = leftRotate(root);
        }
        return root;
    }

    public void insert(int[] arr){
        for(int i : arr){
            root = insert(root,i);
        }
    }

    public void inorder(){
        inorder(root);
    }

    public void inorder(Node root){
        if(root != null){
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    public void preorder(){
        preorder(root);
    }

    public void preorder(Node root){
        if(root != null){
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }


    public static void main(String[] args) {
        MyAvlTree tree = new MyAvlTree();
        tree.insert(new int[]{10,5,15,4,3,19,20,13,11});
        tree.preorder();
    }



}