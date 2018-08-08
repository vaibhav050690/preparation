import sun.reflect.generics.tree.Tree;

public class NAryTree {

    class TreeNode {
        TreeNode firstChild;
        TreeNode nextSiblings;
        String data;

        public TreeNode(String data){
            this.data = data;
            firstChild = null;
            nextSiblings = null;
        }
    }

    TreeNode root;

    public void constructTree(){
        root = new TreeNode("A");
        root.firstChild = new TreeNode("B");
        root.firstChild.nextSiblings = new TreeNode("C");
        root.firstChild.nextSiblings.nextSiblings = new TreeNode("D");
        root.firstChild.nextSiblings.nextSiblings.nextSiblings = new TreeNode("E");
        root.firstChild.nextSiblings.nextSiblings.nextSiblings.nextSiblings = new TreeNode("F");
        root.firstChild.nextSiblings.nextSiblings.nextSiblings.nextSiblings.nextSiblings = new TreeNode("G");
        root.firstChild.nextSiblings.nextSiblings.firstChild = new TreeNode("H");
        root.firstChild.nextSiblings.nextSiblings.nextSiblings.firstChild = new TreeNode("I");
        root.firstChild.nextSiblings.nextSiblings.nextSiblings.firstChild.nextSiblings = new TreeNode("J");
        root.firstChild.nextSiblings.nextSiblings.nextSiblings.nextSiblings.firstChild = new TreeNode("K");
        root.firstChild.nextSiblings.nextSiblings.nextSiblings.nextSiblings.firstChild.nextSiblings = new TreeNode("L");
        root.firstChild.nextSiblings.nextSiblings.nextSiblings.nextSiblings.firstChild.nextSiblings.nextSiblings = new TreeNode("M");
        root.firstChild.nextSiblings.nextSiblings.nextSiblings.nextSiblings.nextSiblings.firstChild = new TreeNode("N");
        root.firstChild.nextSiblings.nextSiblings.nextSiblings.firstChild.nextSiblings.firstChild = new TreeNode("P");
        root.firstChild.nextSiblings.nextSiblings.nextSiblings.firstChild.nextSiblings.firstChild.nextSiblings = new TreeNode("Q");
    }

    public void inorder(){
        inorder(root);
    }

    public void inorder(TreeNode root){
        if(root != null){
            inorder(root.firstChild);
            System.out.print(root.data + " ");
            inorder(root.nextSiblings);
        }
    }

    public static void main(String[] args) {
        NAryTree tree = new NAryTree();
        tree.constructTree();
        tree.inorder();
    }
}