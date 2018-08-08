package others;
//https://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/

import java.util.Stack;

public class permutation {

    public static void permute(String str){
        permute(str,0,str.length()-1);
    }

    public static void permute(String str, int low, int high){
        if(low == high){
            System.out.println(str);
        }
        else {
            for(int i = low; i<=high; i++){
                str = swap(str,low,i);
                permute(str,low+1,high);
                str = swap(str,low,i);
            }
        }
    }

    public static String swap(String str,int i, int j){
        char temp;
        char[] charArray = str.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

    public static void main(String[] args) {
        //permutation.permute("abc");
        permutation.permuteString("abc");
    }

    static class Node {
        String str;
        int level;
        public Node(String str, int level){
            this.str = str;
            this.level = level;
        }
    }
    public static void permuteString(String str){
        Node node = new Node(str,0);
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()){
            Node temp = stack.pop();
            int level = temp.level;
            String string = temp.str;
            if(level == str.length()-1){
                System.out.println(temp.str);
            }
            else {
                for(int i = level; i<str.length(); i++){
                    stack.push(new Node(swap(string,level,i),level+1));
                }
            }
        }
    }

    /*
    How to print all permutations iteratively?
A simple solution to use permutations of n-1 elements to generate permutations of n elements.

For example let us see how to generate permutations of size 3 using permutations of size 2.

Permutations of two elements are 1 2 and 2 1.
Permutations of three elements can be obtained by inserting 3 at different positions in all permutations of size 2.
Inserting 3 in different positions of 1 2 leads to 1 2 3, 1 3 2 and 3 1 2.
Inserting 3 in different positions of 2 1 leads to 2 1 3, 2 3 1 and 3 2 1.
     */
}