import java.util.Stack;

/*
https://www.geeksforgeeks.org/stack-set-2-infix-to-postfix/
https://youtu.be/ysDharaQXkw
 */
public class InfixToPostfixConversion {

    public boolean isOperator(Character ch){
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '(' || ch == ')' || ch == '^';
    }

    //higher value returned means higher precedence
    public int precedence(Character ch){
        switch (ch){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    public String infixToPostfix(String infix){
        String result = "";
        if(infix == null || infix.isEmpty()){
            return result;
        }
        Stack<Character> stack = new Stack<>();
        for(Character ch : infix.toCharArray()){
            if(!isOperator(ch)){
                result+=ch;
            }
            else if(ch == '('){
                stack.push(ch);
            }
            else if(ch == ')'){
                while (!stack.isEmpty() && stack.peek() != '('){
                    result+=stack.pop();
                }
                if(stack.isEmpty()){
                    break;
                }
                if(stack.peek() == '('){
                    stack.pop();
                }
            }
            else {
                while (!stack.isEmpty() && precedence(ch) <= precedence(stack.peek())){
                    result+=stack.pop();
                }
                stack.push(ch);
            }
        }
        while (!stack.isEmpty()){
            result+=stack.pop();
        }
        return result;
    }


    /*
    We use the same algo to convert Infix to Prefix.

Step 1: Reverse the infix expression i.e A+B*C will become C*B+A. Note while reversing each ‘(‘ will become ‘)’ and each ‘)’
becomes ‘(‘.
Step 2: Obtain the postfix expression of the modified expression i.e CB*A+.
Step 3: Reverse the postfix expression. Hence in our example prefix is +A*BC.
*/

    public static void main(String[] args) {
        InfixToPostfixConversion infixToPostfixConversion = new InfixToPostfixConversion();
        String infix = "a+b*(c^d-e)^(f+g*h)-i";
        String postfix = infixToPostfixConversion.infixToPostfix(infix);
        System.out.println(postfix);
    }
}