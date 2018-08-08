import java.util.Stack;

/*
Prefix and Postfix expressions can be evaluated faster than an infix expression.
This is because we don’t need to process any brackets or follow operator precedence rule.
In postfix and prefix expressions which ever operator comes before will be evaluated first, irrespective of its priority.
Also, there are no brackets in these expressions. As long as we can guarantee that a valid prefix or postfix expression is used,
it can be evaluated with correctness.

We can convert infix to postfix and can covert infix to prefix.

In this article, we will discuss how to evaluate an expression written in postfix notation.
 */

public class PostfixExpressionEvaluation {


    //method assumes operator are only +,-,*,/
    public int evaluatePostfix(String postfix){
        if(postfix == null){
            return -1;
        }
        postfix = postfix.trim();
        if(postfix.isEmpty()){
            return -1;
        }
        Stack<Integer> stack = new Stack<>();
        for(char ch : postfix.toCharArray()){
            if(!isOperator(ch)){
                stack.push(Integer.parseInt(String.valueOf(ch)));
            }
            else {
                int right = stack.pop();
                int left = stack.pop();
                int result = -1;
                switch (ch){
                    case '+':
                        result = left + right;
                        break;
                    case '-':
                        result = left - right;
                        break;
                    case '*':
                        result = left * right;
                        break;
                    case '/':
                        result = left / right;
                        break;
                    default:
                        break;
                }
                stack.push(result);
            }
        }
        return stack.pop();
    }

    public boolean isOperator(Character ch){
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    public static void main(String[] args) {
        PostfixExpressionEvaluation postfixExpressionEvaluation = new PostfixExpressionEvaluation();
        System.out.println(postfixExpressionEvaluation.evaluatePostfix("231*+9-"));

        /*
        To evaluate prefix extression, run the loop from right to left instead of left to right in case of postfix.
         */
    }
}